//package Inicializador;
//
//import com.google.gson.*;
//import com.google.gson.reflect.TypeToken;
//import Util.RuntimeTypeAdapterFactory;
//
//import Controlador.Controlador;
//import Dominio.Personas.*;
//import Dominio.Zonas.*;
//import Dominio.Factory.PersonaFactory;
//import Dominio.Enums.TipoPers;
//
//import java.io.*;
//import java.lang.reflect.Type;
//import java.util.*;
//
//public class CargaInicial {
//
//    public static void inicializarDesdeJson() {
//        Controlador c = Controlador.getControlador();
//        cargarPersonas(c.getPersonas(), "personas.json");
//        cargarZonas(c.getZonas(), "zonas.json");
//    }
//
//    private static void cargarPersonas(TreeMap<String, Persona> mapa, String archivo) {
//        try (Reader reader = new FileReader(archivo)) {
//            Gson gson = new Gson();
//            Type tipoLista = new TypeToken<List<Map<String, String>>>() {}.getType();
//            List<Map<String, String>> datos = gson.fromJson(reader, tipoLista);
//
//            for (Map<String, String> entrada : datos) {
//                String tipoStr = entrada.get("tipo");
//                String nombre = entrada.get("nombre");
//
//                TipoPers tipo = TipoPers.valueOf(tipoStr);
//                Persona p = PersonaFactory.crear(tipo, nombre); ///falta agregar la zona donde se encuentra la persona.
//                mapa.put(p.getId(), p);
//            }
//        } catch (IOException e) {
//            System.err.println("Error al cargar personas: " + e.getMessage());
//        }
//    }
//
//    private static void cargarZonas(TreeMap<String, Zona> mapa, String archivo) {
//        try (Reader reader = new FileReader(archivo)) {
//            RuntimeTypeAdapterFactory<Zona> zonaAdapter = RuntimeTypeAdapterFactory
//                    .of(Zona.class, "tipo")
//                    .registerSubtype(ZonaComun.class, "ZONA_COMUN")
//                    .registerSubtype(Escenario.class, "ESCENARIO")
//                    .registerSubtype(ZonaRestringida.class, "ZONA_RESTRINGIDA")
//                    .registerSubtype(Stand.class, "STAND");
//
//            Gson gson = new GsonBuilder()
//                    .registerTypeAdapterFactory(zonaAdapter)
//                    .create();
//
//            Type tipoLista = new TypeToken<List<Zona>>() {}.getType();
//            List<Zona> zonas = gson.fromJson(reader, tipoLista);
//
//            for (Zona z : zonas) {
//                mapa.put(z.getCod(), z);
//            }
//        } catch (IOException e) {
//            System.err.println("Error al cargar zonas: " + e.getMessage());
//        }
//    }
//}
