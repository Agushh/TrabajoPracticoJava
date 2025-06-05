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

    public void addPersona(String nombre, String tipo) throws IllegalArgumentException {
        //todo PATRON DE DISEÑO BUILDER???????????
        Persona p;

        // Creo persona segun su tipo todo (SEGURO MEJORABLE CON PATRON BUILDER)
        switch(tipo){
            case "staff":
                p = new Staff(nombre);
                break;
            case "comerciante":
                p = new Comerciante(nombre);
                break;
            case "asistente":
                p = new Asistente(nombre);
                break;
            case "artista":
                p = new Artista(nombre);
                break;
            default:
                throw new IllegalArgumentException("Tipo de persona inválido: " + tipo);
        }

        //Meto la persona en el Map con su id como clave
        personas.put(p.getId(),p);
    }

    public void mostrarPersonas(){
        personas.forEach((id,persona)->{
            System.out.println(id + " :" + persona.getNombre());
        });
    }
}
