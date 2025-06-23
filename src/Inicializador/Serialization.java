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

        Artista ar1 = new Artista("Andres Calamaro", "AR0001");
        Artista ar2 = new Artista("Gustavo Cerati", "AR0002");
        Artista ar3 = new Artista("Indio Solari", "AR0003");
        Artista ar4 = new Artista("Fito Paez", "AR0004");
        Artista ar5 = new Artista("Charly Garcia", "AR0005");
        Artista ar6 = new Artista("Alberto Spinetta", "AR0006");

        Persona as1 = new Asistente("Mateo", "AS0001");
        Persona as2 = new Asistente("Valentina", "AS0002");
        Persona as3 = new Asistente("Luca", "AS0003");
        Persona as4 = new Asistente("Malena", "AS0004");
        Persona as5 = new Asistente("Felipe", "AS0005");
        Persona as6 = new Asistente("Renata", "AS0006");

        Persona s1 = new Staff("Santiago", "ST0001");
        Persona s2 = new Staff("Camila", "ST0002");

        Comerciante c1 = new Comerciante("Joaquin", "COM0001");
        Comerciante c2 = new Comerciante("Martina", "COM0002");
        Comerciante c3 = new Comerciante("Tomas", "COM0003");
        Comerciante c4 = new Comerciante("Delfina", "COM0004");
        Comerciante c5 = new Comerciante("Benjamin", "COM0005");
        Comerciante c6 = new Comerciante("Julieta", "COM006");

        Zona zc1 = new ZonaComun("ZC001", "Zona de patio de comidas");
        Zona zc2 = new ZonaComun("ZC002", "Espacio Central");
        Zona zc3 = new ZonaComun("ZC003", "Zona de baños");
        Zona zc4 = new ZonaComun("ZC004", "Zona de descanso");
        Zona zr1 = new ZonaRestringida("ZR001", "Camarines", 10);
        Zona zr2 = new ZonaRestringida("ZR002", "Sala de sonido", 20);
        Zona zr3 = new ZonaRestringida("ZR003", "Backstage", 30);

        Stand zs1 = new Stand("ZS001", "Stand de Comida", 200, zc1, c1);
        Stand zs2 = new Stand("ZS002", "Stand de refresco", 200, zc1, c2);

        Escenario ze1 = new Escenario("ZE001", "Escenario N1", 100);
        Escenario ze2 = new Escenario("ZE002", "Escenario N2", 200);
        Escenario ze3 = new Escenario("ZE003", "Escenario N3", 150);
        Escenario ze4 = new Escenario("ZE004", "Escenario N4", 300);

        Acceso acceso = new Acceso(zc1, LocalDateTime.now(), 0, EstadoAcceso.AUTORIZADO);

        ze1.addEvento(LocalDateTime.of(2025, 10, 7, 22, 0), ar1);
        ze1.addEvento(LocalDateTime.of(2025, 11, 2, 15, 30), ar4);
        ze1.addEvento(LocalDateTime.of(2025, 12, 24, 20, 0), ar5);

        ze2.addEvento(LocalDateTime.of(2025, 6, 30, 10, 0), ar2);
        ze2.addEvento(LocalDateTime.of(2025, 7, 2, 12, 0), ar6);
        ze2.addEvento(LocalDateTime.of(2025, 2, 16, 15, 30), ar4);

        ze3.addEvento(LocalDateTime.of(2025, 1, 16, 15, 0), ar4);
        ze3.addEvento(LocalDateTime.of(2025, 2, 16, 15, 0), ar3);
        ze3.addEvento(LocalDateTime.of(2025, 3, 16, 15, 30), ar6);

        ze4.addEvento(LocalDateTime.of(2025, 4, 6, 18, 0), ar3);
        ze4.addEvento(LocalDateTime.of(2025, 3, 5, 12, 30), ar5);
        ze4.addEvento(LocalDateTime.of(2025, 6, 11, 12, 0), ar2);

        //añadir Stand a cada comerciante, y Escenario a cada Artista
        c1.setStand(zs2);
        c2.setStand(zs1);
        c3.setStand(zs1);
        c4.setStand(zs2);
        c5.setStand(zs1);
        c6.setStand(zs2);

        ar1.setEscenario(ze1);
        ar2.setEscenario(ze2);
        ar3.setEscenario(ze3);
        ar4.setEscenario(ze4);
        ar5.setEscenario(ze2);
        ar6.setEscenario(ze1);

        zs1.agregarEmpleado(c5);
        zs1.agregarEmpleado(c2);
        zs1.agregarEmpleado(c3);
        zs2.agregarEmpleado(c1);
        zs2.agregarEmpleado(c4);
        zs2.agregarEmpleado(c6);

        ar1.addAcceso(acceso);
        ar2.addAcceso(acceso);
        ar3.addAcceso(acceso);
        ar4.addAcceso(acceso);
        ar5.addAcceso(acceso);
        ar6.addAcceso(acceso);

        as1.addAcceso(acceso);
        as2.addAcceso(acceso);
        as3.addAcceso(acceso);
        as4.addAcceso(acceso);
        as5.addAcceso(acceso);
        as6.addAcceso(acceso);

        c1.addAcceso(acceso);
        c2.addAcceso(acceso);
        c3.addAcceso(acceso);
        c4.addAcceso(acceso);
        c5.addAcceso(acceso);
        c6.addAcceso(acceso);

        s1.addAcceso(acceso);
        s2.addAcceso(acceso);
        c5.addAcceso(acceso);
        c6.addAcceso(acceso);
        c3.addAcceso(acceso);
        c4.addAcceso(acceso);

        //Carga de listas de ZonasAccesibles
        ar1.addZona(zc1);
        ar1.addZona(ze2);

        ar2.addZona(zs1);
        ar2.addZona(zc2);

        ar3.addZona(zs1);
        ar3.addZona(zc2);

        ar4.addZona(zs1);
        ar4.addZona(zc2);

        ar5.addZona(zs1);
        ar5.addZona(zc2);

        as1.addZona(zc2);
        as1.addZona(ze1);

        as2.addZona(zs2);
        as2.addZona(ze1);

        as3.addZona(zs2);
        as3.addZona(ze1);

        as4.addZona(zs2);
        as4.addZona(ze1);

        as5.addZona(zs2);
        as5.addZona(ze1);

        as6.addZona(zs2);
        as6.addZona(ze1);

        c1.addZona(ze1);
        c1.addZona(zc1);

        c2.addZona(zr2);
        c2.addZona(zr1);

        c3.addZona(zr2);
        c3.addZona(zr1);

        c4.addZona(zr2);
        c4.addZona(zr1);

        c5.addZona(zr2);
        c5.addZona(zr1);

        c6.addZona(zr2);
        c6.addZona(zr1);

        s1.addZona(zr1);
        s1.addZona(zs2);
        s2.addZona(ze2);
        s2.addZona(ze1);

        ar1.setZonaActual(zc1);
        zc1.ponePersona();
        ar2.setZonaActual(zs1);
        zs1.ponePersona();
        ar3.setZonaActual(zs1);
        zs1.ponePersona();
        ar4.setZonaActual(zs2);
        zs2.ponePersona();
        ar5.setZonaActual(zc1);
        zc1.ponePersona();
        ar6.setZonaActual(zc3);
        zc3.ponePersona();

        as1.setZonaActual(zc2);
        zc2.ponePersona();
        as2.setZonaActual(ze1);
        ze1.ponePersona();
        as3.setZonaActual(ze1);
        ze1.ponePersona();
        as4.setZonaActual(ze1);
        ze1.ponePersona();
        as5.setZonaActual(ze1);
        ze1.ponePersona();
        as6.setZonaActual(ze1);
        ze1.ponePersona();

        c1.setZonaActual(zc1);
        zc1.ponePersona();
        c2.setZonaActual(zr1);
        zr1.ponePersona();
        c5.setZonaActual(zs2);
        zs2.ponePersona();
        c6.setZonaActual(zc2);
        zc2.ponePersona();
        c3.setZonaActual(ze2);
        ze2.ponePersona();
        c4.setZonaActual(zc1);
        zc1.ponePersona();

        s1.setZonaActual(zs2);
        zs2.ponePersona();
        s2.setZonaActual(ze1);
        ze1.ponePersona();


        dataContainer.addPersona(ar1);
        dataContainer.addPersona(ar2);
        dataContainer.addPersona(ar3);
        dataContainer.addPersona(ar4);
        dataContainer.addPersona(ar5);
        dataContainer.addPersona(ar6);
        dataContainer.addPersona(as1);
        dataContainer.addPersona(as2);
        dataContainer.addPersona(as3);
        dataContainer.addPersona(as4);
        dataContainer.addPersona(as5);
        dataContainer.addPersona(as6);
        dataContainer.addPersona(c1);
        dataContainer.addPersona(c2);
        dataContainer.addPersona(s1);
        dataContainer.addPersona(s2);
        dataContainer.addPersona(c5);
        dataContainer.addPersona(c6);
        dataContainer.addPersona(c3);
        dataContainer.addPersona(c4);

        dataContainer.addZona(zc1);
        dataContainer.addZona(zc2);
        dataContainer.addZona(zc3);
        dataContainer.addZona(zc4);
        dataContainer.addZona(zr1);
        dataContainer.addZona(zr2);
        dataContainer.addZona(zr3);
        dataContainer.addZona(zs1);
        dataContainer.addZona(zs2);
        dataContainer.addZona(ze1);
        dataContainer.addZona(ze2);
        dataContainer.addZona(ze3);
        dataContainer.addZona(ze4);

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
