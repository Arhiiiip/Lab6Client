package command;

import request.RRHandler;
import utility.MovieFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Класс Invoker, принимающий на вход команду,
 * затем передающий её в Receiver
 */

public class Invoker {

    public Map<String, CommandAbstract> commands;

    private Receiver receiver;
    public HashSet<String> files = new HashSet<>();
    private final RRHandler rrHandler;
    public MovieFactory movieFactory;

    public Invoker(Receiver receiver, RRHandler rrHandler, MovieFactory movieFactory) {
        commands = new HashMap<>();
        this.receiver = receiver;
        this.rrHandler = rrHandler;
        this.movieFactory = movieFactory;
        initCommands();
    }

    public void initCommands() {
        commands.put("add", new AddCommand("add", "Добавить новый элемент в коллекцию", false, rrHandler, movieFactory));
        commands.put("help", new HelpCommand("help", "Вывести справку по доступным командам", commands, false,  rrHandler));
        commands.put("show", new ShowCommand("show", "Вывести в стандартный поток вывода все элементы коллекции в строковом представлении", false, rrHandler));
        commands.put("info", new InfoCommand("info", "Вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)", false, rrHandler));
        commands.put("clear", new ClearCommand("clear", "Очистить коллекцию", false, rrHandler));
        commands.put("exit", new ExitCommand("exit", "Завершить программу (без сохранения в файл)", false, rrHandler));
        commands.put("add_if_max", new AddIfMaxCommand("add_if_max", "Добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции", false, rrHandler, movieFactory));
        commands.put("add_if_min", new AddIfMinCommand("add_if_min", "Добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции", false, rrHandler, movieFactory));
        commands.put("average_of_oscars_count", new AverageOfOscarsCommand("average_of_oscars_count", "Вывести среднее значение поля oscarsCount для всех элементов коллекции", false, rrHandler));
        commands.put("sum_of_oscars_count", new SumOfOscarsCommand("sum_of_oscars_count", "Вывести сумму значений поля oscarsCount для всех элементов коллекции", false, rrHandler));
        commands.put("remove_by_id", new RemoveByIdCommand("remove_by_id", "Удалить элемент из коллекции по его id", true, rrHandler));
        commands.put("remove_lower", new RemoveLowerCommand("remove_lower", "Удалить из коллекции все элементы, меньшие, чем заданный", true, rrHandler));
        commands.put("update_id", new UpdateIdCommand("update_id", "Oбновить значение элемента коллекции, id которого равен заданному", true, rrHandler, movieFactory));
        commands.put("count_greater_than_genre", new CountGreaterGenreCommand("count_greater_than_genre", "Вывести количество элементов, значение поля genre которых больше заданного", true, rrHandler));
        commands.put("execute_script", new ExecuteScriptCommand("execute_script", "Cчитать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме", (HashMap<String, CommandAbstract>) commands, this, files, true, rrHandler));
    }

    public void execute(String command) throws IOException {
        String[] parts = command.split(" ");
        if (commands.containsKey(parts[0])) {
            if (parts.length == 2) {
                if (commands.get(parts[0]).isArgument) {
                    receiver.execute(commands.get(parts[0]), parts[1]);
                } else {
                    System.out.println("Команда не требует аргумента, смотреть help");
                    throw new RuntimeException();
                }
            } else if (parts.length == 1){
                if (!commands.get(parts[0]).isArgument) {
                    receiver.execute(commands.get(parts[0]), "");
                } else {
                    System.out.println("Команде требуется аргумент, смотреть help");
                    throw new RuntimeException();
                }
            } else {
                System.out.println("Команда точно не требует столько аргументов.");
                throw new RuntimeException();
            }
        } else {
            System.out.println("Такой команды нет");
            throw new RuntimeException();
        }
    }
}

