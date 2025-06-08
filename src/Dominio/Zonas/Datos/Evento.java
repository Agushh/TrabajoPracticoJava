package Dominio.Zonas.Datos;

import java.time.LocalDateTime;
import java.util.Objects;

public class Evento {
    private LocalDateTime fechahora;
    private String artista;

    public Evento(LocalDateTime fechahora, String artista){
        this.fechahora=fechahora;
        this.artista=artista;
    }

    public LocalDateTime getFechahora(){return fechahora;};
    public String getArtista(){return artista;};

    @Override
    public String toString(){
        return artista + "-"+fechahora.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        else if(obj == this) return true;
        else return obj instanceof Evento e && e.fechahora.equals(fechahora) && e.artista.equals(artista);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fechahora, artista);
    }
}
