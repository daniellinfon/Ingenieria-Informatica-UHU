#include <iostream>

using namespace std;

int main()
{
    float dist,dist1,total;
    int dias,edad;
    cout << "Vamos a calcular los descuentos que aplica una agencia de viajes en sus billetes de avión"<<endl;
    cout << "Introduce la distancia del viaje a realizar (en km): ";
    cin >> dist;
    if (dist >0){
    cout<< "Número de días que dura el viaje: ";
    cin >> dias;

        if(dias>0){
        cout << "Edad del turista: ";
        cin >> edad;
            if(edad>0){
        dist1= dist*0.75;
        cout << "El precio del viaje es: " <<dist1<< " euros"<<endl;

                if (dias>7 && dist>800 || edad>55){
                total= dist1*0.25;
                cout<< "Usted cumple con los requisitos y tiene derecho a un 25% de descuento"<<endl;
                cout<< "El precio final del viaje es "<<total<<" euros";
                }
                else
                cout << "Lo sentimos pero usted no cumple los requisitos para optar al descuento :("<<endl;
            }
            else
                cout<<"Error"<<endl;
        }
        else
            cout<<"Error"<<endl;
    }
    else
    cout<<"Error"<<endl;


    return 0;
}
