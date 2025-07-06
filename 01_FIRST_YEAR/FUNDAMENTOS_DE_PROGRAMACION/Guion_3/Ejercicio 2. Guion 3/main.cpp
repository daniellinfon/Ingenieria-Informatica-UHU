#include <iostream>

using namespace std;
#define M 10
int main()
{

    int S;
    int tab[10];
    int maximo;
    for(int i=0; i<M; i++)
    {
        cout<<"Introduce un valor para el elemento " <<i+1<< ": ";
        cin >> tab[i];
    }

    cout << "\nIntroduzca el numero a buscar: ";
    cin >> S;


    //esquema de busqueda

    int i= 0;
    bool encontrado = false;

    while ( i<M && !encontrado)
        if (S == tab[i]) encontrado = true;
        else i++;

if (encontrado)
 cout <<"El valor esta en el vector";
else
 cout << "El valor NO está en el vector";


    return 0;
}
