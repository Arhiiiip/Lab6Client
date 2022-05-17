package command;

import data.Movie;
import utility.MovieFactory;
import utility.ObjectForServer;
import utility.RRHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

public class UpdateIdCommand extends CommandAbstract {

    MovieFactory movieFactory;
    RRHandler rrHandler;

    public UpdateIdCommand(String name, String description, MovieFactory movieFactory, boolean isArgument, RRHandler rrHandler) {
        super(name, description, isArgument);
        this.movieFactory = movieFactory;
        this.rrHandler = rrHandler;
    }

    public void execute(ObjectForServer arg) {
        long idFromUser = Long.parseLong(arg.getArg());
        boolean isId = false;
        for (Movie movie : movieFactory.getCollectionForWork()) {
            if (movie.getId() == idFromUser) {
                movieFactory.getCollectionForWork().remove(movie);
                isId = true;
                movieFactory.getCollectionManager().setDateUpdate();
                RRHandler.resB(true);
                break;
            }
        }
        if (!isId) {
            RRHandler.resB(false);
        } else {
            InputStream stream;
            try {
                stream = rrHandler.getSocket().getInputStream();
                ObjectInputStream objectInputStream = new ObjectInputStream(stream);
                ObjectForServer command = (ObjectForServer) objectInputStream.readObject();
                Movie movieForChange = command.getMovie();
                movieForChange.setId(idFromUser);
                movieFactory.getCollectionForWork().add(movieForChange);
                String message = "Element was updated";
                rrHandler.res(message);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        String message = "(((";
        rrHandler.res(message);
    }
}
