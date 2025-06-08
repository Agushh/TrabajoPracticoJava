package Dominio.Factory;

import Dominio.Enums.TipoPers;
import Dominio.Personas.*;
import Dominio.Zonas.Zona;

public class PersonaFactory {

    public static Persona crear(TipoPers tipo, String nombre, Zona zonaActual) throws IllegalArgumentException {
        return switch (tipo) {
            case STAFF -> new Staff(nombre, zonaActual);
            case COMERCIANTE -> new Comerciante(nombre, zonaActual);
            case ASISTENTE -> new Asistente(nombre, zonaActual);
            case ARTISTA -> new Artista(nombre, zonaActual);
            default -> throw new IllegalArgumentException("Tipo de persona inválido: " + tipo);
        };
    }
}
