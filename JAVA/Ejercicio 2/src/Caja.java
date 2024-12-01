public class Caja {
    private Integer saldo=0;
    private Integer interes=0;

    public Caja(int interes){
        Integer f=(interes/100);
        this.interes=f;
    }

    public void depositar(int i){
        this.saldo+=(i*this.interes);
    }

    public void sacar(int i){
        if(i>this.saldo) this.saldo-=(i*this.interes);
        else System.out.println("Eres pobre, infeliz");
    }

    public void mostrarsaldo(){
        System.out.println(this.saldo);
    }
}
