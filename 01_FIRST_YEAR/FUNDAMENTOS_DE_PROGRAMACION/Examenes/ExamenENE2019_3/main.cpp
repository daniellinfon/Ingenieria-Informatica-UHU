#include <iostream>

using namespace std;
typedef char Cadena [50];

struct Terminal
{
    int Numero; // Numero del M�vil
    Cadena Modelo; // Modelo del M�vil
    float Bateria; // Nivel de la bater�a
};

class Torre   //Torre de Comunicaciones
{
    Cadena Carretera; //Nombre de la carretera
    int Kilometro; //Kil�metro dentro de la carretera
    Terminal Moviles[10000]; //Terminales m�viles registrados en la torre
    int NMoviles; //N�mero de Terminales registrados
public:

    void getCarretera(Cadena Nombre); //Devuelve el atributo carretera
    int getKilometro(); //Devuelve el atributo Kilometro
    int getNoMoviles(); //Devuelve el atributo NMoviles
    Terminal getMovil(int Pos); //Devuelve los datos del terminal que est� en la posici�n
//del vector que indica Pos
    int BuscarMovil(int Numero); //Devuelve la posici�n dentro del vector Moviles donde est�
//situado el m�vil cuyo n�mero coincide con el pasado por
//par�metro

    int BuscarTorre(Torre Red[1000], int NTorres, Cadena Carretera, int Kilometro);

};

int BuscarTorre(Torre Red[1000], int NTorres, Cadena Carretera, int Kilometro)
{

}

int main()
{
    Torre RedEspana[1000]; //Conjunto de torres de comunicaci�n de toda Espa�a
    int NTorres; //N�mero de Torres

    bool Registrado=false;
    int NumeMovil;
    cout << "Introduce el n�mero de m�vil a buscar ";
    cin>>NumeMovil;
    for (int km=0; km<1109; km+=50)
    {
        int T=BuscarTorre(RedEspana,NTorres,"AP-7",km);
        if (T>=0)
        {
            int Pos=RedEspana[T].BuscarMovil(NumeMovil);
            if (Pos>=0)
            {
                Registrado=true;
                Terminal Movil=RedEspana[T].getMovil(Pos);
                cout << "Pk: "<<km<<" Bater�a: "<<Movil.Bateria<<endl;
            }
        }
    }
    if (Registrado==false)
        cout<<"El terminal con el n�mero "<<NumeMovil<<" no se ha registrado en autov�a
            AP-7\n";
            return 0;
}
