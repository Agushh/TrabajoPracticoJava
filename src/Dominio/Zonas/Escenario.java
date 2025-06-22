package Dominio.Zonas;

import Dominio.Enums.TipoZona;
import Dominio.Personas.Artista;
import Dominio.Zonas.Datos.Evento;
import Dominio.Zonas.Interface.Capado;

import java.time.LocalDateTime;
import java.util.*;
/**
 * Escenario es un tipo de Zona la cual tiene capacidad maxima y una lista de Eventos de artistas.
 */
public class Escenario extends Zona implements Capado {
    /**
     * Capacidad maxima.
     */
    private int capacidadMaxima;

    /**
     * Lista de eventos.
     */
    private List<Evento> eventos = new ArrayList<>();

    public Escenario(String id, String descripcion, int concurrencia, int capacidadMaxima) {
        super(id, descripcion, concurrencia);
        this.capacidadMaxima = capacidadMaxima;
    }

    public Escenario(){}

    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }
    public void setCapacidadMaxima(int capacidadMaxima){ this.capacidadMaxima = capacidadMaxima;}

    public List<Evento> getEventos(){
        return eventos;
    }
    public void setEventos(List<Evento> eventos){this.eventos = eventos;}

    public void addEvento(LocalDateTime fecha, Artista artista){
        eventos.add(new Evento(fecha,artista));
    }

    @Override
    public int getCapacidad() {return capacidadMaxima - getConcurrencia();}

    @Override
    public String toHTML() {
        return "<html>" + super.toHTML().replace("<html>", "")+ "<br>" + "Capacidad: " + getCapacidad() + "</html>";
    }
}
