package modelo.personas;

import modelo.acceso.Acceso;
import modelo.zonas.Zona;

import java.util.*;

public class Persona {
    private static int contP = 0; //Cuento todas las personas que fueron creadas
    private  String id;
    private  String nombre;
    private List<Acceso> accesos; //todo List?
    protected List<Zona> zonasPermitidas; //todo List? Protected?

    public Persona(String nombre, char tipo){
        this.id= tipo + "-" + String.format("%04d", contP); //Genero un Id unico con inforamcion de tipo
        this.nombre=nombre;
        accesos = new ArrayList<>(); //todo ArrayList?
        zonasPermitidas= new ArrayList<>(); //todo ArrayList?
        contP++;
    }

    public String getId(){return id;}

    public String getNombre(){return nombre;}

    public List<Acceso> getAccesos(){return  accesos;}

    public void addZona(Zona z){zonasPermitidas.add(z);}

    public boolean puedeAcceder(Zona z){
        return true; // se referine despues
    }

    public String toString(){
        return ("ID: " + this.id + ", Nombre: " + this.nombre);
    }
}
