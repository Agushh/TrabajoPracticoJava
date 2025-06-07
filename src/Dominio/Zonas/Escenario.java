package Dominio.Zonas;

import Dominio.Enums.TipoZona;
import Dominio.Zonas.Datos.Evento;
import java.time.LocalDateTime;
import java.util.*;

public class Escenario extends ZonaRestringida{ // todo ZONA RESTRINGIDA?
    private int capacidadMaxima;
    private static int cantActualPers = 0;
    private List<Evento> eventos;
    private TipoZona tipo;

    public Escenario(String descripcion,int capacidadMaxima){
        super(descripcion,capacidadMaxima);
        this.eventos=new ArrayList<>();
    }

    public void addEvento(LocalDateTime fecha, String artista){
        eventos.add(new Evento(fecha,artista));
    }

    public List<Evento> getEventos(){
        return eventos;
    }
}
