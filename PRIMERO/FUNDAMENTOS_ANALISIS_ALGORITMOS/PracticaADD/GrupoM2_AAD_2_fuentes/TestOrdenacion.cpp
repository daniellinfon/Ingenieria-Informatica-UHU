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
#include "ConjuntoInt_emp.h"
#include "Mtime.h"
#include "Constantes.h"
#include <string>
#include <iostream>
#include <fstream>
#include <iomanip>
#include "Graficas.h"
using namespace std;


TestOrdenacion::TestOrdenacion()
{
	nombreAlgoritmo.push_back("Shakersort");
	nombreAlgoritmo.push_back("Mergesort");
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
		case SHAKERSORT: estrategia.ordenaShakersort(v, size);
			break;
		case MERGESORT: estrategia.ordenaMergesort(v, size);
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
		ConjuntoInt_emp *v= new ConjuntoInt_emp(talla); 
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
	cout << "Tiempos de ejecucion " << endl << endl;
	cout << "\tTalla\t\tTiempo (ms)" << endl << endl;
	double tiempo = 0.0;
	for (int talla = tallaIni; talla <= tallaFin; talla += incTalla)
	{
			/*Caso medio (añadir función teórica)*/
			ConjuntoInt_emp* v = new ConjuntoInt_emp(talla);
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
				/*CREAR GRAFICA*/
				Graficas g;
				if(metodo == 2 || metodo == 0)
					g.generarGraficaMEDIO(nombreAlgoritmo[metodo], CUADRADO);
				else
					g.generarGraficaMEDIO(nombreAlgoritmo[metodo], NlogN);
				system("cls");
				break;
			}
			default:	{cout << "Grafica no guardada en fichero " << endl;
						}
	}
	cout << endl;
	system("cls");
}



/*
 * Compara dos metodos de ordenacion. 
 * Genera el fichero de datos y permite las opcion de crear la gráfica correspondiente.
 * param metodo1: Primer metodo de ordenacion a comparar
 * param metodo2: Segundo metodo de ordenacion a comparar
 */
void TestOrdenacion::comparar(int metodo1, int metodo2)
{
	//** ESCRIBIR PARA COMPLETAR LA PRACTICA **//
	ofstream f(nombreAlgoritmo[metodo1] + nombreAlgoritmo[metodo2]+".dat");
	system("cls");
	cout << endl << "Comparacion " << nombreAlgoritmo[metodo1]<< "\t "<< nombreAlgoritmo[metodo2];
	cout << "Tiempos de ejecucion " << endl << endl;
	cout << "\tTalla\t\tTiempo (ms)" <<"\t"<<"Tiempo(ms)"<< endl << endl;
	
	for (int talla = tallaIni; talla <= tallaFin; talla += incTalla)
	{
		/*Caso medio (añadir función teórica)*/
		ConjuntoInt_emp* v = new ConjuntoInt_emp(talla);
		double tiempo1 = 0.0;
		double tiempo2 = 0.0;
		for (int i = 0; i < NUMREPETICIONES; i++)
		{
			v->GeneraVector(talla);
			tiempo1 += ordenarArrayDeInt(v->getDatos(), talla, metodo1);
			v->vaciar();

			v->GeneraVector(talla);
			tiempo2 += ordenarArrayDeInt(v->getDatos(), talla, metodo2);
			v->vaciar();
		}
		tiempo1 = tiempo1 / NUMREPETICIONES;
		tiempo2 = tiempo2 / NUMREPETICIONES;
		
		f << talla << "\t" << tiempo1 <<"\t"<< tiempo2<< endl;
		cout << "\t" << talla << "\t\t" << setw(10) << setprecision(2) << (double)tiempo1 << " \t" <<(double)tiempo2<< endl;
	}
	f.close();
	cout << endl << "Datos guardados en el fichero " << nombreAlgoritmo[metodo1] + nombreAlgoritmo[metodo2] << ".dat " << endl;

	/* Generar grafica */
	char opc;
	Graficas g;
	cout << endl << "Generar grafica de resultados? (s/n): ";
	cin >> opc;
	switch (opc)
	{
	case 's':
	case 'S':
	{
		switch (metodo1)
		{

			case MERGESORT:
			{
				if (metodo2 == SHAKERSORT)
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
				if (metodo2 == MERGESORT)
				{
					g.generarGraficaCMP(nombreAlgoritmo[metodo1], nombreAlgoritmo[metodo2]);
					system("InsercionSeleccion.gpl"); system("cls");
					cout << endl << "Grafica guardada en el fichero InsercionSeleccion.pdf" << endl;
				}

				if (metodo2 == SHAKERSORT)
				{
					g.generarGraficaCMP(nombreAlgoritmo[metodo1], nombreAlgoritmo[metodo2]);
					system("BurbujaSeleccion.gpl"); system("cls");
					cout << endl << "Grafica guardada en el fichero BurbujaSeleccion.pdf" << endl;
				}

				break;
			}

			case SHAKERSORT:
			{
				if (metodo2 == MERGESORT)
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
			default: {cout << "Grafica no guardada en fichero " << endl;
		}
	}
	
	}
	}
	cout << endl;
	system("pause");
	system("cls");
}

/*
 * Compara todos los metodos de ordenacion.
 * Permite crear la gráfica correspondiente.
 */
void TestOrdenacion::comparaTodos()
{
	system("cls");
	cout << "\t\t\t  Comparativa de todos los algoritmos \t"
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
		ConjuntoInt_emp* v = new ConjuntoInt_emp(talla);

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
	system("pause");
}