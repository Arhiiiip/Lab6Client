package command;

import request.RRHandler;

import java.io.IOException;

public class RemoveLowerCommand extends CommandAbstract {

    RRHandler rrHandler;

    public RemoveLowerCommand(String name, String description, boolean isArgument, RRHandler rrHandler) {
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
