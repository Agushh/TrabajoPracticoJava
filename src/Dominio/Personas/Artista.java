package Dominio.Personas;

import Dominio.Enums.TipoPers;
import Dominio.Personas.Datos.Acceso;
import Dominio.Zonas.Zona;

import java.util.List;
import java.util.TreeSet;

public class Artista extends Persona {

    public Artista(String nombre, String id, Zona zonaActual, TreeSet<Zona> zonasPermitidas, List<Acceso> accesos) {
        super(nombre, id, zonaActual, zonasPermitidas, accesos);
    }

    public Artista() {
    }

    public void addZona(Zona z){
        //todo tengo que verificar si ya existe un escenario, ya que solo puede tener uno
    }

    @Override
    public boolean puedeAcceder(Zona z) {
        return getZonasPermitidas().contains(z);
    }
}