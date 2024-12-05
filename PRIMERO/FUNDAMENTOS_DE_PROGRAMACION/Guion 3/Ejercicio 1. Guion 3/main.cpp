#include <iostream>

using namespace std;
#define M 10
int main()
{
    int tab[M];
    int minimo,maximo;

    for(int i=0; i<M; i++){
        cout<<"Introduce un valor para el elemento " <<i+1<< ": ";
        cin >> tab[i];
    }

    maximo=minimo=tab[0];

    for(int i= 1; i<M; i++){
        if(tab[i]<minimo)
            minimo = tab[i];
        else if (tab[i] > maximo)
            maximo = tab[i];

    }
    cout << "El valor maximo almacenado es: "<<maximo << endl;
    cout << "El valor minimo almacenado es: "<<minimo << endl;
    return 0;
}
