#include <iostream>

using namespace std;
#define NFIL 10
#define NCOL 10
int main()
{
    int tab[NFIL][NCOL],opcion,f,c,impar=0,par=0,sum=0,f1,f2,c1,c2,alt;

    do
    {
        cout << "       MENU " << endl
             << "   1. Pedir datos" <<endl
             << "   2. Mostrar" <<endl
             << "   3. Analizar" <<endl
             << "   4. Sumar una fila"<<endl
             << "   5. Sumar una columna"<<endl
             << "   6. Sumar diagonal principal" <<endl
             << "   7. Sumar diagonal secundaria" <<endl
             << "   8. Intercambiar filas" <<endl
             << "   9. Intercambiar columnas"<<endl
             << "   10.Salir"<<endl<<endl
             << " Elija una opcion: ";

        cin>>opcion;
        switch(opcion)
        {
        case 1:
            for(f=0; f<NFIL; f++)
            {
                for(c=0; c<NCOL; c++)
                {
                    do
                    {
                        cout<<"Introduce el valor ["<<f+1<<","<<c+1<<"]: ";
                        cin>> tab[f][c];
                        if(tab[f][c]<0)
                            cout<<"Solo numeros positivos!!"<<endl;
                    }
                    while(tab[f][c]<0);
                }
            }
            cout<<endl<<endl;
            break;
        case 2:
            cout<<endl<<"           TABLA"<<endl;
            for(f=0; f<NFIL; f++)
            {
                for(c=0; c<NCOL; c++)
                {
                        cout<<tab[f][c]<<"  ";
                }
                cout<<endl;
            }
            cout<<endl;
            break;
        case 3:
            for(f=0; f<NFIL; f++)
            {
                for(c=0; c<NCOL; c++)
                {
                    if(tab[f][c]%2==0)
                        par=par+1;
                    else if(tab[f][c]%2!=0)
                        impar=impar+1;
                }
            }
            cout<<"De los valores introducidos, "<<par<<" de ellos son pares y "<<impar<<" impares"<<endl<<endl;
            break;

        case 4:
            cout<<endl;
            for(f=0; f<NFIL; f++)
            {
                for(c=0; c<NCOL; c++)
                {
                    sum=sum+tab[f][c];

                }
                cout<<"La suma de la fila "<<f+1<<" es igual a "<<sum<<endl;
                if(c==NCOL)
                    sum=0;
            }
            cout<<endl;
            break;
        case 5:
            cout<<endl;
            for(c=0; c<NCOL; c++)
            {
                for(f=0; f<NFIL; f++)
                {
                    sum=sum+tab[f][c];
                }
                cout<<"La suma de la columna "<<c+1<<" es igual a "<<sum<<endl;
                if(f==NFIL)
                    sum=0;
            }
            cout<<endl;
            break;
        case 6:
             for(f=0; f<NFIL; f++)
            {
                for(c=0; c<NCOL; c++)
                {
                    if(f==c)
                        sum=sum+tab[f][c];

                }
            }
            cout<<"La suma de la diagonal principal es igual a "<<sum<<endl<<endl;
            break;
        case 7:
            for(f=0; f<NFIL; f++)
            {
                for(c=0; c<NCOL; c++)
                {
                    if(f+c == NFIL-1)
                        sum=sum+tab[f][c];

                }
            }
            cout<<"La suma de la diagonal secundaria es igual a "<<sum<<endl<<endl;
            break;
        case 8:
        cout<<"Introduce el indice de las filas que quieres intercambiar [0,9]: "<<endl;
            do{
                do{
                    cout<<"Fila 1: ";
                    cin>>f1;

                    if(f1<0 || f1>9)
                        cout<<"Indice no valido"<<endl;
                }while(f1<0 || f1>9);

                do{
                    cout<<"Fila 2: ";
                    cin>>f2;

                    if(f2<0 || f2>9)
                        cout<<"Indice no valido"<<endl;
                }while(f2<0 || f2>9);

                if(f1==f2)
                    cout<<"No se puede intercambiar una misma fila."<<endl;

                }while(f1==f2);

                for(c=0; c<NCOL; c++)
                {
                    alt=tab[f1][c];
                    tab[f1][c]=tab[f2][c];
                    tab[f2][c]=alt;
                }
            break;
        case 9:
        cout<<"Introduce el indice de las columnas que quieres intercambiar [0,9]: "<<endl;
            do{
                do{
                    cout<<"Columna 1: ";
                    cin>>f1;

                    if(c1<0 || c1>9)
                        cout<<"Indice no valido"<<endl;
                }while(c1<0 || c1>9);

                do{
                    cout<<"Columna 2: ";
                    cin>>c2;

                    if(c2<0 || c2>9)
                        cout<<"Indice no valido"<<endl;
                }while(c2<0 || c2>9);

                if(c1==c2)
                    cout<<"No se puede intercambiar una misma columna."<<endl;

                }while(c1==c2);

                for(f=0; f<NCOL; f++)
                {
                    alt=tab[f][c1];
                    tab[f][c1]=tab[f][c2];
                    tab[f][c2]=alt;
                }
            break;
            }
        if(opcion<1 || opcion>10)
            cout<<" Del 1 al 10 gilipollas"<<endl<<endl;
    }while (opcion != 10);

    return 0;
}
