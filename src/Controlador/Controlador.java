package Controlador;

import Controlador.Logica.MovimientosLogica;
import Dominio.Personas.*;
import Dominio.Zonas.Zona;
import Dominio.Zonas.Stand;
import Inicializador.Serialization;

import java.lang.reflect.Array;
import java.util.*;

public class Controlador {

    private final TreeMap<String, Zona> zonas= new TreeMap<String, Zona>();
    private final TreeMap<String, Persona> personas= new TreeMap<String, Persona>();
    private final TreeMap<String, Stand> stands = new TreeMap<String, Stand>();

    //Aplico patron de diseño Singleton (Te permite generar una sola instancia del objeto)
    private static final Controlador controlador = new Controlador();

    //private final PersonaLogica personaLogica = PersonaLogica.getInstancia(this.personas);
    //private final ZonaLogica zonaLogica = ZonaLogica.getInstancia(this.zonas);

    //Constructor privado para la aplicacion de Singleton
    private Controlador(){
        //todo comprobar existencia de archivos.
        cargaDeDatos();
    }

    //Get para obtener la unica instancia que existe
    public static Controlador getControlador() {return controlador;}

    public boolean mover(Persona persona, Zona zonaDestino) {
        return MovimientosLogica.moverPersona(persona, zonaDestino);
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

    public void cargaDeDatos() {
        ArrayList<Persona> listPersonas = Serialization.leePersonas();
        ArrayList<Zona> listZonas = Serialization.leeZonas();
        Map<String, Zona> zonasPorId = new HashMap<>();
        for (Zona z : listZonas) {
            zonasPorId.put(z.getId(), z);
        }

        Map<String, Persona> personasPorId = new HashMap<>();
        for (Persona p : listPersonas) {
            personasPorId.put(p.getId(), p);
        }

        // Corrige zonas dentro de personas
        for (Persona persona : listPersonas) {
            // zonaActual
            if (persona.getZonaActual() != null) {
                Zona zonaReal = zonasPorId.get(persona.getZonaActual().getId());
                if (zonaReal != null) {
                    persona.setZonaActual(zonaReal);
                }
            }
            if(persona.getZonasPermitidas() != null)
            {
                TreeSet<Zona> zonasPermitidas = new TreeSet<>();
                for(Zona z : persona.getZonasPermitidas()){
                    Zona temp = zonasPorId.get(z.getId());
                    if(temp != null)
                        zonasPermitidas.add(temp);
                }
                persona.setZonasPermitidas(zonasPermitidas);
            }
            personas.put(persona.getId(), persona);
        }

        for(Zona zona : listZonas)
        {
            if(zona instanceof Stand stand)
            {
                Zona zonaReal = zonasPorId.get(stand.getUbicacion().getId());
                if(zonaReal != null) stand.setUbicacion(zonaReal);

                Persona comercianteResponsable = personasPorId.get(stand.getResponsable().getId()); //Se guarda en tipo persona ya que lee de Un Map de personas, y luego se verifica que sea un comerciante mediante InstaceOf
                if(comercianteResponsable instanceof Comerciante c) stand.setResponsable(c);
                stands.put(stand.getResponsable().getNombre(), stand);
            }
            zonas.put(zona.getId(), zona);
        }
    }
}