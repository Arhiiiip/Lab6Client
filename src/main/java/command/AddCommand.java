package command;

import data.Movie;
import utility.MovieFactory;
import utility.RRHandler;

import java.io.IOException;

/**
 * Класс команды add
 * Которая добавляет элемент в коллекцию
 */

public class AddCommand extends CommandAbstract {

    RRHandler rrHandler;

    public AddCommand(String name, String description, boolean isArgument, RRHandler rrHandler) {
        super(name, description, isArgument);
        this.rrHandler = rrHandler;
    }

    @Override
    public void execute(String arg) {
        Movie movie = MovieFactory.GetMovieFromConsole();
        try {
            rrHandler.reqOb(this.getName(), movie);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
