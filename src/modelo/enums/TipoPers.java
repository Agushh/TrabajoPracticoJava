package modelo.enums;

public enum TipoPers {
    ARTISTA, ASISTENTE, STAFF, COMERCIANTE;

    public String toString() { //todo ROMPO LA PROGRAMACION ESTRUCTURADA?
        switch(this) {
            case ARTISTA: return "ART";
            case ASISTENTE: return "ASI";
            case STAFF: return "STA";
            case COMERCIANTE: return "COM";
            default: return "";
        }
    }
}

