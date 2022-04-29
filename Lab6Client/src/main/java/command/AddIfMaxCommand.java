package command;

import request.Serializator;
import utility.MovieFactory;

public class AddIfMaxCommand extends CommandAbstract {

    MovieFactory movieFactory;

    public AddIfMaxCommand(String name, String description, boolean isArgument) {
        super(name, description, isArgument);
    }

    public void execute(String arg) {
        Serializator serializator = new Serializator();
    }
}
