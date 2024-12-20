#ifndef CLASIFICACION_H
#define CLASIFICACION_H
#include <Prueba.h>
using namespace std;

struct Participante {
    int indice;
	int dorsal;
	int marca;
};

class Clasificacion {
    	Participante *elementos; //elementos de la tabla
    	int participantes;
    	int tamano;
 public:
    	Clasificacion();
    	~Clasificacion();
    	void anadirparticipante(Participante a);
    	void eliminar(int i);
    	Participante consultar(int n);
    	bool vacio();
    	int numparticipantes();
    	void ordenar();
    	void Burbuja(int inicio, int fin);
};

#endif // CLASIFICACION_H
