package Dominio.Enums;

public enum TipoPers {
    ARTISTA, ASISTENTE, STAFF, COMERCIANTE;

    public String trunc() { //todo ROMPO LA PROGRAMACION ESTRUCTURADA?
        switch(this) {
            case ARTISTA: return "ART";
            case ASISTENTE: return "ASI";
            case STAFF: return "STA";
            case COMERCIANTE: return "COM";
            default: return "";
        }
    }
}

/*
//comentario facu: Creo que no rompe la programacion estructurada, pero para ahorrarse el switch, tambien puede ser: 

public enum TipoPers {
    ARTISTA("ART"), ASISTENTE("ASI"), STAFF("STA"), COMERCIANTE("COM");

    private final String codigo;

    TipoPers(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return codigo;
    }
}

*/
