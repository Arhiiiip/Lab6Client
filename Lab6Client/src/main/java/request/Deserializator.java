package request;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;

public class Deserializator {

    public Object deserialize(ByteBuffer buffer) throws ClassNotFoundException, IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(buffer.array());
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        Object o=objectInputStream.readObject();
        objectInputStream.close();
        byteArrayInputStream.close();
        return o;
    }
}
