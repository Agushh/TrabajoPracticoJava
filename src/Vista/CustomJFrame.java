package Vista;
import java.util.*;

import Controlador.Controlador;
import Dominio.Exceptions.AccesoDenegadoException;
import Dominio.Exceptions.GUIException;
import Dominio.Exceptions.ZonaEsLaActualException;
import Dominio.Exceptions.ZonaLlenaException;
import Dominio.Personas.Comerciante;
import Dominio.Personas.Datos.Acceso;
import Dominio.Personas.Persona;
import Dominio.Zonas.Escenario;
import Dominio.Zonas.Stand;
import Dominio.Zonas.Zona;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

public class CustomJFrame extends JFrame{

    //Guardar los labels en el objeto ventana por si hay que cambiarlos.
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
        addBotonFrame("Mover persona",e -> abriPanelMover(controlador.getPersonas(),controlador.getZonas()),this);
        addBotonFrame("Reporte de stands",e ->abrirPanleStands(controlador.getStands()),this);
        addBotonFrame("Reporte de zonas por pantalla",e -> abriPanelZonas(controlador.getZonas()),this);

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
        panel.add(new JLabel("Nombre:  " + perAMostrar.getNombre())); //todo hacer mejor el to string?
        panel.add(new JLabel("ID:  " + perAMostrar.getId()+"             Tipo: "+perAMostrar.getTipo()));
        panel.add(new JLabel("Zona actual: "+ perAMostrar.getZonaActual().getDescripcion()));
        panel.add(new JLabel("Zonas accesibles: "));
        for(Zona zona : perAMostrar.getZonasPermitidas())
            panel.add(new JLabel( " * " + zona.toString()));
        panel.add(new JLabel("Acceso:" ));;
        for (Acceso acceso : perAMostrar.getAccesos()) {
            panel.add(new JLabel(acceso.toString()));
        }
        JOptionPane.showMessageDialog(this, panel, "Datos de la Persona", JOptionPane.PLAIN_MESSAGE);
    }
    public void abriPanelMover(TreeMap<String, Persona> personas, TreeMap<String, Zona> zonas){
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(300, 150));
        JComboBox<Persona> comboPersonas = new JComboBox<>();
        for (Persona p : personas.values()) {
            comboPersonas.addItem(p);
        }

        JComboBox<Zona> comboZonas =new JComboBox<>();
        for (Zona z : zonas.values()) {
            comboZonas.addItem(z);
        }

        //JComboBox<String> combozonas = new JComboBox<>(Zona);
        panel.add(new JLabel("Seleccione una persona:"));
        panel.add(comboPersonas);
        panel.add(new JLabel() {{ setPreferredSize(new Dimension(300, 5)); }});//hago un espacio
        panel.add(new JLabel("Seleccione una zona:"));
        panel.add(comboZonas);
        addBotonPanel("Mover",e->{
            Persona p = (Persona) comboPersonas.getSelectedItem();
            Zona z = (Zona) comboZonas.getSelectedItem();
            accionMover(p,z);}
        ,panel);
        JOptionPane.showMessageDialog(this, panel, "Panel Mover", JOptionPane.PLAIN_MESSAGE);
    }
    void accionMover(Persona perAMover , Zona destino){
        try {
            Controlador.moverPersona(perAMover, destino);//muevo la persona
            JOptionPane.showMessageDialog(null, "✔ Persona movida");
        } catch (AccesoDenegadoException e) {
            JOptionPane.showMessageDialog(null, "✘ Persona sin acceso");
        } catch (ZonaLlenaException e) {
            JOptionPane.showMessageDialog(null, "✘ Zona llena");
        }catch (ZonaEsLaActualException e){
            JOptionPane.showMessageDialog(null, "✘ Persona actualmente en la zona");
        }
    }

    public void abriPanelZonas(TreeMap<String, Zona> zonas){
        // Contenedor real para el scroll
        Box box = Box.createVerticalBox();
        box.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel titulo = new JLabel("Zonas:");
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        box.add(titulo);
        box.add(Box.createRigidArea(new Dimension(0, 30)));

        List<Zona> zonasSorted = new ArrayList<>(zonas.values());

        zonasSorted.sort(Comparator.comparingInt(Zona::getConcurrencia).reversed());

        int acum = 0;
        for (Zona zonaAMostrar : zonasSorted) {
            acum += zonaAMostrar.getConcurrencia();
            box.add(new JLabel(zonaAMostrar.toHTML()));
            if(zonaAMostrar instanceof Escenario){
                box.add(new JLabel("Eventos:"));
                ((Escenario) zonaAMostrar).getEventos().forEach((evento) -> {
                    box.add(new JLabel(evento.toString()));
                });
            }
            box.add(Box.createRigidArea(new Dimension(0, 20)));
        }

        box.add(new JLabel("Cantidad de personas en el predio: " + acum));

        box.add(Box.createRigidArea(new Dimension(0, 30)));

        JScrollPane scrollPane = new JScrollPane(box);
        scrollPane.setPreferredSize(new Dimension(500, 500));

        JDialog dialog = new JDialog((Frame) null, "Panel Zonas", true);
        JButton okButton = new JButton("OK");
        JButton exportButton = new JButton("Exportar txt");

        okButton.addActionListener(e -> dialog.dispose());
        exportButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(dialog, "Exportando Zonas...");
        });
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(okButton);
        buttonPanel.add(exportButton);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(scrollPane, BorderLayout.CENTER);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        dialog.setContentPane(contentPanel);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);

        //scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        //scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        //JOptionPane.showMessageDialog(this, scrollPane, "Panel Zonas", JOptionPane.PLAIN_MESSAGE);
    }


    public void abrirPanleStands(TreeMap<String, Stand> stands) {
        // Usamos un Box vertical para que funcione bien con JScrollPane
        Box box = Box.createVerticalBox();
        box.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel titulo = new JLabel("Stands:");
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        box.add(titulo);
        box.add(Box.createRigidArea(new Dimension(0, 30)));

        for (Stand standAMostrar : stands.values()) {
            box.add(new JLabel(String.valueOf(standAMostrar.toHTML())));
            box.add(Box.createRigidArea(new Dimension(0, 20)));

            box.add(new JLabel("Lista de empleados:"));
            box.add(Box.createRigidArea(new Dimension(0, 5)));

            for(Comerciante comerciante : standAMostrar.getEmpleados())
                box.add(new JLabel(" * " + comerciante.toString()));
            box.add(Box.createRigidArea(new Dimension(0, 20)));
        }

        // Padding inferior para que el último elemento no quede cortado
        box.add(Box.createRigidArea(new Dimension(0, 30)));

        JScrollPane scrollPane = new JScrollPane(box);
        scrollPane.setPreferredSize(new Dimension(500, 500));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JOptionPane.showMessageDialog(this, scrollPane, "Panel Stands", JOptionPane.PLAIN_MESSAGE);
    }

}
