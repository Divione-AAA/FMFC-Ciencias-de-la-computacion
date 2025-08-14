import java.util.ArrayList;

public class busqueda {

    public static void quicksort(ArrayList<Integer> a, int l,int r){
        if(l<r){
            int p = particion(a,l,r);
            quicksort(a, l, p-1);
            quicksort(a, p+1, r);
        }
    }

    public static int particion(ArrayList<Integer> a, int l, int r){
        Integer p = a.get(r);
        int i = l-1;
        for(int j=l;j<r;j++){
            if(a.get(j)<=p){
                i++;
                //swap
                int t = a.get(i);
                a.set(i,a.get(j));
                a.set(j,t);
            }
        }
        return i+1;
    }

    public boolean binaria(ArrayList<Integer> a,int t){
        int l = 0;
        int r = a.size()-1;
        while(l<=r){
            int p = l+(r-l)/2;
            if(a.get(p)==t){
                return true;
            }
            if(a.get(p)>t){
                l = p+1;
            }else{
                r = p-1;
            }
        }
        return false;
    }
    

    public static void main(String[] args) {
        
    }
}
