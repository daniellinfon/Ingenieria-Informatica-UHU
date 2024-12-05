#include<iostream>
#include <cstring>
using namespace std;
typedef char cadena[250];

void invertir (cadena f, int ini, int fin)
{
    int hasta;
    char aux;

    hasta = (ini+fin)/2;
    for (int i=ini; i<hasta; i++)
    {
        aux = f[i];
        f[i] = f[fin];
        f[fin] = aux;
        fin--;
    }
}

int main()
{
    cadena frase;
    int inicio, fin, nletras;

    cout << "Escriba una frase: ";
    cin.getline(frase,250);
    nletras = strlen(frase);
    do
    {
        cout << "Pos. ini. y fin para invertir.\n";
        cout << "Total caracteres ("
					<< nletras << "): ";
        cin >> inicio >> fin;
        if (inicio>=fin || inicio<1 || fin>nletras)
            cout << "\nLímites incorrectos." << endl;
    }
    while (inicio>=fin || inicio<1 || fin>nletras);

    invertir(frase, inicio-1, fin-1);
    cout << endl << frase << endl;
    return 0;
}
