package Controlador.Logica;

import Dominio.Enums.EstadoAcceso;
import Dominio.Personas.*;
import Dominio.Personas.Datos.Acceso;
import Dominio.Zonas.*;
import Dominio.Zonas.Interface.Capado;

import java.time.Duration;
import java.time.LocalDateTime;

public class MovimientosLogica {

    //// Lleva el registro de cuántas personas hay en cada zona
    //private Map<Zona, Integer> ocupacionActual = new HashMap<>(); // zona= clave integer= cantidad de personas

    public static boolean moverPersona(Persona persona, Zona zonaDestino) {
        if (persona.puedeAcceder(zonaDestino)) { // revisa si esta autorizado
            if (!(zonaDestino instanceof Capado Es && Es.getCapacidad() == 0)) {
                zonaDestino.ponePersona();
                persona.getZonaActual().sacaPersona();

                persona.addAcceso(new Acceso(persona.getZonaActual(), LocalDateTime.now(), (int)Duration.between(LocalDateTime.now(),persona.getUltimoAccesoAceptado().getFechahora()).toMinutes() , EstadoAcceso.AUTORIZADO));
                /// zona actual o zonaDestino? rariiii . no entiendo :D

                /// pienso en accesos como un registro que al cambiar de zona, se registra cuanto tiempo estuvo. Tambien estaria bien verlo como
                /// un registro que indica cuanto tiempo estuvo en la zona en la cual se ingresa, pero no seria un cambio de zona, sino mas bien
                /// un registro atemporal de la zona en la que estuvo la persona
                /// nada, compliqueti

                persona.setZonaActual(zonaDestino);
                return true;
            }
        }
        persona.addAcceso(new Acceso(zonaDestino, LocalDateTime.now(), 0, EstadoAcceso.DENEGADO));
        return false;
    }
}
        //int ocupacion = ocupacionActual.getOrDefault(zonaDestino, 0); // devuelve el valor asociado a la clave, si existe en el mapa.
        //boolean tieneLugar = ocupacion < zonaDestino.getCapacidadMaxima(); // revisa que haya lugar

        //EstadoAcceso estado = (autorizado && tieneLugar) ? EstadoAcceso.AUTORIZADO : EstadoAcceso.DENEGADO;

        // Registrar el acceso con hora actual
    //    Acceso acceso = new Acceso(zonaDestino, LocalDateTime.now(), minutos, estado); // se crea el objeto acceso con los datos
    //    persona.getAccesos().add(acceso); // agrega el acceso a la persona
//
    //    // Si fue autorizado, incrementar ocupación
    //    if (estado == EstadoAcceso.AUTORIZADO) {
    //        ocupacionActual.put(zonaDestino, ocupacion + 1);
    //        return true;
    //    }
//
    //    return false;
    //}
//
    //public int getOcupacion(Zona z) {
    //    return ocupacionActual.getOrDefault(z, 0);
    //}
//
    //public Map<Zona, Integer> getMapaOcupacion() {
    //    return ocupacionActual;
    //}


//// en main seria algo como:
//ZonaComun patio = new ZonaComun("Patio Central");
//ZonaRestringida camarines = new ZonaRestringida("Camarines A", 1);
//
//// Crear personas
//Asistente ana = new Asistente("Ana");
//Artista leo = new Artista("Leo");
//
//// Asignar zonas permitidas
//        leo.agregarZonaPermitida(camarines);
//
//// Crear gestor de movimientos
//GestorMovimientos gestor = new GestorMovimientos();
//
//// Simular movimientos
//        System.out.println("Movimientos de Ana:");
//moverYMostrarResultado(gestor, ana, patio, 30);      //debería entrar
//moverYMostrarResultado(gestor, ana, camarines, 15); // no deberia dejarle
//
//public static void moverYMostrarResultado(GestorMovimientos gestor, Persona persona, Zona zona, int minutos) {
//    boolean ok = gestor.moverPersona(persona, zona, minutos);
//    System.out.printf("→ %s intenta entrar a %s... %s\n",
//            persona.getNombre(),
//            zona.getDescripcion(),
//            ok ? "✔ AUTORIZADO" : "✘ DENEGADO");
//}
