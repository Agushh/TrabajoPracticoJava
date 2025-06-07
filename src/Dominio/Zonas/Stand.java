package Dominio.Zonas;

import Dominio.Personas.Comerciante;
import Dominio.Enums.TipoZona;

import java.util.*;

public class Stand extends ZonaRestringida{
    private String ubicacion;
    private String responsable;
    private List<String> empleados= new ArrayList<>();

    public Stand(String descripcion, int capacidadMaxima, String ubicacion, String responsable) {
        super(descripcion, capacidadMaxima, TipoZona.STAND);
        this.ubicacion = ubicacion;
        this.responsable = responsable;
    }

  public void agregarEmpleado(String nombre){empleados.add(nombre);}
  public String getUbicacion(){return ubicacion;}
    public String getResponsable() { return responsable; }
    public List<String> getEmpleados() { return empleados; }



}
