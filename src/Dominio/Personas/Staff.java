package Dominio.Personas;

import Dominio.Enums.TipoPers;
import Dominio.Zonas.Zona;


public class Staff extends Persona {
        public Staff(String nombre, Zona zonaActual) {
            super(nombre, TipoPers.STAFF, zonaActual);
        }

        @Override
        public boolean puedeAcceder(Zona z) {
            return true; // acceso total
        }
}


