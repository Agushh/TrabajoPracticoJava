package modelo;

import modelo.personas.Persona;
import modelo.zonas.Zona;
import modelo.zonas.Stand;
import java.util.TreeSet;

public class Controlador {

    private static TreeSet<Zona> zonas;
    private static TreeSet<Persona> personas;
    private static TreeSet<Stand> stands;

    //Aplico patron de diseño Singleton (Te permite generar una sola instancia del objeto)
    private static final Controlador controlador = new Controlador();

    //Constructor privado para la aplicacion de Singleton
    private Controlador(){
        //Serializacion???
        zonas = new TreeSet<Zona>();
        personas = new TreeSet<Persona>();
        stands = new TreeSet<Stand>();
    }

    //Get para obtener la unica instancia que existe
    public static Controlador getControllador() {return controlador;}
}
