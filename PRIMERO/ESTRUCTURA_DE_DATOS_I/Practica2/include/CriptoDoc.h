#ifndef CRIPTODOC_H
#define CRIPTODOC_H
#include "Lista.h"
#include <fstream>


class CriptoDoc
{
    lista texto;
public:
    bool leer(char fichero[]);
    bool escribir(char fichero[]);
    void cifrar(int codigo);
    void descifrar(int codigo);
    void vaciar();
    void concatenar(CriptoDoc &doc);
    int numlineas();
    linea observar(int i);
};

#endif // CRIPTODOC_H
