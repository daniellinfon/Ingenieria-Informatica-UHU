#include <iostream>
#include <fstream>
#include <string>

using namespace std;

int main() {
    ofstream file("fichero3.prg");
    file << "2 0000004f" << endl; //B4
    file << "3 00000096" << endl; //B9
    file << "0 000000a1" << endl; //B10
    file << "0 000000f7" << endl; //B15
    file << "2 000000f7" << endl; //B15
    file << "3 000000a1" << endl; //B10
    file << "0 00000096" << endl; //B9
    file << "2 0000004f" << endl; //B4
    for (int i = 1; i <= 99; i++) {
        file << "2 0000021a" << endl; //B33
        file << "0 000000f7" << endl; //B15
        file << "0 000000a1" << endl; //B10
        file << "3 00000096" << endl; //B9
        file << "2 0000004f" << endl; //B4
    }
    file.close();
    return 0;
}
