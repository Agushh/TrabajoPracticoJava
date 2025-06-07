package Dominio.Personas;

import Dominio.Enums.TipoPers;
import Dominio.Zonas.Zona;

public class Comerciante extends Persona {

    public Comerciante(String nombre) {
        super(nombre, TipoPers.COMERCIANTE);
    }

    @Override
    public boolean puedeAcceder(Zona z) {
        return zonasPermitidas.contains(z);
    }
}