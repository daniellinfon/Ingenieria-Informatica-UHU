#include <iostream>
#include <cstdlib>
using namespace std;

int main()
{
    int num, a=0, b=1, c, z, x, pos;
    float media=1;
    bool aparece=false;
    do
    {
        system("cls");
        cout<<"Indique la cantidad de terminos de la sucesion de Fibonacci que desea ver: ";
        cin>>num;
        if(num<1)
        {
            cout<<"Opcion no valida. ";
            system("pause");
        }
    }while(num<1);
    cout<<"\nIndique el valor que desea buscar en la sucesion: ";
    cin>>x;
    if(num==1)
    {
            cout<<endl<<"0"<<endl<<"La media es igual a 0";
            if(x==0)
                cout<<endl<<"El valor aparece en la posicion 1";
            else
               cout<<endl<<"El  n  introducido  no  pertenece  a  la  sucesion";
    }
    if(num==2)
    {
            cout<<endl<<"0"<<"\t1"<<endl<<"La media es igual a 0";
            if(x==1)
                cout<<endl<<"El valor aparece en la posicion 2";
            else
               cout<<endl<<"El  n  introducido  no  pertenece  a  la  sucesion";
    }
    if(num>2)
    {
        cout<<endl<<"0"<<"\t1";
        for(int i=3; i<=num; i++)
        {
            c =  a + b;
            media += c;
            cout<<"\t"<<c;
            if(c==x){
                aparece=true;
                pos=i;
            }
            a=b;
            b=c;
        }
        media=media/(float)num;
        cout<<endl<<"La media de estos valores es "<<media;
        if(aparece)
            cout<<endl<<"El valor aparece en la posicion "<<pos;
        else
            cout<<endl<<"El n introducido no pertenece a la serie ";
    }
    return 0;
}
