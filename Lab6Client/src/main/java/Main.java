import command.Invoker;
import command.Receiver;
import org.xml.sax.SAXException;
import utility.*;

import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Lab_5
 * @author Arhiiiip
 * @version 1.0
 */

public class Main {
    private static SocketChannel clientSocket; //сокет для общения
    private static BufferedReader readerSer;
    private static BufferedReader in; // поток чтения из сокета
    private static OutputStream out; // поток записи в сокет
    private static ByteBuffer buffer;
    private static RRHandler rrHandler;

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {

        boolean connect = false;
        while (!connect){
            try {
                clientSocket = SocketChannel.open(new InetSocketAddress("localhost", 8080));
                clientSocket.configureBlocking(false);
                connect = true;
                System.out.println("Подключение прошло успешно");
            }catch (ConnectException e){
                System.out.println("Сервер отключен");
            }
            buffer = ByteBuffer.allocate(100000);
        }

        String link = args[0];
        FileWorker fileWorker = new FileWorker();
        CollectionManager collectionManager = new CollectionManager(fileWorker.parse(link), LocalDateTime.now(), LocalDateTime.now(), 0, link);
        HashSet hashSetId = fileWorker.takeHashSetId();
        MovieFactory movieFactory = new MovieFactory(hashSetId, collectionManager);
        Receiver receiver = new Receiver();
        Invoker invoker = new Invoker(receiver, clientSocket);
        Scanner scanner = new Scanner(System.in);
        Reader reader = new Reader(scanner, invoker);
        rrHandler = new RRHandler(invoker, clientSocket);
        Validator.setReader(reader);

        while (true) {
            try{
                System.out.println("Введите команду:");
                String commandUser = reader.read().trim();
                rrHandler.req(commandUser);
                ObjectForServer response = (ObjectForServer) rrHandler.res(buffer);
                System.out.println(response.toString());
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
}