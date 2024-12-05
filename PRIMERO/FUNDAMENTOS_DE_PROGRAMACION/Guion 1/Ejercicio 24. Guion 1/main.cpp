#include <iostream>
#include<cmath>
using namespace std;

int main()
{
    float q,r,q1C,E,K;
    cout << "Introduzca el valor de la carga q (en  µC): ";
    cin >> q;
    cout <<"Introduce el valor de la distancia entre las cargas (en metros): ";
    cin >> r;
    q1C = q*(pow(10,-6));

    K= 8.85;
    #define pi 3.14159

    E= (q1C*(pow(10,12)))/(4*pi*K*r*r);

    cout << "La intensidad del campo eléctrico creado por dicha carga puntual a la distancia r es igual a " <<E<< " N/C"<<endl;
    return 0;
}
