#include <iostream>
#include <cstdlib>
#include <ctime>
using namespace std;

#define M 1000
char generaVocal();

char generaVocal()
{
    char vocal;
    int num;

        num = rand() % 21;

        if(num<5)
        {
            vocal='a';

        }
        else if(num>4 && num<8)
        {
            vocal='e';

        }
        else if(num>7 && num<13)
        {
            vocal='i';

        }
        else if(num>12 && num<17)
        {
            vocal='o';

        }
        else
        {
            vocal='u';

        }
        return vocal;
}

void estadistica (char t[1000], int N, char v, int &f, int &p)
{
    int a=0, e=0, i=0, o=0, u=0;
    for(int j=0; j<N; j++){

    if(t[j]=='a')
        {
            a=a+1;
        }
        else if(t[j]=='e')
        {

            e=e+1;
        }
        else if(t[j]=='i')
        {

            i=i+1;
        }
        else if(t[j]=='o')
        {

            o=o+1;
        }
        else if(t[j]=='u')
        {
            u=u+1;
        }
    }

    switch(v)
    {
        case 'a':f=a;
        break;
        case 'e':f=e;
        break;
        case 'i':f=i;
        break;
        case 'o':f=o;
        break;
        case 'u':f=u;
        break;
    }
    p=(f*100)/N;
}

int main()
{
    int N,frecuencia,porcentaje;
    char vec[M],vocal;
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
        vec[i]= generaVocal();

    }

    cout<<"Introduce la vocal que quieres comprobar: ";
    cin>>vocal;

    estadistica(vec,N,vocal,frecuencia,porcentaje);

    cout<<endl<<"La vocal "<<vocal<< " ha aparecido "<<frecuencia<<" veces ("<<porcentaje<<"%)"<<endl;

    return 0;
}
