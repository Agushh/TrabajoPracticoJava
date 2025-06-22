package Dominio.Personas;

import Dominio.Enums.TipoPers;
import Dominio.Personas.Datos.Acceso;
import Dominio.Zonas.Stand;
import Dominio.Zonas.Zona;

import java.util.List;
import java.util.TreeSet;

public class Comerciante extends Persona {

    private Stand stand;

    public Comerciante(String nombre, String id) {
        super(nombre, id);
    }

    public Comerciante() {
    }

    public Stand getStand() {
        return stand;
    }

    public void setStand(Stand stand) {
        this.stand = stand;
    }

    @Override
    public boolean puedeAcceder(Zona z) {
        return z.equals(stand) || getZonasPermitidas().contains(z);
    }
}