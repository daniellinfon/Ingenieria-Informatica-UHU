#include <iostream>

using namespace std;

int main()
{
    int num,i;
    cout << "Introduce un numero entero: ";
    cin>> num;

        cout<< "Los divisores de " <<num<< " son: ";
        for(int i=1; i<=num; i++){
                if (num%i==0){
                    cout<<i;
                    if (i<num){
                        cout<<",";
                        }
                    }
                }




    return 0;
}
