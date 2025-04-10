public class Triangulo {
    Integer a,b,c;

    private boolean det(Integer a,Integer b, Integer c){
        return a > b + c || b > c + a || c > a + b || (a==0 && b==0 && c==0);
    }

    public Triangulo(Integer a,Integer b,Integer c) throws Exception{
        if(det(a,b,c)){
            throw new Exception("Triangulo invalido");
        }else{
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }
}
