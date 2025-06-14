package Vista;
import java.util.Collection;
import java.util.TreeMap;
import Controlador.Controlador;
import Dominio.Exceptions.GUIException;
import Dominio.Personas.Datos.Acceso;
import Dominio.Personas.Persona;
import Dominio.Zonas.Zona;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CustomJFrame extends JFrame{

    //guardar los labels en el objeto ventana por si hay que cambiarlos.
    private List<JLabel> labels;

    //constructor
    public CustomJFrame(String title, int minWidth, int minHeight, int x, int y, boolean centered, Controlador controlador) throws GUIException
    { //agrege pasar el controlador por parametro
        super("Example");
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        labels = new ArrayList<>();
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(minWidth,minHeight));
        setSize(500, 500);
        setResizable(true);
        if(centered)
            setLocationRelativeTo(null);
        else
            setLocation(x,y);
        setVisible(true);
        addBotonFrame("Muestra persona",e->abrirPanelPersonas(controlador.getPersonas(),controlador),this);
        addBotonFrame("Mover persona",e -> abriPanelMover(),this);
        addBotonFrame("Reporte de stands",e ->abrirPanleStands(),this);
        addBotonFrame("Reporte de zonas",e -> abriPanelZonas(controlador.getZonas()),this);

        // setLayout(new FlowLayout());
    }
    public void addBotonFrame(String text, ActionListener accion, Container contenedor){ //crea btn de frame
        JButton btn =new JButton(text);
        btn.setPreferredSize(new Dimension(200, 60));
        contenedor.add(btn);
        btn.addActionListener(accion);
        revalidate();
        repaint();
    }
    public  void addBotonPanel(String text, ActionListener accion, Container contenedor){//crea btn de panel
        JButton btn =new JButton(text);
        btn.setPreferredSize(new Dimension(90, 30));
        contenedor.add(btn);
        btn.addActionListener(accion);
        revalidate();
        repaint();
    }
    public void abrirPanelPersonas(TreeMap<String, Persona> personas,Controlador controlador) {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(300, 150));

        //crea una coleccion con los nombre de todas las personas para mostrar en el combo(nombre)
        JComboBox<Persona> comboPersonas = new JComboBox<>();
        for (Persona p : personas.values()) {
            comboPersonas.addItem(p);
        }
        panel.add(new JLabel("Seleccione una persona:"));
        panel.add(comboPersonas);
        addBotonPanel("Mostrar",e-> {Persona p = (Persona) comboPersonas.getSelectedItem();muestraPersonaEnGui(p);},panel);

        JOptionPane.showMessageDialog(this, panel, "Panel Personas", JOptionPane.PLAIN_MESSAGE);
    }
    void muestraPersonaEnGui(Persona perAMostrar){
        JPanel panel = new JPanel(new GridLayout(0,1,0,10));
        panel.add(new JLabel("Nombre:  " + perAMostrar.getNombre())); //todo hacer mejor el to string
        panel.add(new JLabel("ID:  " + perAMostrar.getId()+"             Tipo: "));//todo FALTA AGREGAR QUE TIPO ES TENGO QUE PREGUNTAR NO SE COMO LO MANEJAMOS
        panel.add(new JLabel("Zona actual: "+ perAMostrar.getZonaActual().getDescripcion()));
        panel.add(new JLabel("Acceso:" ));;
        for (Acceso acceso : perAMostrar.getAccesos()) {
            panel.add(new JLabel(""+ acceso.toString() ));
        }
        JOptionPane.showMessageDialog(this, panel, "Datos de la Persona", JOptionPane.PLAIN_MESSAGE);
    }
    public void abriPanelMover(){
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(300, 150));
        String[] personas = {"Alejo", "Facu", "Agus", "Fran"};//ahora lo hago fijo dsp traemos del controlador
        String[] zonas={"Escenario 1","Patio de comidas","Campo"};
        JComboBox<String> combopersonas = new JComboBox<>(personas);
        JComboBox<String> combozonas = new JComboBox<>(zonas);
        panel.add(new JLabel("Seleccione una persona:"));
        panel.add(combopersonas);
        panel.add(new JLabel("Seleccione una zona:"));
        panel.add(combozonas);
        JButton btn=new JButton("Mover");
        panel.add(btn);
        JOptionPane.showMessageDialog(this, panel, "Panel Mover", JOptionPane.PLAIN_MESSAGE);
    }
    public void abriPanelZonas(TreeMap<String, Zona> zonas){ //todo ESTOY ACAAAAAAA---------------
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // margen interno

        JLabel titulo = new JLabel("Zonas:");//crea el titulo lo centra y lo agrega al panel
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titulo);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));
        panel.setPreferredSize(new Dimension(500, 500));
        for (Zona zonaAMostrar : zonas.values()) {
            panel.add(new JLabel(zonaAMostrar.toString()));
            panel.add(Box.createRigidArea(new Dimension(0, 20)));
        }
        JOptionPane.showMessageDialog(this, panel, "Panel Zonas", JOptionPane.PLAIN_MESSAGE);

    }
    public  void abrirPanleStands(){
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(500, 500));
        JOptionPane.showMessageDialog(this, panel, "Panel Stands", JOptionPane.PLAIN_MESSAGE);
        //falta funcionalidad
    }
}
