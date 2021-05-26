package Presentacion;

import Persistencia.Manager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Records extends JPanel {

    private JPanel principal;
    private JButton regresar;

    /**
     * Constructor
     * @param principal Panel del menu principal
     */
    public Records(JPanel principal){
        this.principal = principal;
        prepareElementos();
        prepareAcciones();
    }

    /**
     * Prepara los elementos del JPanel
     */
    private void prepareElementos(){
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(1200, 770));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        regresar = new JButton("Regresar al menú");
        this.add(regresar, BorderLayout.SOUTH);
    }

    /**
     * Prepara la accion de lo componentes
     */
    private void prepareAcciones(){
        regresar.addActionListener(e -> regresar());
    }

    /**
     * funcion para regresar al menu principal
     */
    private void regresar(){
        this.setVisible(false);
        principal.setVisible(true);
    }

    /**
     * Dibujar componentes en la pantalla
     * @param g Graphics
     */
    public void paint(Graphics g){
        super.paintComponent(g);
        Manager manager = Manager.getInstance();
        ArrayList<String> records = manager.getTop5("datos/records.txt");
        Font font = new Font("Arial",Font.BOLD, 30);
        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString("Un solo Jugador",180,200);
        int y = 250;
        for (int i = 0; i < records.size(); i++) {
            if(i >= 5){break;}
            g.drawString(records.get(i),220,y);
            y += 50;
        }
        g.drawString("Dos Jugadores",690,200);
        y = 250;
        records = manager.getTop5("datos/recordsMultiplayer.txt");
        for (int i = 0; i  < records.size(); i++) {
            if(i >= 5){break;}
            g.drawString(records.get(i),725,y);
            y += 50;
        }

    }
}
