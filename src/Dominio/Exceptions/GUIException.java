package Dominio.Exceptions;

//clase heredada de Exception para excepciones de GUI
public class GUIException extends RuntimeException {
    public GUIException(String message) {
        super(message);
    }
}
