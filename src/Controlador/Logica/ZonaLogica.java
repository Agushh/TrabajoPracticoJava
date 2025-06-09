package Controlador.Logica;

import Dominio.Enums.TipoZona;
import Dominio.Factory.ZonaFactory;
import Dominio.Zonas.*;

import java.util.TreeMap;

public class ZonaLogica {

    //Aplico Singleton
    private static ZonaLogica instancia;
    private final TreeMap<String, Zona> zonas;

    private ZonaLogica(TreeMap<String, Zona> zonas) {
        this.zonas = zonas;
    }

    //Get de la instancia Singleton
    public static ZonaLogica getInstancia(TreeMap<String, Zona> zonas) {
        if(instancia == null){
            instancia = new ZonaLogica(zonas);
        }
        return instancia;
    }

    //Get una zona
    public Zona getZona(String codZona) throws IllegalArgumentException {
        Zona z = zonas.get(codZona);
        if (z == null) {
            throw new IllegalArgumentException("No existe una zona con código: " + codZona);
        }
        return z;
    }

    //----------- SOBRECARGO EL ADD ZONA --------------

    public Zona add(TipoZona tipo, String descripcion) throws IllegalArgumentException {
        //Uso el patron de diseño Factory para mayor claridad
        try{
            //verifico que coincida parametros con tipo
            if(tipo == TipoZona.ZONA_COMUN) {
                Zona z = new ZonaComun(descripcion);
                zonas.put(z.getCod(),z);
                return z;
            }else{
                throw new IllegalArgumentException(); // todo HACER EXCEPCION DE TIPO DE ZONA INVALIDO CON PARAMETROS
            }
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException();
        }
    }

    public Zona add(TipoZona tipo, String descripcion, int capacidad) throws IllegalArgumentException {
        //Uso el patron de diseño Factory para mayor claridad
        try{
            Zona z = null;
            //verifico que coincida parametros con tipo
            if(tipo == TipoZona.ESCENARIO) {
                z = new Escenario(descripcion, capacidad);
            }else if(tipo == TipoZona.ZONA_RESTRINGIDA){
                z = new ZonaRestringida(descripcion, capacidad);
            }else {
                throw new IllegalArgumentException(); // todo HACER EXCEPCION DE TIPO DE ZONA INVALIDO CON PARAMETROS
            }
            zonas.put(z.getCod(),z);
            return z;
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException();
        }
    }

    public Zona add(TipoZona tipo, String descripcion, int capacidad, String zona, String responsable) throws IllegalArgumentException { // todo VERIFICAR QUE LOS CODIGOS QUE SE PASEN SEAN CORRESPONDIENTES A LA DATA
        //Uso el patron de diseño Factory para mayor claridad
        try{
            //verifico que coincida parametros con tipo
            if(tipo == TipoZona.STAND) {
                Zona z = new Stand(descripcion, capacidad, zona, responsable);
                zonas.put(z.getCod(),z);
                return z;
            }else{
                throw new IllegalArgumentException(); // todo HACER EXCEPCION DE TIPO DE ZONA INVALIDO CON PARAMETROS
            }
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException();
        }
    }

    //------------------------------------

    //Muestro toda las zonas
    public void mostrarTodas(){
        zonas.forEach((id,zona)->{
            System.out.println(zona.toString());
        });
    }


}
