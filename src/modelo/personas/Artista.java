package modelo.personas;

import modelo.zonas.Zona;

public class Artista extends Persona {
    public Artista(String id, String nombre) {
        super(id, nombre);
    }

    @Override
    public boolean puedeAcceder(Zona z) {
        return zonasPermitidas.contains(z);
    }
}