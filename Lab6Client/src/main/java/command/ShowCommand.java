package command;

import request.Serializator;
import utility.MovieFactory;

public class ShowCommand extends CommandAbstract {

    MovieFactory movieFactory;

    public ShowCommand(String name, String description, boolean isArgument) {
        super(name, description, isArgument);
    }

    public void execute(String arg) {
        Serializator serializator = new Serializator();
    }
}
