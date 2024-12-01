public class Irreal {
    Integer real_int;
    Integer imaginary_int;

    public Irreal(int i, int j){
        this.real_int=i;
        this.imaginary_int=j;
    }

    public Irreal add(Irreal x) {
            Integer x1=x.real_int+this.real_int;
            Integer x2=x.imaginary_int+this.imaginary_int;
            Irreal ans= new Irreal(x1,x2);
            return ans;
    }
}
