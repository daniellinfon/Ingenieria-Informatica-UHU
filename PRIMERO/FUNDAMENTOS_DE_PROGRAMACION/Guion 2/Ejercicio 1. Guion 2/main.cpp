#include <iostream>

using namespace std;

int main()
{
    int nota;
    cout << "Introduce tu nota: ";
    cin >> nota;

    if(nota<0)
        cout<< "Error";
        else if(nota<5)
        cout<<"Suspenso";
        else if(nota<7)
            cout<<"Aprobado";
        else if(nota<9)
            cout <<"Notable";
        else if(nota<11)
            cout<<"Sobresaliente";
    else
        cout<<"Error";



    return 0;
}
