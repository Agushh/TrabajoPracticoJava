package Dominio.Zonas;

import Dominio.Enums.TipoZona;

import java.util.Objects;

public abstract class Zona {
    private static int contZ = 0; //Cuento todas las zonas que fueron creadas
    protected String codigo; // todo PROTECTED?
    private String descripcion;
    private TipoZona tipo;

    // todo Hago PRIVATE CONSTRUCTOR PARA SOLO PODER CREAR A PARTIR DE FACTORY?? Y AGREGO FACTORY ACA??? COMO HAGO??
    Zona(String descripcion, TipoZona tipo){
        this.codigo = this.generateCod(tipo);
        this.descripcion=descripcion;
        this.tipo=tipo;
        contZ++;
    }

    private String generateCod(TipoZona t){
        return t.trunc() + "-" + String.format("%04d", contZ); //Genero un Id unico con inforamcion de tipo
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

    public void mostrar(){
        System.out.println(this.toString());
    }

    public boolean estaLlena(){ // todo esto no tiene que estar aca tiene que estar solo en zonas con capacidad, creo que hay que crear una interfaz
        return false;
    }

}
