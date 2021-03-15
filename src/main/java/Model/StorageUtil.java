package Model;

import java.io.*;
import java.util.List;

public class StorageUtil {
    String path;

    public StorageUtil(String dbName) throws FileNotFoundException, IOException {
        path = "data/" + dbName + ".ser";
        File file = new File(path);
        file.createNewFile();
    }

    public void serializeStoreList(List<?> list) throws IOException {
        FileOutputStream fileOutput = new FileOutputStream(path);
        ObjectOutputStream objOutput = new ObjectOutputStream(fileOutput);
        System.out.println("Recieved:" + list);
        objOutput.writeObject(list);
        System.out.println("List serialization and store success!");
        objOutput.close();
        fileOutput.close();
    }

    public <T> List<T> deserializeReadList() throws ClassNotFoundException, IOException {
        List<T> list = null;
            FileInputStream fileInput = new FileInputStream(path);
            ObjectInputStream objInput = new ObjectInputStream(fileInput);
            list = (List<T>) objInput.readObject();
            objInput.close();
            fileInput.close();
            System.out.println("List deserialization and read success!");
        return list;
    }
}
