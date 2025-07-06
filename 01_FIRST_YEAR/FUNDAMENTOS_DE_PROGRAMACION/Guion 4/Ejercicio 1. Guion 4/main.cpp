#include <iostream>

using namespace std;
#define M 10

void rellenar(int T[M])
{
    for(int i=0; i<M; i++)
    {
        cout<<"Introduce ["<<i<<"]: ";
        cin>> T[i];
    }
}

int minimo(int T[M])
{
    int mini;

    mini = T[0];
    for(int i=1; i<M; i++)
        if(T[i]<mini)
        mini=T[i];

    return mini;
}

int maximo(int T[M])
{
    int maxi;
    maxi=T[0];
    for(int i=1; i<M; i++)
    if(T[i]>maxi)
        maxi=T[i];
    return maxi;
}

int main()
{
    int tabla
}
