package app;

import Exceptions.GUIException;
import modelo.zonas.*;
import modelo.evento.Evento;
import GUI.CustomJFrame;

import java.time.LocalDateTime;

public class MainFestival {
    public static void main(String[] args){

        //creacion de ventana en Swing.
        //CustomJFrame es una clase heredada de JFrame para que los parametros del constructor construyan la ventana a la hora de invocarlo.
        CustomJFrame window = new CustomJFrame("hola", 300, 300, 100, 100, true);

        //metodo para añadir texto / label
        window.addLabel("text");

        Evento even= new Evento(LocalDateTime.now(),"Calamaro");
        Evento tu= new Evento(LocalDateTime.now(),"coldplay");
        ZonaComun patio = new ZonaComun("dx10","Patio Central");
        ZonaComun centro = new ZonaComun("dx20","centro especial");
        Escenario es=new Escenario("ESC1", "Escenario Norte", 2);
        es.agregarEvento(even);
        //System.out.println(estand);
        es.agregarEvento(tu);
        System.out.println(patio.equals(centro));
        System.out.println(patio);
        System.out.println("\n");
        System.out.println(es.getEventos());
    }

}
