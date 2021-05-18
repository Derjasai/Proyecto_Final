package Presentacion;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ConfiguracionJuegoMultiplayer extends JPanel {

    private JColorChooser colorChooser;
    private JTextField campoNombre, campoNombre1;
    private Color cabeza, cabeza1;
    private Color cuerpo, cuerpo1;
    private JButton cambiarColorCabeza, cambiarColorCabeza1;
    private JButton cambiarColorCuerpo, cambiarColorCuerpo1;
    private JLabel colorCabeza, colorCuerpo, colorCabeza1, colorCuerpo1;
    private JButton iniciar;
    private SnOOPeGUI ventana;
    private JRadioButton[] alimentos;
    private JRadioButton[] sorpresas;

    /**
     * Constructor de la clase
     * @param ventana JFrame donde se le añade el JPanel
     */
    public ConfiguracionJuegoMultiplayer(SnOOPeGUI ventana){
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
        this.add(prepareElementosContenedorCabeza());
        this.add(prepareElementosContendorCuerpo());
        this.add(prepareElementosContenedorNombre());
        this.add(prepareElementosOpcionesAlimentos());
        this.add(prepareelementosOpcionesSorpresas());
        this.add(iniciar);
    }

    private Box prepareElementosContenedorCabeza(){
        cambiarColorCabeza  = new JButton("Cambiar color cabeza Serpiente 1");
        cambiarColorCabeza1 = new JButton("Cambiar color cabeza Serpiente 2");
        cabeza = Color.RED; cabeza1 = Color.GREEN;
        colorCabeza = new JLabel("Ejemeplo color cabeza Serpiente 1");
        colorCabeza1 = new JLabel("Ejemeplo color cabeza Serpiente 2");
        Box contenedor = Box.createHorizontalBox();
        contenedor.add(prepareElementosCabeza(cabeza,cambiarColorCabeza,colorCabeza));
        contenedor.add(prepareElementosCabeza(cabeza1,cambiarColorCabeza1,colorCabeza1));
        return contenedor;
    }

    private Box prepareElementosContendorCuerpo(){
        cambiarColorCuerpo  = new JButton("Cambiar color cuerpo Serpiente 1");
        cambiarColorCuerpo1 = new JButton("Cambiar color cuerpo Serpiente 2");
        cuerpo = Color.CYAN; cuerpo1 = Color.BLUE;
        colorCuerpo = new JLabel("Ejemeplo color cuerpo Serpiente 1");
        colorCuerpo1 = new JLabel("Ejemeplo color cuerpo Serpiente 2");
        Box contenedor = Box.createHorizontalBox();
        contenedor.add(prepareElementosCuerpo(cuerpo,cambiarColorCuerpo,colorCuerpo));
        contenedor.add(prepareElementosCuerpo(cuerpo1,cambiarColorCuerpo1,colorCuerpo1));
        return contenedor;
    }

    private Box prepareElementosContenedorNombre(){
        campoNombre = new JTextField("Player 1");
        campoNombre1 = new JTextField("Player 2");

        Box contenedor = Box.createHorizontalBox();
        contenedor.add(prepareElementosNombre(campoNombre));
        contenedor.add(prepareElementosNombre(campoNombre1));
        return contenedor;
    }

    /**
     * Prepara los elementos de seleccion de color de la cabeza
     * @return Retorna el JPanel de seleccion de color de la cabeza
     */
    private JPanel prepareElementosCabeza(Color colorCabeza, JButton boton, JLabel colorCabezaL){
        JPanel cabeza = new JPanel();
        colorCabezaL.setOpaque(true);
        cabeza.setLayout(new FlowLayout());
        colorCabezaL.setBackground(colorCabeza);
        colorCabezaL.setSize(15,15);
        cabeza.add(colorCabezaL);
        cabeza.add(boton);
        return cabeza;
    }

    /**
     * Prepara los elementos de seleccion de color del cuerpo
     * @return Retorna el JPanel de seleccion de color del cuerpo
     */
    private JPanel prepareElementosCuerpo(Color colorCuerpo, JButton boton, JLabel colorCabezaL){
        JPanel cuerpo = new JPanel();
        colorCabezaL.setOpaque(true);
        cuerpo.setLayout(new FlowLayout());
        colorCabezaL.setBackground(colorCuerpo);
        colorCabezaL.setSize(15,15);
        cuerpo.add(colorCabezaL);
        cuerpo.add(boton);
        return cuerpo;
    }

    /**
     * Prepara los elementos de seleccion de nombre
     * @return Retorna el JPanel de seleccion de nombre
     */
    private JPanel prepareElementosNombre(JTextField campoNombre){
        JPanel nombre = new JPanel();
        nombre.setLayout(new FlowLayout());
        JLabel nombreLabel = new JLabel("Digite el nombre del jugador");

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
        cambiarColorCabeza.addActionListener(e->cambiarColorCabeza(cabeza));
        cambiarColorCuerpo1.addActionListener(e->cambiarColorCuerpo(cabeza1));
        cambiarColorCuerpo.addActionListener(e->cambiarColorCuerpo(cuerpo));
        cambiarColorCuerpo1.addActionListener(e->cambiarColorCuerpo(cuerpo1));
        iniciar.addActionListener(e->iniciarJuego());
    }

    /**
     * Funcionalidad para cambiar el color del cuerpo
     */
    private void cambiarColorCuerpo(Color color){
        colorChooser.setVisible(true);
        Color changeColor = JColorChooser.showDialog(null, "Cambiar de color",color);
        if(changeColor != null){ color = changeColor;}
        this.colorCuerpo.setBackground(color);
    }

    /**
     * Funcionalidad para cambiar el color de la cabeza
     */
    private void cambiarColorCabeza(Color color){
        colorChooser.setVisible(true);
        Color changeColor = JColorChooser.showDialog(null, "Cambiar de color",color);
        if(changeColor != null){ color = changeColor;}
        this.colorCabeza.setBackground(color);
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
        ventana.panelJuego = new PanelDeJuego(new Color[]{cabeza,cabeza1},
                new Color[]{cuerpo,cuerpo1},
                new String[]{campoNombre.getText(),campoNombre1.getText()},
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
            img = ImageIO.  read(new File(path)); // la carga en una BufferedReade
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