package Command;

import Data.Movie;
import utility.MovieFactory;

public class UpdateIdCommand extends CommandAbstract {
    MovieFactory movieFactory;

    public UpdateIdCommand(String name, String description, MovieFactory movieFactory, boolean isArgument) {
        super(name, description, isArgument);
        this.movieFactory = movieFactory;
    }

    public void execute(String arg) {
        Movie movieForChange = movieFactory.GetMovieFromConsole();
        long idFromUser = Long.parseLong(arg);
        boolean isId = false;
        for (Movie movie : movieFactory.getCollectionForWork()) {
            if (movie.getId() == idFromUser) {
                movieFactory.getCollectionForWork().remove(movie);
                isId = true;
                movieFactory.getCollectionManager().setDateUpdate();
                break;
            }
        }
        if (!isId) {
            System.out.println("Такого id нет");
        } else {
            movieForChange.setId(idFromUser);
            movieFactory.getCollectionForWork().add(movieForChange);
        }
    }
}
