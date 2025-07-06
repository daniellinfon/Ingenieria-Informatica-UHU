#include <iostream>

using namespace std;

int main()
{
    float a,b,resultado;
    char operacion;
    cout << "Introduce 2 numeros: ";
    cin >> a>>b;
    cout << "*******MENU*******"<<endl;
    cout << "\t Pulse '+','s' o 'S' para sumar "<<endl;
    cout << "\t Pulse '-','r' o 'R' para restar "<<endl;

    cout << "Elija una opcion "<<endl;
    cin >> operacion;
    switch (operacion){
    case '+': resultado= a+b;
    cout << "Resultado igual a: "<<resultado;
            break;
    case 's': resultado= a+b;
    cout << "Resultado igual a: "<<resultado;
            break;
    case 'S': resultado= a+b;
    cout << "Resultado igual a: "<<resultado;
            break;
    case '-': resultado= a-b;
    cout << "Resultado igual a: "<<resultado;
            break;
    case 'r': resultado= a-b;
    cout << "Resultado igual a: "<<resultado;
            break;
    case 'R': resultado= a-b;
    cout << "Resultado igual a: "<<resultado;
            break;
    default: cout << "Operacion erronea ";
    }
    return 0;
}
