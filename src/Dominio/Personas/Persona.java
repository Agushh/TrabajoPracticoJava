package Dominio.Personas;

import Dominio.Enums.EstadoAcceso;
import Dominio.Personas.Datos.Acceso;
import Dominio.Zonas.Zona;
import com.fasterxml.jackson.annotation.*;

import java.time.LocalDateTime;
import java.util.*;


/**
 * Clase abstracta Padre a todas las clases de personas.
 * Cada persona puede ser Artista, Asistente, Comerciante y Staff
 * Cada persona tiene id(referencia unica) nombre, lista de accesos (registro de movimientos), una lista de zonas permitidas donde se puede mover y una zona actual en la que se encuentra.
 *
 * @see Acceso
 * @see Zona
 *
 */

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,               // cómo identificar el tipo (por nombre)
        include = JsonTypeInfo.As.PROPERTY,      // dónde incluirlo (como propiedad)
        property = "type"                        // nombre del campo que indica el tipo
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Artista.class, name = "artista"),
        @JsonSubTypes.Type(value = Asistente.class, name = "asistente"),
        @JsonSubTypes.Type(value = Comerciante.class, name = "comerciante"),
        @JsonSubTypes.Type(value = Staff.class, name = "staff")
})

public abstract class Persona{
    /**
     * Id de la persona. Clave Primaria unica, identificador de persona. Se utiliza en la Serializacion y deserializacion de XML mediante libreria Jackson.
     */
    private String id;

    /**
     * Nombre de la persona.
     */
    private String nombre;
    /**
     * Lista de accesos de la persona. Guarda una trazabilidad de las zonas en las que estuvo, el tiempo de permanencia y si fue un acceso denegado o permitido.
     */
    private List<Acceso> accesos = new ArrayList<>(); //todo Container
    /**
     * TreeSet de Zonas permitidas a las cuales una persona puede moverse. Se utiliza treeSet para una busqueda de zona mas eficaz sobre el Container.
     */
    private TreeSet<Zona> zonasPermitidas = new TreeSet<>(); //todo Container
    /**
     * Zona actual de la persona. El lugar donde esta.
     */
    private Zona zonaActual;

    /**
     * Constructor de personas con nombre y Id.
     * @param nombre Nombre de la persona
     * @param id Id de la persona (Clave primaria, unica)
     */
    public Persona(String nombre, String id){
        this.id = id;
        this.nombre=nombre;
    }

    /**
     * Constructor sin parametros (Para Jackson)
     */
    public Persona(){}

    /**
     * devuelve Id de la persona.
     * @return Id de la persona.
     */
    public String getId(){return id;}

    /**
     * Asigna el Id de la persona.
     * @param id Id de la persona.
     */
    public void setId(String id){this.id = id;}

    /**
     * Devuelve Nombre de la persona.
     * @return nombre de la persona.
     */
    public String getNombre(){return nombre;}

    /**
     * Asigna el nombre de la persona.
     * @param nombre Nombre de la persona.
     */
    public void setNombre(String nombre){this.nombre = nombre;}

    /**
     * Devuelve la lista de accesos de la persona.
     * @return Lista de accesos de la persona.
     */
    public List<Acceso> getAccesos(){return  accesos;} //todo Container

    /**
     * Asigna la lista de accesos de la persona.
     * @param accesos Lista de accesos de la persona.
     */
    public void setAccesos(List<Acceso> accesos){this.accesos = accesos;}

    /**
     * Devuelve el TreeSet de zonas por las cuales puede moverse la persona.
     * @return TreeSet de tipo Zona.
     */
    public TreeSet<Zona> getZonasPermitidas() {
        return zonasPermitidas;
    } //todo Container

    /**
     * Asigna el TreeSet de zonas por las cuales puede moverse la persona.
     * @param zonasPermitidas TreeSet de tipo Zona.
     */
    public void setZonasPermitidas(TreeSet<Zona> zonasPermitidas) { this.zonasPermitidas = zonasPermitidas;}

    /**
     * Devuelve la Zona actual de la persona.
     * @return Zona actual de la persona.
     */
    public Zona getZonaActual() {
        return zonaActual;
    }

    /**
     * Asigna la Zona Actual de la persona.
     * @param zonaActual Zona actual de la persona.
     */
    public void setZonaActual(Zona zonaActual) {
        this.zonaActual = zonaActual;
    }

    /**
     * Metodo que permite identificar el último acceso que tuvo una persona para poder seguir la trazabilidad de la persona.
     * Se utiliza a la hora de mover una persona para calcular cuanto tiempo estuvo en su zona actual y de esta forma guardarlo en la lista.
     *
     * @return Retorna el ultimo acceso permitido, o retorna un acceso generico con el tiempo actual si la persona no tiene accesos permitidos.
     */
    @JsonIgnore
    public Acceso getUltimoAccesoAceptado() {
        int index = accesos.size() -1;
        Acceso temp = accesos.get(index);
        while(index >= 0 && temp.getEstado() == EstadoAcceso.DENEGADO) {
            temp = accesos.get(index--);
        }
        if(index < 0) return new Acceso(null, LocalDateTime.now(), 0, null);
        return temp;
    }

    /**
     * Añade la zona pasada como parametro a la lista de zonas permitidas en las cuales se puede mover una persona.
     * @param z Nueva zona a la cual una persona puede moverse.
     */
    public void addZona(Zona z){zonasPermitidas.add(z);}

    /**
     * Añade un acceso a la lista de accesos que tuvo una persona.
     * @param a Acceso a añadir a la lista.
     */
    public void addAcceso(Acceso a) {accesos.add(a);}


    /**
     * Metodo Abstracto el cual se redefine en sus clases heredadas.
     * Este metodo retorna un booleano segun si una persona tiene permiso para acceder a la zona Z ingresada como parametro o no.
     * @param z Zona a consultar si la persona puede o no moverse a ella.
     * @return Verdadero o falso según su implementacion en subclases.
     */
    public abstract boolean puedeAcceder(Zona z);

    /**
     * ToString de Persona.
     * @return Retorna el nombre y el id en formato String.
     */
    @Override
    public String toString(){return (this.nombre+ "  ID:  "+this.id);}

    /**
     * Metodo equals de persona.
     * @param obj Persona a comparar si es igual a la persona.
     * @return Retorna verdadero si el parametro obj es persona y tiene el mismo ID (identificacion unica por persona).
     */
    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;
        if(obj instanceof Persona p) return id.equals(p.id);
        else return false;
    }

    /**
     * Metodo HashCode de Persona.
     * @return retorna una variable int basada en el ID. Mantiene concordancia con el metodo Equals.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
