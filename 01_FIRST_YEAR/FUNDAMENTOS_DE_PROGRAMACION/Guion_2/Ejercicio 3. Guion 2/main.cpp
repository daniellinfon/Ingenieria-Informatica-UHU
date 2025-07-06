#include <iostream>
#include <math.h>
using namespace std;

int main()
{
    int a,b,c;
    float x1, x2;
    cout << "Introduce los valores enteros de a, b y c para resolver una ecuacion de segundo grado: ";
    cin >> a >> b >> c;
    if(a!=0){
        x1 = (-b + sqrt(b*b-4*a*c))/(2*a);
        x2 = (-b - sqrt(b*b-4*a*c))/(2*a);
        cout << "Las raices son " << x1 << " y " << x2 << endl;

   }
   else
        cout << "NO tiene raiz";

    return 0;
}
