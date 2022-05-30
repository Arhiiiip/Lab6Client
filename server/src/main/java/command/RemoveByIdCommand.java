package command;

import data.Movie;
import utility.MovieFactory;
import utility.ObjectForServer;
import utility.RRHandler;

import java.util.List;
import java.util.stream.Collectors;

public class RemoveByIdCommand extends CommandAbstract {

    private final MovieFactory movieFactory;
    RRHandler rrHandler;

    public RemoveByIdCommand(String name, String description, MovieFactory movieFactory, boolean isArgument, RRHandler rrHandler) {
        super(name, description, isArgument);
        this.movieFactory = movieFactory;
        this.rrHandler = rrHandler;
    }

    public void execute(ObjectForServer arg) {
        long idFromUser = Long.parseLong(arg.getArg());

        List<Movie> colMovieForDel = movieFactory.getCollectionForWork().stream().filter(value -> value.getId() == idFromUser).collect(Collectors.toList());

        if(colMovieForDel.size() == 1) {
            Movie movieForDel = colMovieForDel.get(0);
            movieFactory.getCollectionForWork().remove(movieForDel);
            movieFactory.getCollectionManager().setDateUpdate();
            rrHandler.res("Element with ID" + idFromUser + "was delete");
        }else{
            rrHandler.res("Such an idea to send your mother away, HE IS NOT THERE");
        }
    }
}
