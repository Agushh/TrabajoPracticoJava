import Controlador.Controlador;
import Vista.CustomJFrame;

import static Inicializador.Serialization.createDatos;

public class MainFestival {
    public static void main(String[] args){
        createDatos(); // todo SACAR DATOS
        Controlador controlador = Controlador.getControlador();
        System.out.println(controlador.getPersona("0008"));
        //creacion de ventana en Swing.
        //CustomJFrame es una clase heredada de JFrame para que los parametros del constructor construyan la ventana a la hora de invocarlo.
        //Inicia la gui   todo Hace falta la variable?? Y los parametros??
        CustomJFrame window = new CustomJFrame("TPjava", 300, 300, 100, 100, true,controlador);
    }
}
