#ifndef PILA_H
#define PILA_H
#include <CriptoDoc.h>

class pila
{
    int* elementos;  // elementos de la pila
    int Tope;           // tope de la pila
    int Tama;      // tamaño máximo de la tabla

public:

    pila();      // constructor de la clase
    ~pila();     // destructor de la clase
    void apilar(int e);
    void desapilar();
    bool esvacia();
    int cima() ;
    int longitud();
};

#endif // PILA_H
