package Dominio.Personas.Datos;

import Dominio.Enums.EstadoAcceso;
import Dominio.Zonas.Zona;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Acceso {
    private Zona zona;
    private LocalDateTime fecha;
    private int minutos;
    private EstadoAcceso estado;

    public Acceso(Zona zona, LocalDateTime fecha,int minutos, EstadoAcceso estado){
     this.zona=zona;
      this.fecha=fecha;
      this.minutos=minutos;
      this.estado=estado;
    }
    public Acceso(Zona zona){
    this.zona = zona;
}

    public Acceso(){}

    public Zona getZona() {
        return zona;
    }

    public String getFecha() {
        return fecha.toString();
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
        this.fecha = LocalDateTime.parse(fecha);
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
        return "<html>"+ "Zona: " + zona.getId() + " Estado: " +estado + "<br>"+" Fecha :"+ fecha.format(formato) +"   Min: " + minutos  +"</html>" ;
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
