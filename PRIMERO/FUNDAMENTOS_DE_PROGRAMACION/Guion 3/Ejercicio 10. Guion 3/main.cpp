#include <iostream>
#include <cstring>
using namespace std;

typedef char cadena[150];
int main()
{
    cadena nombre,apellido1,apellido2,nom_completo;

    cout << "Introduce el nombre de la persona: ";
    cin.getline(nombre, 150);
    cout<< "Introduce el primer apellido: ";
    cin.getline(apellido1, 150);
    cout<< "Introduce el segundo apellido: ";
    cin.getline(apellido2, 150);

    strcpy (nom_completo, nombre);
    strcat (nom_completo, " ");
    strcat (nom_completo, apellido1);
    strcat (nom_completo, " ");
    strcat (nom_completo, apellido2);

    cout<< "El nombre completo de la persona es " <<nom_completo<<endl;

    return 0;
}
