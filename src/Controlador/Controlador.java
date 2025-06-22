package Controlador;

import Dominio.Enums.EstadoAcceso;
import Dominio.Exceptions.*;
import Dominio.Personas.*;
import Dominio.Personas.Datos.Acceso;
import Dominio.Zonas.Datos.Evento;
import Dominio.Zonas.Escenario;
import Dominio.Zonas.Interface.Capado;
import Dominio.Zonas.Zona;
import Dominio.Zonas.Stand;
import Dominio.Zonas.ZonaRestringida;
import Inicializador.DataContainer;
import Inicializador.Serialization;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.TreeMap;

public class Controlador {

    private final TreeMap<String, Zona> zonas= new TreeMap<String, Zona>();
    private final TreeMap<String, Persona> personas= new TreeMap<String, Persona>();
    private final TreeMap<String, Stand> stands = new TreeMap<String, Stand>();

    //Aplico patron de diseño Singleton (Te permite generar una sola instancia del objeto)
    private static final Controlador controlador = new Controlador();

    //private final PersonaLogica personaLogica = PersonaLogica.getInstancia(this.personas);
    //private final ZonaLogica zonaLogica = ZonaLogica.getInstancia(this.zonas);

    //Constructor privado para la aplicacion de Singleton
    private Controlador(){

    }

    //Get para obtener la unica instancia que existe
    public static Controlador getControlador() {return controlador;}

    //------------------- Getters ------------------

    public TreeMap<String, Zona> getZonas() {
        return zonas;
    }

    public TreeMap<String, Persona> getPersonas() {
        return personas;
    }

    public TreeMap<String, Stand> getStands() {
        return stands;
    }

    public Zona getZonas(String codZona) throws IllegalArgumentException {
        Zona z = zonas.get(codZona);
        if (z == null) {
            throw new IllegalArgumentException("No existe una zona con código: " + codZona);
        }
        return z;
    }

    public Persona getPersona(String codPersona) throws IllegalArgumentException {
        Persona p = personas.get(codPersona);
        if (p == null) {
            throw new IllegalArgumentException("No existe una persona con código: " + codPersona);
        }
        return p;
    }

    //------------------- Adds -----------------------

    public void addZona(Zona z) throws IllegalArgumentException {
        try{
            zonas.put(z.getId(),z);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException();
        }
    }

    public void addPersona(Persona p) throws IllegalArgumentException {
        try{
            personas.put(p.getId(),p);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException();// todo verificar excepcion y agregar mensaje
        }
    }

    //--------------------- Mover Persona ------------------

    public static void moverPersona(Persona persona, Zona zonaDestino) throws AccesoDenegadoException, ZonaLlenaException, ZonaEsLaActualException, NullPointerException
    {
        if (persona == null) {
            throw new NullPointerException("Persona nula \n Cargar datos!");
        }
        if (!persona.puedeAcceder(zonaDestino)) {
            persona.addAcceso(new Acceso(zonaDestino, LocalDateTime.now(), 0, EstadoAcceso.DENEGADO));
            throw new AccesoDenegadoException();
        }
        if (zonaDestino instanceof Capado capado && capado.getCapacidad() == 0) {
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

    public void mostrarZonas(){
        zonas.forEach((id,zona)->{
            System.out.println(zona.toString());
        });
    }

    public void mostrarPersonas(){
        personas.forEach((id,persona)->{
            System.out.println(persona.toString());
        });
    }

    //-------------- Carga Datos De Archivo --------------

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
                ExceptionLog.append("Zona " + z.getId() + "SIN DESCRIPCION" + " --> CORREGIDO <--\n");
                z.setDescripcion("ZONA-SIN-DESCRIPCION");
            }
            if(z instanceof Escenario escenario && escenario.getCapacidad() < 0)
            {
                ExceptionLog.append("Escenario " + escenario.getId() + " con capacidad menor a 0" + " --> CORREGIDO <--\n");
                escenario.setConcurrencia( escenario.getCapacidadMaxima());
                for(Evento evento : escenario.getEventos())
                {
                    if(evento.getArtista() == null)
                    {
                        ExceptionLog.append("Escenario " + escenario.getId() + "Evento fecha :" +evento.getFecha() + "No tiene artista."  + "\n");
                    }
                }
            }
            if(z instanceof ZonaRestringida zonaRestringida && zonaRestringida.getCapacidad() < 0)
            {
                ExceptionLog.append("Zona Restringida " + zonaRestringida.getId() + " con capacidad menor a 0" + " --> CORREGIDO <--\n");
                zonaRestringida.setConcurrencia( zonaRestringida.getCapacidadMaxima());
            }
            if(z instanceof Stand stand )
            {
                if(stand.getUbicacion() == null)
                {
                    ExceptionLog.append("Stand " + stand.getId() + " sin ubicacion determinada" + "\n");
                }
                if(stand.getResponsable() == null)
                {
                    ExceptionLog.append("Stand " + stand.getId() + " sin Comerciante Responsable" + "\n");
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
                ExceptionLog.append("Persona" + p.getId() + "sin nombre" + " --> CORREGIDO <--\n");
                p.setNombre("PERSONA-SIN-NOMBRE");
            }
            if(p.getZonaActual() == null)
            {
                ExceptionLog.append("Persona "+ p.getId() + "Sin Zona Actual" + "\n");
            }

            if(p instanceof Artista artista && artista.getEscenario() == null)
                ExceptionLog.append("Artista "+ artista.getId() + " Sin Escenario"+ "\n");
            if(p instanceof Comerciante comerciante && comerciante.getStand() == null)
                ExceptionLog.append("Comerciante "+ comerciante.getId() + " Sin Stand asignado"+ "\n");

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
                    System.out.println("HOLA");
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
}