#include <iostream>
#include <Pila.h>
using namespace std;

int menu()
{
    int op;
    system("cls");
    cout << "CriptoDoc" << endl;
    cout << "-----------"<< endl;
    cout << "\t  1. Cifrar documento" << endl;
    cout << "\t  2. Descifrar documento" << endl;
    cout << "\t  3. Descifrar varios documentos" << endl;
    cout << "\t  4. Invertir fichero de codigos" << endl;
    cout << "\t  5. Mostrar ficheros" << endl;
    cout << "\t  6. Mostrar nodos enlazados" << endl;
    cout << "\t  7. Salir" << endl << endl;
    cout << "Indique la opcion deseada: ";
    cin >> op;

    return op;
}

struct TNodo
{
    int Datos; //Datos a almacenar en cada nodo
    TNodo *Siguiente; //Puntero al siguiente nodo
};

void mostrardoc(char fichero[])
{
    ifstream f(fichero);
    linea texto;
    cout<<endl<<endl;
    getline(f, texto);
    while(!f.eof())
    {
        cout<<texto<<endl;
        getline(f, texto);
    }
    f.close();
}

int main()
{
    lista nombres;
    int op;
    CriptoDoc crip;
    do
    {
        op = menu();
        switch (op)
        {
        case 1:
        {
            char nom1[50],nom2[50];
            int code;
            bool leer;
            system("cls");
            cout<<"Introduce el nombre del documento a cifrar: ";
            fflush(stdin);
            gets(nom1);

            leer=crip.leer(nom1);
            if(leer)
            {
                cout<<"\nIntroduce el nombre del documento resultante: ";
                fflush(stdin);
                gets(nom2);
                nombres.anadirDch(nom2);
                do
                {
                    cout<<"\nIntroduce un codigo: ";
                    cin>>code;
                    system("cls");
                    if(code<100||code>200)
                        cout<<"Tu eres tonto";
                }
                while(code<100||code>200);


                crip.cifrar(code);
                if(!crip.escribir(nom2))
                    cout<<"Error, no se pudo escribir en el documento"<<endl;
                else
                    cout<<"Todo ok"<<endl;

            }

            else
                cout<<"Error al leer el fichero"<<endl;

            crip.vaciar();
            system("pause");
            break;
        }
        case 2:
        {
            char nom1[50],nom2[50];
            int code;
            bool leer;
            system("cls");
            cout<<"Introduce el nombre del documento a descifrar: ";
            fflush(stdin);
            gets(nom1);

            leer=crip.leer(nom1);
            if(leer)
            {
                cout<<"\nIntroduce el nombre del documento resultante: ";
                fflush(stdin);
                gets(nom2);
                nombres.anadirDch(nom2);
                do
                {
                    cout<<"\nIntroduce un codigo: ";
                    cin>>code;
                    system("cls");
                    if(code<100||code>200)
                        cout<<"Tu eres tonto";
                }
                while(code<100||code>200);


                crip.descifrar(code);
                if(!crip.escribir(nom2))
                    cout<<"Error, no se pudo escribir en el documento"<<endl;
                else
                    cout<<"Todo ok"<<endl;

            }

            else
                cout<<"Error al leer el fichero"<<endl;

            crip.vaciar();
            system("pause");
            break;
        }
        case 3:
        {
            if(!crip.leer("fichCodigos"))
                cout<<"No se pudo abrir el fichero...\n";
            else
            {
                crip.descifrar(123);
                CriptoDoc aux, aux2;
                int x=0;
                for(int i=1; i<=3; i++)
                {
                    linea text = crip.observar(i), nombre, num;
                    int code = stoi(text);
                    char fichero[50];
                    num= to_string(i);
                    nombre = "doc-"+num;
                    strcpy(fichero, nombre.c_str());
                    if(!aux.leer(fichero))
                        cout<<"No se pudo abrir el fichero "<<nombre<<endl;
                    else
                    {
                        aux.descifrar(code);
                        aux2.concatenar(aux);
                        aux.vaciar();
                        x++;
                    }
                }
                aux2.escribir("docDescifrado.txt");
                nombres.anadirDch("docDescifrado.txt");
                cout<<"Se han descifrado un total de "<<x<<" documentos"<<endl;
            }


            system("pause");
            break;
        }
        case 4:
        {
            pila p;
            if(crip.leer("fichCodigos"))
            {
                crip.descifrar(123);
                for(int i=1; i<=crip.numlineas(); i++)
                {
                    linea text = crip.observar(i);
                    int code = stoi(text);
                    p.apilar(code);
                }
                crip.vaciar();
                ofstream fich("fichCodigos2");
                while(!p.esvacia())
                {
                    int n=p.cima();
                    linea text2=to_string(n);
                    fich << text2<<endl;
                    p.desapilar();
                }
                fich.close();
                if(crip.leer("fichCodigos2"))
                {
                    crip.cifrar(123);
                    if(crip.escribir("fichcodigos2"))
                        cout<<"Se ha escrito correctamente"<<endl;
                    else
                        cout<<"No se puede abrir el fichero"<<endl;
                }
                else
                    cout<<"No se puede abrir el fichero"<<endl;
            }
            else
                cout<<"No se pudo abrir el fichero...\n";

            crip.vaciar();
            system("pause");
            break;
        }

        case 5:
        {
            system("cls");
            if(nombres.esvacia())
                cout<<"No hay ningun archivo";
                    else
                {

                    linea nombre;
                    int i=1;
                    char resp, nom[50];
                    bool salir = false;
                    cout<<"\tLista de Documentos: Indique S/N si quiere visualizar \n";
                    while(i<=nombres.longitud()&&!salir)
                    {
                        nombre = nombres.observar(i);
                        cout<<"\n---> "<<nombre<<"  "<<"Visualizar: ";
                        cin>>resp;
                        if(resp=='S' || resp=='s')
                        {
                            strcpy(nom, nombre.c_str());
                            mostrardoc(nom);
                            salir = true;
                        }
                        else
                            i++;
                    }
                }
            cout<<endl<<endl;
            system("pause");
            break;
        }

        case 6:
        {
            system("cls");
            TNodo *elementos=NULL;
            TNodo *Nodo_ult=elementos;
            if(crip.leer("fichCodigos"))
            {
                crip.descifrar(123);
                for(int i=1; i<=crip.numlineas(); i++)
                {
                    TNodo *nuevo = new TNodo;
                    if(nuevo!=NULL)
                    {
                        nuevo->Siguiente = NULL;
                        linea text = crip.observar(i);
                        int num = stoi(text);
                        nuevo->Datos=num;
                        if(elementos==NULL)//1er nodo
                            elementos=nuevo;
                        else
                            Nodo_ult->Siguiente = nuevo;

                        Nodo_ult=nuevo;
                    }
                }
                //Mostrar
                TNodo *Nodo_Aux=elementos;
                while (Nodo_Aux!=NULL)
                {
                    cout<<Nodo_Aux->Datos<<endl;
                    Nodo_Aux=Nodo_Aux->Siguiente;
                }
                crip.vaciar();

            }
            else
                cout<<"No se pudo abrir el fichero\n";


            system("pause");
            break;
        }

        case 7:
        {
            system("pause");
            break;
        }

        }
    }
    while (op != 7);
    return 0;
}
