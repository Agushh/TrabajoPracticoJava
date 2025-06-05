package modelo.zonas;

import modelo.enums.TipoZona;

import java.util.Objects;

public abstract class Zona {
    protected String codigo; // todo PROTECTED?
    private String descripcion;
    protected TipoZona tipo; // todo PROTECTED?

    Zona(String codigo, String descripcion, TipoZona tipo){
        this.codigo=codigo;
        this.descripcion=descripcion;
        this.tipo=tipo;
    }

    public String getCodigo() {
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
