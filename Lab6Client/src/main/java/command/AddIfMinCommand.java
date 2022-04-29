package command;

import request.Serializator;
import utility.MovieFactory;

public class AddIfMinCommand extends CommandAbstract {

    MovieFactory movieFactory;

    public AddIfMinCommand(String name, String description, boolean isArgument) {
        super(name, description, isArgument);
    }

    public void execute(String arg) {
        Serializator serializator = new Serializator();
    }
}
