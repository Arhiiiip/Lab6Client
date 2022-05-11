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
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 * Lab_5
 *
 * @author Arhiiiip
 * @version 1.0
 */

public class Main {
    private static SocketChannel clientSocket; //сокет для общения
    private static BufferedReader readerSer;
    private static BufferedReader in; // поток чтения из сокета
    private static OutputStream out; // поток записи в сокет

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
//        boolean connect = false;
//        while (!connect) {
//            try {
//                clientSocket = SocketChannel.open(new InetSocketAddress("localhost", 8080));
//                clientSocket.configureBlocking(false);
//                System.out.println("МАМА Я В ЮТУБЕ");
//                connect = true;
//            } catch (ConnectException e) {
//                System.out.println("СЕРВЕР ОФНУТ МАТЬ ТВОЮ");
//            }
//        }
//        Receiver receiver = new Receiver();
//        RRHandler rrHandler = new RRHandler(clientSocket);
//        Invoker invoker = new Invoker(receiver, rrHandler);
//        Scanner scanner = new Scanner(System.in);
//        Reader reader = new Reader(scanner, invoker);
//        Validator.setReader(reader);
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                Reader reader = new Reader(scanner);
                Validator.setReader(reader);
                System.out.println("Введите команду:");
                String commandUser = reader.read().trim();
                boolean connect = false;
                while (!connect) {
                    try {
                        clientSocket = SocketChannel.open(new InetSocketAddress("localhost", 8080));
                        clientSocket.configureBlocking(false);
                        connect = true;
                    } catch (ConnectException e) {
                        System.out.println("The server is down or busy...");
                    }
                }
                Receiver receiver = new Receiver();
                RRHandler rrHandler = new RRHandler(clientSocket);
                Invoker invoker = new Invoker(receiver, rrHandler);
                boolean start;
                try {
                    invoker.execute(commandUser);
                    start = true;
                } catch (RuntimeException e){
                    start = false;
                }
                if(start){
                ObjectForServer response = rrHandler.res();
                System.out.println(response.toString());}
            } catch (Exception e) {
                e.printStackTrace();
            }
            clientSocket.close();
        }
    }
}