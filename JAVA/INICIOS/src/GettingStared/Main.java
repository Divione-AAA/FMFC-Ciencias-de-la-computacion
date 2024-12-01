package GettingStared;

public class Main {

    public static int f(String s, int n){
        if(n<0) return 0;

        if(s.charAt(n) == ' ' ) return 1+f(s,n-1);
        else                    return 0+f(s,n-1);
    }

    public static void main(String[] args) {
        String s= "hector david aguero";
        int n=s.length()-1;
        int ans=f(s,n);
        System.out.print(++ans);
    }
}