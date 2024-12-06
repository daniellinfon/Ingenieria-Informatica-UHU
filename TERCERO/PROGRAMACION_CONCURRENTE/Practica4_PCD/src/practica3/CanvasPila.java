/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica3;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author danie
 */
public class CanvasPila extends Canvas {

    private int cima;
    private final int capacidad;
    private int numelementos;
    private Object[] datos;
    private String mensaje = null;

    public CanvasPila(int capacidad) {
        this.capacidad = capacidad;
        cima = 0;
        numelementos = 0;
        datos = new Object[capacidad];
        this.setSize(400, 400);
        this.setBackground(Color.CYAN);
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }

    public void avisa(String mensaje) {
        this.mensaje = mensaje;
        repaint();
    }

    public void representa(Object buf[], int cima, int numele) {
        datos = buf;
        this.cima = cima;
        numelementos = numele;
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        Font f1 = new Font("Consolas", Font.BOLD, 30);
        int x = 0;

        Image img = createImage(getWidth(), getHeight());
        Graphics og = img.getGraphics();

        og.setFont(f1);
        og.setColor(Color.black);

        if (datos != null) {
            og.drawString("Contenido de la pila:", 20, 50);
            for (int i = 0; i < numelementos; i++) {
                x = 20 + 50 * i;
                og.drawString(datos[i].toString(), x, 100);
            }

            og.drawString("Cima ", x, 130);
            og.setFont(f1);
            og.setColor(Color.black);
            og.drawString("Numero de elementos: " + String.valueOf(numelementos), 120, 520);
            og.drawString("Capacidad restante: " + String.valueOf(capacidad - numelementos), 120, 560);
            

            if (mensaje != null && (numelementos == capacidad || numelementos == 0)) {
                og.setFont(f1);
                og.setColor(Color.red);
                if (numelementos == capacidad) {
                    og.drawString(mensaje, 120, 440);
                    og.fillOval(93, 420, 13, 13);
                    og.setColor(Color.white);
                    og.drawString("PILA VACIA", 120, 480);
                } else if (numelementos == 0) {
                    og.drawString(mensaje, 120, 480);
                    og.fillOval(93, 460, 13, 13);
                    og.setColor(Color.white);
                    og.drawString("PILA LLENA", 120, 440);
                }
            } else {
                og.setColor(Color.white);
                og.drawString("PILA LLENA", 120, 440);
                og.drawString("PILA VACIA", 120, 480);
            }

        }
        g.drawImage(img, 0, 0, null);
    }

}
