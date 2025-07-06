#include <iostream>
#include <cstring>

#define N 60

using namespace std;
typedef char cadena [50];

struct Alumno
{
    int DNI;
    cadena NomyApe;
    float nota;
};

class Convocatoria
{
    Alumno Acta[N];
    int numalumnos;
public:
    Convocatoria(int nAlum);
    int getNumAlum();
    bool ActaCompleta(int maximo);
    Alumno Consultar(int pos);
    void insertar(Alumno a);
    int buscar (int dni);
    void eliminar(int pos);

};

Convocatoria::Convocatoria(int nAlum)
{
    numalumnos=nAlum;
    for(int i=0; i<numalumnos; i++)
    {
        Acta[i].DNI=0;
        Acta[i].nota=0;
    }

}

int Convocatoria::getNumAlum()
{
    return numalumnos;
}

bool Convocatoria::ActaCompleta(int maximo)
{
    bool completo;
    if(numalumnos<=maximo)
        completo=false;
    else
        completo=true;
    return completo;
}

Alumno Convocatoria::Consultar(int pos)
{
    return Acta[pos];
}

void Convocatoria::insertar(Alumno a)
{
    bool parar=false;
    int x=0;
    int i=0;
    int j=0;
    if(numalumnos==0)
    {
        Acta[numalumnos]=a;
        numalumnos++;
    }
    else
    {
        while(x<numalumnos&&!parar)
        {
            if(a.NomyApe[i]<Acta[x].NomyApe[j])
            {

                for(int z=x; z<numalumnos; z++)
                    Acta[z+1]=Acta[z];
                Acta[x]=a;
                parar=true;
                cout<<"Alumno insertado."<<endl;
            }
            else if (a.NomyApe[i]>Acta[x].NomyApe[j])
            {
                x++;
		i=0;
                j=0;

            }
            else //si son iguales
            {
                i++;
                j++;
            }
            if(x==numalumnos)
                Acta[x]=a;

        }
        numalumnos++;
    }
}

int Convocatoria::buscar(int dni)
{
    bool encontrado=false;
    int i=0, pos;
    while(i<numalumnos&&!encontrado)
    {
        if(Acta[i].DNI==dni)
        {
            pos=i;
            encontrado=true;
        }
        else
            i++;
    }
    if(!encontrado)
        pos=-1;
    return pos;
}

void Convocatoria::eliminar(int pos)
{
    for(int i=pos; i<numalumnos; i++)
        Acta[i]=Acta[i+1];
    numalumnos--;
}

void mejoresNotas(Convocatoria C,int &dni1, int &dni2, int &dni3)
{
    int notaMax;
    Alumno aux1,aux2;
    for(int i=0; i<3; i++)
    {
        notaMax=0;
        for(int j=1; j<=C.getNumAlum(); j++)
        {
            if(C.Consultar(j).nota>C.Consultar(notaMax).nota)
                notaMax=j;
        }
        if(i==0)
        {
            dni1=C.Consultar(notaMax).DNI;
            aux1=C.Consultar(notaMax);
            C.eliminar(notaMax);
        }
        else if(i==1)
        {
            dni2=C.Consultar(notaMax).DNI;
            aux2=C.Consultar(notaMax);
            C.eliminar(notaMax);
        }
        else
            dni3=C.Consultar(notaMax).DNI;
    }
    C.insertar(aux1);
    C.insertar(aux2);

}

void Menu(int &op, int nAlum)
{
    cout<<"Acta de convocatoria"<<endl;
    cout<<"__________________________________"<<endl;
    cout<<"Alumnos: " <<nAlum<<endl;

    cout<<"\t\t1.Listado de los alumnos en el acta."<<endl
        <<"\t\t2.Anadir alumno."<<endl
        <<"\t\t3.Eliminar alumno."<<endl
        <<"\t\t4.Consultar mejores alumnos"<<endl
        <<"\t\t5.Salir"<<endl;
    cout<<"Elija opcion: ";
    cin>>op;
}

int main()
{


    int op,nAlum=0;
    Convocatoria C(nAlum);
    do

    {
        Menu(op,C.getNumAlum());
        switch(op)
        {
        case 1:
        {
            if(C.getNumAlum()==0)
                cout<<"No hay alumnos"<<endl;
            else
            {
                cout<<"Nombre\t\tDNI\t\tNota"<<endl;
                for(int i=0; i<C.getNumAlum(); i++)
                    cout<<C.Consultar(i).NomyApe<<"\t\t"<<C.Consultar(i).DNI<<"\t\t"<<C.Consultar(i).nota<<endl;
            }
        }
        system("pause");
        system("cls");
        break;
        case 2:
        {

            Alumno A;
            float nota;
            cadena nom;
            if(C.ActaCompleta(N))
                cout<<"Acta completa"<<endl;
            else
            {
                cout<<"Introduce el DNI del nuevo alumno: ";
                cin>>A.DNI;
                if(C.buscar(A.DNI)!=-1)
                    cout<<"Ya existe alumno con dicho dni"<<endl;
                else
                {
                    cout<<"Introduce nombre y apellidos: ";
                    cin>>nom;
                    strcpy(A.NomyApe,nom);
                    cout<<"Introduce su nota: ";
                    cin>>A.nota;
                    C.insertar(A);

                }
            }
        }
        system("pause");
        system("cls");
        break;
        case 3:
        {
            int DNI;
            if(C.getNumAlum()==0)
                cout<<"No hay alumnos"<<endl;
            else
            {
                cout<<"Introduce el DNI del alumno a eliminar: ";
                cin>>DNI;
                if(C.buscar(DNI)==-1)
                    cout<<"No existe alumno con dicho dni"<<endl;
                else
                    C.eliminar(C.buscar(DNI));
            }
        }
        system("pause");
        system("cls");
        break;
        case 4:
        {
            int dni1,dni2,dni3;
            Alumno a1,a2,a3;
            if(C.getNumAlum()==0)
                cout<<"No hay alumnos suficientes"<<endl;
            else
            {
                mejoresNotas(C,dni1,dni2,dni3);
                a1=C.Consultar(C.buscar(dni1));
                a2=C.Consultar(C.buscar(dni2));
                a3=C.Consultar(C.buscar(dni3));
                cout<<"Nombre\t\tDNI\t\tNota"<<endl;
                if(C.getNumAlum()>=1)
                cout<<"1. "<<a1.NomyApe<<"\t"<<a1.DNI<<"\t"<<a1.nota<<endl;
                if(C.getNumAlum()>=2)
                cout<<"2. "<<a2.NomyApe<<"\t"<<a2.DNI<<"\t"<<a2.nota<<endl;
                if(C.getNumAlum()>=3)
                cout<<"3. "<<a3.NomyApe<<"\t"<<a3.DNI<<"\t"<<a3.nota<<endl;
            }

        }
        system("pause");
        system("cls");
        break;
        case 5:
            system("pause");
            break;
        default:
            cout<<"Error..."<<endl;
            system("pause");
            system("cls");

        }
    }
    while(op!=5);
    return 0;
}
