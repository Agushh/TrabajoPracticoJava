package modelo.zonas;

import modelo.enums.TipoZona;
import modelo.evento.Evento;

import java.util.*;


public class Escenario extends ZonaRestringida{ // todo ZONA RESTRINGIDA?
    private int capacidadMaxima;
    private static int cantActualPers = 0;
    private List<Evento> eventos;
    private TipoZona tipo;

    public Escenario(String codigo, String descripcion,int capacidadMaxima, TipoZona tipo){
        super(codigo,descripcion,capacidadMaxima);
        this.eventos=new ArrayList<>();
        this.tipo= tipo;
    }

    public void addEvento(Evento e){
        eventos.add(e);
    }

    public List<Evento> getEventos(){
        return eventos;
    }
}
