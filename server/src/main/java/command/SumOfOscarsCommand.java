package command;

import data.Movie;
import utility.MovieFactory;
import utility.ObjectForServer;
import utility.RRHandler;

public class SumOfOscarsCommand extends CommandAbstract {

    MovieFactory movieFactory;
    RRHandler rrHandler;

    public SumOfOscarsCommand(String name, String description, MovieFactory movieFactory, boolean isArgument, RRHandler rrHandler) {
        super(name, description, isArgument);
        this.movieFactory = movieFactory;
        this.rrHandler = rrHandler;
    }

    public void execute(ObjectForServer arg) {
        String result = "";
        int sum = movieFactory.getCollectionForWork().stream().map(Movie::getOscarsCount).reduce(0, Integer::sum);
        result = result + sum;
        rrHandler.res(result);
    }
}