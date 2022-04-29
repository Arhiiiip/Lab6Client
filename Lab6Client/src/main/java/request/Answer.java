package request;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Answer {

    public static SocketChannel socket;

    public Answer(SocketChannel socket) {
        Answer.socket = socket;
    }

    public String answer(ByteBuffer buffer) throws IOException, ClassNotFoundException {
        ByteArrayInputStream byteArrayToServerStream = new ByteArrayInputStream(buffer.array());
        ObjectInputStream fromServer = new ObjectInputStream(byteArrayToServerStream);
        Object object = fromServer.readObject();
        fromServer.close();
        byteArrayToServerStream.close();
        return (object.toString());
    }
}
