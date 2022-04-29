package command;

import request.Sender;
import utility.MovieFactory;

/**
 * Класс команды add
 * Которая добавляет элемент в коллекцию
 */

public class AddCommand extends CommandAbstract {

    MovieFactory movieFactory;
    Sender sender;

    public AddCommand(String name, String description, boolean isArgument) {
        super(name, description, isArgument);
    }


    /**
     * Iсполнение команды add
     * @param arg
     */
    @Override
    public void execute(String arg) {

    }
}
