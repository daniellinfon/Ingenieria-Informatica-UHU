#include <iostream>

using namespace std;

int main()
{
    int a,b,c,d,sum1,sum2,rest1,rest2,mult1,mult2,div1,div2,den;
    cout << "Introduce los valores del primer complejo z1 (a,b)"<<endl;
    cout << "Introduce a: ";
    cin>> a;
    cout << "Introduce b: ";
    cin>> b;
    cout << "Introduce los valores del segundo complejo z2 (c,d)"<<endl;
    cout << "Introduce c: ";
    cin>> c;
    cout << "Introduce d: ";
    cin >> d;

    sum1 = a+c;
    sum2 = b+d;
    cout<< "La suma (a,b)+(c,d)= (" <<sum1<< "," <<sum2<< ")" << endl;

    rest1 = a-c;
    rest2 = b-d;
    cout<< "La resta (a,b)-(c,d)= (" <<rest1<< "," <<rest2<< ")" << endl;

    mult1= (a*c)-(b*d);
    mult2= (a*d)-(b*c);
    cout<< "La multiplicacion(a,b)*(c,d)= (" <<mult1<< "," <<mult2<< ")" << endl;

    div1= (a*c)+(b*d);
    div2= (b*c)-(a*d);
    den= (c*c)+(d*d);
    cout<< "La division(a,b)/(c,d)= (" <<div1<< "/"<<den<<", " <<div2<< "/" <<den<< ")" << endl;
    return 0;
}
