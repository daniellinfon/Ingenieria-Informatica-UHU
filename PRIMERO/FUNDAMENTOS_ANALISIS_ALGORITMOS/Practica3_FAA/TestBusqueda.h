/* 
 * La clase TestBusqueda contiene los metodos para:
 * 1. Comprobar que los métodos de búsqueda de la clase AlgoritmosBusqueda funcionan adecuadamente.
 * 2. Calcular la eficiencia para el caso medio de un método de búsqueda, permitiendo guardar los 
 *    datos e imprimir la gráfica correspondiente con ajuste al Orden de complejidad.
 * 3. Comparar el coste temporal de dos métodos de búsqueda
 *    permitiendo guardar los datos e imprimir la gráfica correspondiente.
 * 4. Comparar todos los algoritmos de búsqueda implementados permitiendo guardar los datos e imprimir la gráfica correspondiente.
 */
#pragma once

//** ESCRIBIR PARA COMPLETAR LA PRACTICA **//
#include <vector>
#include <string>
#include <iostream>
using namespace std;

class TestBusqueda
{
	vector<string> nombreAlgoritmo;
public:
    
	TestBusqueda();
	~TestBusqueda();
		/*
		 * Busca un array de enteros según el método indicado
		 * param v: el array de enteros a ordenar
		 * param size: tamaño del array de enteros a ordenar
		 * param metodo: Metodo de ordenacion a utilizar
		 * return Tiempo empleado en la BUSQUEDA (en milisegundos)
		 */
		static double buscaEnArrayDeInt(int key, int v[], int size, int metodo, int &posicion);
		/*
		 * Comprueba que los metodos de busqueda funcionan correctamente
		 */
		void comprobarMetodosBusqueda();

		/*
		 * Calcula el caso medio de un metodo de busqueda.
		 * Permite las opciones de crear el fichero de datos y la gráfica correspondiente.
		 * param metodo: metodo de busqueda a estudiar.
		 */
		void casoMedio(int metodo);

		/*
		 * Compara dos metodos de busqueda.
		 * Permite las opciones de crear el fichero de datos y la gráfica correspondiente.
		 * param metodo1: Primer metodo de busqueda a comparar
		 * param metodo2: Segundo metodo de busqueda a comparar
		 */
		void comparar(int metodo1, int metodo2);

		/*
		 * Compara todos los metodos de busqueda.
		 * Permite crear la gráfica correspondiente.
		 */
		void comparaTodos();
	
};