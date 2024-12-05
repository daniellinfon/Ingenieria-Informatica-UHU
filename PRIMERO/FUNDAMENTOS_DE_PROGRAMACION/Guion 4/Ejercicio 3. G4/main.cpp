#include <iostream>
#include <cstdlib>
#include <ctime>

using namespace std;

#define F 10
#define C 15

void rellenar (int T[10][15])
{
    int f,c;
    srand(time(0));
     for(f=0; f<F; f++)
        for(c=0; c<C; c++)
            T[f][c] = rand() % 101;

}

bool buscar (int T[10][15], int x)
{
    int f,c;
    bool encontrado = false;
    f=0;
    while(f<F && !encontrado)
    {
        c=0;
        while(c < C && !encontrado)

            if(T[f][c]== x)
                encontrado = true;
            else
                c++;

        if(!encontrado)
            f++;

    }

    return encontrado;
}

int main()
{
    int S;
    int tab[F][C];
    bool esta;

    rellenar(tab);

    cout << "\nIntroduzca el numero a buscar: ";
    cin >> S;

    esta = buscar(tab, S);

if (esta)
    cout << "Esta en la tabla";
else
    cout<< "No esta ";
    return 0;
}
