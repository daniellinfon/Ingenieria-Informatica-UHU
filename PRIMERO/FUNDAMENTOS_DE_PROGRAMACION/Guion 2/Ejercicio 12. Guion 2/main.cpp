#include <iostream>

using namespace std;

int main()
{
    int a,b,mcd;
    cout << "Introduce dos valores enteros: ";
    cin >> a>>b;

    while(a!=b)
    {
        if (a>b)
            a = a-b;
        else
            b = b-a;
    }

    mcd=a;

    cout<<endl<< "El mcd es: " <<mcd<<endl;

    return 0;
}
