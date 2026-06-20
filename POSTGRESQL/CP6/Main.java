public class Main {
    public static void main(String[] args) throws Exception {
        try {
            Conexion conexion = new Conexion("postgres", "postgres");
            Manipuladora manipuladora = new Manipuladora(conexion);

            String idTrabajador = "1";
            Trabajador trabajador = manipuladora.buscar(idTrabajador);

            System.out.println("ID Trabajador: " + idTrabajador);
            System.out.println("Nombre: " + trabajador.getNombre());

            manipuladora.cerrar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
