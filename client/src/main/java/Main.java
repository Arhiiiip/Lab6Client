import utility.ObjectForServer;
import command.Invoker;
import command.Receiver;
import request.RRHandler;
import utility.Reader;
import utility.Validator;

import java.io.IOException;
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


    public static void main(String[] args) throws IOException {
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                Reader reader = new Reader(scanner);
                Validator.setReader(reader);
                System.out.println("Введите команду:");
                String commandUser = reader.read().trim();
                boolean connect = false;
                int i = 0;
                while (!connect && i < 5) {
                    try {
                        clientSocket = SocketChannel.open(new InetSocketAddress("localhost", 8080));
                        clientSocket.configureBlocking(false);
                        connect = true;
                    } catch (ConnectException e) {
                        System.out.println("The server is down or busy...");
                        i += 1;
                    }
                }
                if (i == 5){
                    continue;
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