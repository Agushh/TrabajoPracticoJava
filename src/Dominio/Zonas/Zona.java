package Dominio.Zonas;

import Dominio.Enums.TipoZona;

import java.util.Objects;

public abstract class Zona implements Comparable{
    private static int contZ = 0; //Cuento todas las zonas que fueron creadas
    private String codigo; // todo PROTECTED? ///Private, acceder con GetCod
    private String descripcion;
    private int concurrencia; ///cantidad de personas. Se utiliza en todas las zonas.

    // todo Hago PRIVATE CONSTRUCTOR PARA SOLO PODER CREAR A PARTIR DE FACTORY?? Y AGREGO FACTORY ACA??? COMO HAGO??
    Zona(String descripcion, TipoZona tipo){
        this.codigo = tipo.trunc() + "-" + String.format("%04d", contZ++); ;
        this.descripcion=descripcion;
        concurrencia = 0;
    }

    public String getCod() {
        return codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getConcurrencia()
    {
        return concurrencia;
    }

    public abstract int getCapacidadMaxima();

    public void ponePersona()
    {
        concurrencia ++;
    }

    public void sacaPersona()
    {
        concurrencia --;
    }

    @Override
    public String toString() {
        return "[" + codigo + "] " + codigo + ": " + descripcion;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        else if (o == this) return true;
        else return codigo.equals(((Zona) o).getCod());
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    /// Funcion para el TreeSet de Zonas en Persona. Se usa para ordenar los elementos de forma automatica.
    @Override
    public int compareTo(Object o) {
        if(o == this) return 0;
        return codigo.compareTo(((Zona) o).codigo);
    }

    public void mostrar(){
        System.out.println(this.toString());
    }


    /*
    public boolean estaLlena(){ // todo esto no tiene que estar aca tiene que estar solo en zonas con capacidad, creo que hay que crear una interfaz
        return false;
    }
    */
}
