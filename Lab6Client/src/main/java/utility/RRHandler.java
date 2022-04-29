package utility;

import command.Invoker;
import request.Deserializator;
import request.Sender;
import request.Serializator;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class RRHandler {

    Invoker invoker;
    Serializator serializator = new Serializator();
    SocketChannel socketChannel;
    Deserializator deserializator = new Deserializator();

    public RRHandler(Invoker invoker, SocketChannel socketChannel) {
        this.invoker = invoker;
        this.socketChannel = socketChannel;
    }

    public void req(String command) throws IOException {
        ObjectForServer req;
        command = command.replaceAll("\\s+", " ");
        invoker.execute(command);
        String[] parts = command.split(" ");
        if (parts.length == 2) {
            req = new ObjectForServer(parts[0], parts[1]);
        } else {
            req = new ObjectForServer(parts[0], "");
        }
        Sender.send(serializator.serialize(req), socketChannel);
    }

    public Object res(ByteBuffer buffer) throws IOException, ClassNotFoundException {
        socketChannel.read(buffer);
        Object o = deserializator.deserialize(buffer);
        return o;
//        Integer bytes = socketChannel.read(buffer);
//        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(buffer.array());
//        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
//        ByteBuffer command = (ByteBuffer) objectInputStream.readObject();
//        byteArrayInputStream.close();
//        System.out.println(command.toString());

    }
}

