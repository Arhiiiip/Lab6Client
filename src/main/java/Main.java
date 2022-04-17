import Command.Invoker;
import Command.Receiver;
import org.xml.sax.SAXException;
import utility.*;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Lab_5
 * @author Arhiiiip
 * @version 1.0
 */

public class Main {
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        String link = args[0];
        FileWorker fileWorker = new FileWorker();
        CollectionManager collectionManager = new CollectionManager(fileWorker.parse(link), LocalDateTime.now(), LocalDateTime.now(), 0, link);
        HashSet hashSetId = fileWorker.takeHashSetId();
        MovieFactory movieFactory = new MovieFactory(hashSetId, collectionManager);
        Receiver receiver = new Receiver();
        Invoker invoker = new Invoker(receiver, movieFactory);
        Scanner scanner = new Scanner(System.in);
        Reader reader = new Reader(scanner, invoker);
        Validator.setReader(reader);
        while (true) {
            System.out.println("Введите команду:");
            String commandUser = reader.read().trim();
            invoker.execute(commandUser);
        }
    }
}