package Dominio.Personas;

import Dominio.Zonas.Escenario;
import Dominio.Zonas.Zona;
import Dominio.Zonas.ZonaComun;

/**
 * Clase Artista. Hereda de Persona.
 * Cada artista incorpora su referencia a escenario.
 * @see Escenario
 */
public class Artista extends Persona {

    /**
     * Referencia a Escenario (Zona).
     */
    private Escenario escenario;

    /**
     * Constructor de Artista. Llama al constructor del padre (Persona)
     * @param nombre Nombre de la persona
     * @param id Id de la persona
     */
    public Artista(String nombre, String id) {
        super(nombre, id);
    }

    /**
     * Constructor generico (Para Jackson)
     */
    public Artista(){}

    /**
     * Devuelve la referencia al objeto Escenario(Zona)
     * @return Escenario(Zona)
     */
    public Escenario getEscenario() {
        return escenario;
    }

    /**
     * Permite asignar cual es el escenario designado al artista
     * @param escenario Escenario(Zona)
     */
    public void setEscenario(Escenario escenario) {
        this.escenario = escenario;
    }

    /**
     * Se hace override de la funcion AddZona para que el artista solo tenga un escenario designado al cual puede ir.
     * @param z Nueva zona a la cual una persona puede moverse.
     */
    @Override
    public void addZona(Zona z){
        if(z instanceof Escenario)
            this.escenario = (Escenario)z;
        else
            getZonasPermitidas().add(z);

    }

    /**
     * Se hace override a la funcion puedeAcceder de persona para implementar los permisos del artista.
     * @param z Zona a consultar si la persona puede o no moverse a ella.
     * @return Va a devolver true siempre y cuando la zona sea su escenario o la zona este en su TreeSet de zonas permitidas o la zona sea de tipo Zona comun.
     */
    @Override
    public boolean puedeAcceder(Zona z) {
        return z instanceof ZonaComun || z.equals(escenario) || getZonasPermitidas().contains(z);
    }
}