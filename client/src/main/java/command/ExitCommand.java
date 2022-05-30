package command;

import request.RRHandler;

import java.io.IOException;

public class ExitCommand extends CommandAbstract {

    public ExitCommand(String name, String description, boolean isArgument, RRHandler rrHandler) {
        super(name, description, isArgument, rrHandler);
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
