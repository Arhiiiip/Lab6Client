import DBManager.ImportCollection;
import DBManager.UpdateDB;
import command.Invoker;
import command.Receiver;
import utility.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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

    private static final String DB_USERNAME = "s336947";
//    private static final String DB_PASSWORD = "";
    private static final String DB_URL = "jdbc:postgresql://localhost:8080/studs";

    public static void main(String[] args) throws IOException, SQLException {
        Scanner scannerr = new Scanner(System.in);
        final String DB_PASSWORD = scannerr.nextLine();
        final int PORT = 8090;
        scannerr.close();
        ServerSocket serverSocket = new ServerSocket(PORT);

        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);


        ImportCollection importCollection = new ImportCollection(connection);
        CollectionManager collectionManager = new CollectionManager(importCollection.getCollectionFromDB(), LocalDateTime.now(), LocalDateTime.now(), 0);
        HashSet hashSetId = importCollection.getHashSetId();
        MovieFactory movieFactory = new MovieFactory(hashSetId, collectionManager, connection);
        UpdateDB updateDB = new UpdateDB(connection);

//        Runtime.getRuntime().addShutdownHook(new Thread(() -> SaveToDB.saveAndExit(collectionManager.getMoviesLinkedHashSet())));

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

            } catch (Exception e) {
                System.out.println("Client finish operation");
            }
            updateDB.update(collectionManager.getMoviesLinkedHashSet(), hashSetId);
        }
    }
}