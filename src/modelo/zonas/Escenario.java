package modelo.zonas;

import modelo.enums.TipoZona;
import modelo.evento.Evento;

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

    public void addEvento(Evento e){
        eventos.add(e);
    }

    public List<Evento> getEventos(){
        return eventos;
    }
}
