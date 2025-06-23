package Inicializador;

import Dominio.Enums.EstadoAcceso;
import Dominio.Personas.*;
import Dominio.Personas.Datos.Acceso;
import Dominio.Zonas.*;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.time.LocalDateTime;

public class Serialization {
    public static void createDatos(){

        DataContainer dataContainer = new DataContainer();

        Artista ar1 = new Artista("Agustin", "0001");
        Artista ar2 = new Artista("Alejo", "0002");

        Persona as1 = new Asistente("Facundo", "0003");
        Persona as2 = new Asistente("Francisco", "0004");

        Persona s1 = new Staff("Roberto", "0007");
        Persona s2 = new Staff("Pepito", "0008");

        Comerciante c1 = new Comerciante("Martin", "0005");
        Comerciante c2 = new Comerciante("Julian", "0006");
        Comerciante c3 = new Comerciante("Fidel", "0011");
        Comerciante c4 = new Comerciante("Ivan", "0012");
        Comerciante c5 = new Comerciante("Marcos", "0009");
        Comerciante c6 = new Comerciante("Matias", "0010");

        Zona z1 = new ZonaComun("ZC001", "Zona de patio de comidas");
        Zona z2 = new ZonaComun("ZC002", "Zona de baños");
        Zona z3 = new ZonaRestringida("ZR003", "Camarines",  1);
        Zona z4 = new ZonaRestringida("ZR004", "Sala de sonido",  200);
        Stand z5 = new Stand("ZS005", "Stand de Comida", 200, z1, c1);
        Stand z6 = new Stand("ZS006", "Stand de refresto",  200, z2, c2);
        Escenario z7 = new Escenario("ZE007", "Escenario N1",  100);
        Escenario z8 = new Escenario("ZE008", "Escenario N2",  200);

        Acceso acceso = new Acceso(z1, LocalDateTime.now(), 100, EstadoAcceso.AUTORIZADO);

        z7.addEvento(LocalDateTime.of(2025, 10, 7, 22, 0), ar1);
        z7.addEvento(LocalDateTime.of(2025, 11, 2, 15, 0), ar2);
        z7.addEvento(LocalDateTime.of(2025, 12, 24, 20, 0), ar2);
        z8.addEvento(LocalDateTime.of(2025, 6, 30, 10, 0), ar1);
        z8.addEvento(LocalDateTime.of(2025, 7, 2, 12, 0), ar1);
        z8.addEvento(LocalDateTime.of(2025, 2, 16, 15, 0), ar2);

        //añadir Stand a cada comerciante, y Escenario a cada Artista
        c5.setStand(z5);
        c6.setStand(z6);
        c3.setStand(z5);
        c4.setStand(z6);

        ar1.setEscenario(z7);
        ar2.setEscenario(z8);

        c1.setStand(z5);
        c2.setStand(z6);

        z5.agregarEmpleado(c5);
        z5.agregarEmpleado(c6);
        z6.agregarEmpleado(c3);
        z6.agregarEmpleado(c4);

        ar1.addAcceso(acceso);
        ar2.addAcceso(acceso);
        as1.addAcceso(acceso);
        as2.addAcceso(acceso);
        c1.addAcceso(acceso);
        c2.addAcceso(acceso);
        s1.addAcceso(acceso);
        s2.addAcceso(acceso);
        c5.addAcceso(acceso);
        c6.addAcceso(acceso);
        c3.addAcceso(acceso);
        c4.addAcceso(acceso);

        //Carga de listas de ZonasAccesibles
        ar1.addZona(z1);
        ar1.addZona(z8);
        ar2.addZona(z5);
        ar2.addZona(z2);
        as1.addZona(z2);
        as1.addZona(z7);
        as2.addZona(z6);
        as2.addZona(z7);
        c1.addZona(z7);
        c1.addZona(z1);
        c2.addZona(z4);
        c2.addZona(z3);
        s1.addZona(z3);
        s1.addZona(z6);
        s2.addZona(z8);
        s2.addZona(z7);
        c5.addZona(z1);
        c5.addZona(z2);
        c6.addZona(z3);
        c6.addZona(z4);
        c3.addZona(z5);
        c3.addZona(z6);
        c4.addZona(z7);
        c4.addZona(z8);


        ar1.setZonaActual(z1);
        z1.ponePersona();
        ar2.setZonaActual(z5);
        z5.ponePersona();
        as1.setZonaActual(z2);
        z2.ponePersona();
        as2.setZonaActual(z7);
        z7.ponePersona();
        c1.setZonaActual(z1);
        z1.ponePersona();
        c2.setZonaActual(z3);
        z3.ponePersona();
        s1.setZonaActual(z6);
        z6.ponePersona();
        s2.setZonaActual(z7);
        z7.ponePersona();
        c5.setZonaActual(z6);
        z6.ponePersona();
        c6.setZonaActual(z2);
        z2.ponePersona();
        c3.setZonaActual(z8);
        z8.ponePersona();
        c4.setZonaActual(z1);
        z1.ponePersona();


        dataContainer.addPersona(ar1);
        dataContainer.addPersona(ar2);
        dataContainer.addPersona(as1);
        dataContainer.addPersona(as2);
        dataContainer.addPersona(c1);
        dataContainer.addPersona(c2);
        dataContainer.addPersona(s1);
        dataContainer.addPersona(s2);
        dataContainer.addPersona(c5);
        dataContainer.addPersona(c6);
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
        try
        {
            mapper.writeValue(new File("datosFestival.xml"), dataContainer);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
