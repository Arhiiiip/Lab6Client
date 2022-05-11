package command;
<<<<<<<< Updated upstream:src/main/java/command/CommandAbstract.java
========

import java.io.Serializable;
>>>>>>>> Stashed changes:src/main/java/Command/CommandAbstract.java

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
    public String arg;

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
        if (isArgument){
        return name + " - " + description + ". Так же команда требует аргумент";
        }else {
            return name + " - " + description;
        }
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isArgument() {
        return isArgument;
    }

    public String getArg() {
        return arg;
    }
}
