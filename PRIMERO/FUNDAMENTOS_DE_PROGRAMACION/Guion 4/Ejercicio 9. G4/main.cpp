#include <iostream>
#include <cstdlib>
#include <cstring>
using namespace std;
typedef char cadena[150];


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

 void invertir (cadena f, int ini, int fin)
{
    int hasta,nletras;
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

bool palindroma (char f[250],cadena f2)
{
    bool palin=false;
    if(strcmp(f,f2)==0)
        palin=true;

    return palin;
}

int main()
{
    char x=' ';
    int inicio,fin,nletras,len,i;
    bool espalindromo;
    cadena frase, frase2;
    cout << "Introduce una frase por teclado: ";
    cin.getline(frase, 150);

    i=0;
    len=strlen(frase);
    for(int j=0; j<=len; j++)
        if (frase[j] != ' ')
    {
        frase2[i] = frase[j];
        i++;
    }


    compactar(frase,x);
    cout<<endl;
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

    invertir(frase,inicio-1, fin-1);

    espalindromo=palindroma(frase,frase2);

    if(espalindromo)
        cout<<"\nLa frase es un palindromo."<<endl;
    if(!espalindromo)
        cout<<"\nLa frase no es un palindromo."<<endl;











    return 0;
}
