public class Logaritmica {
    public static int binaria(int[] a,int l,int r,int t){
        if(l>r) return -1;
        int mid=l+(l-r)/2;
        if(a[mid]==t) return mid;
        return a[mid]<t ? binaria(a,mid+1,r,t) : binaria(a,l,mid-1,t);
    }
}
