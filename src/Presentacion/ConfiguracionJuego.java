package Presentacion;

import javax.swing.*;
import java.awt.*;

public class ConfiguracionJuego extends JPanel {

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

    /**
     * Constructor de la clase
     * @param ventana JFrame donde se le añade el JPanel
     */
    public ConfiguracionJuego(JFrame ventana){
        this.menuSeleciones = menuSeleciones;
        this.ventana = ventana;
        prepareElementos();
        prepareAcciones();
    }

    /**
     * Prepare elementos visuales en general
     */
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

    /**
     * Prepara los elementos de seleccion de color de la cabeza
     * @return Retorna el JPanel de seleccion de color de la cabeza
     */
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

    /**
     * Prepara los elementos de seleccion de color del cuerpo
     * @return Retorna el JPanel de seleccion de color del cuerpo
     */
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

    /**
     * Prepara los elementos de seleccion de nombre
     * @return Retorna el JPanel de seleccion de nombre
     */
    private JPanel prepareElementosNombre(){
        JPanel nombre = new JPanel();
        campoNombre = new JTextField();
        campoNombre.setText("Player");
        JLabel nombreLabel = new JLabel("Digite el nombre del jugador");
        nombre.setLayout(new FlowLayout());

        Box tamanoOriginal = Box.createVerticalBox();
        tamanoOriginal.add(nombreLabel);
        tamanoOriginal.add(campoNombre);

        nombre.add(tamanoOriginal);
        return nombre;
    }

    /**
     * Prepara los elementos de seleccion de las frutas con las que jugar
     * @return Retorna el JPanel de seleccion de las frutas con las que jugar
     */
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

    /**
     * Prepara las acciones de para seleccionar los colores
     */
    private void prepareAcciones(){
        cambiarColorCabeza.addActionListener(e->cambiarColorCabeza());
        cambiarColorCuerpo.addActionListener(e->cambiarColorCuerpo());
        iniciar.addActionListener(e->iniciarJuego());
    }

    /**
     * Funcionalidad para cambiar el color del cuerpo
     */
    private void cambiarColorCuerpo(){
        colorChooser.setVisible(true);
        Color changeColor = JColorChooser.showDialog(null, "Cambiar de color",cuerpo);
        if(changeColor != null){ cuerpo = changeColor;}
        this.colorCuerpo.setBackground(cuerpo);
    }

    /**
     * Funcionalidad para cambiar el color de la cabeza
     */
    private void cambiarColorCabeza(){
        colorChooser.setVisible(true);
        Color changeColor = JColorChooser.showDialog(null, "Cambiar de color",cabeza);
        if(changeColor != null){ cabeza = changeColor;}
        this.colorCabeza.setBackground(cabeza);
    }

    /**
     * Revisa las frutas seleccionadas
     */
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

    /**
     * Inicia el juego
     */
    private void iniciarJuego(){
        this.setVisible(false);
        ventana.add(new PanelDeJuego(new Color[]{cabeza},new Color[]{cuerpo},new String[]{campoNombre.getText()}, getElecciones()));
        ventana.pack();
        ventana.setLocationRelativeTo(null);
    }

}
