#include <iostream>
#include <Clasificacion.h>
#include <ctime>
using namespace std;

int main()
{
    srand(time(0));
    Prueba campeonato("huelvaextremapro.dat", "huelvaextrema.dat");
    campeonato.generarInfoPaises();
    campeonato.mostrarInfoPaises();
    int opc;
    do
    {
        int numCiclistas=campeonato.getNumCiclistas();
        system("cls");
        cout<<"\nHuelva Extrema\n"
            <<"-------------------------------------\n"
            <<"Ciclistas: "<<numCiclistas
            <<endl
            <<endl
            <<"\t\t1. Consulta de inscripciones\n"
            <<"\t\t2. Inscripcion a la prueba\n"
            <<"\t\t3. Busqueda de una inscripcion\n"
            <<"\t\t4. Modificar datos de una inscripcion\n"
            <<"\t\t5. Eliminar una inscripcion\n"
            <<"\t\t6. Mostrar Clasificacion\n"
            <<"\t\t7. Salir\n"
            <<endl
            <<endl
            <<"Indique la opcion deseada: ";
        cin>>opc;
        system("cls");

        switch(opc)
        {
            case 1: {
                        cadena pais;
                        cout<<"Indique el pais o '*' para mostrar todo: ";
                        fflush(stdin);
                        gets(pais);
                        system("cls");
                        campeonato.mostrar(pais);
                        cout<<endl;
                        system("pause");
                    }
                    break;

            case 2: {
                        Ciclista cic;
                        cadena palabra;
                        int x;
                        cout<<"Introduzca datos del ciclista:"<<endl<<endl
                            <<"Dorsal: ";
                        cin>>cic.dorsal;
                        x = campeonato.buscar(cic.dorsal);
                        if(x!=-1)
                            cout<<"\nERROR: Ciclista ya inscrito. \n";
                        else
                        {
                            cout<<"\nPais: ";
                            fflush(stdin);
                            gets(palabra);
                            strcpy(cic.pais, palabra);
                            cout<<"\nNombre: ";
                            fflush(stdin);
                            gets(palabra);
                            strcpy(cic.nombre, palabra);
                            cout<<"\nApellidos: ";
                            fflush(stdin);
                            gets(palabra);
                            strcpy(cic.apellidos, palabra);
                            cout<<endl;
                            campeonato.insertar(cic);
                            cout<<"Ciclista inscrito correctamente!";
                        }
                        cout<<endl;
                        system("pause");
                    }
                    break;

            case 3: {
                        int dorsal, posicion;
                        Ciclista cic;
                        cout<<"Indique el dorsal del ciclista: ";
                        cin>>dorsal;
                        posicion = campeonato.buscar(dorsal);
                        if(posicion!=-1)
                        {
                            cic = campeonato.consultar(posicion);
                            cout<<endl
                                <<"Dorsal: "<<cic.dorsal
                                <<"\nPais: "<<cic.pais
                                <<"\nNombre: "<<cic.nombre
                                <<"\nApellidos: "<<cic.apellidos;
                        }
                        else
                            cout<<"\nERROR: Ciclista no inscrito.";
                        cout<<endl;
                        system("pause");
                    }
                    break;

            case 4: {
                        Ciclista cic,c;
                        cadena palabra;
                        int nuevoDorsal;
                        int posicion, dorsalUsado;
                        cout<<"Introduzca datos del ciclista a modificar:"<<endl<<endl
                            <<"Dorsal: ";
                        cin>>cic.dorsal;
                        posicion = campeonato.buscar(cic.dorsal);
                        if(posicion!=-1)
                        {

                            cout<<"\nNombre: ";
                            fflush(stdin);
                            gets(palabra);
                            strcpy(cic.nombre, palabra);
                            cout<<"\nApellidos: ";
                            fflush(stdin);
                            gets(palabra);
                            strcpy(cic.apellidos, palabra);
                            cout<<endl;
                            cout<<"\nDorsal: ";
                            cin>>nuevoDorsal;
                            cic.dorsal=nuevoDorsal;


                            campeonato.modificar(cic, posicion);
                            cout<<"\nCiclista modificado correctamente!";
                        }
                        else
                        {
                            cout<<"\nERROR: Dorsal no encontrado.";
                        }
                        cout<<endl;
                        system("pause");
                    }
                    break;

            case 5: {
                        int dorsal, posicion;
                        cout<<"Indique el dorsal del ciclista a eliminar: ";
                        cin>>dorsal;
                        posicion = campeonato.buscar(dorsal);
                        if(posicion!=-1)
                        {
                            campeonato.eliminar(posicion);
                            cout<<"\nCiclista eliminado correctamente!";
                        }
                        else
                            cout<<"\nERROR: Dorsal no encontrado.";
                        cout<<endl;
                        system("pause");
                    }
                    break;

            case 6: {
                        campeonato.Competicion();
                        cout<<endl;
                        system("pause");
                    }
                    break;

        }
    }while(opc!=7);
    return 0;
}
