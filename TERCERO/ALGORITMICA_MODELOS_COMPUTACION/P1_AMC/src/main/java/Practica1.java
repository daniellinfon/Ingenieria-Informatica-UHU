
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Math.abs;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;
import java.io.PrintWriter;

public class Practica1 {

    public static void main(String[] args) throws IOException, Exception {

        Scanner input = new Scanner(System.in);
        String dataset = "dataset657.tsp", aux;
        String nPuntos;
        Algoritmos a = new Algoritmos(dataset);
        FicheroTSP p;
        Resultado r = null;

        boolean peorCasoActivado = false;
        int opcion, opc;

        do {
            System.out.println("\n\n\t\t*** AMC | Practica 1 | Curso 23/24 ***");
            System.out.println("\t\tMiguel Quiroga - Pablo Fernández - Daniel Linfon - Pablo Gómez");
            System.out.println("\n\t\t1.- PRACTICA 1A");
            System.out.println("\t\t2.- PRACTICA 1B");
            System.out.println("\t\t0.- Salir");
            System.out.println("\t\t------------------");
            System.out.print("\t\tElige una opción: ");
            opc = input.nextInt();

            switch (opc) {
                case 1:                         //PRACTICA 1A
                    do {

                        System.out.println("\n\n\t\t*** AMC | Practica 1A | Curso 23/24 ***");
                        System.out.println("\t\tMiguel Quiroga - Pablo Fernández - Daniel Linfon - Pablo Gómez");
                        System.out.println("\n\t\t\t\t\t DATASET: " + dataset);
                        System.out.println("\t\t\t\t\t PEOR CASO: " + (peorCasoActivado ? "ON" : "OFF"));
                        System.out.println("\n\t\t***     MENÚ PRINCIPAL 1A     ***");
                        System.out.println("\n\t\t1.- CREAR FICHERO TSP ALEATORIO");
                        System.out.println("\t\t2.- CARGAR UN DATASET EN MEMORIA");
                        System.out.println("\t\t3.- COMPROBAR ESTRATEGIAS");
                        System.out.println("\t\t4.- COMPARAR TODAS LAS ESTRATEGIAS");
                        System.out.println("\t\t5.- COMPARAR 2 ESTRATEGIAS");
                        System.out.println("\t\t6.- ACTIVAR/DESACTIVAR PEOR CASO (TODOS LOS PUNTOS EN LA MISMA VERTICAL)");
                        System.out.println("\t\t0.- Salir");
                        System.out.println("\t\t---------");
                        System.out.print("\t\tElige una opción: ");

                        opcion = input.nextInt();

                        switch (opcion) {
                            case 1:
                                // Implementa la lógica para la opción 1 aquí
                                System.out.print("\nIntroduce el numero de puntos que debe generar el fichero -->");

                                nPuntos = input.next();

                                p = new FicheroTSP(nPuntos, peorCasoActivado);

                                break;

                            case 2:
                                // Implementa la lógica para la opción 2 aquí
                                int op;

                                do {
                                    System.out.println("\n***     SUBMENÚ OPCIÓN 2     ***");
                                    System.out.println("\n1.- CREAR Y CARGAR DATASET EN MEMORIA");
                                    System.out.println("2.- CARGAR DATASET EN MEMORIA");
                                    System.out.println("0.- Salir");
                                    System.out.println("----------------------------");
                                    System.out.print("Elige una opción: ");

                                    op = input.nextInt();

                                    switch (op) {

                                        case 1:
                                            System.out.print("\nIntroduce el numero de puntos --> ");

                                            nPuntos = input.next();

                                            p = new FicheroTSP(nPuntos, peorCasoActivado);

                                            if (peorCasoActivado == true) {
                                                dataset = "dataset" + nPuntos + "on.tsp";
                                            } else {
                                                dataset = "dataset" + nPuntos + "off.tsp";
                                            }

                                            a.setData(dataset);

                                            System.out.println("\nOperacion finalizada con éxito");

                                            op = 0;

                                            break;

                                        case 2:

                                            System.out.print("\nIntroduce el nombre del fichero a cargar en memoria --> ");

                                            aux = input.next();

                                            try {
                                                a.setData(aux);
                                                op = 0;
                                                dataset = aux;

                                            } catch (Exception e) {
                                            }
                                            break;

                                        case 0:
                                            break;

                                        default:
                                            System.out.println("ERROR: Opción no válida");
                                            break;
                                    }
                                } while (op != 0);

                                break;

                            case 3:
                                // Implementa la lógica para la opción 3 aquí
                                System.out.println("\nDataset-->" + dataset);
                                System.out.println("\n| Estrategia\t\t\t" + "| Punto1\t\t\t\t\t\t\t\t" + "| Punto2\t\t\t\t\t\t\t\t" + "| Distancia\t\t\t" + "| Calculadas\t\t\t" + "| Tiempo(mseg)");
                                a.Exhaustivo();
                                r = a.getResultado();
                                System.out.println("| Exhaustivo\t\t\t| " + r.getParCercano()[0].getEtiqueta() + "(" + r.getParCercano()[0].getx() + ", " + r.getParCercano()[0].gety() + ")\t\t\t\t\t| " + r.getParCercano()[1].getEtiqueta() + "(" + r.getParCercano()[1].getx() + ", " + r.getParCercano()[1].gety() + ")\t\t\t\t\t| " + conversor(r.getDistanciaMin()) + "\t\t\t| " + a.getCalculados() + "\t\t\t| " + a.getTiempo());
                                a.ExhaustivoPoda();
                                r = a.getResultado();
                                System.out.println("| ExhausPoda\t\t\t| " + r.getParCercano()[0].getEtiqueta() + "(" + r.getParCercano()[0].getx() + ", " + r.getParCercano()[0].gety() + ")\t\t\t\t\t| " + r.getParCercano()[1].getEtiqueta() + "(" + r.getParCercano()[1].getx() + ", " + r.getParCercano()[1].gety() + ")\t\t\t\t\t| " + conversor(r.getDistanciaMin()) + "\t\t\t| " + a.getCalculados() + "\t\t\t\t| " + a.getTiempo());
                                a.DyV();
                                r = a.getResultado();
                                System.out.println("| DyVenceras\t\t\t| " + r.getParCercano()[0].getEtiqueta() + "(" + r.getParCercano()[0].getx() + ", " + r.getParCercano()[0].gety() + ")\t\t\t\t\t| " + r.getParCercano()[1].getEtiqueta() + "(" + r.getParCercano()[1].getx() + ", " + r.getParCercano()[1].gety() + ")\t\t\t\t\t| " + conversor(r.getDistanciaMin()) + "\t\t\t| " + abs(a.getCalculados()) + "\t\t\t| " + a.getTiempo());
                                a.DyVMejorado();
                                r = a.getResultado();
                                System.out.println("| DyVMejorado\t\t\t| " + r.getParCercano()[0].getEtiqueta() + "(" + r.getParCercano()[0].getx() + ", " + r.getParCercano()[0].gety() + ")\t\t\t\t\t| " + r.getParCercano()[1].getEtiqueta() + "(" + r.getParCercano()[1].getx() + ", " + r.getParCercano()[1].gety() + ")\t\t\t\t\t| " + conversor(r.getDistanciaMin()) + "\t\t\t| " + a.getCalculados() + "\t\t\t| " + a.getTiempo());

                                break;

                            case 4:
                                // Implementa la lógica para la opción 4 aquí 

                                File f = new File("ExhaustivoGP");
                                if (f.exists()) {
                                    f.delete();
                                }

                                f = new File("ExhaustivoPodaGP");
                                if (f.exists()) {
                                    f.delete();
                                }

                                f = new File("DyVGP");
                                if (f.exists()) {
                                    f.delete();
                                }

                                f = new File("DyVMejoradoGP");
                                if (f.exists()) {
                                    f.delete();
                                }

                                FileWriter FWEx = new FileWriter("ExhaustivoGP");
                                FileWriter FWExPod = new FileWriter("ExhaustivoPodaGP");
                                FileWriter FWDyV = new FileWriter("DyVGP");
                                FileWriter FWDyVMej = new FileWriter("DyVMejoradoGP");

                                PrintWriter outEx = new PrintWriter(FWEx);
                                PrintWriter outExPod = new PrintWriter(FWExPod);
                                PrintWriter outDyV = new PrintWriter(FWDyV);
                                PrintWriter outDyVMej = new PrintWriter(FWDyVMej);

                                int talla = 0;
                                double ex,
                                 expod,
                                 dyv,
                                 dyvmej;

                                // Realiza la comparación de las estrategias aquí
                                System.out.println("");
                                System.out.println("\t\t\t\tTiempos de ejecucion promedio");
                                System.out.println("\t\tExhaustivo\tExhaustivoPoda\tDivideVenceras\tDyV Mejorado");
                                System.out.println("\tTalla\tTiempo(mseg)\tTiempo(mseg)\tTiempo(mseg)\tTiempo(mseg)");

                                for (int i = 1; i <= 10; i++) {

                                    if (i <= 5) {
                                        talla = 1000 * i;
                                    } else {
                                        switch (i) {
                                            case 6:
                                                talla = 10000;
                                                break;
                                            case 7:
                                                talla = 20000;
                                                break;
                                            case 8:
                                                talla = 30000;
                                                break;
                                            case 9:
                                                talla = 40000;
                                                break;
                                            case 10:
                                                talla = 50000;
                                                break;
                                        }
                                    }

                                    p = new FicheroTSP(String.valueOf(talla), peorCasoActivado);

                                    if (peorCasoActivado == true) {
                                        a.setData("dataset" + talla + "on.tsp");
                                    } else {
                                        a.setData("dataset" + talla + "off.tsp");
                                    }

                                    a.Exhaustivo();
                                    ex = a.getTiempo();
                                    a.ExhaustivoPoda();
                                    expod = a.getTiempo();
                                    a.DyV();
                                    dyv = a.getTiempo();
                                    a.DyVMejorado();
                                    dyvmej = a.getTiempo();

                                    outEx.println(talla + "\t" + ex);
                                    outExPod.println(talla + "\t" + expod);
                                    outDyV.println(talla + "\t" + dyv);
                                    outDyVMej.println(talla + "\t" + dyvmej);

                                    System.out.println("\t" + talla + "\t" + ex + "\t\t" + expod + "\t\t" + dyv + "\t\t" + dyvmej);
                                }
                                outEx.close();
                                outExPod.close();
                                outDyV.close();
                                outDyVMej.close();

                                File comparacion1A = new File("comparacion1A.gp");
                                try {//If the file already exists, delete it..
                                    comparacion1A.delete();
                                } catch (Exception e) {}
                                
                                FileWriter outF = new FileWriter("comparacion1A.gp");
                                PrintWriter out = new PrintWriter(outF);
                                
                                out.print("set title " + "\"" + "COMPARACION" + "\"" + "\n");
                                out.print("set xlabel " + "\"Talla\"" + "\n");
                                out.print("set ylabel " + "\"Tiempo(ms)\"" + "\n");
                                out.println("set xrange [1000:50000]");
                                out.println("set yrange [0:30]");
                                out.println("plot \"ExhaustivoGP\" with linespoints,\"ExhaustivoPodaGP\" with linespoints,\"DyVGP\" with linespoints,\"DyVMejoradoGP\" with linespoints");
                                out.close();// It's done, closing document.
                                Runtime.getRuntime().exec("gnuplot -persist comparacion1A.gp");

                                System.out.println("");
                                System.out.println("Presione una tecla para continuar...");
                                input.next();

                                break;

                            case 5:
                                // Implementa la lógica para la opción 5 aquí

                                String est1 = null,
                                 est2 = null;
                                int estrategia1,
                                 estrategia2 = 0,
                                 talla1;

                                Algoritmos a2;
                                do {
                                    System.out.println("*** COMPARACION DE DOS ESTRATEGIAS ***\n");
                                    System.out.println("*** Selecciona las dos estrategias a comparar: ***");
                                    System.out.println("1.- EXHAUSTIVO");
                                    System.out.println("2.- EXHAUSTIVO CON PODA");
                                    System.out.println("3.- DyV");
                                    System.out.println("4.- DyV MEJORADO");
                                    System.out.println("0.- Salir");
                                    System.out.println("----------------------------");

                                    do {
                                        System.out.print("Elige la estrategia 1: ");
                                        estrategia1 = input.nextInt();

                                        switch (estrategia1) {
                                            case 1:
                                                est1 = "Exhaustivo";
                                                break;
                                            case 2:
                                                est1 = "ExhaustivoPoda";
                                                break;
                                            case 3:
                                                est1 = "DivideVenceras";
                                                break;
                                            case 4:
                                                est1 = "DyV Mejorado";
                                                break;
                                            case 0:
                                                break;
                                            default:
                                                System.out.println("ERROR: Opción no válida");
                                                break;
                                        }
                                    } while (estrategia1 > 4);

                                    if (estrategia1 != 0) {

                                        do {
                                            System.out.print("Elige la estrategia 2: ");
                                            estrategia2 = input.nextInt();

                                            switch (estrategia2) {
                                                case 1:
                                                    est2 = "Exhaustivo";
                                                    break;
                                                case 2:
                                                    est2 = "ExhaustivoPoda";
                                                    break;
                                                case 3:
                                                    est2 = "DivideVenceras";
                                                    break;
                                                case 4:
                                                    est2 = "DyV Mejorado";
                                                    break;
                                                case 0:
                                                    break;
                                                default:
                                                    System.out.println("ERROR: Opción no válida");
                                                    break;
                                            }
                                        } while (estrategia2 > 4);

                                        if (estrategia2 != 0) {
                                            // Realiza la comparación de las estrategias aquí
                                            System.out.println("");
                                            System.out.println("\t\t\t*** " + est1 + " vs " + est2 + " ***");
                                            System.out.println("\n\t\t\t\tTiempos de ejecucion promedio\n");
                                            System.out.println("\t\t" + est1 + "\t" + est2 + "\t" + est1 + "\t" + est2);
                                            System.out.println("\tTalla\tTiempo(mseg)\tTiempo(mseg)\tDistancias\tDistancias");
                                            for (int i = 1; i <= 5; i++) {
                                                talla1 = 1000 * i;
                                                p = new FicheroTSP(String.valueOf(talla1), peorCasoActivado);

                                                if (peorCasoActivado == true) {
                                                    a.setData("dataset" + talla1 + "on.tsp");
                                                } else {
                                                    a.setData("dataset" + talla1 + "off.tsp");
                                                }

                                                if (peorCasoActivado == true) {
                                                    a2 = new Algoritmos("dataset" + talla1 + "on.tsp");
                                                } else {
                                                    a2 = new Algoritmos("dataset" + talla1 + "off.tsp");
                                                }

                                                switch (estrategia1) {
                                                    case 1:
                                                        a.Exhaustivo();
                                                        break;
                                                    case 2:
                                                        a.ExhaustivoPoda();
                                                        break;
                                                    case 3:
                                                        a.DyV();
                                                        break;
                                                    case 4:
                                                        a.DyVMejorado();
                                                        break;
                                                }

                                                switch (estrategia2) {
                                                    case 1:
                                                        a2.Exhaustivo();
                                                        break;
                                                    case 2:
                                                        a2.ExhaustivoPoda();
                                                        break;
                                                    case 3:
                                                        a2.DyV();
                                                        break;
                                                    case 4:
                                                        a2.DyVMejorado();
                                                        break;
                                                }

                                                System.out.println("\t" + talla1 + "\t" + a.getTiempo() + "\t\t" + a2.getTiempo() + "\t\t" + a.getCalculados() + "\t\t" + a2.getCalculados());
                                            }
                                            System.out.println("");
                                            System.out.println("Presione una tecla para continuar...");
                                            input.next();
                                        }
                                    }
                                } while (estrategia1 != 0 && estrategia2 != 0);

                                break;

                            case 6:
                                peorCasoActivado = !peorCasoActivado;

                                break;

                            case 0:
                                System.out.println("\t\tSaliendo de Practica 1A...");
                                break;

                            default:
                                System.out.println("ERROR: Opción no válida");

                                break;
                        }
                    } while (opcion != 0);
                    break;

                case 2:                             //PRACTICA 1B

                    do {

                        boolean mostrarRecorrido = false;
                        Punto solucion[] = new Punto[a.getNumPuntos()];
                        p = new FicheroTSP(String.valueOf(a.getNumPuntos()), true);

                        System.out.println("\n\n\t\t*** AMC | Practica 1b | Curso 23/24 ***");
                        System.out.println("\t\tMiguel Quiroga - Pablo Fernández - Daniel Linfon - Pablo Gómez");
                        System.out.println("\n\t\t\t\t\t DATASET: " + dataset);
                        System.out.println("\n\t\t***     MENÚ PRINCIPAL 1B     ***");
                        System.out.println("\n\t\t1.- CREAR FICHERO TSP ALEATORIO");
                        System.out.println("\t\t2.- CARGAR UN DATASET EN MEMORIA");
                        System.out.println("\t\t3.- ESTRATEGIA DE BÚSQUEDA VORAZ UNIDIRECCIONAL");
                        System.out.println("\t\t4.- ESTRATEGIA DE BÚSQUEDA VORAZ BIDIRECCIONAL");
                        System.out.println("\t\t5.- ESTRATEGIA DE BÚSQUEDA PERMUTADA");
                        System.out.println("\t\t6.- COMPARAR ESTRATEGIAS");
                        System.out.println("\t\t0.- Salir");
                        System.out.println("\t\t---------");
                        System.out.print("\t\tElige una opción: ");
                        opcion = input.nextInt();
                        System.out.println("");

                        a.setData(dataset);

                        switch (opcion) {
                            case 1:
                                // Implementa la lógica para la opción 1 aquí
                                System.out.print("\nIntroduce el numero de puntos que debe generar el fichero -->");

                                nPuntos = input.next();

                                p = new FicheroTSP(nPuntos, false);

                                break;

                            case 2:
                                // Implementa la lógica para la opción 2 aquí
                                int op;

                                do {
                                    System.out.println("\n***     SUBMENÚ OPCIÓN 2     ***");
                                    System.out.println("\n1.- CREAR Y CARGAR DATASET EN MEMORIA");
                                    System.out.println("2.- CARGAR DATASET EN MEMORIA");
                                    System.out.println("0.- Salir");
                                    System.out.println("----------------------------");
                                    System.out.print("Elige una opción: ");

                                    op = input.nextInt();

                                    switch (op) {

                                        case 1:
                                            System.out.print("\nIntroduce el numero de puntos --> ");

                                            nPuntos = input.next();

                                            p = new FicheroTSP(nPuntos, false);

                                            dataset = "dataset" + nPuntos + "off.tsp";

                                            a.setData(dataset);

                                            System.out.println("\nOperacion finalizada con éxito");

                                            op = 0;

                                            break;

                                        case 2:

                                            System.out.print("\nIntroduce el nombre del fichero a cargar en memoria --> ");

                                            aux = input.next();

                                            try {
                                                a.setData(aux);
                                                op = 0;
                                                dataset = aux;

                                            } catch (Exception e) {
                                            }
                                            break;

                                        case 0:
                                            break;

                                        default:
                                            System.out.println("ERROR: Opción no válida");
                                            break;
                                    }
                                } while (op != 0);

                                break;
                            case 3:
                                r = a.BusquedaUnidireccional();
                                solucion = r.getSolucion();
                                System.out.println("Distancia: " + r.getDistanciaTotal());
                                mostrarRecorrido = true;
                                break;
                            case 4:
                                r = a.BusquedaBidireccional();
                                solucion = r.getSolucion();
                                System.out.println("Distancia: " + r.getDistanciaTotal());
                                mostrarRecorrido = true;
                                break;
                            case 5:
                                if (a.getNumPuntos() < 13) {

                                    r = a.BusquedaPermutada();
                                    solucion = r.getSolucion();
                                    System.out.println("Distancia: " + r.getDistanciaTotal());
                                    mostrarRecorrido = true;

                                } else {
                                    System.out.println("ERROR: No se admiten conjuntos mayores a 12 puntos para este algoritmo");
                                }
                                break;
                            case 6:
                                int talla1b = 500;
                                String fichero = null;
                                Resultado b1,
                                 b2;
                                int maxUni,
                                 maxBidir;
                                double tUni,
                                 tBidir;
                                
                                File f = new File("UnidireccionalGP");
                                if (f.exists()) {
                                    f.delete();
                                }

                                f = new File("BidireccionalGP");
                                if (f.exists()) {
                                    f.delete();
                                }

                                FileWriter FWuni = new FileWriter("UnidireccionalGP");
                                FileWriter FWbidir = new FileWriter("BidireccionalGP");

                                PrintWriter outUni = new PrintWriter(FWuni);
                                PrintWriter outBidir = new PrintWriter(FWbidir);

                                System.out.println("\n\t      		************Comparacion de las estrategias**************");
                                System.out.println("\n\t\t\tUnidireccional\t\tBidireccional");

                                for (int i = 0; i < 10; i++) {

                                    maxUni = 0;
                                    maxBidir = 0;
                                    tUni = 0;
                                    tBidir = 0;

                                    for (int j = 0; j < 100; j++) {

                                        p = new FicheroTSP(String.valueOf(talla1b), false);
                                        fichero = "dataset" + talla1b + "off.tsp";
                                        a.setData(fichero);

                                        b1 = a.BusquedaUnidireccional();
                                        tUni += a.getTiempo();

                                        b2 = a.BusquedaBidireccional();
                                        tBidir += a.getTiempo();

                                        if (b1.getDistanciaTotal() > b2.getDistanciaTotal()) {
                                            maxUni++;
                                        } else {
                                            maxBidir++;
                                        }
                                        p.borraFichero(fichero);
                                    }
                                    
                                    outUni.println(talla1b + "\t" + (tUni / 100));
                                    outBidir.println(talla1b + "\t" + (tBidir / 100));
                                    
                                    System.out.println("Talla " + talla1b + "-->\t\t" + maxUni + "(" + (tUni / 100) + "ms)" + "\t\t" + maxBidir + "(" + (tBidir / 100) + "ms)");
                                    talla1b += 500;
                                }
                                outUni.close();
                                outBidir.close();
                                
                                File comparacion1B = new File("comparacion1B.gp");
                                try {//If the file already exists, delete it..
                                    comparacion1B.delete();
                                } catch (Exception e) {}
                                
                                FileWriter outF = new FileWriter("comparacion1B.gp");
                                PrintWriter out = new PrintWriter(outF);
                                
                                out.print("set title " + "\"" + "COMPARACION" + "\"" + "\n");
                                out.print("set xlabel " + "\"Talla\"" + "\n");
                                out.print("set ylabel " + "\"Tiempo(ms)\"" + "\n");
                                out.println("set xrange [500:5000]");
                                out.println("set yrange [0:80]");
                                out.println("plot \"UnidireccionalGP\" with linespoints,\"BidireccionalGP\" with linespoints");
                                out.close();// It's done, closing document.
                                Runtime.getRuntime().exec("gnuplot -persist comparacion1B.gp");
                                
                                break;
                            case 0:
                                System.out.println("\t\tSaliendo de Practica 1B...");
                        }

                        if (mostrarRecorrido) {/*
                            System.out.println("Ciudad de partida: " + solucion[0].getEtiqueta());

                            for (int i = 1; i <= a.getNumPuntos(); i++) {
                                System.out.println("Ciudad " + i + " --> " + solucion[i].getEtiqueta());
                            }

                            System.out.println("Ultima ciudad: " + solucion[a.getNumPuntos()].getEtiqueta());
                             */
                            System.out.println("Tiempo(mseg): " + a.getTiempo());

                            p.generaFicheroSolucion(r.getSolucion(), r.getDistanciaTotal());
                            solucion = null;
                        }

                    } while (opcion != 0);

                    break;
                case 0:
                    System.out.println("\t\tSaliendo del programa...");
                    break;
                default:
                    System.out.println("\t\tERROR: Opción no válida");
            }
        } while (opc != 0);
    }

    public static double conversor(double valor) {
        double value;
        BigDecimal dMin = new BigDecimal(valor);
        dMin = dMin.setScale(8, RoundingMode.HALF_UP);
        value = dMin.doubleValue();
        return value;
    }
}
