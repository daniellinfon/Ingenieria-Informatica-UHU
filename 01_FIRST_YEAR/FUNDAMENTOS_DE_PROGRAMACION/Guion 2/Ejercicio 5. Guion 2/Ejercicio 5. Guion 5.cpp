#include <iostream>

using namespace std;

int main()
{
    int a;
    cout << "Introduce un año: " << endl;
    cin >> a;

    if (a%4 != 0)
        cout << "El año no es bisiesto";
    else if (a%100 != 0)
        cout << "El año es bisiesto";
    else if (a%400 != 0)
        cout << "El año no es bisiesto";
    else
        cout << "El año es bisiesto";
    return 0;
}
