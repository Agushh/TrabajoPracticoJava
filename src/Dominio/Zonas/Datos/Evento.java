package Dominio.Zonas.Datos;

import Dominio.Personas.Artista;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;
import java.util.Objects;

public class Evento {
    private LocalDateTime fecha; // formato ISO como "2025-06-21T12:30:00"
    private Artista artista;

    public Evento(LocalDateTime fecha, Artista artista){
        this.fecha = fecha;
        this.artista=artista;
    }

    public String getFecha() { //todo Devuleve String????
        return fecha.toString();
    }

    public void setFecha(String fecha) {
        this.fecha = LocalDateTime.parse(fecha);
    }
    public Artista getArtista(){return artista;};

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    @Override
    public String toString(){
        return artista.getNombre() + "-"+ fecha.toString();
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
