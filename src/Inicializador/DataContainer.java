package Inicializador;

import Dominio.Personas.Persona;
import Dominio.Zonas.Zona;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import java.util.ArrayList;

public class DataContainer {
    @JacksonXmlElementWrapper(useWrapping = false) // evita que cree un nodo extra "zonas"
    private ArrayList<Zona> zonas = new ArrayList<>();
    @JacksonXmlElementWrapper(useWrapping = false) // evita que cree un nodo extra "personas"
    private ArrayList<Persona> personas = new ArrayList<>();


    public DataContainer(ArrayList<Persona> personas, ArrayList<Zona> zonas){
        this.personas = personas;
        this.zonas = zonas;
    }

    public DataContainer(){}

    public ArrayList<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(ArrayList<Persona> personas) {
        this.personas = personas;
    }

    public void addPersona(Persona persona){personas.add(persona);}

    public ArrayList<Zona> getZonas() {
        return zonas;
    }

    public void setZonas(ArrayList<Zona> zonas) {
        this.zonas = zonas;
    }

    public void addZona(Zona zona) {zonas.add(zona);}
}
