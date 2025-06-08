package Dominio.Zonas;

import Dominio.Enums.TipoZona;

import java.util.Objects;

public abstract class Zona implements Comparable{
    private static int contZ = 0; //Cuento todas las zonas que fueron creadas
    private String codigo; // todo PROTECTED? ///Private, acceder con GetCod
    private String descripcion;
    private TipoZona tipo;

    // todo Hago PRIVATE CONSTRUCTOR PARA SOLO PODER CREAR A PARTIR DE FACTORY?? Y AGREGO FACTORY ACA??? COMO HAGO??
    Zona(String descripcion, TipoZona tipo){
        this.codigo = tipo.trunc() + "-" + String.format("%04d", contZ); ;
        this.descripcion=descripcion;
        this.tipo=tipo;
        contZ++;
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
        if (o == null) return false; /// Borre, no hace falta comprobar la clase porque está dentro del código de zona
        else if (o == this) return true;
        else return codigo.equals(((Zona) o).getCod());
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    /// Funcion para el TreeSet de Zonas en Persona. De esta forma funcionara.
    @Override
    public int compareTo(Object o) {
        return codigo.compareTo(((Zona) o).codigo);
    }

    public void mostrar(){
        System.out.println(this.toString());
    }

    public boolean estaLlena(){ // todo esto no tiene que estar aca tiene que estar solo en zonas con capacidad, creo que hay que crear una interfaz
        return false;
    }

}
