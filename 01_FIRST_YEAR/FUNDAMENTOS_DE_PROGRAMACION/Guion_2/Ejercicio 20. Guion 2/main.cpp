#include <iostream>

using namespace std;

int main()
{
    int x;
    float q,Q;
    cout << "Vamos a calcular el modulo de Young"<<endl;

    cout<< "Introduce el valor de la carga: ";
    cin>>q;

    cout<<endl<<"En que unidades ha introducido el valor? "
              <<"\n1.Newtons"
              <<"\n2.KiloNewtons"
              <<"\nElije una opcion: ";
    cin>>x;
    switch(x)
{
    case 1: Q==q;
    case 2: Q=q*1000;
}
    cout<< "Introduce el valor de la seccion: ";
    cin>>sec;

    cout<<endl<<"En que unidades ha introducido el valor? "
              <<"\n1.Metros"
              <<"\n2.Milimetros"
              <<"\nElije una opcion: ";
    cin>>y;
    switch(y)
{
    case 1:Sec=sec*1000;
    case 2:Sec==sec;
}
    cout<< "Introduce el valor de la longitud inicial: ";
    cin>>l;

    cout<<endl<<"En que unidades ha introducido el valor? "
              <<"\n1.Metros"
              <<"\n2.Milimetros"
              <<"\nElije una opcion: ";
    cin>>z;
    switch(z)
{
    case 1:L=l*1000;
    case 2:L==l;
}
    cout<< "Introduce el valor del incremento de la longitud: ";
    cin>>al;

    cout<<endl<<"En que unidades ha introducido el valor? "
              <<"\n1.Metros"
              <<"\n2.Milimetros"
              <<"\nElije una opcion: ";
    cin>>p;
    switch(p)
{
    case 1: Al=al*1000;
    case 2: Al==al;
}
    e=AL/L;



    return 0;
}
