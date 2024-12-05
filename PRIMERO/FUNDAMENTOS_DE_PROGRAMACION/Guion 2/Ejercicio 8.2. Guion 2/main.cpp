#include <iostream>

using namespace std;

int main()
{
    int i,num,div=0;
    cout << "Introduce un numero entero: ";
    cin >> num;

    for(i=1; i<=num; i++) {
        if(num%i==0){
                div++;
        }
    }
    if(div>2) {
        cout << "El numero no es primo"<<endl;
    }
    else
        cout << "El numero es primo"<<endl;
    return 0;
}


