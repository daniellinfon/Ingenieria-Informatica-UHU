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
    /*Devuelve en el parámetro nom el nombre del alumno */
    int leeredad();
    /* Devuelve los años que tiene el alumno */
    void direccion (cad dir);
    /*Devuelve en el parámetro dir la dirección donde vive el alumno*/
};

class asignatura
{
    /* Alumnos matriculados en una asignatura*/
    cad nomasig; /*Nombre de la asignatura */
    alumno talumnos[200]; /*alumnos matriculados */
    int nalumnos; /*Numero de alumnos matriculados desde la posición 0 hasta la
posición nalumnos-1 */
public:
    int leern();
    /* Devuelve el número de alumnos matriculados en esa asignatura, si hay cinco estarán en las
    cinco primeras posiciones de talumnos */
    void leeralumno (int i, alumno &a);
    /* Dada la posición i devuelve en a el alumno que se encuentra en esa posición*/
    void leernomasig (cad n);
    /*Devuelve en n el nombre de la asignatura*/
};

class grado
{
    /* Asignaturas que forman parte de ese grado*/
    asignatura tasignaturas[60];
    int nasignaturas; /* Si hay 5 asignaturas estarán desde la posición 0 hasta la posición 4 de
la tabla tasignaturas */
public:
    void asignaturasalumno (cad nomalum);
    /* Dado el nombre de un alumno mostrará por pantalla el nombre de las asignaturas en las que
    está matriculado*/
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
            /* Está ese alumno matriculado en esa asignatura*/
            tasignaturas][i].leernomasig (nomasig);
            /* Obtengo el nombre de la asignatura*/
            cout << nomasig;
        }
    }
}
