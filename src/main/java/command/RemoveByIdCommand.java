package command;

import data.Movie;
import utility.MovieFactory;

public class RemoveByIdCommand extends CommandAbstract {

    private final MovieFactory movieFactory;

    public RemoveByIdCommand(String name, String description, MovieFactory movieFactory, boolean isArgument) {
        super(name, description, isArgument);
        this.movieFactory = movieFactory;
    }

    public void execute(String arg) {
        long idFromUser = Long.valueOf(arg);
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
            System.out.println("Такого айди нет");
        }
    }
}
