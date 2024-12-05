#include <iostream>
#include <cmath>
using namespace std;

int main()
{
    int a,b,c;
    float radicando,raizcuadrada, x1, x2;
    cout << "Dada una ecuacion de segundo grado ax2 + bx + c";
    cout << "Introduce los valores enteros de a, b y c";
    cin >> a >> b >> c;
    if(a==0){
        cout << "El coeficiente no puede ser 0";

   }
   else
    { radicando = (b*b)- (4*a*c);
     if (radicando < 0)
        cout << "No tiene raices reales";
   else if (radicando == 0)
   { x1= -(float)b/(2*a);
   cout << "La unica raiz es " << x1;
   }
   else
    { raizcuadrada = sqrt(radicando);
        x1 = (-b + raizcuadrada)/(2*a);
        x2 = (-b - raizcuadrada)/(2*a);
        cout << "La primera raiz es " << x1 << endl;
        cout << "La segunda raiz es " << x2;
    }
    }
    return 0;
}
