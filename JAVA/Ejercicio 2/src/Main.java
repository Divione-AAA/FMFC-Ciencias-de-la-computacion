import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        int s=4;
        int t;
        Scanner cin = new Scanner("System.in");
        Caja bpa = new Caja(6);
        System.out.println("Introduce 1 para añadir, 2 para sacar y 3 para mostrar, 0 para salir");
        while(s>0) {

            s=cin.nextInt();

            if(s==1){
                t=cin.nextInt();
                bpa.depositar(t);
            }else if(s==2){
                t=cin.nextInt();
                bpa.sacar(t);
            }else if(s==3){
                t=cin.nextInt();
                bpa.mostrarsaldo();
            }
        }
    }
}