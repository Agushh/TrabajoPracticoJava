package Dominio.Personas;

import Dominio.Enums.TipoPers;
import Dominio.Zonas.Zona;

public class Comerciante extends Persona {

    public Comerciante(String nombre, Zona zonaActual) {
        super(nombre, TipoPers.COMERCIANTE, zonaActual);
    }

    @Override
    public boolean puedeAcceder(Zona z) {
        return getZonasPermitidas().contains(z);
    }
}