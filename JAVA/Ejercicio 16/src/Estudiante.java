public class Estudiante {
    //nombre y carnet de identidad y metodos recursivos para determinar cantidad de palbras del nombre y si es palindromo

    public String nombre,carnet;
    public String[] nombres;//ordenado buscar persona en especifico

    public static int cantidad(String s){
        if(s.length()==0) return 1;
        char f=s.charAt(s.length()-1);
        String t=s.substring(0,s.length()-1);
        //System.out.println(t);
        return f==' ' ? 1+cantidad(t) : cantidad(t);
    }



}
