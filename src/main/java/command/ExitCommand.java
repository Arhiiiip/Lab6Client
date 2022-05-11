package command;

import utility.RRHandler;

public class ExitCommand extends CommandAbstract {


    RRHandler rrHandler;

    public ExitCommand(String name, String description, boolean isArgument, RRHandler rrHandler) {
        super(name, description, isArgument);
        this.rrHandler = rrHandler;
    }

    @Override
    public void execute(String arg) {
        System.exit(0);
    }
}
