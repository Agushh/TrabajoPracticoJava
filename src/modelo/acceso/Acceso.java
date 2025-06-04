package modelo.acceso;


import modelo.enums.EstadoAcceso;
import modelo.zonas.Zona;

import java.time.LocalDateTime;

public class Acceso {
private Zona zona;
private LocalDateTime fechahora;
private int minutos;
private EstadoAcceso estado;

public Acceso(Zona zona, LocalDateTime fechahora,int minutos, EstadoAcceso estado){
    this.zona=zona;
    this.fechahora=fechahora;
    this.minutos=minutos;
    this.estado=estado;
}

    public Zona getZona() {
        return zona;
    }


    public LocalDateTime getFechahora() {
        return fechahora;
    }


    public int getMinutos() {
        return minutos;
    }

    @Override
    public String toString(){
        return fechahora + " - Zona: " + zona.getCodigo() + " - Estado: " + estado + " - " + minutos + " min";
    }


    public EstadoAcceso getEstado() {
        return estado;
    }


}
