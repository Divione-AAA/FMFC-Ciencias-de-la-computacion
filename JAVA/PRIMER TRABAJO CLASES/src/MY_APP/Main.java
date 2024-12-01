package MY_APP;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        Estudiante david = new Estudiante("David",19);

        System.out.println("ponga las calificaciones");
        Scanner cin = new Scanner(System.in);

        boolean l=true;

        do{
            int e = cin.nextInt();
            int m = cin.nextInt();
            int s = cin.nextInt();
            int c = cin.nextInt();
            l=david.notas(e,m,s,c);
        }while (l);

        System.out.println("El promedio es:");
        david.promedio();
    }
}