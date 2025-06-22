package Inicializador;

import Dominio.Enums.EstadoAcceso;
import Dominio.Personas.*;
import Dominio.Personas.Datos.Acceso;
import Dominio.Zonas.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import javax.xml.crypto.Data;
import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.TreeSet;

public class Serialization {
    public static void createDatos(){

        DataContainer dataContainer = new DataContainer();

        Artista p1 = new Artista("Agustin", "0001");
        Artista p2 = new Artista("Alejo", "0002");
        Persona p3 = new Asistente("Facundo", "0003");
        Persona p4 = new Asistente("Francisco", "0004");
        Comerciante p5 = new Comerciante("Martin", "0005");
        Comerciante p6 = new Comerciante("Julian", "0006");
        Persona p7 = new Staff("Roberto", "0007");
        Persona p8 = new Staff("Pepito", "0008");

        Comerciante c1 = new Comerciante("Marcos", "0009");
        Comerciante c2 = new Comerciante("Matias", "0010");
        Comerciante c3 = new Comerciante("Fidel", "0011");
        Comerciante c4 = new Comerciante("Ivan", "0012");

        Zona z1 = new ZonaComun("ZC001", "Zona de patio de comidas", 20);
        Zona z2 = new ZonaComun("ZC002", "Zona de baños", 11);
        Zona z3 = new ZonaRestringida("ZR003", "Camarines", 12, 1);
        Zona z4 = new ZonaRestringida("ZR004", "Sala de sonido", 203, 200);
        Zona z5 = new Stand("ZS005", "Stand de Comida", 13, 200, z1, p5);
        Zona z6 = new Stand("ZS006", "Stand de refresto", 1, 200, z2, p6);
        Zona z7 = new Escenario("ZE007", "Escenario N1", 27, 100);
        Zona z8 = new Escenario("ZE008", "Escenario N2", 0, 200);

        Acceso acceso = new Acceso(z1, LocalDateTime.now(), 100, EstadoAcceso.AUTORIZADO);

        ((Escenario) z7).addEvento(LocalDateTime.of(2025, 10, 7, 22, 00), p1);
        ((Escenario) z7).addEvento(LocalDateTime.of(2025, 11, 2, 15, 00), p2);
        ((Escenario) z7).addEvento(LocalDateTime.of(2025, 12, 24, 20, 00), p2);
        ((Escenario) z8).addEvento(LocalDateTime.of(2025, 6, 30, 10, 00), p1);
        ((Escenario) z8).addEvento(LocalDateTime.of(2025, 7, 2, 12, 00), p1);
        ((Escenario) z8).addEvento(LocalDateTime.of(2025, 2, 16, 15, 00), p2);

        //añadir Stand a cada comerciante, y Escenario a cada Artista
        c1.setStand((Stand) z5);
        c2.setStand((Stand) z6);
        c3.setStand((Stand) z5);
        c4.setStand((Stand) z6);

        p1.setEscenario((Escenario) z7);
        p2.setEscenario((Escenario) z8);

        p5.setStand((Stand) z5);
        p6.setStand((Stand) z6);

        ((Stand) z5).agregarEmpleado(c1);
        ((Stand) z5).agregarEmpleado(c2);
        ((Stand) z6).agregarEmpleado(c3);
        ((Stand) z6).agregarEmpleado(c4);

        p1.addAcceso(acceso);
        p2.addAcceso(acceso);
        p3.addAcceso(acceso);
        p4.addAcceso(acceso);
        p5.addAcceso(acceso);
        p6.addAcceso(acceso);
        p7.addAcceso(acceso);
        p8.addAcceso(acceso);
        c1.addAcceso(acceso);
        c2.addAcceso(acceso);
        c3.addAcceso(acceso);
        c4.addAcceso(acceso);

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
        c1.addZona(z1);
        c1.addZona(z2);
        c2.addZona(z3);
        c2.addZona(z4);
        c3.addZona(z5);
        c3.addZona(z6);
        c4.addZona(z7);
        c4.addZona(z8);


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
        c1.setZonaActual(z6);
        z6.ponePersona();
        c2.setZonaActual(z2);
        z2.ponePersona();
        c3.setZonaActual(z8);
        z8.ponePersona();
        c4.setZonaActual(z1);
        z1.ponePersona();


        dataContainer.addPersona(p1);
        dataContainer.addPersona(p2);
        dataContainer.addPersona(p3);
        dataContainer.addPersona(p4);
        dataContainer.addPersona(p5);
        dataContainer.addPersona(p6);
        dataContainer.addPersona(p7);
        dataContainer.addPersona(p8);
        dataContainer.addPersona(c1);
        dataContainer.addPersona(c2);
        dataContainer.addPersona(c3);
        dataContainer.addPersona(c4);

        dataContainer.addZona(z1);
        dataContainer.addZona(z2);
        dataContainer.addZona(z3);
        dataContainer.addZona(z4);
        dataContainer.addZona(z5);
        dataContainer.addZona(z6);
        dataContainer.addZona(z7);
        dataContainer.addZona(z8);

        XmlMapper mapper = new XmlMapper();

        // Habilitar el formato bonito (pretty print)
        mapper.enable(com.fasterxml.jackson.databind.SerializationFeature.INDENT_OUTPUT);
        mapper.setSerializationInclusion(JsonInclude.Include.ALWAYS); // guarda los campos null
        try
        {
            mapper.writeValue(new File("datosFestival.xml"), dataContainer);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
