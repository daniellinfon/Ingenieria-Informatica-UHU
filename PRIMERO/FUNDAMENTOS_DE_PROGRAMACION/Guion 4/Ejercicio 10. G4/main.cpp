#include <iostream>

using namespace std;

int menu()
{
    int opcion;
    cout <<"       MENU " << endl
             << "   1. Pedir Vector 1" <<endl
             << "   2. Pedir Vector 2" <<endl
             << "   3. Mostrar Vectores" <<endl
             << "   4. Comprobar Vectores"<<endl
             << "   5. Salir"<<endl
             << " Elija una opcion: ";
        cin>>opcion;
        cout<<endl;
        return opcion;
}

void rellenar(int v[10])
{
    for(int i=0; i<10; i++)
            {
                cout<< "Introduce el valor " << i+1<<": ";
                cin>> v[i];
            }
}

void mostrar (int v[10], int v2[10])
{
    int i;
    cout << "Vector 1: ";
                 for(i=0; i<10; i++)
            {
                cout<< v[i];
                if(i<9)
                    cout<<", ";
            }
            cout<<endl<<"Vector 2: ";
            for (i=0; i<10; i++)
            {
                cout<< v2[i];
                if(i<9)
                    cout<<", ";
            }
            cout<<endl;

}

bool iguales (int v1[10], int v2[10])
{
    int i=0;
    bool iguales=true;
            while(i<10 && iguales)
                if(v1[i] == v2[i])
                    i++;
                else
                    iguales = false;

    return iguales;

}

int main()
{
    int opcion;
    bool igual;
    int v1[10],v2[10];

    do{
    opcion=menu();
    switch(opcion)
    {
        case 1: rellenar(v1);
        break;

        case 2: rellenar(v2);
        break;

        case 3: mostrar(v1,v2);
        break;

        case 4:
        break;

        case 5: break;

        default:
            cout<< "Opcion incorrecta..." <<endl;
    }
    cout <<"\n";
    }while(opcion!=5);
    return 0;
}
