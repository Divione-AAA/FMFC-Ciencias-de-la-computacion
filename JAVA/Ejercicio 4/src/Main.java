import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Introduzca las coordenadas x y y de un punto en el plano");
        Scanner cin = new Scanner(System.in);
        Punto uno = new Punto();
        double x,y;
        x=cin.nextInt();
        y=cin.nextInt();
        uno.setPunto(x,y);
        System.out.println("El cuadrante al cual pertence es "+uno.determinarCuadrante());
        System.out.println("La distancia es de "+uno.determinarDistancia());
    }
}