package Command;

import Data.Movie;
import utility.MovieFactory;

/**
 * Класс команды add
 * Которая добавляет элемент в коллекцию
 */

public class AddCommand extends CommandAbstract {

    MovieFactory movieFactory;

    public AddCommand(String name, String description, MovieFactory movieFactory, boolean isArgument) {
        super(name, description, isArgument);
        this.movieFactory = movieFactory;
    }


    /**
     * Iсполнение команды add
     * @param arg
     */
    @Override
    public void execute(String arg) {
        Movie movie = movieFactory.GetMovieFromConsole();
        movieFactory.getCollectionForWork().add(movie);
        movieFactory.getCollectionManager().setDateUpdate();
    }
}
