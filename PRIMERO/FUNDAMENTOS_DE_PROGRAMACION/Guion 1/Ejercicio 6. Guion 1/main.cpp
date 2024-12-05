#include <iostream>

using namespace std;

int main()
{
    float teoria,practico,resultado;


    cout << "Introduce tu nota del examen de teoria: " << endl;
    cin >> teoria;
    if(teoria >=0 && teoria <=10) {
        cout << "Introduce tu nota del examen practico: " << endl;
        cin >> practico;
         if(practico >=0 && practico <=10) {
                resultado = (teoria*0.4)+(practico*0.6);
                cout << "La nota final es " << resultado;
         }
         else
            cout << "ERROR";
    }
    else
        cout << "ERROR";




    return 0;
}
