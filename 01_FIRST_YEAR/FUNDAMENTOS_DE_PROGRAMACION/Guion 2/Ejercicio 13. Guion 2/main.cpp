#include <iostream>

using namespace std;

int main()
{
    int n,x=0,y=1,z=1;
    do{
    cout << "Indica la cantidad de terminos de la sucesion de Fibonacci: ";
    cin >>n;

    if (n<0){
        cout<<"Error,vuelve a intentarlo"<<endl;
    }
   } while(n<0);
cout <<"0 ";
    for(int i=2; i<n; i++){
        z = x+y;

        cout <<z<<" ";
        x=y;
        y=z;
    }


    return 0;
}
