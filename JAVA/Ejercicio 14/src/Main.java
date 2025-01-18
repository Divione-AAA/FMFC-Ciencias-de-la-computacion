public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Trabajador f = new Trabajador();
        String m="dabalearrozalazorraelabad";
        f.setStr(m);
        boolean j=f.pal(0,m.length()-1);
        System.out.println(j ? "1" : "0");
    }
}