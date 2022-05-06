package view.submenu;

import controller.ShowController;
import model.Show;
import view.MenuType;

import java.util.ArrayList;
import java.util.Scanner;

import static utils.UtilFunctions.isValidDate;

public class ShowSubMenu extends BaseSubMenu<Show> {
    private final ShowController controller = new ShowController();
    private ArrayList<Show> shows;

    public ShowSubMenu() {
        this.shows = controller.getAll();
    }

    @Override
    public void onGetAll() {
        printShows();
    }

    @Override
    public void onGetByName() {
        String target = getTargetName(MenuType.SHOW);
        Show result = controller.getByName(target);
        if (result != null) {
            System.out.println("SHOW FOUND");
            System.out.println(result);
        } else {
            System.out.println("No show found with name: " + target);
        }
    }

    @Override
    public void onCreate() {
        Show newShow = getNewObj();
        controller.create(newShow);
        System.out.println("SHOW CREATED");
    }

    @Override
    public void onUpdate() {
        printShows();
        String target = getTargetName(MenuType.SHOW);
        Show showToEdit = controller.getByName(target);
        if (showToEdit != null) {
            System.out.println("EDITING SHOW: " + showToEdit.getTitle());
            Show editedShow = getNewObj();
            controller.update(showToEdit, editedShow);
        } else {
            System.out.println("No show found with name: " + target);
        }
    }

    @Override
    public void onDelete() {
        printShows();
        String target = getTargetName(MenuType.SHOW);
        Show showToDelete = controller.getByName(target);
        if (showToDelete != null) {
            System.out.println("DELETING SHOW: " + showToDelete.getTitle());
            controller.delete(showToDelete.getTitle());
        } else {
            System.out.println("No show found with name: " + target);
        }
    }

    @Override
    protected Show getNewObj() {
        Scanner scanner = new Scanner(System.in);
        Show show = new Show();

        System.out.print("Enter show title:");
        show.setTitle(scanner.nextLine());

        System.out.print("Enter show description:");
        show.setDescription(scanner.nextLine());

        System.out.print("Enter show season:");
        show.setSeason(scanner.nextLine());

        String date;
        do {
            System.out.print("Enter the release date of the show in dd/MM/yyyy format: ");
            date = scanner.nextLine();
            if (!isValidDate(date)) {
                System.out.println("Invalid date format");
            }
        } while(!isValidDate(date));
        show.setReleaseDate(date);

        return show;
    }

    private void printShows() {
        shows = controller.getAll();
        for (Show show : shows) {
            System.out.println(show.getTitle());
        }
    }
}
