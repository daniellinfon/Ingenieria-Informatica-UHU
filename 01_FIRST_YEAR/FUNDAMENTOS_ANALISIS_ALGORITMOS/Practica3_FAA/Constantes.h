/* 
 *Definiciones de las Constantes para la práctica 2 
 */
#pragma once

/* Constantes simbólicas para indicar el metodo de ordenacion*/
enum algoritmosOrd {BURBUJA, INSERCION, SELECCION};
/* Constantes simbólicas para indicar el metodo de busqueda*/
enum algoritmosBus {SECUENCIAL, BINARIA, INTERPOLACION };
/* Constantes para indicar el Orden del metodo de ordenacion y busqueda*/
enum ordenes {LOGLOGN, LOGN, N,NLOGN, CUADRADO};
/* Constantes para indicar el Numero de repeticiones para el caso medio de cada método de búsqueda */
static const int NUMREPETICIONES=10;
/* Constantes para indicar la variacion de las tallas del vector */
enum valoresTallas { tallaIni = 100,tallaFin = 1000,incTalla = 100};
