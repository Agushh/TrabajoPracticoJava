package modelo.personas;

public class PersonaFactory {

    public static Persona crear(String tipo, String nombre) throws IllegalArgumentException {
        Persona p;

        switch(tipo){
            case "staff":
                p = new Staff(nombre);
                break;
            case "comerciante":
                p = new Comerciante(nombre);
                break;
            case "asistente":
                p = new Asistente(nombre);
                break;
            case "artista":
                p = new Artista(nombre);
                break;
            default:
                throw new IllegalArgumentException("Tipo de persona inválido: " + tipo);
        }

        return p;
    }
}
