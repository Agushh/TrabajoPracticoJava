package Controlador;

import Dominio.Enums.EstadoAcceso;
import Dominio.Exceptions.AccesoDenegadoException;
import Dominio.Exceptions.ZonaEsLaActualException;
import Dominio.Exceptions.ZonaLlenaException;
import Dominio.Personas.*;
import Dominio.Personas.Datos.Acceso;
import Dominio.Zonas.Interface.Capado;
import Dominio.Zonas.Zona;
import Dominio.Zonas.Stand;
import Inicializador.Serialization;

import java.lang.reflect.Array;
import java.util.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.TreeMap;

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

    //------------------- Getters ------------------

    public TreeMap<String, Zona> getZonas() {
        return zonas;
    }

    public TreeMap<String, Persona> getPersonas() {
        return personas;
    }

    public TreeMap<String, Stand> getStands() {
        return stands;
    }

    public Zona getZonas(String codZona) throws IllegalArgumentException {
        Zona z = zonas.get(codZona);
        if (z == null) {
            throw new IllegalArgumentException("No existe una zona con código: " + codZona);
        }
        return z;
    }

    public Persona getPersona(String codPersona) throws IllegalArgumentException {
        Persona p = personas.get(codPersona);
        if (p == null) {
            throw new IllegalArgumentException("No existe una persona con código: " + codPersona);
        }
        return p;
    }

    //------------------- Adds -----------------------

    public void addZona(Zona z) throws IllegalArgumentException {
        try{
            zonas.put(z.getId(),z);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException();
        }
    }

    public void addPersona(Persona p) throws IllegalArgumentException {
        try{
            personas.put(p.getId(),p);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException();// todo verificar excepcion y agregar mensaje
        }
    }

    //--------------------- Mover Persona ------------------

    public static void moverPersona(Persona persona, Zona zonaDestino) throws AccesoDenegadoException, ZonaLlenaException, ZonaEsLaActualException
    {
        if (!persona.puedeAcceder(zonaDestino)) {
            persona.addAcceso(new Acceso(zonaDestino, LocalDateTime.now(), 0, EstadoAcceso.DENEGADO));
            throw new AccesoDenegadoException();
        }
        if (zonaDestino instanceof Capado capado && capado.getCapacidad() == 0) {
            persona.addAcceso(new Acceso(zonaDestino, LocalDateTime.now(), 0, EstadoAcceso.DENEGADO));
            throw new ZonaLlenaException();
        }
        if (persona.getZonaActual()==zonaDestino){
            throw  new ZonaEsLaActualException();
        }
        zonaDestino.ponePersona();
        persona.getZonaActual().sacaPersona();
        persona.addAcceso(new Acceso(persona.getZonaActual(), LocalDateTime.now(), (int) Duration.between(LocalDateTime.now(), LocalDateTime.parse(persona.getUltimoAccesoAceptado().getFecha())).toMinutes() , EstadoAcceso.AUTORIZADO));
        persona.setZonaActual(zonaDestino);

    }

    //--------------------- Mostrar Todas ------------------
    // Metodos para desarollo!!!

    public void mostrarZonas(){
        zonas.forEach((id,zona)->{
            System.out.println(zona.toString());
        });
    }

    public void mostrarPersonas(){
        personas.forEach((id,persona)->{
            System.out.println(persona.toString());
        });
    }

    //-------------- Carga Datos De Archivo --------------

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