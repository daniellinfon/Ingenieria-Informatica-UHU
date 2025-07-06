#include <iostream>

using namespace std;
struct futbolista
{
    char nombre[51]; // Nombre del futbolista.
    int demarcacion; // 1 portero, 2 defensa, 3 central, 4 delantero
    float nomina; // sueldo del futbolista.
};

struct equipo
{
    char nombre[51]; // Nombre del equipo de futbol.
    float presupuesto; // Presupuesto del equipo.
    int numjugadores; // Nº de jugadores en la plantilla del equipo.
    futbolista jugadores[25]; // Datos de cada jugador de la plantilla.
};

struct liga
{
    char temporada[14]; // Temporada de la liga. Ejemplo: “Liga 13-14”
    int numequipos; // Nº de equipos participantes en la liga de esa temporada.
    equipo equipos[20]; // Clasificación final de los equipos de dicha liga, la primera posición
}; // corresponde al equipo que ha quedado primero, y la última posición al
// que ha quedado último.

int main()
{
    liga ligas[10]; // Información de cada liga jugada
    equipo equipos[20];
    futbolista jugadores[25];
    int Nligas,Nequipos,Njugadores; // Número de ligas jugadas

    cout<<"Introduce cuantas ligas se han jugado: ";
    cin>>Nligas;


    for(int i=0;i<Nligas;i++)
    {
        cout<<"Liga: "<<ligas[i].temporada<<" \nUltimo puesto: ";
        cout<<ligas[i].equipos[ligas[i].Nequipos-1].nombre<<endl;
        cout<<"Porteros: ";
        for(int j=0; j<Njugadores;j++)
        {
            if(ligas[i].equipos[Nequipos-1].jugadores[j].demarcacion==4)
            cout<<ligas[i].equipos[Nequipos-1].jugadores[j].nombre<<", ";
        }cout<<endl<<endl;
    }
return 0;
}
