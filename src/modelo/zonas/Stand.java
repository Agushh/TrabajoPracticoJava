package modelo.zonas;

public class Stand {
    private String nombre;
    private int numero;


    public Stand(String nombre, int numero){
        this.nombre=nombre;
        this.numero=numero;
    }

    @Override
    public String toString(){
        return this.nombre +"  "+ this.numero;
    }



}
