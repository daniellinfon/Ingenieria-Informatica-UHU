#include <iostream>
#include <cstdlib>
#include <ctime>
using namespace std;

#define M 1000

int main()
{
    char Vector[M];
    float N,num,a,e,o,x,u,porcentaje_a,porcentaje_e,porcentaje_i,porcentaje_o,porcentaje_u;
    do
    {
        cout << "Introduce el número de elementos del vector a ser rellenados: ";
        cin >> N;

        if(N<0 || N>1000)
            cout<<"Error,vuelva a intentarlo"<<endl;
    }
    while(N<0 || N>1000);

    srand(time(0));

    for(int i=0; i<N; i++)
    {
        num = rand() % 21;

        if(num<5)
        {
            Vector[i]='a';
            a=a+1;
        }
        else if(num>4 && num<8)
        {
            Vector[i]='e';
            e=e+1;
        }
        else if(num>7 && num<13)
        {
            Vector[i]='i';
            x=x+1;
        }
        else if(num>12 && num<17)
        {
            Vector[i]='o';
            o=o+1;
        }
        else
        {
            Vector[i]='u';
            u=u+1;
        }
    }
    porcentaje_a= (a*100)/N;
    porcentaje_e= (e*100)/N;
    porcentaje_i= (x*100)/N;
    porcentaje_o= (o*100)/N;
    porcentaje_u= (u*100)/N;


    cout<<"La vocal 'a' ha aparecido "<<a<<" veces("<<porcentaje_a<<"%)"<<endl;
    cout<<"La vocal 'e' ha aparecido "<<e<<" veces("<<porcentaje_e<<"%)"<<endl;
    cout<<"La vocal 'i' ha aparecido "<<x<<" veces("<<porcentaje_i<<"%)"<<endl;
    cout<<"La vocal 'o' ha aparecido "<<o<<" veces("<<porcentaje_o<<"%)"<<endl;
    cout<<"La vocal 'u' ha aparecido "<<u<<" veces("<<porcentaje_u<<"%)"<<endl;
    return 0;
}
