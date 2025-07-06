#include <iostream>
#include <cstdlib>
#include <time.h>
using namespace std;

int main()
{
    int num,numero,intentos=10;
    bool encontrado=false;
    srand(time(0));//Inicializar los números aleatorios
    numero=100+rand()%(999-100);// Número aleatorio entre 100 y 99
    do{
            do{

    cout << "Intenta adivinar un numero de 3 cifras. Introduce un numero: ";
    cin>> num;
    cout<<endl<<"Le quedan "<<intentos<<" intentos"<<endl;
    intentos--;
    if(num<100 || num>999){
        cout<<"Solo numeros de 3 cifras!!"<<endl<<endl;
        }
    }while(num<100 || num>999);


        if(num<numero){
        cout<<"El numero introducido es menor que el numero buscado"<<endl;
        }
        else if(num>numero){
                cout<<"El numero introducido es mayor que el numero buscado"<<endl;
                }
        else if(num==numero)
            encontrado=true;



    }while(!encontrado && intentos>=0);
    if(encontrado)
        cout<<"Ha acertado, el numero buscado era "<<numero<<endl;
    else
        cout<<"Se han agotado los intentos, el numero era el "<<numero<<endl;
    return 0;
}
