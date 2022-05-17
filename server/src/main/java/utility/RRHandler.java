package utility;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class RRHandler {


    static Socket socket;

    public RRHandler(Socket socket) {
        RRHandler.socket = socket;
    }

    public static Socket getSocket() {
        return socket;
    }

    public static void setSocket(Socket socket) {
        RRHandler.socket = socket;
    }

    public static void res(String result) {
        try {
            OutputStream outputStream = socket.getOutputStream();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objOut = new ObjectOutputStream(byteArrayOutputStream);
            objOut.writeObject(new ObjectForServer(result));
            objOut.flush();
            byteArrayOutputStream.writeTo(outputStream);
            byteArrayOutputStream.reset();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public static void resB(boolean result) {
        try {
            OutputStream outputStream = socket.getOutputStream();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objOut = new ObjectOutputStream(byteArrayOutputStream);
            objOut.writeObject(new ObjectForServer(result));
            objOut.flush();
            byteArrayOutputStream.writeTo(outputStream);
            byteArrayOutputStream.reset();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}


