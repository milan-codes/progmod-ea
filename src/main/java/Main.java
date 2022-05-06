import view.Menu;
import view.MenuType;
import view.submenu.MovieSubMenu;
import view.submenu.ShowSubMenu;

public class Main {
    private static final Menu menu = Menu.getInstance();

    public static void main(String[] args) {
        do {
            int mainMenu = menu.show(MenuType.MAIN);
            switch (mainMenu) {
                case 1 -> handleMovies();
                case 2 -> handleTasks();
                case 3 -> System.exit(0);
                default -> System.out.println("Invalid input");
            }
        } while (true);
    }

    static void handleMovies() {
        MovieSubMenu movieSubMenu = new MovieSubMenu();
        do {
            int movieMenu = menu.show(MenuType.MOVIE);
            switch (movieMenu) {
                case 1:
                    movieSubMenu.onGetAll();
                    break;
                case 2:
                    movieSubMenu.onGetByName();
                    break;
                case 3:
                    movieSubMenu.onCreate();
                    break;
                case 4:
                    movieSubMenu.onUpdate();
                    break;
                case 5:
                    movieSubMenu.onDelete();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid input");
            }
        } while (true);

    }

    static void handleTasks() {
        ShowSubMenu showSubMenu = new ShowSubMenu();
        do {
            int showMenu = menu.show(MenuType.SHOW);
            switch (showMenu) {
                case 1:
                    showSubMenu.onGetAll();
                    break;
                case 2:
                    showSubMenu.onGetByName();
                    break;
                case 3:
                    showSubMenu.onCreate();
                    break;
                case 4:
                    showSubMenu.onUpdate();
                    break;
                case 5:
                    showSubMenu.onDelete();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid input");
            }
        } while (true);
    }
}
