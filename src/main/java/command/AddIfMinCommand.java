package command;

import data.Movie;
import utility.MovieFactory;

public class AddIfMinCommand extends CommandAbstract {

    MovieFactory movieFactory;

    public AddIfMinCommand(String name, String description, MovieFactory movieFactory, boolean isArgument) {
        super(name, description, isArgument);
        this.movieFactory = movieFactory;
    }

    public void execute(String arg) {
        Movie movieForAdd = movieFactory.GetMovieFromConsole();
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
        } else {
            System.out.println("Таких элементов нет");
        }
    }
}
