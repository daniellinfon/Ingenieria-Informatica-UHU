#include <iostream>

using namespace std;

int main()
{
    float x,y,area,perim;
    cout << "Vamos a calcular el area y el perimetro de un rectangulo. Introduzca sus medidas de ancho y largo en cm: ";
    cin >>  x>>y;
    area=x*y;
    perim= 2*x+2*y;
    cout << "El area es igual a " <<area<< " cm y el perimetro " << perim << " cm.";
    return 0;
}
