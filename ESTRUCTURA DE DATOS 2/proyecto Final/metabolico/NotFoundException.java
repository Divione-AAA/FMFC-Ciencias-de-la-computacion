package metabolico;

/**
 * Excepción lanzada cuando no se encuentra un elemento esperado en la red
 * (p. ej. al intentar eliminar un metabolito inexistente).
 *
 * Se define como checked exception para forzar el manejo en las capas que
 * modifiquen la estructura de la red.
 */
public class NotFoundException extends Exception {
    public NotFoundException(String message) { super(message); }
}
