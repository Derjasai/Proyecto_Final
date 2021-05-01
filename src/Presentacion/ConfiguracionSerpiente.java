package Presentacion;

import Aplicacion.Dulce;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ConfiguracionSerpiente extends JPanel {

    private JColorChooser colorChooser;
    private JTextField campoNombre;
    private Color cabeza;
    private Color cuerpo;
    private JButton cambiarColorCabeza;
    private JButton cambiarColorCuerpo;
    private JLabel colorCabeza, colorCuerpo;
    private JButton iniciar;
    private JPanel menuSeleciones;
    private JFrame ventana;
    private JRadioButton[] alimentos;
    private ButtonGroup uwu;

    public ConfiguracionSerpiente(JPanel menuSeleciones, JFrame ventana){
        this.menuSeleciones = menuSeleciones;
        this.ventana = ventana;
        prepareElementos();
        prepareAcciones();
    }

    private void prepareElementos() {
        alimentos = new JRadioButton[4];
        this.setLayout(new GridLayout(5,1));
        this.colorChooser = new JColorChooser();
        this.iniciar = new JButton("Iniciar Juego");
        this.add(prepareElementosCabeza());
        this.add(prepareElementosCuerpo());
        this.add(prepareElementosNombre());
        this.add(prepareElementosOpciones());
        this.add(iniciar);
    }

    private JPanel prepareElementosCabeza(){
        JPanel cabeza = new JPanel();
        this.cabeza = Color.GREEN;
        colorCabeza = new JLabel("Color Ejemplo de la Cabeza");
        colorCabeza.setOpaque(true);
        cambiarColorCabeza = new JButton("Cambiar de color");
        cabeza.setLayout(new FlowLayout());
        colorCabeza.setBackground(this.cabeza);
        colorCabeza.setSize(15,15);
        cabeza.add(colorCabeza);
        cabeza.add(cambiarColorCabeza);
        return cabeza;
    }

    private JPanel prepareElementosCuerpo(){
        JPanel cuerpo = new JPanel();
        this.cuerpo = Color.CYAN;
        colorCuerpo = new JLabel("Color Ejemplo del Cuerpo");
        colorCuerpo.setOpaque(true);
        cambiarColorCuerpo = new JButton("Cambiar de color");
        cuerpo.setLayout(new FlowLayout());
        colorCuerpo.setBackground(this.cuerpo);
        colorCuerpo.setSize(15,15);
        cuerpo.add(colorCuerpo);
        cuerpo.add(cambiarColorCuerpo);
        return cuerpo;
    }

    private JPanel prepareElementosNombre(){
        JPanel nombre = new JPanel();
        campoNombre = new JTextField();
        JLabel nombreLabel = new JLabel("Digite el nombre del jugador");
        nombre.setLayout(new FlowLayout());

        Box tamanoOriginal = Box.createVerticalBox();
        tamanoOriginal.add(nombreLabel);
        tamanoOriginal.add(campoNombre);

        nombre.add(tamanoOriginal);
        return nombre;
    }

    private JPanel prepareElementosOpciones(){
        JPanel opciones = new JPanel();
        JRadioButton manzana = new JRadioButton("Manzana", true);
        JRadioButton manzanaArco = new JRadioButton("Manzana Arcoiris", true);
        JRadioButton dulce = new JRadioButton("Dulce", true);
        JRadioButton veneno = new JRadioButton("Veneno");

        opciones.add(manzana);alimentos[0] = manzana;
        opciones.add(manzanaArco);alimentos[1] = manzanaArco;
        opciones.add(dulce);alimentos[2] = dulce;
        opciones.add(veneno);alimentos[3] = veneno;
        return opciones;
    }

    private void prepareAcciones(){
        cambiarColorCabeza.addActionListener(e->cambiarColorCabeza());
        cambiarColorCuerpo.addActionListener(e->cambiarColorCuerpo());
        iniciar.addActionListener(e->iniciarJuego());
    }

    private void cambiarColorCuerpo(){
        colorChooser.setVisible(true);
        Color changeColor = JColorChooser.showDialog(null, "Cambiar de color",cuerpo);
        if(changeColor != null){ cuerpo = changeColor;}
        this.colorCuerpo.setBackground(cuerpo);
    }

    private void cambiarColorCabeza(){
        colorChooser.setVisible(true);
        Color changeColor = JColorChooser.showDialog(null, "Cambiar de color",cabeza);
        if(changeColor != null){ cabeza = changeColor;}
        this.colorCabeza.setBackground(cabeza);
    }

    private String[] getElecciones(){
        int count= 0,apunt=0;
        for (int i = 0; i < 4; i++) {
            if (alimentos[i].isSelected()){count++;}
        }

        String[] elecciones = new String[count];

        for (int i = 0; i < 4; i++) {
            if (alimentos[i].isSelected()){elecciones[apunt] = alimentos[i].getText();apunt++;}
        }
        return elecciones;
    }
    
    private void iniciarJuego(){
        this.setVisible(false);
        ventana.add(new PanelDeJuego(new Color[]{cabeza},new Color[]{cuerpo},new String[]{campoNombre.getText()}, getElecciones()));
        ventana.pack();
        ventana.setLocationRelativeTo(null);
    }

}
