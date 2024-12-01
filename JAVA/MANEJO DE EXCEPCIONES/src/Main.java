public class Main {
    public static void main(String[] args) {

        try {
            System.out.println(12 / 0);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}