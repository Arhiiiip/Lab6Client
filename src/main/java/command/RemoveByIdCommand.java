package command;

<<<<<<<< Updated upstream:src/main/java/command/RemoveByIdCommand.java
import data.Movie;
import utility.MovieFactory;
========
import utility.RRHandler;

import java.io.IOException;
>>>>>>>> Stashed changes:src/main/java/Command/RemoveByIdCommand.java

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
