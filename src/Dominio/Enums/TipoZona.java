package Dominio.Enums;

public enum TipoZona {
    ESCENARIO, ZONA_COMUN, ZONA_RESTRINGIDA, STAND;

    public String toString() { //todo ROMPO LA PROGRAMACION ESTRUCTURADA?
        switch(this) {
            case ESCENARIO: return "ESC";
            case ZONA_COMUN: return "COM";
            case ZONA_RESTRINGIDA: return "RES";
            case STAND: return "STA";
            default: return "";
        }
    }
}

/*
Comentario facu: lo mismo que con tipopers, se puede ahorrar el switch y creo que queda mas prolijo:

package modelo.enums;

public enum TipoZona {
    ESCENARIO("ESC"),
    ZONA_COMUN("COM"),
    ZONA_RESTRINGIDA("RES"),
    STAND("STA");

    private final String codigo;

    TipoZona(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return codigo;
    }
}


*/


