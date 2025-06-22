package Dominio.Personas;

import Dominio.Enums.TipoPers;
import Dominio.Personas.Datos.Acceso;
import Dominio.Zonas.Escenario;
import Dominio.Zonas.Zona;

import java.util.List;
import java.util.TreeSet;

public class Artista extends Persona {

    private Escenario escenario;

    public Artista(String nombre, String id) {
        super(nombre, id);
    }

    public Artista(){

    }

    public Escenario getEscenario() {
        return escenario;
    }

    public void setEscenario(Escenario escenario) {
        this.escenario = escenario;
    }

    public void addZona(Zona z){
        if(z instanceof Escenario) {
            this.escenario = (Escenario)z;
        }else{
            getZonasPermitidas().add(z);
        }
    }

    @Override
    public boolean puedeAcceder(Zona z) {
        return z.equals(escenario) || getZonasPermitidas().contains(z);
    }
}