package Dominio.Zonas;

import Dominio.Personas.Comerciante;
import Dominio.Personas.Persona;

import java.util.*;

public class Stand extends ZonaRestringida{
    private Zona ubicacion;
    private Persona responsable;
    private List<Comerciante> empleados= new ArrayList<>();

    public Stand(String id, String descripcion, int concurrencia, int capacidadMaxima, Zona ubicacion, Persona responsable, List<Comerciante> empleados) {
        super(id, descripcion, concurrencia, capacidadMaxima);
        this.ubicacion = ubicacion;
        this.responsable = responsable;
        this.empleados = empleados;
    }

    public Stand() {}

    public Zona getUbicacion() {return ubicacion;}
    public void setUbicacion(Zona ubicacion) {this.ubicacion = ubicacion;}

    public Persona getResponsable() { return responsable; }
    public void setResponsable(Comerciante responsable){this.responsable = responsable;}

    public List<Comerciante> getEmpleados() { return empleados; }
    public void setEmpleados(List<Comerciante> empleados) {this.empleados = empleados;}

    public void agregarEmpleado(Comerciante id){empleados.add(id);}

    @Override
    public String toStringCompleto() {
        return "<html>" + super.toStringCompleto().replace("<html>", "").replace("</html>", "") +"<br>" + "Capacidad: " + getCapacidad()+"<br>" +"Comerciante responsable: "+getResponsable()+"</html>";
    }


}
