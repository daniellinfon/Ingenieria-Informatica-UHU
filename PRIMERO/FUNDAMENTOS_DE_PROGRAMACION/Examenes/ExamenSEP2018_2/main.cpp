#include <iostream>

using namespace std;

struct VIVIENDA
{
    float precio; /* Precio de adquisición de la vivienda*/
    char dni[10]; /* DNI del propietario de la vivienda */
    char nombre[30]; /* Nombre del propietario de la vivienda*/
};

class PLANTA
{
    VIVIENDA viviendas[10];
    int nc; /* Número de viviendas almacenadas en la tabla desde la posición 0 hasta nc-1 */
public:
    int num_viviendas();
    /* Número de viviendas almacenadas*/
    VIVIENDA devolver_vivienda (int n);
    /* Devuelve la vivienda que está en la posición n de la tabla vivendas */
    void agregar (VIVIENDA c);
    /* Añade la vivienda c en la tabla viviendas*/
};

class EDIFICIO
{
    PLANTA plantas[20];
    int np; /*Número de plantas almacenadas en la tabla desde la posición 0 hasta np-1 */
public:
    int num_plantas();
    /*Número de plantas almacenadas*/
    PLANTA devolver_planta (int n);
    /* Devuelve la PLANTA almacenada en la posición n de la tabla plantas*/
    void cargar();
    /* Carga desde teclado los datos del edificio*/
};

int main()
{
    EDIFICIO x;
    x.cargar();
    int np, nv, pmax, vmax;
    PLANTA p;
    float maximo;
    VIVIENDA v;
    np = x.num_plantas();
    for (int i=0; i < np; i++)  /* Para cada planta del edificio*/
    {
        p= x.devolver_planta(i);
        nv = p.num_viviendas();
        for (int j=0; j < nv; j++)   /* Para cada vivienda de la planta*/
        {
            v = p.devolver_vivienda (j);
            if ( (i==0) && (j==0) )   /* Si es la primera vivienda es diferente*/
            {
                maximo= v.precio;
                pmax=0; /* Número de planta*/
                vmax= 0; /* Número de vivienda */
            }
            else if (v.precio > maximo)
            {
                maximo= v.precio;
                pmax = i;
                vmax =j;
            }
        }
    }
    p = x.devolver_planta(pmax);
    v= p.devolver_vivienda(vmax);
    cout << v.dni << " " << v.nombre;


    return 0;
}
