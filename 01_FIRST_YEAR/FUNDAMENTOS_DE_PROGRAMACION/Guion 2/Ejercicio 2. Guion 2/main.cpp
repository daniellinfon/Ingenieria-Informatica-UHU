#include <iostream>

using namespace std;

int main()
{
    int a,b;
    cout << "Introduce 2 numeros enteros: ";
    cin >> a>>b;

    if(a>b)
        cout<< "El numero " <<a<< " es mayor que "<<b<<endl;
        else if(a==b)
            cout<< "Ambos son iguales"<<endl;
    else
        cout<< "El numero "<<b<< " es mayor que "<<a<<endl;
    return 0;
}
