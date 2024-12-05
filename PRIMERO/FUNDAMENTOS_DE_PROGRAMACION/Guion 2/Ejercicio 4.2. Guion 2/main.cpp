#include <iostream>

using namespace std;

int main()
{
    int num,n;
    cout << "Vamos a calcular la tabla de multiplicar de un numero" << endl;
    do{
    cout<< "Introduce un numero: ";
    cin >> num;

    if (num>=1 && num<11){
            int n = 1;

    while (n < 11) {

        cout << num << " x " << n << " = " << num*n << "\n";
        n++;
    }

    }else
    cout << "Error,reintentelo"<<endl;



    }while(num<1 || num>10);
    return 0;
}
