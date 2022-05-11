package command;

<<<<<<<< Updated upstream:src/main/java/command/SumOfOscarsCommand.java
import data.Movie;
import utility.MovieFactory;
========
import utility.RRHandler;

import java.io.IOException;
>>>>>>>> Stashed changes:src/main/java/Command/SumOfOscarsCommand.java

public class SumOfOscarsCommand extends CommandAbstract {

    RRHandler rrHandler;

    public SumOfOscarsCommand(String name, String description, boolean isArgument, RRHandler rrHandler) {
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