package festival.app;

import festival.modelo.enums.EquipoFutbol;
import festival.modelo.zonas.*;
import festival.modelo.enums.TipoZona;
import festival.modelo.evento.Evento;

import java.time.LocalDateTime;
import java.util.*;
import java.util.Objects;

public class MainFestival {
    public static void main(String[] args){
    Evento even= new Evento(LocalDateTime.now(),"Calamaro");
    Evento tu= new Evento(LocalDateTime.now(),"coldplay");
    ZonaComun patio = new ZonaComun("dx10","Patio Central");
        ZonaComun centro = new ZonaComun("dx20","centro especial");
    Escenario es=new Escenario("ESC1", "Escenario Norte", 2);
    es.agregarEvento(even);
    Stand estand = new Stand("Salah",10, EquipoFutbol.LIVERPOOL);
    System.out.println(estand);
    es.agregarEvento(tu);
    System.out.println(patio.equals(centro));
    System.out.println(patio);
    System.out.println("\n");
    System.out.println(es.getEventos());
    }

}
//LocalDateTime.now()