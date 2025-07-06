#include <iostream>
#include <cstring>
using namespace std;
#define MAX 50

typedef char cad[20];

// ----------- clase TPROD ---------------
class tprod
{
    cad nombre;
    float precio;
    int stock;
public:
    tprod();
    void cambiarnombre(cad nom);
    void cambiarprecio(float prec);
    void cambiarstock(int stoc);
    void leenombre (cad nom);
    float leeprecio ();
    int leestock();
    bool vender(int cantidad, float &total);
};

tprod::tprod()
{
    strcpy(nombre, "NO HAY PRODUCTO");
    precio = 0;
    stock = 0;
}

void tprod::cambiarnombre(cad nom)
{
    strcpy(nombre,nom);
}

void tprod::leenombre (cad nom)
{
    strcpy(nom, nombre);
}

void tprod::cambiarprecio(float prec)
{
    precio = prec;
}

float tprod::leeprecio ()
{
    return precio;
}

void tprod::cambiarstock(int stoc)
{
    stock = stoc;
}

int tprod::leestock()
{
    return stock;
}

bool tprod::vender(int cantidad, float &total)
{
    bool ok = true;
    if (stock < cantidad)
        ok = false;
    else
    {
        stock = stock - cantidad;
        total = cantidad*precio;
    }
    return ok;
}

// ---------- clase ALMACEN -------------
class almacen
{
    tprod productos[MAX];
    int nprod;
public:
    almacen();
    void vaciar();
    int existe(cad nom);
    void verprod (int pos, tprod &prod);
    int insertar(tprod P);
    void vertabla ();
    bool vender (int pos, int cant, float &total);
    bool vender (cad nom, int cant, float &total);
};

almacen::almacen()
{
   vaciar();
}

void almacen::vaciar()
{
    nprod=0;
}

int almacen::existe(cad nom)
{
    int i=0, encontrado=0;
    cad n;
    while (!encontrado && i<nprod)
    {
        productos[i].leenombre(n);
        if (strcmp(nom, n)==0)
            encontrado=1;
        else
            i++;
    }
    if (!encontrado)
        i=-1;
    return i;
}

void almacen::verprod (int pos, tprod &prod)
{
    prod = productos[pos];
}

int almacen::insertar (tprod P)
{
    int valor, lugar;
    cad nom;
    if (nprod == MAX)
       valor =2;
    else
    {
        P.leenombre(nom);
        lugar= existe(nom);
        if (lugar!= -1)
            valor =1;
        else
        {
            productos[nprod] = P;
            nprod++;
            valor =0;
        }
    }
    return valor;
}

void almacen::vertabla ()
{
    tprod p;
    cad nom;
    float prec;
    int st;
    cout << "******* Contenido del almacen *******\n";
    if (nprod ==0)
        cout << "EL ALMACEN ESTA VACIO ****\n";
    else
    {
        cout << "Numero\t" << "Nombre\t\t" << "Precio\t" << "Stock\n";
        for (int i=0; i < nprod; i++)
        {
            cout << i+1 << "\t";
            verprod(i,p);
            p.leenombre(nom);
            cout << nom << "\t\t";
            prec = p.leeprecio();
            cout << prec << "\t";
            st = p.leestock();
            cout << st << "\n";
        }
    }
}


bool almacen::vender (cad nom, int cant, float &total)
{
    bool ok=false;
    int lugar= existe(nom);
    if (lugar!=-1)
      ok=vender(lugar,cant,total);

    return ok;

}

bool almacen::vender (int pos, int cant, float &total)
{
    bool ok;
    ok = productos[pos].vender(cant, total);
    return ok;
}

char menu ()
{
    char opc;

    cout << " ************* MENU ******************\n";
    cout << " *******A.- Visualizar la tabla. *****\n";
    cout << " *******B.- Insertar un producto.*****\n";
    cout << " *******C.- Vender un producto. *****\n";
    cout << " *******D.- Vaciar el almacén.   *****\n";
    cout << " *******E.- Salir.               *****\n";
    cout << " *************************************\n";
    cout << " Pon la opción que deseas:\n";
    cin >> opc;
    return opc;
}

int main ()
{
    almacen a;
    tprod p;
    cad nom;
    float prec;
    int sto, cant, Pos, Estado;
    char opcion;
    bool ok;
    do
    {
        opcion = menu();
        switch(opcion)
        {
        case 'a' :
        case 'A' :
            a.vertabla();
            break;
        case 'b' :
        case 'B' :
            cout << "Pon el nombre del producto: ";
            cin >> nom;
            p.cambiarnombre(nom);
            cout << "Pon el precio del producto: ";
            cin >> prec;
            p.cambiarprecio(prec);
            cout << "Pon el stock del producto: ";
            cin >> sto;
            p.cambiarstock(sto);
            Estado =a.insertar(p);
            if (Estado==2)
                cout <<"El almacén está lleno, no se puede insertar más productos\n";
            else
                if (Estado==1)
                    cout <<"Ya existe un producto con ese nombre\n";
                else
                    cout <<"Insertado el producto correctamente\n";
            break;
        case 'c' :
        case 'C' :
            cout << "Pon el nombre del producto: ";
            cin >> nom;
            Pos = a.existe(nom);
            if (Pos==-1)
                cout << "Ese producto NO existe en el almacén\n";
            else
            {
                cout << "cantidad a vender: ";
                cin >> cant;
                ok = a.vender(Pos, cant, prec);
                if (ok)
                    cout << "El total a pagar es de " << prec << " euros.";
                else
                    cout << "No se ha podido realizar la venta por falta de stock.";
            }
            break;
        case 'd' :
        case 'D' :
            a.vaciar();
            break;
        case 'e' :
        case 'E' :
            cout <<"Gracias por utilizar este maravilloso programa\n";
            break;
        default :
            cout << "ERROR EN LA OPCION";
        }
        cout <<"\n";
        system ("pause");
        system("cls");
    }
    while ( opcion != 'E' && opcion != 'e');
    return 0;
}
