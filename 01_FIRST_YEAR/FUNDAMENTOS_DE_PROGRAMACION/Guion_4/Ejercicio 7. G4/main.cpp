#include <iostream>
#include <cstring>

using namespace std;
typedef char cadena[250];

void compactar (char f[250], char c)
{
    cadena f2;
    int len= strlen(f);
    c=' ';
    int i = 0;
    for(int j=0; j<=len; j++)
        if (f[j] != c)
    {
        f2[i] = f[j];
        i++;
    }
 strcpy(f,f2);
}
int main()
{
    cadena frase;
    char a=' ';
    cout << "Introduce una frase: ";
    cin.getline(frase,250);

    compactar(frase,a);

    cout<<"La frase compactada es "<< frase;
    return 0;
}
