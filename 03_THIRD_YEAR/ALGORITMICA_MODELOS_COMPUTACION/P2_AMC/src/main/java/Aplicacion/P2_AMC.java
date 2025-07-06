package Aplicacion;

import java.io.IOException;
import java.util.Scanner;
import libClases.*;

public class P2_AMC {

    public static void main(String[] args) {

        int num, opc, automata = 0; // automata = 1 -> está cargado un AFD; automata = 2 -> está cargado un AFND
        Scanner input = new Scanner(System.in);
        AFD automataFD = null;
        AFND automataFND = null;
        String cadena;

        do {
            System.out.println("\n\n\t\t*** AMC | Practica 2 | Curso 23/24 ***");
            System.out.println("\t\tMiguel Quiroga - Daniel Linfon");
            System.out.println("\n\t\t1.- Cargar autómata");
            System.out.println("\t\t2.- Analizar cadena");
            System.out.println("\t\t0.- Salir");
            System.out.println("\t\t------------------");
            System.out.print("\t\tElige una opción: ");
            opc = input.nextInt();

            switch (opc) {

                case 1:
                    do {
                        System.out.println("\n¿Desea cargar un AFD(1) o un AFND(2)? ");
                        num = input.nextInt();
                    } while (num != 1 && num != 2);

                    try {
                        if (num == 1) {
                            automataFD = AFD.pedir();
                            automata = 1;
                        } else {
                            automataFND = AFND.pedir();
                            automata = 2;
                        }
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;

                case 2:
                    if (automata == 0) {
                        System.out.println("\nERROR: Debes cargar primero un autómata.");
                    } else {
                        System.out.println("\nIntroduce la cadena a analizar: ");
                        cadena = input.next();

                        if (automata == 1) {
                            if (automataFD.reconocer(cadena)) {
                                System.out.println("\nCADENA ACEPTADA");
                            } else {
                                System.out.println("\nCADENA RECHAZADA");
                            }
                        } else {
                            if (automataFND.reconocer(cadena)) {
                                System.out.println("\nCADENA ACEPTADA");
                            } else {
                                System.out.println("\nCADENA RECHAZADA");
                            }
                        }
                    }
                    break;

                case 0:
                    System.out.println("\n\t\tSaliendo del Programa...");
                    break;

                default:
                    System.out.println("\nERROR: Opción no válida");
                    break;
            }
        } while (opc != 0);
    }
}
