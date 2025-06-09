package Controlador.Logica;

import Dominio.Enums.TipoPers;
import Dominio.Factory.PersonaFactory;
import Dominio.Personas.Persona;
import Dominio.Zonas.Zona;

import java.util.TreeMap;

public class PersonaLogica {

    //Aplico Singleton
    private static PersonaLogica instancia;
    private final TreeMap<String, Persona> personas;

    private PersonaLogica(TreeMap<String, Persona> personas) {
        this.personas = personas;
    }

    //Get de la instancia Singleton
    public static PersonaLogica getInstancia(TreeMap<String, Persona> personas) {
        if (instancia == null) {
            instancia = new PersonaLogica(personas);
        }
        return instancia;
    }

    //Get una persona
    public Persona getPersona(String codPersona) throws IllegalArgumentException {
        Persona p = personas.get(codPersona);
        if (p == null) {
            throw new IllegalArgumentException("No existe una zona con código: " + codPersona);
        }
        return p;
    }

    //Agrego persona al Map
    public Persona add(TipoPers tipo, String nombre, Zona zonaActual) throws IllegalArgumentException {
        //Uso el patron de diseño Factory para mayor claridad
        try{
            Persona p = PersonaFactory.crear(tipo, nombre, zonaActual);
            personas.put(p.getId(),p);
            return p;
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException();// todo verificar excepcion y agregar mensaje
        }
    }

    //Agrego una zona permitida a una persona con su codigo
    public void addZonaPermitida(String persCod, Zona zona) throws IllegalArgumentException {
        try{
            Persona p = personas.get(persCod);
            p.addZona(zona);
            personas.put(p.getId(),p);
        }catch (Exception e){
            throw new IllegalArgumentException("Codigo de persona no encontrado: " + persCod);
        }
    }

    //Muestro toda las personas
    public void mostrarTodas(){
        personas.forEach((id,persona)->{
            System.out.println(persona.toString());
        });
    }
}
