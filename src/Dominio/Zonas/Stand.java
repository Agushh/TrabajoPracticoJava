package Dominio.Zonas;

import Dominio.Personas.Comerciante;
import Dominio.Enums.TipoZona;

import java.util.*;

public class Stand extends ZonaRestringida{
    private Zona ubicacion; //se cambio de string a comerciante
    private Comerciante responsable; //se cambio de string a comerciante
    private List<String> empleados= new ArrayList<>();// todo Cambiar de String a Comerciante

    public Stand(String descripcion, int capacidadMaxima, Zona ubicacion, Comerciante responsable) {
        super(descripcion, capacidadMaxima, TipoZona.STAND);
        this.ubicacion = ubicacion;
        this.responsable = responsable;
    }

    @Override
    public String toString() {
        return "<html>" + super.toString().replace("<html>", "").replace("</html>", "") +"<br>" + "Capacidad: " + getCapacidad()+"<br>" +"Comerciante responsable: "+getResponsable()+"</html>";
    }

    public void agregarEmpleado(String nombre){empleados.add(nombre);}
  public Zona getUbicacion(){return ubicacion;}
    public Comerciante getResponsable() { return responsable; }
    public List<String> getEmpleados() { return empleados; }



}
