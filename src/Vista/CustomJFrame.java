package Vista;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;

import Controlador.Controlador;
import Dominio.Exceptions.*;
import Dominio.Personas.Comerciante;
import Dominio.Personas.Datos.Acceso;
import Dominio.Personas.Persona;
import Dominio.Zonas.Datos.Evento;
import Dominio.Zonas.Escenario;
import Dominio.Zonas.Stand;
import Dominio.Zonas.Zona;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;

public class CustomJFrame extends JFrame{
    public CustomJFrame()
    {
        Controlador controlador = Controlador.getControlador();
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        setTitle("Trabajo Practico Java");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(300,300));
        setSize(500, 500);
        setResizable(true);
        setLocationRelativeTo(null);
        setVisible(true);
        addBotonFrame("Muestra persona", _ ->abrirPanelPersonas(controlador.getPersonas()),this);
        addBotonFrame("Mover persona", _ -> abriPanelMover(controlador.getPersonas(),controlador.getZonas()),this);
        addBotonFrame("Reporte de stands", _ ->abrirPanelStands(controlador.getStands()),this);
        addBotonFrame("Reporte de zonas", _ -> abriPanelZonas(controlador.getZonas()),this);
        addBotonFrame("Cargar Datos", _ -> cargarDatosGui(controlador),this);

        // Ejecutar función al cerrar
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Controlador.getControlador().guardarDatos();
            }
        });
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
    public void abrirPanelPersonas(TreeMap<String, Persona> personas) {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(300, 150));

        //crea una coleccion con el nombre de todas las personas para mostrar en el combo(nombre)
        JComboBox<Persona> comboPersonas = new JComboBox<>();

        for (Persona p : personas.values()) {
            comboPersonas.addItem(p);
        }
        panel.add(new JLabel("Seleccione una persona:"));
        panel.add(comboPersonas);
        addBotonPanel("Mostrar", _ -> {Persona p = (Persona) comboPersonas.getSelectedItem();muestraPersonaEnGui(p);},panel);

        JOptionPane.showMessageDialog(this, panel, "Panel Personas", JOptionPane.PLAIN_MESSAGE);
    }
    void muestraPersonaEnGui(Persona perAMostrar) throws NullPointerException{
        try{
            JPanel panel = new JPanel(new GridLayout(0,1,0,10));
            panel.add(new JLabel("Nombre:  " + perAMostrar.getNombre())); //todo hacer mejor el to string?
            panel.add(new JLabel("ID:  " + perAMostrar.getId()+"             Tipo: " + perAMostrar.getClass().getSimpleName()));
            panel.add(new JLabel("Zona actual: "+ perAMostrar.getZonaActual().getDescripcion()));
            panel.add(new JLabel("Zonas accesibles: "));
            for(Zona zona : perAMostrar.getZonasPermitidas())
                panel.add(new JLabel( " * " + zona.toString()));
            panel.add(new JLabel("Acceso:" ));
            for (Acceso acceso : perAMostrar.getAccesos()) {
                panel.add(new JLabel(acceso.toString()));
            }
            JOptionPane.showMessageDialog(this, panel, "Datos de la Persona", JOptionPane.PLAIN_MESSAGE);
        }catch (NullPointerException e){
            JOptionPane.showMessageDialog(this, "Error al mostrar la persona \n Cargar datos!");
        }

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
        addBotonPanel("Mover", _ ->{
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
        }catch (NullPointerException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private void abriPanelZonas(TreeMap<String, Zona> zonas){
        // Contenedor real para el scroll
        Box box = Box.createVerticalBox();
        box.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JDialog dialog = new JDialog((Frame) null, "Panel Zonas", true);
        JLabel titulo = new JLabel("Zonas:");
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        if(!zonas.isEmpty()) {
            box.add(titulo);
            box.add(Box.createRigidArea(new Dimension(0, 30)));

            List<Zona> zonasSorted = new ArrayList<>(zonas.values());

            zonasSorted.sort(Comparator.comparingInt(Zona::getConcurrencia).reversed());

            int acum = 0;
            for (Zona zonaAMostrar : zonasSorted) {
                acum += zonaAMostrar.getConcurrencia();
                box.add(new JLabel(zonaAMostrar.toHTML()));
                if (zonaAMostrar instanceof Escenario escenario) {
                    box.add(new JLabel("    Eventos:"));
                    for(Evento evento : escenario.getEventos())
                    {
                        box.add(new JLabel("        " + evento.toString()));
                    }
                }
                box.add(Box.createRigidArea(new Dimension(0, 20)));
            }

            box.add(new JLabel("Cantidad de personas en el predio: " + acum));

            box.add(Box.createRigidArea(new Dimension(0, 30)));

            JScrollPane scrollPane = new JScrollPane(box);
            scrollPane.setPreferredSize(new Dimension(500, 500));

            JButton okButton = new JButton("OK");
            JButton exportButton = new JButton("Exportar");

            okButton.addActionListener(e -> dialog.dispose());
            exportButton.addActionListener(e -> generarReporteZonasTXT(dialog, zonasSorted));
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
        }else{
            JOptionPane.showMessageDialog(dialog, "Cargar datos!");
        }
    }


    private void abrirPanelStands(TreeMap<String, Stand> stands) {
        Box box = Box.createVerticalBox();
        box.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JDialog dialog = new JDialog((Frame) null, "Panel Stands", true);
        JLabel titulo = new JLabel("Stands:");
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        if(!stands.isEmpty()){
            box.add(titulo);
            box.add(Box.createRigidArea(new Dimension(0, 30)));

            for (Stand standAMostrar : stands.values()) {
                box.add(new JLabel(standAMostrar.toHTML()));
                box.add(new JLabel("Responsable: "+ standAMostrar.getResponsable().getNombre()));
                box.add(Box.createRigidArea(new Dimension(0, 20)));
                box.add(new JLabel("Lista de empleados:"));
                box.add(Box.createRigidArea(new Dimension(0, 5)));

                for (Comerciante comerciante : standAMostrar.getEmpleados()) {
                    box.add(new JLabel(" * " + comerciante));
                }
                box.add(Box.createRigidArea(new Dimension(0, 20)));
            }

            box.add(Box.createRigidArea(new Dimension(0, 30)));

            JScrollPane scrollPane = new JScrollPane(box);
            scrollPane.setPreferredSize(new Dimension(500, 500));
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

            JButton okButton = new JButton("OK");
            JButton exportButton = new JButton("Exportar");

            okButton.addActionListener(e -> dialog.dispose());

            exportButton.addActionListener(e -> generarReporteStandsTXT(dialog, stands));

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
        }else{
            JOptionPane.showMessageDialog(dialog, "Cargar datos!");
        }

    }

    private void generarReporteZonasTXT (JDialog dialog, List<Zona> zonas) {
        try {
            int acum = 0;
            FileWriter writer = new FileWriter("Zonas.txt");
            writer.write("Zonas: \n \n");
            for (Zona zona : zonas) {
                acum += zona.getConcurrencia();
                writer.write(zona + "\n");
                writer.write("Concurrencia: "+ zona.getConcurrencia()+"\n");
                if(zona instanceof Escenario){
                    writer.write("  Eventos: \n");
                    ((Escenario) zona).getEventos().forEach((evento) -> {
                        try {
                            writer.write("   * " + evento.toString() +" \n");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
                }
                writer.write("\n");
            }
            writer.write("Cantidad de personas en el predio: " + acum);
            writer.close();
            JOptionPane.showMessageDialog(dialog, "Zonas.txt generado correctamente...");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(dialog, "Error al exportar zonas...");
        }
    }

    private void generarReporteStandsTXT (JDialog dialog, TreeMap<String, Stand> stands) {
        try {
            FileWriter writer = new FileWriter("Stands.txt");
            writer.write("Stands: \n \n");
            for (Stand stand : stands.values()) {
                writer.write(stand.toString()+"\n");
                writer.write("Concurrencia: "+ stand.getConcurrencia()+"\n");
                writer.write("Responsable: "+ stand.getResponsable().getNombre()+"\n");
                writer.write("Lista de empleados \n");

                for (Comerciante comerciante : stand.getEmpleados()) {
                    writer.write(" * " + comerciante.toString() + "\n");
                }
                writer.write("\n");
            }
            writer.close();
            JOptionPane.showMessageDialog(dialog, "Stands.txt generado correctamente...");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(dialog, "Error al exportar stands...");
        }
    }

    private void cargarDatosGui(Controlador controlador) {
        JDialog dialog = new JDialog((Frame) null, "Panel Carga Datos", true);
        try{
            controlador.cargaDeDatos();
            JOptionPane.showMessageDialog(dialog, "Datos cargados correctamente...");
        }catch (DatosIncorrectosException e){
            JOptionPane.showMessageDialog(dialog, "Datos cargados. Se detectaron las siguientes inconsistencias : \n \n" + e.getMessage() + "\n El programa funcionara pero con posibles errores!! \n Se recomienda verificar los datos enlistados!");
        }catch (DeserializationException e){
            JOptionPane.showMessageDialog(dialog, e.getMessage());
        }
    }

}
