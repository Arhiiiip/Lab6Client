package Command;

import utility.MovieFactory;

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

    public Invoker(Receiver receiver, MovieFactory movieFactory) {
        commands = new HashMap<>();
        this.receiver = receiver;
        initCommands(movieFactory);
    }

    public void initCommands(MovieFactory movieFactory) {
        commands.put("add", new AddCommand("add", "Добавить новый элемент в коллекцию", movieFactory, false));
        commands.put("help", new HelpCommand("help", "Вывести справку по доступным командам", movieFactory, commands, false));
        commands.put("show", new ShowCommand("show", "Вывести в стандартный поток вывода все элементы коллекции в строковом представлении", movieFactory, false));
        commands.put("info", new InfoCommand("info", "Вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)", movieFactory, false));
        commands.put("save", new SaveCommand("save", "Cохранить коллекцию в файл", movieFactory, false));
        commands.put("clear", new ClearCommand("clear", "Очистить коллекцию", movieFactory, false));
        commands.put("exit", new ExitCommand("exit", "Завершить программу (без сохранения в файл)", movieFactory, false));
        commands.put("add_if_max", new AddIfMaxCommand("add_if_max", "Добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции", movieFactory, false));
        commands.put("add_if_min", new AddIfMinCommand("add_if_min", "Добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции", movieFactory, false));
        commands.put("average_of_oscars_count", new AverageOfOscarsCommand("average_of_oscars_count", "Вывести среднее значение поля oscarsCount для всех элементов коллекции", movieFactory, false));
        commands.put("sum_of_oscars_count", new SumOfOscarsCommand("sum_of_oscars_count", "Вывести сумму значений поля oscarsCount для всех элементов коллекции", movieFactory, false));
        commands.put("remove_by_id", new RemoveByIdCommand("remove_by_id id", "Удалить элемент из коллекции по его id", movieFactory, true));
        commands.put("remove_lower", new RemoveLowerCommand("remove_lower {element}", "Удалить из коллекции все элементы, меньшие, чем заданный", movieFactory, true));
        commands.put("update_id", new UpdateIdCommand("update_id id", "Oбновить значение элемента коллекции, id которого равен заданному", movieFactory, true));
        commands.put("count_greater_than_genre", new CountGreaterGenreCommand("count_greater_than_genre genre", "Вывести количество элементов, значение поля genre которых больше заданного", movieFactory, true));
        commands.put("execute_script", new ExecuteScriptCommand("execute_script link_to_file", "Cчитать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме", movieFactory, (HashMap<String, CommandAbstract>) commands, this, files, true));
    }

    public void execute(String command) {
        command = command.replaceAll("\\s+", " ");
        String[] parts = command.split(" ");
        if (commands.containsKey(parts[0])) {
            if (parts.length == 2) {
                if (commands.get(parts[0]).isArgument) {
                    receiver.execute(commands.get(parts[0]), parts[1]);
                } else {
                    System.out.println("Команда не требует аргумента, смотреть help");
                }
            } else {
                if (!commands.get(parts[0]).isArgument) {
                    receiver.execute(commands.get(command), "");
                } else {
                    System.out.println("Команде требуется аргумент, смотреть help");
                }
            }
        } else {
            System.out.println("Такой команды нет");
        }
    }
}

