import java.util.LinkedList;
import java.util.Scanner;
import java.io.*;
public class Main {

    public static double determinarPromedio(LinkedList<Ruta> lista){
        return 2.2;
    }

    public static void serializar(LinkedList<Ruta> l, String na){

        try(ObjectOutput oos = new ObjectOutputStream(new FileOutputStream(na))){
            oos.writeObject(l);
            System.out.println("Ruta anadida con exito");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static LinkedList<Ruta> deserializar(){

        LinkedList<Ruta> d = new LinkedList<>();
        d=null;

        try (ObjectInput ois = new ObjectInputStream(new FileInputStream("list.bin"))){
            d =( LinkedList<Ruta> ) ois.readObject();
        }catch(IOException | ClassNotFoundException e){
            e.printStackTrace();;
        }
        return d;
    }

    public static LinkedList<Ruta>  eliminar(int identificador, LinkedList<Ruta> list){
        LinkedList<Ruta> t=deserializar();

        if(t != null){
            t.removeIf(ruta -> (ruta.getNumeroIdentificacion()==(identificador)));
        }
        serializar(t,"list.bin");

        return t;
    }

    public static void main(String[] args) {

        Scanner cin = new Scanner(System.in);
        int seleccion=-1,auxiliarSeleccion=-1,identificador;
        double promedio=0.0;
        System.out.println("1: Adicionar nueva ruta");
        System.out.println("2: Eliminar ruta");
        System.out.println("3: Determinar anio de mantenimiento");
        System.out.println("0: Salir");
        LinkedList<Ruta> lista = new LinkedList<Ruta>();

        while(seleccion!=0){
            seleccion = cin.nextInt();

            if(seleccion == 1){
                System.out.println("Seleccione '1' si la ruta es local o '2' si es municipal");

                while (auxiliarSeleccion == -1){

                    auxiliarSeleccion = cin.nextInt();

                    if(auxiliarSeleccion == 1){

                        System.out.println("Introduzca paradas intermedias, omnibus asignados, distancia y  numero de identificacion");

                        int p = cin.nextInt();
                        int o = cin.nextInt();
                        int d = cin.nextInt();
                        int i = cin.nextInt();

                        Ruta_local tl = new Ruta_local(p,o,d,i);
                        lista.add(tl);

                        auxiliarSeleccion = 1;
                        serializar(lista,"list.bin");

                    }else if(auxiliarSeleccion == 2){

                        System.out.println("Introduzca la distancia, el numero de identificacion, matricula y fecha de mantenimiento");

                        int d = cin.nextInt();
                        int i = cin.nextInt();
                        String c= cin.nextLine();
                        String m= cin.nextLine();

                        Ruta_municipal tm = new Ruta_municipal(d,i,c,m);
                        lista.add(tm);

                        auxiliarSeleccion = 1;
                        serializar(lista,"list.bin");

                    }else {
                        System.out.println("Muestre un valor valido dentro del rango");
                        auxiliarSeleccion=-1;
                    }
                }
            }else if(seleccion == 2){
                System.out.println("Introduzca el identificador de la ruta:");
                identificador= cin.nextInt();
                lista=eliminar(identificador,lista);
            }else if(seleccion == 3){
                System.out.println("Introduzca el numero identificador de la ruta:");
                identificador= cin.nextInt();
            }else{
                System.out.println("Y gracias por usar nuestros servicios");
            }
        }
    }
} 