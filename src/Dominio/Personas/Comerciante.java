package Dominio.Personas;

import Dominio.Zonas.Stand;
import Dominio.Zonas.Zona;

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