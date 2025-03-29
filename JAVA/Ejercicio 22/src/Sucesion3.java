public class Sucesion3 implements Aritmetica{

    private int v_inicial,a;

    public Sucesion3(int n){
        this.v_inicial = n;
        this.a = n;
    }

    @Override
    public int sucesion() {
        return this.a+=3;
    }

    @Override
    public void reset(){
        this.a = this.v_inicial;
        return;
    }
}
