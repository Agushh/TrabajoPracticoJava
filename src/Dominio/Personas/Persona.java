package Dominio.Personas;

import Dominio.Enums.EstadoAcceso;
import Dominio.Personas.Datos.Acceso;
import Dominio.Zonas.Zona;
import com.fasterxml.jackson.annotation.*;

import java.time.LocalDateTime;
import java.util.*;


@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,               // cómo identificar el tipo (por nombre)
        include = JsonTypeInfo.As.PROPERTY,     // dónde incluirlo (como propiedad)
        property = "type"                        // nombre del campo que indica el tipo
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Artista.class, name = "artista"),
        @JsonSubTypes.Type(value = Asistente.class, name = "asistente"),
        @JsonSubTypes.Type(value = Comerciante.class, name = "comerciante"),
        @JsonSubTypes.Type(value = Staff.class, name = "staff")
})


public abstract class Persona{
    private String id;
    private String nombre;
    private List<Acceso> accesos = new ArrayList<>();
    private TreeSet<Zona> zonasPermitidas = new TreeSet<>();
    private Zona zonaActual;


    public Persona(String nombre, String id){
        this.id = id;
        this.nombre=nombre;
    }

    public Persona(){}

    public String getId(){return id;}
    public void setId(String id){this.id = id;}

    public String getNombre(){return nombre;}
    public void setNombre(String nombre){this.nombre = nombre;}

    public List<Acceso> getAccesos(){return  accesos;}
    public void setAccesos(List<Acceso> accesos){this.accesos = accesos;}

    public TreeSet<Zona> getZonasPermitidas() {
        return zonasPermitidas;
    }
    public void setZonasPermitidas(TreeSet<Zona> zonasPermitidas) { this.zonasPermitidas = zonasPermitidas;}

    public Zona getZonaActual() {
        return zonaActual;
    }
    public void setZonaActual(Zona zonaActual) {
        this.zonaActual = zonaActual;
    }

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

    public void addZona(Zona z){zonasPermitidas.add(z);}

    public void addAcceso(Acceso a) {accesos.add(a);}

    public abstract boolean puedeAcceder(Zona z);

    public String toString(){return (this.nombre+ "  ID:  "+this.id);} //por que sale con corchetes y una coma?

    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;
        if(obj instanceof Persona p) return id.equals(p.id);
        else return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
