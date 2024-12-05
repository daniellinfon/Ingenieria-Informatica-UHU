#include <iostream>
#include <conio.h>
#include <cstring>
#include <cctype>

#define T 25

using namespace std;

class PalabraOculta
{
    char palabraSecreta[T]; // Palabra a adivinar.
    char palabraJugada[T];
    /* Inicialmente contendrá tantos guiones como letras tenga el atributo
    palabraSecreta. Los guiones irán siendo sustituidos por las letras que
    el jugador vaya adivinando en cada jugada. */
    int Puntos; // Puntos conseguidos por el jugador.

public:
    void Iniciar();
    bool Jugada(char letra);
    int getPuntos();
    int longpal();
    void MostrarJugada();
    void DescubrirSecreta();
};

void PalabraOculta::Iniciar()
{
    int longpalabra = 0, i = 0;

    cout << "Introduce la palabra a adivinar: ";
    do
    {
        palabraSecreta[i] = getch();

        if (palabraSecreta[i] != '\r')
        {
            cout << "*";
            longpalabra++;
        }

        i++;
    }
    while (palabraSecreta[i-1] != '\r' && i < T); // '\r' => TECLA ENTER

    strupr(palabraSecreta);

    for (i = 0; i < longpalabra; i++)
        palabraJugada[i] = '-';

    Puntos = 9;
}

bool PalabraOculta::Jugada(char letra)
{
    letra = toupper(letra);
    bool encontrada = false;
    int i = 0;

    while (i < strlen(palabraSecreta) && !encontrada)
    {
        if (letra == palabraSecreta[i] && palabraJugada[i] == '-')
        {
            palabraJugada[i] = letra;
            encontrada = true;
        }
        i++;
    }

    if (!encontrada)
        Puntos--;

    return encontrada;
}

int PalabraOculta::getPuntos()
{
    return Puntos;
}

int PalabraOculta::longpal()
{
    return strlen(palabraSecreta);
}

void PalabraOculta::MostrarJugada()
{
    cout << endl << palabraJugada << endl;
}

void PalabraOculta::DescubrirSecreta()
{
    cout << endl << palabraSecreta << endl;
}


int main()
{
    PalabraOculta P1;
    char letra;
    int adivinadas = 1;
    bool win = false;

    P1.Iniciar();

    do
    {
        cout << endl << "Puntos restantes: " << P1.getPuntos() << endl << endl;
        P1.MostrarJugada();
        cout << endl << "Introduce una letra: ";

        cin >> letra;

        if (P1.Jugada(letra))
        {
            cout << endl << "La letra se encuentra en la palabra!" << endl;
            adivinadas++;
        }

        else
            cout << endl << "La letra NO se encuentra en la palabra o ya ha sido descubierta." << endl;

        if(adivinadas == P1.longpal())
            win = true;
    }
    while (P1.getPuntos() > 0 && !win);

    if (P1.getPuntos() == 0)
        cout << endl << "Te quedan 0 puntos, has perdido." << endl;
    if (win)
        cout << endl << "Enhorabuena, has ganado!" << endl << "Puntos sobrantes: " << P1.getPuntos() << endl;

    return 0;
}
