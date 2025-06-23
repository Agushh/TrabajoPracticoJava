package Inicializador;

import Dominio.Personas.Persona;
import Dominio.Zonas.Zona;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import java.util.ArrayList;

/**
 * Clase contenedora de datos utilizada para la serialización y deserialización de información
 * en formato XML. Agrupa listas de Personas y Zonas.
 * Esta clase es útil para estructurar los datos al guardarlos o cargarlos desde archivos XML
 * utilizando Jackson XML.
 *
 */
public class DataContainer {
    /**
     * Lista de zonas registradas en el sistema.
     * El atributo {@code @JacksonXmlElementWrapper(useWrapping = false)} evita que
     * Jackson envuelva los elementos en un nodo adicional.
     */
    @JacksonXmlElementWrapper(useWrapping = false)
    private ArrayList<Zona> zonas = new ArrayList<>();

    /**
     * Lista de personas registradas en el sistema.
     * El atributo {@code @JacksonXmlElementWrapper(useWrapping = false)} evita que
     * Jackson envuelva los elementos en un nodo adicional.
     */
    @JacksonXmlElementWrapper(useWrapping = false)
    private ArrayList<Persona> personas = new ArrayList<>();

    /**
     * Constructor que permite inicializar el contenedor con listas existentes.
     *
     * @param personas lista de personas a cargar.
     * @param zonas lista de zonas a cargar.
     */
    public DataContainer(ArrayList<Persona> personas, ArrayList<Zona> zonas){
        this.personas = personas;
        this.zonas = zonas;
    }

    /**
     * Constructor generico (para Jackson).
     */
    public DataContainer(){}

    /**
     * Devuelve la lista de personas contenidas.
     *
     * @return lista de personas.
     */
    public ArrayList<Persona> getPersonas() {
        return personas;
    }

    /**
     * Asigna la lista de personas contenidas.
     *
     */
    public void setPersonas(ArrayList<Persona> personas) {
        this.personas = personas;
    }

    /**
     * Agrega una persona a la lista.
     *
     * @param persona persona a agregar.
     */
    public void addPersona(Persona persona){personas.add(persona);}

    /**
     * Devuelve la lista de zonas contenidas.
     *
     * @return lista de zonas.
     */
    public ArrayList<Zona> getZonas() {
        return zonas;
    }

    /**
     * Reemplaza la lista de zonas contenidas.
     *
     * @param zonas nueva lista de zonas.
     */
    public void setZonas(ArrayList<Zona> zonas) {
        this.zonas = zonas;
    }

    /**
     * Agrega una zona a la lista.
     *
     * @param zona zona a agregar.
     */
    public void addZona(Zona zona) {zonas.add(zona);}
}
