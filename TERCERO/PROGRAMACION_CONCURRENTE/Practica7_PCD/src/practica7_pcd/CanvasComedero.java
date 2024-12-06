/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica7_pcd;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.util.ArrayList;

/**
 *
 * @author usuario
 */
class animal {

    int id;
    char tipo;

    public animal(int id, char tipo) {
        this.id = id;
        this.tipo = tipo;
    }

    public void setid(int id) {
        this.id = id;

    }

    public void settipo(char tipo) {
        this.tipo = tipo;

    }

    public int getid() {
        return id;
    }

    public char gettipo() {
        return tipo;
    }

}

public class CanvasComedero extends Canvas {

    private ArrayList<Integer> colaPerros = new ArrayList();
    private ArrayList<Integer> colaGatos = new ArrayList();
    private final Image perroImg, gatoImg, comederoImg;
    private animal[] comedero;

    CanvasComedero(int ancho, int alto) throws InterruptedException {
        this.setSize(ancho, alto);
        this.setBackground(Color.cyan);

        comedero = new animal[4];
        for (int i = 0; i < 4; i++) {
            comedero[i] = new animal(-1, 'n');
        }

        perroImg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagenes\\perro.png"));
        gatoImg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagenes\\gato.png"));
        comederoImg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagenes\\comedero.png"));

        MediaTracker dibu = new MediaTracker(this);
        dibu.addImage(perroImg, 0);
        dibu.waitForID(0);
        dibu.addImage(gatoImg, 1);
        dibu.waitForID(1);
        dibu.addImage(comederoImg, 2);
        dibu.waitForID(2);
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }

    @Override
    public void paint(Graphics g) {
        Font f1 = new Font("Consolas", Font.BOLD, 20);
        Font f2 = new Font("Tahoma", Font.BOLD, 25);
        int x = 0;

        Image img = createImage(getWidth(), getHeight());
        Graphics og = img.getGraphics();

        //Rectangulos
        og.setColor(Color.white);
        og.fillRect(20, 20, 945, 180);
        og.fillRect(20, 230, 945, 220);
        og.fillRect(20, 480, 945, 180);
        og.setColor(Color.black);
        og.drawRect(20, 20, 945, 180);
        og.drawRect(20, 230, 945, 220);
        og.drawRect(20, 480, 945, 180);

        og.setFont(f2);
        og.drawString("COLA PERROS", 25, 45);
        og.drawString("COMEDERO ", 25, 255);
        for (int i = 0; i < 4; i++) {
            og.drawImage(comederoImg, 240 + (i * 130), 355, null);
        }
        //og.drawImage(rehabilitacionImg, 535, 295, null);
        og.drawString("COLA GATOS", 25, 505);

        //Cola
        og.setFont(f1);
        for (int i = 0; i < colaPerros.size(); i++) {
            og.drawString(" " + colaPerros.get(i), 20 + (100 * i), 90);
            og.drawImage(perroImg, 35 + (100 * i), 90, null);
        }
        for (int j = 0; j < colaGatos.size(); j++) {
            og.drawString(" " + colaGatos.get(j), 20 + (100 * j), 550);
            og.drawImage(gatoImg, 35 + (100 * j), 550, null);
        }

        //Comedero
        og.setColor(Color.BLACK);
        for (int i = 0; i < 4; i++) {
            if (comedero[i].getid() != -1) {
                if (comedero[i].gettipo() == 'P') {
                    og.setColor(Color.red);
                    og.drawImage(perroImg, 240 + (i * 130), 270, null);
                    og.drawString("" + comedero[i].getid(), 230 + (i * 130), 270);
                } else {
                    og.setColor(Color.red);
                    og.drawImage(gatoImg, 240 + (i * 130), 270, null);
                    og.drawString("" + comedero[i].getid(), 230 + (i * 130), 270);
                }
            }
        }

        g.drawImage(img, 0, 0, null);
    }

    public synchronized void encolar(int id, char tipo) {
        if (tipo == 'P') {
            colaPerros.add(id);
        } else {
            colaGatos.add(id);
        }

        repaint();
    }

    public synchronized void desencolar(int id, char tipo) {
        if (tipo == 'P') {
            colaPerros.remove((Object) id);
        } else {
            colaGatos.remove((Object) id);
        }
        repaint();
    }

    public synchronized void enComedero(int id, char tipo) {
        boolean ocupado = true;
        int i = 0;
        while (ocupado) {
            if (comedero[i].getid() == -1) {
                comedero[i].setid(id);
                comedero[i].settipo(tipo);
                ocupado = false;
                repaint();
            }
            i++;
        }
    }

    public synchronized void finComedero(int id) {
        boolean ocupado = true;
        int i = 0;
        while (ocupado) {
            if (comedero[i].getid() == id) {
                comedero[i].setid(-1);
                comedero[i].settipo('n');
                ocupado = false;
                repaint();
            }
            i++;
        }
    }
}
