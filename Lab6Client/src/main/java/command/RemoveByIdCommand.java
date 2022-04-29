package command;

import request.Serializator;

public class RemoveByIdCommand extends CommandAbstract {

    public RemoveByIdCommand(String name, String description, boolean isArgument) {
        super(name, description, isArgument);
    }

    public void execute(String arg) {
        Serializator serializator = new Serializator();
    }
}
