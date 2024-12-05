/*
 * Clase AlgoritmosOrdenacion que define los Algoritmos de Ordenación para ordenar un vector de enteros en orden descendente.
 * Define las implementaciones de los siguientes métodos de Ordenación en vectores: 
 *	- Burbuja
 *	- Inserción
 *	- Selección.
 */
#pragma once

/*
 * declaración de la clase AlgoritmosOrdenacion
 */
class AlgoritmosOrdenacion
{  
public:
    AlgoritmosOrdenacion();
    ~AlgoritmosOrdenacion();
	
	/*
	 * método ordenaBurbuja, implementa el método de ordenación Burbuja.
	 * param v: el array de enteros a ordenar
	 * param size: tamaño del array de enteros a ordenar
	 */
   void ordenaShakersort(int v[], int size);

	/*
	 * método ordenaInsercion, implementa el método de ordenación por Inserción.
	 * param v: el array de enteros a ordenar
	 * param size: tamaño del array de enteros a ordenar
	 */

	/*
	 * método ordenaSeleccion, implementa el método de ordenación por Selección.
	 * param v: el array de enteros a ordenar
	 * param size: tamaño del array de enteros a ordenar
	 */
    void ordenaSeleccion(int v[], int size);

	void ordenaMergesort(int v[], int size);
};