package Dominio.Personas.Datos;


import Dominio.Enums.EstadoAcceso;
import Dominio.Zonas.Zona;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Acceso {
    private Zona zona;
    private String fecha;
    private int minutos;
    private EstadoAcceso estado;

    public Acceso(Zona zona, String fecha,int minutos, EstadoAcceso estado){
     this.zona=zona;
      this.fecha=fecha;
      this.minutos=minutos;
      this.estado=estado;
    }
    public Acceso(Zona zona){
    this.zona = zona;
}

    public Acceso(){}

    @JsonIgnore
    public LocalDateTime getFechaAsDateTime() {
        return LocalDateTime.parse(fecha);
    }

    @JsonIgnore
    public void setFechaFromDateTime(LocalDateTime fecha) {
        this.fecha = fecha.toString(); // o con DateTimeFormatter
    }
    /// Getters
    public Zona getZona() {
        return zona;
    }

    public String getFecha() {
        return fecha;
    }

    public int getMinutos() {
        return minutos;
    }

    public EstadoAcceso getEstado() {
        return estado;
    }

    public void setZona(Zona zona) {
        this.zona = zona;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }

    public void setEstado(EstadoAcceso estado) {
        this.estado = estado;
    }

    @Override
    public String toString(){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");// agrego un formato para que se vea bien la hora y la fecha
        return "<html>"+ "Zona: " + zona.getId() + " Estado: " +estado + "<br>"+" Fecha :"+ LocalDateTime.parse(fecha).format(formato) +"   Min: " + minutos  +"</html>" ;
    }

    /// Fecha de ingreso + Zona => Clave primaria
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        return obj instanceof Acceso ac && ac.zona.equals(zona) && ac.fecha.equals(fecha);
    }
    @Override
    public int hashCode() {
        return Objects.hash(zona, fecha);
    }
}
