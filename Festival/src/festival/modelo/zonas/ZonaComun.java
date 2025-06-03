package festival.modelo.zonas;

import festival.modelo.enums.TipoZona;

public class ZonaComun extends Zona{
    public ZonaComun(String codigo, String descripcion){
        super(codigo,descripcion, TipoZona.ZONA_COMUN);
    }

    @Override
    public int getCapacidadMaxima(){
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof ZonaComun)){
            return false;
        }
        ZonaComun a=(ZonaComun) obj;
        return this.codigo.equals(a.getCodigo()) && a.codigo!=null ;
    }
}
