#include <iostream>
#include <conio.h>
#include <cstring>
using namespace std;

class Vector
{ float valores[15];
 int nElem; //Nº de elementos del atributo valores
public:
 Vector();
 bool rellenar (int cuantos);
 void mostrar();
 int getnElem();
 float getValor(int pos);
 int menorRelativo(int P);
 void intercambio (int pos1, int pos2);
};

Vector::Vector()
{
    nElem=0;
}
bool Vector::rellenar (int cuantos)
{
    bool ok=false;

    if(cuantos>=1 && cuantos<=15)
    {
        ok=true;
        nElem=cuantos;
        for(int i=0; i<cuantos; i++)
        {
            cout<<"[" << i+1 << "] = ";
            cin>> valores[i];
        }
    }
    return ok;
}

void Vector::mostrar ()
{
    for(int i=0; i<nElem; i++)
    cout<<endl<<"["<<i+1<<"] = " <<valores[i];
}

int Vector::getnElem()
{
    return nElem;
}

float Vector::getValor(int pos)
{
    return valores[pos];
}

int Vector::menorRelativo (int P)
{
    int pmenor = P;
    float menor = valores[P];

    for(int i=P+1; i<nElem; i++)
        if(valores[i]<menor)
        {
        menor=valores[i];
        pmenor = i;
        }
    return pmenor;
}

void Vector::intercambio (int pos1, int pos2)
{
    float aux;
    aux = valores[pos1];
    valores[pos1] = valores[pos2];
    valores[pos2] = aux;
}

int menu()
{
    int opcion;
        cout << "            --- MENU --- " << endl
             << "   1. Insertar datos en el vector" <<endl
             << "   2. Mostrar Vector" <<endl
             << "   3. Menor relativo" <<endl
             << "   4. Ordenar Vector de menor a mayor"<<endl
             << "   5. Salir"<<endl
             << "   Elija una opcion: ";
        cin>>opcion;
    return opcion;
}

int main()
{
    Vector V;
    int opcion, cuantos, pos, posmenor;
    bool ok;

    do
    {
        opcion = menu();
        switch(opcion)
        {
            case 1: do
            {
                cout << "Cuantos elementos quiere?: ";
                cin>> cuantos;
                ok = V.rellenar(cuantos);
                if(!ok)
                    cout <<endl<<"Cantidad erronea"<<endl;
            }while(!ok);
            break;

            case 2: if(V.getnElem()==0)
                        cout<<endl<<"El vector esta vacio"<<endl;
                    else
                        V.mostrar();
                        cout<<endl;
                    break;

            case 3: if(V.getnElem()==0)
                        cout<<endl<<"El vector esta vacio"<<endl;
                    else
                    {
                        do
                        {
                            cout<< "A partir de que posicion [1-15]: ";
                            cin >>pos;
                            if (pos<1||pos>15)
                                cout<<endl<<"Posicion erronea."<<endl;
                        }while(pos<1 || pos>15);
                        posmenor=V.menorRelativo(pos-1);
                        cout<< "El valor mas pequeno a partir de la posicion ["<<pos<<"] es ";
                        cout<< " [" <<posmenor+1<< "]= " <<V.getValor(posmenor)<<endl;
                    }
                    break;

            case 4: cuantos=V.getnElem();
                    if(cuantos!=0)
                    {
                        for(int i=0; i<cuantos; i++)
                        {
                            posmenor= V.menorRelativo(i);
                            V.intercambio(i, posmenor);
                        }
                        cout<<endl<<"El vector ha sido ordenado."<<endl;
                    }
                    else
                        cout<<endl<<"El vector esta vacio."<<endl;

            }
            getch();
        }while(opcion!=5);
        return 0;
}
