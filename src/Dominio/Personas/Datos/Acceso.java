package Dominio.Personas.Datos;

import Dominio.Enums.EstadoAcceso;
import Dominio.Zonas.Zona;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Clase acceso.
 * Cada acceso consta de la zona en la que una persona estuvo, la fecha y hora de su estadia, y la cantidad de minutos de permanencia.
 * A su vez, si el acceso fue denegado, se creara un acceso denegado para guardar la trazabilidad de la persona. dicho acceso tendra una permanencia de 0 minutos.
 * Si la persona tiene un acceso permitido, se calculara su tiempo de estadia segun su ultimo acceso autorizado.
 *
 *
 * @see Zona
 * @see EstadoAcceso
 */
public class Acceso {
    /**
     * Zona en la cual se accedio.
     */
    private Zona zona;
    /**
     * Fecha y hora en la cual se salio de la zona.
     */
    private LocalDateTime fecha;
    /**
     * Cantidad de minutos de permanencia.
     */
    private int minutos;
    /**
     * Estado tipo Autorizado o Denegado.
     */
    private EstadoAcceso estado;

    /**
     * Constructor de la Clase Acceso.
     * @param zona Zona
     * @param fecha Fecha
     * @param minutos Minutos
     * @param estado Estado (Autorizado, Denegado)
     */
    public Acceso(Zona zona, LocalDateTime fecha,int minutos, EstadoAcceso estado){
     this.zona=zona;
      this.fecha=fecha;
      this.minutos=minutos;
      this.estado=estado;
    }


    /**
     * Constructor de acceso generico(Para Jackson)
     */
    public Acceso(){}

    /**
     * Permite consultar la zona del acceso
     * @return Zona
     */
    public Zona getZona() {
        return zona;
    }

    /**
     * Devuelve la fecha y hora del acceso como cadena de texto.
     *
     * @return fecha en formato ISO-8601.
     */
    public String getFecha() {
        return fecha.toString();
    }

    /**
     * Devuelve la duración del acceso en minutos.
     *
     * @return cantidad de minutos que duró el acceso.
     */
    public int getMinutos() {
        return minutos;
    }

    /**
     * Devuelve el estado actual del acceso.
     *
     * @return estado del acceso.
     */
    public EstadoAcceso getEstado() {
        return estado;
    }

    /**
     * Establece la Zona para este acceso.
     *
     * @param zona la zona a asignar.
     */
    public void setZona(Zona zona) {
        this.zona = zona;
    }

    /**
     * Establece la fecha del acceso a partir de una cadena.
     *
     * @param fecha cadena de texto en formato ISO-8601 (por ejemplo, "2025-06-21T14:30").
     */
    public void setFecha(String fecha) {
        this.fecha = LocalDateTime.parse(fecha);
    }

    /**
     * Establece la duración del acceso en minutos.
     *
     * @param minutos cantidad de minutos.
     */
    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }

    /**
     * Establece el estado del acceso.
     *
     * @param estado el nuevo estado del acceso.
     */
    public void setEstado(EstadoAcceso estado) {
        this.estado = estado;
    }
    /**
     * Devuelve una representación en cadena del acceso, formateada en HTML.
     * Incluye zona, estado, fecha y duración.
     *
     * @return descripción legible del acceso en HTML.
     */
    @Override
    public String toString(){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");// agrego un formato para que se vea bien la hora y la fecha
        return "<html>"+ "Zona: " + zona.getId() + " Estado: " +estado + "<br>"+" Fecha :"+ fecha.format(formato) +"   Min: " + minutos  +"</html>" ;
    }

    /**
     * Compara este acceso con otro para determinar si son equivalentes.
     * Dos accesos son iguales si tienen la misma zona y la misma fecha.
     *
     * @param obj el objeto a comparar.
     * @return {@code true} si son iguales, de lo contrario {@code false}.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        return obj instanceof Acceso ac && ac.zona.equals(zona) && ac.fecha.equals(fecha);
    }

    /**
     * Calcula el hash del acceso en base a su zona y fecha.
     *
     * @return valor hash.
     */
    @Override
    public int hashCode() {
        return Objects.hash(zona, fecha);
    }
}
