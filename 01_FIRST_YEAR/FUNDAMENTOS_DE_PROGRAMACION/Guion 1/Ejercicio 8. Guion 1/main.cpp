#include <iostream>

using namespace std;

int main()
{
    float x,y;
    cout << "Introduzca 2 numeros: " << endl;
    cin >> x >> y;
    float sum,rest,producto,cociente;
    sum = x + y;
    rest = x - y;
    producto = x*y;
    cociente = x/y;
    cout << "La suma es igual a " << sum << endl;
    cout << "La diferencia es igual a " << rest << endl;
    cout << "El producto es igual a " << producto << endl;
    cout << "El cociente es igual a " << cociente << endl;
    return 0;
}
