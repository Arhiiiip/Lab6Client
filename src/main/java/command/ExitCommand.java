package command;

import utility.MovieFactory;

public class ExitCommand extends CommandAbstract {

    MovieFactory movieFactory;
    boolean temp;

    public ExitCommand(String name, String description, MovieFactory movieFactory, boolean isArgument) {
        super(name, description, isArgument);
        this.movieFactory = movieFactory;
    }

    public void execute(String arg) {
        System.exit(0);
    }
}
