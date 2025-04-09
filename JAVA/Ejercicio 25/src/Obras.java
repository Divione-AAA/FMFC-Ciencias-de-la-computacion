public class Obras {
    private String nombre;
    private Integer superficie_t,superficie;

    public Obras(String s, Integer superficie, Integer superficie_t){
        this.nombre = s;
        this.superficie = superficie;
        this.superficie_t = superficie_t;
    }

    public void incrementar(Integer t) throws Exception{
        if(t+superficie > superficie_t){
            throw new Exceptions("Valor fuera de limites");
        }else{
            this.superficie+=t;
        }
    }
}
