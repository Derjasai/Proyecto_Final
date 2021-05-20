package Presentacion;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ConfiguracionJuego extends JPanel {

    private JColorChooser colorChooser;
    private JTextField campoNombre;
    private Color cabeza;
    private Color cuerpo;
    private JButton cambiarColorCabeza;
    private JButton cambiarColorCuerpo;
    private JLabel colorCabeza, colorCuerpo;
    private JButton iniciar;
    private SnOOPeGUI ventana;
    private JRadioButton[] alimentos;
    private JRadioButton[] sorpresas;

    /**
     * Constructor de la clase
     * @param ventana JFrame donde se le añade el JPanel
     */
    public ConfiguracionJuego(SnOOPeGUI ventana){
        this.ventana = ventana;
        prepareElementos();
        prepareAcciones();
    }

    /**
     * Prepare elementos visuales en general
     */
    private void prepareElementos() {
        alimentos = new JRadioButton[4];sorpresas = new JRadioButton[6];
        this.setLayout(new GridLayout(6,1));
        this.colorChooser = new JColorChooser();
        this.iniciar = new JButton("Iniciar Juego");
        this.setFocusable(true);
        this.add(prepareElementosCabeza());
        this.add(prepareElementosCuerpo());
        this.add(prepareElementosNombre());
        this.add(prepareElementosOpcionesAlimentos());
        this.add(prepareelementosOpcionesSorpresas());
        this.add(iniciar);
    }

    /**
     * Prepara los elementos de seleccion de color de la cabeza
     * @return Retorna el JPanel de seleccion de color de la cabeza
     */
    private JPanel prepareElementosCabeza(){
        JPanel cabeza = new JPanel();
        this.cabeza = Color.RED;
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
    private JPanel prepareElementosOpcionesAlimentos(){
        JPanel opciones = new JPanel();
        JRadioButton manzana = new JRadioButton("Manzana", true);
        JRadioButton manzanaArco = new JRadioButton("Manzana Arcoiris", true);
        JRadioButton dulce = new JRadioButton("Dulce", true);
        JRadioButton veneno = new JRadioButton("Veneno");

        opciones.add(setRadioButton(manzana,"imgs/roja.png",0,alimentos));
        opciones.add(setRadioButton(manzanaArco,"imgs/multicolor.png",1,alimentos));
        opciones.add(setRadioButton(dulce,"imgs/dulce.png",2,alimentos));
        opciones.add(setRadioButton(veneno,"imgs/veneno.png",3,alimentos));
        return opciones;
    }

    private JPanel prepareelementosOpcionesSorpresas(){
        JPanel opciones = new JPanel();

        JRadioButton aumento = new JRadioButton("Aumento vel.", true);
        JRadioButton disminucion = new JRadioButton("Disminucion vel.", true);
        JRadioButton bloque = new JRadioButton("Bloque Trampa", true);
        JRadioButton lupa = new JRadioButton("Lupa",true);
        JRadioButton division = new JRadioButton("Division",true);
        JRadioButton fuego = new JRadioButton("Fuego",true);

        opciones.add(setRadioButton(aumento,"imgs/aumento.png", 0,sorpresas));
        opciones.add(setRadioButton(disminucion,"imgs/disminucion.png", 1,sorpresas));
        opciones.add(setRadioButton(bloque,"imgs/bloqueTrampa.png", 2,sorpresas));
        opciones.add(setRadioButton(lupa,"imgs/lupa.png", 3,sorpresas));
        opciones.add(setRadioButton(division,"imgs/division.png", 4,sorpresas));
        opciones.add(setRadioButton(fuego,"imgs/fuego.png", 5,sorpresas));
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
    private String[] getEleccionesAlimentos(){
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
     * Revisa las sorpresas seleccionadas
     */
    private String[] getEleccionesSorpresas(){
        int count= 0,apunt=0;
        for (int i = 0; i < 6; i++) {
            if (sorpresas[i].isSelected()){count++;}
        }

        String[] elecciones = new String[count];

        for (int i = 0; i < 6; i++) {
            if (sorpresas[i].isSelected()){elecciones[apunt] = sorpresas[i].getText();apunt++;}
        }
        return elecciones;
    }

    /**
     * Inicia el juego
     */
    private void iniciarJuego(){
        this.setVisible(false);
        ventana.panelJuego = new PanelDeJuego(new Color[]{cabeza},
                new Color[]{cuerpo},
                new String[]{campoNombre.getText()},
                getEleccionesAlimentos(),
                getEleccionesSorpresas(),
                ventana);
        ventana.add(ventana.panelJuego);
        ventana.pack();
        ventana.setLocationRelativeTo(null);
    }

    private Icon getImage(String path){
        BufferedImage img = null;
        try {
            img = ImageIO. read(new File(path)); // la carga en una BufferedReade
        } catch (IOException e) {
            e.printStackTrace();
        }
        Icon icon = new ImageIcon(img);

        return icon;
    }

    private JRadioButton setRadioButton(JRadioButton boton, String path, int i, JRadioButton[] lista){
        lista[i] = boton;
        boton.setIcon(getImage("imgs/barrier.png"));
        boton.setIconTextGap(10);
        boton.setSelectedIcon(getImage(path));
        return boton;
    }
}
