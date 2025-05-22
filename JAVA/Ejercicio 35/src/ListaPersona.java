import java.util.ArrayList;

public class ListaPersona {
    ArrayList<Persona> personas = new ArrayList();

    public void addPersonas(Persona t){
        personas.add(t);
    }

    public void delPersona(Persona t){
        personas.remove(t);
    }

    public ArrayList<Persona> getPersonas() {
        return personas;
    }
}
