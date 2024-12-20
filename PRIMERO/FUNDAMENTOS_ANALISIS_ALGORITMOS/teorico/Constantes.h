/* 
 *Definiciones de las Constantes para la pr�ctica 2 
 */
#pragma once

/* Constantes simb�licas para indicar el metodo de ordenacion*/
enum algoritmos {SHAKERSORT, MERGESORT, SELECCION, TERNARIA};
/* Constantes para indicar el Orden del metodo de ordenacion*/
enum ordenes {CUADRADO, NlogN, LOGN};
/* Constantes para indicar el Numero de repeticiones para el caso medio de cada m�todo de b�squeda */
static const int NUMREPETICIONES=1000;
/* Constantes para indicar la variacion de las tallas del vector */
enum valoresTallas { tallaIni = 100,tallaFin = 1000,incTalla = 100};
/* Constantes simb�licas para indicar los casos del metodo de Busqueda SECUENCIAL*/
enum algoritmosBusquedaCasos { SECUENCIALPEOR, SECUENCIALMEDIO, SECUENCIALMEJOR };