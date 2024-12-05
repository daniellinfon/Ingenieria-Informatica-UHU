#include <iostream>
#include <cstdlib>
#include <cstring>
using namespace std;

typedef char cadena[150];

int main()
{   cadena frase, frase_tmp;

    cout << "Introduce una frase: ";
    cin.getline(frase, 150);

    int len= strlen(frase);

    int i = 0;
    for(int j=0; j<=len; j++)
        if (frase[j] != ' ')
    {
        frase_tmp[i] = frase[j];
        i++;
    }


    cout<< "La frase compactada es "<<frase_tmp<<endl;
    return 0;
}
