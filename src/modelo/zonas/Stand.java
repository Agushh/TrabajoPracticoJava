package modelo.zonas;

import modelo.enums.EquipoFutbol;
import modelo.personas.Comerciante;
import modelo.enums.TipoZona;

import java.util.*;
import java.util.Objects;

public class Stand {
    private String nombre;
    private int numero;
    private EquipoFutbol equi;

    public Stand(String nombre, int numero, EquipoFutbol equi){
        this.nombre=nombre;
        this.numero=numero;
        this.equi=equi;
    }

    @Override
    public String toString(){
        return this.nombre +"  "+ this.numero + "  "+this.equi;
    }



}
