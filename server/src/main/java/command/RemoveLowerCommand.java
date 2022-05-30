package command;

import data.Movie;
import utility.MovieFactory;
import utility.ObjectForServer;
import utility.RRHandler;

import java.util.List;
import java.util.stream.Collectors;

public class RemoveLowerCommand extends CommandAbstract {

    MovieFactory movieFactory;
    RRHandler rrHandler;

    public RemoveLowerCommand(String name, String description, MovieFactory movieFactory, boolean isArgument, RRHandler rrHandler) {
        super(name, description, isArgument);
        this.movieFactory = movieFactory;
        this.rrHandler = rrHandler;
    }

    public void execute(ObjectForServer arg) {
        long oscarsCountFromUser = Integer.parseInt(arg.getArg());

        List<Movie> moviesForDel = movieFactory.getCollectionForWork().stream().filter(value -> value.getOscarsCount() < oscarsCountFromUser).collect(Collectors.toList());

        if(moviesForDel.size() == 1){
            movieFactory.getCollectionForWork().removeAll(moviesForDel);
            movieFactory.getCollectionManager().setDateUpdate();
            rrHandler.res(moviesForDel.size() + "elements was deleted");
        } else {
            rrHandler.res("There is no such element");
        }
    }
}
