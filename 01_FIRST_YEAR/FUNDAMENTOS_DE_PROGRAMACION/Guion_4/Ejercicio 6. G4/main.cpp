#include <iostream>
#include <cstring>
using namespace std;

typedef char cadena[150];
void rellenar(cadena D)
{
    cout << "Introduce una frase pelotuo: ";
    cin.getline(D,150);
}

void conversion(cadena D)
{
    int len = strlen(D);
    cadena frase2;
    int i = 0;
    for(int j=0; j<=len; j++)
        if (D[j] != ' ')
        {
            frase2[i] = D[j];
            i++;
        }
        else if (D[j]==' ')
        {
            D[j]='*';
            frase2[i] = D[j];
            i++;
        }

    strcpy(D, frase2);
}

int main()
{
    cadena frase;
    rellenar(frase);
    conversion(frase);
    cout<< "La frase transformada es "<<frase<<endl;


    return 0;
}
