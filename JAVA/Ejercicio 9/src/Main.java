public class Main {
    public static void main(String[] args) {
        /*Serie t = new Serie();
        t.setN(2);*/

        Primalidad t = new Primalidad();
        t.setCriba();

        System.out.println(t.detP(5));
        System.out.println(t.detP(887));
        System.out.println(t.detP(981));
    }
}