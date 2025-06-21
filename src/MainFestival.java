import Controlador.Controlador;
import Dominio.Enums.EstadoAcceso;
import Dominio.Enums.TipoPers;
import Dominio.Enums.TipoZona;
import Dominio.Personas.Datos.Acceso;
import Dominio.Zonas.*;
import Dominio.Zonas.Datos.Evento;
import Inicializador.Serialization;
import Dominio.Personas.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class MainFestival {
    public static void main(String[] args){

        Serialization.createDatos();
        ArrayList<Persona> personas = Serialization.leePersonas();
        ArrayList<Zona> zonas = Serialization.leeZonas();
        System.out.println(personas.get(0).getNombre());
        System.out.println(zonas.get(0).getDescripcion());

        //Genero instancia de controlador
        Controlador controlador = Controlador.getControlador();

        //Stand stand = (Stand) controlador.zona().add(TipoZona.STAND, "Expresso Sabores", 10, zc1, c1);
        //stand.agregarEmpleado((Comerciante) controlador.persona().add(TipoPers.COMERCIANTE, "Mauro", zc1));
        //stand.agregarEmpleado((Comerciante) controlador.persona().add(TipoPers.COMERCIANTE, "Lucas", zc1));

        //((Stand) controlador.zona().add(TipoZona.STAND, "Parliamo Pizza", 40, zc1, c1)).agregarEmpleado((Comerciante) controlador.persona().add(TipoPers.COMERCIANTE, "Pilar", zc1)); //creo una zona y un empleado para probar
        ////Muestro personas
        //controlador
        //        .persona()
        //        .mostrarTodas();

        //// Obtener una persona del sistema
        //Persona p1 = controlador.persona().getPersona("COM-0000"); // ID generado para Alejo

        //// Crea algunos accesos para ver como quedan
        //p1.addAcceso(new Acceso(zc1, LocalDateTime.now(), 10, EstadoAcceso.AUTORIZADO));
        //p1.addAcceso(new Acceso(zc1, LocalDateTime.now().plusMinutes(30), 5, EstadoAcceso.DENEGADO));
        //p1.addAcceso(new Acceso(zc1, LocalDateTime.now().plusHours(1), 20, EstadoAcceso.AUTORIZADO));
        ////creacion de ventana en Swing.
        ////CustomJFrame es una clase heredada de JFrame para que los parametros del constructor construyan la ventana a la hora de invocarlo.
        //CustomJFrame window = new CustomJFrame("TPjava", 300, 300, 100, 100, true,controlador);//inicia la gui

        //// Inicializamos los datos desde JSON
        //CargaInicial.inicializarDesdeJson();

        //// Obtenemos la instancia única del controlador
        //Controlador c = Controlador.getControlador();

        //// Recorremos y mostramos las personas cargadas
        //System.out.println("Personas cargadas:");
        //for (Persona p : c.getPersonas().values()) {
        //    System.out.println("- " + p.getClass().getSimpleName() + ": " + p.getNombre() + " (ID: " + p.getId() + ")");
        //}

        //// Recorremos y mostramos las zonas cargadas
        //System.out.println("\nZonas cargadas:");
        //for (Zona z : c.getZonas().values()) {
        //    System.out.println("- " + z.getClass().getSimpleName() + ": " + z.getCod() + " - " + z.getDescripcion());
        //}

        //// Mostrar cantidad total
        //System.out.println("\nTotal personas: " + c.getPersonas().size());
        //System.out.println("Total zonas: " + c.getZonas().size());


        // */

    }

}
