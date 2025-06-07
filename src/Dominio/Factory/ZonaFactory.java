package Dominio.Factory;

import Dominio.Enums.TipoZona;
import Dominio.Personas.Comerciante;
import Dominio.Zonas.*;

public class ZonaFactory {

    public static Zona crear(TipoZona tipo, String desc) throws IllegalArgumentException {
        Zona z;

        switch(tipo){//todo PENSAR COMO RESOLVER TEMA PARAMETROS
            case ZONA_COMUN:
                z = new ZonaComun(desc);
                break;
            case ZONA_RESTRINGIDA:
                z = new ZonaRestringida(desc,40);
                break;
            case ESCENARIO:
                z = new Escenario(desc,40);
                break;
            case STAND:
                z = new Stand(desc,40, "", new Comerciante("Aeljo"));
                break;
            default:
                throw new IllegalArgumentException("Tipo de zona inválido: " + tipo);
        }

        return z;
    }
}
