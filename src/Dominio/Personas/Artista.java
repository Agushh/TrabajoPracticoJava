package Dominio.Personas;

import Dominio.Enums.TipoPers;
import Dominio.Zonas.Zona;

public class Artista extends Persona {

    public Artista(String nombre) {
        super(nombre, TipoPers.ARTISTA);
    }

    public void addZona(Zona z){
        //todo tengo que verificar si ya existe un escenario, ya que solo puede tener uno
    }

    @Override
    public boolean puedeAcceder(Zona z) {
        return zonasPermitidas.contains(z);
    }
}