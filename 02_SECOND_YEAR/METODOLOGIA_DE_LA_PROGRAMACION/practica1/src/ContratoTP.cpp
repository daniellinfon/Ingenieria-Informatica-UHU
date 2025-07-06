#include "ContratoTP.h"
#include <cstring> //strlen, strcpy

int ContratoTP::minutosTP=300;
float ContratoTP::precioTP=10;
const float ContratoTP::precioExcesoMinutos=0.15;

ContratoTP::ContratoTP(long int dni, Fecha f, int m, char *c):Contrato(dni,f)
{
    minutosHablados=m;
    this->correo=new char [strlen(c)+1];
    strcpy(this->correo, c);
}

//static se pone en el .h (no se pone en el .cpp)
void ContratoTP::setTarifaPlana(int m, float p) {
  ContratoTP::minutosTP=m; //puedo poner minutosTP=m ...pongo ContratoTP::minutosTP para recordar que es estatico
  ContratoTP::precioTP=p;  //puedo poner precioTP=p  ...pongo ContratoTP::precioTP para recordar que es estatico
}

float ContratoTP::factura() const
{
    int exceso;
    if(minutosHablados>minutosTP)
        exceso=minutosHablados-minutosTP;
    else
        exceso=0;
    return precioTP+(exceso*precioExcesoMinutos);
}

void ContratoTP::setMinutosHablados(int m)
{
    this->minutosHablados=m;
}

void ContratoTP::ver() const
{
    Contrato::ver();
    cout<<" "<<minutosHablados<<"m, "<<minutosTP<<"("<<precioTP<<") - ";
    cout<< factura()<<"€, "<<correo;

}

ostream& operator<<(ostream &s, const ContratoTP &c)
{
    s << (Contrato &)c;
    s << " " << c.getMinutosHablados() << "m, " << c.getLimiteMinutos() << "(" << c.getPrecio() << ") - ";
    s << c.factura() << "€, ";
    s << c.getCorreo();
    return s;
}

ContratoTP::~ContratoTP()
{
    delete [] correo;
}

ContratoTP::ContratoTP(const ContratoTP& c):Contrato(c.getDniContrato(), c.getFechaContrato())
{
    this->minutosTP=c.minutosTP;
    this->precioTP=c.precioTP;
    this->minutosHablados=c.minutosHablados;
    //precioExcesoMinutos;
    this->correo= new char [strlen(c.correo)+1];
    strcpy(this->correo, c.correo);
}
//RESTO DE METODOS Y FUNCIONES A RELLENAR POR EL ALUMNO...
