package Dominio.Zonas;

/**
 * Zona comun es una zona a la cual tienen acceso todas las personas.
 */
public class ZonaComun extends Zona{

    /**
     * Constructor.
     * @param id Id de la zona.
     * @param descripcion Descripcion de la zona.
     */
    public ZonaComun(String id, String descripcion) {
        super(id, descripcion);
    }

    /**
     * Constructor (Necesario para Jackson).
     */
    public ZonaComun() {
    }

    /**
     * Verifica que 2 objetos sean iguales.
     * @param obj Objeto a comparar.
     * @return Si son iguales.
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof ZonaComun zc){
            return zc.getId()!=null && getId().equals((zc.getId()));
        }
        return  false;
    }
}
