package libClases;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FicheroEntrada {

    public static void generaFicheroAFD(String estadoInicial, String[] estadosFinales, String[] estadosRestantes, List<TransicionAFD> transiciones, String nombreFichero) throws IOException {

        String contenido = "TIPO: AFD\n" + "ESTADOS: " + estadoInicial;

        for (int i = 0; i < estadosRestantes.length; i++) {
            contenido += (" " + estadosRestantes[i]);
        }

        for (int i = 0; i < estadosFinales.length; i++) {
            contenido += (" " + estadosFinales[i]);
        }

        contenido += "\n";

        contenido += "INICIAL: " + estadoInicial + "\n";

        contenido += "FINALES:";

        for (int i = 0; i < estadosFinales.length; i++) {
            contenido += (" " + estadosFinales[i]);
        }

        contenido += "\n";

        contenido += "TRANSICIONES:\n";

        for (TransicionAFD t : transiciones) {
            contenido += (t.getEstadoInicial() + " '" + t.getSimbolo() + "' " + t.getEstadoFinal() + "\n");
        }

        contenido += "FIN";

        File archivo = new File(nombreFichero + ".afd");
        FileWriter fw = new FileWriter(archivo);
        BufferedWriter bw = new BufferedWriter(fw);

        if (!archivo.exists()) {
            archivo.createNewFile();
        }

        bw.write(contenido);
        bw.close();
        fw.close();
    }

    public static AFD leeFicheroAFD(String nombreFichero) throws FileNotFoundException, UnsupportedEncodingException, IOException {

        String[] estadosFinales = null;
        String estadoInicial = null;
        List<TransicionAFD> transiciones = new ArrayList<TransicionAFD>();
        boolean fin = false;

        if (!new File(nombreFichero).canRead()) {
            System.err.println("ERROR: No se puede leer el archivo " + nombreFichero);
            //System.exit(1);
        }

        Reader reader = new InputStreamReader(new FileInputStream(nombreFichero), "UTF8");
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line = bufferedReader.readLine();

        while (line != null && !fin) {
            
            /*
            if (line.trim().startsWith("FIN")) {
                break;
            }
            */

            if (line.startsWith("INICIAL")) {
                // Dividir la cadena en palabras usando el espacio como delimitador
                String[] palabras = line.split("\\s+");

                // Verificar si hay suficientes palabras y la primera palabra es "INICIAL:"
                if (palabras.length >= 2 && "INICIAL:".equals(palabras[0])) {
                    // Asignar el valor del estado inicial que es la segunda palabra
                    estadoInicial = palabras[1];
                }
            }

            if (line.startsWith("FINALES")) {

                String[] palabras = line.split("\\s+");

                if (palabras.length >= 2 && "FINALES:".equals(palabras[0])) {

                    estadosFinales = Arrays.copyOfRange(palabras, 1, palabras.length);
                }
            }

            if (line.startsWith("TRANSICIONES")) {

                String e1, e2;
                char simbolo;

                line = bufferedReader.readLine();

                while (!line.trim().startsWith("FIN")) {

                    String[] palabras = line.split("\\s+");

                    e1 = palabras[0];

                    simbolo = palabras[1].toCharArray()[1];

                    e2 = palabras[2];

                    transiciones.add(new TransicionAFD(e1, e2, simbolo));

                    line = bufferedReader.readLine();
                }
                fin = true;
            }

            if (!fin) {
                line = bufferedReader.readLine();
            }
        }
        
        System.out.println("\nFichero " + nombreFichero + " cargado correctamente.");

        return new AFD(estadosFinales, estadoInicial, transiciones);
    }

    public static void generaFicheroAFND(String estadoInicial, String[] estadosFinales, String[] estadosRestantes, List<TransicionAFND> transiciones, List<TransicionLambda> transicionesLambda, String nombreFichero) throws IOException {

        String contenido = "TIPO: AFND\n" + "ESTADOS: " + estadoInicial;

        for (String e : estadosRestantes) {
            contenido += (" " + e);
        }

        for (String e : estadosFinales) {
            contenido += (" " + e);
        }

        contenido += "\n";

        contenido += "INICIAL: " + estadoInicial + "\n";

        contenido += "FINALES:";

        for (String e : estadosFinales) {
            contenido += (" " + e);
        }

        contenido += "\n";

        contenido += "TRANSICIONES:\n";

        for (TransicionAFND t : transiciones) {
            contenido += (t.getEstadoInicial() + " '" + t.getSimbolo() + "'");

            for (String estadoFinal : t.getEstadosFinales()) {
                contenido += (" " + estadoFinal);
            }

            contenido += "\n";
        }

        contenido += "TRANSICIONES LAMBDA:\n";

        for (TransicionLambda t : transicionesLambda) {
            contenido += t.getEstadoInicial();

            for (String estadoFinal : t.getEstadosFinales()) {
                contenido += (" " + estadoFinal);
            }

            contenido += "\n";
        }

        contenido += "FIN";

        File archivo = new File(nombreFichero + ".afnd");
        FileWriter fw = new FileWriter(archivo);
        BufferedWriter bw = new BufferedWriter(fw);

        if (!archivo.exists()) {
            archivo.createNewFile();
        }

        bw.write(contenido);
        bw.close();
        fw.close();
    }

    public static AFND leeFicheroAFND(String nombreFichero) throws FileNotFoundException, UnsupportedEncodingException, IOException {

        String estadoInicial = null;
        List<String> estadosFinales = new ArrayList<String>();
        List<TransicionAFND> transiciones = new ArrayList<TransicionAFND>();
        List<TransicionLambda> transicionesλ = new ArrayList<TransicionLambda>();
        boolean fin = false;

        if (!new File(nombreFichero).canRead()) {
            System.err.println("ERROR: No se puede leer el archivo " + nombreFichero);
            //System.exit(1);
        }

        Reader reader = new InputStreamReader(new FileInputStream(nombreFichero), "UTF8");
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line = bufferedReader.readLine();

        while (line != null && !fin) {

            /*
            if (line.trim().startsWith("FIN")) {
                break;
            }
            */

            if (line.startsWith("INICIAL")) {
                // Dividir la cadena en palabras usando el espacio como delimitador
                String[] palabras = line.split("\\s+");

                // Verificar si hay suficientes palabras y la primera palabra es "INICIAL:"
                if (palabras.length >= 2 && "INICIAL:".equals(palabras[0])) {
                    // Asignar el valor del estado inicial que es la segunda palabra
                    estadoInicial = palabras[1];
                }
            }

            if (line.startsWith("FINALES")) {

                String[] palabras = line.split("\\s+");

                if (palabras.length >= 2 && "FINALES:".equals(palabras[0])) {

                    estadosFinales = new ArrayList<>(Arrays.asList(palabras).subList(1, palabras.length));
                }
            }

            if (line.startsWith("TRANSICIONES")) {

                String e1;
                String[] e2;
                char simbolo;

                line = bufferedReader.readLine();

                while (!line.trim().startsWith("TRANSICIONES LAMBDA")) {

                    String[] palabras = line.split("\\s+");

                    e1 = palabras[0];

                    simbolo = palabras[1].toCharArray()[1];

                    e2 = Arrays.copyOfRange(palabras, 2, palabras.length);

                    transiciones.add(new TransicionAFND(e1, e2, simbolo));

                    line = bufferedReader.readLine();
                }
                
                while (!line.trim().startsWith("FIN")) {

                    String[] palabras = line.split("\\s+");

                    e1 = palabras[0];

                    e2 = Arrays.copyOfRange(palabras, 1, palabras.length);

                    transicionesλ.add(new TransicionLambda(e1, e2));

                    line = bufferedReader.readLine();
                }
                fin = true;
            }

            if (!fin) {
                line = bufferedReader.readLine();
            }
        }
        
        System.out.println("\nFichero " + nombreFichero + " cargado correctamente.");

        return new AFND(estadosFinales, estadoInicial, transiciones, transicionesλ);
    }
}
