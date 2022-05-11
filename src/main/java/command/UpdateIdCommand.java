package command;

import data.Movie;
import utility.MovieFactory;
import utility.ObjectForServer;
import utility.RRHandler;

import java.io.IOException;

public class UpdateIdCommand extends CommandAbstract {

    RRHandler rrHandler;

    public UpdateIdCommand(String name, String description, boolean isArgument, RRHandler rrHandler) {
        super(name, description, isArgument);
        this.rrHandler = rrHandler;
    }

    @Override
    public void execute(String arg) {
        try {
            rrHandler.req(this.getName(), arg);
            ObjectForServer response = rrHandler.res();
            if(response.isAnswerB()){
                Movie movie = MovieFactory.GetMovieFromConsole();
                rrHandler.reqOb(this.getName(), movie);
            } else {
                System.out.println("Not this id");
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
