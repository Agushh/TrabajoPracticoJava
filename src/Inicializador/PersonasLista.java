package Inicializador;

import Dominio.Personas.Persona;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.ArrayList;

@JacksonXmlRootElement(localName = "personas")
public class PersonasLista {
    @JacksonXmlElementWrapper(useWrapping = false) // evita que cree un nodo extra "personas"
    private ArrayList<Persona> personas = new ArrayList<>();

    public PersonasLista() {}

    public PersonasLista(ArrayList<Persona> personas) {
        this.personas = personas;
    }

    public ArrayList<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(ArrayList<Persona> personas) {
        this.personas = personas;
    }

    public void addPersona(Persona persona){personas.add(persona);}
}