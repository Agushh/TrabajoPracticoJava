package modelo.personas;

import modelo.zonas.Zona;


public class Asistente extends Persona{
    public Asistente(String id, String nombre){
        super(id,nombre);
    }

    @Override
    public boolean puedeAcceder(Zona z){
        return true;
    }

}
