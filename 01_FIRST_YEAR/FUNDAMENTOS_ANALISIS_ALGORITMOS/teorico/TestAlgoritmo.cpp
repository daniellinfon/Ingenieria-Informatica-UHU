/* 
 * La clase TestAlgoritmo contiene los metodos para:
 * 1. Comprobar que el/los algoritmos funcionan adecuadamente.
 * 2. Calcular la eficiencia para los casos de un método de búsqueda,
 *    permitiendo guardar los datos e imprimir la gráfica correspondiente 
 * 3. Comparar el coste temporal de los casos de búsqueda
 *    secuencial, permitiendo guardar los datos e imprimir la 
 *    gráfica correspondiente.
 */
#include "TestAlgoritmo.h"
#include "Constantes.h"
#include "ConjuntoInt_teor.h"
#include "Mtime.h"
#include <string>
#include <fstream>
#include <iomanip>
#include <iostream>
using namespace std;

TestAlgoritmo::TestAlgoritmo()
{
	nombreAlgoritmoCaso.push_back("SecuencialPeor");
	nombreAlgoritmoCaso.push_back("SecuencialMedio");
	nombreAlgoritmoCaso.push_back("SecuencialMejor");
} 
TestAlgoritmo::~TestAlgoritmo()
{
}

/* Comprueba que el algoritmo funciona */
void TestAlgoritmo::comprobarAlgoritmo()
{
	int talla;
	cout<<endl<<endl<<"Introduce la talla: ";
	cin>>talla; 
	ConjuntoInt *v= new ConjuntoInt(talla); 
	v->GeneraVector(talla);
	cout <<endl<<endl<< "vector para el algoritmo "<< ":"<<endl<<endl;
	v->escribe();
	int key;
	cout<<endl<<endl<<"Introduce la clave a buscar: ";
	cin>>key; 
	int posicion;
	Mtime t;
	LARGE_INTEGER t_ini, t_fin;
	double secs;
	QueryPerformanceCounter(&t_ini);
	/* ...hacer algo... */
	// Buscar la clave en el array
	posicion = v->busquedaSecuencial(key);
	QueryPerformanceCounter(&t_fin);
	secs = t.performancecounter_diff(&t_fin, &t_ini);
	cout<<endl<<endl<<"posicion de "<<key<<" buscado con el algoritmo de busqueda secuencial: "<<posicion<<endl<<endl;
	cout << "tiempo de ejecucion= " << secs * 1000000000 << "ns" << endl;
	v->vaciar(); 
	system("pause");
	system("cls");
}
/*
 * Calcula el coste TEORICO de los casos de un Algoritmo,
 * Permite las opciones de crear el fichero de datos y la gráfica correspondiente.
 * param metodo: caso del algoritmo a estudiar.
 */
	void TestAlgoritmo::costeCasoTeorico(int numerocaso)
	{
		ofstream f(nombreAlgoritmoCaso[numerocaso]+"Teorico.dat");
		system("cls");
		cout<<endl<<"Busqueda Secuencial" << nombreAlgoritmoCaso[numerocaso]+" Teorico";
		cout<<"Tiempos de ejecucion "<<endl<<endl;
		cout<<"\tTalla\t\tTiempo (oe)"<<endl<<endl;
		double tiempo=0;
		for (int talla = tallaIni; talla <= tallaFin; talla += incTalla)
		{
			switch (numerocaso){
			case SECUENCIALPEOR: /*Caso peor (T(n)= 6n+8)*/
				{
				tiempo = (double)6*talla + 8;
				}
				break;
			case SECUENCIALMEDIO:/*Caso medio (T(n)= 3n+8)*/
				{
					//tiempo = (double)3*talla+8;
					/*CASO MEDIO ORDENACION POR SELECCION T(n) = n^2*/
					//tiempo = (double)talla*talla;
					/*CASO MEDIO MERGESORT T(n) = NLogN*/
					tiempo = (double)log(talla);
				}
				break;
			case SECUENCIALMEJOR:/*Caso mejor (T(n)= 8)*/
				{
					tiempo = (double)8;
				}
				break;
			}
			f<<talla<<"\t"<< tiempo<<endl;
			cout<<"\t"<<talla<<"\t\t"<<setw(10)<<setprecision(2)<<(double)tiempo<<" \t\t"<<endl;
		}
		f.close();
		cout <<endl<<"Datos guardados en el fichero "<<nombreAlgoritmoCaso[numerocaso]<<"Teorico.dat "<<endl;
		
		/* Generar grafica */
		char opc;
		cout<<endl<<"Generar grafica de resultados? (s/n): ";
		cin>>opc;
		switch (opc){
		case 's':
		case 'S':{
			switch (numerocaso){
			case SECUENCIALMEJOR:{
				/* Ejecutar el fichero por lotes (comandos)*/
				system("CmdMejorTeorico.gpl"); system("cls");
				cout <<endl<<"Grafica guardada en el fichero "<<nombreAlgoritmoCaso[numerocaso]+"Teorico"<<".pdf"<<endl;
													 }break;
			case SECUENCIALPEOR:{
				/* Ejecutar el fichero por lotes (comandos)*/
				system("CmdPeorTeorico.gpl"); system("cls");
				cout <<endl<<"Grafica guardada en el fichero "<<nombreAlgoritmoCaso[numerocaso]+"Teorico"<<".pdf"<<endl;
													}break;
			case SECUENCIALMEDIO:{
				/* Ejecutar el fichero por lotes (comandos)*/
				system("CmdMedioTeorico.gpl"); 
				system("cls");
				//system((gpl).c_str());
				cout <<endl<<"Grafica guardada en el fichero "<<nombreAlgoritmoCaso[numerocaso]+"Teorico"<<".pdf"<<endl;
													 }break;
			default: {cout <<"Error caso "<<endl;}break;
				}
			}
		default: {cout <<"Grafica no guardada en fichero "<<endl;
						 }break;			 				
		}
		cout<<endl;
		system("pause");
		system("cls");
	}

/*
 * Compara los casos TEORICOS de un algoritmo. 
 * Permite las opciones de crear el fichero de datos y la gráfica correspondiente.
 * param metodo1: Primer algoritmo a comparar
 * param metodo2: Segundo algoritmo a comparar
 * param metodo3: Segundo algoritmo a comparar
 */
void TestAlgoritmo::compararTeorico(int metodo1, int metodo2, int metodo3) 
{
	ofstream f(nombreAlgoritmoCaso[metodo1]+nombreAlgoritmoCaso[metodo2]+nombreAlgoritmoCaso[metodo3]+"Teorico.dat");
	system("cls"); 
	cout<<endl<<"Busqueda Secuencial" << " Teorico";
	cout<<". Tiempos de ejecucion "<<endl<<endl;
	cout<<endl;;
	cout<<"\tTalla\t\tSecuencial\t\tBinaria\t\tInterpolacion\t\tTiempo (oe)"<<endl<<endl;
	double tiempoPeor=0;int tiempoMedio=0; int tiempoMejor=0;
	for (int talla = tallaIni; talla <= tallaFin; talla += incTalla) 
	{
		/*Caso peor (T(n)= 6n+8)*/
		tiempoPeor = (double)talla;
		/*Caso medio (T(n)= 3n+8)*/
		tiempoMedio = (double)log(talla);
		/*Caso mejor (T(n)= 8)*/
		tiempoMejor = (double)log(log(talla));
		/*escribir en el fichero*/
		f<<talla<<"\t"<< tiempoPeor<<"\t"<< tiempoMedio<<"\t"<< tiempoMejor<<endl;
		/*Visualizar en pantalla*/
		cout<<"\t"<<talla<<"\t\t"<<setw(10)<<setprecision(2)<<(double)tiempoPeor<<" \t"<<setw(10)<<setprecision(2)<<(double)tiempoMedio<<" \t"<<setw(10)<<setprecision(2)<<(double)tiempoMejor<<" \t\t" <<endl;
		cout<<endl;
	}
	f.close();
	cout <<endl<<"Datos guardados en el fichero "<<nombreAlgoritmoCaso[metodo1]+nombreAlgoritmoCaso[metodo2]+nombreAlgoritmoCaso[metodo3]<<"Teorico.dat "<<endl;
	/* Generar grafica */
		char opc;
		cout<<endl<<"Generar grafica de resultados? (s/n): ";
		cin>>opc;
		switch (opc){
		case 's':
		case 'S':{
			/* Ejecutar el fichero por lotes (comandos)*/
			system("CmdCompararTeorico.gpl"); system("cls");
			//system((gpl).c_str());
			cout <<endl<<"Grafica guardada en el fichero "<<nombreAlgoritmoCaso[metodo1]+nombreAlgoritmoCaso[metodo2]+nombreAlgoritmoCaso[metodo3]+"Teorico"<<".pdf"<<endl;
						 }break;
		default: {cout <<"Grafica no guardada en fichero "<<endl;
						 }break;
		}
		cout<<endl;
		system("pause");
		system("cls");
}
/*
 * Calcula el coste EMPÍRICO de los casos de un Algoritmo,
 * Permite las opciones de crear el fichero de datos y la gráfica correspondiente.
 * param metodo: caso del algoritmo a estudiar.
 */
void TestAlgoritmo::costeCasoEmpirico(int numerocaso)
{
	ofstream f(nombreAlgoritmoCaso[numerocaso] + "Empirico.dat");
	system("cls");
	cout << endl << "Busqueda Secuencial " << nombreAlgoritmoCaso[numerocaso] + " Empirico";
	cout << "Tiempos de ejecucion " << endl << endl;
	cout << "\tTalla\t\tTiempo (ms)" << endl << endl;
	double tiempo = 0;
	for (int talla = tallaIni; talla <= tallaFin; talla += incTalla)
	{
		switch (numerocaso) {
		case SECUENCIALPEOR: /*Caso peor no se encuentra la clave*/
		{
			ConjuntoInt* v = new ConjuntoInt(talla);
			v->GeneraVector(talla);
			//caso peor, clave no se encuentra
			int key = -1;
			int posicion;
			Mtime t;
			LARGE_INTEGER t_ini, t_fin;
			QueryPerformanceCounter(&t_ini);
			posicion = v->busquedaSecuencial(key);
			QueryPerformanceCounter(&t_fin);
			tiempo = t.performancecounter_diff(&t_fin, &t_ini);
			delete v;

		}
		break;
		case SECUENCIALMEDIO:/*Caso medio (T(n)= 3n+8)*/
		{
			ConjuntoInt* v = new ConjuntoInt(talla);
			v->GeneraVector(talla);
			//caso MEDIO, clave cualquier posicion
			//int key = ;
			int posicion;
			Mtime t;
			LARGE_INTEGER t_ini, t_fin;
			for (int i = 1; i < NUMREPETICIONES; i++)
			{
				QueryPerformanceCounter(&t_ini);
				v->busquedaSecuencial(v->generaKey());
				QueryPerformanceCounter(&t_fin);
				tiempo += t.performancecounter_diff(&t_fin, &t_ini);
			}
			tiempo = tiempo / NUMREPETICIONES;
			delete v;
		}
		break;
		case SECUENCIALMEJOR:/*Caso mejor (T(n)= 8)*/
		{
			ConjuntoInt* v = new ConjuntoInt(talla);
			v->GeneraVector(talla);
			//caso mejor, clave primera posicion
			//int key = ;
			int posicion;
			Mtime t;
			LARGE_INTEGER t_ini, t_fin;
			QueryPerformanceCounter(&t_ini);
			v->busquedaSecuencial(v->getPrimero());
			QueryPerformanceCounter(&t_fin);
			tiempo = t.performancecounter_diff(&t_fin, &t_ini);
			delete v;

		}
		break;
		}
		f << talla << "\t" << tiempo*1000 << endl;
		cout << "\t" << talla << "\t\t" << setw(10) << setprecision(2) << (double)tiempo*1000000 << " \t\t" << endl;
	}
	f.close();
	cout << endl << "Datos guardados en el fichero " << nombreAlgoritmoCaso[numerocaso] << "Empirico.dat " << endl;

	/* Generar grafica */
	char opc;
	cout << endl << "Generar grafica de resultados? (s/n): ";
	cin >> opc;
	switch (opc) {
	case 's':
	case 'S': {
		switch (numerocaso) {
		case SECUENCIALMEJOR: {
			/* Ejecutar el fichero por lotes (comandos)*/
			system("CmdMejorEmpirico.gpl"); system("cls");
			cout << endl << "Grafica guardada en el fichero " << nombreAlgoritmoCaso[numerocaso] + "Empirico" << ".pdf" << endl;
		}break;
		case SECUENCIALPEOR: {
			/* Ejecutar el fichero por lotes (comandos)*/
			system("CmdPeorEmpirico.gpl"); system("cls");
			cout << endl << "Grafica guardada en el fichero " << nombreAlgoritmoCaso[numerocaso] + "Empirico" << ".pdf" << endl;
		}break;
		case SECUENCIALMEDIO: {
			/* Ejecutar el fichero por lotes (comandos)*/
			system("CmdMedioEmpirico.gpl");
			system("cls");
			//system((gpl).c_str());
			cout << endl << "Grafica guardada en el fichero " << nombreAlgoritmoCaso[numerocaso] + "Empirico" << ".pdf" << endl;
		}break;
		default: {cout << "Error caso " << endl; }break;
		}
	}
	default: {cout << "Grafica no guardada en fichero " << endl;
	}break;
	}
	cout << endl;
	system("pause");
	system("cls");
}
/*
  * Compara los casos EMPÍRICOS de un algoritmo.
  * Permite las opciones de crear el fichero de datos y la gráfica correspondiente.
  * param metodo1: Primer algoritmo a comparar
  * param metodo2: Segundo algoritmo a comparar
  * param metodo3: Segundo algoritmo a comparar
  */


void TestAlgoritmo::compararEmpirico(int metodo1, int metodo2, int metodo3)
{
	ofstream f(nombreAlgoritmoCaso[metodo1] + nombreAlgoritmoCaso[metodo2] + nombreAlgoritmoCaso[metodo3] + "Empirico.dat");
	system("cls");
	cout << endl << "Busqueda Secuencial" << " Empirico";
	cout << ". Tiempos de ejecucion " << endl << endl;
	cout << endl;;
	cout << "\tTalla\t\tTiempo (ms)" << endl << endl;

	int keyp, keym, keymj;
	int pospeor, posmedio, posmejor;
	Mtime t;
	LARGE_INTEGER t_ini, t_fin;
	double tiempoPeor = 0; double tiempoMedio = 0; double tiempoMejor = 0;
	for (int talla = tallaIni; talla <= tallaFin; talla += incTalla)
	{
		ConjuntoInt* v = new ConjuntoInt(talla);
		v->GeneraVector(talla);

		/*Caso peor (T(n)= 6n+8)*/
		keyp = -1;

		QueryPerformanceCounter(&t_ini);
		pospeor = v->busquedaSecuencial(keyp);
		QueryPerformanceCounter(&t_fin);

		tiempoPeor = t.performancecounter_diff(&t_fin, &t_ini);
		tiempoPeor = tiempoPeor * 1000000;

		/* Caso medio(T(n) = 3n + 8) */
		tiempoMedio = 0;
		for (int i = 1; i < NUMREPETICIONES; i++)
		{
			keym = v->generaKey();
			QueryPerformanceCounter(&t_ini);

			posmedio = v->busquedaSecuencial(keym);
			QueryPerformanceCounter(&t_fin);

			tiempoMedio += t.performancecounter_diff(&t_fin, &t_ini);
		}

		tiempoMedio = tiempoMedio / NUMREPETICIONES;
		tiempoMedio = tiempoMedio * 1000000;

		/* Caso mejor(T(n) = 8) */
		keymj = v->getPrimero();
		QueryPerformanceCounter(&t_ini);


		posmejor = v->busquedaSecuencial(keymj);

		QueryPerformanceCounter(&t_fin);

		tiempoMejor = t.performancecounter_diff(&t_fin, &t_ini);
		tiempoMejor = tiempoMejor * 1000000;

		/* escribir en el fichero */
		f << talla << "\t" << tiempoPeor << "\t" << tiempoMedio << "\t" << tiempoMejor << endl;
		/* Visualizar en pantalla */
		cout << "\t" << talla << "\t\t" << setw(10) << setprecision(2) << (double)tiempoPeor << " \t" << setw(10) << setprecision(2) << (double)tiempoMedio << " \t" << setw(10) << setprecision(2) << (double)tiempoMejor << " \t\t" << endl;
		cout << endl;
	}
	f.close();
	cout << endl << "Datos guardados en el fichero " << nombreAlgoritmoCaso[metodo1] + nombreAlgoritmoCaso[metodo2] + nombreAlgoritmoCaso[metodo3] << "Empirico.dat " << endl;
	/* Generar grafica */
	char opc;
	cout << endl << "Generar grafica de resultados? (s/n): ";
	cin >> opc;
	switch (opc) {
	case 's':
	case 'S': {
		/* Ejecutar el fichero por lotes (comandos)*/
		system("CmdCompararEmpirico.gpl"); system("cls");
		//system((gpl).c_str());
		cout << endl << "Grafica guardada en el fichero " << nombreAlgoritmoCaso[metodo1] + nombreAlgoritmoCaso[metodo2] + nombreAlgoritmoCaso[metodo3] + "Empirico" << ".pdf" << endl;
	}break;
	default: {cout << "Grafica no guardada en fichero " << endl;
	}break;
	}
	cout << endl;
	system("pause");
	system("cls");
}