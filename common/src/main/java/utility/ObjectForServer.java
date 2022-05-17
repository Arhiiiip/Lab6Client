package utility;

import data.Movie;

import java.io.Serializable;

public class ObjectForServer implements Serializable {

    private final static long serialVersionUID = 1128932627584373707L;

    String command;
    String arg;
    String answer;
    Movie movie;
    boolean answerB;

    public ObjectForServer(String command, String arg) {
        this.command = command;
        this.arg = arg;
    }

    public ObjectForServer(String command, Movie movie) {
        this.command = command;
        this.movie = movie;
    }

    public Movie getMovie() {
        return movie;
    }

    public boolean isAnswerB() {
        return answerB;
    }

    public ObjectForServer(Boolean answerB) {
        this.answerB = answerB;
    }

    public ObjectForServer(String answer) {
        this.answer = answer;
    }

    public String getCommand() {
        return command;
    }

    public String getArg() {
        return arg;
    }

    public String getAnswer() {
        return answer;
    }

    public String getCommandUser() {
        String commandUser;
        commandUser = command + arg;
        return commandUser;
    }

    @Override
    public String toString() {
        return "Ответ:\n" + answer;
    }
}
