package command;

import request.Serializator;
import utility.MovieFactory;

public class ExitCommand extends CommandAbstract {

    MovieFactory movieFactory;
    boolean temp;

    public ExitCommand(String name, String description, boolean isArgument) {
        super(name, description, isArgument);
    }

    public void execute(String arg) {
        Serializator serializator = new Serializator();
    }
}
