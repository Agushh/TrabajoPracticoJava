package Dominio.Zonas;

import Dominio.Enums.TipoZona;

import java.util.Objects;

public abstract class Zona {
    private static int contZ = 0; //Cuento todas las zonas que fueron creadas
    protected String codigo; // todo PROTECTED?
    private String descripcion;
    protected TipoZona tipo; // todo PROTECTED?

    // todo Hago PRIVATE CONSTRUCTOR PARA SOLO PODER CREAR A PARTIR DE FACTORY?? Y AGREGO FACTORY ACA??? COMO HAGO??
    Zona(String descripcion, TipoZona tipo){
        this.codigo = this.generateCod(tipo);
        this.descripcion=descripcion;
        this.tipo=tipo;
        contZ++;
    }

    private String generateCod(TipoZona t){
        return String.valueOf(t) + "-" + String.format("%04d", contZ); //Genero un Id unico con inforamcion de tipo
    }

    public String getCod() {
        return codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public TipoZona getTipo() {
        return tipo;
    }


    public abstract int getCapacidadMaxima();

    @Override
    public String toString() {
        return "[" + tipo + "] " + codigo + ": " + descripcion;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Zona zona = (Zona) o;
        return codigo.equals(zona.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }



}
