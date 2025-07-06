#include <iostream>
#include <cstring>
#include <cstdlib>
#define F 2
#define C 4
using namespace std;
typedef char cadena[30];
struct persona
{
    int dni;
    cadena nombre;
};

void rellenar (persona T[2][4])
{
    cadena nombre;

    cout<<"Rellena la tabla con los datos de las personas: "<<endl<<endl;

    for ( int f=0; f< F; f++)
        for (int c=0; c<C; c++)
        {

            cout << "Introduce el NOMBRE de la persona de la fila "<<f+1 << " y columna " <<c+1<< " : ";
            cin>>T[f][c].nombre;
            cout << "Introduce el DNI de la persona de la fila "<<f+1 << " y columna " <<c+1<< " : ";
            cin >> T[f][c].dni;
            cout<<endl;
        }
}

bool buscar (persona T[2][4], long dni, int &f, int &c, cadena nombre2)
{
    bool encontrado=false;

    f=0;
     while (f<F && !encontrado)
    {
       c=0;
        while (c<C && !encontrado){
            if (dni==T[f][c].dni)
            {
                encontrado = true;
                strcpy(nombre2, T[f][c].nombre);
            }
            else
                c++;
    }
    f++;
    }
    return encontrado;
}


int main()
{
    persona tab[F][C];
    bool esta;
    int i,j,dni, dni_buscado;
    cadena nombre;

    rellenar(tab);

    cout << "Introduzca el dni a buscar ";
    cin >> dni_buscado;

    esta=buscar (tab, dni_buscado, i, j, nombre);

     if (esta)
        cout << "La persona con DNI "<< dni_buscado<<", se llama "<< nombre<<endl;
     else
        cout << "No se encontro";

    return 0;
}
