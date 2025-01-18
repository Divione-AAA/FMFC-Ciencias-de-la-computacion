public class Palindromo{

    public boolean pal(String str){

        int a=0;
        int b=str.length()-1;
        char c,d;

        while(a<b){
            c=str.charAt(a);
            d=str.charAt(b);
            if(c!=d){
                return false;
            }
            a++;b--;
        }
        return true;
    }
}
