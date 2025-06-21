package Dominio.Zonas.Datos;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;
import java.util.Objects;

public class Evento {
    private String fecha; // formato ISO como "2025-06-21T12:30:00"
    private String artista;

    public Evento(String fecha, String artista){
        this.fecha = fecha;
        this.artista=artista;
    }
    @JsonIgnore
    public LocalDateTime getFechaAsDateTime() {
        return LocalDateTime.parse(fecha);
    }
    @JsonIgnore
    public void setFechaFromDateTime(LocalDateTime fecha) {
        this.fecha = fecha.toString(); // o con DateTimeFormatter
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public String getArtista(){return artista;};

    public void setArtista(String artista) {
        this.artista = artista;
    }



    @Override
    public String toString(){
        return artista + "-"+ fecha;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        else if(obj == this) return true;
        else return obj instanceof Evento e && e.fecha.equals(fecha) && e.artista.equals(artista);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fecha, artista);
    }
}
