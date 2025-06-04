package modelo.personas;

import modelo.zonas.Zona;

public class Comerciante extends Persona {
    public Comerciante(String id, String nombre) {
        super(id, nombre);
    }

    @Override
    public boolean puedeAcceder(Zona z) {
        return zonasPermitidas.contains(z);
    }
}