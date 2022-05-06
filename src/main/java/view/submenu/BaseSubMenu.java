package view.submenu;

import view.MenuType;

import java.util.Scanner;

/**
 * Base class for all submenus.
 */
public abstract class BaseSubMenu<T> {

    /**
     * This method is used to get all instances of the T class
     */
    public abstract void onGetAll();

    /**
     * This method is used to get an instance of the T class by its name
     */
    public abstract void onGetByName();

    /**
     * This method is used to create an instance of the T class, and then add it to the database
     */
    public abstract void onCreate();

    /**
     * This method is used to update an instance of the T class
     */
    public abstract void onUpdate();

    /**
     * This method is used to delete an instance of the T class
     */
    public abstract void onDelete();

    /**
     * This method is used to create a new instance of the T class by user input
     *
     * @return New T object
     */
    protected abstract T getNewObj();

    /**
     * This method is used to get a target object's name
     *
     * @param menuType the type of menu to be displayed
     * @return Target name
     */
    protected String getTargetName(MenuType menuType) {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Enter a %s name: ", menuType.name());
        return scanner.nextLine();
    }
}
