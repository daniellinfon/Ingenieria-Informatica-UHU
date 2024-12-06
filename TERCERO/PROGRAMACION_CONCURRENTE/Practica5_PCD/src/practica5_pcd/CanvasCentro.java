package practica5_pcd;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
 * @author danie
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

    private ArrayList<Integer> colaclientes = new ArrayList();
    private ArrayList<Character> colaclientes2 = new ArrayList();
    private final Image clienteImg, masajeImg, rehabilitacionImg, vestuarioImg;
    private cliente masaje, rehab, vestuario;

    public CanvasCentro(int ancho, int alto) throws InterruptedException {
        this.setSize(ancho, alto);
        this.setBackground(Color.cyan);
        masaje = new cliente(-1, 'n');
        rehab = new cliente(-1, 'n');
        vestuario = new cliente(-1,'n');

        clienteImg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagenes\\cliente.png"));
        masajeImg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagenes\\masaje.png"));
        rehabilitacionImg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagenes\\rehabilitacion.png"));
        vestuarioImg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagenes\\taquilla.jpg"));

        MediaTracker dibu = new MediaTracker(this);
        dibu.addImage(clienteImg, 0);
        dibu.waitForID(0);
        dibu.addImage(masajeImg, 1);
        dibu.waitForID(1);
        dibu.addImage(rehabilitacionImg, 2);
        dibu.waitForID(2);
        dibu.addImage(vestuarioImg, 3);
        dibu.waitForID(3);
    }

    public synchronized void encolar(int id, char tipo) {
        colaclientes.add(id);
        colaclientes2.add(tipo);
        repaint();
    }

    public synchronized void desencolar(int id, char tipo) {
        colaclientes.remove((Object) id);
        colaclientes2.remove((Object) tipo);
        repaint();
    }

    public synchronized void enMasaje(int id) {
        masaje.setid(id);
        repaint();
    }

    public synchronized void enFisio(int id, char tipo) {
        rehab.setid(id);
        rehab.settipo(tipo);
        repaint();
    }

    public synchronized void enVestuario(int id, char tipo) {
        vestuario.setid(id);
        vestuario.settipo(tipo);
        repaint();
    }

    public synchronized void finMasaje() {
        masaje.setid(-1);
        masaje.settipo('n');
        repaint();
    }

    public synchronized void finRehab() {
        rehab.setid(-1);
        rehab.settipo('n');
        repaint();
    }

    public synchronized void finVestuario() {
        vestuario.setid(-1);
        vestuario.settipo('n');
        repaint();
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
        og.fillRect(20, 230, 465, 220);
        og.fillRect(500, 230, 465, 220);
        og.fillRect(20, 480, 945, 180);
        og.setColor(Color.black);
        og.drawRect(20, 20, 945, 180);
        og.drawRect(20, 230, 465, 220);
        og.drawRect(500, 230, 465, 220);
        og.drawRect(20, 480, 945, 180);

        og.setFont(f2);
        og.drawString("SALA DE ESPERA", 25, 45);
        og.drawString("MASAJISTA", 25, 255);
        og.drawImage(masajeImg, 25, 295, null);
        og.drawString("FISIOTERAPEUTA", 505, 255);
        og.drawImage(rehabilitacionImg, 535, 295, null);
        og.drawString("VESTUARIO", 25, 505);
        og.drawImage(vestuarioImg, 50, 545, null);
        og.drawImage(vestuarioImg, 800, 545, null);

        //Cola de clientes
        og.setFont(f1);
        for (int i = 0; i < colaclientes.size(); i++) {
            og.drawString(" " + colaclientes.get(i), 20 + 100 * i, 90);
            og.drawImage(clienteImg, 25 + 100 * i, 90, null);

            if (colaclientes2.get(i) == 'm') {
                og.drawString("M", 60 + 100 * i, 160);
            } else {
                og.drawString("R", 60 + 100 * i, 160);
            }
        }

        //Masajista
        if (masaje.getid() != -1) {
            og.drawString("M", 285, 390);
            og.setColor(Color.red);
            og.drawImage(clienteImg, 250, 320, null);
            og.drawString("" + masaje.getid(), 245, 320);
        }

        //Fisio
        og.setColor(Color.BLACK);
        if (rehab.getid() != -1) {
            if (rehab.gettipo() == 'm') {
                og.drawString("M", 785, 390);
                og.setColor(Color.red);
                og.drawImage(clienteImg, 750, 320, null);
                og.drawString("" + rehab.getid(), 745, 320);
            } else {
                og.drawString("R", 785, 390);
                og.setColor(Color.red);
                og.drawImage(clienteImg, 750, 320, null);
                og.drawString("" + rehab.getid(), 745, 320);
            }
        }

        //Vestuario
        og.setColor(Color.BLACK);
        if (vestuario.getid() != -1) {
            if (vestuario.gettipo() == 'm') {
                og.drawString("M", 485, 620);
                og.setColor(Color.red);
                og.drawImage(clienteImg, 450, 550, null);
                og.drawString("" + vestuario.getid(), 445, 550);
            } else {
                og.drawString("R", 485, 620);
                og.setColor(Color.red);
                og.drawImage(clienteImg, 450, 550, null);
                og.drawString("" + vestuario.getid(), 445, 550);
            }
        }

        g.drawImage(img, 0, 0, null);
    }

}
