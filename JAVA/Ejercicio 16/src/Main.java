public class Main {
    public static void main(String[] args) {
        MCD f = new MCD();
        int a[]=new int[4];
        a[0]=4;a[1]=4;a[2]=4;a[3]=4;
        System.out.println(f.pal("aaaaaaaa"));
    }
}

/*

a=0,b=s.size()-1;
string s;

pal(int a,int b)
    if(a>=b) return true;
    if(s[a]!=s[b]) return false;
    return pal(a+1,b-1);


*/