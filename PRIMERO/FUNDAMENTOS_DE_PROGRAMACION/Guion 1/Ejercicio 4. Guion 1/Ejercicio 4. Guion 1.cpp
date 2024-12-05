#include <iostream>

using namespace std;

int main()
{
    int a,b,c;
    cout << "Introduzca un valor para a: " << endl;
    cin >> a;
    cout << "Introduzca un valor para b: " << endl;
    cin >> b;
    c=a;
    a=b;
    b=c;

    cout << "Ahora el valor de a es " << a << " y el valor de b es " << c << endl;
    return 0;

}
