package Dominio.Personas.Datos;


import Dominio.Enums.EstadoAcceso;
import Dominio.Zonas.Zona;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

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
public Acceso(Zona zona){
    this.zona = zona;
}
//Constructor generico para comparacion a la hora de hacer una busqueda en Persona

    /// Getters
    public Zona getZona() {
        return zona;
    }

    public LocalDateTime getFechahora() {
        return fechahora;
    }

    public int getMinutos() {
        return minutos;
    }

    public EstadoAcceso getEstado() {
        return estado;
    }


    @Override
    public String toString(){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        return "Zona: " + zona.getCod() + " Estado: " +estado + " Fecha :"+ fechahora.format(formato) +" Min: " + minutos   ;
        //return fechahora + " - Zona: " + zona.getCod() + " - Estado: " + estado + " - " + minutos + " min";
    }

    /// Fecha de ingreso + Zona => Clave primaria
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        return obj instanceof Acceso ac && ac.zona.equals(zona) && ac.fechahora.equals(fechahora);
    }
    @Override
    public int hashCode() {
        return Objects.hash(zona, fechahora);
    }
}
