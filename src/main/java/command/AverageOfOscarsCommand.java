package command;

<<<<<<<< Updated upstream:src/main/java/command/AverageOfOscarsCommand.java
import data.Movie;
import utility.MovieFactory;
========
import utility.RRHandler;

import java.io.IOException;
>>>>>>>> Stashed changes:src/main/java/Command/AverageOfOscarsCommand.java

public class AverageOfOscarsCommand extends CommandAbstract {

    RRHandler rrHandler;

    public AverageOfOscarsCommand(String name, String description, boolean isArgument, RRHandler rrHandler) {
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
