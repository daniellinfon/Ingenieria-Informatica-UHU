#include <iostream>
#include<cmath>
using namespace std;

int main()
{
    float q1,q2,r,k,q2C,q1C,F;
    cout << "Introduzca el valor de la carga q1 (en  µC): ";
    cin >> q1;
    cout << "Introduzca el valor de la carga q2 (en  µC): ";
    cin >> q2;
    cout <<"Introduce el valor de la distancia entre las cargas (en metros): ";
    cin >> r;
    q1C = q1*(pow(10,-6));
    q2C = q2*(pow(10,-6));

    k= 9*(pow(10,9));
    F = (k*q1C*q2C)/(r*r);

    cout<< "La fuerza de atraccion entre las cargas es " <<F<< " N";

    return 0;
}
