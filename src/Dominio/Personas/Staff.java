package Dominio.Personas;

import Dominio.Enums.TipoPers;
import Dominio.Personas.Datos.Acceso;
import Dominio.Zonas.Zona;

import java.util.List;
import java.util.TreeSet;


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


