package modelo.personas;

import modelo.zonas.Zona;

public class Comerciante extends Persona {
    public Comerciante(String nombre) {
        super(nombre, 'C');
    }

    @Override
    public boolean puedeAcceder(Zona z) {
        return zonasPermitidas.contains(z);
    }
}