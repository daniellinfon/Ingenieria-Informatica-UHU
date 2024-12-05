#include "Fecha.h"

Fecha::Fecha(const int &dia, const int &m, const int &anio)
{
    this->setFecha(dia, m, anio); //el cogido es el mismo que el del metodo setFecha
}

void Fecha::setFecha(const int &dia, const int &mes, const int &a)
{
    /*
      if ((mes == 1) || (mes == 3) || (mes == 5) ....
          dmax=31;
      else if ((mes == 4) || (mes == 6) || (mes == 9) ....
          dmax=30;
      else if (mes == 2)
          dmax=28;

      switch (mes) {
        case 1:
        case 3:
        case 5:
           ...
                dmax=31;
                break;
        case 4:
        case 6:
        case 9:
           ...
                dmax=30;
                break;
        default:
                dmax=28;
                break;
      }
    */
//ES MAS RAPIDO Y COMODO USAR UN ARRAY QUE GUARDE LOS DIAS DE CADA MES...
    int dmax, diaMes[] = {0,31,28,31,30,31,30,31,31,30,31,30,31};
    this->anio=a; //VIP debo asignar año para que al llamar a bisiesto() tenga el año bien
    if (this->bisiesto())
        diaMes[2]=29;

    if (mes<1)  //si el mes es incorrecto
        this->mes=1;
    else if (mes>12) //si el mes es incorrecto
        this->mes=12;
    else
        this->mes=mes;
    dmax=diaMes[this->mes]; //una vez fijado el mes veo cuantos dias tiene ese mes como maximo

    if (dia>dmax) //si dia es superior al numero de dias de dicho mes
        this->dia=dmax;
    else if (dia<1) //si dia es inferior a 1
        this->dia=1;
    else
        this->dia=dia;
}

bool Fecha::bisiesto() const
{
    if (this->anio%4==0)
    {
        if(this->anio%100==0 && this->anio%400==0)
            return true;
        else if (this->anio%100==0 && this->anio%400!=0)
        return false;
        else
        return true;
    }
    else
        return false;

}

void Fecha::ver() const
{
    if (this->dia < 10)
        cout << "0";
    cout << this->dia << "/";
    if (this->mes < 10)
        cout << "0";
    cout << this->mes << "/" << this->anio;
}

Fecha Fecha::operator++()     //++f
{
    int dmax, diaMes[] = {0,31,28,31,30,31,30,31,31,30,31,30,31};
    if (this->bisiesto()) //si el año es bisiesto febrero tiene 29 dias
        diaMes[2]=29;
    dmax=diaMes[this->mes];
    this->dia++;
    if (this->dia>dmax)   //si al incrementar dia superamos el numero de dias de dicho mes
    {
        this->dia=1;      //pasamos a 1
        this->mes++;      //del mes siguiente
        if (this->mes>12)   //si al incrementar mes pasamos de 12 meses
        {
            this->mes=1;    //pasamos al mes 1
            this->anio++;   //del año siguiente
        }
    }
    return *this; //devolvemos el objeto fecha ya incrementado
}

Fecha Fecha::operator++(int i) //f++
{
    Fecha copia(*this);
    this->operator++();
    return copia;
}

Fecha Fecha::operator+(const int &i) const //i+f
{
    Fecha suma(dia, mes, anio); //creo fecha local suma igual a la que invoca el método
    for (int n=1; n<=i; n++)
        ++suma; //llamo al operator++ (notacion prefija) que ya tenemos hecha
    return suma; //devolvemos el objeto fecha suma ya incrementado
};

Fecha operator+(const int &i, const Fecha &f) //f+i
{
    return f+i; //llamo al operator+ (notacion prefija)
}

ostream& operator<<(ostream &s, const Fecha &f)
{
    int i=1;
    bool encontrado = false;
    int numMes[]= {0,1,2,3,4,5,6,7,8,9,10,11,12};
    string MesNom[]={" ","ene","feb","mar","abr","may","jun","jul","ago","sep","oct","nov","dic"};
    if(f.getDia()<10)
        s << "0"<< f.getDia()<<" ";
    else
        s<< f.getDia()<<" ";

     while(i<13 && !encontrado)
    {
            if(f.getMes()==numMes[i])
            {
                s<< MesNom[i]<<" ";
                encontrado=true;
            }
            else
                i++;
    }
    s<<f.getAnio();
    return s;
}

//RESTO DE METODOS Y FUNCIONES A RELLENAR POR EL ALUMNO...

