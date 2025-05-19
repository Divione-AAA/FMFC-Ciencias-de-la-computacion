import java.io.*;

public class Main {
    public static void main(String[] args) {

        try {
            File test  = new File("test");
            System.out.println(test.getPath());
        }catch (Exception e){
            e.printStackTrace();
        }




    }
}