#include <iostream>
#include <windows.h>
using namespace std;

int main()
{
    int hora,minuto,seg,time,tsimulador;
    do{
    cout << "Introduce la hora de inicio (0-23): ";
    cin >>hora;
    if(hora<0 || hora>23){
        cout<<"Debe ser un valor entre 0 y 23!!"<<endl;
    }
    }while(hora<0 || hora>23);
    do{
    cout<<endl<<"Introduce los minutos iniciales (0-60): ";
    cin>> minuto;
    cout<<endl;
    if(minuto<0 || minuto>60){
        cout<<"Debe ser un valor entre 0 y 60!!"<<endl;
    }
    }while(minuto<0 || minuto>60);
    do{
    cout<<"Introduce la cantidad de tiempo, en minutos, que durara la simulación: ";
    cin >> time;
    cout<<endl;
    if(time<0){
        cout<<"Debe ser un valor igual o mayor que 0!!"<<endl;
    }
    }while(time<0);

    tsimulador= time*60; //minutos de simulacion a segundos

    do{
        system("cls");
        if(hora<10)
            cout<<"0";
        cout<<hora<<" : ";
        if(minuto<10)
            cout<<"0";
        cout<<minuto<<" : ";
        if(seg<10)
            cout<<"0";
        cout<<seg;

        Sleep(500);

        seg++;
        if(seg==60)
        {
            seg=0;
            minuto++;
            if(minuto==60){
                minuto=0;
                hora++;
            if(hora==24)
                hora=0;
        }
    }

    tsimulador--;
    }while(tsimulador>0);

    return 0;
}
