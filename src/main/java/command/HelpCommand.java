package command;

import utility.MovieFactory;

import java.util.Map;

public class HelpCommand extends CommandAbstract {
    private Map<String, CommandAbstract> commands;

    MovieFactory movieFactory;

    public HelpCommand(String name, String description, MovieFactory movieFactory, Map<String, CommandAbstract> commands, boolean isArgument) {
        super(name, description, isArgument);
        this.commands = commands;
        this.movieFactory = movieFactory;
    }

    public void execute(String arg) {
        for (Map.Entry<String, CommandAbstract> entry : commands.entrySet()) {
            System.out.println(entry.getValue().toString());
        }
    }
}
