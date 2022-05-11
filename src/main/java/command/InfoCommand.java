package command;

import utility.RRHandler;

import java.io.IOException;

public class InfoCommand extends CommandAbstract {

    RRHandler rrHandler;

    public InfoCommand(String name, String description, boolean isArgument, RRHandler rrHandler) {
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
