public class Main {
    public static void main(String[] args) {
        //System.out.println("Hello world!");
        Persona f = new Persona("Fulana");
        try{
            f.addAmigo("Esperanza");
            f.nombreDe(9180);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}