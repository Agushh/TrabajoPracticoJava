package Dominio.Zonas;
import Dominio.Enums.TipoZona;

public class ZonaRestringida extends Zona{
    private int capacidadMaxima;
    private static int cantActualPers = 0;

    public ZonaRestringida(String descripcion, int capacidadMaxima){
        super(descripcion,TipoZona.ZONA_RESTRINGIDA);
        this.capacidadMaxima=capacidadMaxima;
    }

    public ZonaRestringida(String descripcion, int capacidadMaxima, TipoZona tipo){
        super(descripcion,tipo);
        this.capacidadMaxima=capacidadMaxima;
    }

    @Override
    public int getCapacidadMaxima(){
    return capacidadMaxima;
    }

}
