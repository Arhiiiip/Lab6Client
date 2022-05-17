import utility.ObjectForServer;
import command.Invoker;
import command.Receiver;
import org.xml.sax.SAXException;
import utility.*;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Lab_5
 *
 * @author Arhiiiip
 * @version 1.0
 */

public class Main {

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        final int PORT = 8080;
        ServerSocket serverSocket = new ServerSocket(PORT);


        String link = args[0];
        FileWorker fileWorker = new FileWorker(link);
        CollectionManager collectionManager = new CollectionManager(fileWorker.parse(), LocalDateTime.now(), LocalDateTime.now(), 0, link);
        HashSet hashSetId = fileWorker.takeHashSetId();
        MovieFactory movieFactory = new MovieFactory(hashSetId, collectionManager);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> fileWorker.saveAndExit(collectionManager.getMoviesLinkedHashSet())));

        Receiver receiver = new Receiver();
        Invoker invoker = new Invoker(receiver, movieFactory);
        Scanner scanner = new Scanner(System.in);
        Reader reader = new Reader(scanner, invoker);
        Validator.setReader(reader);
        while (true) {
            Socket clientConnect = serverSocket.accept();
            RRHandler rrHandler = new RRHandler(clientConnect);
            invoker.setRrHandler(rrHandler);
            String client = clientConnect.toString().substring(23, 33);
            System.out.println("подключился пользователь " + client);
            try {
                ObjectForServer command;
                while (clientConnect.isConnected()) {
                    try {
                        InputStream stream = rrHandler.getSocket().getInputStream();
                        ObjectInputStream objectInputStream = new ObjectInputStream(stream);
                        command = (ObjectForServer) objectInputStream.readObject();
                        if (command != null) {
                            invoker.execute(command);
                        }

                    } catch (SocketException e) {
                        System.out.println("Client " + client + " was disconnect");
                        clientConnect.close();
                        break;
                    }
                }
            } catch (Exception e) {
                System.out.println("Client finish operation");
            }
        }
    }
}