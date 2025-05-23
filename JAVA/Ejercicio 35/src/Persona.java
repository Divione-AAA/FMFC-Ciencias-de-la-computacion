public class Persona {

    private String nombre;
    private String apellido;
    private int edad;

    public Persona(String nombre, String apellido, int edad) throws Exception {

        if (nombre == null || apellido == null || edad < 0) {
            throw new Exception("usuario no vlaido");
        }

        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
    }
}
