package INTERFACES;

public class Estudiante extends Escuela implements Persona{

    public Estudiante(String n){
        super(n);
    }

    @Override
    public void comer() {
        System.out.println("estoy comiendo");
    }
}
