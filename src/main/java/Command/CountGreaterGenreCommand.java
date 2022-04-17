package Command;

import Data.Movie;
import Data.MovieGenre;
import utility.MovieFactory;

public class CountGreaterGenreCommand extends CommandAbstract {

    MovieFactory movieFactory;

    public CountGreaterGenreCommand(String name, String description, MovieFactory movieFactory, boolean isArgument) {
        super(name, description, isArgument);
        this.movieFactory = movieFactory;
    }

    public void execute(String arg) {
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
        MovieGenre argUser = MovieGenre.valueOf(arg);
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
        System.out.println(countMore);
    }
}
