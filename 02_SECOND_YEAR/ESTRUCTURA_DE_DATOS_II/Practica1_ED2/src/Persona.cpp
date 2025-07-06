#include "Persona.h"

Persona::Persona(const string& n , int e)
{
    nombre=n;
    edad=e;
}

bool Persona::operator==(const Persona& p) const
{
    if(nombre==p.nombre && edad==p.edad)
        return true;
    else
        return false;
}

ostream& operator<<(ostream &s, const Persona &p)
{
    s << p.getNombre() <<", " <<p.getEdad();
    return s;
}
