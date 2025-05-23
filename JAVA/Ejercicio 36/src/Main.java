import java.io.*;

public class Main {
    public static void main(String[] args){
        Persona f = new Persona("Fulana","Detal",19);

        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Fulana.txt"))){
            oos.writeObject(f);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Fulana.txt"))){
            Persona p = (Persona) ois.readObject();
            System.out.println(p.nombre + " " + p.apellido + " " + p.edad);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}