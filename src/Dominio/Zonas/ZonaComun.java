package Dominio.Zonas;

import Dominio.Enums.TipoZona;

public class ZonaComun extends Zona{
    public ZonaComun(String descripcion){
        super(descripcion, TipoZona.ZONA_COMUN);
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
        return this.codigo.equals(a.getCod()) && a.codigo!=null ;
    }
}
