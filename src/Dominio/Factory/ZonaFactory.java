package Dominio.Factory;

import Dominio.Enums.TipoZona;
import Dominio.Personas.Comerciante;
import Dominio.Zonas.*;

public class ZonaFactory {

    private static void throwExce(TipoZona tipo) throws IllegalArgumentException {
        throw new IllegalArgumentException("Tipo de zona o cantidad de parametros invalidos: " + tipo);
    }

    // todo HACE FALTA ESTE FACTORY CUANDO AL ESTAR SOBRE CARGANDO EL ADD EN EL CONTROLADOR PODRIA LLAMAR AL CONSTRUCTOR DIRECTAMENTE DESDE AHI

    public static Zona crear(TipoZona tipo, String descripcion) throws IllegalArgumentException {
        Zona z = null;

        switch(tipo){
            case ZONA_COMUN:
                z = new ZonaComun(descripcion);
                break;
            default:
                throwExce(tipo);
        }

        return z;
    }

    public static Zona crear(TipoZona tipo, String descripcion, int capacidad) throws IllegalArgumentException {
        Zona z = null;

        switch (tipo) {
            case ESCENARIO:
                z = new Escenario(descripcion, capacidad);
                break;
            case ZONA_RESTRINGIDA:
                z = new ZonaRestringida(descripcion, capacidad);
                break;
            default:
                throwExce(tipo);
        }
        return z;
    }

    public static Zona crear(TipoZona tipo, String descripcion, int capacidad, Zona zona, Comerciante responsable) throws IllegalArgumentException {
        Zona z = null;

        switch(tipo){
            case STAND:
                z = new Stand(descripcion, capacidad, zona, responsable);
                break;
            default:
                throwExce(tipo);
        }

        return z;
    }
}
