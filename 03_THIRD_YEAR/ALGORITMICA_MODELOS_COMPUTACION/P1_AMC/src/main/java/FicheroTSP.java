
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import static java.lang.Math.abs;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class FicheroTSP {

    private final String numpuntos;
    private String ruta;
    private final boolean caso;

    public FicheroTSP(String numpuntos, boolean caso) throws IOException {

        this.numpuntos = numpuntos;
        this.caso = caso;
        generaFichero();
        //System.out.println("Archivo generado con éxito...");

    }

    private void generaFichero() throws IOException {
        String inicial = "NAME : dataset" + this.numpuntos + "\n"
                + "TYPE : TSP" + "\n"
                + "COMMENT : " + this.numpuntos + " puntos para ejecutar" + "\n"
                + "DIMENSION : " + this.numpuntos + "\n"
                + "EDGE_WEIGHT_TYPE : EUC_2D\n"
                + "NODE_COORD_SECTION\n";
        int num, den;
        double x, y, aux1, xtrunk, ytrunk;
        Random r = new Random(System.currentTimeMillis());

        if (this.caso) { //PEOR CASO si caso=true
            for (int i = 1; i <= parseInt(numpuntos); i++) {
                aux1 = (r.nextDouble(1000) + 7); //7 y 1007
                y = aux1 / ((double) i + 1 + i * 0.100); //aux2; //+(i/3.0);
                num = r.nextInt(3);
                y += ((i % 500) - num * (r.nextInt(100)));
                x = 1;
                ytrunk = abs(y);
                BigDecimal ybig = new BigDecimal(ytrunk);
                ybig = ybig.setScale(10, RoundingMode.HALF_UP);
                inicial += i + " " + x + " " + ybig + "\n";

            }
        } else { //CASO MEDIO
            for (int i = 1; i <= parseInt(numpuntos); i++) {
                num = (r.nextInt(4000) + 1); //genera un número aleatorio entre 1 y 4000
                den = (r.nextInt(17) + 7); //genera un aleatorio entre 7 y 17
                x = num / ((double) den + 0.37); //division con decimales
                y = ((r.nextInt(4000) + 1)) / ((double) (r.nextInt(11) + 7) + 0.37);
                xtrunk = abs(x);
                ytrunk = abs(y);
                BigDecimal xbig = new BigDecimal(xtrunk);
                BigDecimal ybig = new BigDecimal(ytrunk);
                xbig = xbig.setScale(10, RoundingMode.HALF_UP);
                ybig = ybig.setScale(10, RoundingMode.HALF_UP);
                inicial += i + " " + xbig + " " + ybig + "\n";
            }
        }
        inicial += "EOF";

        File archivo;

        if (caso == true) {
            archivo = new File("dataset" + this.numpuntos + "on.tsp");
        } else {
            archivo = new File("dataset" + this.numpuntos + "off.tsp");
        }

        FileWriter fw = new FileWriter(archivo);
        BufferedWriter bw = new BufferedWriter(fw);
        if (!archivo.exists()) {
            archivo.createNewFile();

        }

        bw.write(inicial);
        bw.close();
        fw.close();

    }

    public void escribeFichero(Punto puntos[], String nombre) throws IOException {
        String inicial = "NAME : dataset" + this.numpuntos + "\n"
                + "TYPE : TSP" + "\n"
                + "COMMENT : " + this.numpuntos + " puntos para ejecutar" + "\n"
                + "DIMENSION : " + this.numpuntos + "\n"
                + "EDGE_WEIGHT_TYPE : EUC_2D\n"
                + "NODE_COORD_SECTION\n";

        for (int i = 0; i < parseInt(numpuntos); i++) {

            inicial += i + " " + puntos[i].getx() + " " + puntos[i].gety() + "\n";

        }
        inicial += "EOF";

        File archivo;

        if (caso == true) {
            archivo = new File(nombre + "on.tsp");
        } else {
            archivo = new File(nombre + "off.tsp");
        }

        FileWriter fw = new FileWriter(archivo);
        BufferedWriter bw = new BufferedWriter(fw);
        if (!archivo.exists()) {
            archivo.createNewFile();

        }

        bw.write(inicial);
        bw.close();
        fw.close();

    }

    public void generaFicheroSolucion(Punto puntos[], double distanciaTotal) throws IOException, Exception {

        String inicial = "NAME : dataset" + this.numpuntos + ".tour" + "\n"
                + "TYPE : TOUR" + "\n"
                + "DIMENSION : " + this.numpuntos + "\n"
                + "SOLUTION : " + distanciaTotal + "\n"
                + "TOUR_SECTION\n";

        inicial += puntos[0].getEtiqueta();
        for (int i = 1; i < puntos.length; i++) {

            inicial += "," + puntos[i].getEtiqueta();

        }
        inicial += "\n";

        for (int i = 0; i < puntos.length - 1; i++) {

            inicial += ((float) this.dPuntos(puntos[i], puntos[i + 1])) + " - " + puntos[i].getEtiqueta() + "," + puntos[i + 1].getEtiqueta() + "\n";

        }

        inicial += "EOF";

        File archivo = new File("dataset" + numpuntos + ".tour");
        FileWriter fw = new FileWriter(archivo);
        BufferedWriter bw = new BufferedWriter(fw);
        if (!archivo.exists()) {
            archivo.createNewFile();

        }

        bw.write(inicial);
        bw.close();
        fw.close();

    }

    private double dPuntos(Punto p1, Punto p2) {

        return sqrt(pow((p1.getx() - p2.getx()), 2) + pow((p1.gety() - p2.gety()), 2));
    }

    public void borraFichero(String nombre) {
        
        File archivo = new File(nombre);
        archivo.delete();
    }
}
