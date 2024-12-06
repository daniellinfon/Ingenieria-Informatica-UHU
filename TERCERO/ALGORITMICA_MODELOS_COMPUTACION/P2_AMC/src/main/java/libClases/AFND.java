package libClases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class AFND {

    private List<String> estadosFinales;
    private String estadoInicial;
    private List<TransicionAFND> transiciones;
    private List<TransicionLambda> transicionesλ;
    private boolean mostrarTransiciones;

    public AFND(List<String> estadosFinales, String estadoInicial, List<TransicionAFND> transiciones, List<TransicionLambda> transicionesλ) {
        this.estadosFinales = estadosFinales;
        this.estadoInicial = estadoInicial;
        this.transiciones = transiciones;
        this.transicionesλ = transicionesλ;
        this.mostrarTransiciones = false;
    }

    public void agregarTransicion(String e1, char simbolo, String[] e2) {
        transiciones.add(new TransicionAFND(e1, e2, simbolo));
    }

    public void agregarTransicion(String e1, String[] e2) {
        transicionesλ.add(new TransicionLambda(e1, e2));
    }

    private Set<String> transicion(String estado, char simbolo) {
        Set<String> nuevosEstados = new HashSet<>();

        for (TransicionAFND transicion : transiciones) {
            if (transicion.getEstadoInicial().equals(estado) && transicion.getSimbolo() == simbolo) {
                if (mostrarTransiciones) {
                    System.out.print(transicion.getEstadoInicial() + " + '" + transicion.getSimbolo() + "' => ");
                }

                for (String estadoDestino : transicion.getEstadosFinales()) {
                    System.out.print(estadoDestino + " ");
                    nuevosEstados.add(estadoDestino);
                }
                System.out.println();
            }
        }

        // Calcular la clausura lambda de los nuevos estados
        Set<String> clausuraLambda = new HashSet<>();
        for (String nuevoEstado : nuevosEstados) {
            clausuraLambda.addAll(λ_clausura(Set.of(nuevoEstado)));
        }

        return clausuraLambda;
    }

    public Set<String> transicion(Set<String> macroestado, char simbolo) {
        Set<String> nuevosEstados = new HashSet<>();

        for (String estado : macroestado) {
            Set<String> nuevosEstadosIndividuales = transicion(estado, simbolo);
            nuevosEstados.addAll(nuevosEstadosIndividuales);
        }

        // Calcular la clausura lambda de los nuevos estados
        Set<String> clausuraLambda = new HashSet<>();
        for (String nuevoEstado : nuevosEstados) {
            clausuraLambda.addAll(λ_clausura(Set.of(nuevoEstado)));
        }

        return clausuraLambda;
    }

    public Set<String> transicionλ(String estado) {
        Set<String> nuevosEstados = new HashSet<>();

        for (TransicionLambda transicionLambda : transicionesλ) {
            if (transicionLambda.getEstadoInicial().equals(estado)) {
                for (String estadoDestino : transicionLambda.getEstadosFinales()) {
                    nuevosEstados.add(estadoDestino);
                }
            }
        }

        return nuevosEstados;
    }

    private Set<String> λ_clausura(Set<String> macroestado) {
        Set<String> clausuraLambda = new HashSet<>(macroestado);

        Set<String> estadosPendientes = new HashSet<>(clausuraLambda);
        while (!estadosPendientes.isEmpty()) {
            String estadoActual = estadosPendientes.iterator().next();
            estadosPendientes.remove(estadoActual);

            // Calcular transición lambda para el estado actual
            Set<String> nuevosEstados = transicionλ(estadoActual);
            for (String nuevoEstado : nuevosEstados) {
                if (!clausuraLambda.contains(nuevoEstado)) {
                    clausuraLambda.add(nuevoEstado);
                    estadosPendientes.add(nuevoEstado);
                }
            }
        }

        return clausuraLambda;
    }

    private boolean esFinal(String estado) {
        return estadosFinales.contains(estado);
    }

    public boolean esFinal(Set<String> macroestado) {
        for (String estado : macroestado) {
            if (esFinal(estado)) {
                return true;
            }
        }
        return false;
    }

    public boolean reconocer(String cadena) {
        Scanner sc = new Scanner(System.in);
        char[] simbolos = cadena.toCharArray();
        String estado = estadoInicial;
        Set<String> macroestado = λ_clausura(new HashSet<>(List.of(estado)));

        System.out.println("¿Desea mostrar las transiciones? (s/n)");
        char op = sc.next().toLowerCase().charAt(0);

        if (op == 's') {
            mostrarTransiciones = true;
            for (char simbolo : simbolos) {
                System.out.print("Estados actuales: ");
                for (String e : macroestado) {
                    System.out.print(e + " ");
                }
                System.out.println("\n");

                macroestado = transicion(macroestado, simbolo);
            }

            System.out.print("Estados actuales: ");
            for (String e : macroestado) {
                System.out.print(e + " ");
            }
        } else {
            mostrarTransiciones = false;
        }

        return esFinal(macroestado);
    }

    public static AFND pedir() throws IOException {

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

            // DEFINIMOS LAS TRANSICIONES NORMALES
            System.out.println("\n¿Quiere añadir una transición? (s/n)");
            op = sc.next().toLowerCase().charAt(0);

            int numTransiciones = 0;
            List<TransicionAFND> transiciones = new ArrayList<TransicionAFND>();

            while (op == 's') {
                numTransiciones++;
                String e1;
                String[] e2;
                char simbolo;
                boolean existe = true;
                int numEstadosDestino;

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
                    System.out.println("Introduce el número de estados destino: ");
                    numEstadosDestino = sc.nextInt();
                    if (numEstadosDestino > (numEstados - 1)) {
                        System.out.println("ERROR: El número de estados destino no puede superar el número de estados restantes");
                    }
                    if (numEstadosDestino < 1) {
                        System.out.println("ERROR: El número de estados destino debe ser como mínimo 1");
                    }
                } while ((numEstadosDestino > (numEstados - 1)) || (numEstadosDestino < 1));

                e2 = new String[numEstadosDestino];

                for (int i = 0; i < numEstadosDestino; i++) {
                    do {
                        System.out.println("\t - Estado destino " + (i + 1) + ": ");
                        e2[i] = sc.next();

                        if (!pertenece(e2[i], estadosFinales) && !pertenece(e2[i], estadosRestantes) && !e2[i].equals(estadoInicial)) {
                            existe = false;
                            System.out.println("ERROR: El estado " + e2[i] + " no existe.");
                        } else {
                            existe = true;
                        }
                    } while (!existe);
                }

                transiciones.add(new TransicionAFND(e1, e2, simbolo));

                System.out.println("\n¿Quiere añadir otra transición? (s/n)");
                op = sc.next().toLowerCase().charAt(0);
            }

            // DEFINIMOS LAS TRANSICIONES LAMBDA
            System.out.println("\n¿Quiere añadir una transición LAMBDA? (s/n)");
            op = sc.next().toLowerCase().charAt(0);

            int numTransicionesLambda = 0;
            List<TransicionLambda> transicionesLambda = new ArrayList<TransicionLambda>();

            while (op == 's') {
                numTransicionesLambda++;
                String e1;
                String[] e2;
                boolean existe = true;
                int numEstadosDestino;

                System.out.println("\n***** TRANSICIÓN LAMBDA " + numTransiciones + " *****\n");

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

                do {
                    System.out.println("Introduce el número de estados destino: ");
                    numEstadosDestino = sc.nextInt();
                    if (numEstadosDestino > (numEstados - 1)) {
                        System.out.println("ERROR: El número de estados destino no puede superar el número de estados restantes");
                    }
                    if (numEstadosDestino < 1) {
                        System.out.println("ERROR: El número de estados destino debe ser como mínimo 1");
                    }
                } while ((numEstadosDestino > (numEstados - 1)) || (numEstadosDestino < 1));

                e2 = new String[numEstadosDestino];

                for (int i = 0; i < numEstadosDestino; i++) {
                    do {
                        System.out.println("\t - Estado destino " + (i + 1) + ": ");
                        e2[i] = sc.next();

                        if (!pertenece(e2[i], estadosFinales) && !pertenece(e2[i], estadosRestantes) && !e2[i].equals(estadoInicial)) {
                            existe = false;
                            System.out.println("ERROR: El estado " + e2[i] + " no existe.");
                        } else {
                            existe = true;
                        }
                    } while (!existe);
                }

                transicionesLambda.add(new TransicionLambda(e1, e2));

                System.out.println("\n¿Quiere añadir otra transición LAMBDA? (s/n)");
                op = sc.next().toLowerCase().charAt(0);
            }

            FicheroEntrada.generaFicheroAFND(estadoInicial, estadosFinales, estadosRestantes, transiciones, transicionesLambda, nombreFichero);

        } else {
            System.out.println("Introduce el nombre del fichero: ");
            nombreFichero = sc.next();
        }

        return FicheroEntrada.leeFicheroAFND(nombreFichero + ".afnd");
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
