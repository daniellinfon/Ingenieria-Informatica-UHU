#include <iostream>
#include <cstring>

using namespace std;
#define NFIL 3
#define NCOL 4

typedef char cadena[30];

int main ()
{
  	cadena tabla[NFIL][NCOL], S;
 	int f, c;

 	//Esquema de recorrido de tabla
 	for(f = 0; f < NFIL; f++)
        for(c = 0; c < NCOL; c++)
        {
            cout << "Introduce la palabra [" << f+1 << "," << c+1 << "]: ";
            cin >> tabla[f][c];
        }

 	cout << "Introduzca la palabra a buscar: ";
	cin >> S;

	//Esquema de b�squeda de tabla[f][c] == S NO ES V�LIDO, hay que utilizar la funci�n strcmp
	bool encontrado = false;
	f = 0;
	while (f < NFIL && !encontrado)
  	{
        c=0;
        while (c < NCOL && !encontrado)
            if (strcmp(tabla[f][c], S) == 0)
                encontrado = true;
            else
                c++;
        if (!encontrado)
            f++;
  	}

	cout << "La palabra " << S;
	if (!encontrado)
		cout <<" no ";
	cout << " est� en la tabla." << endl;
 	return 0;
}
