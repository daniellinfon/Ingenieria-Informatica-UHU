#pragma once

#include <iostream>
using namespace std;

/////////// Declaraci�n de la clase conjuntoInt /////////////

class ConjuntoInt_emp
{
private:
	int tamano;
	int *datos; 
public:
	ConjuntoInt_emp (int max= 0); 
	~ConjuntoInt_emp (); 
	void vaciar ();
	void GeneraVector (int tam);
	int* getDatos();
	void escribe ();
};