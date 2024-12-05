#include <iostream>

using namespace std;
typedef char Cadena [50];

struct Terminal
{
    int Numero; // Numero del Móvil
    Cadena Modelo; // Modelo del Móvil
    float Bateria; // Nivel de la batería
};

class Torre   //Torre de Comunicaciones
{
    Cadena Carretera; //Nombre de la carretera
    int Kilometro; //Kilómetro dentro de la carretera
    Terminal Moviles[10000]; //Terminales móviles registrados en la torre
    int NMoviles; //Número de Terminales registrados
public:

    void getCarretera(Cadena Nombre); //Devuelve el atributo carretera
    int getKilometro(); //Devuelve el atributo Kilometro
    int getNoMoviles(); //Devuelve el atributo NMoviles
    Terminal getMovil(int Pos); //Devuelve los datos del terminal que está en la posición
//del vector que indica Pos
    int BuscarMovil(int Numero); //Devuelve la posición dentro del vector Moviles donde está
//situado el móvil cuyo número coincide con el pasado por
//parámetro

    int BuscarTorre(Torre Red[1000], int NTorres, Cadena Carretera, int Kilometro);

};

int BuscarTorre(Torre Red[1000], int NTorres, Cadena Carretera, int Kilometro)
{

}

int main()
{
    Torre RedEspana[1000]; //Conjunto de torres de comunicación de toda España
    int NTorres; //Número de Torres

    bool Registrado=false;
    int NumeMovil;
    cout << "Introduce el número de móvil a buscar ";
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
                cout << "Pk: "<<km<<" Batería: "<<Movil.Bateria<<endl;
            }
        }
    }
    if (Registrado==false)
        cout<<"El terminal con el número "<<NumeMovil<<" no se ha registrado en autovía
            AP-7\n";
            return 0;
}
