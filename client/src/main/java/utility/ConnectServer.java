//package utility;
//
//import java.io.IOException;
//import java.net.ConnectException;
//import java.net.InetSocketAddress;
//import java.nio.channels.SocketChannel;
//
//public class ConnectServer {
//
//    public static boolean connect(SocketChannel clientSocket) throws IOException {
//        boolean connect = false;
//        int i = 0;
//        while (!connect && i < 5) {
//            try {
//                clientSocket = SocketChannel.open(new InetSocketAddress("localhost", 8090));
//                clientSocket.configureBlocking(false);
//                connect = true;
//            } catch (ConnectException e) {
//                System.out.println("The server is down or busy...");
//                i += 1;
//            }
//        }
//        if (i == 5) {
//            System.out.println("Not connection...");
//            System.exit(0);
//        }
//        return connect;
//    }
//}
