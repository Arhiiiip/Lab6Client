package command;

import data.Movie;
import utility.MovieFactory;
import utility.ObjectForServer;
import utility.RRHandler;

public class AverageOfOscarsCommand extends CommandAbstract {

    MovieFactory movieFactory;
    RRHandler rrHandler;

    public AverageOfOscarsCommand(String name, String description, MovieFactory movieFactory, boolean isArgument, RRHandler rrHandler) {
        super(name, description, isArgument);
        this.movieFactory = movieFactory;
        this.rrHandler = rrHandler;
    }

    public void execute(ObjectForServer arg) {
        try {
            int sum = 0;
            for (Movie movie : movieFactory.getCollectionForWork()) {
                sum = sum + movie.getOscarsCount();
            }
            int res = sum / movieFactory.getCollectionForWork().size();
            String result;
            result = String.valueOf(res);
            rrHandler.res(result);
        }catch (ArithmeticException e){
            String message = "The collection is empty, But it is not divisible by zero if you did not study at school.\n" +
                    "If you're going to be smart, we'll turn you off and ban you)";
            rrHandler.res(message);
        }
    }
}
