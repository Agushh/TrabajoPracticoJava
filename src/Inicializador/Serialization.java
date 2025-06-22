package Inicializador;

import Dominio.Enums.EstadoAcceso;
import Dominio.Personas.*;
import Dominio.Personas.Datos.Acceso;
import Dominio.Zonas.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.TreeSet;

public class Serialization {


    public static void createDatos(){

        PersonasLista personasLista = new PersonasLista();
        ZonasLista zonasLista = new ZonasLista();

        Artista p1 = new Artista("Agustin", "0001", new TreeSet<Zona>(), new ArrayList<Acceso>());
        Artista p2 = new Artista("Alejo", "0002", new TreeSet<Zona>(), new ArrayList<Acceso>());
        Persona p3 = new Asistente("Facundo", "0003", new TreeSet<Zona>(), new ArrayList<Acceso>());
        Persona p4 = new Asistente("Francisco", "0004", new TreeSet<Zona>(), new ArrayList<Acceso>());
        Comerciante p5 = new Comerciante("Martin", "0005", new TreeSet<Zona>(), new ArrayList<Acceso>());
        Comerciante p6 = new Comerciante("Julian", "0006", new TreeSet<Zona>(), new ArrayList<Acceso>());
        Persona p7 = new Staff("Roberto", "0007", new TreeSet<Zona>(), new ArrayList<Acceso>());
        Persona p8 = new Staff("Pepito", "0008", new TreeSet<Zona>(), new ArrayList<Acceso>());


        Zona z1 = new ZonaComun("ZC001", "Zona de patio de comidas", 20);
        Zona z2 = new ZonaComun("ZC002", "Zona de baños", 11);
        Zona z3 = new ZonaRestringida("ZR003", "Camarines", 12, 1);
        Zona z4 = new ZonaRestringida("ZR004", "Sala de sonido", 0, 200);
        Zona z5 = new Stand("ZS005", "Stand de Comida", 13, 200, z1, p5, null);
        Zona z6 = new Stand("ZS006", "Stand de refresto", 1, 200, z2, p6, null);
        Zona z7 = new Escenario("ZE007", "Escenario N1", 27, 100, null);
        Zona z8 = new Escenario("ZE008", "Escenario N2", 0, 200, null);


        Acceso acceso = new Acceso(z1, LocalDateTime.now(), 100, EstadoAcceso.AUTORIZADO);

        //añadir Stand a cada comerciante, y Escenario a cada Artista
        p1.setEscenario((Escenario) z7);
        p2.setEscenario((Escenario) z8);

        p5.setStand((Stand) z5);
        p6.setStand((Stand) z6);

        p1.addAcceso(acceso);
        p2.addAcceso(acceso);
        p3.addAcceso(acceso);
        p4.addAcceso(acceso);
        p5.addAcceso(acceso);
        p6.addAcceso(acceso);
        p7.addAcceso(acceso);
        p8.addAcceso(acceso);
        //Carga de listas de ZonasAccesibles
        p1.addZona(z1);
        p1.addZona(z8);
        p2.addZona(z5);
        p2.addZona(z2);
        p3.addZona(z2);
        p3.addZona(z7);
        p4.addZona(z6);
        p4.addZona(z7);
        p5.addZona(z7);
        p5.addZona(z1);
        p6.addZona(z4);
        p6.addZona(z3);
        p7.addZona(z3);
        p7.addZona(z6);
        p8.addZona(z8);
        p8.addZona(z7);

        p1.setZonaActual(z1);
        z1.ponePersona();
        p2.setZonaActual(z5);
        z5.ponePersona();
        p3.setZonaActual(z2);
        z2.ponePersona();
        p4.setZonaActual(z7);
        z7.ponePersona();
        p5.setZonaActual(z1);
        z1.ponePersona();
        p6.setZonaActual(z3);
        z3.ponePersona();
        p7.setZonaActual(z6);
        z6.ponePersona();
        p8.setZonaActual(z7);
        z7.ponePersona();

        personasLista.addPersona(p1);
        personasLista.addPersona(p2);
        personasLista.addPersona(p3);
        personasLista.addPersona(p4);
        personasLista.addPersona(p5);
        personasLista.addPersona(p6);
        personasLista.addPersona(p7);
        personasLista.addPersona(p8);

        zonasLista.addZona(z1);
        zonasLista.addZona(z2);
        zonasLista.addZona(z3);
        zonasLista.addZona(z4);
        zonasLista.addZona(z5);
        zonasLista.addZona(z6);
        zonasLista.addZona(z7);
        zonasLista.addZona(z8);

        XmlMapper mapper = new XmlMapper();

        try
        {
            mapper.writeValue(new File("personas.xml"), personasLista);
            mapper.writeValue(new File("zonas.xml"), zonasLista);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
    public static ArrayList<Persona> leePersonas()
    {
        PersonasLista personas = new PersonasLista();
        try
        {
            XmlMapper mapper = new XmlMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            personas = mapper.readValue(new File("personas.xml"), PersonasLista.class);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return personas.getPersonas();
    }

    public static ArrayList<Zona> leeZonas()
    {
        ZonasLista zonas = new ZonasLista();
        try
        {
            XmlMapper mapper = new XmlMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            zonas = mapper.readValue(new File("zonas.xml"), ZonasLista.class);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return zonas.getZonas();
    }

}
