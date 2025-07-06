#include "Clasificacion.h"
#include <iostream>
#include <fstream>
using namespace std;

//FUNCIÓN GENÉRICA
void intercambiar(Participante &mayor, Participante &menor)
{
    Participante aux;
    aux = mayor;
    mayor = menor;
    menor = aux;
}

Clasificacion::Clasificacion()
{
    participantes=0;
    tamano=0;
    elementos=NULL;
}

Clasificacion::~Clasificacion()
{
    if(elementos!=NULL)
        delete[]elementos;
}

void Clasificacion::anadirparticipante(Participante a)
{
    if(tamano==participantes)
    {
        Participante *nuevo=new Participante[tamano+SALTO];
        if(nuevo!=NULL)
            for(int i=0; i<participantes; i++)
                nuevo[i]=elementos[i];
        if(elementos!=NULL)
            delete[]elementos;
        nuevo[participantes]=a;
        elementos=nuevo;
        participantes++;
        tamano+=SALTO;
    }
    else
    {
        elementos[participantes]=a;
        participantes++;
    }
}

void Clasificacion::eliminar(int i) //1 hasta par..
{
    if(i>participantes)
        cout<<"Posicion no admitida\n";
    else
    {
        for(int j=i-1; j<participantes-1; j++)
            elementos[j]=elementos[j+1];
        participantes--;
    }


}

Participante Clasificacion::consultar(int n)
{
    if(n>participantes)
        cout<<"Posicion no admitida\n";
    else
    {
        return elementos[n-1];
    }
}

bool Clasificacion::vacio()
{
    return(participantes==0);
}

int Clasificacion::numparticipantes()
{
    return participantes;
}

void Clasificacion::ordenar()
{
    Burbuja(0, participantes-1);
}

void Clasificacion::Burbuja(int inicio, int fin)
{
        int pos,ele;
        for (pos=inicio; pos<fin; pos++)
            for (ele=fin; ele>pos; ele--)
                if(elementos[ele].marca<elementos[ele-1].marca)
                    intercambiar(elementos[ele-1],elementos[ele]); //Función genérica implementada
}
