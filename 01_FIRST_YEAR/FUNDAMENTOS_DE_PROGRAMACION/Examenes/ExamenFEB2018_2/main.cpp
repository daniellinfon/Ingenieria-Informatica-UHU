#include <iostream>

using namespace std;
typedef char cad[20];

class alumno
{
    /* Datos de un alumno*/
    cad nombre, direc;
    int edad;
public:
    void nombre(cad nom);
    /*Devuelve en el par�metro nom el nombre del alumno */
    int leeredad();
    /* Devuelve los a�os que tiene el alumno */
    void direccion (cad dir);
    /*Devuelve en el par�metro dir la direcci�n donde vive el alumno*/
};

class asignatura
{
    /* Alumnos matriculados en una asignatura*/
    cad nomasig; /*Nombre de la asignatura */
    alumno talumnos[200]; /*alumnos matriculados */
    int nalumnos; /*Numero de alumnos matriculados desde la posici�n 0 hasta la
posici�n nalumnos-1 */
public:
    int leern();
    /* Devuelve el n�mero de alumnos matriculados en esa asignatura, si hay cinco estar�n en las
    cinco primeras posiciones de talumnos */
    void leeralumno (int i, alumno &a);
    /* Dada la posici�n i devuelve en a el alumno que se encuentra en esa posici�n*/
    void leernomasig (cad n);
    /*Devuelve en n el nombre de la asignatura*/
};

class grado
{
    /* Asignaturas que forman parte de ese grado*/
    asignatura tasignaturas[60];
    int nasignaturas; /* Si hay 5 asignaturas estar�n desde la posici�n 0 hasta la posici�n 4 de
la tabla tasignaturas */
public:
    void asignaturasalumno (cad nomalum);
    /* Dado el nombre de un alumno mostrar� por pantalla el nombre de las asignaturas en las que
    est� matriculado*/
}

void asignaturasalumno (cad nomalum)
{
    int n,j;
    alumno d;
    bool encontrar = false;
    cout<<"Introduce el nombre de un alumno: "
        cin>> nomalum;

    for(int i=0; i<nasignaturas; i++)
    {
        n= tasignaturas[i].leern();
        j=0;
        while ( (j<n) && (!enc) )
        {
            tasignaturas[i].leeralumno(j, d); /*Obtengo el alumno*/
            d.nombre(nom); /*Obtengo su nombre*/
            if ( (strcmp(nom, nomalumn)==0)
                    encontrado=true;
                    else j++;
            }
    if (encontrado==true)
        {
            /* Est� ese alumno matriculado en esa asignatura*/
            tasignaturas][i].leernomasig (nomasig);
            /* Obtengo el nombre de la asignatura*/
            cout << nomasig;
        }
    }
}
