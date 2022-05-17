package command;

import data.Movie;
import utility.MovieFactory;
import utility.ObjectForServer;
import utility.RRHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ShowCommand extends CommandAbstract {

    MovieFactory movieFactory;
    RRHandler rrHandler;

    public ShowCommand(String name, String description, MovieFactory movieFactory, boolean isArgument, RRHandler rrHandler) {
        super(name, description, isArgument);
        this.movieFactory = movieFactory;
        this.rrHandler = rrHandler;
    }

    public void execute(ObjectForServer arg) {
        String result = "";
        List list = new ArrayList<>();
        list.addAll(movieFactory.getCollectionForWork());
        Collections.sort(list, new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {
                return o1.compareTo(o2);
            }
        });
        for (Object movie : list) {
            result = result + "\n" + movie.toString();
        }
        rrHandler.res(result);
    }
}
