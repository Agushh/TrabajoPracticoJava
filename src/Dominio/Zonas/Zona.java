package Dominio.Zonas;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.Objects;


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
    private String id; // todo PROTECTED? ///Private, acceder con GetCod
    private String descripcion;
    private int concurrencia; ///cantidad de personas. Se utiliza en todas las zonas.

    public Zona(String id, String descripcion, int concurrencia){
        this.id = id;
        this.descripcion=descripcion;
        this.concurrencia = concurrencia;
    }

    public Zona(){}

    public String getId() {return id;}
    public void setId(String id) {this.id = id;}

    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {this.descripcion = descripcion; }

    public int getConcurrencia()
    {
        return concurrencia;
    }
    public void setConcurrencia(int concurrencia) {this.concurrencia = concurrencia; }

    public void ponePersona()
    {
        concurrencia ++;
    }
    public void sacaPersona()
    {
        concurrencia --;
    }

    public  String toString(){
        return   "Cod:  " + id + " --   Desc:   " + descripcion;
    }

    public  String toHTML(){
        return   "<html>" + "Cod:  " + id + " --   Desc:   " + descripcion+"<br>"+"Concurrencia actual : "+getConcurrencia()+"</html>";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        else if (o == this) return true;
        else return id.equals(((Zona) o).id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /// Funcion para el TreeSet de Zonas en Persona. Se usa para ordenar los elementos de forma automatica.
    @Override
    public int compareTo(Zona o) {
        if(o == this) return 0;
        return id.compareTo(o.id);
    }
}
