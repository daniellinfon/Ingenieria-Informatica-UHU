#include <iostream>

using namespace std;

int main()
{
    int Vector1[10], Vector2[10], opcion, i;
    bool iguales;
    do
    {
        cout << "       MENU " << endl
             << "   1. Pedir Vector 1" <<endl
             << "   2. Pedir Vector 2" <<endl
             << "   3. Mostrar Vectores" <<endl
             << "   4. Comprobar Vectores"<<endl
             << "   5. Salir"<<endl
             << " Elija una opcion: ";
        cin>>opcion;
        switch(opcion)
        {
        case 1:
            for(i=0; i<10; i++)
            {
                cout<< "Introduce el valor " << i+1<<": ";
                cin>> Vector1[i];
            }
            break;
        case 2:
            for (i=0; i<10; i++)
            {
                cout<<"Introduce el valor "<<i+1<<": ";
                cin>> Vector2[i];
            }
                break;
        case 3:
            cout << "Vector 1: ";
                 for(i=0; i<10; i++)
            {
                cout<< Vector1[i];
                if(i<9)
                    cout<<", ";
            }
            cout<<endl<<"Vector 2: ";
            for (i=0; i<10; i++)
            {
                cout<< Vector2[i];
                if(i<9)
                    cout<<", ";
            }
            cout<<endl;
            break;
        case 4:
            i=0;
            iguales=true;
            while(i<10 && iguales)
                if(Vector1[i] == Vector2[i])
                    i++;
                else
                    iguales = false;
            cout<< "Los vectores ";
            if(!iguales)
                cout<< "no ";
            cout <<"son iguales."<<endl;
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
