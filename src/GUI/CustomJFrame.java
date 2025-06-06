package GUI;

import Exceptions.GUIException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CustomJFrame extends JFrame{

    //guardar los labels en el objeto ventana por si hay que cambiarlos.
    private List<JLabel> labels;

    //constructor
    public CustomJFrame(String title, int minWidth, int minHeight, int x, int y, boolean centered) throws GUIException
    {
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

       // setLayout(new FlowLayout());
    }
    public void addboton(String text, ActionListener accion){
        JButton btn =new JButton(text);
        btn.setPreferredSize(new Dimension(200, 60));
        add(btn);
        btn.addActionListener(accion);
        revalidate();
        repaint();
    }
    public void abrir_panel_personas() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(300, 150));
        String[] personas = {"Alejo", "Facu", "Agus", "Fran"};//ahora lo hago fijo dsp traemos del controlador
        JComboBox<String> comboPersonas = new JComboBox<>(personas);
        panel.add(new JLabel("Seleccione una persona:"));
        panel.add(comboPersonas);
        JButton btn=new JButton("Mostrar");
        panel.add(btn);//falta funcionalidad
        JOptionPane.showMessageDialog(this, panel, "Panel Personas", JOptionPane.PLAIN_MESSAGE);

    }
    public void abri_panel_mover(){
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
        JOptionPane.showMessageDialog(this, panel, "Panel Personas", JOptionPane.PLAIN_MESSAGE);
    }
    public void abri_panel_zonas(){
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(500, 500));
        JOptionPane.showMessageDialog(this, panel, "Panel Personas", JOptionPane.PLAIN_MESSAGE);
        //falta funcionalidad
    }
    public  void abrir_panle_stands(){
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(500, 500));
        JOptionPane.showMessageDialog(this, panel, "Panel Personas", JOptionPane.PLAIN_MESSAGE);
        //falta funcionalidad
    }
    //añadir texto
    public void addLabel(String text)
    {
        JLabel label = new JLabel(text);
        labels.add(label);
        add(label);
        label.setText("hola");
        label.setHorizontalTextPosition(JLabel.CENTER);
        setVisible(true);
    }
}
