#include <iostream>

using namespace std;

int main()
{
    int n,num,suma,maximo,minimo;
    float media;

   do
   { cout << "Cuantos numeros va a poner?";
   cin>>n;

   if(n<=0)
    cout << "Cantidad erronea";

   } while (n<=0);

    cout << "Indique el numero 1: ";
    cin >> num;

    maximo=minimo=suma=num;

    for(int = 2; i<=n; i++)
    {
        cout << "Indique el numero " <<i<< ": ";
        cin >> num;
        suma += num;
        if (num>maximo)
            maximo=num;
        else if (num<minimo)
            minimo=num;
    }
    media = suma/(float)n;

    cout<< "La media es: " <<media<<endl;
    cout<< "El mayor es: " <<maximo<<endl;
    cout<< "El menor es: " <<minimo<<endl;
    return 0;
}
