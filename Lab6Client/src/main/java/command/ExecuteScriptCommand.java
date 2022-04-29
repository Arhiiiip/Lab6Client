package command;

import request.Serializator;
import utility.MovieFactory;

import java.util.HashMap;
import java.util.HashSet;

public class ExecuteScriptCommand extends CommandAbstract {

    Invoker invoker;
    MovieFactory movieFactory;
    private RuntimeException RuntimeException = new RuntimeException();
    HashMap<String, CommandAbstract> commands;
    HashSet<String> files;


    public ExecuteScriptCommand(String name, String description, HashMap<String, CommandAbstract> commands, Invoker invoker, HashSet<String> files, boolean isArgument) {
        super(name, description, isArgument);
        this.commands = commands;
        this.invoker = invoker;
        this.files = files;
    }

    public void execute(String arg) {
        Serializator serializator = new Serializator();
    }
}
