#include <iostream>

using namespace std;

int main()
{
    float a,b,c,d,e,med;
    cout << "Introduce 5 notas de examenes: ";
    cin>> a>>b>>c>>d>>e;
    med= (a+b+c+d+e)/5;
    cout << "La media es igual a " << med << endl;
    return 0;
}
