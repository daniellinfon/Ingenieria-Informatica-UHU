
#include <iostream>
using namespace std;

typedef char Cadena[50]; // Tipo de datos Cadena
#define MAX_CUENTAS 10 // Número de Cuentas
#define MAX_CLIENTES 100 // Número de clientes
class Cliente
{
 Cadena Nombre; // Nombre y dirección
 Cadena Direccion;
 Cuenta Cuentas[MAX_CUENTAS]; // cuentas corrientes
 int NoCuentas; // Nº de cuentas abiertas
 public:
 Cliente();
 void ActualizarCliente(Cadena pNomb, Cadena pDir);
 void DameNombre(Cadena pNom);
 void DameDireccion(Cadena pDir);
 int BuscarCuenta(int pNoCuenta);
 bool CrearCuenta(Cuenta pCu);
 bool ActalizarCuenta(Cuenta pCu);
 bool BorrarCuenta(int pNoCuenta);
 int DameNoCuentas();
 Cuenta DameCuenta(int pos);
 void Mostrar(char Campo);
};

Cliente::Cliente()
{
    Nombre=0;
    Direccion=0;
    NoCuentas=0;
}

void Cliente::ActalizarCliente(Cadena pNomb, Cadena pDir)
{
    Nombre=pNomb;
    Direccion=pDir;
    NoCuentas=0;
}
void Cliente::DameNombre(Cadena pNom)
{
    pNom=Nombre;
}

void Cliente::DameDireccion(Cadena pDir)
{
    pDir=Direccion;
}

int BuscarCuenta(int pNoCuenta)
{

}
