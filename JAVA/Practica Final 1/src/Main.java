import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception{
        //System.out.println("Hello, World!");
        Solicitud t1 = new SolicitudA(12.0,1,"AD612","Name1");
        Solicitud t2 = new SolicitudB(12,"AD612","Name1");
        System.out.println("El costo de la primera actividad es de: "+t2.getTiempo());
        ArrayList<Solicitud> t = new ArrayList<>();
        t.add(t1);
        t.add(t2);
        FileHandler.write("file",t);
        FileHandler tt = new FileHandler();
        t.indexOf(t1);
    }
}