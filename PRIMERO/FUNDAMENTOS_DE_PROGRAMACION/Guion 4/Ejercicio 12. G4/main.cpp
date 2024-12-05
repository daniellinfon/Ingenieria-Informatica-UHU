#include <iostream>
#include <cstring>
using namespace std;

int menu()
{
    int opcion;
    cout <<"       MENU " << endl
             << "   1. Pedir Vector 1" <<endl
             << "   2. Pedir Vector 2" <<endl
             << "   3. Mostrar Vectores" <<endl
             << "   4. Mezclar Vectores"<<endl
             << "   5. Mostrar mezcla"<<endl
             << "   6. Salir"<<endl
             << " Elija una opcion: ";
        cin>>opcion;
        cout<<endl;
        return opcion;
}

void rellenar(int v[15], int num)
{
    for(int i=0; i<num; i++)
            {
                cout<< "Introduce el valor " << i+1<<": ";
                cin>> v[i];
            }
}

void mostrar (int v[15], int v2[15],int numuno,int numdos)
{
    int i,x;

    do
    {
    cout<<"Que vector desea mostrar (1,2)?: ";
    cin>>x;

    if(x==1){
    cout << "Vector 1: ";
                 for(i=0; i<numuno; i++)
            {
                cout<< v[i];
                if(i<numuno-1)
                    cout<<", ";
            }
    }
        else if(x==2){
            cout<<endl<<"Vector 2: ";
            for (i=0; i<numdos; i++)
            {
                cout<< v2[i];
                if(i<numdos-1)
                    cout<<", ";
            }}

        else
            cout<<"Error"<<endl;
    }while(x!=1 && x!=2);
            cout<<endl;

}

void mezclar(int Vfus[30],int v1[15], int v2[15], int numuno, int numdos)
{
    int i;
    for(i=0; i<numuno; i++)
            Vfus[i]=v1[i];

            for(i=0; i<numdos; i++){
                Vfus[numuno+i]=v2[i];

            }


}

void mostrarMezcla(int Vfus[30],int numuno,int numdos)
{
    cout<<"Vector mezclado: ";
    int num3= numuno+numdos;
    int i;
        for(i=0; i<num3;i++){
            cout<<Vfus[i];
            if(i<(num3-1))
                    cout<<", ";
            }cout<<endl;
}

int main()
{

    int opcion,numuno,numdos;
    bool igual;
    int v1[15],v2[15],vfus[30];

    do{
    opcion=menu();
    switch(opcion)
    {
        case 1: do{
            cout<<"Indique el numero de elementos que desea rellenar del Vector 1(15 max): ";
            cin>>numuno;
            }while(numuno>15);
        rellenar(v1,numuno);
        break;

        case 2: do{
            cout<<"Indique el numero de elementos que desea rellenar del Vector 2(15 max): ";
            cin>>numdos;
            }while(numdos>15);
        rellenar(v2,numdos);
        break;

        case 3: mostrar(v1,v2,numuno,numdos);
        break;

        case 4: mezclar(vfus,v1,v2,numuno,numdos);
        break;

        case 5: mostrarMezcla(vfus,numuno,numdos);
        break;

        case 6: break;

        default:
            cout<< "Opcion incorrecta..." <<endl;
    }
    cout <<"\n";
    }while(opcion!=6);
    return 0;
}
