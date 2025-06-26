import java.io.Serializable;

public class Solicitud implements Serializable {
    private Integer numero;
    private String codigo;
    private String nombre;

    public Solicitud(Integer numero, String codigo, String nombre) throws Exception{
        this.numero = numero;
        this.codigo = codigo;
        this.nombre = nombre;
        if(numero==null || codigo==null || nombre==null || nombre.length()==0 || numero<0){
            throw new ConstException("El constructor es invalido");
        }
    }

    public String getCodigo() {
        return codigo;
    }

    public Integer getTiempo(){
    return Tiempo(codigo,codigo.length()-1,"");
    }

    private Integer Tiempo(String codigo,int p,String s){
        if (p>0){
            char r = codigo.charAt(p);
            if((r<97 || r>122) && (r<65 || r>90)){
               s+=r;
            }
            return Tiempo(codigo,p-1,s);
        }else{
            char r = codigo.charAt(0);
            if((r<97 || r>122) && (r<65 || r>90)){
                s+=r;
            }
            String f = "";
            for(int i=s.length()-1;i>=0;i--){
                f+=s.charAt(i);
            }
            return Integer.parseInt(f);
        }
    }
}
