package Dominio.Personas;

import Dominio.Enums.TipoPers;
import Dominio.Personas.Datos.Acceso;
import Dominio.Zonas.Zona;

import java.util.List;
import java.util.TreeSet;

public class Comerciante extends Persona {

    public Comerciante(String nombre, String id, Zona zonaActual, TreeSet<Zona> zonasPermitidas, List<Acceso> accesos) {
        super(nombre, id, zonaActual, zonasPermitidas, accesos);
    }

    public Comerciante() {
    }

    @Override
    public boolean puedeAcceder(Zona z) {
        return getZonasPermitidas().contains(z);
    }
}