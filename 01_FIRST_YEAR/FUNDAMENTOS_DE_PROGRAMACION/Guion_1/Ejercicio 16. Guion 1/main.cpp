#include <iostream>

using namespace std;

int main()
{
    int seg,seg2,minutos,minutos2,horas;
    cout << "Introduce una cantidad de segundos: ";
    cin >> seg;
    seg2 = seg%60;
    minutos = seg/60;
    minutos2 = minutos%60;
    horas = minutos/60;
    cout << "El valor de " << seg<< " segundos equivale a " << horas << " horas, " << minutos2 << " minutos y " << seg2<< " segundos."<<endl;
    return 0;
}
