#include <iostream>
#include <MultiConjunto.h>
#include <string>
using namespace std;

int main()
{
    MultiConjunto<int> obj1;

    if(obj1.esVacio())
        cout<<"Conjunto vacio..."<<endl;

    obj1.anade(1);
    obj1.anade(2);
    obj1.anade(3);
    obj1.ver();
    if(obj1.esVacio())
        cout<<"Conjunto vacio..."<<endl;

    cout<<"Hay "<<obj1.cardinalidad()<<" elementos"<<endl;
    obj1.elimina(2);
    if(obj1.pertenece(2))
        cout<<"EL 2 Pertenece"<<endl;
    else
        cout<<"EL 2 No Pertenece"<<endl;

    if(obj1.pertenece(3))
        cout<<"El 3 pertenece"<<endl;
    obj1.ver();
    cout<<"---------------------------------------"<<endl;

    MultiConjunto<char> obj2;

    if(obj2.esVacio())
        cout<<"Conjunto vacio..."<<endl;

    obj2.anade('A');
    obj2.anade('B');
    obj2.anade('C');
    obj2.ver();
    if(obj2.esVacio())
        cout<<"Conjunto vacio..."<<endl;

    cout<<"Hay "<<obj2.cardinalidad()<<" elementos"<<endl;
    obj2.elimina('B');
    if(obj2.pertenece('B'))
        cout<<"B Pertenece"<<endl;
    else
        cout<<"B No Pertenece"<<endl;

    if(obj2.pertenece('A'))
        cout<<"A pertenece"<<endl;
    obj2.ver();
    cout<<"---------------------------------------"<<endl;

    MultiConjunto<Persona> obj3;
    Persona A("Daniel",19), B("Pablo", 19), C("Ana", 19);
    if(obj3.esVacio())
        cout<<"Conjunto vacio..."<<endl;

    obj3.anade(A);
    obj3.anade(B);
    obj3.anade(C);
    obj3.ver();
    if(obj3.esVacio())
        cout<<"Conjunto vacio..."<<endl;

    cout<<"Hay "<<obj3.cardinalidad()<<" elementos"<<endl;
    obj3.elimina(B);
    if(obj3.pertenece(B))
        cout<<B.getNombre() << " Pertenece"<<endl;
    else
        cout<<B.getNombre() <<" No Pertenece"<<endl;

    if(obj3.pertenece(A))
        cout<<A.getNombre() << " pertenece"<<endl;
    obj3.ver();

    return 0;
}
