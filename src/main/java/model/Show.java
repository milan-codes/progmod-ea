package model;

public class Show {
    /**
     * The tv show's name.
     */
    private String title;
    /**
     * The description of the tv show.
     */
    private String description;
    /**
     * Season of the tv show.
     */
    private String season;
    /**
     * Release date of the tv show.
     */
    private String releaseDate;

    public Show() {
    }

    public Show(String title, String description, String releaseDate, String season) {
        this.title = title;
        this.description = description;
        this.releaseDate = releaseDate;
        this.season = season;
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

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Title: ").append(title).append("\n");
        sb.append("Description: ").append(description).append("\n");
        sb.append("Season: ").append(season).append("\n");
        sb.append("Release date: ").append(releaseDate).append("\n");
        return sb.toString();
    }

    /**
     * Static class to create a show object without exposing the creation logic
     */
    public static class ShowFactory implements Factory<Show> {
        public Show factory() {
            return new Show();
        }
    }
}
