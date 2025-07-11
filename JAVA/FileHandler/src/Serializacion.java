import java.io.*;
import java.util.ArrayList;
//para que funcione la clase objeto debe de implementar serializabele
public class Serializacion {

    //esto escribe el arraylist en un objeto
    public void write(ArrayList<Objeto> t,String n) throws Exception {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(n))){
            oos.writeObject(t);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void read() throws Exception {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("text.bin"))){
            ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
