#include <iostream>

using namespace std;

int main()
{
    int alum,sus=0,aprob=0,notab=0,sob=0;
    float nota;
    do{
    cout << "Introduce el numero de alumnos examinados: ";
    cin>> alum;
    for(int i=1; i<=alum; i++ )

            do{
        cout<<"Introduce la nota "<<i<<": "<<endl;
        cin >> nota;
        if(nota>=0 && nota<11){
                if(nota<5){
                    sus=sus+1;
                }else if (nota<7){
                aprob=aprob+1;
                }else if(nota<9){
                notab= notab=1;
                }else
                sob=sob+1;
        }else
        cout<<"Error, reintentalo"<<endl;
            }while(nota<0 || nota>10);

    }while(alum<0);

    cout<<"A continuacion, se muestra una estadistica de las notas de los alumnos"<<endl;
    cout<< "Suspensos: "<<sus<<endl;
    cout<< "Aprobados: "<<aprob<<endl;
    cout<< "Notables: "<<notab<<endl;
    cout<< "Sobresalientes: "<<sob<<endl;
    return 0;
}
