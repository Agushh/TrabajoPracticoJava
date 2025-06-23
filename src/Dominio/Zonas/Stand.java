package Dominio.Zonas;

import Dominio.Personas.Comerciante;

import java.util.*;

/**
 * Stand es un tipo de Zona la cual es una zona restringida que tiene un comerciante responsable, una ubicacion y una lista de empleados.
 * @see Zona
 * @see Comerciante
 */
public class Stand extends ZonaRestringida{
    /**
     * Ubicacion del stand.
     */
    private Zona ubicacion;
    /**
     * Comerciante responsable.
     */
    private Comerciante responsable;
    /**
     * Lista de empleados.
     */
    private List<Comerciante> empleados= new ArrayList<>(); // todo CONTAINER


    /**
     * Constructor.
     * @param id Id del stand.
     * @param descripcion Descripcion del stand.
     * @param capacidadMaxima Capacidad maxima del stand.
     * @param ubicacion Zona en la que se encuentra el stand.
     * @param responsable Comerciante responsable del stand.
     */
    public Stand(String id, String descripcion, int capacidadMaxima, Zona ubicacion, Comerciante responsable) {
        super(id, descripcion, capacidadMaxima);
        this.ubicacion = ubicacion;
        this.responsable = responsable;
    }

    /**
     * Constructor (Necesario para Jackson).
     */
    public Stand() {}


    /**
     * Retorna la ubicacion del stand.
     * @return Ubicacion del stand.
     */
    public Zona getUbicacion() {return ubicacion;}

    /**
     * Setea la ubicacion del stand.
     * @param ubicacion Zona en la que se encuentra el stand.
     */
    public void setUbicacion(Zona ubicacion) {this.ubicacion = ubicacion;}

    /**
     * Retorna el comerciante responsable del stand.
     * @return Comerciante responsable.
     */
    public Comerciante getResponsable() { return responsable; }

    /**
     * Setea el comerciante responsable del stand.
     * @param responsable Comerciante responsable.
     */
    public void setResponsable(Comerciante responsable){this.responsable = responsable;}

    /**
     * Retorna la lista de empleados.
     * @return Lista de empleados.
     */
    public List<Comerciante> getEmpleados() { return empleados; }

    /**
     * Setea la lista de empleados (Necesario para Jackson).
     * @param empleados Lista de empleados.
     */
    public void setEmpleados(List<Comerciante> empleados) {this.empleados = empleados;}

    /**
     * Agrega un comerciante a la lista de empleados.
     * @param emp Comerciante.
     */
    public void agregarEmpleado(Comerciante emp){empleados.add(emp);}

    /**
     * Retorna informacion en String con forma de html.
     * @return Informacion con tags de html.
     */
    @Override
    public String toHTML() {
        return "<html>" + super.toHTML().replace("<html>", "") +"<br>" + "Capacidad: " + getCapacidad()+"<br>" +"Comerciante responsable: "+getResponsable() + "<br>" + "Ubicacion: " + getUbicacion() +"</html>";
    }

    /**
     * Retorna informacion en String.
     * @return Informacion en String.
     */
    @Override
    public String toString() {
        return "Capacidad: " + getCapacidad() + "  Comerciante responsable: "+getResponsable()  + "  Ubicacion: " + getUbicacion();
    }
}
