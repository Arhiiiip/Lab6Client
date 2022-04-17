package Command;

import utility.MovieFactory;
import utility.Validator;
import utility.Reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class ExecuteScriptCommand extends CommandAbstract {

    Invoker invoker;
    MovieFactory movieFactory;
    private RuntimeException RuntimeException = new RuntimeException();
    HashMap<String, CommandAbstract> commands;
    HashSet<String> files;


    public ExecuteScriptCommand(String name, String description, MovieFactory movieFactory, HashMap<String, CommandAbstract> commands, Invoker invoker, HashSet<String> files, boolean isArgument) {
        super(name, description, isArgument);
        this.movieFactory = movieFactory;
        this.commands = commands;
        this.invoker = invoker;
        this.files = files;
    }

    public void execute(String arg) {
        if (files.contains(arg)) {
            System.out.println("Произошло зацикливание жопы");
            return;
        }
        files.add(arg);
        Scanner scannerForFile;
        try {
            scannerForFile = new Scanner(new File(arg));
            Scanner prevScanner = Reader.scanner;
            Reader readerForFile = new Reader(scannerForFile, invoker);
            while (scannerForFile.hasNextLine()) {
                Validator.setReader(readerForFile);
                invoker.execute(readerForFile.read());
            }
            files.remove(arg);
            scannerForFile.close();
            Reader.setScanner(prevScanner);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
