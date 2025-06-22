package Dominio.Personas;

import Dominio.Zonas.Escenario;
import Dominio.Zonas.Zona;
import Dominio.Zonas.ZonaComun;


public class Asistente extends Persona{

    public Asistente(String nombre, String id) {
        super(nombre, id);
    }

    public Asistente() {
    }

    @Override
    public boolean puedeAcceder(Zona z) {
        return z instanceof ZonaComun || z instanceof Escenario;
    }

}
