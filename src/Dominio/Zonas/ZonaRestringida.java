package Dominio.Zonas;
import Dominio.Zonas.Interface.Capado;

public class ZonaRestringida extends Zona implements Capado {
    private int capacidadMaxima;

    public ZonaRestringida(String id, String descripcion, int concurrencia, int capacidadMaxima) {
        super(id, descripcion, concurrencia);
        this.capacidadMaxima = capacidadMaxima;
    }

    public ZonaRestringida(){}

    public int getCapacidadMaxima(){return capacidadMaxima;}
    public void setCapacidadMaxima(int capacidadMaxima){ this.capacidadMaxima = capacidadMaxima;}

    @Override
    public int getCapacidad() {return capacidadMaxima - getConcurrencia();}
}
