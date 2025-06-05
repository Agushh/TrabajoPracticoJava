package modelo.personas;

import modelo.enums.TipoPers;
import modelo.zonas.Zona;


public class Staff extends Persona {
        public Staff(String nombre) {
            super(nombre, TipoPers.STAFF);
        }

        @Override
        public boolean puedeAcceder(Zona z) {
            return true; // acceso total
        }
}


