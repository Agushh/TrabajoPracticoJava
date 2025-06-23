package Dominio.Exceptions;

/**
 * Excepcion para cuando el archivo a cargar tiene datos incorrectos.
 */
public class DatosIncorrectosException extends GUIException {
  public DatosIncorrectosException(String message) {
    super(message);
  }
}
