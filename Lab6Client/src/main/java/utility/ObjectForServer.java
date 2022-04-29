package utility;

import java.io.Serializable;

public class ObjectForServer implements Serializable {

    private final static long serialVersionUID = 1128932627584373707L;

    String command;
    String arg;
    String answer;

    public ObjectForServer(String command, String arg) {
        this.command = command;
        this.arg = arg;
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

    public String getCommandUser() {
        String commandUser;
        commandUser = command + arg;
        return commandUser;
    }

    @Override
    public String toString() {
        return "answer:\n" + answer;
    }
}
