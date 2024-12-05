#ifndef PRUEBA_H
#define PRUEBA_H
#include <fstream>
#include <string.h>
#include <cstdlib>

using namespace std;

#define SALTO 4
#define SALTO2 2

const int TAM_CADENA = 30;

typedef char cadena[TAM_CADENA];

struct Ciclista {
	int dorsal;
	cadena pais;
	cadena nombre;
	cadena apellidos;
	int marca;
	int posicion;
};

struct PaisParticipante
{
 cadena nombrePais; //nombre del país con representación en la prueba
 int numParticipantes; //número de ciclistas inscritos de dicho país
};

class Prueba {
	    fstream fich; //fichero primera fase
        fstream fichero; //fichero segunda fase
		int numCiclistas;
		PaisParticipante *paises;
        int numPaises;
        int tamPaises;

public:
		Prueba(char FicheroOrigen[],char FicheroDestino[]);
		~Prueba();
		int getNumCiclistas();
		void mostrar(cadena pais);
		Ciclista consultar(int posicion);
		int buscar(int dor);
		void insertar(Ciclista c);
		void modificar(Ciclista c, int posicion);
		void eliminar(int posicion);
		void Competicion();
		void generarInfoPaises();
        void mostrarInfoPaises();
};

#endif // PRUEBA_H
