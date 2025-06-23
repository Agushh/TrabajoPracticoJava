package Dominio.Personas;

import Dominio.Zonas.Stand;
import Dominio.Zonas.Zona;

/**
 * Clase Staff. Hereda de Persona.
 * La persona que sea staff tiene acceso a cualquier zona del festival.
 * @see Stand
 */
public class Staff extends Persona {
    /**
     * Constructor de Asistente. Llama al constructor del padre (Persona)
     * @param nombre Nombre de la persona
     * @param id Id de la persona
     */
    public Staff(String nombre, String id) {
        super(nombre, id);
    }

    /**
     * Constructor generico (Para Jackson)
     */
    public Staff() {}

    /**
     * Se hace override a la funcion puedeAcceder de persona para implementar los permisos del Staff.
     * @param z Zona a consultar si la persona puede o no moverse a ella.
     * @return Siempre devolvera verdadero.
     */
    @Override
        public boolean puedeAcceder(Zona z) {
            return true;
        }
}


