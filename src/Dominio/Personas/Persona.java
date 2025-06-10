package Dominio.Personas;

import Dominio.Enums.EstadoAcceso;
import Dominio.Enums.TipoZona;
import Dominio.Personas.Datos.Acceso;
import Dominio.Enums.TipoPers;
import Dominio.Zonas.Zona;

import java.rmi.AccessException;
import java.util.*;

public abstract class Persona implements Comparable {
    private static int contP = 0; //Cuento todas las personas que fueron creadas
    private  String id;
    private  String nombre;
    private final List<Acceso> accesos = new ArrayList<>(); //todo List?
    private TreeSet<Zona> zonasPermitidas; //todo TreeSet? DEFINIR EQUALS ASI NO SE REPITEN. Protected?
    private Zona zonaActual;


    public Persona(String nombre, TipoPers tipo, Zona zonaActual){
        id = tipo.trunc() + "-" + String.format("%04d", contP++); /*genera id unico*/ /// suprimi la funcion generateCod, y lo incorpore en linea.
        this.nombre=nombre;
        zonasPermitidas= new TreeSet<>(); //todo TreeSet? DEFINIR EQUALS ASI NO SE REPITEN
        this.zonaActual = zonaActual;
    }

    public String getId(){return id;}

    public String getNombre(){return nombre;}

    public List<Acceso> getAccesos(){return  accesos;}

    public Acceso getUltimoAccesoAceptado() {
        int index = accesos.size() -1;
        Acceso temp = accesos.get( index);
        while(temp.getEstado() == EstadoAcceso.DENEGADO) {
            temp = accesos.get(index--);
        }
        return temp;
    }

    public TreeSet<Zona> getZonasPermitidas() {
        return zonasPermitidas;
    }

    public Zona getZonaActual() {
        return zonaActual;
    }

    public void setZonaActual(Zona zonaActual) {
        this.zonaActual = zonaActual;
    }

    public void addZona(Zona z){zonasPermitidas.add(z);}

    public void addAcceso(Acceso a) {accesos.add(a);}

    public abstract boolean puedeAcceder(Zona z);

    public String toString(){return ("ID: " + this.id + ", Nombre: " + this.nombre);}

    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;
        if(obj instanceof Persona p) return id.equals(p.id);
        else return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    ///El ToString() cumple la misma funcion
    public void mostrar(){
        System.out.println(this.toString());
    }


    //todo
    @Override
    public int compareTo(Object o) {
        if(this == o) return 0;
        return id.compareTo(((Persona) o).id);
    }
}
