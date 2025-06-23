package Dominio.Zonas.Datos;

import Dominio.Personas.Artista;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Evento de un escenario el cual tiene fecha, hora y un artista correspondiente.
 * @see Artista
 */
public class Evento {
    /**
     * Fecha y hora del evento.
     */
    private LocalDateTime fecha; // formato ISO como "2025-06-21T12:30:00"

    /**
     * Artista en el evento.
     */
    private Artista artista;

    /**
     * Constructor.
     * @param fecha Fecha del evento.
     * @param artista Artista del evento.
     */
    public Evento(LocalDateTime fecha, Artista artista){
        this.fecha = fecha;
        this.artista=artista;
    }

    /**
     * Constructor (Necesario para Jackson).
     */
    public Evento(){}

    /**
     * Retorna la fecha del evento.
     * @return La fecha del evento en String.
     */
    public String getFecha() {
        return fecha.toString();
    }

    /**
     * Setea la fecha del evento (Necesario para el Jackson).
     * @param fecha Fecha del evento.
     */
    public void setFecha(String fecha) {
        this.fecha = LocalDateTime.parse(fecha);
    }

    /**
     * Retorna el artista del evento.
     * @return El Artista del evento.
     */
    public Artista getArtista(){return artista;}

    /**
     * Setea el artista del evento.
     * @param artista Artista del evento.
     */
    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    /**
     * Retorna la informacion del evento en String.
     * @return Info en String.
     */
    @Override
    public String toString(){
        return artista.getNombre() + "-"+ fecha.toString();
    }

    /**
     * Compara objetos con este evento y verifica que sean iguales.
     * @return Si es igual o no.
     */
    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        else if(obj == this) return true;
        else return obj instanceof Evento e && e.fecha.equals(fecha) && e.artista.equals(artista);
    }
    /**
     * Retorna el hashcode del evento.
     * @return el hashcode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(fecha, artista);
    }
}
