#ifndef CONTRATOTP_H
#define CONTRATOTP_H

#include <iostream> //cin, cout
#include "Fecha.h"
#include "Contrato.h"

using namespace std;

class ContratoTP: public Contrato {
  static int minutosTP;
  static float precioTP;
  int minutosHablados;
  static const float precioExcesoMinutos;
  char *correo;

public:
  ContratoTP(long int dni, Fecha f, int m, char *c);
  virtual ~ContratoTP(); //¿es necesario? pensar y reflexionad
  ContratoTP(const ContratoTP& c);  //¿es necesario? pensar y reflexionad
  //ContratoTP& operator=(const ContratoTP& c); //¿es necesario? pensar y reflexionad

  static int getLimiteMinutos() { return ContratoTP::minutosTP; }
  static float getPrecio() { return ContratoTP::precioTP; }
  static void setTarifaPlana(int m, float p); //el el .cpp se pone la cabecera sin la palabra static

  int getMinutosHablados() const {return minutosHablados;}
  void setMinutosHablados(int m);
  float factura() const;
  void ver() const;
 // virtual void nada() const { ; } //lo implemento (si no quiero que haga nada pongo ;
  const char* getCorreo() const {return correo;}
};

ostream& operator<<(ostream &s, const ContratoTP &c);

#endif // CONTRATOTP_H
