package modelo.enums;

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

