package Dominio.Personas;

import Dominio.Personas.Datos.Acceso;
import Dominio.Enums.TipoPers;
import Dominio.Zonas.Zona;

import java.util.*;

public class Persona {
    private static int contP = 0; //Cuento todas las personas que fueron creadas
    private  String id;
    private  String nombre;
    private List<Acceso> accesos; //todo List?
    protected TreeSet<Zona> zonasPermitidas; //todo TreeSet? DEFINIR EQUALS ASI NO SE REPITEN. Protected?

    public Persona(String nombre, TipoPers tipo){
        this.id = this.generateCod(tipo);
        this.nombre=nombre;
        accesos = new ArrayList<>(); //todo ArrayList?
        zonasPermitidas= new TreeSet<>(); //todo TreeSet? DEFINIR EQUALS ASI NO SE REPITEN
        contP++;
    }

    private String generateCod(TipoPers t){
        return t.trunc() + "-" + String.format("%04d", contP); //Genero un Id unico con inforamcion de tipo
    }

    public String getId(){return id;}

    public String getNombre(){return nombre;}

    public List<Acceso> getAccesos(){return  accesos;}

    public void addZona(Zona z){zonasPermitidas.add(z);}

    public boolean puedeAcceder(Zona z){
        return true; // todo se redefine despues
    }

    public String toString(){
        return ("ID: " + this.id + ", Nombre: " + this.nombre);
    }

    public void mostrar(){
        System.out.println(this.toString());
    }
}
