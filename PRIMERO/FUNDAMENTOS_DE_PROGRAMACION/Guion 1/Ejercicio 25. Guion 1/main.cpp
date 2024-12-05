#include <iostream>

using namespace std;

int main()
{
    float m,v,h,v1,Ep,Ec,Em,g,h1;
    cout << "Vamos a  la energía cinética, la energía potencial y energía mecánica del cuerpo"<<endl;
    cout<< "Introduce el valor de la masa (kg): ";
    cin >>m;
    cout << "Introduce el valor de la velocidad (km/h): ";
    cin >> v;
    cout << "Introduce el valor de la altura (km): ";
    cin>> h;

    v1= (v*1000)/3600;
    h1= h*1000;
    Ec= (m*v1*v1)/2;
    g= 9.8;
    Ep= m*g*h1;
    Em = Ec+Ep;

    cout <<"La Ec es igual a " <<Ec<< "J"<<endl;
    cout<< "La Ep es igual a "<<Ep<< "J"<<endl;
    cout<<"La Em es igual a "<<Em<< "J"<<endl;

    return 0;
}
