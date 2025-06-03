package modelo.zonas;
import modelo.enums.TipoZona;

public class ZonaRestringida extends Zona{
    protected int capacidadMaxima;

    public ZonaRestringida(String codigo, String descripcion, int capacidadMaxima){
        super(codigo,descripcion,TipoZona.ZONA_RESTRINGIDA);
        this.capacidadMaxima=capacidadMaxima;
    }

    @Override
    public int getCapacidadMaxima(){
    return capacidadMaxima;
    }



}
