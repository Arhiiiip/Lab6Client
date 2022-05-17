package request;

import data.Movie;
import utility.ObjectForServer;

import java.io.IOException;
import java.nio.channels.SocketChannel;

public class RRHandler {

    Serializator serializator = new Serializator();
    SocketChannel socketChannel;


    public RRHandler(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }


    public void reqOb(String command, Movie movie) throws IOException {
        ObjectForServer req;
        req = new ObjectForServer(command, movie);
        Sender.send(serializator.serialize(req), socketChannel);
    }

    public void req(String command, String arg) throws IOException {
        ObjectForServer req;
        req = new ObjectForServer(command, arg);
        Sender.send(serializator.serialize(req), socketChannel);
    }

    public ObjectForServer res() throws IOException, ClassNotFoundException {
        Deserializator deserializator = new Deserializator();
        return deserializator.deserialize(socketChannel);
    }

    public Serializator getSerializator() {
        return serializator;
    }

    public void setSerializator(Serializator serializator) {
        this.serializator = serializator;
    }

    public SocketChannel getSocketChannel() {
        return socketChannel;
    }

    public void setSocketChannel(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }
}