#include <iostream>
#include <cstring>
using namespace std;

struct Toma
{
    int dia, mes; //Fecha de captación
    float Temperatura, Presion; //Datos captados
    bool Correcta; //Datos captados correctamente.
};

typedef char Cadena[50];
class Satelite
{
    Toma Datos[10000]; //Tomas captadas por el satélite
    int NTomas; //Nº de tomas realizadas.
    Cadena Nombre; //Nombre del satélite
    bool Activo; //Si es true, el satélite está activo.
public:
    Satelite();
    bool Activar(Cadena pNombre, Cadena pPassword);
    bool Esta_Activo();
    int Dame_NTomas();
    Toma Dame_Toma(int pPos);
    void Actualiza_Toma(int pPos, Toma pDato);
    void Dame_Nombre(Cadena pNombre);
    float TemperaturaMedia(int pMes);
    int AnularDatos(int pMes);
};

Satelite::Satelite()
{
    NTomas=0;
    Activo=false;
}

bool Satelite::Esta_Activo()
{
    return Activo;
}
bool Satelite::Activar(Cadena pNombre, Cadena pPassword)
{
    if (strcmp(pPassword,"123qF349.")==0)
    {
        Activo=true;
        strcpy(Nombre,pNombre);
    }
    return Activo;
}

int Satelite::Dame_NTomas()
{
    return NTomas;
}

void Satelite::Dame_Nombre(Cadena pNombre)
{
    strcpy(pNombre,Nombre);
}

float Satelite::TemperaturaMedia(int pMes)
{
    float Media=0;
    int Ndatos=0;
    if (Activo)
    {
        for (int i=0; i<NTomas; i++)
            if (Datos[i].Correcta && Datos[i].mes==pMes)
            {
                Media+=Datos[i].Temperatura;
                Ndatos++;
            }
        if (Ndatos>0)
            Media/=Ndatos;
    }
    return Media;
}

int Satelite::AnularDatos(int pMes)
{
    int NAnulaciones;
    if (Activo)
    {
        NAnulaciones=0;
        for (int i=0; i<NTomas; i++)
            if (Datos[i].Correcta && Datos[i].mes==pMes && (Datos[i].Presion>40 || Datos[i].Temperatura<-70))
            {
                Datos[i].Correcta=false;
                NAnulaciones++;
            }
    }
    else NAnulaciones=-1;
    return NAnulaciones;
}

int Buscar(Satelite pLista[50], int pNSatelites, Cadena pNombre)
{
    bool Encontrado=false;
    int pos,i=0;
    Cadena Nom;
    while (i<pNSatelites && !Encontrado)
    {
        if (pLista[i].Esta_Activo())
        {
            pLista[i].Dame_Nombre(Nom);
            if (strcmp(Nom, pNombre)==0 && pLista[i].Dame_NTomas()>1000)
            {
                Encontrado=true;
                pos=i;
            }
        }
        i++;
    }
    if (!Encontrado) pos=-1;
    return pos;
}


int main()
{
    cout << "Hace frio carlita " << endl;
    return 0;
}
