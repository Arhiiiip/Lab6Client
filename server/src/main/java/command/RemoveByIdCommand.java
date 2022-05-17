package command;

import data.Movie;
import utility.MovieFactory;
import utility.ObjectForServer;
import utility.RRHandler;

public class RemoveByIdCommand extends CommandAbstract {

    private final MovieFactory movieFactory;
    RRHandler rrHandler;

    public RemoveByIdCommand(String name, String description, MovieFactory movieFactory, boolean isArgument, RRHandler rrHandler) {
        super(name, description, isArgument);
        this.movieFactory = movieFactory;
        this.rrHandler = rrHandler;
    }

    public void execute(ObjectForServer arg) {
        long idFromUser = Long.valueOf(arg.getArg());
        boolean isId = false;
        for (Movie movie : movieFactory.getCollectionForWork()) {
            if (movie.getId() == idFromUser) {
                movieFactory.getCollectionForWork().remove(movie);
                isId = true;
                movieFactory.getCollectionManager().setDateUpdate();
                String message = "Element with ID" + idFromUser + "was delete";
                rrHandler.res(message);
                break;
            }
        }
        if (!isId) {
            String message = "Such an idea to send your mother away, HE IS NOT THERE";
            rrHandler.res(message);
        }
    }
}
