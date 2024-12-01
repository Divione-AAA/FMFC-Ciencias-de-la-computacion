import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        //Inicializamos la lista entrelazada

        LinkedList<Persona> lista = new LinkedList<>();

        lista.add(new Persona("Juana","210905",19));
        lista.add(new Persona("Pablo","241005",18));
        lista.add(new Persona("Adriano","101005",18));

        //Serializacion

        try(ObjectOutput oos = new ObjectOutputStream(new FileOutputStream("list.bin"))){

            oos.writeObject(lista);

        }catch (IOException e){

            e.printStackTrace();

        }

        //Deserializando para acceder al archivo

        LinkedList<Persona> deserie = new LinkedList<Persona>();
        deserie=null;

        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("list.bin"))){

            deserie=(LinkedList<Persona>) ois.readObject();

        }catch (IOException e) {
            e.printStackTrace();
        }catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        //Modificando datos

        if(deserie != null){

            String remover="210905";
            deserie.removeIf(persona -> (persona.getCarnetIdentidad().equals(remover)));
            deserie.forEach(System.out::println);
        }

        //Serializar de nuevo para guardar los datos

        try(ObjectOutput oos = new ObjectOutputStream(new FileOutputStream("list.bin"))){

            oos.writeObject(deserie);

        }catch (IOException e){

            e.printStackTrace();

        }
    }
}