package libClases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AFD implements Proceso {

    private String[] estadosFinales; //indica cuales son los estados Finales
    private String estadoInicial;
    private List<TransicionAFD> transiciones; //indica la lista de transiciones del AFD
    private boolean mostrarTransiciones;

    public AFD(String[] estadosFinales, String estadoInicial, List<TransicionAFD> transiciones) {
        this.estadosFinales = estadosFinales;
        this.estadoInicial = estadoInicial;
        this.transiciones = transiciones;
        this.mostrarTransiciones = false;
    }

    /*
    public void load(String filePath) {

    }
     */
    public void agregarTransicion(String e1, char simbolo, String e2) {
        transiciones.add(new TransicionAFD(e1, e2, simbolo));
    }

    public String transicion(String estado, char simbolo) {

        for (TransicionAFD t : transiciones) {

            if (t.getEstadoInicial().equals(estado) && t.getSimbolo() == simbolo) {
                if (mostrarTransiciones) {
                    System.out.println(t.getEstadoInicial() + " + '" + t.getSimbolo() + "' => " + t.getEstadoFinal());
                }

                return t.getEstadoFinal();
            }
        }
        return null;
    }

    @Override
    public boolean esFinal(String estado) {

        for (int i = 0; i < estadosFinales.length; i++) {
            if (estadosFinales[i].equals(estado)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean reconocer(String cadena) {
        Scanner sc = new Scanner(System.in);
        char[] simbolo = cadena.toCharArray();
        String estado = estadoInicial;

        System.out.println("¿Desea mostrar las transiciones? (s/n)");
        char op = sc.next().toLowerCase().charAt(0);

        if (op == 's') {
            mostrarTransiciones = true;
        } else {
            mostrarTransiciones = false;
        }

        for (int i = 0; i < simbolo.length; i++) {
            estado = transicion(estado, simbolo[i]);

            if (estado == null) {
                System.out.println("ERROR: Transición no existente.");
                return false;
            }
        }

        return esFinal(estado);
    }

    public static AFD pedir() throws IOException {

        String nombreFichero;

        Scanner sc = new Scanner(System.in);
        System.out.println("¿Desea introducir por teclado o leer de un fichero existente? (t/f)");
        char op = sc.next().toLowerCase().charAt(0);

        if (op == 't') {
            //Por teclado
            int numEstados, numEstadosFinales;

            System.out.println("Introduce el nombre del nuevo fichero: ");
            nombreFichero = sc.next();

            do {
                System.out.println("Introduce el número de estados: ");
                numEstados = sc.nextInt();
                if (numEstados < 2) {
                    System.out.println("ERROR: El número de estados debe ser mayor que 1");
                }
            } while (numEstados < 2);

            System.out.println("Introduce el estado inicial: ");
            String estadoInicial = sc.next();

            do {
                System.out.println("Introduce el número de estados finales: ");
                numEstadosFinales = sc.nextInt();
                if (numEstadosFinales > (numEstados - 1)) {
                    System.out.println("ERROR: El número de estados finales no puede superar el número de estados restantes");
                }
            } while (numEstadosFinales > (numEstados - 1));

            String[] estadosFinales = new String[numEstadosFinales];

            for (int i = 0; i < numEstadosFinales; i++) {

                System.out.println("Introduce el estado final " + (i + 1) + ": ");
                estadosFinales[i] = sc.next();
            }

            int numEstadosRestantes = (numEstados - (numEstadosFinales + 1));

            String[] estadosRestantes = new String[numEstadosRestantes];

            System.out.println("\n***** Faltan " + numEstadosRestantes + " estados por definir *****\n");

            if (numEstadosRestantes > 0) {

                for (int i = 0; i < numEstadosRestantes; i++) {
                    System.out.println("Introduce el estado " + (i + 1) + ": ");
                    estadosRestantes[i] = sc.next();
                }
            }

            System.out.println("\n¿Quiere añadir una transición? (s/n)");
            op = sc.next().toLowerCase().charAt(0);

            int numTransiciones = 0;
            List<TransicionAFD> transiciones = new ArrayList<TransicionAFD>();

            while (op == 's') {
                numTransiciones++;
                String e1, e2;
                char simbolo;
                boolean existe = true;

                System.out.println("\n***** TRANSICIÓN " + numTransiciones + " *****\n");

                do {

                    System.out.println("\t - Estado origen: ");
                    e1 = sc.next();

                    if (!pertenece(e1, estadosFinales) && !pertenece(e1, estadosRestantes) && !e1.equals(estadoInicial)) {
                        existe = false;
                        System.out.println("ERROR: El estado " + e1 + " no existe.");
                    } else {
                        existe = true;
                    }

                } while (!existe);

                System.out.println("\t - Símbolo de transición: ");
                simbolo = sc.next().toLowerCase().charAt(0);

                do {

                    System.out.println("\t - Estado destino: ");
                    e2 = sc.next();

                    if (!pertenece(e2, estadosFinales) && !pertenece(e2, estadosRestantes) && !e2.equals(estadoInicial)) {
                        existe = false;
                        System.out.println("ERROR: El estado " + e2 + " no existe.");
                    } else {
                        existe = true;
                    }

                } while (!existe);

                transiciones.add(new TransicionAFD(e1, e2, simbolo));

                System.out.println("\n¿Quiere añadir otra transición? (s/n)");
                op = sc.next().toLowerCase().charAt(0);
            }

            FicheroEntrada.generaFicheroAFD(estadoInicial, estadosFinales, estadosRestantes, transiciones, nombreFichero);
        } else {
            System.out.println("Introduce el nombre del fichero: ");
            nombreFichero = sc.next();
        }

        return FicheroEntrada.leeFicheroAFD(nombreFichero + ".afd");
    }

    @Override
    public String toString() {

        return null;
    }

    private static boolean pertenece(String estado, String[] estados) {

        for (String e : estados) {
            if (estado.equals(e)) {
                return true;
            }
        }
        return false;
    }
}
