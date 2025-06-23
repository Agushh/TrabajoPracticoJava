package Dominio.Exceptions;

/**
 * Excepcion para cuando el archivo a cargar esta corrupto.
 */
public class DeserializationException extends GUIException {
    /**
     * Constructor.
     */
    public DeserializationException(String message) {
        super(message);
    }
}
