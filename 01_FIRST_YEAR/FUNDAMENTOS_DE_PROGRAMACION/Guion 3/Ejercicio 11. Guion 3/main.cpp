#include <iostream>
#include <cstdlib>
#include <ctime>
#include <stdlib.h>

#define M 30//MAX CARACTERES PERMITIDOS
#define L 10//N D NOM O APELLIDOS
using namespace std;
typedef char Vnombres[M],Vapellidos[M];
int main()
{
   int opcion, i;
   Vnombres nombre[L];
   Vapellidos apellido[L];
    do
    {
        cout << "       MENU " << endl
             << "   1. Pedir nombres" <<endl
             << "   2. Pedir apellidos" <<endl
             << "   3. Mostrar Vectores" <<endl
             << "   4. Generar nombres de personas"<<endl
             << "   5. Salir"<<endl
             << " Elija una opcion: ";
        cin>>opcion;
        switch(opcion)
        {
        case 1:
            for(i=0; i<10; i++)
            {
                cout<< "Introduce el nombre " << i+1<<": ";
                cin>> nombre[i];
            }
            break;
        case 2:
            for (i=0; i<10; i++)
            {
                cout<<"Introduce el apellido "<<i+1<<": ";
                cin>> apellido[i];
            }
                break;
        case 3:
            cout << "Vector Nombres: ";
                 for(i=0; i<10; i++)
            {
                cout<< nombre[i];
                if(i<9)
                    cout<<", ";
            }
            cout<<endl<<"Vector Apellidos: ";
            for (i=0; i<10; i++)
            {
                cout<< apellido[i];
                if(i<9)
                    cout<<", ";
            }
            cout<<endl;
            break;
        case 4:
            int num,randnum,randnum2,randnum3;
            srand(time(0));

            cout<<"Cuantos nombres desea generar: ";
            cin >>num;

            for(int k=1; k<= num; k++){
                randnum= rand()%L;
                 randnum2= rand()%L;
                  randnum3= rand()%L;

                  cout<<endl<<"Nombre aleatorio "<<k<<": "<<nombre[randnum]<<" " << apellido[randnum2]<<" "<<apellido[randnum3]<<endl;

            }


            break;
        case 5: cout<<endl;
        break;
        default:
            cout<< "Opcion incorrecta..." <<endl;
        }
    }
    while (opcion != 5);



    return 0;
}
