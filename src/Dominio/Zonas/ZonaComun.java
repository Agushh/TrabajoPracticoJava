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
        if(obj instanceof ZonaComun zc){
            return zc.getCod()!=null && getCod().equals((zc.getCod()));
        }
        return  false;
    }
}
