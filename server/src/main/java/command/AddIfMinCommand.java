package command;

import data.Movie;
import utility.MovieFactory;
import utility.ObjectForServer;
import utility.RRHandler;
import utility.Validator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddIfMinCommand extends CommandAbstract {

    MovieFactory movieFactory;
    RRHandler rrHandler;

    public AddIfMinCommand(String name, String description, MovieFactory movieFactory, boolean isArgument, RRHandler rrHandler) {
        super(name, description, isArgument);
        this.movieFactory = movieFactory;
        this.rrHandler = rrHandler;
    }

    public void execute(ObjectForServer arg) {
        Movie movie = arg.getMovie();
        movie.setId(Validator.autoCreatAndCheckId(movieFactory.getHashSetId()));
        int oscarsCountFromUser = movie.getOscarsCount();
        int minOscarsCount = Integer.MAX_VALUE;
        for (Movie movieF : movieFactory.getCollectionForWork()) {
            if (movieF.getOscarsCount() < minOscarsCount) {
                minOscarsCount = movieF.getOscarsCount();
            }
        }
        if (oscarsCountFromUser < minOscarsCount) {
            movieFactory.getCollectionForWork().add(movie);
            movieFactory.getCollectionManager().setDateUpdate();
            String reqP = "insert into \"Person\" (name, weight, eyecolor, nationality, location_name, location_x, location_y) values (?,?,?,?,?,?,?) returning id";
            try {
                PreparedStatement ptP = movieFactory.getConnection().prepareStatement(reqP);
                ptP.setString(1, movie.getDirector().getName());
                ptP.setDouble(2, movie.getDirector().getWeight());
                ptP.setInt(3, movie.getDirector().getEyeColor().ordinal());
                ptP.setInt(4, movie.getDirector().getNationality().ordinal());
                ptP.setString(5, movie.getDirector().getLocation().getName());
                ptP.setInt(6, movie.getDirector().getLocation().getX());
                ptP.setDouble(7, movie.getDirector().getLocation().getY());
                ptP.execute();
                ResultSet resultSet = ptP.getResultSet();
                if (resultSet.next()) {
                    String req = "insert into \"Movie\" (num, filmname, coordinates_x, coordinates_y, date, genre, mpaarating, director, oscarscount, \"user\") values (?,?,?,?,?,?,?,?,?,?)";
                    int person = resultSet.getInt(1);
                    PreparedStatement pt = movieFactory.getConnection().prepareStatement(req);
                    pt.setLong(1, movie.getId());
                    pt.setString(2, movie.getName());
                    pt.setInt(3, movie.getCoordinates().getX());
                    pt.setInt(4, movie.getCoordinates().getY());
                    pt.setString(5, movie.getCreationDate().toString());
                    pt.setInt(6, movie.getGenre().ordinal());
                    pt.setInt(7, movie.getMpaaRating().ordinal());
                    pt.setInt(8, person);
                    pt.setInt(9, movie.getOscarsCount());
                    pt.setString(10, movie.getUser());
                    pt.execute();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            String message = "Элемент добавлен";
            rrHandler.res(message);
        } else {
            String message = "Таких элементов нет";
            rrHandler.res(message);
        }
    }
}
