package Controlador;

import Dominio.Enums.TipoPers;
import Dominio.Enums.TipoZona;
import Dominio.Factory.PersonaFactory;
import Dominio.Personas.*;
import Dominio.Zonas.Zona;
import Dominio.Zonas.Stand;
import Dominio.Factory.ZonaFactory;

import java.util.TreeMap;

public class Controlador {

    private static TreeMap<String, Zona> zonas;
    private static TreeMap<String, Persona> personas;
    private static TreeMap<String, Stand> stands;

    //Aplico patron de diseño Singleton (Te permite generar una sola instancia del objeto)
    private static final Controlador controlador = new Controlador();

    //Constructor privado para la aplicacion de Singleton
    private Controlador(){
        //Serializacion???
        zonas = new TreeMap<String, Zona>();
        personas = new TreeMap<String, Persona>();
        stands = new TreeMap<String, Stand>();
    }

    //Get para obtener la unica instancia que existe
    public static Controlador getControllador() {return controlador;}

    //Agrego persona al Map
    public void addPersona(TipoPers tipo, String nombre) throws IllegalArgumentException {
        //Uso el patron de diseño Factory para mayor claridad
        try{
            Persona p = PersonaFactory.crear(tipo, nombre);
            personas.put(p.getId(),p);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException();// todo verificar excepcion y agregar mensaje
        }
    }

    //Agrego zona al Map
    public void addZona(TipoZona tipo, String descripcion) throws IllegalArgumentException {
        //Uso el patron de diseño Factory para mayor claridad
        try{
            Zona z = ZonaFactory.crear(tipo, descripcion);
            zonas.put(z.getCod(),z);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException();// todo verificar excepcion y agregar mensaje
        }
    }

    //Agrego una zona permitida a una persona con su codigo
    public void addZonaPermitida(String persCod,Zona zona) throws IllegalArgumentException { //todo tener en cuenta escenario de artista (reescribir el metodo de addZona)
        try{
            Persona p = personas.get(persCod);
            p.addZona(zona);
            personas.put(p.getId(),p);
        }catch (Exception e){
            throw new IllegalArgumentException("Codigo de persona no encontrado: " + persCod);
        }
    }

    public void mostrarPersonas(){
        personas.forEach((id,persona)->{
            System.out.println(persona.toString());
        });
    }
}
