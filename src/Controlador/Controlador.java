package Controlador;

import Controlador.Logica.PersonaLogica;
import Controlador.Logica.ZonaLogica;
import Dominio.Personas.*;
import Dominio.Zonas.Zona;
import Dominio.Zonas.Stand;

import java.util.TreeMap;

public class Controlador {

    private final TreeMap<String, Zona> zonas= new TreeMap<String, Zona>();
    private final TreeMap<String, Persona> personas= new TreeMap<String, Persona>();
    private final TreeMap<String, Stand> stands = new TreeMap<String, Stand>();

    //Aplico patron de diseño Singleton (Te permite generar una sola instancia del objeto)
    private static final Controlador controlador = new Controlador();
    private final PersonaLogica personaLogica = PersonaLogica.getInstancia(this.personas);
    private final ZonaLogica zonaLogica = ZonaLogica.getInstancia(this.zonas);

    //Constructor privado para la aplicacion de Singleton
    private Controlador(){
        // todo Serializacion???
    }

    //Get para obtener la unica instancia que existe
    public static Controlador getControlador() {return controlador;}

    public PersonaLogica persona() {
        return personaLogica;
    }

    public ZonaLogica zona() {
        return zonaLogica;
    }

    public TreeMap<String, Zona> getZonas() {
        return zonas;
    }

    public TreeMap<String, Persona> getPersonas() {
        return personas;
    }

    public TreeMap<String, Stand> getStands() {
        return stands;
    }

}
