package Dominio.Zonas;

import Dominio.Personas.Comerciante;
import Dominio.Personas.Persona;

import java.util.*;

public class Stand extends ZonaRestringida{
    private Zona ubicacion;
    private Comerciante responsable;
    private List<Comerciante> empleados= new ArrayList<>();

    public Stand(String id, String descripcion, int concurrencia, int capacidadMaxima, Zona ubicacion, Comerciante responsable, List<Comerciante> empleados) {
        super(id, descripcion, concurrencia, capacidadMaxima);
        this.ubicacion = ubicacion;
        this.responsable = responsable;
        this.empleados = empleados;
    }

    public Stand() {}

    public Zona getUbicacion() {return ubicacion;}
    public void setUbicacion(Zona ubicacion) {this.ubicacion = ubicacion;}

    public Comerciante getResponsable() { return responsable; }
    public void setResponsable(Comerciante responsable){this.responsable = responsable;}

    public List<Comerciante> getEmpleados() { return empleados; }
    public void setEmpleados(List<Comerciante> empleados) {this.empleados = empleados;}

    public void agregarEmpleado(Comerciante id){empleados.add(id);}

    @Override
    public String toString() {
        return super.toString() + "\n" +
                "Capacidad: " + getCapacidad() + "\n" +
                "Comerciante responsable: " + getResponsable() + "\n" +
                "Ubicacion: " + getUbicacion() + "\n";
    }

    public String toHTML() {
        return "<html>" + super.toHTML().replace("<html>", "") +"<br>" + "Capacidad: " + getCapacidad()+"<br>" +"Comerciante responsable: "+getResponsable() + "<br>" + "Ubicacion: " + getUbicacion() +"</html>";
    }


}
