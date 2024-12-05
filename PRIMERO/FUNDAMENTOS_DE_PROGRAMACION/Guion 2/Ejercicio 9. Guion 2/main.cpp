#include <iostream>
using namespace std;

int main()
{
    long ini, fin;

    cout << "Introduce el valor inicial y final del intervalo: ";
    cin >> ini >> fin;

    if (ini>fin)
    {
        long temp=ini;
        ini=fin;
        fin=temp;
    }

    cout << "El número primos comprendido en el intervalo ["
         << ini <<", " << fin << "] son:"<<endl;

    for (long numero=ini; numero<=fin; numero++)
    {
        bool esprimo=true;
        long divisor=2;

        while ( divisor < numero/2+1 && esprimo)
        {
            if (numero % divisor == 0)
                esprimo = false;
            divisor++;
        }

        if (esprimo)
            cout << numero << " ";
    }
    cout << endl;

    return 0;
}
