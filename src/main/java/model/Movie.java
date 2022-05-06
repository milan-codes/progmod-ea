package model;

public class Movie {
    /**
     * The name of the exam.
     */
    private String title;
    /**
     * The description of the exam.
     */
    private String description;
    /**
     * The classroom in which the exam will be held.
     */
    private String releaseYear;
    /**
     * The duration of the exam.
     */
    private String runtime;
    /**
     * The date of the exam.
     */
    private String review;

    public Movie() {
    }

    public Movie(String title, String description, String releaseYear, String runtime, String review) {
        this.title = title;
        this.description = description;
        this.releaseYear = releaseYear;
        this.runtime = runtime;
        this.review = review;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(title).append("\n");
        sb.append("Description: ").append(description).append("\n");
        sb.append("Release: ").append(releaseYear).append("\n");
        sb.append("Runtime: ").append(runtime).append("\n");
        sb.append("Review: ").append(review).append("\n");

        return sb.toString();
    }

    /**
     * Static class to create a movie object without exposing the creation logic
     */
    public static class MovieFactory implements Factory<Movie> {
        public Movie factory() {
            return new Movie();
        }
    }
}
