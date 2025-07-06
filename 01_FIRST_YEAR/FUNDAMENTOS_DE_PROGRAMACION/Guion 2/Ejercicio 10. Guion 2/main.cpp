#include <iostream>

using namespace std;

int main()
{
    int num;
    long fact=1;
    do {
        cout << "Introduce un número entero positivo y menor que 20: ";
        cin>> num;
    }while (num<0 || num>20);


    for(int i=1;i<=num ;i++)
        fact=fact*i; // fact *= i;
    cout << "El numero factorial es " <<fact<<endl;
    return 0;
}
