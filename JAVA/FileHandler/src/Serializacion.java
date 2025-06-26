import java.io.*;
import java.util.ArrayList;
//para que funcione la clase objeto debe de implementar serializabele
public class Serializacion {
    //esto escribe el arraylist en un objeto
    public void write(ArrayList<Objeto> t) throws Exception {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("text.bin"))){
            oos.writeObject(t);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //este metodo lo lee el arraylist serializado y lo pone en otro, esto es deserializacion, no lo piden
    public void read() throws Exception {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("text.bin"))){
            ArrayList<Objeto> t = (ArrayList<Objeto>) ois.readObject();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
