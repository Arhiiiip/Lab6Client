package command;

import request.RRHandler;

import java.io.IOException;

public class RemoveByIdCommand extends CommandAbstract {

    RRHandler rrHandler;

    public RemoveByIdCommand(String name, String description, boolean isArgument, RRHandler rrHandler) {
        super(name, description, isArgument);
        this.rrHandler = rrHandler;
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