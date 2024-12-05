#include <iostream>
using namespace std;
#include <cstdlib> /* para srand, rand */
#include "ConjuntoInt_emp.h"

ConjuntoInt_emp::ConjuntoInt_emp (int max)
{
 tamano= max;
 datos= new int[max];
}
ConjuntoInt_emp::~ConjuntoInt_emp()
{
 delete[] datos;
}
void ConjuntoInt_emp::vaciar ()
{
 tamano= 0;
}
int* ConjuntoInt_emp::getDatos()
{
	int* v=datos;
	for (int i= 0; i <tamano;i++){
		v[i]=datos[i];}
	return v;
}

void ConjuntoInt_emp::GeneraVector (int tam)
{
 tamano=tam;
 srand((unsigned)time(NULL)); //srand(time(0));
 for (int i= 0; i<tamano; i++){
     datos[i] = rand()%1000; //genera un número aleatorio entre 1 y 999
 }
}

void ConjuntoInt_emp::escribe()
{
 for (int i= 0; i<tamano; i++)
     cout << datos[i] << (i<tamano-1? ", ": "\n");
}

