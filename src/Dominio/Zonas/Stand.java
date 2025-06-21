package Dominio.Zonas;

import Dominio.Personas.Comerciante;
import Dominio.Enums.TipoZona;

import java.util.*;

public class Stand extends ZonaRestringida{
    private Zona ubicacion; //se cambio de string a comerciante
    private Comerciante responsable; //se cambio de string a comerciante
    private List<Comerciante> empleados= new ArrayList<>();// todo Cambiar de String a Comerciante

    public Stand(String descripcion, int capacidadMaxima, Zona ubicacion, Comerciante responsable) {
        super(descripcion, capacidadMaxima, TipoZona.STAND);
        this.ubicacion = ubicacion;
        this.responsable = responsable;
    }

    @Override
    public String toStringCompleto() {
        return "<html>" + super.toStringCompleto().replace("<html>", "").replace("</html>", "") +"<br>" + "Capacidad: " + getCapacidad()+"<br>" +"Comerciante responsable: "+getResponsable()+"</html>";
    }

    public void agregarEmpleado(Comerciante id){empleados.add(id);} //debe ser lista de empleados no de nombres
    public Zona getUbicacion(){return ubicacion;}
    public Comerciante getResponsable() { return responsable; }
    public List<Comerciante> getEmpleados() { return empleados; }

}
