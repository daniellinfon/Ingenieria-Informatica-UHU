#include <iostream>

using namespace std;

int main()
{
    int a;
    cout << "Introduce un a�o: " << endl;
    cin >> a;

    if (a%4 != 0)
        cout << "El a�o no es bisiesto";
    else if (a%100 != 0)
        cout << "El a�o es bisiesto";
    else if (a%400 != 0)
        cout << "El a�o no es bisiesto";
    else
        cout << "El a�o es bisiesto";
    return 0;
}
