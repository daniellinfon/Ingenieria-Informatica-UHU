#include <iostream>
#include <cstring>
using namespace std;

typedef char cadena[150];

int main()
{
    cadena frase,frase2;
    cout << "Introduce una frase pelotuo: ";
    cin.getline(frase,150);

    int len = strlen(frase);

    int i = 0;
    for(int j=0; j<=len; j++)
        if (frase[j] != ' ')
        {
            frase2[i] = frase[j];
            i++;
        }
        else if (frase[j]==' ')
        {
            frase[j]='*';
            frase2[i] = frase[j];
            i++;
        }

    strcpy(frase, frase2);

    cout<< "La frase transformada es "<<frase<<endl;



    return 0;
}
