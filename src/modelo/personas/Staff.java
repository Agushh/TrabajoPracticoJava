package modelo.personas;

import modelo.zonas.Zona;


public class Staff extends Persona {
        public Staff(String id, String nombre) {
            super(id, nombre);
        }

        @Override
        public boolean puedeAcceder(Zona z) {
            return true; // acceso total
        }
}


