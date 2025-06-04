package modelo.personas;

import modelo.acceso.Acceso;
import modelo.zonas.Zona;

import java.util.*;

public class Persona {
protected  String id;
protected  String nombre;
protected List<Acceso> accesos = new ArrayList<>();
protected List<Zona> zonasPermitidas= new ArrayList<>();

public Persona(String id, String nombre){
    this.id=id;
    this.nombre=nombre;
}

public String getId(){return id;}
public String getNombre(){return nombre;}
public List<Acceso> getAccesos(){return  accesos;}
public void agregarZonaPermitira(Zona z){zonasPermitidas.add(z);}
public boolean puedeAcceder(Zona z){
    return true; // se referine despues
}


}
