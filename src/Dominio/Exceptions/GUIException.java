package Dominio.Exceptions;

/**
 * Excepcion padre excepciones de la UI.
 */
public class GUIException extends RuntimeException {
    /**
     * Constructor.
     */
    public GUIException(String message) {
        super(message);
    }

}

