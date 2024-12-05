#include <iostream>
#include <cstdlib>
#include <ctime>
using namespace std;

#define NFIL 10
#define NCOL 15

int main()
{
    int tabla[NFIL][NCOL];
    int X,f,c;

    srand(time(0));// inicializar semilla generador de numeros aleatorios

//esquema recorrido de tabla
    for(f=0; f<NFIL; f++)
        for(c=0; c<NCOL; c++)
            tabla[f][c] = rand() % 101;

    cout << "Introduce el numero a buscar: ";
    cin >> X;

//Esquema de busqueda de tabla[f][c] == X
    bool encontrado = false;

    f=0;
    while(f<NCOL && !encontrado)
    {
        c=0;
        while(f < NFIL && !encontrado)
            if(tabla[f][c]== X)
                encontrado = true;
            else
                c++;

        if(!encontrado)
            f++;

    }
    if (!encontrado)
        cout<<" No ";
    cout<<"esta en la tabla."<<endl;
    return 0;
}
