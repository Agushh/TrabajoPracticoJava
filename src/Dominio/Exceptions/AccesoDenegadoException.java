package Dominio.Exceptions;

/**
 * Excepcion para cuando se quiere mover a una persona y no tiene permisos.
 */
public class AccesoDenegadoException extends GUIException {
  /**
   * Constructor.
   */
  public AccesoDenegadoException() {
    super("Acceso Denegado");
  }
}