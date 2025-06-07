import Controlador.Controlador;
import Dominio.Enums.TipoPers;
import Dominio.Zonas.*;
import Dominio.Zonas.Datos.Evento;
import Vista.CustomJFrame;

import java.time.LocalDateTime;

public class MainFestival {
    public static void main(String[] args){

        //Genero instancia de controlador
        Controlador controlador = Controlador.getControllador();

        //Intento agregar personas, si el tipo no coincide muestro el error
        try {
            controlador.addPersona(TipoPers.COMERCIANTE, "Alejo");
            controlador.addPersona(TipoPers.STAFF, "Facu");
        }catch (Exception e){ // todo ESPECIFICAR MEJOR LAS EXCEPCIONES
            System.out.println(e);
        }

        controlador.mostrarPersonas();

        //creacion de ventana en Swing.
        //CustomJFrame es una clase heredada de JFrame para que los parametros del constructor construyan la ventana a la hora de invocarlo.
        CustomJFrame window = new CustomJFrame("hola", 300, 300, 100, 100, true);

        //metodo para añadir texto / label
       // window.addLabel("text");
        //gui
        window.addboton("Muestra persona",e->window.abrir_panel_personas());
        window.addboton("Mover persona",e -> window.abri_panel_mover());
        window.addboton("Reporte de stands",e ->window.abrir_panle_stands());
        window.addboton("Reporte de zonas",e -> window.abri_panel_zonas());

        //esto dsp lo sacamos del main en una funcion carga gui
        //fin gui
        Evento even= new Evento(LocalDateTime.now(),"Calamaro");
        Evento tu= new Evento(LocalDateTime.now(),"coldplay");
        ZonaComun centro = new ZonaComun("centro especial");
        ZonaComun patio = new ZonaComun("Patio Central");
        Escenario es=new Escenario( "Escenario Norte", 2);
        es.addEvento(even);
        //System.out.println(estand);
        es.addEvento(tu);
        System.out.println(patio.equals(centro));
        System.out.println(patio);
        System.out.println("\n");
        System.out.println(es.getEventos());
    }

}
