package boton.visual;

public class Cuadrado {

    private double lado;
    public double area;
    public double perimetro;
    
    public Cuadrado(double l){
        this.lado=l;
        CArea();
        CPerimetro();
    }
    
    private void CArea(){
        this.area=lado*lado;
    }
    
    private void CPerimetro(){
        this.perimetro=lado*4;
    }


}