package command;

import data.Movie;
import utility.MovieFactory;
import utility.ObjectForServer;
import utility.RRHandler;

import java.util.LinkedHashSet;

public class RemoveLowerCommand extends CommandAbstract {

    MovieFactory movieFactory;
    RRHandler rrHandler;

    public RemoveLowerCommand(String name, String description, MovieFactory movieFactory, boolean isArgument, RRHandler rrHandler) {
        super(name, description, isArgument);
        this.movieFactory = movieFactory;
        this.rrHandler = rrHandler;
    }

    public void execute(ObjectForServer arg) {
        long oscarsCountFromUser = Integer.valueOf(arg.getArg());
        LinkedHashSet<Movie> collectionForRemove = new LinkedHashSet<Movie>();
        boolean isElements = false;
        for (Movie movie : movieFactory.getCollectionForWork()) {
            if (movie.getOscarsCount() < oscarsCountFromUser) {
                collectionForRemove.add(movie);
                isElements = true;
            }
        }
        if (isElements) {
            movieFactory.getCollectionForWork().removeAll(collectionForRemove);
            movieFactory.getCollectionManager().setDateUpdate();
            String message = collectionForRemove.size() + "elements was deleted";
            rrHandler.res(message);
        } else {
            String message = "There is no such element";
            rrHandler.res(message);
        }
    }
}
