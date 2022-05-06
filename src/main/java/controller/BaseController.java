package controller;

import java.util.ArrayList;

/**
 * An abstract class that all controllers must extend.
 *
 * @param <T> The type of the controller.
 */
public abstract class BaseController<T> {
    /**
     * This method is used to retrieve all T objects from the database.
     *
     * @return An ArrayList of the retrieved T objects.
     */
    public abstract ArrayList<T> getAll();

    /**
     * This method is used to retrieve a T object by its name from the database.
     *
     * @param name The name of the object to retrieve.
     * @return The retrieved object.
     */
    public abstract T getByName(String name);

    /**
     * This method is used to create a T object and save it to the database.
     *
     * @param newObj The object to be created.
     * @return The saved object.
     */
    public abstract T create(T newObj);

    /**
     * This method is used to update a T object in the database.
     *
     * @param oldObj The object to be updated.
     * @param newObj The new object to be used.
     * @return The saved object.
     */
    public abstract T update(T oldObj, T newObj);

    /**
     * This method is used to delete a T object from the database.
     *
     * @param name The name of the object to be deleted.
     */
    public abstract void delete(String name);
}
