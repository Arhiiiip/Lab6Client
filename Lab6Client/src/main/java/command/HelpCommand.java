package command;

import java.util.Map;

public class HelpCommand extends CommandAbstract{
    private transient Map<String, CommandAbstract> commands;

    public HelpCommand(String name, String description, Map<String, CommandAbstract> commands, boolean isArgument) {
        super(name, description, isArgument);
        this.commands = commands;
    }

    public void execute(String arg) {
//        Serializator serializator = new Serializator();
//        try {
//            System.out.println("Да");
////            Sender.send(serializator.serialize(this));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }
}
