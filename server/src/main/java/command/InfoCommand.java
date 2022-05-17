package command;

import utility.MovieFactory;
import utility.ObjectForServer;
import utility.RRHandler;

public class InfoCommand extends CommandAbstract {

    MovieFactory movieFactory;
    RRHandler rrHandler;

    public InfoCommand(String name, String description, MovieFactory movieFactory, boolean isArgument, RRHandler rrHandler) {
        super(name, description, isArgument);
        this.movieFactory = movieFactory;
        this.rrHandler = rrHandler;
    }

    public void execute(ObjectForServer arg) {
        String result = "";
        result = result + "Information about collection:" + "\n" +
                movieFactory.getCollectionForWork().getClass() + "\n" +
                "Count element: " + movieFactory.getCollectionForWork().size() + "\n" +
                "Date and time initialization: " + movieFactory.getCollectionManager().getDateInitialization() + "\n" +
                "Date and time last initialization: " + movieFactory.getCollectionManager().getDateUpdate();
        rrHandler.res(result);
    }
}
