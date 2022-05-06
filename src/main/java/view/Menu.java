package view;

import java.util.Scanner;

/**
 * This class is the main menu of the program.
 * The class is singleton to ensure that only one instance is created during runtime.
 */
public class Menu {

    private static Menu instance = null;
    private final Scanner scanner = new Scanner(System.in);

    private final String[] mainMenuText = {"<1> Manage movies", "<2> Manage tv shows", "<3> Exit program"};
    private final String[] subMenuText = {"<1> Show all", "<2> Get by name", "<3> Add", "<4> Edit", "<5> Remove", "<6> Go back"};

    private Menu() {
        printGreeting();
    }

    /**
     * This method is used to get the instance of the menu.
     *
     * @return the instance of the menu
     */
    public static Menu getInstance() {
        if (instance == null) {
            instance = new Menu();
        }
        return instance;
    }

    /**
     * This method is used to print the greeting to the user.
     */
    private void printGreeting() {
        System.out.println("Welcome to our TV tracker program!");
    }

    /**
     * This method is used to print the main menu.
     *
     * @param type the type of the menu
     * @return the user's choice
     */
    public int show(MenuType type) {
        int result = -1;
        System.out.printf("MANAGE %s\n", type.name());
        System.out.println("-------------------------");
        if (type != MenuType.MAIN) {
            for (String subMenuItem : subMenuText) {
                System.out.println(subMenuItem);
            }
        } else {
            for (String mainMenuItem : mainMenuText) {
                System.out.println(mainMenuItem);
            }
        }
        System.out.println("-------------------------");
        System.out.print("Enter your choice: ");
        if (scanner.hasNextInt()) {
            result = scanner.nextInt();
        }
        scanner.nextLine();
        return result;
    }
}
