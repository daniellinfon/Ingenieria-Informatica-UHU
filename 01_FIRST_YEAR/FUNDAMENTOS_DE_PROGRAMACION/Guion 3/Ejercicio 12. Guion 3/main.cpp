#include <iostream>
#include <cstring>

using namespace std;

typedef char cadena[150];
int main()
{
    cadena nombre,nombre1,nombre2,apellido1,apellido2,nom_completo;
    bool nombres=false;

    cout<< "Introduce el nombre de la persona: ";
    cin.getline(nombre, 150);
    cout<< "Introduce el primer apellido: ";
    cin.getline(apellido1, 150);
    cout<< "Introduce el segundo apellido: ";
    cin.getline(apellido2, 150);

    int len= strlen(nombre);
    int i = 0;
    for(int j=0; j<=len; j++)
    {

        if(j==0)
        {
            nombre1[i]=nombre[j];
        }
        if(nombre[j]==' ')
        {
            j=j+1;
            nombre2[i]=nombre[j];
            i++;
            nombres=true;
        }
    }

    nombre2[i] = '\0';


    strcpy (nom_completo, apellido1);
    strcat (nom_completo, " ");
    strcat (nom_completo, apellido2);
    strcat (nom_completo, ", ");
    strcat (nom_completo, nombre1);
    strcat (nom_completo, ".");

    if(nombres){
    strcat (nom_completo, nombre2);
    strcat (nom_completo, ".");
    }

    cout<<endl<<"El nombre completo es "<<nom_completo;

    return 0;
}
