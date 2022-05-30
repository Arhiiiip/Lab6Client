package command;

import request.RRHandler;

import java.io.IOException;

public class ShowCommand extends CommandAbstract {

    public ShowCommand(String name, String description, boolean isArgument, RRHandler rrHandler) {
        super(name, description, isArgument, rrHandler);
    }

    @Override
    public void execute(String arg) {
        try {
            rrHandler.req(this.getName(), arg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
