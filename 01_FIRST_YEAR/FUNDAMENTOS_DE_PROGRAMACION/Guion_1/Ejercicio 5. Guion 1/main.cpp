#include <iostream>

using namespace std;

int main()
{
    float bytes;
    float resultado;
    cout << "Introduce un valor de bytes: " << endl;
    cin >> bytes;
    resultado = bytes/1024;
    cout << bytes << " bytes equivale a " << resultado << " kilobytes" << endl;
    return 0;
}
