package Dominio.Exceptions;

public class DeserializationException extends RuntimeException {
    public DeserializationException(String message) {
        super(message);
    }
}
