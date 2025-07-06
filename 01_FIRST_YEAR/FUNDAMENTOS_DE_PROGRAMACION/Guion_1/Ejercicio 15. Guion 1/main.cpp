#include <iostream>

using namespace std;

int main()
{
    float a,b,c,d,e,f,x,y;
    cout << "Introduce los valores de a, b, c, d, e, y f " << endl;
    cin >> a>>b>>c>>d>>e>>f;

    x = (c*e+b*f)/(a*e-b*d);
    y = (a*f-c*d)/(a*e/b*d);

    cout << "El valor de x es igual a " <<x<< " y el valor de y es " <<y;
    return 0;
}
