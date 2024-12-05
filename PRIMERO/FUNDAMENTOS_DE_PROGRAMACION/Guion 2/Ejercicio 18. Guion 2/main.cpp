#include <iostream>

using namespace std;
int main ()
{
    int num,S=0,n,b,p,numero;
    bool encontrado;
    do{
        cout << "Introduce un numero entero: ";
        cin >> num;
        if (num<0)
            cout<<"Error, vuelve a intentarlo"<<endl;
    }while (num<0);

    n=((num%90)*123);
    for(int div=1; div <= n; div++){
        if (n%div==0)
            S=S+div;
    }

    if (S<=n*2){
        b=((num%90)*123)%5397;
        b=n;

    }
    cout<<endl<<"Introduce el numero de puntos con los que quieres comenzar: ";
    cin>>p;

        do{

    cout <<"Intenta adivinar el numero secreto. Introduce un numero: ";
    cin>> numero;

        if(numero<n){
        cout<<"El numero introducido es menor que el numero buscado"<<endl;
        p--;
        cout<<"Le quedan "<<p<<" puntos"<<endl;

        }
        else if(numero>n){
                cout<<"El numero introducido es mayor que el numero buscado"<<endl;
                p=p-2;
                cout<<"Le quedan "<<p<<" puntos"<<endl;

                }
       else if(numero==n)
            encontrado=true;

    }while(!encontrado && p>0);

    if(encontrado)
        cout<<"Enhorabuena!! Ha acertado con "<<p<< " puntos restantes. El numero secreto era "<<n<<endl;
    else
        cout<<"Lo sentimos, se han agotado los puntos. El numero secreto era el "<<n<<endl;

    return 0;
}
