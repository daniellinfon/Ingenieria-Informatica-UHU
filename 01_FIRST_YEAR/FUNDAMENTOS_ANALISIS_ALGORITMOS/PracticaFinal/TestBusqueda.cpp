/* 
 * La clase TestBusqueda contiene los metodos para:
 * 1. Comprobar que los métodos de búsqueda de la clase AlgoritmosBusqueda funcionan adecuadamente.
 * 2. Calcular la eficiencia para el caso medio de un método de búsqueda,
 *    permitiendo guardar los datos e imprimir la gráfica correspondiente con ajuste al Orden de complejidad.
 * 3. Comparar el coste temporal de dos métodos de búsqueda
 *    permitiendo guardar los datos e imprimir la gráfica correspondiente.
 * 4. Comparar todos los algoritmos de búsqueda implementados permitiendo guardar los datos e imprimir la gráfica correspondiente.
 */
#include "TestBusqueda.h"
#include "AlgoritmosBusqueda.h"
#include "Mtime.h"
#include "Constantes.h"
#include "ConjuntoInt.h"
#include "AlgoritmosOrdenacion.h"
#include "Graficas.h"
#include <fstream>
#include <iomanip>
//** ESCRIBIR PARA COMPLETAR LA PRACTICA **//
TestBusqueda::TestBusqueda()
{
	nombreAlgoritmo.push_back("Secuencial");
	nombreAlgoritmo.push_back("Binaria");
	nombreAlgoritmo.push_back("Interpolacion");
	/**** EXA MAYO22 ****/
	nombreAlgoritmo.push_back("Ternaria");
} 
TestBusqueda::~TestBusqueda(){}

double TestBusqueda::buscaEnArrayDeInt(int key, int v[], int size, int metodo, int& posicion)
{
	AlgoritmosBusqueda estrategia;
	Mtime t;
	LARGE_INTEGER t_inicial, t_final;
	QueryPerformanceCounter(&t_inicial);
	/* Invoca al método de ordenación elegido */
	switch (metodo) {
	case SECUENCIAL: posicion = estrategia.busquedaSecuencialIt(v, size, key);
		break;
	case BINARIA: posicion = estrategia.busquedaBinariaIt(v, size, key);
		break;
	case INTERPOLACION: posicion = estrategia.busquedaInterpolacionIt(v, size, key);
		break;
	/**** EXA MAYO22 ****/
	case TERNARIA: posicion = estrategia.busquedaTernaria(v, size, key);
		break;
	}
	QueryPerformanceCounter(&t_final);
	return t.performancecounter_diff(&t_final, &t_inicial) * 1000;
}

void TestBusqueda::comprobarMetodosBusqueda()
{
	int talla,key,pos;
	cout << endl << endl << "Introduce la talla: ";
	cin >> talla;
	system("cls");
	for (int metodo = 0; metodo < nombreAlgoritmo.size(); metodo++)
	{
		ConjuntoInt* v = new ConjuntoInt(talla);
		v->GeneraVector(talla);
		cout << endl << endl << "vector inicial para el metodo " << nombreAlgoritmo[metodo] << ":" << endl << endl;
		if (metodo != SECUENCIAL)
		{
			AlgoritmosOrdenacion alg;
			alg.ordenaInsercion(v->getDatos(), talla);
		}
		v->escribe();
		cout << "\nIntroduce clave a buscar: ";
		cin >> key;
		double secs = buscaEnArrayDeInt(key, v->getDatos(), talla, metodo, pos);
		cout << endl << endl << "Posicion de "<<key<<" Busqueda con el metodo " << nombreAlgoritmo[metodo] << " es "<< pos << endl << endl;
		cout << "Tiempo: " << secs << " ms" << endl;
		//v->escribe();
		cout << endl;
		v->vaciar();
		system("pause");
		//system("cls");
	}
	system("cls");
}

void TestBusqueda::casoMedio(int metodo)
{
	ofstream f(nombreAlgoritmo[metodo] + ".dat");
	system("cls");
	cout << endl << "Busqueda " << nombreAlgoritmo[metodo];
	cout << "Tiempos de ejecucion " << endl << endl;
	cout << "\tTalla\t\tTiempo (ms)" << endl << endl;
	for (int talla = tallaIni; talla <= tallaFin; talla += incTalla)
	{
		/*Caso medio (añadir función teórica)*/
		double tiempo = 0.0;
		ConjuntoInt* v = new ConjuntoInt(talla);
		for (int i = 0; i < NUMREPETICIONES; i++)
		{
			v->GeneraVector(talla);
			if (metodo == BINARIA || metodo == INTERPOLACION || /**** EXA MAYO22 ****/  metodo == TERNARIA)
			{
				AlgoritmosOrdenacion alg;
				alg.ordenaInsercion(v->getDatos(), talla);
			}
			int key = v->generaKey(), pos;
			tiempo += buscaEnArrayDeInt(key, v->getDatos(), talla, metodo, pos);
			v->vaciar();
		}
		tiempo = tiempo / NUMREPETICIONES;

		f << talla << "\t" << tiempo << endl;
		cout << "\t" << talla << "\t\t" << setw(10) << setprecision(2) << (double)tiempo << " \t\t" << endl;
	}
	f.close();
	cout << endl << "Datos guardados en el fichero " << nombreAlgoritmo[metodo] << ".dat " << endl;

	/* Generar grafica */
	char opc;
	cout << endl << "Generar grafica de resultados? (s/n): ";
	cin >> opc;
	switch (opc)
	{
	case 's':
	case 'S':
	{
		/*CREAR GRAFICA*/
		Graficas g;
		int orden;
		switch (metodo)
		{
		case SECUENCIAL: orden = N;
			break;
		case BINARIA: orden = LOGN;
			break;
		case INTERPOLACION: orden = LOGLOGN;
			break;
		/**** EXA MAYO22 ****/
		case TERNARIA: orden = LOGN;
			break;
		}
		g.generarGraficaMEDIO(nombreAlgoritmo[metodo], orden);
		system("cls");
		break;
	}
	default: {cout << "Grafica no guardada en fichero " << endl;
	}
	}
	cout << endl;
	system("cls");
}

void TestBusqueda::comparar(int metodo1, int metodo2)
{
	ofstream f(nombreAlgoritmo[metodo1] + nombreAlgoritmo[metodo2] + ".dat");
	system("cls");
	cout << endl << "Comparacion " << nombreAlgoritmo[metodo1] << " y " << nombreAlgoritmo[metodo2];
	cout << "\tTiempos de ejecucion " << endl << endl;
	cout << "\tTalla\t\tTiempo (ms)" << "\t" << "Tiempo(ms)" << endl << endl;

	for (int talla = tallaIni; talla <= tallaFin; talla += incTalla)
	{
		
		ConjuntoInt* v = new ConjuntoInt(talla);
		double tiempo1 = 0.0;
		double tiempo2 = 0.0;
		for (int i = 0; i < NUMREPETICIONES; i++)
		{
			v->GeneraVector(talla);
			/**** EXA MAYO22 ****/
			if (metodo1 == BINARIA || metodo1 == INTERPOLACION || /**** EXA MAYO22 ****/  metodo1 == TERNARIA)
			{
				AlgoritmosOrdenacion alg;
				alg.ordenaInsercion(v->getDatos(), talla);
			}
			int key = v->generaKey(), pos;
			tiempo1 += buscaEnArrayDeInt(key, v->getDatos(), talla, metodo1, pos);
			v->vaciar();

			v->GeneraVector(talla);
			/**** EXA MAYO22 ****/
			if (metodo2 == BINARIA || metodo2 == INTERPOLACION || /**** EXA MAYO22 ****/  metodo2 == TERNARIA)
			{
				AlgoritmosOrdenacion alg;
				alg.ordenaInsercion(v->getDatos(), talla);
			}
			key = v->generaKey(), pos;
			tiempo2 += buscaEnArrayDeInt(key, v->getDatos(), talla, metodo2, pos);;
			v->vaciar();
		}
		tiempo1 = tiempo1 / NUMREPETICIONES;
		tiempo2 = tiempo2 / NUMREPETICIONES;

		f << talla << "\t" << tiempo1 << "\t" << tiempo2 << endl;
		cout << "\t" << talla << "\t\t" << setw(10) << setprecision(2) << (double)tiempo1 << " \t" << (double)tiempo2 << endl;
	}
	f.close();
	cout << endl << "Datos guardados en el fichero " << nombreAlgoritmo[metodo1] + nombreAlgoritmo[metodo2] << ".dat " << endl;

	/* Generar grafica */
	char opc;
	Graficas g;
	cout << endl << "Generar grafica de resultados? (s/n): ";
	cin >> opc;
	if (opc == 's' || 'S')
	{
		g.generarGrafica(nombreAlgoritmo[metodo1], nombreAlgoritmo[metodo2]);
	}
	else
	{
		cout << "Grafica no guardada en fichero " << endl;
	}
		
	cout << endl;
	system("pause");
	system("cls");
}


void TestBusqueda::comparaTodos()
{
	system("cls");
	cout << "\t\t\t  Comparativa de todos los algoritmos de Busqueda \t"
		<< "\n\n"
		<< "\t\t\t\t\tTIEMPO (ms)\n"
		<< "\t        TALLA \t       \t";
	for (int algoritmo = 0; algoritmo < nombreAlgoritmo.size(); algoritmo++)
		cout << nombreAlgoritmo[algoritmo] << "  \t";
	cout << "\n";

	ofstream file("tTodos.dat");
	if (file.fail())
		cout << "Error al abrir al crear el archivo.\nNo se guardaran los datos.\n";

	double* tiempo = new double[nombreAlgoritmo.size()];
	for (int talla = tallaIni; talla <= tallaFin; talla += incTalla) {
		ConjuntoInt* v = new ConjuntoInt(talla);

		for (int algoritmo = 0; algoritmo < nombreAlgoritmo.size(); algoritmo++) {
			tiempo[algoritmo] = 0;
			for (int i = 0; i < NUMREPETICIONES; i++) {
				v->GeneraVector(talla);
				int key = v->generaKey(), pos;
				if (algoritmo == BINARIA || algoritmo == INTERPOLACION || /**** EXA MAYO22 ****/  algoritmo == TERNARIA)
				{
					AlgoritmosOrdenacion alg;
					alg.ordenaInsercion(v->getDatos(), talla);
				}
				tiempo[algoritmo] += buscaEnArrayDeInt(key, v->getDatos(), talla, algoritmo, pos);
				v->vaciar();
			}
			tiempo[algoritmo] /= NUMREPETICIONES;
		}

		delete v;


		//Mostrar los datos
		cout.precision(4);
		cout << "\t\t" << talla;
		for (int algoritmo = 0; algoritmo < nombreAlgoritmo.size(); algoritmo++)
			cout << "\t     " << setw(10) << fixed << setprecision(4) << tiempo[algoritmo];
		cout << "\n";
		file << talla;
		for (int algoritmo = 0; algoritmo < nombreAlgoritmo.size(); algoritmo++)
			file << "\t" << tiempo[algoritmo];
		file << "\n";
	}
	file.close();

	//Generar grafica
	char opt;
	cout << "\nGenerar grafica de resultados? (s/n): ";
	cin >> opt;
	if (opt == 's' || opt == 'S') {
		Graficas g;
		g.generarGrafica(nombreAlgoritmo);
		cout << "La grafica fue generada.\n\n";
		system("start grafica.gpl");
	}
	else cout << "No se generara la grafica.\n\n";
}
