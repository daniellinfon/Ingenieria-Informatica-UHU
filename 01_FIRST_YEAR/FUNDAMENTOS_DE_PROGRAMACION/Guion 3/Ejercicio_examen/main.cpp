#include <iostream>
#define N 8
#define M 5

using namespace std;

typedef int Conjunto[N];

int main()
{
    Conjunto cjtos[M];
    int op,nconjun,elementos[N], conjun1, conjun2;
    do
    {
        cout << "   MENU CONJUNTOS " << endl
             << "   1. Cargar Conjuntos" <<endl
             << "   2. Mostrar Conjuntos" <<endl
             << "   3. Mostrar interseccion de dos conjuntos" <<endl
             << "   4. Ver si dos conjuntos son Disjuntos"<<endl
             << "   5. Ver si dos conjuntos son iguales"<<endl
             << "   6. Salir"<<endl
             << " Elija una opcion: ";
        cin>>op;
        system("cls");

        switch(op)
        {
        case 1:
            do
            {
                cout<<"Numero de conjuntos que desea cargar entre 1 y 5: ";
                cin >> nconjun;
                system("cls");
            }
            while(nconjun<1 || nconjun>5);

            for (int i=0; i<nconjun; i++)
            {

                do
                {
                    cout << "Numero de elementos que tiene el conjunto " << i+1 << ": ";
                    cin >> elementos[i];
                }
                while (elementos[i]<1 || elementos[i]>N);
            }
            for (int i=0; i<nconjun; i++)
            {
                for (int j=0; j<elementos[i]; j++)
                {
                    cout << "Asigne un valor al elemento " << j+1 << " del conjunto " << i+1 << ": ";
                    cin >> cjtos[i][j];
                }
            }
            break;
        case 2:
            for (int i=0; i<nconjun; i++)
            {
                for (int j=0; j<elementos[i]; j++)
                {
                    cout<<"Conjunto "<<i+1<<", elemento "<<j+1<<": "<<cjtos[i][j]<<endl;
                }
            }

            break;
        case 3:
        {
            int inter=0;
            cout << "Introduzca un conjunto: ";
            cin >> conjun1;
            cout << "Introduzca otro conjunto: ";
            cin >> conjun2;

            cout << "Coinciden los siguientes valores: ";

            for (int i=0; i<elementos[conjun1-1]; i++)
            {
                for (int j=0; j<elementos[conjun2-1]; j++)
                {
                    if (cjtos[conjun1-1][i]==cjtos[conjun2-1][j])
                    {
                        cout << cjtos[conjun1-1][i] << ", ";
                        inter++;
                    }
                }
            }cout<<endl;
            if (inter==0)
            {
                system("cls");
                cout << "No coincide ningun valor."<<endl;
            }
            break;
        }

        case 4:
        {
            int inter=0;
            cout << "Introduzca un conjunto: ";
            cin >> conjun1;
            cout << "Introduzca otro conjunto: ";
            cin >> conjun2;

            cout << "No son disjuntos";

            for (int i=0; i<elementos[conjun1-1]; i++)
            {
                for (int j=0; j<elementos[conjun2-1]; j++)
                {
                    if (cjtos[conjun1-1][i]==cjtos[conjun2-1][j])
                    {
                        inter++;
                    }
                }
            }
            if (inter==0)
            {
                system("cls");
                cout << "Son disjuntos";
            }
            break;
        }

        case 5:
        {
            int inter=0;
            cout << "Introduzca un conjunto: ";
            cin >> conjun1;
            cout << "Introduzca otro conjunto: ";
            cin >> conjun2;

            cout << "No son iguales";

            for (int i=0; i<elementos[conjun1-1]; i++)
            {
                for (int j=0; j<elementos[conjun2-1]; j++)
                {
                    if (cjtos[conjun1-1][i]==cjtos[conjun2-1][j])
                    {
                        cout << cjtos[conjun1-1][i] << ", ";
                        inter++;
                    }
                }
            }
            if (inter==elementos[conjun1-1] && inter==elementos[conjun2-1])
            {
                system("cls");
                cout << "Los elementos son iguales" << endl;
            }
            break;
        }
        }

    }
    while (op != 6);
    return 0;
}






