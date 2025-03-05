public class MCD {

    public int a,b;

    public int mcd(int a, int b){
        return b>0 ? mcd(b,a%b) : a;
    }

    public static int sum(int[] a){
        if(a.length==0) return 0;
        int b[] = new int[a.length-1];
        System.arraycopy(a,0,b,0,a.length-1);
        return sum(b)+a[a.length-1];
    }

    public boolean pal(String s){
        if(s.length()<=1) return true;
        if(s.charAt(0)==s.charAt(s.length()-1)){
            String t;
            if(s.length()>2)t=s.substring(1,s.length()-1);
            else t=s.substring(0,1);
            //System.out.println(t);
            return pal(t);
        }
        return false;
    }
}//anade  otro metodo q sume list de valores externos con metodo recursiv*/