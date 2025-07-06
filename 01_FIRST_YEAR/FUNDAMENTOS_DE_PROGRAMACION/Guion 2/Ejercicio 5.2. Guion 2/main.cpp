#include <iostream>

using namespace std;

int main()
{
    int ini,fin;
    do{
    cout << "Introduce el numero de la tabla inicial y de la final (del 1 al 10): ";
    cin>> ini>>fin;
    if(ini>=1 && ini<11){
            if( fin>=1 && fin<11){
                if (ini>fin){

                        long temp=ini;
                        ini=fin;
                        fin=temp;


                    }
            }else
        cout<<"Error,vuelve a intentarlo"<<endl;
    }else
            cout<<"Error,vuelve a intentarlo"<<endl;
    }while(ini<1 || ini>10 || fin<1 || fin>10);

    for(int n=1; n<11; n++) {
    for(int i=ini; i<=fin; i++){

        cout << i << " x " << n << " = " <<i*n
        <<"\t";

       } cout<<endl;
    }
    return 0;
}
