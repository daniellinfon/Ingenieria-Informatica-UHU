#include <iostream>
#include <cstring>

using namespace std;
typedef char Cadena[50]; // Tipo de datos Cadena
#define MAX_CUENTAS 100 // Número de Cuentas
#define MAX_CLIENTES 100 // Número de clientes

class Cuenta //Contiene los datos de una cuenta bancaria
{
    float Saldo; // Saldo de la cuenta
    int NoCuenta; // Número de la cuenta
    bool Bloqueada; // true si está bloqueada
public:
    Cuenta();
    Cuenta(int pNo, float pSal);
    bool ActualizarSaldo(int pSal);
    void ActualizarBloqueo(bool pBloq);
    float DameSaldo();
    int DameNoCuenta();
    bool EstaBloqueada();
};

Cuenta::Cuenta()
{
    Saldo=0;
    NoCuenta=0;
    Bloqueada=false;
}

Cuenta::Cuenta(int pNo, float pSal)
{
    Saldo=pSal;
    NoCuenta=pNo;
    Bloqueada=false;
}

bool Cuenta::ActualizarSaldo(int pSal)
{
    bool saldoact= false;
    if(Bloqueada==false)
    {
        Saldo=pSal;
        saldoact=true;
    }

    return saldoact;

}

void Cuenta::ActualizarBloqueo(bool pBloq)
{
    Bloqueada=pBloq;
}

float Cuenta::DameSaldo()
{
    return Saldo;
}

int Cuenta::DameNoCuenta()
{
    return NoCuenta;
}
bool Cuenta::EstaBloqueada()
{
    return Bloqueada;
}

int BuscarCuenta(Cuenta Ctas[MAX_CUENTAS], int NCuentas, int NoCuenta)
{
    int i=0;
    bool encontrado=false;
    while(i<NCuentas && !encontrado)
    {
        if(Ctas[i].DameNoCuenta()==NoCuenta)
        {
            encontrado=true;
            return i;
        }
        else
            i++;

    }

    if(!encontrado)
        return -1;
}

int MenuCuentas()
{
    int op;
    do
    {
        cout << "\n--- Menu Gestion de Cuentas ---\n" <<
             "1. Anadir una cuenta a un cliente\n" <<
             "2. Mostrar las cuentas del cliente\n" <<
             "3. Borrar una cuenta del cliente\n" <<
             "4. Modificar saldo de una cuenta\n" <<
             "5. Modificar estado de una cuenta\n" <<
             "6. Salir\n" <<
             "\nElige Opcion: ";

        cin >> op;

        if (op < 1 || op > 6)
        {
            cout << "\nOpcion no valida.\n";
            system("pause");
        }

        system("cls");
    }
    while (op < 1 || op > 6);

    return op;
}

class Cliente
{
    Cadena Nombre; // Nombre y dirección
    Cadena Direccion;
    Cuenta Cuentas[MAX_CUENTAS]; // cuentas corrientes
    int NoCuentas; // Nº de cuentas abiertas
    int NClientes;
public:
    Cliente();
    void ActualizarCliente(Cadena pNomb, Cadena pDir);
    void DameNombre(Cadena pNom);
    void DameDireccion(Cadena pDir);
    int BuscarCuenta(int pNoCuenta);
    bool CrearCuenta(Cuenta pCu);
    bool ActalizarCuenta(Cuenta pCu);
    bool BorrarCuenta(int pNoCuenta);
    int DameNoCuentas();
    Cuenta DameCuenta(int pos);
    void Mostrar(char Campo);
};

Cliente::Cliente()
{
    NoCuentas=0;
    NClientes=0;
}

void Cliente::ActualizarCliente(Cadena pNomb, Cadena pDir)
{
    strcpy(Nombre,pNomb);
    strcpy(Direccion,pDir);
    NoCuentas=0;
}

void Cliente::DameNombre(Cadena pNom)
{
    strcpy(pNom,Nombre);
}

void Cliente::DameDireccion(Cadena pDir)
{
    strcpy(pDir,Direccion);
}

int Cliente::BuscarCuenta(int pNoCuenta)
{
    bool encontrado=false;
    int i=0;
    while(i<NoCuentas&&!encontrado)
    {
        if(Cuentas[i].DameNoCuenta()==pNoCuenta)
            encontrado=true;
        else
            i++;
    }
    if(!encontrado)
        i=-1;

    return i;
}
bool Cliente::CrearCuenta(Cuenta pCu)
{
    bool nuevo=false, encontrada=false;
    int i=0;

    if(NoCuentas<MAX_CUENTAS)
    {
        while(i<NoCuentas&&!encontrada)
        {
            if(Cuentas[i].DameNoCuenta()==pCu.DameNoCuenta())
                encontrada=true;
            else
                i++;
        }
        if(!encontrada)
        {
            NoCuentas++;
            Cuentas[NoCuentas]=pCu;
            nuevo=true;
            cout<<"Cuenta creada con exito"<<endl;
        }
    }
    else
        cout<<"Limite de cuentas alcanzadas..."<<endl;
    return nuevo;
}

bool Cliente::ActalizarCuenta(Cuenta pCu)
{
    int i=0;
    bool encontrada=false;
    while(i<NoCuentas&&!encontrada)
    {
        if(Cuentas[i].DameNoCuenta()==pCu.DameNoCuenta())
        {
            encontrada=true;
            Cuentas[i]=pCu;
            cout<<"Cuenta actualizada con exito"<<endl;
        }

        else
            i++;
    }
    if(!encontrada)
        cout<<"No existe ninguna cuenta con el Numero indicado..."<<endl;
    return encontrada;
}
bool Cliente::BorrarCuenta(int pNoCuenta)
{
    int i=0;
    bool encontrada=false;
    while(i<NoCuentas&&!encontrada)
    {
        if(Cuentas[i].DameNoCuenta()==pNoCuenta)
        {
            encontrada=true;
            for(int j=i; j<NoCuentas; j++)
                Cuentas[i]=Cuentas[i+1];
            NoCuentas--;
            cout<<"Cuenta actualizada con exito"<<endl;
        }

        else
            i++;
    }
    if(!encontrada)
        cout<<"No existe ninguna cuenta con el Numero indicado..."<<endl;
    return encontrada;
}

int Cliente::DameNoCuentas()
{
    return NoCuentas;
}
Cuenta Cliente::DameCuenta(int pos)
{
    return Cuentas[pos];
}
void Cliente::Mostrar(char Campo)
{
    switch(Campo)
    {
    case 'd':
        cout<<"Clientes\t\t\tDireccion"<<endl;
        for(int i=0; i<NClientes; i++)
            cout<<Cliente[i].Nombre<<"\t\t\t"<<Cliente[i].Direccion<<endl;
        break;
    case 'c':
        cout<<"Clientes\t\t\tCuentas\t\t\tNumero\t\t\tSaldo\t\t\tEstado"<<endl;
        for(int i=0; i<NClientes; i++)
            for(int j=0; j<Cliente[i].NoCuentas; j++)
            {
                cout<<Cliente[i].Nombre<<"\t\t\t"<<j+1<<Cliente[i].Cuentas[j].NoCuenta<<"\t\t\t"<<Cliente[i].Cuentas[j].Saldo<<"\t\t\t";
                if(Cliente[i].Cuentas[j].EstaBloqueada())
                    cout<<"Bloqueda"<<endl;
                else
                    cout <<"Desbloqueada"<<endl;
            }
        break;
    case 't':
        cout<<"Clientes\t\t\tDireccion\t\t\tCuentas\t\t\tNumero\t\t\tSaldo\t\t\tEstado"<<endl;


    }

}


int main()
{
    Cuenta DatosCuentas[MAX_CUENTAS];
    int nCuentas = 0; //Nº de elementos del vector DatosCuentas
    int op;
    do
    {
        op= MenuCuentas();

        switch(op)
        {
        case 1:

            float saldo;
            int numcuenta;
            if(nCuentas<MAX_CUENTAS)
            {
                cout<<"Introduce el Numero de la nueva cuenta: ";
                cin>>numcuenta;
                int poscuenta= BuscarCuenta(DatosCuentas,nCuentas,numcuenta);
                if(poscuenta==-1)
                {
                    cout<<"Introduce el saldo de la nueva cuenta: ";
                    cin>>saldo;
                    DatosCuentas[nCuentas]=Cuenta(numcuenta,saldo);
                    nCuentas++;
                }
                else
                    cout<<"Error, ya existe una cuenta con el mismo numero..."<<endl;

            }
            else
                cout<<"Error, limite de cuentas maximas alcanzado..."<<endl;

            system("pause");
            system("cls");
            break;
        case 2:
        {
            if(nCuentas==0)
                cout<<"No hay ninguna cuenta..."<<endl;
            else
                cout<<"Cuenta\t\t\tNcuenta\t\t\tSaldo\t\t\tEstado"<<endl;
            for(int i=0; i<nCuentas; i++)
            {
                cout<<i+1<<"\t\t\t"<<DatosCuentas[i].DameNoCuenta()<<"\t\t\t"<<DatosCuentas[i].DameSaldo()<<"\t\t\t";
                if(DatosCuentas[i].EstaBloqueada())
                    cout<<"Bloqueado"<<endl;
                else
                    cout<<"Desbloqueado"<<endl;
            }

        }
        system("pause");
        system("cls");
        break;
        case 3:
        {
            int Numeroc;
            cout<<"Introduce el Numero de la cuenta a eliminar: ";
            cin>>Numeroc;
            if(BuscarCuenta(DatosCuentas,nCuentas,Numeroc)==-1)
                cout<<"Error, no existe ninguna cuenta con dicho numero..."<<endl;
            else
            {
                for(int i=BuscarCuenta(DatosCuentas,nCuentas,Numeroc); i<nCuentas; i++)
                    DatosCuentas[i]=DatosCuentas[i+1];

                cout<<"Cuenta borrada..."<<endl;
                nCuentas--;
            }
        }
        system("pause");
        system("cls");
        break;
        case 4:
        {
            int ncuenta;
            float sal;
            cout<<"Introduce el numero de la cuenta: ";
            cin>>ncuenta;
            if(BuscarCuenta(DatosCuentas,nCuentas,ncuenta)==-1)
                cout<<"Error, no existe ninguna cuenta con dicho numero..."<<endl;
            else
            {
                cout<<"Introduce el nuevo saldo: ";
                cin>>sal;
                if(DatosCuentas[BuscarCuenta(DatosCuentas,nCuentas,ncuenta)].ActualizarSaldo(sal))
                    cout<<"Saldo actualizado"<<endl;
                else
                    cout<<"Error, no se ha podido actualizar el saldo..."<<endl;
            }
        }
        system("pause");
        system("cls");
        break;
        case 5:
        {
            int Numeroc;
            char bloq;
            bool pBloq;
            cout<<"Introduce el numero de la cuenta: ";
            cin>>Numeroc;
            int pos=BuscarCuenta(DatosCuentas,nCuentas,Numeroc);
            if(pos==-1)
                cout<<"Error, no existe ninguna cuenta con dicho numero..."<<endl;
            else
            {
                cout<<"Desea bloquear la cuenta?(s/n) ";
                cin>>bloq;
                switch (bloq)
                {
                case 's':
                    pBloq = true;
                    DatosCuentas[pos].ActualizarBloqueo(pBloq);

                    break;

                case 'n':
                    pBloq = false;
                    DatosCuentas[pos].ActualizarBloqueo(pBloq);

                    break;

                default:
                    cout << "\nCaracter no valido, no se ha podido realizar la accion.";
                }
            }
        }

        system("pause");
        system("cls");

        break;
        }
    }
    while(op!=6);
    return 0;
}
