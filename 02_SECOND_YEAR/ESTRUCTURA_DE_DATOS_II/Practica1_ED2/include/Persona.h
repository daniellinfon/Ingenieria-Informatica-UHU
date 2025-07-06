#ifndef PERSONA_H
#define PERSONA_H
#include <iostream>
#include <string>

using namespace std;

class Persona
{
 private:
 string nombre;
 int edad;

 public:
 Persona(const string& n = "", int e = 0);
 const string& getNombre() const { return nombre;}
 int getEdad() const { return edad;}
 void setNombre(const string& n){nombre=n;}
 void setEdad(int e) { edad=e;}
 bool operator==(const Persona& p) const;


};
ostream& operator<<(ostream &s, const Persona &p);
#endif // PERSONA_H
