package Dominio.Personas;

import Dominio.Zonas.Escenario;
import Dominio.Zonas.Zona;
import Dominio.Zonas.ZonaComun;


/**
 * Clase Artista. Hereda de Persona.
 * Se trata de los espectadores/personas que asisten al festival.
 *
 */
public class Asistente extends Persona{

    /**
     * Constructor de Asistente. Llama al constructor del padre (Persona)
     * @param nombre Nombre de la persona
     * @param id Id de la persona
     */
    public Asistente(String nombre, String id) {
        super(nombre, id);
    }

    /**
     * Constructor generico (Para Jackson)
     */
    public Asistente() {
    }

    /**
     * Se hace override a la funcion puedeAcceder de persona para implementar los permisos del Asistente.
     * @param z Zona a consultar si la persona puede o no moverse a ella.
     * @return Va a devolver verdadero siempre y cuando la zona sea Comun o la zona este en su TreeSet de zonas permitidas.
     */
    @Override
    public boolean puedeAcceder(Zona z) {
        return z instanceof ZonaComun || z instanceof Escenario;
    }

}
