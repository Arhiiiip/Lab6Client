package command;

import data.Movie;
import utility.MovieFactory;

public class AddIfMaxCommand extends CommandAbstract {

    MovieFactory movieFactory;

    public AddIfMaxCommand(String name, String description, MovieFactory movieFactory, boolean isArgument) {
        super(name, description, isArgument);
        this.movieFactory = movieFactory;
    }

    public void execute(String arg) {
        Movie movieForAdd = movieFactory.GetMovieFromConsole();
        int oscarsCountFromUser = movieForAdd.getOscarsCount();
        int maxOscarsCount = 0;
        for (Movie movie : movieFactory.getCollectionForWork())
            if (movie.getOscarsCount() > maxOscarsCount) {
                maxOscarsCount = movie.getOscarsCount();
            }
        if (oscarsCountFromUser > maxOscarsCount) {
            movieFactory.getCollectionForWork().add(movieForAdd);
            movieFactory.getCollectionManager().setDateUpdate();
        } else {
            System.out.println("Таких элементов нет");
        }
    }
}
