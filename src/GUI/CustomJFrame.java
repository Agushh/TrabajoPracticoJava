package GUI;

import Exceptions.GUIException;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CustomJFrame extends JFrame{

    //guardar los labels en el objeto ventana por si hay que cambiarlos.
    private List<JLabel> labels;

    //constructor
    public CustomJFrame(String title, int minWidth, int minHeight, int x, int y, boolean centered) throws GUIException
    {
        super("Example");
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
