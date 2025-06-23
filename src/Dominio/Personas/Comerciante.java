package Dominio.Personas;

import Dominio.Zonas.Stand;
import Dominio.Zonas.Zona;
import Dominio.Zonas.ZonaComun;
/**
 * Clase Comerciante. Hereda de Persona.
 * Cada Comerciante incorpora su referencia al Stand donde trabaja.
 * @see Stand
 */
public class Comerciante extends Persona {

    /**
     * Referencia al stand(Zona) donde el comerciante trabaja.
     */
    private Stand stand;

    /**
     * Constructor de Asistente. Llama al constructor del padre (Persona)
     * @param nombre Nombre de la persona
     * @param id Id de la persona
     */
    public Comerciante(String nombre, String id) {
        super(nombre, id);
    }

    /**
     * Constructor generico (Para Jackson)
     */
    public Comerciante() {
    }

    /**
     * Retorna la referencia al stand donde trabaja.
     * @return Stand(Zona)
     */
    public Stand getStand() {
        return stand;
    }

    /**
     * Permite modificar el stand donde trabaja.
     * @param stand Stand(Zona)
     */
    public void setStand(Stand stand) {
        this.stand = stand;
    }

    /**
     * Se hace override a la funcion puedeAcceder de persona para implementar los permisos del Comerciante.
     * @param z Zona a consultar si la persona puede o no moverse a ella.
     * @return Va a devolver verdadero siempre y cuando la zona sea Comun o la zona este en su TreeSet de zonas permitidas o la zona sea su stand de trabajo.
     */
    @Override
    public boolean puedeAcceder(Zona z) {
        return z instanceof ZonaComun || z.equals(stand) || getZonasPermitidas().contains(z);
    }
}