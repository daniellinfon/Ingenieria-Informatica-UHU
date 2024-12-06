
import java.io.IOException;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import java.security.SecureRandom;

public class Algoritmos {

    private int numPuntos;
    private Punto lista[];
    private double tiempo;
    private Resultado resultado;
    private int calculados;
     private SecureRandom r;
    private FicheroTSP t;
    
    public void mostrarInfo() {
        System.out.println("Distancia: " + resultado.getDistanciaMin());
        System.out.println("Par de puntos: \nP1 = " + resultado.getParCercano()[0].getx() + " - " + resultado.getParCercano()[0].gety());
        System.out.println("P2 = " + resultado.getParCercano()[1].getx() + " - " + resultado.getParCercano()[1].gety());
        System.out.println("Tiempo: " + tiempo);
    }

    public Algoritmos(String ruta) {
        setData(ruta);
        resultado = new Resultado(Double.POSITIVE_INFINITY);
        tiempo = Double.POSITIVE_INFINITY;
         r = new SecureRandom();
    }

    public final void setData(String ruta) {
        TSPlibReader r = new TSPlibReader( ruta);
        numPuntos = r.getNum();
        lista = new Punto[numPuntos];
        System.arraycopy(r.nodeptr, 0, lista, 0, r.nodeptr.length);
    }
    
    public Resultado getResultado()
    {
        return this.resultado;
    }
    
    public double getTiempo()
    {
        return tiempo;
    }
    
    public int getCalculados()
    {
        return calculados;
    }

    public void Exhaustivo() throws IOException {

        t = new FicheroTSP(String.valueOf(numPuntos),true);
        
        resultado.setDistanciaMin(Double.POSITIVE_INFINITY);
        calculados=0;
        double ini = System.currentTimeMillis();
        double d;

        int i = 0;

        while (i < (numPuntos - 1)) {

            int j = i + 1;

            while (j < numPuntos) {
                
                d = distancia(lista[i], lista[j]);

                if (d != 0 && d < resultado.getDistanciaMin()) {
                    resultado.setDistanciaMin(d);
                    resultado.setParCercano(lista[i], lista[j]);
                }
                j++;
            }
            i++;
        }

        double fin = System.currentTimeMillis();

        tiempo = fin - ini;
        
        t.escribeFichero(lista, "Exhaustivo");
        
    }

    public void ExhaustivoPoda() throws IOException {
        
        t = new FicheroTSP(String.valueOf(numPuntos),true);
        
        resultado.setDistanciaMin(Double.POSITIVE_INFINITY);
        calculados=0;
        double d;
        
        Punto copia[] = new Punto[numPuntos];
        System.arraycopy(lista, 0, copia, 0, lista.length);
        
        double ini = System.currentTimeMillis();
        
        HeapSort.sortX(copia);

        int i = 0;

        while (i < (numPuntos - 1)) {

            int j = i + 1;

            while (j < numPuntos && ((copia[j].getx() - copia[i].getx()) < resultado.getDistanciaMin())) {
                
                d = distancia(copia[i], copia[j]);

                if (d != 0 && d < resultado.getDistanciaMin()) {
                    resultado.setDistanciaMin(d);
                    resultado.setParCercano(copia[i], copia[j]);
                }
                j++;
            }
            i++;
        }
        double fin = System.currentTimeMillis();

        tiempo = fin - ini;
        
        t.escribeFichero(copia, "ExhaustivoPoda");
    }

    public void DyV() throws IOException {
        
        resultado.setDistanciaMin(Double.POSITIVE_INFINITY);
        calculados=0;
        
        int izq = 0, der = numPuntos - 1;
        
        t = new FicheroTSP(String.valueOf(numPuntos),true);
        
        Punto copia[] = new Punto[numPuntos];
        System.arraycopy(lista, 0, copia, 0, lista.length);

        double ini = System.currentTimeMillis();

        HeapSort.sortX(copia);

        resultado = DyV(copia, izq, der);

        double fin = System.currentTimeMillis();

        tiempo = fin - ini;
        
        t.escribeFichero(copia, "DivideYVenceras");
    }

    private Resultado DyV(Punto lista[], int izq, int der) {
        
        Resultado r = new Resultado(Double.POSITIVE_INFINITY);

        if (der - izq == 1) { // si sólo hay dos puntos

            r.setParCercano(lista[izq], lista[der]);
            r.setDistanciaMin(distancia(lista[izq], lista[der]));

        } else if (der - izq > 1) { // si hay más de dos puntos

            Resultado d1 = DyV(lista, izq, izq + ((der - izq) / 2));
            Resultado d2 = DyV(lista, izq + (((der - izq) / 2) + 1), der);

            if (d1.getDistanciaMin() <= d2.getDistanciaMin()) {
                r.setDistanciaMin(d1.getDistanciaMin());
                r.setParCercano(d1.getParCercano()[0], d1.getParCercano()[1]);
            } else {
                r.setDistanciaMin(d2.getDistanciaMin());
                r.setParCercano(d2.getParCercano()[0], d2.getParCercano()[1]);
            }

            double xm = ((lista[der].getx() - lista[izq].getx()) / 2) + lista[izq].getx();

            double limiteIzq = xm - r.getDistanciaMin();
            double limiteDer = xm + r.getDistanciaMin();

            while (lista[izq].getx() < limiteIzq) {
                izq++;
            }

            while (lista[der].getx() > limiteDer) {
                der--;
            }

            Resultado d3 = Exhaustivo(lista, izq, der);

            if (d3.getDistanciaMin() < r.getDistanciaMin()) {
                r.setDistanciaMin(d3.getDistanciaMin());
                r.setParCercano(d3.getParCercano()[0], d3.getParCercano()[1]);
            }
        }

        return r;
    }

    private Resultado Exhaustivo(Punto lista[], int izq, int der) {

        Resultado r = new Resultado(Double.POSITIVE_INFINITY); //distancia(lista[izq], lista[der])
        double d;

        int i = izq;

        while (i <= (der - 1)) {

            int j = i + 1;

            while (j <= der) {
                
                d = distancia(lista[i], lista[j]);

                if (d != 0 && d < r.getDistanciaMin()) {
                    r.setDistanciaMin(d);
                    r.setParCercano(lista[i], lista[j]);
                }
                j++;
            }
            i++;
        }
        return r;
    }

    public void DyVMejorado() throws IOException {
        
        resultado.setDistanciaMin(Double.POSITIVE_INFINITY);
        calculados=0;
        
        int izq = 0, der = numPuntos - 1;
        
        t = new FicheroTSP(String.valueOf(numPuntos),true);
        
        Punto copia1[] = new Punto[numPuntos];
        System.arraycopy(lista, 0, copia1, 0, numPuntos);
        
        Punto copia2[] = new Punto[numPuntos];
        System.arraycopy(lista, 0, copia2, 0, numPuntos);
        
        HeapSort.sortX(copia2);

        double ini = System.currentTimeMillis();

        HeapSort.sortX(copia1);

        resultado = DyVMejorado(copia1, izq, der);

        double fin = System.currentTimeMillis();

        tiempo = fin - ini;
        
        t.escribeFichero(copia2, "DyVMejorado");
    }

    private Resultado DyVMejorado(Punto lista[], int izq, int der) {

        Resultado r = new Resultado(Double.POSITIVE_INFINITY);

        if (der - izq == 1) { // si sólo hay dos puntos

            r.setParCercano(lista[izq], lista[der]);
            r.setDistanciaMin(distancia(lista[izq], lista[der]));

        } else if (der - izq > 1) { // si hay más de dos puntos

            Resultado d1 = DyV(lista, izq, izq + ((der - izq) / 2));
            Resultado d2 = DyV(lista, izq + (((der - izq) / 2) + 1), der);

            if (d1.getDistanciaMin() <= d2.getDistanciaMin()) {
                r.setDistanciaMin(d1.getDistanciaMin());
                r.setParCercano(d1.getParCercano()[0], d1.getParCercano()[1]);
            } else {
                r.setDistanciaMin(d2.getDistanciaMin());
                r.setParCercano(d2.getParCercano()[0], d2.getParCercano()[1]);
            }

            double xm = ((lista[der].getx() - lista[izq].getx()) / 2) + lista[izq].getx();

            double limiteIzq = xm - r.getDistanciaMin();
            double limiteDer = xm + r.getDistanciaMin();

            while (lista[izq].getx() < limiteIzq) {
                izq++;
            }

            while (lista[der].getx() > limiteDer) {
                der--;
            }
            
            Punto listaAux[] = new Punto[(der-izq)+1];
            
            int j=izq;
            
            for (int i = 0; i < ((der-izq)+1); i++) {
                listaAux[i] = lista[j];
                j++;
            }
            
            
            HeapSort.sortY(listaAux);
            
            j=izq;
            
            for (int i = 0; i < ((der-izq)+1); i++) {
                lista[j] = listaAux[i];
                j++;
            }
            
            Resultado d3 = Exhaustivo12Pos(lista, izq, der);

            if (d3.getDistanciaMin() < r.getDistanciaMin()) {
                r.setDistanciaMin(d3.getDistanciaMin());
                r.setParCercano(d3.getParCercano()[0], d3.getParCercano()[1]);
            }
        }
        return r;
    }

    private Resultado Exhaustivo12Pos(Punto lista[], int izq, int der) {

        int doce;
        double d;

        Resultado r = new Resultado(Double.POSITIVE_INFINITY);

        int i = izq;

        while (i <= (der - 1)) {

            doce = 0;

            int j = i + 1;

            while (j <= der && doce < 12) {
                
                d = distancia(lista[i], lista[j]);

                if (d != 0 && d < r.getDistanciaMin()) {
                    r.setDistanciaMin(d);
                    r.setParCercano(lista[i], lista[j]);
                }
                doce++;
                j++;
            }
            i++;
        }

        return r;
    }
    
     private boolean estaLlena(Punto x[]) {
        return x[numPuntos] != null;
    }

    public Resultado BusquedaUnidireccional() throws Exception {

        for (int k = 0; k < numPuntos; k++) {
            lista[k].setVisitado(false);
        }

        resultado = new Resultado(0, numPuntos + 1);

        Punto solucion[] = new Punto[numPuntos + 1];
        Punto siguiente;
        int i = 1, rdm = r.nextInt(numPuntos), j = 0, num = 0;
        siguiente = lista[rdm];

        lista[rdm].setVisitado(true);
        solucion[0] = siguiente;
        double dist = Double.POSITIVE_INFINITY, ini = System.currentTimeMillis();

        while (!estaLlena(solucion)) {
            if (distancia(siguiente, lista[j]) < dist && !lista[j].getVisitado()) {
                dist = distancia(siguiente, lista[j]);
                num = j;
            }

            if (j == numPuntos - 1) {

                solucion[i] = lista[num];
                siguiente = solucion[i];
                lista[num].setVisitado(true);
                j = -1;
                if (i <= numPuntos) {
                    resultado.setDistanciaTotal(dist + resultado.getDistanciaTotal());
                    if (i == numPuntos - 1) {
                        lista[rdm].setVisitado(false);
                    }
                }
                i++;
                dist = Double.POSITIVE_INFINITY;
            }

            j++;
        }

        double fin = System.currentTimeMillis();
        tiempo = fin - ini;

        resultado.setSolucion(solucion);

        if (estaLlena(resultado.getSolucion())) {
            return resultado;
        } else {
            throw new Exception("No se encontró una solución");
        }
    }

    public Resultado BusquedaBidireccional() throws Exception {

        for (int k = 0; k < numPuntos; k++) {
            lista[k].setVisitado(false);
        }

        resultado = new Resultado(0, numPuntos * 2);

        Punto solucion[] = new Punto[numPuntos * 2];
        int num1 = -1, num2 = -1;
        int finBucle = numPuntos - 1;
        Punto extremoIzq, extremoDer = new Punto();
        boolean primeraIteracion = true;

        int izq = 0, j = 0, mitad = r.nextInt(numPuntos), der = numPuntos;
        extremoIzq = lista[mitad];
        lista[mitad].setVisitado(true);
        solucion[numPuntos - 1] = extremoIzq;
        double dist, dist1 = Double.POSITIVE_INFINITY, dist2 = Double.NEGATIVE_INFINITY, ini = System.currentTimeMillis();

        while (finBucle != 0) {
            if (distancia(extremoIzq, lista[j]) < dist1 && !lista[j].getVisitado()) {
                dist1 = distancia(extremoIzq, lista[j]);
                num1 = j;
            }

            if (distancia(extremoDer, lista[j]) < dist2 && !lista[j].getVisitado()) {
                dist2 = distancia(extremoDer, lista[j]);
                num2 = j;
            }

            if (j == numPuntos - 1) {
                if (num1 != num2) { //Distintos puntos
                    if (!primeraIteracion) {
                        solucion[izq] = lista[num1];
                        solucion[der] = lista[num2];
                        extremoDer = solucion[der];
                        extremoIzq = solucion[izq];
                        lista[num1].setVisitado(true);
                        lista[num2].setVisitado(true);
                        resultado.setDistanciaTotal(dist1 + dist2 + resultado.getDistanciaTotal());
                        izq++;
                        der++;
                        finBucle -= 2;
                    } else { //Si es la primera iteracion inicializamos el extremo derecho
                        solucion[der] = lista[num1];
                        extremoDer = lista[num1];
                        lista[num1].setVisitado(true);
                        primeraIteracion = false;
                        resultado.setDistanciaTotal(dist1 + resultado.getDistanciaTotal());
                        der++;
                        finBucle--;
                    }

                } else { //Si es el mismo punto por la izquierda y derecha, miramos que distacia es mas corta

                    if (dist1 < dist2) {
                        solucion[izq] = lista[num1];
                        extremoIzq = solucion[izq];
                        resultado.setDistanciaTotal(dist1 + resultado.getDistanciaTotal());
                        izq++;
                    } else {
                        solucion[der] = lista[num2];
                        extremoDer = solucion[der];
                        resultado.setDistanciaTotal(dist2 + resultado.getDistanciaTotal());
                        der++;
                    }
                    lista[num1].setVisitado(true);
                    finBucle--;
                }
                j = -1;
                //System.out.println("Distancia total: " + resultado.getDistanciaTotal());
                dist1 = Double.POSITIVE_INFINITY;
                dist2 = Double.POSITIVE_INFINITY;
            }
            j++;
        }

        Punto solucionFinal[] = new Punto[numPuntos + 1];
        //Ordenamos el array y terminamos el ciclo

        for (int z = numPuntos - 1; z < der; z++) {        //Extremo der a principio del array
            solucionFinal[j] = solucion[z];
            //System.out.println(solucion[z].getEtiqueta());
            j++;
        }

        if (extremoIzq.getEtiqueta() != lista[mitad].getEtiqueta()) //Si extremo izquierdo no es el inicial, calculamos la distancia entre extremos
        {
            resultado.setDistanciaTotal(distancia(extremoIzq, extremoDer) + resultado.getDistanciaTotal());
        }

        //System.out.println("Extremos " + extremoIzq.getEtiqueta() + " - " + extremoDer.getEtiqueta());
        for (int z = izq - 1; z >= 0; z--) {                 //Extremo izq a continuacion
            solucionFinal[j] = solucion[z];
            //System.out.println(solucion[z].getEtiqueta() + " -----");
            j++;
        }

        //Cerrar el array con el punto inicial
        solucionFinal[j] = solucionFinal[0];
        resultado.setDistanciaTotal(distancia(solucionFinal[j - 1], solucionFinal[j]) + resultado.getDistanciaTotal());

        double fin = System.currentTimeMillis();

        tiempo = fin - ini;

        resultado.setSolucion(solucionFinal);

        if (finBucle == 0) {
            return resultado;
        } else {
            throw new Exception("No se encontró una solución");
        }
    }

    public Resultado BusquedaPermutada() throws Exception {

        resultado = new Resultado(Double.POSITIVE_INFINITY, numPuntos + 1);

        double ini = System.currentTimeMillis();

        permuta(lista, 0);

        double fin = System.currentTimeMillis();

        tiempo = fin - ini;

        if (estaLlena(resultado.getSolucion())) {
            return resultado;
        } else {
            throw new Exception("No se encontró una solución");
        }
    }

    public void permuta(Punto[] puntos, int indice) {

        // ITERATIVO
        /*
        int n = array.length;
        int[] indices = new int[n];
        List<Punto[]> permutaciones = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            indices[i] = 0;
        }

        permutaciones.add(Arrays.copyOf(array, n));

        int i = 0;
        
        while (i < n) {
            if (indices[i] < i) {
                intercambia(array, i % 2 == 0 ? 0 : indices[i], i);
                permutaciones.add(Arrays.copyOf(array, n));
                indices[i]++;
                i = 0;
            } else {
                indices[i] = 0;
                i++;
            }
        }

        for (Punto[] perm : permutaciones) {
            
            double d = 0;

            for (int k = 0; k < (perm.length - 1); k++) {

                d += distancia(perm[k], perm[k + 1]);
            }
            
            if(d < resultado.getDistanciaTotal()) {

                resultado.setSolucion(perm);
                resultado.setDistanciaTotal(d);
            }
        }
         */
        
        //RECURSIVO
        if (indice == numPuntos - 1) {

            double d = 0;

            for (int i = 0; i < (numPuntos - 1); i++) {

                d += distancia(puntos[i], puntos[i + 1]);
            }

            d += distancia(puntos[numPuntos - 1], puntos[0]);

            if (d < resultado.getDistanciaTotal()) {

                Punto solucion[] = new Punto[numPuntos + 1];

                for (int i = 0; i < numPuntos; i++) {
                    solucion[i] = puntos[i];
                }

                solucion[numPuntos] = solucion[0];
                resultado.setSolucion(solucion);
                resultado.setDistanciaTotal(d);
            }

        } else {

            for (int i = indice; i < numPuntos; i++) {

                // Intercambia el elemento en la posición 'index' con el elemento en la posición 'i'
                intercambia(puntos, indice, i);

                // Llama recursivamente para las permutaciones restantes
                permuta(puntos, indice + 1);

                // Deshace el intercambio para volver al estado original
                intercambia(puntos, indice, i);
            }
        }
    }

    public void intercambia(Punto[] puntos, int i, int j) {

        Punto temp = puntos[i];
        puntos[i] = puntos[j];
        puntos[j] = temp;
    }

    private double distancia(Punto p1, Punto p2) {

        calculados++;
        return sqrt(pow((p1.getx() - p2.getx()), 2) + pow((p1.gety() - p2.gety()), 2));
    }

    public int getNumPuntos() {
        return numPuntos;
    }

}
