package Dominio.Zonas;

import Dominio.Enums.TipoZona;

import java.util.Objects;

public abstract class Zona implements Comparable{
    private String id; // todo PROTECTED? ///Private, acceder con GetCod
    private String descripcion;
    private int concurrencia; ///cantidad de personas. Se utiliza en todas las zonas.

    public Zona(String id, String descripcion, int concurrencia){
        this.id =
        this.descripcion=descripcion;
        this.concurrencia = 0;
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

    @Override
    public String toString() {
        return   "<html>" + "Cod:  " + id + " --   Desc:   " + descripcion + "</html>" ;
                //+"<br>"+"Concurrencia actual : "+getConcurrencia()+"</html>";
    }
    public  String toStringCompleto(){
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
    public int compareTo(Object o) {
        if(o == this) return 0;
        return id.compareTo(((Zona) o).id);
    }
}
