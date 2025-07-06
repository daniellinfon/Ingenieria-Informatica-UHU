#include <iostream>

using namespace std;

int main()
{
    int a,b,S=0,media;
    char x;
    bool numnoencontrado=false,noengano=false;
    do
    {
        cout << "Piensa en un numero" << endl
             << "Introduce los extremos del rango [a,b] en el que ese numero este incluido"<<endl
             << "El valor de a: ";
        cin>> a;
        cout<< "El valor de b: ";
        cin>> b;

        if (a>=b)
            cout<< "El valor a debe ser menor que b!!"<<endl;
    }
    while(a>=b);
    do{

    media= (a+b)/2;

        cout<<endl<<"¿Tu numero es menor(<), mayor(>) o igual(=) a " <<media<<" ? ";
        cin>> x;

        switch(x)
        {

        case '<':
            b=media-1;
            break;
        case '>':
            a=media+1;
            break;
        case '=':
            cout<<endl<<"He ganado :)";
            numnoencontrado=true;
            break;
        default:
            cout<<"Escribe '<','>' o '='!!"<<endl;
        }

    if(a>b){
        noengano=true;
        cout<<endl<<"Me has timao guarro, pa tu casa";
    }
    }while(numnoencontrado && noengano);
    return 0;
}
