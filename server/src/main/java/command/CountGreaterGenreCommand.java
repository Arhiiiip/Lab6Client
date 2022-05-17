package command;

import data.Movie;
import data.MovieGenre;
import utility.MovieFactory;
import utility.ObjectForServer;
import utility.RRHandler;

public class CountGreaterGenreCommand extends CommandAbstract {

    MovieFactory movieFactory;
    RRHandler rrHandler;

    public CountGreaterGenreCommand(String name, String description, MovieFactory movieFactory, boolean isArgument, RRHandler rrHandler) {
        super(name, description, isArgument);
        this.movieFactory = movieFactory;
        this.rrHandler = rrHandler;
    }

    public void execute(ObjectForServer arg) {
        long countWestern = 0;
        long countDrama = 0;
        long countTragedy = 0;
        long countScienceFiction = 0;
        for (Movie movie : movieFactory.getCollectionForWork()) {
            switch (movie.getGenre()) {
                case DRAMA: {
                    countDrama += 1;
                    break;
                }
                case TRAGEDY: {
                    countTragedy += 1;
                    break;
                }
                case WESTERN: {
                    countWestern += 1;
                    break;
                }
                case SCIENCE_FICTION: {
                    countScienceFiction += 1;
                    break;
                }
            }
        }

        long countUserGenre = 0;
        MovieGenre argUser = MovieGenre.valueOf(arg.getArg());
        switch (argUser) {
            case DRAMA: {
                countUserGenre = countDrama;
                break;
            }
            case TRAGEDY: {
                countUserGenre = countTragedy;
                break;
            }
            case WESTERN: {
                countUserGenre = countWestern;
                break;
            }
            case SCIENCE_FICTION: {
                countUserGenre = countScienceFiction;
                break;
            }
        }
        long[] arr = {countDrama, countTragedy, countWestern, countScienceFiction};
        long countMore = 0;
        for (long x : arr) {
            if (x > countUserGenre) {
                countMore += x;
            }
        }
        String result = String.valueOf(countMore);
        rrHandler.res(result);
    }
}
