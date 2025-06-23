package Dominio.Exceptions;

/**
 * Excepcion para cuando el archivo a cargar tiene datos incorrectos.
 */
public class DatosIncorrectosException extends GUIException {
  /**
   * Constructor.
   */
  public DatosIncorrectosException(String message) {
    super(message);
  }
}
