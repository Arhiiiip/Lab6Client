package command;

import utility.MovieFactory;
import utility.ObjectForServer;
import utility.RRHandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Registration extends CommandAbstract {

    MovieFactory movieFactory;
    RRHandler rrHandler;

    public Registration(String name, String description, boolean isArgument, MovieFactory movieFactory, RRHandler rrHandler) {
        super(name, description, isArgument);
        this.movieFactory = movieFactory;
        this.rrHandler = rrHandler;
    }

    public void execute(ObjectForServer arg) {
        String[] args = arg.getArg().split(" ");
        String name = args[0];
        String password = args[1];
        try {
            String req = "select exists(select * from users where name = ?)";
            PreparedStatement ps = movieFactory.getConnection().prepareStatement(req);
            ps.setString(1, arg.getArg());
            ps.execute();
            ResultSet resultSet = ps.getResultSet();
            if (resultSet.next() && !resultSet.getBoolean(1)) {
                String sql = "INSERT INTO users (name, password) values(?, ?)";
                PreparedStatement pStatement = movieFactory.getConnection().prepareStatement(sql);
                pStatement.setString(1, name);
                pStatement.setString(2, password);
                pStatement.execute();
                RRHandler.resB(true);
            } else {
                RRHandler.resB(false);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
