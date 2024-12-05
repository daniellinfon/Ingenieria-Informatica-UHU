#include <iostream>
#define L 15
using namespace std;

int main()
{
    double vec[15];
    int valor, n,m;

    for(n=0; n<L; n++){
    cout << "Introduce un valor para el elemento "<<n+1<<": ";
    cin>>vec[n];
    }

    //Algoritmo para ordenar el vector
    for(n=1; n<L; n++)
    {
        m=0;
        while(m<n)
        {
            if(vec[n]<vec[m])
            {
                valor=vec[n];
                vec[n]=vec[m];
                vec[m]=valor;
            }
            m++;
        }
    }

    cout<<endl<<"Vector ordenado: ";
    for(n=0; n<L; n++)
    {
        cout<<vec[n];
        if(n!= L-1)
            cout<<" < ";
    }
    return 0;
}
