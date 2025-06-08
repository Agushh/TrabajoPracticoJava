package Dominio.Personas;

import Dominio.Enums.TipoZona;
import Dominio.Personas.Datos.Acceso;
import Dominio.Enums.TipoPers;
import Dominio.Zonas.Zona;

import java.util.*;

public class Persona {
    private static int contP = 0; //Cuento todas las personas que fueron creadas
    private  String id;
    private  String nombre;
    private TipoPers tipo;
    private List<Acceso> accesos; //todo List?
    protected TreeSet<Zona> zonasPermitidas; //todo TreeSet? DEFINIR EQUALS ASI NO SE REPITEN. Protected?

    public Persona(String nombre, TipoPers tipo){
        id = tipo.trunc() + "-" + String.format("%04d", contP++); /*genera id unico*/ /// suprimi la funcion generateCod, y lo incorpore en linea.
        this.tipo = tipo;     ///Añadi tipo para la comprobación de si puede acceder
        this.nombre=nombre;
        accesos = new ArrayList<>(); //todo ArrayList?
        zonasPermitidas= new TreeSet<>(); //todo TreeSet? DEFINIR EQUALS ASI NO SE REPITEN
    }

    public String getId(){return id;}

    public String getNombre(){return nombre;}

    public List<Acceso> getAccesos(){return  accesos;}

    public void addZona(Zona z){zonasPermitidas.add(z);}

    public void addAcceso(Acceso a) {accesos.add(a);}

    public boolean puedeAcceder(Zona z){
        switch (tipo)
        {
            case STAFF -> {
            return true;
            }
            case ASISTENTE -> {
                return z.getTipo() == TipoZona.ZONA_COMUN || z.getTipo() == TipoZona.ESCENARIO;
            }
            case ARTISTA, COMERCIANTE -> {
                if(accesos.contains(new Acceso(z))) return true;
            }
        }
        return false;
    }

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

}
