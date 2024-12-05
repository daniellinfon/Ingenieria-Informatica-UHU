#include <iostream>
#include <cstdlib>
#include <cstring>
using namespace std;

typedef char cadena[150];

int main ()
{
    cadena frase, frase_tmp;

    cout << "Introduce una frase por teclado: ";
    cin.getline(frase, 150);

    int len = strlen(frase);

    int i = 0;
    for (int j=len-1; j>=0; j--)
    {
        frase_tmp[i] = frase[j];
        i++;
    }
    frase_tmp[i] = '\0';
    strcpy(frase, frase_tmp);

    /* SOLUCIÓN MÁS RÁPIDA:

        int i = 0, j = len-1;
        char tmpc;

        while (i < j)
        {
           tmpc = frase[i];
           frase[i] = frase[j];
           frase[j] = tmpc;
           i++;
           j--;
        }
    */
    cout << "La frase invertida es: \n" << frase << endl;
    return 0;
}
