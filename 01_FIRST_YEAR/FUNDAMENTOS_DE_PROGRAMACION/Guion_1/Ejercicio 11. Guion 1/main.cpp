#include <iostream>

using namespace std;

int main()
{
    float euro, dolar, libra;
    cout << "Indroduce una cantidad de €: " << endl;
    cin >> euro;
    dolar = euro*1.16;
    libra = euro*0.84655857;
    cout << euro << "€ son " << dolar << " $ " <<endl;
    cout << euro << "€ son " << libra << " £ " <<endl;
    return 0;
}
