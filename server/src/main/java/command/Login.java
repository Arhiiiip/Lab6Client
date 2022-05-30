package command;

import utility.MovieFactory;
import utility.ObjectForServer;
import utility.RRHandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends CommandAbstract {

    MovieFactory movieFactory;
    RRHandler rrHandler;


    public Login(String name, String description, boolean isArgument, MovieFactory movieFactory, RRHandler rrHandler) {
        super(name, description, isArgument);
        this.movieFactory = movieFactory;
        this.rrHandler = rrHandler;
    }

    public void execute(ObjectForServer arg) {
        String[] args = arg.getArg().split(" ");
        String name = args[0];
        String password = args[1];
        try {
            String req = "SELECT EXISTS(select password from users where name = ?)";
            PreparedStatement ps = movieFactory.getConnection().prepareStatement(req);
            ps.setString(1, name);
            ps.execute();
            ResultSet resultSet = ps.getResultSet();
            if (resultSet.next() && resultSet.getBoolean(1)) {
                String reqq = "select password from users where name = ?";
                PreparedStatement pss = movieFactory.getConnection().prepareStatement(reqq);
                pss.setString(1, name);
                pss.execute();
                ResultSet resultSett = pss.getResultSet();
                if (resultSett.next()) {
                    String result = resultSett.getString(1);
                    if (result.equals(password)) {
                        RRHandler.resB(true);
                    } else {
                        RRHandler.resB(false);
                    }
                }else{
                    RRHandler.resB(false);
                }
            } else {
                RRHandler.resB(false);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
