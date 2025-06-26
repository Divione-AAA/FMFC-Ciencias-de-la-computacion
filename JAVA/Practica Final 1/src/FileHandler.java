import java.io.*;
import java.util.ArrayList;

public class FileHandler{

    public static void write(String filename, ArrayList<Solicitud> list) throws FileNotFoundException {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename+".bin")) ) {
            oos.writeObject(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static ArrayList<Solicitud> read(String filename) throws FileNotFoundException {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename+".bin"))){
           ArrayList<Solicitud> d = (ArrayList<Solicitud>) ois.readObject();
           return  d;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
