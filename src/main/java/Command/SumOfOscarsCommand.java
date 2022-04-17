package Command;

import Data.Movie;
import utility.MovieFactory;

public class SumOfOscarsCommand extends CommandAbstract {

    MovieFactory movieFactory;

    public SumOfOscarsCommand(String name, String description, MovieFactory movieFactory, boolean isArgument) {
        super(name, description, isArgument);
        this.movieFactory = movieFactory;
    }

    public void execute(String arg) {
        int sum = 0;
        for (Movie movie : movieFactory.getCollectionForWork()) {
            sum = sum + movie.getOscarsCount();
        }
        System.out.println(sum);
    }
}