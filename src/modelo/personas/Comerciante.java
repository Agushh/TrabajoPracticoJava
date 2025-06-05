package modelo.personas;

import modelo.enums.TipoPers;
import modelo.zonas.Zona;

public class Comerciante extends Persona {

    public Comerciante(String nombre) {
        super(nombre, TipoPers.COMERCIANTE);
    }

    @Override
    public boolean puedeAcceder(Zona z) {
        return zonasPermitidas.contains(z);
    }
}