package command;

import request.RRHandler;

import java.io.IOException;

public class ExitCommand extends CommandAbstract {


    RRHandler rrHandler;

    public ExitCommand(String name, String description, boolean isArgument, RRHandler rrHandler) {
        super(name, description, isArgument);
        this.rrHandler = rrHandler;
    }

    @Override
    public void execute(String arg) {
        try {
            rrHandler.req("save", arg);
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
