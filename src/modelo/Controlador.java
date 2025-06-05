package modelo;

import modelo.personas.*;
import modelo.zonas.Zona;
import modelo.zonas.Stand;
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
    public Persona addPersona(String tipo, String nombre) throws IllegalArgumentException {
        //Uso el patron de diseño Factory para mayor claridad
        Persona p = PersonaFactory.crear(tipo, nombre);
        personas.put(p.getId(),p);
        return p; // todo Me sirve retornarlo para poder usar patron Bilder??
    }

    public void addZonaPermitida(String persCod,Zona zona) throws IllegalArgumentException {
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
