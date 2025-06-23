import Controlador.Controlador;
import Vista.CustomJFrame;

import static Inicializador.Serialization.createDatos;

/**
 * Programa el cual permite manejar personas de un festival teniendo un seguimiento de todos los accesos de cada persona y pudiendo generar reportes de zonas, stands y una persona especifica.
 * @author Facundo Fuimara
 * @author Alejo Milani
 * @author Agustin Zalazar
 * @author Franco Dimeglio
 * @version 1.0
 */
public class MainFestival {
    public static void main(String[] args){
        Controlador controlador = Controlador.getControlador();
        new CustomJFrame();
    }
}
