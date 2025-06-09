import Controlador.Controlador;
import Dominio.Enums.TipoPers;
import Dominio.Enums.TipoZona;
import Dominio.Factory.ZonaFactory;
import Dominio.Zonas.*;
import Dominio.Zonas.Datos.Evento;
import Vista.CustomJFrame;
// import Inicializador.CargaInicial;
import Dominio.Personas.*;
import java.time.LocalDateTime;

public class MainFestival {
    public static void main(String[] args){
        //Genero instancia de controlador
        Controlador controlador = Controlador.getControlador();

        //Agrego zona
        controlador
                .zona()
                .add(TipoZona.ZONA_COMUN, "Zona Comun N1");

        //Muestor zonas
        controlador
                .zona()
                .mostrarTodas();

        //Obtengo zona
        Zona zc1 = controlador.zona().getZona("ZCO-0000");

        //Intento agregar personas, si el tipo no coincide muestro el error
        try {
            controlador
                    .persona()
                    .add(TipoPers.COMERCIANTE, "Alejo", zc1);
            controlador
                    .persona()
                    .add(TipoPers.STAFF, "Facu", zc1);
        }catch (Exception e){ // todo ESPECIFICAR MEJOR LAS EXCEPCIONES
            System.out.println(e);
        }

        //Muestro personas
        controlador
                .persona()
                .mostrarTodas();

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


/*
        // Inicializamos los datos desde JSON
        CargaInicial.inicializarDesdeJson();

        // Obtenemos la instancia única del controlador
        Controlador c = Controlador.getControlador();

        // Recorremos y mostramos las personas cargadas
        System.out.println("Personas cargadas:");
        for (Persona p : c.getPersonas().values()) {
            System.out.println("- " + p.getClass().getSimpleName() + ": " + p.getNombre() + " (ID: " + p.getId() + ")");
        }

        // Recorremos y mostramos las zonas cargadas
        System.out.println("\nZonas cargadas:");
        for (Zona z : c.getZonas().values()) {
            System.out.println("- " + z.getClass().getSimpleName() + ": " + z.getCod() + " - " + z.getDescripcion());
        }

        // Mostrar cantidad total
        System.out.println("\nTotal personas: " + c.getPersonas().size());
        System.out.println("Total zonas: " + c.getZonas().size());
*/

    }

}
