#include <iostream>

using namespace std;

int main()
{
    float x,y,norm,extra,total;
    cout << "Para calcular el sueldo de un empleado, introduzca el numero de horas normales trabajadas: ";
    cin >> x;
    cout << "Si trabaja horas extras, indique cuantas: ";
    cin >> y;

    norm = x*5;
    extra= y*8;
    total = (norm+extra)*0.85;

    cout << "Su sueldo seria de " << total<< " euros ";
    return 0;

}
