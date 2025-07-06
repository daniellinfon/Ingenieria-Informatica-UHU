#include <iostream>

using namespace std;

int main()
{
    float x,y;
    cout << "Introduce una temperatura medida en la escala Celsius: ";
    cin >> x;

    y= ((9*x)/5)+32;

    cout << x << " grados Celsius son " << y << " grados Fahrenheit" << endl;
    return 0;
}
