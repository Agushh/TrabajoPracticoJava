package Dominio.Personas;

import Dominio.Enums.TipoPers;
import Dominio.Enums.TipoZona;
import Dominio.Personas.Datos.Acceso;
import Dominio.Zonas.Escenario;
import Dominio.Zonas.Zona;
import Dominio.Zonas.ZonaComun;

import java.util.List;
import java.util.TreeSet;


public class Asistente extends Persona{

    public Asistente(String nombre, String id, Zona zonaActual, TreeSet<Zona> zonasPermitidas, List<Acceso> accesos) {
        super(nombre, id, zonaActual, zonasPermitidas, accesos);
    }

    public Asistente() {
    }

    @Override
    public boolean puedeAcceder(Zona z) {
        return z instanceof ZonaComun || z instanceof Escenario;
    }

}
