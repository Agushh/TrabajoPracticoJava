package Dominio.Exceptions;

/**
 * Excepcion para cuando se quiere mover a una persona y no tiene permisos.
 */
public class AccesoDenegadoException extends GUIException {
  public AccesoDenegadoException() {
    super("Acceso Denegado");
  }
}