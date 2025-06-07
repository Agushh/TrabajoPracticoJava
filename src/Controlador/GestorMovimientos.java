/*package servicios;

import modelo.acceso.Acceso;
import modelo.enums.EstadoAcceso;
import modelo.personas.Artista;
import modelo.personas.Asistente;
import modelo.personas.Persona;
import modelo.zonas.Zona;
import modelo.zonas.ZonaComun;
import modelo.zonas.ZonaRestringida;

import java.time.LocalDateTime;
import java.util.*;

public class GestorMovimientos {

    // Lleva el registro de cuántas personas hay en cada zona
    private Map<Zona, Integer> ocupacionActual = new HashMap<>(); // zona= clave integer= cantidad de personas

    public boolean moverPersona(Persona persona, Zona zonaDestino, int minutos) {
        boolean autorizado = persona.puedeAcceder(zonaDestino); // revisa si esta autorizado 
        int ocupacion = ocupacionActual.getOrDefault(zonaDestino, 0); // devuelve el valor asociado a la clave, si existe en el mapa.
        boolean tieneLugar = ocupacion < zonaDestino.getCapacidadMaxima(); // revisa que haya lugar 

        EstadoAcceso estado = (autorizado && tieneLugar) ? EstadoAcceso.AUTORIZADO : EstadoAcceso.DENEGADO;

        // Registrar el acceso con hora actual
        Acceso acceso = new Acceso(zonaDestino, LocalDateTime.now(), minutos, estado); // se crea el objeto acceso con los datos
        persona.getAccesos().add(acceso); // agrega el acceso a la persona

        // Si fue autorizado, incrementar ocupación
        if (estado == EstadoAcceso.AUTORIZADO) {
            ocupacionActual.put(zonaDestino, ocupacion + 1);
            return true;
        }

        return false;
    }

    public int getOcupacion(Zona z) {
        return ocupacionActual.getOrDefault(z, 0);
    }

    public Map<Zona, Integer> getMapaOcupacion() {
        return ocupacionActual;
    }
}

// en main seria algo como: 
        ZonaComun patio = new ZonaComun("Patio Central");
        ZonaRestringida camarines = new ZonaRestringida("Camarines A", 1);

        // Crear personas
        Asistente ana = new Asistente("Ana");
        Artista leo = new Artista("Leo");

        // Asignar zonas permitidas
        leo.agregarZonaPermitida(camarines);

        // Crear gestor de movimientos
        GestorMovimientos gestor = new GestorMovimientos();

        // Simular movimientos
        System.out.println("Movimientos de Ana:");
        moverYMostrarResultado(gestor, ana, patio, 30);      //debería entrar
        moverYMostrarResultado(gestor, ana, camarines, 15); // no deberia dejarle

public static void moverYMostrarResultado(GestorMovimientos gestor, Persona persona, Zona zona, int minutos) {
        boolean ok = gestor.moverPersona(persona, zona, minutos);
        System.out.printf("→ %s intenta entrar a %s... %s\n",
                persona.getNombre(),
                zona.getDescripcion(),
                ok ? "✔ AUTORIZADO" : "✘ DENEGADO");
    }
*/