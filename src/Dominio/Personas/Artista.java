package Dominio.Personas;

import Dominio.Enums.TipoPers;
import Dominio.Personas.Datos.Acceso;
import Dominio.Zonas.Escenario;
import Dominio.Zonas.Zona;

import java.util.List;
import java.util.TreeSet;

public class Artista extends Persona {

    private Escenario escenario;

    public Artista(String nombre, String id, TreeSet<Zona> zonasPermitidas, List<Acceso> accesos) {
        super(nombre, id, zonasPermitidas, accesos);
    }

    public Artista() {
    }

    public void addZona(Zona z){
        if(z instanceof Escenario escenario) {
            getZonasPermitidas().remove(this.escenario);
            this.escenario = escenario;
            addZona(escenario);
        }
    }

    @Override
    public boolean puedeAcceder(Zona z) {
        return z.equals(escenario) || getZonasPermitidas().contains(z);
    }
}