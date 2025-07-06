#include <iostream>
#include <cstring>

using namespace std;
#define M 10

void rellenar (int T[10])
{
for(int i=0; i<M; i++)
    {
        cout<<"Introduce un valor para el elemento " <<i+1<< ": ";
        cin >> T[i];
    }
}

bool buscar(int T[10], int x, int i)
{
bool encontrado = false;
i=0;
    while ( i<M && !encontrado)
    {
        if (x==T[i])
            encontrado = true;
        else i++;
    }

    return encontrado;
}

int main()
{

    int S, z;
    int tab[10];
    bool esta;

    rellenar(tab);

    cout << "\nIntroduzca el numero a buscar: ";
    cin >> S;

    esta = buscar(tab, S, z);

if (esta)
 cout <<"El valor esta en el vector";
else
 cout << "El valor NO está en el vector";


    return 0;
}
