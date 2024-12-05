#include <iostream>

using namespace std;

int main()
{
    float x1,y1,x2,y2,dist1,dist2;
    cout << "Introduzca las coordenadas x1 e y1 del punto A(x1,y1)" <<endl;
    cout << "x1 = ";
    cin >> x1;
    cout << "y1 = ";
    cin >> y1;
    cout << "A(" << x1 << ", " << y1 << ")"<<endl;

    cout << "Introduzca las coordenadas x2 e y2 del punto B(x2,y2)" << endl;
    cout << "x2 = ";
    cin >> x2;
    cout << "y2 = ";
    cin >> y2;
    cout << "B(" << x2 << ", " << y2 << ")"<<endl;

    dist1 = x2 - x1;
    dist2 = y2 - y1;
    cout << "La distancia entre ambos puntos es de (" << dist1 << ", " << dist2 << ")";
    return 0;
}
