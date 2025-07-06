#include <iostream>
#define M 15
using namespace std;

int main()
{
    int p,pos,Vector[M],minimo;
    for(int i=0; i<M; i++)
            {
                cout<< "Introduce el valor " << i+1<<": ";
                cin>> Vector[i];
            }

    cout<<"\nIntroduce una posicion del vector: ";
    cin>>p;

    minimo=Vector[p];

    for( int i=p;i<M; i++){
            if(minimo>Vector[i]){
            minimo = Vector[i];
            pos=i+1;
            }
    }


    cout << "\nEl valor minimo almacenado es: "<<minimo<<" y su posicion es el "<<pos<<endl;
    return 0;
}
