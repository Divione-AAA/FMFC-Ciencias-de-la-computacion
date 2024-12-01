import java.util.Vector;
public class Persona {
    private int dia,mes,año;

    public Vector Getedad(){
        Vector<Integer> t = new Vector<Integer>();
        t.add(dia);
        t.add(mes);
        t.add(año);
        return t;
    }

    public void Setdia(int dia){
        this.dia=dia;
    }
    public void Setmes(int mes) {
        this.mes = mes;
    }
    public void Setano(int ano){
        this.año=ano;
    }
}
