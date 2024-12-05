#include <iostream>
#include <cstring>
#include<math.h>
#include <locale.h>
#include<cstdio>

using namespace std;


struct Punto
{
    int coordX;
    int coordY;
};

class Triangulo
{
    Punto A,B,C;
    char etiqueta[30];
public:
    Triangulo();
    Triangulo(int aX,int aY,int bX,int bY, int cX, int cY, char nombre[30]);
    Triangulo(Punto aP, Punto bP, Punto cP, char nombre[30]);
    bool setVertice(char c,Punto b);
    Punto getVerticeA();
    Punto getVerticeB();
    Punto getVerticeC();
    void setEtiqueta(char nombre[30]);
    void getEtiqueta(char nombre[30]);
    double calculaCircunradio();

};

Triangulo::Triangulo()
{
    A.coordX=0;
    A.coordY=0;
    B.coordX=1;
    B.coordY=0;
    C.coordX=0;
    C.coordY=1;
}

Triangulo::Triangulo(int aX,int aY,int bX,int bY, int cX, int cY, char nombre[30])
{
    A.coordX=aX;
    A.coordY=aY;
    B.coordX=bX;
    B.coordY=bY;
    C.coordX=cX;
    C.coordY=cY;
    strcpy(etiqueta, nombre);
}

Triangulo::Triangulo(Punto aP, Punto bP, Punto cP, char nombre[30])
{
    A=aP;
    B=bP;
    C=cP;
    strcpy(etiqueta, nombre);
}

bool Triangulo::setVertice(char c,Punto p)
{
    bool hecho;
    switch(c)
    {
    case'a':
        case'A':
                A=p;
        hecho=true;
        break;
    case'b':
        case'B':
                B=p;
        hecho=true;
        break;
    case'c':
        case'C':
                C=p;
        hecho=true;
        break;
    default:
        hecho=false;
    }
    return hecho;

}

Punto Triangulo::getVerticeA()
{
    return A;
}

Punto Triangulo::getVerticeB()
{
    return B;
}

Punto Triangulo::getVerticeC()
{
    return C;
}

void Triangulo::setEtiqueta(char nombre[30])
{
    strcpy(etiqueta,nombre);
}

void Triangulo::getEtiqueta(char nombre[30])
{
    strcpy(nombre,etiqueta);
}
double calcularLado(Punto P1, Punto P2)
{
    double d;
    d=sqrt((pow(P2.coordX-P1.coordX,2))+(pow(P2.coordX-P1.coordX,2)));
    return d;
}

double Triangulo::calculaCircunradio()
{
    double R,a,b,c,s;
    a=calcularLado(B,C);
    b=calcularLado(A,C);
    c=calcularLado(A,B);
    s=(a+b+c)/2;

    R=(a*b*c)/(4*sqrt(s*(s-a)*(s-b)*(s-c)));
    return R;
}



bool buscaTriangulo(Triangulo vTriangulos[],int n, int &posicion,char nombre[30])
{
    bool encontrado=false;
    char nom[30];
    int i=0;
    while(i<n && !encontrado)
    {
        vTriangulos[i].getEtiqueta(nom);
        if(strcmp(nom,nombre)==0)
        {
            posicion=i;
            encontrado=true;
        }
        else
            i++;
    }

    if(!encontrado)
        posicion=-1;

    return encontrado;
}

void imprimirInformacionTriangulo(Triangulo t)
{
    char nom[30];
    t.getEtiqueta(nom);
    cout<<"****Datos del triangulo "<<nom<<"****"<<endl
        <<"----------------------------------------------------"<<endl
        <<"Punto A ("<<t.getVerticeA().coordX<<", "<<t.getVerticeA().coordY<<")"<<endl
        <<"Punto B ("<<t.getVerticeB().coordX<<", "<<t.getVerticeB().coordY<<")"<<endl
        <<"Punto C ("<<t.getVerticeC().coordX<<", "<<t.getVerticeC().coordY<<")"<<endl
        <<"Circunradio = "<<t.calculaCircunradio()<<endl;
}

int menu()
{
    int op;
    cout<<"\t\tMENU"<<endl
        <<"\t1. Añadir triangulo."<<endl
        <<"\t2. Modificar coordenadas triangulo."<<endl
        <<"\t3. Mostrar informacion triangulo."<<endl
        <<"\t4. Salir."<<endl<<endl
        <<"Elija opcion: ";
    cin>>op;

    return op;
}

int main()
{
    setlocale(LC_ALL, "spanish");
    Triangulo T[100];
    int nTriangulos=0;
    int op;
    do
    {
        op=menu();
        switch(op)
        {
        case 1:
        {
            char nom[30],nombre[30];
            Punto a,b,c;
            int i=0;
            bool encontrado=false;
            if(nTriangulos<100)
            {
                cout<<"Introduce un nombre para el triangulo: ";
                cin>>nom;
                while(i<nTriangulos && !encontrado)
                {
                    T[i].getEtiqueta(nombre);
                    if(strcmp(nom,nombre)==0)
                    {
                        cout<<"Error, ya existe un triangulo con dicho nombre."<<endl;
                        encontrado=true;
                    }
                    else
                        i++;
                }
                if(!encontrado||nTriangulos==0)
                {
                    nTriangulos++;
                    T[nTriangulos-1].setEtiqueta(nom);
                    cout<<"Introduce las coordenadas del Punto A"<<endl
                        <<"Coordenada X: ";
                    cin>>a.coordX;
                    cout<<"Coordenada Y: ";
                    cin>>a.coordY;
                    T[nTriangulos-1].setVertice('A',a);

                    cout<<"Introduce las coordenadas del Punto B"<<endl
                        <<"Coordenada X: ";
                    cin>>b.coordX;
                    cout<<"Coordenada Y: ";
                    cin>>b.coordY;
                    T[nTriangulos-1].setVertice('B',b);

                    cout<<"Introduce las coordenadas del Punto C"<<endl
                        <<"Coordenada X: ";
                    cin>>c.coordX;
                    cout<<"Coordenada Y: ";
                    cin>>c.coordY;
                    T[nTriangulos-1].setVertice('C',c);
                }
            }
            else
                cout<<"Limite alcanzado..."<<endl;

        }
        system("pause");
        system("cls");
        break;
        case 2:
        {
            char nom[30],nombre[30],letra;
            bool encontrado;
            int i;
            Punto p;
            cout<<"Introduce la etiqueta del triangulo a modificar: ";
            cin>>nom;
            encontrado=buscaTriangulo(T,nTriangulos,i,nom);
            if(encontrado)
            {
                do
                {
                    cout<<"Introduce el vertice a modificar (A,B,C): ";
                    cin>>letra;
                }
                while(letra!='A'&& letra!='B' && letra!= 'C');
                switch(letra)
                {
                case'A':
                    {
                        cout<<"Introduce la coordenada X: ";
                        cin>>p.coordX;
                        cout<<"\nIntroduce la coordenada Y: ";
                        cin>>p.coordY;
                        if(T[i].setVertice('A', p))
                        cout<<"Modificado con exito"<<endl;
                        else
                            cout<<"Error..."<<endl;
                        }
                        break;

            case'B':
                {
                    cout<<"Introduce la coordenada X: ";
                    cin>>p.coordX;
                    cout<<"\nIntroduce la coordenada Y: ";
                    cin>>p.coordY;
                    if(T[i].setVertice('B', p))
                        cout<<"Modificado con exito"<<endl;
                            else
                                cout<<"Error..."<<endl;
                            }
                            break;

            case'C':
                {
                    cout<<"Introduce la coordenada X: ";
                    cin>>p.coordX;
                    cout<<"\nIntroduce la coordenada Y: ";
                    cin>>p.coordY;
                    if(T[i].setVertice('C', p))
                        cout<<"Modificado con exito"<<endl;
                            else
                                cout<<"Error..."<<endl;
                            }
                            break;
            }
        }
        else
            cout<<"No existe ningun triangulo con dicha etiqueta"<<endl;
    }
    system("pause");
    system("cls");
    break;

    case 3:
    {
        system("cls");
        char nom[30];
            bool encontrado;
            int i=0;
            cout<<"Introduce la etiqueta del triangulo a modificar: ";
            cin>>nom;
            encontrado=buscaTriangulo(T,nTriangulos,i,nom);
            if(encontrado)
                imprimirInformacionTriangulo(T[i]);
            else
                cout<<"No existe ningun triangulo con dicha etiqueta"<<endl;
        }
        system("pause");
        system("cls");
        break;
        case 4:
            system("pause");
            break;
        default:
            cout<<"Tonto"<<endl;
                system("pause");
                system("cls");
        }
    }
    while(op != 4);


        return 0;
}
