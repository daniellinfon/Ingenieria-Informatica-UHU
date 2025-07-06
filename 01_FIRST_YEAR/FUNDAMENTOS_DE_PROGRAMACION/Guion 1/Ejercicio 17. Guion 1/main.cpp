#include <iostream>

using namespace std;

int main()
{
    float longitud_en_metros= 15000;
    float precio_total_en_euros;
    precio_total_en_euros = 5000 * (longitud_en_metros / 5280.0);
    cout << "Para arreglar la carretera entre dos pueblos de su provincia de 15000 metros cuesta " << precio_total_en_euros<< " euros" <<endl;
    return 0;
}
