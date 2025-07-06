#include <iostream>
#include <cstring>
using namespace std;

typedef char Cadena[50];
struct Vehiculo
{
    Cadena Matricula; //Matricula de veh�culo
    float hora; //Hora de pasada
    int Kilometros; //Kil�metro de la carretera.
    bool Cadenas; //true si lleva cadenas puestas.
};

class PuertoMontana
{
    Vehiculo Trafico[10000];
    int NVehiculos;
//N�mero de veh�culos detectados, desde Trafico[0] hasta Trafico[NVehiculos-1]
    Cadena Nombre; //Nombre del puerto de monta�a
    bool Abierto; //true si el puerto est� abierto
public:
    bool ActualizarPuerto(float pTemperaturas[3]);
    void Dame_Nombre(Cadena pNombre);
    int VehiculosEnPeligro(int KmInicial, int KmFinal);
};

void Dame_Nombre(Cadena pNombre)
{
    strcpy(pnombre,Nombre);

}

bool ActualizarPuerto(float pTemperaturas[3])
{
    bool Abierto;
    float TemperaturaMedia=0;
    for (int i=0; i<3; i++)
        TemperaturaMedia= TemperaturaMedia + pTemperaturas[i];
    TemperaturaMedia= TemperaturaMedia / 3.0;

    if (TemperaturaMedia>=0) Abierto=true;
    else Abierto=false;
    return Abierto;
}

int VehiculosEnPeligro(int KmInicial, int KmFinal)
{
    int vpeligro,i;

    if(Abierto=false)
        vpeligro=0
        for(int i=0; i<NVehiculos;i++)
        if (Trafico[i].Cadenas==false && Trafico[i].Kilometros>=KmInicial && Trafico[i].Kilometros<=KmFinal)
        vpeligro++;
    else(Abierto=true)
        vpeligro=-1;
    return vpeligro;
}

int main()
{
    cout << "Hello world!" << endl;
    return 0;
}
