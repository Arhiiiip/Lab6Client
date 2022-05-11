package command;

<<<<<<<< Updated upstream:src/main/java/command/ShowCommand.java
import data.Movie;
import utility.MovieFactory;
========
import utility.RRHandler;

import java.io.IOException;
>>>>>>>> Stashed changes:src/main/java/Command/ShowCommand.java

public class ShowCommand extends CommandAbstract {

    RRHandler rrHandler;

    public ShowCommand(String name, String description, boolean isArgument, RRHandler rrHandler) {
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
