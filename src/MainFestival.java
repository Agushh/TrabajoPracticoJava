import Controlador.Controlador;
import Dominio.Enums.EstadoAcceso;
import Dominio.Enums.TipoPers;
import Dominio.Enums.TipoZona;
import Dominio.Factory.ZonaFactory;
import Dominio.Personas.Datos.Acceso;
import Dominio.Zonas.*;
import Dominio.Zonas.Datos.Evento;
import Vista.CustomJFrame;
 import Inicializador.CargaInicial;
import Dominio.Personas.*;
import java.time.LocalDateTime;

public class MainFestival {
    public static void main(String[] args){
        //Genero instancia de controlador
        Controlador controlador = Controlador.getControlador();

        //Agrego zona
        controlador.zona().add(TipoZona.ZONA_COMUN, "Entrada");
        controlador.zona().add(TipoZona.ZONA_COMUN, "Patio de Comidas");
        controlador.zona().add(TipoZona.ZONA_COMUN, "Baño 1");
        controlador.zona().add(TipoZona.ESCENARIO,"Escenario principal",20);

        
        //Muestor zonas
        controlador
                .zona()
                .mostrarTodas();

        //Obtengo zona
        Zona zc1 = controlador.zona().getZona("ZCO-0000");
        Comerciante c1 = (Comerciante) controlador
                .persona()
                .add(TipoPers.COMERCIANTE, "Alejo", zc1);

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

        controlador.zona().add(TipoZona.STAND,"Expresso Sabores",10,zc1,c1);
        //Muestro personas
        controlador
                .persona()
                .mostrarTodas();

        // Obtener una persona del sistema
        Persona p1 = controlador.persona().getPersona("COM-0000"); // ID generado para Alejo

        // Crea algunos accesos para ver como quedan
        p1.addAcceso(new Acceso(zc1, LocalDateTime.now(), 10, EstadoAcceso.AUTORIZADO));
        p1.addAcceso(new Acceso(zc1, LocalDateTime.now().plusMinutes(30), 5, EstadoAcceso.DENEGADO));
        p1.addAcceso(new Acceso(zc1, LocalDateTime.now().plusHours(1), 20, EstadoAcceso.AUTORIZADO));
        //creacion de ventana en Swing.
        //CustomJFrame es una clase heredada de JFrame para que los parametros del constructor construyan la ventana a la hora de invocarlo.
        CustomJFrame window = new CustomJFrame("TPjava", 300, 300, 100, 100, true,controlador);//inicia la gui

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


    }

}
