package modelo.personas;

import modelo.zonas.Zona;

public class Artista extends Persona {

    public Artista(String nombre) {
        super(nombre,'A'); // todo CAMBIAR LETRA POR ENUM???
    }

    @Override
    public boolean puedeAcceder(Zona z) {
        return zonasPermitidas.contains(z);
    }
}