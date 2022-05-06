package controller;

import model.Show;
import utils.XMLReader;
import utils.XMLWriter;

import java.util.ArrayList;

public class ShowController extends BaseController<Show> {
    private ArrayList<Show> shows;

    public ShowController() {
        shows = new ArrayList<>();
    }

    private void updateShowList() {
        XMLReader<Show> reader = new XMLReader<>("src/main/resources/shows.xml", new Show.ShowFactory());
        reader.read();
        shows = reader.getList();
    }

    private void saveShowList() {
        XMLWriter<Show> writer = new XMLWriter<>("src/main/resources/shows.xml", new Show.ShowFactory(), shows);
        writer.write();
    }

    @Override
    public ArrayList<Show> getAll() {
        updateShowList();
        return shows;
    }

    @Override
    public Show getByName(String name) {
        updateShowList();
        return shows.stream().filter(show -> show.getTitle().equals(name)).findFirst().orElse(null);
    }

    @Override
    public Show create(Show newObj) {
        shows.add(newObj);
        saveShowList();
        return newObj;
    }

    @Override
    public Show update(Show oldObj, Show newObj) {
        updateShowList();
        shows.removeIf(show -> show.getTitle().equals(oldObj.getTitle()));
        shows.add(newObj);
        saveShowList();

        return newObj;
    }

    @Override
    public void delete(String name) {
        updateShowList();
        shows.removeIf(show -> show.getTitle().equals(name));
        saveShowList();
    }
}
