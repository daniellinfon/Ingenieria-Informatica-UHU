#include <iostream>
#include <cstdlib>
#include <cstring>
using namespace std;

typedef char cadena[150];

int main ()
{
    cadena frase, frase_tmp;
    bool palindromo=false;
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

    if(strcmp(frase,frase_tmp)==0)
        palindromo=true;

    if(palindromo)
    cout << "\nLa frase es un palindromo";
    if(!palindromo)
    cout<<"\nLa no es un palindromo";

    return 0;
}
