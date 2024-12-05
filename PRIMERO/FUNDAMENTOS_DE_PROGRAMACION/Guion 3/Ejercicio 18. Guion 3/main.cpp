#include <iostream>
#include <cstring>
using namespace std;

int main()
{
    int Vuno[15], Vdos[15], Vfus[30],opcion, i;
    int numuno, numdos,maximo,minimo,sum;
    do
    {
        cout << "       MENU " << endl
             << "   1. Pedir Vector 1" <<endl
             << "   2. Pedir Vector 2" <<endl
             << "   3. Mostrar Vectores" <<endl
             << "   4. Mezclar Vectores"<<endl
             << "   5. Mostrar Mezcla"<<endl
             << "   6. Salir"<<endl
             << " Elija una opcion: ";
        cin>>opcion;
        switch(opcion)
        {
        case 1:
            do{
            cout<<"Indique el numero de elementos que desea rellenar del Vector 1(15 max): ";
            cin>>numuno;
            }while(numuno>15);


            for(i=0; i<numuno; i++)
            {
                cout<< "Introduce el valor " << i+1<<": ";
                cin>> Vuno[i];

            }
            break;
        case 2:
            do{
            cout<<"Indique el numero de elementos que desea rellenar del Vector 1(15 max): ";
            cin>>numdos;
            }while(numdos>15);

            for (i=0; i<numdos; i++)
            {
                cout<<"Introduce el valor "<<i+1<<": ";
                cin>> Vdos[i];
            }
                break;
        case 3:
             cout << "Vector 1: ";
                 for(i=0; i<numuno; i++)
            {
                cout<< Vuno[i];
                if(i<numuno-1)
                    cout<<", ";
            }
            cout<<endl<<"Vector 2: ";
            for (i=0; i<numdos; i++)
            {
                cout<< Vdos[i];
                if(i<numdos-1)
                    cout<<", ";
            }
            cout<<endl;

        case 4:
            for(i=0; i<numuno; i++)
            Vfus[i]=Vuno[i];

            for(i=0; i<numdos; i++){
                Vfus[numuno+i]=Vdos[i];
            }
            break;
        case 5:
            sum=numuno+numdos;
            cout << "Vector mezclado: ";
                 for(i=0; i<sum; i++)
            {
                cout<< Vfus[i];
                if(i<(sum-1))
                    cout<<", ";
            }cout<<endl;

        }
    }while (opcion != 6);
    return 0;
}
