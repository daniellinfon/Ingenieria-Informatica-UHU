#include <iostream>

using namespace std;

int main()
{
    float v0,v,s,s0,a,t;
    cout << "Un Formula 1 comienza su vuelta de clasificación con una velocidad inicial de 69 m/s, ¿Qué velocidad final tendrá y cuantos metros habrá recorrido si el piloto acelera ";
    cout << "a 3.6 durante 10 segundos?" <<endl<<endl;
    v0 = 69;
    a = 3.6;
    t = 10;
    s0 = 0;

    v= v0+(a*t);
    s= s0 +(v0*t) + ((a*t*t)/2);

    cout << "Su velocidad final es de " <<v<< " m/s y habra recorrido " <<s<< " metros ";
    return 0;
}
