/*
 * Clase AlgoritmosOrdenacion que implementa los Algoritmos de Ordenación para ordenar un vector de enteros en orden descendente.
 * Define las implementaciones de los siguientes métodos de Ordenación en vectores: 
 *	- Burbuja
 *	- Inserción
 *	- Selección.
 */
#include "AlgoritmosOrdenacion.h"

void merge(int v[], int e, int m, int d)
{
    int* B = new int[d - e + 1];
    int i = e; int j = m + 1; int k = 0;
    while (i <= m && j <= d) {
        if (v[i] < v[j])
            B[k++] = v[i++];
        else
            B[k++] = v[j++];
    }
    while (i <= m)
        B[k++] = v[i++];
    while (j <= d)
        B[k++] = v[j++];
    for (k = 0; k <= d - e; ++k)
        v[e + k] = B[k];
    delete[] B;
}

void mergesort(int v[], int e, int d)
{
    if (e < d)
    {
        int m = (e + d) / 2;
        mergesort(v, e, m);
        mergesort(v, m + 1, d);
        merge(v, e, m, d);
    }

}



AlgoritmosOrdenacion :: AlgoritmosOrdenacion() {}
AlgoritmosOrdenacion :: ~AlgoritmosOrdenacion(){}

/*
 * método ordenaBurbuja, implementa el método de ordenación Burbuja.
 * param v: el array de enteros a ordenar
 * param size: tamaño del array de enteros a ordenar
 */
void AlgoritmosOrdenacion::ordenaShakersort(int v[], int size)
{
    int N = size;
    int posi_vector_izq = 0;
    int posi_vector_dcha = N - 1;
    while (posi_vector_izq <= posi_vector_dcha) {
        for (int i = posi_vector_izq; i < posi_vector_dcha; i++) {
            if (v[i] > v[i + 1]) {
                int temporal = v[i];
                v[i] = v[i + 1];
                v[i + 1] = temporal;
            }
        }
        posi_vector_dcha--;
        for (int j = posi_vector_dcha; j > posi_vector_izq; j--) {
            if (v[j] < v[j - 1]) {
                int temporal = v[j];
                v[j] = v[j - 1];
                v[j - 1] = temporal;
            }
        }
         posi_vector_izq++;
    }
}


/*
 * método ordenaInsercion, implementa el método de ordenación por Inserción-
 * param v: el array de enteros a ordenar
 * param size: tamaño del array de enteros a ordenar
 */

/*
 * método ordenaSeleccion, implementa el método de ordenación por Selección.
 * param v: el array de enteros a ordenar
 * param size: tamaño del array de enteros a ordenar
 */
void AlgoritmosOrdenacion :: ordenaSeleccion(int v[], int size)
{
    int posminimo, aux;
    for (int i = 0; i <= size - 2; i++)
    {
        posminimo = i;
        for (int j = i + 1; j <= size - 1; j++)
        {
            if (v[j] < v[posminimo])
                posminimo = j;
        }
        aux = v[posminimo];
        v[posminimo] = v[i];
        v[i] = aux;
    }
}

void AlgoritmosOrdenacion::ordenaMergesort(int v[], int size)
{
    mergesort(v, 0, size - 1);
}





