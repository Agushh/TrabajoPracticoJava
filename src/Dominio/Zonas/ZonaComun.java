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
}
