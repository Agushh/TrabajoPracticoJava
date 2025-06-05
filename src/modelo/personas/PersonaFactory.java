package modelo.personas;

import modelo.enums.TipoPers;
public class PersonaFactory {

    public static Persona crear(TipoPers tipo, String nombre) throws IllegalArgumentException {
        Persona p;

        switch(tipo){
            case STAFF:
                p = new Staff(nombre);
                break;
            case COMERCIANTE:
                p = new Comerciante(nombre);
                break;
            case ASISTENTE:
                p = new Asistente(nombre);
                break;
            case ARTISTA:
                p = new Artista(nombre);
                break;
            default:
                throw new IllegalArgumentException("Tipo de persona inválido: " + tipo);
        }

        return p;
    }
}
