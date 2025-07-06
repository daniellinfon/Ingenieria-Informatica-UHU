#include <iostream>
#include<cmath>
using namespace std;

int main()
{
    char Op;
    float q1,q2,r,k,q2C,q1C,F;
    do{
    cout << "Introduzca el valor de la carga q1 (en  µC): ";
    cin >> q1;
    cout << "Introduzca el valor de la carga q2 (en  µC): ";
    cin >> q2;
    cout <<"Introduce el valor de la distancia entre las cargas (en metros): ";
    cin >> r;
    cout<<endl;
    q1C = q1*(pow(10,-6));
    q2C = q2*(pow(10,-6));

    k= 9*(pow(10,9));
    F = (k*q1C*q2C)/(r*r);

    cout<< "La fuerza de atraccion entre las cargas es " <<F<< " N"<<endl;
    if (F<0){
        cout<<"La fuerza es de atraccion."<<endl;
    } else if(F>0){
        cout<<"La fuerza es de repulsion."<<endl;
    }

        do{
    cout<< "Quiere volver a realizarlo con datos diferentes? introduzca 'N' para no realizarlo o 'S' para realizarlo: ";
    cin >>Op;
    cout<<endl;
    if(Op=='S'||Op=='N'){


    switch (Op){
    case 'N':  cout<<"Gracias por utilizar este programa :)"<<endl;
    break;
    case 'S':;
        }
        }else
        cout<<"Introduce 'S' o 'N'!!"<<endl;

        }while(Op!='S'&& Op!='N');

    }while(Op=='S');
    return 0;
}
