package controller;

import model.Movie;
import utils.XMLReader;
import utils.XMLWriter;

import java.util.ArrayList;

public class MovieController extends BaseController<Movie> {
    private ArrayList<Movie> movies;

    public MovieController() {
        updateMovieList();
    }

    private void updateMovieList() {
        XMLReader<Movie> reader = new XMLReader<>("src/main/resources/movies.xml", new Movie.MovieFactory());
        reader.read();
        movies = reader.getList();
    }

    private void saveMovieList() {
        XMLWriter<Movie> writer = new XMLWriter<>("src/main/resources/movies.xml", new Movie.MovieFactory(), movies);
        writer.write();
    }

    @Override
    public ArrayList<Movie> getAll() {
        updateMovieList();
        return movies;
    }

    @Override
    public Movie getByName(String name) {
        updateMovieList();
        return movies.stream().filter(movie -> movie.getTitle().equals(name)).findFirst().orElse(null);
    }

    @Override
    public Movie create(Movie newObj) {
        movies.add(newObj);
        saveMovieList();
        return newObj;
    }

    @Override
    public Movie update(Movie oldObj, Movie newObj) {
        updateMovieList();
        movies.removeIf(movie -> movie.getTitle().equals(oldObj.getTitle()));
        movies.add(newObj);
        saveMovieList();

        return newObj;
    }

    @Override
    public void delete(String name) {
        updateMovieList();
        movies.removeIf(movie -> movie.getTitle().equals(name));
        saveMovieList();
    }
}
