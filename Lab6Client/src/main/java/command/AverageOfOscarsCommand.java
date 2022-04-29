package command;

import request.Serializator;
import utility.MovieFactory;

public class AverageOfOscarsCommand extends CommandAbstract {

    MovieFactory movieFactory;

    public AverageOfOscarsCommand(String name, String description, boolean isArgument) {
        super(name, description, isArgument);
    }

    public void execute(String arg) {
        Serializator serializator = new Serializator();
    }
}
