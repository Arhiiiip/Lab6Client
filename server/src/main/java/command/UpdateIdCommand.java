package command;

import data.Movie;
import utility.MovieFactory;
import utility.ObjectForServer;
import utility.RRHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class UpdateIdCommand extends CommandAbstract {

    MovieFactory movieFactory;
    RRHandler rrHandler;

    public UpdateIdCommand(String name, String description, MovieFactory movieFactory, boolean isArgument, RRHandler rrHandler) {
        super(name, description, isArgument);
        this.movieFactory = movieFactory;
        this.rrHandler = rrHandler;
    }

    public void execute(ObjectForServer arg) {
        long idFromUser = Long.parseLong(arg.getArg());

        List<Movie> mov = movieFactory.getCollectionForWork().stream().filter(value -> value.getId() == idFromUser).collect(Collectors.toList());


        if (mov.size() == 1) {
            movieFactory.getCollectionForWork().remove(mov.get(0));
            movieFactory.getCollectionManager().setDateUpdate();
            RRHandler.resB(true);
            try {
                InputStream stream;
                stream = rrHandler.getSocket().getInputStream();
                ObjectInputStream objectInputStream = new ObjectInputStream(stream);
                ObjectForServer command = (ObjectForServer) objectInputStream.readObject();
                Movie movieForChange = command.getMovie();
                movieForChange.setId(idFromUser);
                movieFactory.getCollectionForWork().add(movieForChange);

                try {
                    String reqPR = "select director from \"Movie\" where id = ?";
                    PreparedStatement ptPR = movieFactory.getConnection().prepareStatement(reqPR);
                    ptPR.setLong(1, idFromUser);
                    ptPR.execute();
                    ResultSet resultSet = ptPR.getResultSet();
                    if (resultSet.next()) {
                        int person = resultSet.getInt(1);
                        String reqP = "update \"Person\" (name, weight, eyecolor, nationality, location_name, location_x, location_y) values (?,?,?,?,?,?,?) where id = ?";
                        PreparedStatement ptP = movieFactory.getConnection().prepareStatement(reqP);
                        ptP.setString(1, movieForChange.getDirector().getName());
                        ptP.setDouble(2, movieForChange.getDirector().getWeight());
                        ptP.setInt(3, movieForChange.getDirector().getEyeColor().ordinal());
                        ptP.setInt(4, movieForChange.getDirector().getNationality().ordinal());
                        ptP.setString(5, movieForChange.getDirector().getLocation().getName());
                        ptP.setInt(6, movieForChange.getDirector().getLocation().getX());
                        ptP.setDouble(7, movieForChange.getDirector().getLocation().getY());
                        ptP.setInt(8, person);
                        ptP.execute();
                        String reqM = "update \"Movie\" (num, filmname, coordinates_x, coordinates_y, date, genre, mpaarating, director, oscarscount, \"user\") values (?,?,?,?,?,?,?,?,?,?) where id = ?";
                        PreparedStatement ptM = movieFactory.getConnection().prepareStatement(reqM);
                        ptM.setLong(1, movieForChange.getId());
                        ptM.setString(2, movieForChange.getName());
                        ptM.setInt(3, movieForChange.getCoordinates().getX());
                        ptM.setInt(4, movieForChange.getCoordinates().getY());
                        ptM.setString(5, movieForChange.getCreationDate().toString());
                        ptM.setInt(6, movieForChange.getGenre().ordinal());
                        ptM.setInt(7, movieForChange.getMpaaRating().ordinal());
                        ptM.setInt(9, movieForChange.getOscarsCount());
                        ptM.setString(10, movieForChange.getUser());
                        ptM.setLong(11, idFromUser);
                        ptM.execute();
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }


                String message = "Element was updated";
                rrHandler.res(message);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            RRHandler.resB(false);
        }
        rrHandler.res("(((");
    }
}
