#include <iostream>

using namespace std;

int main()
{

    float a,b,resultado;
    char operacion;
    cout << "Introduce dos numeros enteros: ";
    cin >> a>>b;
    do {
    cout << "*******MENU*******"<<endl;
    cout << "\t A. Sumar "<<endl;
    cout << "\t B. Restar "<<endl;
    cout << "\t C. Multiplicar "<<endl;
    cout << "\t D. Dividir "<<endl;
    cout << "\t E. Salir"<<endl;

    cout << "Elija una opcion "<<endl;
    cin >> operacion;

    switch (operacion){

    case 'A': resultado= a+b;
    cout << "Resultado igual a: "<<resultado<<endl;
            break;
    case 'B': resultado= a-b;
    cout << "Resultado igual a: "<<resultado<<endl;
            break;
    case 'C': resultado= a*b;
    cout << "Resultado igual a: "<<resultado<<endl;
            break;
    case 'D': resultado= a/b;
    cout << "Resultado igual a: "<<resultado<<endl;
            break;

    case 'E': break;
    default: cout << "Operacion erronea "<<endl;
        }
     }while(operacion!='E');
    return 0;
}

