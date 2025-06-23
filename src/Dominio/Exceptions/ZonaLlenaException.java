package Dominio.Exceptions;

/**
 * Excepcion cuando una zona esta llena.
 */
public class ZonaLlenaException extends GUIException {
  public ZonaLlenaException() {
    super("Zona Llena");
  }
}
