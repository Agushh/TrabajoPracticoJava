package Dominio.Zonas;
import Dominio.Zonas.Interface.Capado;

/**
 * Zona restringida es una zona a la cual solo algunas personas tienen acceso y tiene una capacidad maxima.
 */
public class ZonaRestringida extends Zona implements Capado {
    /**
     * Capacidad maxima de personas.
     */
    private int capacidadMaxima;

    /**
     * Constructor.
     * @param id Id de la zona.
     * @param descripcion Descripcion de la zona.
     * @param capacidadMaxima Capacidad maxima de la zona.
     */
    public ZonaRestringida(String id, String descripcion, int capacidadMaxima) {
        super(id, descripcion);
        this.capacidadMaxima = capacidadMaxima;
    }

    /**
     * Constructor (Necesario para Jackson).
     */
    public ZonaRestringida(){}

    /**
     * Retorna la capacidad maxima.
     * @return Capacidad maxima.
     */
    public int getCapacidadMaxima(){return capacidadMaxima;}

    /**
     * Setea la capacidad maxima de la zona (Necesario para Jackson).
     * @param capacidadMaxima Capacidad maxima de la zona.
     */
    public void setCapacidadMaxima(int capacidadMaxima){ this.capacidadMaxima = capacidadMaxima;}


    /**
     * Retorna la capacidad disponible de la zona.
     * @return Capacidad disponible.
     */
    @Override
    public int getCapacidad() {return capacidadMaxima - getConcurrencia();}
}
