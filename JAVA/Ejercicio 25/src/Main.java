import java.security.spec.ECField;

public class Main {
    public static void main(String[] args) {

        Obras t = new Obras("ejemplo",10,100);
        try {
            Integer s = 500;
            t.incrementar(s);
            System.out.println("Exito");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
