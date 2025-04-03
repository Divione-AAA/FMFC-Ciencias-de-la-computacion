public class Banco {

    private double cantidad;
    private String nombre;

    public Banco(double c, String n) throws Exceptions{

        if(c<0){
            throw new Exceptions("puto el q lo lea");
        }
        if(n.equals("Fulana")){
            throw new Exceptions("no, por malpaga");
        }
        this.cantidad = c;
        this.nombre = n;
    }
}
