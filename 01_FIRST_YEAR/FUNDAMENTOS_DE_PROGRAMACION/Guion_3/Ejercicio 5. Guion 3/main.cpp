#include <iostream>
#include <cstring>
#define NFIL 2
#define NCOL 4
using namespace std;

typedef char cadena[30];
struct persona
{
    int dni;
    cadena nombre;
};
int main()
{
    persona tabla [NFIL][NCOL];
    int dni_buscado;
    bool encontrado = false;

    cout<<"Rellena la tabla con los datos de las personas: "<<endl;
    for ( int f=0; f< NFIL; f++)
        for (int c=0; c<NCOL; c++)
        {

            cout << "Introduce el NOMBRE de la persona de la fila "<<f+1 << " y columna " <<c+1<< " : ";
            cin >> tabla [f][c].nombre;
            cout << "Introduce el DNI de la persona de la fila "<<f+1 << " y columna " <<c+1<< " : ";
            cin >> tabla[f][c].dni;
        }

    //POR SI QUIERES MOSTRAR LA TAB:
    /*for(int f=0; f<NFIL; f++)
        for(c=0; c<NCOL; c++){
            cout<<"Nombre: "<<tabla[f][c].nombre<< " | DNI: "<<tabla[f][c].dni<<"\t\t";

            if (NCOL==3)
            cout<<endl;
        }*/

    cout << "Introduzca el dni a buscar ";
    cin >> dni_buscado;



    int f = 0;
    cadena nombre2;
    while (f<NFIL && encontrado == false)
    {
       int c=0;
        while (c<NCOL && encontrado == false){
            if (dni_buscado==tabla[f][c].dni)
            {
                encontrado = true;
                strcpy(nombre2, tabla[f][c].nombre);
            }
            else
                c++;
    }
    f++;
    }
    if (encontrado)
        cout << "La persona con DNI "<< dni_buscado<<", se llama "<< nombre2<<endl;
    else
        cout << "No hay nadie con el dni indicado"<<endl;
    return 0;
}
