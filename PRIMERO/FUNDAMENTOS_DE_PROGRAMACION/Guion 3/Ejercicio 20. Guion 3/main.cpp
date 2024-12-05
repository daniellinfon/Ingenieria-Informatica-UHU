#include <iostream>
#include <time.h>
#include <cstdlib>
#define TAMA 25

using namespace std;

int main()
{
    int Datos[1000]; //Vector donde se almacenan los valores a analizar
    int NDatos=0; //Nº de valores almacenados en Datos
    int Valores[TAMA]; //Vector que contiene la frecuencia de aparición
    int op,i=0,numrand,valor,valormax;
    bool datosfin=false, noesta=false;
    char op2;
    srand(time(NULL));
    do
    {
        cout << "       MENU " << endl
             << "   1. Pedir datos" <<endl
             << "   2. Analizar datos" <<endl
             << "   3. Estan todos?" <<endl
             << "   4. Valor repetido"<<endl
             << "   5. Valor mas repetido"<<endl
             << "   6. Mostrar datos" <<endl
             << "   7. Mostrar analisis" <<endl
             << "   8. Salir" <<endl
             << " Elija una opcion: ";

        cin>>op;
        switch(op)
        {

        case 1:
            cout<<"Desea introducirlos manualmente (m) o de forma aleatoria (a)? ";
            cin >> op2;

            switch(op2)
            {

            case 'm':
                cout<<"Introduce valores enteros en el intervalo[0,24]. Introduce -1 cuando quieras parar. ";
                    while(i<1000 && !datosfin)
                {
                    do
                    {
                        cin>>Datos[i];
                        if(Datos[i]==-1)
                            datosfin= true;
                        else if(Datos[i] < -1 || Datos[i]>24)
                            cout<<"Valor no valido, introducelo de nuevo."<<endl;
                        else
                            NDatos++;
                    }while(Datos[i] < -1 || Datos[i]>24);
                }
                break;
            case 'a':
                NDatos= 1+rand()%1000;

                while(i<NDatos)
                {
                    numrand= rand()%25;
                    Datos[i]=numrand;
                    i++;
                }
                break;

            }
            break;
        case 2:
            for(i=0; i<TAMA; i++)
                Valores[i]=0;
            for(i=0; i<NDatos; i++)
                Valores[Datos[i]]++;
            break;
        case 3:
            for(i=0; i<TAMA; i++)
            {
                if(Valores[i]==0)
                    noesta=true;
            }
            if(noesta)
                cout<<endl<<"Hay valores del intervaolo [0,24] que no estan.";
            else
                cout<<endl<<"Todos los valores del intervalo [0,24] aparecen al menos una vez en el vector Datos";
            break;
        case 4:
            cout<<endl<<"Introduce un valor del intervalo [0,24] para ver cuantas veces se repite: ";

            do
            {
                cin>>valor;
            }
            while(valor<0 || valor>24);

            cout<<endl<<"El valor "<<valor<< " se repite "<<Valores[valor]<< "veces.";
            break;
        case 5:
            i=0;
            valormax=Valores[i];
            while(i<TAMA)
            {
                if(Valores[i]>valormax)
                    valormax = Valores[i];
                i++;
            }
            cout<<endl<<"El valor mas repetido es el "<<valormax;
            break;
        case 6:
            for(i=0; i<NDatos; i++)
            {
                cout<< Datos[i]<<" ";
            }
            break;
        case 7:
            for(i=0;  i<TAMA; i++)
                cout<<Valores[i]<<" ";
            break;
        }
    }
    while(op!=8);

    return 0;
}
