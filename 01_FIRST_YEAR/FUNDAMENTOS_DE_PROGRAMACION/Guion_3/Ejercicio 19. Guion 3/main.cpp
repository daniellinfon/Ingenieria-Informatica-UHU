#include <iostream>
#include <conio.h>
#include <cstring>
#include <cctype>

using namespace std;

int main()
{
   char palabraSecreta[50], palabra[50], letra[1];
   int puntos=9, tamano, ldescub=0, descubant=0, t, p;

   cout << "Tamano de la palabra: ";
   cin >> tamano;


   cout << "Introduzca la palabra secreta: ";
   for (int i=0; i<tamano; i++)
    {palabraSecreta[i]=getch();
    cout << "*";}

   strupr(palabraSecreta);
   for (int i=0; i<tamano; i++)
    palabra[i]='_';

   cout << endl << "Palabra a descubrir: " << palabra << endl;

   do
{
   descubant=ldescub;
   cout << endl << "Introduzca una letra: ";
   cin >> letra[0];
   letra[0]=toupper(letra[0]);

   cout << letra[0];
   system("cls");

   cout << endl << "Palabra a descubrir: ";

   for (int i=0; i<tamano; i++)
    {
    if (letra[0]==palabraSecreta[i])
      {palabra[i]=palabraSecreta[i];
      ldescub=ldescub+1;}}
    if (ldescub==descubant)
      {puntos= puntos-1;}

    t=ldescub;
    p=puntos;


   cout << palabra;
}
while (t<tamano && p>0);


    if (t==tamano)
     {cout << endl << "ENHORABUENA" << endl;
     cout << "Puntos conseguidos: " << puntos;}
    else
     {cout << endl << "Ups, se ha quedado sin puntos." << endl;
     cout << "La palabra era: " << palabraSecreta;}



    return 0;
}
