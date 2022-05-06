package view.submenu;

import controller.MovieController;
import model.Movie;
import view.MenuType;

import java.util.ArrayList;
import java.util.Scanner;

import static utils.UtilFunctions.isValidDate;

public class MovieSubMenu extends BaseSubMenu<Movie> {
    private final MovieController controller = new MovieController();
    private ArrayList<Movie> movies;

    public MovieSubMenu() {
        this.movies = controller.getAll();
    }

    @Override
    public void onGetAll() {
        printMovies();
    }

    @Override
    public void onGetByName() {
        String target = getTargetName(MenuType.MOVIE);
        Movie result = controller.getByName(target);
        if (result != null) {
            System.out.println("MOVIE FOUND");
            System.out.println(result);
        } else {
            System.out.println("No movie found with name: " + target);
        }
    }

    @Override
    public void onCreate() {
        Movie userMovie = getNewObj();
        controller.create(userMovie);
        System.out.println("MOVIE CREATED");
    }

    @Override
    public void onUpdate() {
        printMovies();
        String target = getTargetName(MenuType.MOVIE);
        Movie movieToEdit = controller.getByName(target);
        if (movieToEdit != null) {
            System.out.println("EDITING MOVIE " + movieToEdit.getTitle());
            Movie editedMovie = getNewObj();
            controller.update(movieToEdit, editedMovie);
        } else {
            System.out.println("No movie found with name: " + target);
        }
    }

    @Override
    public void onDelete() {
        printMovies();
        String target = getTargetName(MenuType.MOVIE);
        Movie movieToDelete = controller.getByName(target);
        if (movieToDelete != null) {
            System.out.println("DELETING MOVIE " + movieToDelete.getTitle());
            controller.delete(movieToDelete.getTitle());
        } else {
            System.out.println("No movie found with name: " + target);
        }
    }

    @Override
    protected Movie getNewObj() {
        Scanner scanner = new Scanner(System.in);
        Movie movie = new Movie();

        System.out.print("Enter movie title: ");
        movie.setTitle(scanner.nextLine());

        System.out.print("Enter movie description: ");
        movie.setDescription(scanner.nextLine());

        int num = -1;
        do {
            System.out.print("Enter movie runtime in minutes: ");
            if (scanner.hasNextInt()) {
                num = scanner.nextInt();
                scanner.nextLine();
            } else {
                scanner.nextLine();
                System.out.println("Input must be an integer");
            }
            if (num < 0) {
                System.out.println("Duration must be positive");
            }
        } while (num < 0);
        movie.setRuntime(String.valueOf(num));

        String date;
        do {
            System.out.print("Enter release date in dd/MM/yyyy format: ");
            date = scanner.nextLine();
            if (!isValidDate(date)) {
                System.out.println("Invalid date format");
            }
        } while(!isValidDate(date));
        movie.setReleaseYear(date);

        System.out.print("Enter your review: ");
        movie.setReview(scanner.nextLine());

        return movie;
    }

    private void printMovies() {
        movies = controller.getAll();
        for (Movie movie : movies) {
            System.out.println(movie.getTitle());
        }
    }
}
