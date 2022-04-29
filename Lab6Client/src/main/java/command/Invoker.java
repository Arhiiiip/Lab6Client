package command;

import java.io.IOException;
import java.nio.channels.SocketChannel;
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
    SocketChannel client;

    public Invoker(Receiver receiver, SocketChannel client) {
        commands = new HashMap<>();
        this.receiver = receiver;
        initCommands();
        this.client = client;

    }

    public void initCommands() {
        commands.put("add", new AddCommand("add", "Добавить новый элемент в коллекцию", false));
        commands.put("help", new HelpCommand("help", "Вывести справку по доступным командам", commands, false));
        commands.put("show", new ShowCommand("show", "Вывести в стандартный поток вывода все элементы коллекции в строковом представлении", false));
        commands.put("info", new InfoCommand("info", "Вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)", false));
        commands.put("save", new SaveCommand("save", "Cохранить коллекцию в файл", false));
        commands.put("clear", new ClearCommand("clear", "Очистить коллекцию", false));
        commands.put("exit", new ExitCommand("exit", "Завершить программу (без сохранения в файл)", false));
        commands.put("add_if_max", new AddIfMaxCommand("add_if_max", "Добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции", false));
        commands.put("add_if_min", new AddIfMinCommand("add_if_min", "Добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции", false));
        commands.put("average_of_oscars_count", new AverageOfOscarsCommand("average_of_oscars_count", "Вывести среднее значение поля oscarsCount для всех элементов коллекции", false));
        commands.put("sum_of_oscars_count", new SumOfOscarsCommand("sum_of_oscars_count", "Вывести сумму значений поля oscarsCount для всех элементов коллекции", false));
        commands.put("remove_by_id", new RemoveByIdCommand("remove_by_id id", "Удалить элемент из коллекции по его id", true));
        commands.put("remove_lower", new RemoveLowerCommand("remove_lower {element}", "Удалить из коллекции все элементы, меньшие, чем заданный", true));
        commands.put("update_id", new UpdateIdCommand("update_id id", "Oбновить значение элемента коллекции, id которого равен заданному", true));
        commands.put("count_greater_than_genre", new CountGreaterGenreCommand("count_greater_than_genre genre", "Вывести количество элементов, значение поля genre которых больше заданного", true));
        commands.put("execute_script", new ExecuteScriptCommand("execute_script link_to_file", "Cчитать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме", (HashMap<String, CommandAbstract>) commands, this, files, true));
    }

    public void execute(String command) throws IOException {
        String[] parts = command.split(" ");
        System.out.println("Зашёл");
        if (commands.containsKey(parts[0])) {
            if (parts.length == 2) {
                if (commands.get(parts[0]).isArgument) {
                    receiver.execute(commands.get(parts[0]), parts[1]);
                    System.out.println("Отправлено");
                } else {
                    System.out.println("Команда не требует аргумента, смотреть help");
                }
            } else if (parts.length == 1){
                if (!commands.get(parts[0]).isArgument) {
                    receiver.execute(commands.get(parts[0]), "");
                    System.out.println("Отправлено");
                } else {
                    System.out.println("Команде требуется аргумент, смотреть help");
                }
            } else {
                System.out.println("Команда точно не требует столько аргументов.");
            }
        } else {
            System.out.println("Такой команды нет");
        }
    }
}

