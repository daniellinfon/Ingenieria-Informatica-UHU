/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica8_pcd;

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
class cliente {

    int id;
    char tipo;

    public cliente(int id, char tipo) {
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

public class CanvasCentro extends Canvas {

    private ArrayList<Integer> colaEfectivo = new ArrayList();
    private ArrayList<Integer> colaTarjeta = new ArrayList();
    private final Image efectivoImg, tarjetaImg, cajaImg;
    private cliente[] caja;
    private boolean fin = false;
    private int tiempoEfectivo = 0, tiempoTarjeta = 0;

    public CanvasCentro(int ancho, int alto) throws InterruptedException {
        this.setSize(ancho, alto);
        this.setBackground(Color.cyan);

        caja = new cliente[4];
        for (int i = 0; i < 4; i++) {
            caja[i] = new cliente(-1, 'n');
        }

        efectivoImg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagenes\\efectivo.png"));
        tarjetaImg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagenes\\tarjeta.png"));
        cajaImg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagenes\\cajero.png"));

        MediaTracker dibu = new MediaTracker(this);
        dibu.addImage(efectivoImg, 0);
        dibu.waitForID(0);
        dibu.addImage(tarjetaImg, 1);
        dibu.waitForID(1);
        dibu.addImage(cajaImg, 2);
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

        if (!fin) {
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
            og.drawString("COLA EFECTIVO", 25, 45);
            og.drawString("CAJAS ", 25, 255);
            for (int i = 0; i < 4; i++) {
                og.drawImage(cajaImg, 240 + (i * 130), 355, null);
            }
            og.drawString("COLA TARJETA", 25, 505);

            //Cola
            og.setFont(f1);
            for (int i = 0; i < colaEfectivo.size(); i++) {
                og.drawString(" " + colaEfectivo.get(i), 20 + (100 * i), 90);
                og.drawImage(efectivoImg, 35 + (100 * i), 90, null);
            }
            for (int j = 0; j < colaTarjeta.size(); j++) {
                og.drawString(" " + colaTarjeta.get(j), 20 + (100 * j), 550);
                og.drawImage(tarjetaImg, 35 + (100 * j), 550, null);
            }

            //Cajas
            og.setColor(Color.BLACK);
            for (int i = 0; i < 4; i++) {
                if (caja[i].getid() != -1) {
                    if (caja[i].gettipo() == 'E') {
                        og.setColor(Color.red);
                        og.drawImage(efectivoImg, 240 + (i * 130), 270, null);
                        og.drawString("" + caja[i].getid(), 230 + (i * 130), 270);
                    } else {
                        og.setColor(Color.red);
                        og.drawImage(tarjetaImg, 240 + (i * 130), 270, null);
                        og.drawString("" + caja[i].getid(), 230 + (i * 130), 270);
                    }
                }
            }
        } else {
            og.setColor(Color.white);
            og.fillRect(20, 230, 945, 220);
            og.setColor(Color.black);
            og.drawRect(20, 230, 945, 220);

            og.setFont(f2);
            og.drawString("Tiempo total en EFECTIVO ---> " + (tiempoEfectivo / 1000) + " segs.", 240, 290);
            og.drawString("Tiempo total en TARJETA ---> " + (tiempoTarjeta / 1000) + " segs.", 240, 375);
            
        }

        g.drawImage(img, 0, 0, null);
    }

    public synchronized void encolar(int id, char tipo) {
        if (tipo == 'E') {
            colaEfectivo.add(id);
        } else {
            colaTarjeta.add(id);
        }

        repaint();
    }

    public synchronized void desencolar(int id, char tipo) {
        if (tipo == 'E') {
            colaEfectivo.remove((Object) id);
        } else {
            colaTarjeta.remove((Object) id);
        }
        repaint();
    }

    public synchronized void enCaja(int id, char tipo) {
        boolean ocupado = true;
        int i = 0;
        while (ocupado) {
            if (caja[i].getid() == -1) {
                caja[i].setid(id);
                caja[i].settipo(tipo);
                ocupado = false;
                repaint();
            }
            i++;
        }
    }

    public synchronized void finCaja(int id) {
        boolean ocupado = true;
        int i = 0;
        while (ocupado) {
            if (caja[i].getid() == id) {
                caja[i].setid(-1);
                caja[i].settipo('n');
                ocupado = false;
                repaint();
            }
            i++;
        }
    }

    public synchronized void tiempoFinal(int tiempoEfectivo, int tiempoTarjeta) {
        this.tiempoEfectivo = tiempoEfectivo;
        this.tiempoTarjeta = tiempoTarjeta;
        fin=true;
        repaint();
    }

}
