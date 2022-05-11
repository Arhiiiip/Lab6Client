package command;

<<<<<<<< Updated upstream:src/main/java/command/RemoveLowerCommand.java
import data.Movie;
import utility.MovieFactory;
========
import utility.RRHandler;
>>>>>>>> Stashed changes:src/main/java/Command/RemoveLowerCommand.java

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
