#include "TestAlgoritmo.h"
#include <iostream>
#include <stdlib.h>
#include"TestAlgoritmo.h"
#include "Constantes.h"
using namespace std;

int menu() {
	int opc;
	do {
		cout << "PRACTICA 1 FUNDAMENTOS DE ANALISIS DE ALGORITMOS" << endl;
		cout << "ALUMNO: DANIEL LINFON YE LIU			  PROF: TERESA SANTOS" << endl;
		cout << "*** ESTUDIO DE LA COMPLEJIDAD DEL ALGORITMO DE BUSQUEDA SECUENCIAL ***" << endl;
		cout << "1.- ESTUDIO TEORICO" << endl;
		cout << "2.- ESTUDIO EMPIRICO" << endl;
		cout << "3.- SALIR" << endl;
		cout << "------------------" << endl;
		cout << "ELIGE OPCION: ";
		cin >> opc;
		system("cls");
	} while (opc != 1 && opc != 2 && opc != 3);

	return opc;
}

int menuteorico() {
	int opc;
	do {
		cout << "*** MENU TEORICO BUSQUEDA SECUENCIAL ***" << endl;
		cout << "1.- PROBAR ALGORITMO DE BUSQUEDA SECUENCIAL" << endl;
		cout << "2.- OBTENER CASOS DEL METODO DE BUSQUEDA SECUENCIA" << endl;
		cout << "3.- COMPARAR CASOS" << endl;
		cout << "4.- VOLVER AL MENU ANTERIOR" << endl;
		cout << "----------------" << endl;
		cout << "ELIGE OPCION: ";
		cin >> opc;
		system("cls");
	} while (opc != 1 && opc != 2 && opc != 3 && opc != 4);

	return opc;
}

int menuteorico2() {
	int opc;
	do {
		cout << "*** CASO A ESTUDIAR PARA BUSQUEDA SECUENCIAL ***" << endl;
		cout << "0.- CASO PEOR" << endl;
		cout << "1.- CASO MEDIO" << endl;
		cout << "2.- CASO MEJOR" << endl;
		cout << "----------------" << endl;
		cout << "ELIGE OPCION: ";
		cin >> opc;
		system("cls");
	} while (opc != 0 && opc != 1 && opc != 2);

	return opc;

}

int menuempirico1() {
	int opc;
	do {
		cout << "*** MENU TEORICO BUSQUEDA SECUENCIAL ***" << endl;
		cout << "1.- PROBAR ALGORITMO DE BUSQUEDA SECUENCIAL" << endl;
		cout << "2.- OBTENER CASOS DEL METODO DE BUSQUEDA SECUENCIA" << endl;
		cout << "3.- COMPARAR CASOS" << endl;
		cout << "4.- VOLVER AL MENU ANTERIOR" << endl;
		cout << "----------------" << endl;
		cout << "ELIGE OPCION: ";
		cin >> opc;
		system("cls");
	} while (opc != 1 && opc != 2 && opc != 3);

	return opc;
}

int menuempirico2() {
	int opc;
	do {
		cout << "*** CASO A ESTUDIAR PARA BUSQUEDA SECUENCIAL ***" << endl;
		cout << "0.- CASO PEOR" << endl;
		cout << "1.- CASO MEDIO" << endl;
		cout << "2.- CASO MEJOR" << endl;
		cout << "----------------" << endl;
		cout << "ELIGE OPCION: ";
		cin >> opc;
		system("cls");
	} while (opc != 0 && opc != 1 && opc != 2);

	return opc;
}


int main()
{
	int opc;
	int opc1;
	int opc2;
	int caso;
	int caso2;
	TestAlgoritmo test;
	do {
		opc = menu();
		cout << "opc: " << opc;
		switch (opc) {
		case 1:
			opc1 = menuteorico();
			switch (opc1) {
			case 1:
				test.comprobarAlgoritmo();
				break;
			case 2:
				caso = menuteorico2();
				test.costeCasoTeorico(caso);
				break;
			case 3:
				test.compararTeorico(SECUENCIALPEOR,SECUENCIALMEDIO,SECUENCIALMEJOR);
				break;
			default:
				break;
			}
			break;

		case 2:
			opc2 = menuempirico1();
			switch (opc2)
			{
			case 1:
				test.comprobarAlgoritmo();
				break;
			case 2:
				caso2 = menuempirico2();
				test.costeCasoEmpirico(caso2);
				break;
			case 3:
				test.compararEmpirico(SECUENCIALPEOR, SECUENCIALMEDIO, SECUENCIALMEJOR);
				break;
			default:
				break;
			}
			break;
		case 3:
		default:
			break;
		}
		cout << "\n"; system("pause");
	} while (opc != 3);




}

