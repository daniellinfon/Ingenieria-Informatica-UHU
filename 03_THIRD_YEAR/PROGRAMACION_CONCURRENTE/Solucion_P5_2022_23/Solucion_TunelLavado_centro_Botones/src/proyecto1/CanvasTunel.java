/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pedro
 */
class vehiculo {

    int id;
    char tipo;

    public vehiculo(int id, char tipo) {
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

public class CanvasTunel extends Canvas {

    private Image furgoimg, cocheimg, tunelimg;
    private ArrayList<Integer> colacoches = new ArrayList();
    private ArrayList<Integer> colafurgos = new ArrayList();
    private vehiculo[] laterales = new vehiculo[2];
    private vehiculo central;

    public CanvasTunel(int ancho, int alto) throws InterruptedException {

        super.setSize(ancho, alto);
        super.setBackground(Color.white);
        central = new vehiculo(-1, 'n');
        laterales[0] = new vehiculo(-1, 'n');
        laterales[1] = new vehiculo(-1, 'n');

        cocheimg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagenes/coche_peque.png"));
        furgoimg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagenes/furgo_peque.png"));
        tunelimg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagenes/tunel2.png"));

        MediaTracker dibu = new MediaTracker(this);
        dibu.addImage(cocheimg, 0);
        dibu.waitForID(0);
        dibu.addImage(furgoimg, 1);
        dibu.waitForID(1);
        dibu.addImage(tunelimg, 2);
        dibu.waitForID(2);

    }

    public synchronized void  enconlacoches(int id) {
        colacoches.add(id);
        repaint();
    }

    public synchronized void fincolacoches(int id) {
        colacoches.remove((Object) id);
        repaint();
    }

    public synchronized void enconlafurgos(int id) {
        colafurgos.add(id);
        repaint();
    }

    public synchronized void fincolafurgos(int id) {
        colafurgos.remove((Object) id);
        repaint();
    }

    public synchronized void encentro(int id, char tipo) {
        central.setid(id);
        central.settipo(tipo);
        repaint();
    }

    public synchronized void fincentro() {
        central.setid(-1);
        central.settipo('n');
        repaint();
    }

    public synchronized void enlateral(int id, char tipo) {
        if (laterales[0].getid() == -1) {
            laterales[0].setid(id);
            laterales[0].settipo(tipo);
        } else {
            laterales[1].setid(id);
            laterales[1].settipo(tipo);
        }
        repaint();
    }

    public synchronized void finlateral(int id) {
        if (laterales[0].getid() == id) {
            laterales[0].setid(-1);
            laterales[0].settipo('n');
        } else {
            laterales[1].setid(-1);
            laterales[1].settipo('n');
        }
        repaint();
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }

    @Override
    public  void paint(Graphics g) {
        Image imagen = createImage(getWidth(), getHeight());
        Font f1 = new Font("Arial", Font.BOLD, 12);
        Graphics gbuf = imagen.getGraphics();
        gbuf.setFont(f1);

        gbuf.setColor(Color.cyan);
        gbuf.fillRect(280, 50, 600, 80);
        gbuf.setColor(Color.red);
        for (int i = 0; i < colacoches.size(); i++) {
            gbuf.drawString(" " + colacoches.get(i), 310 + 80 * i, 80);
            gbuf.drawImage(cocheimg, 300 + 80 * i, 80, null);
        }
        gbuf.setColor(Color.pink);
        gbuf.fillRect(280, 250, 600, 80);
        gbuf.setColor(Color.blue);
        for (int i = 0; i < colafurgos.size(); i++) {
            gbuf.drawString(" " + colafurgos.get(i), 310 + 80 * i, 280);
            gbuf.drawImage(furgoimg, 300 + 80 * i, 280, null);
        }

        gbuf.setColor(Color.BLACK);
        gbuf.drawImage(tunelimg, 40, 10, null);
        gbuf.drawImage(tunelimg, 40, 130, null);
        gbuf.drawImage(tunelimg, 40, 250, null);

        if (central.getid() != -1) {
            if (central.gettipo() == 'c') {
                gbuf.setColor(Color.red);
                gbuf.drawImage(cocheimg, 75, 190, null);

            } else {
                gbuf.setColor(Color.blue);
                gbuf.drawImage(furgoimg, 75, 190, null);
            }

            gbuf.drawString("" + central.getid(), 80, 200);
        }

        if (laterales[0].getid() != -1) {
            if (laterales[0].gettipo() == 'c') {
                gbuf.setColor(Color.red);
                gbuf.drawImage(cocheimg, 75, 75, null);

            } else {
                gbuf.setColor(Color.blue);
                gbuf.drawImage(furgoimg, 75, 75, null);
            }
            gbuf.drawString("" + laterales[0].getid(), 80, 80);

        }

        if (laterales[1].getid() != -1) {
            if (laterales[1].gettipo() == 'c') {
                gbuf.setColor(Color.red);
                gbuf.drawImage(cocheimg, 75, 310, null);
            } else {
                gbuf.drawImage(furgoimg, 75, 310, null);
                gbuf.setColor(Color.blue);
            }
            gbuf.drawString("" + laterales[1].getid(), 80, 320);
        }

        g.drawImage(imagen, 0, 0, this);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(CanvasTunel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
