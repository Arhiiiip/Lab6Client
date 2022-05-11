package command;

<<<<<<<< Updated upstream:src/main/java/command/CountGreaterGenreCommand.java
import data.Movie;
import data.MovieGenre;
import utility.MovieFactory;
========
import utility.RRHandler;

import java.io.IOException;
>>>>>>>> Stashed changes:src/main/java/Command/CountGreaterGenreCommand.java

public class CountGreaterGenreCommand extends CommandAbstract {

    RRHandler rrHandler;

    public CountGreaterGenreCommand(String name, String description, boolean isArgument, RRHandler rrHandler) {
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
