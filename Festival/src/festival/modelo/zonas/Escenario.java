package festival.modelo.zonas;
import festival.modelo.evento.Evento;
import festival.modelo.enums.TipoZona;
import java.util.*;


public class Escenario extends ZonaRestringida{
private List<Evento> eventos;
public Escenario(String codigo, String descripcion,int capacidadMaxima){
    super(codigo,descripcion,capacidadMaxima);
    this.eventos=new ArrayList<>();
    this.tipo=TipoZona.ESCENARIO;
}

public void agregarEvento(Evento e){
    eventos.add(e);
}

public List<Evento> getEventos(){
    return eventos;
}

}
