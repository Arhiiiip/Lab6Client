package command;

import utility.MovieFactory;
import utility.ObjectForServer;
import utility.RRHandler;

public class ExitCommand extends CommandAbstract {

    MovieFactory movieFactory;
    RRHandler rrHandler;
    boolean temp;

    public ExitCommand(String name, String description, MovieFactory movieFactory, boolean isArgument, RRHandler rrHandler) {
        super(name, description, isArgument);
        this.movieFactory = movieFactory;
        this.rrHandler = rrHandler;
    }

    public void execute(ObjectForServer arg) {
        System.exit(0);
    }
}
