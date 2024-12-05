#include <iostream>
#include<cmath>
using namespace std;

int main()
{
    float rad;
    cout << "Vamos a calcular la longitud, la superficie y el volumen de la circunferencia, círculo y esfera." << endl;
    cout << "Introduce el valor del radio: " <<endl;
    cin >> rad;

    float longitud,superficie,volumen;
    const float pi=3.14159;

    longitud = (2*pi*rad);
    superficie = (pi*rad*rad);
    volumen = ((4*pi*rad*rad*rad)/3);

    cout << "La longitud es igual a " << longitud << endl;
    cout << "La superficie es igual a " << superficie << endl;
    cout << "El volumen es igual a " << volumen << endl;
    return 0;
}
