package command;

import utility.ObjectForServer;

/**
 * Абстрактный класс команды
 */

public abstract class CommandAbstract {

    /** Переменная команды name - означающая имя */
    final String name;
    /** Переменная команды description - означающая описание */
    final String description;
    /** Свойство показывающее нужен ли команде аргумент */
    final boolean isArgument;

    public CommandAbstract(String name, String description, boolean isArgument) {
        this.name = name;
        this.description = description;
        this.isArgument = isArgument;
    }

    public void execute(ObjectForServer arg) {
    }

    @Override
    public String toString() {
        return name + " - " + description;
    }
}
