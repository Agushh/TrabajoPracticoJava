package Dominio.Zonas;

public class ZonaComun extends Zona{

    public ZonaComun(String id, String descripcion, int concurrencia) {
        super(id, descripcion, concurrencia);
    }

    public ZonaComun() {
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof ZonaComun zc){
            return zc.getId()!=null && getId().equals((zc.getId()));
        }
        return  false;
    }
}
