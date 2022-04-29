package command;

import java.io.Serializable;

/**
 * Абстрактный класс команды
 */

public abstract class CommandAbstract implements Serializable {

    /** Переменная команды name - означающая имя */
    final String name;
    /** Переменная команды description - означающая описание */
    final String description;
    /** Свойство показывающее нужен ли команде аргумент */
    final boolean isArgument;
    public transient String arg;

    public CommandAbstract(String name, String description, boolean isArgument) {
        this.name = name;
        this.description = description;
        this.isArgument = isArgument;
    }

    public void execute(String arg) {
    }

    public void setArg(String arg) {
        this.arg = arg;
    }

    @Override
    public String toString() {
        return name + " - " + description;
    }
}
