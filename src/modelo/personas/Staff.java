package modelo.personas;

import modelo.zonas.Zona;


public class Staff extends Persona {
        public Staff(String nombre) {
            super(nombre, 'S');
        }

        @Override
        public boolean puedeAcceder(Zona z) {
            return true; // acceso total
        }
}


