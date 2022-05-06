package model;

/**
 * A simple factory interface to create objects without exposing the creation logic.
 * All model classes must implement this interface.
 *
 * @param <T> The type of object to create.
 */
public interface Factory<T> {
    T factory();
}
