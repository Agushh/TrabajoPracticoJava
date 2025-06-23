package Dominio.Exceptions;

/**
 * Excepcion cuando la zona a la cual se quiere mover es la actual.
 */
public class ZonaEsLaActualException extends GUIException {
    /**
     * Constructor.
     */
    public ZonaEsLaActualException() {
        super("El Zona es la actual");
    }
}