package command;

import data.Movie;
import utility.MovieFactory;
import request.RRHandler;

import java.io.IOException;

public class AddIfMaxCommand extends CommandAbstract {

    MovieFactory movieFactory;

    public AddIfMaxCommand(String name, String description, boolean isArgument, RRHandler rrHandler, MovieFactory movieFactory) {
        super(name, description, isArgument, rrHandler);
        this.movieFactory = movieFactory;
    }

    @Override
    public void execute(String arg) {
        Movie movie = movieFactory.GetMovieFromConsole();
        try {
            rrHandler.reqOb(this.getName(), movie);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
