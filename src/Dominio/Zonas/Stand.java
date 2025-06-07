package Dominio.Zonas;

import Dominio.Personas.Comerciante;
import Dominio.Enums.TipoZona;

import java.util.*;

public class Stand extends ZonaRestringida{
    private String ubicacion;
    private Comerciante responsable;
    private List<String> empleados= new ArrayList<>();

    public Stand(String descripcion, int capacidadMaxima, String ubicacion, Comerciante responsable) {
        super(descripcion, capacidadMaxima);
        this.ubicacion = ubicacion;
        this.responsable = responsable;
        this.tipo = TipoZona.STAND;
    }

  public void agregarEmpleado(String nombre){empleados.add(nombre);}
  public String getUbicacion(){return ubicacion;}
    public Comerciante getResponsable() { return responsable; }
    public List<String> getEmpleados() { return empleados; }



}
