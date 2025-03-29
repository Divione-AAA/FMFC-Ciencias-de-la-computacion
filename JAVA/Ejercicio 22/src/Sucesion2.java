public class Sucesion2 implements Aritmetica{

    private int v_inicial,a;

    public Sucesion2(int n){
        this.v_inicial = n;
        this.a = n;
    }

    @Override
    public int sucesion() {
        return this.a+=2;
    }

    @Override
    public void reset(){
        this.a = this.v_inicial;
        return;
    }
}
