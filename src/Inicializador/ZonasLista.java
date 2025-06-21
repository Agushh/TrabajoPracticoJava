package Inicializador;

import Dominio.Zonas.Zona;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.ArrayList;

@JacksonXmlRootElement(localName = "zonas")
public class ZonasLista {
    @JacksonXmlElementWrapper(useWrapping = false) // evita que cree un nodo extra "personas"
    private ArrayList<Zona> zonas = new ArrayList<>();

    public ZonasLista(ArrayList<Zona> zonas) {
        this.zonas = zonas;
    }

    public ZonasLista(){}

    public ArrayList<Zona> getZonas() {
        return zonas;
    }

    public void setZonas(ArrayList<Zona> zonas) {
        this.zonas = zonas;
    }

    public void addZona(Zona zona) {zonas.add(zona);}
}
