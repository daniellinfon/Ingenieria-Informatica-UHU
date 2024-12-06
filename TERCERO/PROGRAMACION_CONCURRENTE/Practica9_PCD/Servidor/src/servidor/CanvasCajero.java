package servidor;



import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.util.ArrayList;

public class CanvasCajero extends Canvas {

    class cliente {

        private int id;
        private char tipo;

        public cliente(int id, char tipo) {
            this.id = id;
            this.tipo = tipo;

        }

        public int getId() {
            return id;
        }

        public char getTipo() {
            return tipo;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setTipo(char tipo) {
            this.tipo = tipo;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final cliente c = (cliente) obj;
            if (c.getId() == this.getId() && c.getTipo() == this.getTipo()) {
                return true;
            } else {
                return false;
            }
        }

    }

    private ArrayList<Integer> colaefectivo = new ArrayList();
    private ArrayList<Integer> colatarjeta = new ArrayList();
    //private ArrayList<animal> comedero = new ArrayList();
    private cliente[] cajero = new cliente[4];

    Image efectivoimg, tarjetaimg, cajeroimg;

    public CanvasCajero() throws InterruptedException {
        super.setSize(850, 600);
        super.setBackground(Color.white);

        efectivoimg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagenes/efectivoimg.png"));
        tarjetaimg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagenes/tarjetaimg.png"));
        cajeroimg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagenes/cajeroimg.png"));

        MediaTracker dibu = new MediaTracker(this);
        dibu.addImage(efectivoimg, 0);
        dibu.waitForID(0);
        dibu.addImage(tarjetaimg, 1);
        dibu.waitForID(1);
        dibu.addImage(cajeroimg, 2);
        dibu.waitForID(2);
        
        for (int i = 0; i < cajero.length; i++) {
            cajero[i] = new cliente(-1, 'n');
        }

    }

    public void encolaefectivo(int id) {
        colaefectivo.add(id);
        repaint();
    }

    public void fincolaefectivo(int id) {
        colaefectivo.remove((Object) id);
        repaint();
    }

    public void encolatarjeta(int id) {
        colatarjeta.add(id);
        repaint();
    }

    public void fincolatarjeta(int id) {
        colatarjeta.remove((Object) id);
        repaint();
    }

    public void encajero(int id, char tipo) {
        //comedero.add(new animal(id, tipo));
        int i = 0;
        boolean añadido = false;
        while (!añadido && i < cajero.length) {
            if (cajero[i].getId() == -1) {
                cajero[i].setId(id);
                cajero[i].setTipo(tipo);
                añadido = true;
            } else {
                i++;
            }
            repaint();
        }

    }

    public void fincajero(int id, char tipo) {
        //animal a = new animal(id, tipo);
        //comedero.remove(a);
        int i = 0;
        boolean borrado = false;
        while (!borrado && i < cajero.length) {
                if (cajero[i].equals(new cliente(id, tipo))) {
                    cajero[i].setId(-1);
                    borrado = true;
                } else {
                    i++;
                }
            
            repaint();
        }
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }

    @Override
    public void paint(Graphics g) {
        Image imagen = createImage(getWidth(), getHeight());
        Font f1 = new Font("Arial", Font.BOLD, 12);
        Graphics gbuf = imagen.getGraphics();
        gbuf.setFont(f1);

        gbuf.setColor(Color.LIGHT_GRAY);
        gbuf.fillRect(0, 0, 851, 150);
        gbuf.fillRect(0, 411, 851, 151);

        gbuf.setColor(Color.black);
        for (int i = 0; i < colaefectivo.size(); i++) {
            gbuf.drawString(" " + colaefectivo.get(i), 50 + 90 * i, 12);
            gbuf.drawImage(efectivoimg, -95 + 90 * i, 0, null);
        }

        for (int i = 0; i < colatarjeta.size(); i++) {
            gbuf.drawString(" " + colatarjeta.get(i), 75 + 80 * i, 410);
            gbuf.drawImage(tarjetaimg, -100 + 80 * i, 410, null);
        }

        gbuf.drawImage(cajeroimg, -140, 145, null);
        gbuf.drawImage(cajeroimg, 60, 145, null);
        gbuf.drawImage(cajeroimg, 260, 145, null);
        gbuf.drawImage(cajeroimg, 460, 145, null);

        /*for (int i = 0; i < comedero.size(); i++) {
            
            if(comedero.get(i).getTipo()=='G')
            {
                gbuf.drawString(" " + comedero.get(i).getId(), 165 + 140 * i, 185);
                gbuf.drawImage(gatoimg, 68 + 140 * i, 185, null);
            }
            else
            {
                gbuf.drawString(" " + comedero.get(i).getId(), 140 + 140 * i, 165);
                gbuf.drawImage(perroimg, 120 + 140 * i, 175, null);
            }
                
        }*/
        for (int i = 0; i < cajero.length; i++) {
            if (cajero[i].getId() != -1) {
                if (cajero[i].getTipo() == 'E') {
                    gbuf.drawString(" " + cajero[i].getId(), 163 + 194 * i, 170);
                    gbuf.drawImage(efectivoimg, 15 + 195 * i, 165, null);
                } else {
                    gbuf.drawString(" " + cajero[i].getId(), 140 + 197 * i, 170);
                    gbuf.drawImage(tarjetaimg, -40 + 200 * i, 175, null);
                }

            }

        }
        g.drawImage(imagen, 0, 0, this);
    }
}
