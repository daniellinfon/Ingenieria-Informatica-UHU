/*
 * Clase Graficas, contiene métodos para guardar las gráficas de los resultados, es decir, crea
 * los ficheros por lo lotes (comandos) para generar los ficheros gráficos que corresponda.
 */
#include "Graficas.h"
#include "TestOrdenacion.h"
#include "Constantes.h"
#include <string>
#include <iostream>
#include <fstream>
#include <iomanip>

using namespace std;

 /*
  * Método generarGraficaMEDIO, genera el fichero de comandos para GNUPlot y dibuja la gráfica
  * del caso medio de un método de ordenación y su ajuste a la función correspondiente.
  * param metodo: metodo de ordenacion.
  * param orden: Orden del metodo de ordenacion.
  */
void Graficas::generarGraficaMEDIO(string nombre_metodo,int orden)
{
  //** ESCRIBIR PARA COMPLETAR LA PRACTICA **//
	ofstream fout("CmdMedio.gpl");

	fout << "#ESTE ES UN SCRIPT DE CASO MEDIO\n";
	fout << "set title \"" << nombre_metodo << "\"" << endl;
	fout << "set key top left vertical inside " << endl;
	fout << "set xlabel \" Talla(n)\"" << endl;
	fout << "set ylabel \" Tiempo (ms)\"" << endl;
	switch (orden)
	{
	case CUADRADO:
	{
		fout << "Cuadrado(x)=a*x*x + b*x + c" << endl;
		fout << "fit Cuadrado(x) \"" + nombre_metodo + ".dat\" using 1:2 via a,b,c " << endl;
		fout << "plot \"" + nombre_metodo + ".dat\" using 1 : 2 title \"" + nombre_metodo + "\", Cuadrado(x)" << endl;
	}
	break;
	case NLOGN:
	{
		fout << "NLOGN(x)=a*x*log(x)+b*x+c " << endl;
		fout << "fit NLOGN(x) \"" + nombre_metodo + ".dat\" using 1:2 via a,b,c " << endl;
		fout << "plot \"" + nombre_metodo + ".dat\" using 1 : 2 title \"" + nombre_metodo + "\", NLOGN(x)" << endl;
	}
	break;
	case N:
	{
		fout << "N(x)=a*x + b " << endl;
		fout << "fit N(x) \"" + nombre_metodo + ".dat\" using 1:2 via a,b " << endl;
		fout << "plot \"" + nombre_metodo + ".dat\" using 1 : 2 title \"" + nombre_metodo + "\", N(x)" << endl;
	}
	break;
	case LOGN:
	{
		fout << "LogN(x)=a*log(x)+b " << endl;
		fout << "fit LogN(x) \"" + nombre_metodo + ".dat\" using 1:2 via a,b " << endl;
		fout << "plot \"" + nombre_metodo + ".dat\" using 1 : 2 title \"" + nombre_metodo + "\", LogN(x)" << endl;
	}
	break;
	case LOGLOGN:
	{
		fout << "LogLogN(x)=a*log(log(x))+b " << endl;
		fout << "fit LogLogN(x)\"" + nombre_metodo + ".dat\" using 1:2 via a,b " << endl;
		fout << "plot \"" + nombre_metodo + ".dat\" using 1 : 2 title \"" + nombre_metodo + "\", LogLogN(x)" << endl;
	}
	break;
	}
	fout << "set terminal pdf" << endl;
	fout << "set output \"" + nombre_metodo + ".pdf\"" << endl;
	fout << "replot" << endl;
	fout << "pause - 1" << endl;
	fout.close();

	system("CmdMedio.gpl");
	cout << "grafica creada en " << nombre_metodo << ".pdf" << endl;
	
}	

/*
 * Método generarGrafica, genera el fichero de comandos para GNUPlot.
 * param nombre1: es el nombre del fichero de datos del primer método de ordenación 
 * param nombre2: es el nombre del fichero de datos del segundo método de ordenación 
 */
void  Graficas::generarGrafica(string nombre1,string nombre2)
{
	ofstream f("CmdComparar.gpl");
	f << "#ESTE ES UN SCRIPT DE GNUPLOT PARA COMPARACION DE 2 METODOS" << endl;
	f << "set title \" Comparacion tiempos " << nombre1 << " y " << nombre2 << "\"" << endl;
	f << "set key top left vertical inside" << endl;
	f << "set grid" << endl;
	f << "set xlabel \"Talla (n)\"" << endl;
	f << "set ylabel \"Tiempo (ms)\"" << endl;
	f << "plot \"" << nombre1 + nombre2 << +".dat\" using 1:2 with lines title \"" << nombre1 << "\"," << "\"" << nombre1 + nombre2 << +".dat\" using 1:3 with lines title \"" << nombre2 << "\"" << endl;
	f << "set terminal pdf" << endl;
	f << "set output \"" << nombre1 + nombre2 << +".pdf\"" << endl;
	f << "replot" << endl;
	f << "pause -1 \"Pulsa Enter para continuar...\"" << endl;
	f.close();
	system("CmdComparar.gpl");
	cout << "Grafica guardada en" << nombre1 << nombre2 << ".pdf";
}

/*
 * Dibuja la gráfica de todos los métodos de ordenación.
 */
void Graficas::generarGrafica(vector<string> nombreAlgoritmo)
{
	ofstream f("grafica.gpl");
	f << "#ESTE ES UN SCRIPT DE GNUPLOT PARA ESTUDIO DE TODOS LOS METODOS" << endl;
	f << "set title \"Comparativa de todos los algoritmos\"\n"
		<< "set key top left vertical inside\n"
		<< "set grid\n"
		<< "set xlabel \"Talla (n)\"\n"
		<< "set ylabel \"Tiempo(ms)\"\n"
		<< "\n";

	f << "plot ";
	for (int algoritmo = 0; algoritmo < nombreAlgoritmo.size(); algoritmo++)
		f << "\"tTodos.dat\" using 1:" << (algoritmo + 2) << " with lines title \"" << nombreAlgoritmo[algoritmo] << "\"" << (algoritmo == nombreAlgoritmo.size() - 1 ? "\n" : ", ");
	f << "\n"
		<< "set terminal pdf\n"
		<< "set output \"busquedaComparativaTodos.pdf\"\n"
		<< "replot\n"
		<< "#pause -1 \"Pulsa Enter para continuar...\"\n"
		<< "unset output";

	f.close();
}