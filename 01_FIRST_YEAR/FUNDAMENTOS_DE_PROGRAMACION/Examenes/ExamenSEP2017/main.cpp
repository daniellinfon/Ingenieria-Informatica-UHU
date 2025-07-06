#include <iostream>
#include <string.h>
using namespace std;
typedef char cadena[30];
struct producto
{
    cadena nomprod; //Nombre del producto
    int precio; //Precio por unidad del producto
};
struct seccion
{
    producto prods[40];
    int nprod; //Número de productos almacenados en prods
//desde prods[0] a prods[np
};
class empresa
{
    cadena nomempresa; //Nombre de la empresa;
    seccion sec[5];
    int nsec; //Número de secciones almacenadas en sec
//desde sec[0] hasta sec[nsec
public:
    empresa();
//Pedirá por teclado el nombre de la empresa y
//Para cada sección anterior pedirá cuantos productos tiene y para cada
//producto su nombre y el precio unitario, guardando todos estos valores en el objeto
    bool insertar (int ns, producto prod);
//Trata de añadir el producto
//Comprobará si ya existe un producto almacenado en cualquier sección
//de la empresa con el mismo nombre que tiene
//Si ya existía devolverá false y no lo insertará.
//Si no existía, comprobará si la tabla
//Si está llena devolverá false no añadiéndolo, en caso contrario
//si hay hueco lo insertará devolviendo true.
    void numerosecciones(int &n);
//Devuelve en n el número de secciones que hay en la empresa
    void listar(); //No hay que implementarlo
//Muestra por pantalla todos los datos de la empresa
};

empresa::empresa()
{
    cout << "Introduce el nombre de la empresa: ";
    cin.getline(nomempresa,30);

    do
    {

        cout << "Introduce el numero de secciones de la empresa: ";
        cin >> nsec;
        if(nsec<0||nsec>5)
            cout<<"Error,intentelo de nuevo."<<endl;
    }
    while(nsec<0 || nsec>5);

    for (int i=0; i < nsec; i++)
    {
        cout << "Cuantos productos tiene la seccion " << i+1 << ": ";
        cin >> sec[i].nprod;

        int pProd = sec[i].nprod;

        for (int i=0; i < pProd; i++)
        {
            cout << "Introduce el nombre del producto " << i+1 << ": ";
            cin >> sec[i].prods[i].nomprod;

            cout << "Introduce el precio del producto " << i+1 << ": ";
            cin >> sec[i].prods[i].precio;
        }
    }
}

bool empresa::insertar (int ns, producto prod)
{
    bool encontrado=false, devuelto, tablallena=false;
    int cantidad, i=0, j=0;

    while( ( i < nsec ) && ( !encontrado) )
    {
        j = 0 ;
        while( ( j < sec[i].nprod ) && (!encontrado ) )
        {
            if ( strcmp ( sec[i].prods[j].nomprod, prod.nomprod ) == 0 )
            encontrado = true ;
            else j++ ;
        }
        i++ ;
    }

    if(!encontrado)
    {
        if(sec[ns].nprod == 40)
            tablallena=true;
        else
        {
            cantidad=sec[ns].nprod;
            sec[ns].prods[cantidad]=prod;
            sec[ns].nprod++;
        }
    }

    if(encontrado==true)
        devuelto=false;
    else if(tablallena==true)
        devuelto=false;
    else
        devuelto=true;
    return devuelto;
}

void empresa::numerosecciones(int &n)
{
    n=nsec;
}

void empresa::listar()
{
    for(int i=0; i<nsec; i++)
    {
        cout<<"Seccion N "<<i<<endl;
        for(int j=0; j<sec[i].nprod;j++)
        {
            cout<<"Producto N " <<sec[i].prods[j].precio<<endl;
        }
    }
}
int main()
{
    empresa emp;
    producto p;
    int num,numsec;
    bool insertado;

    cout<<"Introduce el nombre del producto a insertar: ";
    cin>>p.nomprod;
    cout<<"Introduce el precio por unidad del producto: ";
    cin>>p.precio;

    emp.numerosecciones(numsec);
    do{
        cout<<"Pon el numero de la seccion: ";
        cin>>num;
    }while((num<0) || (num>=numsec));

    emp.listar();
    insertado=emp.insertar(num,p);
    if(insertado=true)
        cout<<"Se ha insertado correctamente";
    else
        cout<<"Error, no se ha insertado";
    emp.listar();


    return 0;
}
