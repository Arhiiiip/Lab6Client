package Command;

import Data.Movie;
import utility.MovieFactory;

public class ShowCommand extends CommandAbstract {

    MovieFactory movieFactory;

    public ShowCommand(String name, String description, MovieFactory movieFactory, boolean isArgument) {
        super(name, description, isArgument);
        this.movieFactory = movieFactory;
    }

    public void execute(String arg) {
        for (Movie movie : movieFactory.getCollectionForWork()) {
            System.out.println(movie.toString());
        }
    }
}
