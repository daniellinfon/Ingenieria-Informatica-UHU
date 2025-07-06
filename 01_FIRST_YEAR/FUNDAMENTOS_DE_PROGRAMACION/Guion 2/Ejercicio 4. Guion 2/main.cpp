#include <iostream>

using namespace std;

int main()
{
    float a,b,resultado;
    int operacion;
    cout << "Introduce dos numeros enteros: ";
    cin >> a>>b;
    cout << "*******MENU*******"<<endl;
    cout << "\t 1. Sumar "<<endl;
    cout << "\t 2. Restar "<<endl;
    cout << "\t 3. Multiplicar "<<endl;
    cout << "\t 4. Dividir "<<endl;

    cout << "Elija una opcion "<<endl;
    cin >> operacion;
    switch (operacion){
    case 1: resultado= a+b;
    cout << "Resultado igual a: "<<resultado;
            break;
    case 2: resultado= a-b;
    cout << "Resultado igual a: "<<resultado;
            break;
    case 3: resultado= a*b;
    cout << "Resultado igual a: "<<resultado;
            break;
    case 4: resultado= a/b;
    cout << "Resultado igual a: "<<resultado;
            break;
    default: cout << "Operacion erronea ";
            resultado=0;

    }

    return 0;
}
