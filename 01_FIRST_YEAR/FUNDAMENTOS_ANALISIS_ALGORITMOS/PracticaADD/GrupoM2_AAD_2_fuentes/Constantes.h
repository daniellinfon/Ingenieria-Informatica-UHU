/* 
 *Definiciones de las Constantes para la práctica 2 
 */
#pragma once

/* Constantes simbólicas para indicar el metodo de ordenacion*/
enum algoritmos {SHAKERSORT, MERGESORT, SELECCION};
/* Constantes para indicar el Orden del metodo de ordenacion*/
enum ordenes {CUADRADO, NlogN};
/* Constantes para indicar el Numero de repeticiones para el caso medio de cada método de búsqueda */
static const int NUMREPETICIONES=1000;
/* Constantes para indicar la variacion de las tallas del vector */
enum valoresTallas { tallaIni = 100,tallaFin = 1000,incTalla = 100};
/* Constantes simbólicas para indicar los casos del metodo de Busqueda SECUENCIAL*/
enum algoritmosBusquedaCasos { SECUENCIALPEOR, SECUENCIALMEDIO, SECUENCIALMEJOR };