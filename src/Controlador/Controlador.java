package Controlador;

import Controlador.Logica.MovimientosLogica;
import Dominio.Enums.EstadoAcceso;
import Dominio.Exceptions.AccesoDenegadoException;
import Dominio.Exceptions.ZonaEsLaActualException;
import Dominio.Exceptions.ZonaLlenaException;
import Dominio.Personas.*;
import Dominio.Personas.Datos.Acceso;
import Dominio.Zonas.Interface.Capado;
import Dominio.Zonas.Zona;
import Dominio.Zonas.Stand;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.TreeMap;

public class Controlador {

    private final TreeMap<String, Zona> zonas= new TreeMap<String, Zona>();
    private final TreeMap<String, Persona> personas= new TreeMap<String, Persona>();
    private final TreeMap<String, Stand> stands = new TreeMap<String, Stand>();

    //Aplico patron de diseño Singleton (Te permite generar una sola instancia del objeto)
    private static final Controlador controlador = new Controlador();

    //Constructor privado para la aplicacion de Singleton
    private Controlador(){
        // todo Serializacion???
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

    public Zona getZona(String codZona) throws IllegalArgumentException {
        Zona z = zonas.get(codZona);
        if (z == null) {
            throw new IllegalArgumentException("No existe una zona con código: " + codZona);
        }
        return z;
    }

    public Persona getPersona(String codPersona) throws IllegalArgumentException {
        Persona p = personas.get(codPersona);
        if (p == null) {
            throw new IllegalArgumentException("No existe una zona con código: " + codPersona);
        }
        return p;
    }

    //------------------- Adds -----------------------

    public Zona addZona(Zona z) throws IllegalArgumentException {
        try{
            zonas.put(z.getId(),z);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException();
        }
    }

    public Persona addPersona(Persona p) throws IllegalArgumentException {
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
            throw new AccesoDenegadoException();
        }
        if (zonaDestino instanceof Capado capado && capado.getCapacidad() == 0) {
            throw new ZonaLlenaException();
        }
        if (persona.getZonaActual()==zonaDestino){
            throw  new ZonaEsLaActualException();
        }
        zonaDestino.ponePersona();
        persona.getZonaActual().sacaPersona();
        persona.addAcceso(new Acceso(persona.getZonaActual(), LocalDateTime.now().toString(), (int) Duration.between(LocalDateTime.now(), persona.getUltimoAccesoAceptado().getFechaAsDateTime()).toMinutes() , EstadoAcceso.AUTORIZADO));
        persona.setZonaActual(zonaDestino);
        persona.addAcceso(new Acceso(zonaDestino, LocalDateTime.now().toString(), 0, EstadoAcceso.DENEGADO));
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

}