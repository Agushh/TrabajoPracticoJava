package modelo.evento;

import java.time.LocalDateTime;
import java.util.*;

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

}
