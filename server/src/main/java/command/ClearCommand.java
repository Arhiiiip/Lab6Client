package command;

import utility.MovieFactory;
import utility.ObjectForServer;
import utility.RRHandler;

public class ClearCommand extends CommandAbstract {

    MovieFactory movieFactory;
    RRHandler rrHandler;

    public ClearCommand(String name, String description, MovieFactory movieFactory, boolean isArgument, RRHandler rrHandler) {
        super(name, description, isArgument);
        this.movieFactory = movieFactory;
        this.rrHandler = rrHandler;
    }

    public void execute(ObjectForServer arg) {
        if (!(movieFactory.getCollectionForWork().size() == 0)) {
            movieFactory.getCollectionForWork().clear();
            movieFactory.getCollectionManager().setDateUpdate();
        } else {
            String message = "Collection is empty";
            rrHandler.res(message);
        }
        String message = "Collection was clear";
        rrHandler.res(message);
    }
}
