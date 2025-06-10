package Dominio.Personas;

import Dominio.Enums.TipoPers;
import Dominio.Enums.TipoZona;
import Dominio.Zonas.Escenario;
import Dominio.Zonas.Zona;
import Dominio.Zonas.ZonaComun;


public class Asistente extends Persona{
    public Asistente(String nombre, Zona zonaActual){
        super(nombre, TipoPers.ASISTENTE, zonaActual);
    }

    @Override
    public boolean puedeAcceder(Zona z) {
        return z instanceof ZonaComun || z instanceof Escenario;
    }

}
