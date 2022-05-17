package command;

import data.Movie;
import utility.MovieFactory;
import utility.ObjectForServer;
import utility.RRHandler;
import utility.Validator;

public class AddIfMinCommand extends CommandAbstract {

    MovieFactory movieFactory;
    RRHandler rrHandler;

    public AddIfMinCommand(String name, String description, MovieFactory movieFactory, boolean isArgument, RRHandler rrHandler) {
        super(name, description, isArgument);
        this.movieFactory = movieFactory;
        this.rrHandler = rrHandler;
    }

    public void execute(ObjectForServer arg) {
        Movie movieForAdd = arg.getMovie();
        movieForAdd.setId(Validator.autoCreatAndCheckId(movieFactory.getHashSetId()));
        int oscarsCountFromUser = movieForAdd.getOscarsCount();
        int minOscarsCount = Integer.MAX_VALUE;
        for (Movie movie : movieFactory.getCollectionForWork()) {
            if (movie.getOscarsCount() < minOscarsCount) {
                minOscarsCount = movie.getOscarsCount();
            }
        }
        if (oscarsCountFromUser < minOscarsCount) {
            movieFactory.getCollectionForWork().add(movieForAdd);
            movieFactory.getCollectionManager().setDateUpdate();
            String message = "Элемент добавлен";
            rrHandler.res(message);
        } else {
            String message = "Таких элементов нет";
            rrHandler.res(message);
        }
    }
}
