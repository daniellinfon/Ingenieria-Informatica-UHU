#include <iostream>
using namespace std;
typedef char Cadena[50]; // Tipo de datos Cadena
#define MAX_CUENTAS 100 // Número de Cuentas
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
    Saldo = 0;
    NoCuenta = 0;
    Bloqueada = false;
}

Cuenta::Cuenta(int pNo, float pSal)
{
    Saldo = pSal;
    NoCuenta = pNo;
    Bloqueada = false;
}

bool Cuenta::ActualizarSaldo(int pSal)
{
    if (Bloqueada == false)
    {
        Saldo = pSal;
        Bloqueada = true;
    }
    else
        Bloqueada = false;

    return Bloqueada;
}

void Cuenta::ActualizarBloqueo(bool pBloq)
{
    Bloqueada = pBloq;
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
    if (Bloqueada == true)
        return Bloqueada;
    else
        return Bloqueada;
}

int BuscarCuenta(Cuenta Ctas[MAX_CUENTAS], int NCuentas, int NoCuenta)
{
    bool encontrada = false;
    int i = 0, valor = -1;

    while ((i < NCuentas) && (!encontrada))
    {
        if (Ctas[i].DameNoCuenta() == NoCuenta)
        {
            valor = i;
            encontrada = true;
        }
        i++;
    }
    return valor;
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
            cout << "\nOpcion no valida.\n";
    }
    while (op < 1 || op > 6);

    return op;
}


int main()
{
    Cuenta DatosCuentas[MAX_CUENTAS];
    int nCuentas = 0; //Nº de elementos del vector DatosCuentas
    int op, num, pos;
    float saldo;
    char opc;
    bool pBloq;

    do
    {
        system("cls");
        op = MenuCuentas();

        switch (op)
        {
        case 1:
            if (nCuentas < MAX_CUENTAS)
            {
                cout << "\nIntroduce el numero de la nueva cuenta: ";
                cin >> num;

                if (BuscarCuenta(DatosCuentas, nCuentas, num) == -1)
                {
                    cout << "\nIntroduce el saldo de la nueva cuenta: ";
                    cin >> saldo;

                    DatosCuentas[nCuentas] = Cuenta(num, saldo); //PREGUNTAR COMO ES POSIBLE QUE SE PUEDA HACER ESTO
                    nCuentas++;
                }
                else
                    cout << "\nERROR: Ya existe una cuenta con dicho numero.";
            }
            else
                cout << "\nERROR: No hay espacio para mas cuentas.";

            cout << endl;
            system("pause");

            break;

        case 2:
            cout << "\tNoCuenta:\t\tSaldo:\t\tEstado:\n";

            for (int i = 0; i < nCuentas; i++)
            {
                cout << i+1 << "\t"
                     << DatosCuentas[i].DameNoCuenta() << "\t\t\t"
                     << DatosCuentas[i].DameSaldo() << "\t\t";

                if (DatosCuentas[i].EstaBloqueada())
                    cout << "Bloqueada";

                else
                    cout << "Desbloqueada";

                cout << endl;
            }
            cout << endl;
            system("pause");

            break;

        case 3:
            cout << "\nIntroduce el numero de la cuenta que desea eliminar: ";
            cin >> num;

            pos = BuscarCuenta(DatosCuentas, nCuentas, num);

            if (pos == -1)
            {
                cout << "\nERROR: La cuenta no existe.";
            }
            else
            {
                for (int i = pos; i < nCuentas; i++)
                    DatosCuentas[i] = DatosCuentas[i+1];

                nCuentas--;
            }
            cout << endl;
            system("pause");

            break;

        case 4:
            cout << "\nIntroduce el numero de la cuenta que desea actualizar: ";
            cin >> num;

            pos = BuscarCuenta(DatosCuentas, nCuentas, num);

            if (pos == -1)
            {
                cout << "\nERROR: La cuenta no existe.";
            }
            else
            {
                cout << "\nIntroduce el nuevo saldo de la cuenta: ";
                cin >> saldo;

                if (!DatosCuentas[pos].ActualizarSaldo(saldo))
                    cout << "\nERROR: La cuenta esta bloqueada.";
            }
            cout << endl;
            system("pause");

            break;

        case 5:
            cout << "\nIntroduce el numero de la cuenta que desea actualizar: ";
            cin >> num;

            pos = BuscarCuenta(DatosCuentas, nCuentas, num);

            if (pos == -1)
            {
                cout << "\nERROR: La cuenta no existe.";
            }
            else
            {
                cout << "\nIndica si desea bloquear la cuenta o no (s/n): ";
                cin >> opc;

                switch (opc)
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
            cout << endl;
            system("pause");

            break;

        case 6:
            cout << "\nGracias por ultilizar el programa, hasta pronto!";

            cout << endl;
            system("pause");

            break;
        }

    }
    while (op != 6);

    return 0;
}
