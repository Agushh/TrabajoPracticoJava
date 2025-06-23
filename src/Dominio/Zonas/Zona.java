package Dominio.Zonas;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.Objects;


/**
 * Zona es una clase abstracta la cual tiene un Id unico, Descripcion, y la Concurrencia de la zona.
 */
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,               // cómo identificar el tipo (por nombre)
        include = JsonTypeInfo.As.PROPERTY,     // dónde incluirlo (como propiedad)
        property = "type"                        // nombre del campo que indica el tipo
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Escenario.class, name = "escenario"),
        @JsonSubTypes.Type(value = Stand.class, name = "stand"),
        @JsonSubTypes.Type(value = ZonaComun.class, name = "zona Comun"),
        @JsonSubTypes.Type(value = ZonaRestringida.class, name = "zona Restringida")
})


public abstract class Zona implements Comparable<Zona>{
    /**
     * Id de la zona.
     */
    private String id;

    /**
     * Descripcion de la zona.
     */
    private String descripcion;

    /**
     * Concurrencia de la zona.
     */
    private int concurrencia; ///cantidad de personas. Se utiliza en todas las zonas.

    /**
     * Constructor.
     * @param id Id de la zona.
     * @param descripcion Descripcion de la zona.
     */
    public Zona(String id, String descripcion){
        this.id = id;
        this.descripcion=descripcion;
        this.concurrencia = 0;
    }

    /**
     * Constructor (Necesario para Jackson).
     */
    public Zona(){}

    /**
     * Retorna el Id de la zona
     * @return Id de la zona.
     */
    public String getId() {return id;}

    /**
     * Setea el id de la zona.
     * @param id Id de la zona.
     */
    public void setId(String id) {this.id = id;}

    /**
     * Retorna la descripcion de la zona.
     * @return La descripcion de la zona.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Setea la descripcion de la zona.
     * @param descripcion La descripcion de la zona.
     */
    public void setDescripcion(String descripcion) {this.descripcion = descripcion; }

    /**
     * Retorna la concurrencia de la zona.
     * @return La concurrecia de la zona.
     */
    public int getConcurrencia()
    {
        return concurrencia;
    }

    /**
     * Setea la concurrencia de la zona.
     * @param concurrencia Concurrencia de la zona.
     */
    public void setConcurrencia(int concurrencia) {this.concurrencia = concurrencia; }


    /**
     * Agrega una persona a la zona.
     */
    public void ponePersona()
    {
        concurrencia ++;
    }

    /**
     * Saca una persona de la zona.
     */
    public void sacaPersona()
    {
        concurrencia --;
    }

    /**
     * Retorna informacion de las zona.
     * @return Info en forma de String.
     */
    @Override
    public  String toString(){
        return   "Cod:  " + id + " --   Desc:   " + descripcion;
    }

    /**
     * Retorna informacion de las zona en forma de string con etiquetas html.
     * @return Info en forma de String con etiquetas html.
     */
    public  String toHTML(){
        return   "<html>" + "Cod:  " + id + " --   Desc:   " + descripcion+"<br>"+"Concurrencia actual : "+getConcurrencia()+"</html>";
    }

    /**
     * Verifica que 2 zonas sean iguales.
     * para
     * @return Si una zona es igual o no.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        else if (o == this) return true;
        else return id.equals(((Zona) o).id);
    }

    /**
     * Genera un hashcode a partir del id.
     * @return Retorna el hashcode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * Compara zonas para poder ordenarlas en el TreeSet.
     * @param o La zona a ser comparada.
     * @return valor entre -1 y 1.
     */
    @Override
    public int compareTo(Zona o) {
        if(o == this) return 0;
        return id.compareTo(o.id);
    }
}
