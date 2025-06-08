package Dominio.Personas;

import Dominio.Enums.TipoPers;
import Dominio.Enums.TipoZona;
import Dominio.Zonas.Zona;


public class Asistente extends Persona{
    public Asistente(String nombre, Zona zonaActual){
        super(nombre, TipoPers.ASISTENTE, zonaActual);
    }

    @Override
    public boolean puedeAcceder(Zona z) {
        TipoZona tipo = z.getTipo();
        return tipo == TipoZona.ZONA_COMUN || tipo == TipoZona.ESCENARIO;
    }

}
