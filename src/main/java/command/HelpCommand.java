package command;

import utility.RRHandler;

import java.io.IOException;
import java.util.Map;

public class HelpCommand extends CommandAbstract{

    private transient Map<String, CommandAbstract> commands;
    RRHandler rrHandler;

    public HelpCommand(String name, String description, Map<String, CommandAbstract> commands, boolean isArgument, RRHandler rrHandler) {
        super(name, description, isArgument);
        this.commands = commands;
        this.rrHandler = rrHandler;
    }

    @Override
    public void execute(String arg) {
        for (Map.Entry<String, CommandAbstract> entry : commands.entrySet()) {
            System.out.println(entry.getValue().toString());
        }
        try {
            rrHandler.req(this.getName(), arg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
