#include<iostream>
#include <cstring>
using namespace std;
#define M 3
#define N 4
typedef char cadena[30];


void rellenar(cadena D[M][N])
{
    for(int i = 0; i < M; i++)
        for(int j = 0; j < N; j++)
        {
            cout << "\nIntroduce la palabra " << i+1 <<" " <<j+1 <<"\n";
            cin >> D[i][j];
        }
}

bool buscar(cadena D[M][N], cadena S, int &f, int &c)
{
    //Esquema de búsqueda
    bool encontrado=false;
    f = 0;
    while ( f < M && !encontrado )
    {
        c = 0;
        while (   c < N && !encontrado)
        {
            if (strcmp(D[f][c], S) == 0)
                encontrado = true;
            else
                c++;
        }
        if (!encontrado)
            f++;
    }
    return encontrado;
}

int main()
{
    cadena dic[M][N], palabra;
    bool esta;
    int i, j;

    rellenar(dic);

    cout << "Introduzca la palabra a buscar\n";
    cin >> palabra;

    esta = buscar(dic, palabra, i, j);

    if (esta)
        cout << "Se encontro en la posicion " << i+1 << " , " << j+1;
    else
        cout << "No se encontro";

    return 0;
}
