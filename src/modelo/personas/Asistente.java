package modelo.personas;

import modelo.enums.TipoZona;
import modelo.zonas.Zona;


public class Asistente extends Persona{
    public Asistente(String nombre){
        super(nombre,'E'); // todo CAMBIAR LETRA POR ENUM???
    }

    @Override
    public boolean puedeAcceder(Zona z) {
        TipoZona tipo = z.getTipo();
        return tipo == TipoZona.ZONA_COMUN || tipo == TipoZona.ESCENARIO;
    }

}
