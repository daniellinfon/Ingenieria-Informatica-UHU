
/*
 * Clase AlgoritmosBusqueda que implementa los Algoritmos de Búsqueda para buscar un elemento en un vector de enteros.
 * Define las implementaciones iterativas de los siguientes métodos de búsqueda en vectores de enteros:
 *	- Secuencial
 *	- Binaria o dicotómica
 *  - Interpolacion
 */

#include "AlgoritmosBusqueda.h"

/*
 * Implementación de los métodos de la clase AlgoritmosBusqueda
 */
AlgoritmosBusqueda::AlgoritmosBusqueda(){}
AlgoritmosBusqueda:: ~AlgoritmosBusqueda(){}

/*
	 * Función busquedaSecuencialIt, implementa el método de búsqueda secuencial Iterativo
	 * param v: el array de enteros donde buscar
	 * param size: tamaño del array
	 * param key: clave o elemento a buscar
	 * return posición de la clave en el array
	 */
int AlgoritmosBusqueda::busquedaSecuencialIt(int v[], int size, int key)
{
	//** ESCRIBIR PARA COMPLETAR LA PRACTICA **//
	int i = 0;
	while (v[i] != key && i < size)
	{
		i = i + 1;
	}
	if (v[i] == key)
		return i;	 // devuelve la posición i donde se encuentra el valor en el array
	else
		return -1;  // devuelve -1, no se encuentra el valor en el array
}

/*
	 * Función busquedaBinariaIt, implementa el método de búsqueda binaria Iterativa
	 * param v: el array de enteros donde buscar
	 * param size: tamaño del array
	 * param key: clave o elemento a buscar
	 * return posición de la clave en el array
	 */
int AlgoritmosBusqueda::busquedaBinariaIt(int v[], int size,int key)
{
	//** ESCRIBIR PARA COMPLETAR LA PRACTICA **//
	bool encontrado = false;
	int mitad, primero, ultimo;
	primero = 0;
	ultimo = size - 1;
	while ((primero <= ultimo) && !encontrado)
	{
		mitad = ((ultimo + primero) / 2);
		if (key == v[mitad])
			encontrado = true;
		else
		{
			if (key < v[mitad])
				ultimo = mitad - 1;
			else
				if (key > v[mitad])
				primero = mitad + 1;
		}
	}
	if (encontrado)
		return mitad;
	else
		return (-1);
}

/*
 * Función busquedaInterpolacionIt, implementa el método de búsqueda por Interpolacion Iterativa
 * param v: el array de enteros donde buscar
 * param size: tamaño del array
 * param key: clave o elemento a buscar
 * return posición de la clave en el array
 */	 
int AlgoritmosBusqueda::busquedaInterpolacionIt(int v[], int size,int key)
{
	//** ESCRIBIR PARA COMPLETAR LA PRACTICA **//
	int p, primero, ultimo;
	primero = 0;
	ultimo = size - 1;
	while ((v[ultimo] >= key) && (v[primero] < key))
	{
		p = primero + ((ultimo - primero) * (key - v[primero]) / (v[ultimo] - v[primero]));
		if (key > v[p])
			primero = p + 1;
		else
		{
			if (key < v[p])
				ultimo = p - 1;
			else
				primero = p;
			
		}
	}

	if (v[primero] == key)
		return primero;
	else
		return (-1);

}

/****EXA MAYO22****/

int AlgoritmosBusqueda::busquedaTernaria(int v[], int size, int key)
{
	return Ternaria(v, 0, size-1, key);
}


int AlgoritmosBusqueda::Ternaria(int v[], int primero, int ultimo, int key)
{
	if (primero >= ultimo)
	{
		if (v[ultimo] == key)
			return ultimo;
		else
			return -1;
	}

	int tercio = ((ultimo - primero + 1) / 3);
	if (key == v[primero + tercio])
		return (primero + tercio);
	else
	{
		if (key < v[primero + tercio])
			return Ternaria(v, primero, primero + tercio - 1, key);
		else
		{
			if (key == v[ultimo - tercio])
				return (ultimo - tercio);
			else if (key < v[ultimo - tercio])
				return Ternaria(v, primero + tercio + 1, ultimo - tercio - 1, key);
			else
				return Ternaria(v, ultimo - tercio + 1, ultimo, key);
		}
	}
}
