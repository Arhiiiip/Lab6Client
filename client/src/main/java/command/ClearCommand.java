package command;

import request.RRHandler;

public class ClearCommand extends CommandAbstract {


    public ClearCommand(String name, String description, boolean isArgument, RRHandler rrHandler) {
        super(name, description, isArgument, rrHandler);
    }
}
