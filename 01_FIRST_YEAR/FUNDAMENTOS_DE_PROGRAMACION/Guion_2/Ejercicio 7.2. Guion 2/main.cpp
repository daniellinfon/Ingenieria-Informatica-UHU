#include <iostream>

using namespace std;
int main ()
{
    int num,S=0;
    do{
        cout << "Introduce un numero entero: ";
        cin >> num;
        if (num<0)
            cout<<"Error, vuelve a intentarlo"<<endl;
    }while (num<0);

    for(int div=1; div <= num; div++){
        if (num%div==0)
            S=S+div;
    }

    if (S>num*2)
        cout<<"El numero "<<num<< " es abundante";
    if (S<=num*2)
        cout<<"El numero "<<num<< " no es abundante";

    return 0;
}
