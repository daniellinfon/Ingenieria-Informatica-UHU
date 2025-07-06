#include <iostream>

using namespace std;

int main()
{
    float x1,x2,y1,y2,sum;
    cout << "Inroduzca un valor para el numerador de la primera fraccion: ";
    cin >> x1;
    cout << "Inroduzca un valor para el denominador de la primera fraccion: ";
    cin >> y1;
    cout << "Inroduzca un valor para el numerador de la segunda fraccion: ";
    cin >> x2;
    cout << "Inroduzca un valor para el denominador de la segunda fraccion: ";
    cin>> y2;

    sum = ((x1*y2)+(y1*x2))/(y1*y2);
    cout << "El resutaldo de sumar ambas es " <<sum<< endl;
    return 0;
}
