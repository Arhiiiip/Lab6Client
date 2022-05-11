package command;

import utility.MovieFactory;

public class ClearCommand extends CommandAbstract {

    MovieFactory movieFactory;

    public ClearCommand(String name, String description, MovieFactory movieFactory, boolean isArgument) {
        super(name, description, isArgument);
        this.movieFactory = movieFactory;
    }

    public void execute(String arg) {
        if (!(movieFactory.getCollectionForWork().size() == 0)) {
            movieFactory.getCollectionForWork().clear();
            movieFactory.getCollectionManager().setDateUpdate();
        } else {
            System.out.println("Коллекция уже пустая!");
        }
    }
}
