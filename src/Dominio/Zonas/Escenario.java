package Dominio.Zonas;

import Dominio.Personas.Artista;
import Dominio.Zonas.Datos.Evento;
import Dominio.Zonas.Interface.Limitado;

import java.time.LocalDateTime;
import java.util.*;
/**
 * Escenario es un tipo de Zona la cual tiene capacidad maxima y una lista de Eventos de artistas.
 * @see Evento
 */
public class Escenario extends Zona implements Limitado {
    /**
     * Capacidad maxima.
     */
    private int capacidadMaxima;

    /**
     * Lista de eventos.
     */
    private List<Evento> eventos = new ArrayList<>();

    /**
     * Constructor
     * @param id Id del escenario.
     * @param descripcion Descripcion del escenario.
     * @param capacidadMaxima Capacidad maxima de la zona.
     */
    public Escenario(String id, String descripcion, int capacidadMaxima) {
        super(id, descripcion);
        this.capacidadMaxima = capacidadMaxima;
    }

    /**
     * Constructor (Necesario para Jackson).
     */
    public Escenario(){}

    /**
     * Retorna capacidad de la zona.
     * @return Capacidad maxima.
     */
    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    /**
     * Setea capacidad (Necesario para Jackson)
     * @param capacidadMaxima Capacidad maxima de la zona.
     */
    public void setCapacidadMaxima(int capacidadMaxima){ this.capacidadMaxima = capacidadMaxima;}

    /**
     * Retorna lista de eventos.
     * @return Lista de eventos.
     */
    public List<Evento> getEventos(){
        return eventos;
    }

    /**
     * Setea lista de eventos (Necesario para Jackson).
     * @param eventos Lista de Eventos.
     */
    public void setEventos(List<Evento> eventos){this.eventos = eventos;}

    /**
     * Agrega un evento a la lista.
     * @param fecha Fecha del evento.
     * @param artista Artista
     */
    public void addEvento(LocalDateTime fecha, Artista artista){
        eventos.add(new Evento(fecha,artista));
    }

    /**
     * Retorna la capacidad disponible.
     * @return Capacidad disponible.
     */
    @Override
    public int getCapacidad() {return capacidadMaxima - getConcurrencia();}

    /**
     * Retorna informacion en String con forma de html.
     * @return Informacion con tags de html.
     */
    @Override
    public String toHTML() {
        return "<html>" + super.toHTML().replace("<html>", "")+ "<br>" + "Capacidad: " + getCapacidad() + "</html>";
    }
}
