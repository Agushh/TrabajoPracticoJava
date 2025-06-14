package Dominio.Zonas;

import Dominio.Enums.TipoZona;
import Dominio.Zonas.Datos.Evento;
import Dominio.Zonas.Interface.Capado;

import java.time.LocalDateTime;
import java.util.*;

public class Escenario extends Zona implements Capado { // todo ZONA RESTRINGIDA?
    private int capacidadMaxima;
    //private static int cantActualPers = 0; // se hereda var concurrencia de Zona.

    private final List<Evento> eventos;
    private TipoZona tipo;

    public Escenario(String descripcion,int capacidadMaxima){
        super(descripcion, TipoZona.ESCENARIO);
        this.capacidadMaxima = capacidadMaxima;
        this.eventos=new ArrayList<>();
    }

    @Override
    public int getCapacidad()
    {
        return capacidadMaxima - getConcurrencia();
    }

    @Override
    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    @Override
    public String toString() {
        return "<html>" + super.toString().replace("<html>", "").replace("</html>", "")+ "<br>" + "Capacidad: " + getCapacidad() + "</html>";
    }

    public void addEvento(LocalDateTime fecha, String artista){
        eventos.add(new Evento(fecha,artista));
    }

    public List<Evento> getEventos(){
        return eventos;
    }

}
