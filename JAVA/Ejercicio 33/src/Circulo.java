public class Circulo {
    private Double radio;

    public Circulo(Double r) throws Exception{
        if(r<=0) throw new Exception("Numero invalido");
        this.radio = r;
    }

    public Double getArea() throws Exception{
        if(radio<=0) throw new Exception("Instancie la clase");
        return 3.14*radio*radio;
    }
}
