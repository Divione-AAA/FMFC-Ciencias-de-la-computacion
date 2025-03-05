public class Emoglobina {

    public static double[] r;
    public String revertida,s;

    public Emoglobina(String s){
        this.s=s;
    }

    public static double menor(double[] a){

        if(a.length==1) return a[0];

        double c=a[a.length-1];
        double b[]=new double[a.length-1];
        System.arraycopy(a,0,b,0,a.length-1);
        return Math.min(c,menor(b));
    }

    public static double m(int n){
        if(n==0){
            return r[0];
        }
        return Math.min(r[n],m(n-1));
    }

    public void reverse(){
        if(s.length()==0) return;
        char f=s.charAt(s.length()-1);
        revertida+=f;
        String m;
        if(s.length()>1) m=s.substring(0,s.length()-2);
        else m=s.substring(0);
        this.s=m;
        reverse();
    }

    public static String reve(String s){
        if(s.length()==1) return s;
        return s.charAt(s.length()-1)+reve(s.substring(0,s.length()-2));
    }
}

/*
 un metodo void que revierta una cadena
* */