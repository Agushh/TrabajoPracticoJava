package Dominio.Personas;

import Dominio.Zonas.Zona;


public class Staff extends Persona {

    public Staff(String nombre, String id) {
        super(nombre, id);
    }

    public Staff() {
    }

    @Override
        public boolean puedeAcceder(Zona z) {
            return true; // acceso total
        }
}


