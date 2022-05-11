package command;

import data.Movie;
import utility.MovieFactory;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class SaveCommand extends CommandAbstract {

    MovieFactory movieFactory;

    public SaveCommand(String name, String description, MovieFactory movieFactory, boolean isArgument) {
        super(name, description, isArgument);
        this.movieFactory = movieFactory;
    }

    public void execute(String arg) {
        String link = movieFactory.getCollectionManager().getLink();
        PrintWriter fileOut = null;
        try {
            fileOut = new PrintWriter(link);
            fileOut.println("<movies>");
            for (Movie movie : movieFactory.getCollectionForWork()) {
                fileOut.println("<movie>");
                fileOut.println("<id>" + movie.getId() + "</id>");
                fileOut.println("<name>" + movie.getName() + "</name>");
                fileOut.println("<coordinates>" + "\n" + "<coordinate_x>" + movie.getCoordinates().getX() + "</coordinate_x>" + "\n" + "<coordinate_y>" + movie.getCoordinates().getY() + "</coordinate_y>" + "\n" + "</coordinates>");
                fileOut.println("<creationDate>" + movie.getCreationDate() + "</creationDate>");
                fileOut.println("<oscarsCount>" + movie.getOscarsCount() + "</oscarsCount>");
                fileOut.println("<genre>" + movie.getGenre() + "</genre>");
                fileOut.println("<mpaaRating>" + movie.getMpaaRating() + "</mpaaRating>");
                fileOut.print("<director>" + "\n" +
                        "<name>" + movie.getDirector().getName() + "</name>" + "\n" +
                        "<weight>" + movie.getDirector().getWeight() + "</weight>" + "\n" +
                        "<eyecolor>" + movie.getDirector().getEyeColor() + "</eyecolor>" + "\n" +
                        "<nationality>" + movie.getDirector().getNationality() + "</nationality>" + "\n" +
                        "<location>" + "\n" +
                        "<loc_x>" + movie.getDirector().getLocation().getX() + "</loc_x>" + "\n" +
                        "<loc_y>" + movie.getDirector().getLocation().getY() + "</loc_y>" + "\n" +
                        "<name>" + movie.getDirector().getLocation().getName() + "</name>" + "\n" +
                        "</location>" + "\n" + "</director>" + "\n" + "</movie>" + "\n"
                );
            }
            fileOut.println("</movies>");
            fileOut.close();
            movieFactory.getCollectionManager().setSavedCollection(movieFactory.getCollectionForWork());
        } catch (FileNotFoundException e) {
            System.out.println("Не достаточно прав дя сохранения.");
        }
    }
}
