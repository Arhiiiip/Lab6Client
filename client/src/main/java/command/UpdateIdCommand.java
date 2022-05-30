package command;

import utility.ObjectForServer;
import data.Movie;
import utility.MovieFactory;
import request.RRHandler;

import java.io.IOException;

public class UpdateIdCommand extends CommandAbstract {

    MovieFactory movieFactory;

    public UpdateIdCommand(String name, String description, boolean isArgument, RRHandler rrHandler, MovieFactory movieFactory) {
        super(name, description, isArgument, rrHandler);
        this.movieFactory = movieFactory;
    }

    @Override
    public void execute(String arg) {
        try {
            rrHandler.req(this.getName(), arg);
            ObjectForServer response = rrHandler.res();
            if(response.isAnswerB()){
                Movie movie = movieFactory.GetMovieFromConsole();
                rrHandler.reqOb(this.getName(), movie);
            } else {
                System.out.println("Not this id");
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
