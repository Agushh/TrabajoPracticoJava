package Controlador;

import Dominio.Enums.EstadoAcceso;
import Dominio.Exceptions.*;
import Dominio.Personas.*;
import Dominio.Personas.Datos.Acceso;
import Dominio.Zonas.Datos.Evento;
import Dominio.Zonas.Escenario;
import Dominio.Zonas.Interface.Limitado;
import Dominio.Zonas.Zona;
import Dominio.Zonas.Stand;
import Dominio.Zonas.ZonaRestringida;
import Inicializador.DataContainer;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.util.*;
import java.time.Duration;
import java.time.LocalDateTime;

/**
 * El controlador contiene la lista de personas, zonas, stands y contiene una instancia Singleton del controlador. Permite realizar movimientos y cargar datos.
 * @see Persona
 * @see Zona
 * @see Stand
 */
public class Controlador {

    /**
     * Lista de zonas.
     */
    private final TreeMap<String, Zona> zonas= new TreeMap<>();

    /**
     * Lista de personas.
     */
    private final TreeMap<String, Persona> personas= new TreeMap<>();

    /**
     * Lista de stands.
     */
    private final TreeMap<String, Stand> stands = new TreeMap<>();

    /**
     * Instancia singleton del controlador.
     */
    private static final Controlador controlador = new Controlador();

    /**
     * Constructor privado para poder aplicar singleton.
     */
    private Controlador(){
    }

    /**
     * Retorna la unica instancia del controlador.
     * @return Instancia del controlador.
     */
    public static Controlador getControlador() {return controlador;}

    //------------------- Getters ------------------

    /**
     * Retorna la lista de zonas.
     * @return Lista de zonas.
     */
    public TreeMap<String, Zona> getZonas() {
        return zonas;
    }

    /**
     * Retorna la lista de personas.
     * @return Lista de personas.
     */
    public TreeMap<String, Persona> getPersonas() {
        return personas;
    }

    /**
     * Retorna la lista de stands.
     * @return Lista de stands.
     */
    public TreeMap<String, Stand> getStands() {
        return stands;
    }


    /**
     * Busca el codigo de una Zona y la retorna.
     * @param codZona Codigo de una zona.
     * @return Una zona.
     * @throws IllegalArgumentException El codigo de la zona no se encontro.
     */
    @Deprecated
    public Zona getZonas(String codZona) throws IllegalArgumentException {
        Zona z = zonas.get(codZona);
        if (z == null) {
            throw new IllegalArgumentException("No existe una zona con código: " + codZona);
        }
        return z;
    }

    /**
     * Busca el codigo de una Persona y la retorna.
     * @param codPersona Codigo de una persona.
     * @return Una persona.
     * @throws IllegalArgumentException El codigo de la persona no se encontro.
     */
    @Deprecated
    public Persona getPersona(String codPersona) throws IllegalArgumentException {
        Persona p = personas.get(codPersona);
        if (p == null) {
            throw new IllegalArgumentException("No existe una persona con código: " + codPersona);
        }
        return p;
    }

    //------------------- Adds -----------------------

    /**
     * Permite agregar una zona a la lista.
     * @param z Zona a agregar.
     * @throws IllegalArgumentException No es una zona correcta.
     */
    @Deprecated
    public void addZona(Zona z) throws IllegalArgumentException {
        try{
            zonas.put(z.getId(),z);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException();
        }
    }


    /**
     * Permite agregar una persona a la lista.
     * @param p Persona a agregar.
     * @throws IllegalArgumentException No es una persona correcta.
     */
    @Deprecated
    public void addPersona(Persona p) throws IllegalArgumentException {
        try{
            personas.put(p.getId(),p);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException();// todo verificar excepcion y agregar mensaje
        }
    }

    //--------------------- Mover Persona ------------------

    /**
     * Permite mover a una persona de una zona a otra.
     * @param persona Persona a mover.
     * @param zonaDestino Zona destino de la persona.
     * @throws AccesoDenegadoException La persona no tiene permiso.
     * @throws ZonaLlenaException La zona esta llena.
     * @throws ZonaEsLaActualException La persona ya se encuentra en esa zona.
     * @throws NullPointerException La persona es nula.
     */
    public static void moverPersona(Persona persona, Zona zonaDestino) throws AccesoDenegadoException, ZonaLlenaException, ZonaEsLaActualException, NullPointerException
    {
        if (persona == null) {
            throw new NullPointerException("Persona nula \n Cargar datos!");
        }
        if (!persona.puedeAcceder(zonaDestino)) {
            persona.addAcceso(new Acceso(zonaDestino, LocalDateTime.now(), 0, EstadoAcceso.DENEGADO));
            throw new AccesoDenegadoException();
        }
        if (zonaDestino instanceof Limitado limitado && limitado.getCapacidad() == 0) {
            persona.addAcceso(new Acceso(zonaDestino, LocalDateTime.now(), 0, EstadoAcceso.DENEGADO));
            throw new ZonaLlenaException();
        }
        if (persona.getZonaActual()==zonaDestino){
            throw  new ZonaEsLaActualException();
        }
        zonaDestino.ponePersona();
        persona.getZonaActual().sacaPersona();
        persona.addAcceso(new Acceso(persona.getZonaActual(), LocalDateTime.now(), (int) Duration.between(LocalDateTime.parse(persona.getUltimoAccesoAceptado().getFecha()), LocalDateTime.now()).toMinutes() , EstadoAcceso.AUTORIZADO));
        persona.setZonaActual(zonaDestino);

    }

    //--------------------- Mostrar Todas ------------------
    // Metodos para desarollo!!!

    /**
     * Permite hacer un print de todas las zonas.
     */
    @Deprecated
    public void mostrarZonas(){
        for(Zona zona : new ArrayList<>(zonas.values()))
            System.out.println(zona.toString());
    }

    /**
     * Permite hacer un print de todas las personas.
     */
    @Deprecated
    public void mostrarPersonas(){
        for(Persona persona : new ArrayList<>(personas.values()))
            System.out.println(persona.toString());
    }

    //-------------- Carga Datos De Archivo --------------

    /**
     * Permite cargar los datos de un archivo datosFestival.xml que se encuentra en la raiz del proyecto. Verificando la correcta composicion de los mismos.
     * @throws DatosIncorrectosException Los datos tienen errores logicos.
     * @throws DeserializationException Los datos no tienen la estructura adecuada o estan rotos.
     */
    public void cargaDeDatos() throws DatosIncorrectosException, DeserializationException {
        DataContainer dataContainer;

        StringBuilder ExceptionLog = new StringBuilder();

        try
        {
            XmlMapper mapper = new XmlMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            dataContainer = mapper.readValue(new File("datosFestival.xml"), DataContainer.class);

        } catch (Exception e) {
            throw new DeserializationException("Fallo en la lectura de datos sobre el archivo : \n" + e);
        }

        //Guarda los datos de dataContainer en dos listas locales
        ArrayList<Zona> listZonas = dataContainer.getZonas();
        ArrayList<Persona> listPersonas = dataContainer.getPersonas();


        //Validacion de datos y carga en zonasPorId.

        Map<String, Zona> zonasPorId = new HashMap<>();
        int contZona = 0;
        for (Zona z : listZonas) {
            if(z.getId().isEmpty()) {
                ExceptionLog.append("Se encontro zona SIN ID"  + " --> CORREGIDO <--\n");
                z.setId("ZONA-SIN-ID-" + ++contZona);
            }
            if(z.getDescripcion().isEmpty())
            {
                ExceptionLog.append("Zona ").append(z.getId()).append("SIN DESCRIPCION").append(" --> CORREGIDO <--\n");
                z.setDescripcion("ZONA-SIN-DESCRIPCION");
            }
            if(z instanceof Escenario escenario && escenario.getCapacidad() < 0)
            {
                ExceptionLog.append("Escenario ").append(escenario.getId()).append(" con capacidad menor a 0").append(" --> CORREGIDO <--\n");
                escenario.setConcurrencia( escenario.getCapacidadMaxima());
                for(Evento evento : escenario.getEventos())
                {
                    if(evento.getArtista() == null)
                    {
                        ExceptionLog.append("Escenario ").append(escenario.getId()).append("Evento fecha :").append(evento.getFecha()).append("No tiene artista.").append("\n");
                    }
                }
            }
            if(z instanceof ZonaRestringida zonaRestringida && zonaRestringida.getCapacidad() < 0)
            {
                ExceptionLog.append("Zona Restringida ").append(zonaRestringida.getId()).append(" con capacidad menor a 0").append(" --> CORREGIDO <--\n");
                zonaRestringida.setConcurrencia( zonaRestringida.getCapacidadMaxima());
            }
            if(z instanceof Stand stand )
            {
                if(stand.getUbicacion() == null)
                {
                    ExceptionLog.append("Stand ").append(stand.getId()).append(" sin ubicacion determinada").append("\n");
                }
                if(stand.getResponsable() == null)
                {
                    ExceptionLog.append("Stand ").append(stand.getId()).append(" sin Comerciante Responsable").append("\n");
                }
            }
            zonasPorId.put(z.getId(), z);
        }

        Map<String, Persona> personasPorId = new HashMap<>();
        int contPersonasCode = 0;
        for (Persona p : listPersonas) {

            if(p.getId().isEmpty())
            {
                ExceptionLog.append("Persona sin id" + " --> CORREGIDO <--\n");
                p.setId("PERSONA-SIN-ID-" + ++contPersonasCode);
            }
            if(p.getNombre().isEmpty())
            {
                ExceptionLog.append("Persona").append(p.getId()).append("sin nombre").append(" --> CORREGIDO <--\n");
                p.setNombre("PERSONA-SIN-NOMBRE");
            }
            if(p.getZonaActual() == null)
            {
                ExceptionLog.append("Persona ").append(p.getId()).append("Sin Zona Actual").append("\n");
            }

            if(p instanceof Artista artista && artista.getEscenario() == null)
                ExceptionLog.append("Artista ").append(artista.getId()).append(" Sin Escenario").append("\n");
            if(p instanceof Comerciante comerciante && comerciante.getStand() == null)
                ExceptionLog.append("Comerciante ").append(comerciante.getId()).append(" Sin Stand asignado").append("\n");

            personasPorId.put(p.getId(), p);
        }

        // Corrige zonas dentro de personas
        for (Persona persona : listPersonas) {
            if(persona instanceof Artista artista)
            {
                if(artista.getEscenario() != null)
                    if(zonasPorId.get(artista.getEscenario().getId()) instanceof Escenario escenario)
                        artista.setEscenario(escenario);
            }
            else if(persona instanceof Comerciante comerciante)
            {
                if(comerciante.getStand() != null)
                    if(zonasPorId.get(comerciante.getStand().getId()) instanceof Stand stand)
                        comerciante.setStand(stand);
            }

            if (persona.getZonaActual() != null) {
                Zona zonaReal = zonasPorId.get(persona.getZonaActual().getId());
                if (zonaReal != null) {
                    persona.setZonaActual(zonaReal);
                }
            }
            if(persona.getZonasPermitidas() != null)
            {
                TreeSet<Zona> zonasPermitidas = new TreeSet<>();
                for(Zona z : persona.getZonasPermitidas()){
                    Zona temp = zonasPorId.get(z.getId());
                    if(temp != null)
                        zonasPermitidas.add(temp);
                }
                persona.setZonasPermitidas(zonasPermitidas);
            }
            personas.put(persona.getId(), persona);
        }

        for(Zona zona : listZonas)
        {

            if(zona instanceof Stand stand)
            {
                Zona zonaReal = zonasPorId.get(stand.getUbicacion().getId());
                if(zonaReal != null) stand.setUbicacion(zonaReal);

                Persona comercianteResponsable = personasPorId.get(stand.getResponsable().getId()); //Se guarda en tipo persona ya que lee de Un Map de personas, y luego se verifica que sea un comerciante mediante InstaceOf
                if(comercianteResponsable instanceof Comerciante c) stand.setResponsable(c);
                stands.put(stand.getResponsable().getNombre(), stand);
            }
            if(zona instanceof Escenario escenario)
            {
                for(Evento evento : escenario.getEventos())
                {
                    Persona artistaReal = personasPorId.get(evento.getArtista().toString());
                    if(artistaReal instanceof Artista artista)
                        evento.setArtista(artista);
                }
            }
            zonas.put(zona.getId(), zona);
        }
        if(!ExceptionLog.isEmpty()) throw new DatosIncorrectosException(ExceptionLog.toString());
    }

    /**
     * Guarda los datos en datosFestival.xml.
     */
    public void guardarDatos()
    {
        DataContainer dataContainer = new DataContainer(new ArrayList<>(personas.values()), new ArrayList<>(zonas.values()));
        XmlMapper mapper = new XmlMapper();

        // Habilitar el formato bonito (pretty print)
        mapper.enable(com.fasterxml.jackson.databind.SerializationFeature.INDENT_OUTPUT);

        try
        {
            mapper.writeValue(new File("datosFestival.xml"), dataContainer);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}