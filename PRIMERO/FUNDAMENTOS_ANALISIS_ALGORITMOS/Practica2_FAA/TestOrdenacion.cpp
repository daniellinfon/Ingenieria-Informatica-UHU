/* 
 * La clase TestOrdenacion contiene los metodos para:
 * 1. Comprobar que los métodos de ordenacion de la clase Ordenacion funcionan adecuadamente.
 * 2. Calcular la eficiencia para el caso medio de un método de ordenación,
 *    guardando los datos y permitiendo imprimir la gráfica correspondiente 
 * 3. Comparar el coste temporal de dos de los métodos de ordenación 
 *    burbuja, inserción, y selección, guardando los datos y permitiendo imprimir la gráfica correspondiente.
 */
#include "AlgoritmosOrdenacion.h"
#include "TestOrdenacion.h"
#include "ConjuntoInt.h"
#include "Mtime.h"
#include "Constantes.h"
#include "Graficas.h"
#include <string>
#include <iostream>
#include <fstream>
#include <iomanip>
using namespace std;


TestOrdenacion::TestOrdenacion()
{
	nombreAlgoritmo.push_back("Burbuja");
	nombreAlgoritmo.push_back("Insercion");
	nombreAlgoritmo.push_back("Seleccion");
} 
TestOrdenacion::~TestOrdenacion(){}

/*
 * Ordena un array de enteros según el método indicado
 * param v: el array de enteros a ordenar
 * param size: tamaño del array de enteros a ordenar
 * param metodo: Metodo de ordenacion a utilizar
 * return Tiempo empleado en la ordenación (en milisegundos)
 */
double TestOrdenacion::ordenarArrayDeInt(int v[],int size,int metodo) 
{
	AlgoritmosOrdenacion estrategia;
	Mtime t;
	LARGE_INTEGER t_inicial, t_final;
	QueryPerformanceCounter(&t_inicial);
	/* Invoca al método de ordenación elegido */
	switch (metodo){
		case BURBUJA: estrategia.ordenaBurbuja(v, size);
			break;
		case INSERCION: estrategia.ordenaInsercion(v, size);
			break;
		case SELECCION: estrategia.ordenaSeleccion(v, size);
			break;
	}
	QueryPerformanceCounter(&t_final);
	return t.performancecounter_diff(&t_final, &t_inicial) * 1000;   
}

/*
 * Comprueba los metodos de ordenacion
 */
void TestOrdenacion::comprobarMetodosOrdenacion()
{
	int talla;
	cout<<endl<<endl<<"Introduce la talla: ";
	cin>>talla; 
	system("cls"); 
	for (int metodo = 0; metodo < nombreAlgoritmo.size(); metodo++)
	{
		ConjuntoInt *v= new ConjuntoInt(talla); 
		v->GeneraVector(talla);
		cout <<endl<<endl<< "vector inicial para el metodo "<<nombreAlgoritmo[metodo]<< ":"<<endl<<endl;
		v->escribe();
		double secs=ordenarArrayDeInt(v->getDatos(),talla,metodo);
		cout<<endl<<endl<<"Array ordenado con metodo "<<nombreAlgoritmo[metodo]<< ":"<<endl<<endl;
		v->escribe();
		cout<<endl;
		v->vaciar(); 
		system("pause");
		system("cls");
	} 
	system("cls");
}
    
/*
 * Calcula el caso medio de un metodo de ordenacion.
 * Permite las opciones de crear el fichero de datos y la gráfica correspondiente.
 * param metodo: metodo de ordenacion a estudiar.
 */
void TestOrdenacion::casoMedio(int metodo)
//** ESCRIBIR PARA COMPLETAR LA PRACTICA **//
{
	ofstream f(nombreAlgoritmo[metodo] + ".dat");
	system("cls");
	cout << endl << "Ordenacion " << nombreAlgoritmo[metodo];
	cout << "  Tiempos de ejecucion " << endl << endl;
	cout << "\tTalla\t\tTiempo (ms)" << endl << endl;
	double tiempo = 0.0;
	for (int talla = tallaIni; talla <= tallaFin; talla += incTalla)
	{
			/*Caso medio (añadir función teórica)*/
			ConjuntoInt* v = new ConjuntoInt(talla);
			for (int i = 0; i < NUMREPETICIONES; i++)
			{
				v->GeneraVector(talla);
				tiempo += ordenarArrayDeInt(v->getDatos(), talla, metodo);
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
	
				/* CREA GRAFICA*/
			    Graficas g;
				g.generarGraficaMEDIO(nombreAlgoritmo[metodo], CUADRADO);
				system("cls");
				//system((gpl).c_str());
				//cout << endl << "Grafica guardada en el fichero " << nombreAlgoritmo[metodo] << ".pdf" << endl;
				break;
			}
			default:	{cout << "Grafica no guardada en fichero " << endl;
						}
	}
	cout << endl;
	system("pause");
	system("cls");
}



/*
 * Compara dos metodos de ordenacion. 
 * Genera el fichero de datos y permite las opcion de crear la gráfica correspondiente.
 * param metodo1: Primer metodo de ordenacion a comparar
 * param metodo2: Segundo metodo de ordenacion a comparar
 */
void TestOrdenacion::comparar(int metodo1, int metodo2) {
	//** ESCRIBIR PARA COMPLETAR LA PRACTICA **//
	ofstream f(nombreAlgoritmo[metodo1] + nombreAlgoritmo[metodo2]);
	system("cls");
	cout << endl << "*** Ordenacion por " << nombreAlgoritmo[metodo1] + " y " + nombreAlgoritmo[metodo2] + "***" << endl << endl;
	cout << "Tiempos de ejecucion promedio " << endl << endl;
	cout << "\t\t\t" + nombreAlgoritmo[metodo1] << "\t\t\t\t " + nombreAlgoritmo[metodo2] << endl << endl;
	cout << "\tTalla\t\tTiempo (mseg) \t\t\t\tTiempo (mseg)" << endl << endl;
	double tiempo1 = 0, tiempo2 = 0;
	double secs1 = 0, secs2 = 0;
	ConjuntoInt* v;
	Graficas g;

	for (int talla = tallaIni; talla <= tallaFin; talla += incTalla)
	{
		for (int i = 0; i < NUMREPETICIONES; i++)
		{
			v = new ConjuntoInt(talla);
			v->GeneraVector(talla);
			secs1 = ordenarArrayDeInt(v->getDatos(), talla, metodo1);
			secs2 = ordenarArrayDeInt(v->getDatos(), talla, metodo2);
			tiempo1 = tiempo1 + secs1;
			tiempo2 = tiempo2 + secs2;
			delete v;
		}

		f << talla << "\t" << tiempo1 << "\t" << tiempo2 << endl;
		cout << "\t" << talla << "\t\t" << setw(10) << setprecision(2) << (double)tiempo1 << " \t\t" << "\t\t" << setw(10) << setprecision(2) << (double)tiempo2 << " \t\t" << endl;
	}
	f.close();
	//generar grafica
	char op;
	cout << endl << "Generar grafica de resultados? (s/n): ";
	cin >> op;
	switch (op)
	{
	case 'S':
	case 's':
	{
		switch (metodo1)
		{

		case INSERCION:
		{
			if (metodo2 == BURBUJA)
			{
				g.generarGraficaCMP(nombreAlgoritmo[metodo1], nombreAlgoritmo[metodo2]);
				system("BurbujaInsercion.gpl"); system("cls");
				cout << endl << "Grafica guardada en el fichero BurbujaInsercion.pdf" << endl;
			}

			if (metodo2 == SELECCION)
			{
				g.generarGraficaCMP(nombreAlgoritmo[metodo1], nombreAlgoritmo[metodo2]);
				system("InsercionSeleccion.gpl"); system("cls");
				cout << endl << "Grafica guardada en el fichero InsercionSeleccion.pdf" << endl;
			}

			break;

		}

		case SELECCION:
		{
			if (metodo2 == INSERCION)
			{
				g.generarGraficaCMP(nombreAlgoritmo[metodo1], nombreAlgoritmo[metodo2]);
				system("InsercionSeleccion.gpl"); system("cls");
				cout << endl << "Grafica guardada en el fichero InsercionSeleccion.pdf" << endl;
			}

			if (metodo2 == BURBUJA)
			{
				g.generarGraficaCMP(nombreAlgoritmo[metodo1], nombreAlgoritmo[metodo2]);
				system("BurbujaSeleccion.gpl"); system("cls");
				cout << endl << "Grafica guardada en el fichero BurbujaSeleccion.pdf" << endl;
			}

			break;
		}

		case BURBUJA:
		{
			if (metodo2 == INSERCION)
			{
				g.generarGraficaCMP(nombreAlgoritmo[metodo1], nombreAlgoritmo[metodo2]);
				system("BurbujaInsercion.gpl"); system("cls");
				cout << endl << "Grafica guardada en el fichero BurbujaInsercion.pdf" << endl;
			}

			if (metodo2 == SELECCION)
			{
				g.generarGraficaCMP(nombreAlgoritmo[metodo1], nombreAlgoritmo[metodo2]);
				system("BurbujaSeleccion.gpl"); system("cls");
				cout << endl << "Grafica guardada en el fichero BurbujaSeleccion.pdf" << endl;
			}
			break;
		}
		}
	}
	break;
	}
	cout << endl;
	system("pause");
	system("cls");

}
void TestOrdenacion::comparaTodos()
{
	cout << "\t  Comparativa de todos los algoritmos \t"
		<< "\n\n"
		<< "\t             \t\t       \t      TIEMPO (ms)\n"
		<< "\t       TALLA \t       \t";
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
				tiempo[algoritmo] += ordenarArrayDeInt(v->getDatos(), talla, algoritmo);
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