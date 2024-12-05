#include <iostream>
#include <cstdlib>
#include <math.h>
#include <queue>
#include <list>
#include "arbin.h"
#include "abb.h"
#include "Excep_Ej6.h"

// Recorridos

template <typename T>
void inorden(const Arbin<T>& a, const typename Arbin<T>::Iterador& r)
{
    if (!r.arbolVacio())
    {
        inorden(a, a.subIzq(r));
        cout << r.observar() << " ";
        inorden(a, a.subDer(r));
    }
}

template <typename T>
void preorden(const Arbin<T>& a, const typename Arbin<T>::Iterador& r)
{
    if (!r.arbolVacio())
    {
        cout << r.observar() << " ";
        preorden(a, a.subIzq(r));
        preorden(a, a.subDer(r));
    }
}

template <typename T>
void postorden(const Arbin<T>& a, const typename Arbin<T>::Iterador& r)
{
    if (!r.arbolVacio())
    {
        postorden(a, a.subIzq(r));
        postorden(a, a.subDer(r));
        cout << r.observar() << " ";
    }
}

template <typename T>
void anchura(const Arbin<T>& a)
{
    if (!a.esVacio())
    {
        //list<T> lista;
        queue<typename Arbin<T>::Iterador> c;
        typename Arbin<T>::Iterador ic = a.getRaiz();
        c.push(ic);

        while (!c.empty())
        {
            ic = c.front();
            c.pop();
            cout << ic.observar() << " ";
            //lista.push_back(ic.observar());
            if (!a.subIzq(ic).arbolVacio())
                c.push(a.subIzq(ic));
            if (!a.subDer(ic).arbolVacio())
                c.push(a.subDer(ic));
        }
    }
}


/***************************************************************************/
/****************************** EJERCICIOS *********************************/
/***************************************************************************/
//Ejercicio 1

template <typename T> int numHojas (const Arbin<T>& a)
{
    int hojas=0;
    numHojas(a, a.getRaiz(), hojas);
    return hojas;
}

template <typename T> void numHojas (const Arbin<T>& a, const typename Arbin<T>::Iterador& r, int &hojas)
{
    if(!r.arbolVacio())
    {
        if(a.subIzq(r).arbolVacio() && a.subDer(r).arbolVacio())
            hojas++;
        else
        {
            numHojas(a, a.subIzq(r),hojas);
            numHojas(a, a.subDer(r),hojas);
        }
    }
}

/****************************************************************************/
//Ejercicio 2

template <typename T> Arbin<T> simetrico (const Arbin<T>& a)
{
    return simetrico(a,a.getRaiz());
}

template <typename T> Arbin<T> simetrico (const Arbin<T>& a, const typename Arbin<T>::Iterador& r)
{
    if(r.arbolVacio())
        return Arbin<T>();
    else
    {
        if(a.subIzq(r).arbolVacio() && a.subDer(r).arbolVacio())
            return Arbin<T>(r.observar(),Arbin<T>(),Arbin<T>());
        else
            return Arbin<T>(r.observar(),simetrico(a,a.subDer(r)),simetrico(a,a.subIzq(r)));
    }
}

/****************************************************************************/
//Ejercicio 3

template <typename T>
void recorridoZigzag( const Arbin<T>& a, char sentido )
{
    recorridoZigzag(a,a.getRaiz(),sentido);
}

template <typename T>
void recorridoZigzag( const Arbin<T>& a, const typename Arbin<T>::Iterador& r, char sentido)
{
    if(!r.arbolVacio())
    {
        cout<<r.observar()<<" ";
        if(toupper(sentido) == 'I')
            recorridoZigzag(a,a.subIzq(r),'D');

        else if(toupper(sentido) == 'D')
            recorridoZigzag(a,a.subDer(r),'I');

        else
            cout<<"Error, sentido incorrecto";
    }
}
/******************************************************************************/
//Ejercicio 4

template<typename T>
int numNodos(const Arbin<T>& a)
{
    return numNodos(a,a.getRaiz());
}

template<typename T>
int numNodos(const Arbin<T>& a, const typename Arbin<T>::Iterador &r)
{
    if(r.arbolVacio()) //el árbol no cambia, el que puede cambiar a vacio
        return 0;	//es el iterador "r"
    else
    {
        return (1 + numNodos(a,a.subIzq(r)) + numNodos(a,a.subDer(r)));
    }
}

template <typename T>
bool compensado(const Arbin<T>& a)
{
    return compensado(a,a.getRaiz());
}

template <typename T>
bool compensado(const Arbin<T>& a,const typename Arbin<T>::Iterador& r)
{
    if(r.arbolVacio()||(a.subIzq(r).arbolVacio() && a.subDer(r).arbolVacio()))
        return true;
    else
    {
        if ( ((numNodos(a,a.subIzq(r)) - numNodos(a,a.subDer(r))) <= 1) && (compensado(a,a.subIzq(r))) && (compensado (a,a.subDer(r))) )
            return true;
        else
            return false;
    }
}
/*****************************************************************************/
//Ejercicio 5

void palabras(const Arbin<char>& a, const typename Arbin<char>::Iterador& r, string &word, string actual)
{
    if(!r.arbolVacio())
    {
        string act= actual+r.observar();

        if(a.subIzq(r).arbolVacio() && a.subDer(r).arbolVacio())
            word=word+act+"\n";
        else
        {
            palabras(a,a.subIzq(r),word,act);
            palabras(a,a.subDer(r),word,act);
        }
    }
}

void palabras(const Arbin<char>& a)
{
    string word="", actual="";
    palabras(a,a.getRaiz(),word,actual);
    cout<< word;
}



/******************************************************************************/
//Ejercicio 6

void siguienteMayor(const ABB<int>& a, int x, const ABB<int>::Iterador& r, int &y)
{
    int actual;
    if(!r.arbolVacio())
    {
        actual=r.observar();
        if(actual == x)
        {
            if(a.subDer(r).arbolVacio() && y<x)
                y=0;
            else
                siguienteMayor(a,x,a.subDer(r),y);
        }
        else if(actual > x)
        {
            y=actual;
            siguienteMayor(a,x,a.subIzq(r),y);
        }
        else if(actual < x)
        {
            siguienteMayor(a,x,a.subDer(r),y);
        }
    }
}

int siguienteMayor(const ABB<int>& a, int x)
throw (NoHaySiguienteMayor)
{
    int y=0;
    siguienteMayor(a,x,a.getRaiz(),y);
    if(y==0)
        throw NoHaySiguienteMayor();
    else
        return y;
}


/******************************************************************************/
//Ejercicio 7

/*
template <typename T>
int posicionInorden(const ABB<T>& a, const T& obj)
{
    return posicionInorden(a,obj,a.getRaiz());
}

template <typename T>
int posicionInorden(const ABB<T>& a, const T& obj, const typename Arbin<T>::Iterador& r)
{
    if (r.arbolVacio())
        return 0;
    else
    {
        if (r.observar() == obj)
            return (numNodos(a,a.subIzq(r))+1);
        else if (obj < r.observar())
            return posicionInorden(a,obj,a.subIzq(r));
        else //if (obj > r.observar())
        {
            int p = posicionInorden(a,obj,a.subDer(r));
            if (p == 0)
                return 0;
            else
                return (numNodos(a,a.subIzq(r)) + 1 + p);
        }
    }
}*/

template <typename T>
int posicionInorden(const ABB<T>& a, const T& obj)
{
    int num=0;
    bool enc=false;
    posicionInorden(a,obj,a.getRaiz(),num,enc);
    if(enc == false)
        num=0;
    return num;
}

template <typename T>
void posicionInorden(const ABB<T>& a, const T& obj, const typename Arbin<T>::Iterador& r, int &num, bool &encontrado)
{
    if (!r.arbolVacio())
    {
        posicionInorden(a,obj,a.subIzq(r),num,encontrado);

        if(!encontrado)
            num++;

        if (r.observar() == obj && !encontrado)
            encontrado = true;

        if(!encontrado)
            posicionInorden(a,obj,a.subDer(r),num,encontrado);
    }

}

/******************************************************************************/
//Ejercicio 8

void haySumaCamino(const Arbin<int>& a, int suma,const typename Arbin<int>::Iterador& r,int actual,bool &encontrado)
{
    if(a.esVacio())
        encontrado=true;
    else
    {
        if(!r.arbolVacio())
        {
            int act= actual+r.observar();
            if(a.subIzq(r).arbolVacio() && a.subDer(r).arbolVacio())
            {
                if(act==suma)
                    encontrado= true;
            }
            else
            {
                haySumaCamino(a,suma,a.subIzq(r),act,encontrado);
                haySumaCamino(a,suma,a.subDer(r),act,encontrado);
            }
        }
    }
}

bool haySumaCamino(const Arbin<int>& a, int suma)
{
    bool encontrado=false;
    int actual=0;
    haySumaCamino(a, suma,a.getRaiz(),actual,encontrado);
    return encontrado;
}

//No Hay Siguiente Menor

void siguienteMenor(const ABB<int>& a, int x, const ABB<int>::Iterador& r, int &y)
{
    int actual;
    if(!r.arbolVacio())
    {
        actual=r.observar();
        if(actual == x)
        {
            if(a.subIzq(r).arbolVacio() && y>x)
                y=0;
            else
                siguienteMenor(a,x,a.subIzq(r),y);
        }
        else if(actual > x)
        {
            siguienteMenor(a,x,a.subIzq(r),y);
        }
        else if(actual < x)
        {
            y=actual;
            siguienteMenor(a,x,a.subDer(r),y);
        }
    }
}

int siguienteMenor(const ABB<int>& a, int x)
throw (NoHaySiguienteMayor)
{
    int y=0;
    siguienteMenor(a,x,a.getRaiz(),y);
    if(y==0)
        throw NoHaySiguienteMayor();
    else
        return y;
}

//CargarPar
template<typename T>
bool CargarPar(const Arbin<T> &a, const typename Arbin<T>::Iterador &r)
{
    if(r.arbolVacio()||(a.subIzq(r).arbolVacio() && a.subDer(r).arbolVacio()))
        return true;

    if ( (((numNodos(a,a.subIzq(r)) - numNodos(a,a.subDer(r))) == 2) || (numNodos(a,a.subIzq(r)) - numNodos(a,a.subDer(r))) == 0) && (CargarPar(a,a.subIzq(r))) && (CargarPar (a,a.subDer(r))) )
        return true;
    else
        return false;
}

template<typename T>
bool CargarPar(const Arbin<T> &a)
{
    return CargarPar(a,a.getRaiz());
}

//contrasena

void contrasena (const Arbin<char> &a, const string palabra,const typename Arbin<char>::Iterador &r, bool &password, string actual)
{
    if(!r.arbolVacio())
    {
        string act=actual+r.observar();

        if(act==palabra)
            password=true;
        else
        {
            contrasena(a,palabra,a.subIzq(r),password,act);
            contrasena(a,palabra,a.subDer(r),password,act);
        }
    }
}

bool contrasena (const Arbin<char> &a, const string palabra)
{
    bool password=false;
    string actual="";
    contrasena(a,palabra,a.getRaiz(),password,actual);
    return password;
}

//Hay Posible Camino

void hayPosibleCamino(const Arbin<int>&a, const int n, const typename Arbin<int>::Iterador&r, int actual,bool &encontrado)
{
    if(n==0)
        encontrado=true;
    if(!r.arbolVacio())
    {
        int act=actual+r.observar();

        if(act>n)
            encontrado=true;

        else
        {
            hayPosibleCamino(a,n,a.subIzq(r),act,encontrado);
            hayPosibleCamino(a,n,a.subDer(r),act,encontrado);
        }
    }
}

bool hayPosibleCamino(const Arbin<int>&a, const int n)
{
    bool encontrado=false;
    int act=0;
    hayPosibleCamino(a,n,a.getRaiz(),act,encontrado);
    return encontrado;
}

//Peso hoja
void PesoDeHoja(const Arbin<int>&a, const int cota, const typename Arbin<int>::Iterador&r, int actual)
{
    if(!r.arbolVacio())
    {
        int act=actual+r.observar();

        if(a.subIzq(r).arbolVacio() && a.subDer(r).arbolVacio())
        {
            if(act<cota)
                cout<<r.observar()<<" ";
        }
        else
        {
            PesoDeHoja(a,cota,a.subIzq(r),act);
            PesoDeHoja(a,cota,a.subDer(r),act);
        }
    }
}

void PesoDeHoja(const Arbin<int>&a, const int cota)
{
    int act=0;
    PesoDeHoja(a,cota,a.getRaiz(),act);
}

template<typename T>
void HojasFaltantes(const Arbin<T> &a, const typename Arbin<T>::Iterador &r, int &cont,int nivel)
{
    if(!r.arbolVacio())
    {
        nivel++;
        HojasFaltantes(a,a.subIzq(r),cont,nivel);
        HojasFaltantes(a,a.subDer(r),cont,nivel);
    }
    else
    {
        if(cont<nivel)
            cont=nivel;
    }
}

template<typename T>
int HojasFaltantes(const Arbin<T> &a)
{
    int cont=0;
    int nivel=0;
    HojasFaltantes(a,a.getRaiz(),cont,nivel);
    nivel=1;
    for(int i=0;i<cont;i++)
        nivel=nivel*2;
    nivel--;
    numNodos(a);
    return (nivel-numNodos(a));
}


//Ejercicio 2
void predecesor(const ABB<int>& a, const int n, const ABB<int>::Iterador& r, int &y)
{
    int actual;
    //if(existe(a,a.getRaiz(),n))
    //{
        if(!r.arbolVacio())
        {
            actual=r.observar();
            if(actual == n)
            {
                if(a.subIzq(r).arbolVacio() && y>n)
                    y=0;
                else
                    predecesor(a,n,a.subIzq(r),y);
            }
            else if(actual > n)
            {
                predecesor(a,n,a.subIzq(r),y);
            }
            else if(actual < n)
            {
                y=actual;
                predecesor(a,n,a.subDer(r),y);
            }
        }
    //}
}

int predecesor(const ABB<int>&a, const int n)
{
    int y=0;
    predecesor(a,n,a.getRaiz(),y);
    return y;
}


/****************************************************************************/
/****************************************************************************/
int main(int argc, char *argv[])
{
     Arbin<char> A('t', Arbin<char>('m', Arbin<char>(),
                                   Arbin<char>('f', Arbin<char>(), Arbin<char>())),
                  Arbin<char>('k', Arbin<char>('d', Arbin<char>(), Arbin<char>()),
                              Arbin<char>()));

    Arbin<char> H('m', Arbin<char>('q', Arbin<char>('s', Arbin<char>(), Arbin<char>()),
                                   Arbin<char>('t', Arbin<char>(), Arbin<char>())),
                  Arbin<char>('d', Arbin<char>('k', Arbin<char>(), Arbin<char>()),
                              Arbin<char>()));

    Arbin<char> I('m', Arbin<char>('q', Arbin<char>('s', Arbin<char>(), Arbin<char>()),
                                   Arbin<char>('t', Arbin<char>(), Arbin<char>())),
                  Arbin<char>('d', Arbin<char>('k', Arbin<char>(), Arbin<char>()),
                              Arbin<char>('x', Arbin<char>(), Arbin<char>())));

    Arbin<char> B('t', Arbin<char>('n', Arbin<char>(),
                                   Arbin<char>('d', Arbin<char>('e', Arbin<char>(), Arbin<char>()),
                                           Arbin<char>())),
                  Arbin<char>('m', Arbin<char>('f', Arbin<char>(), Arbin<char>()),
                              Arbin<char>('n', Arbin<char>(), Arbin<char>())));

    Arbin<char> D('t', Arbin<char>('k', Arbin<char>('d', Arbin<char>(), Arbin<char>()),
                                   Arbin<char>()),
                  Arbin<char>('m', Arbin<char>(),
                              Arbin<char>('f', Arbin<char>(), Arbin<char>())));

    Arbin<char> E('o', Arbin<char>('r', Arbin<char>(),
                                   Arbin<char>('o', Arbin<char>(), Arbin<char>())),
                  Arbin<char>('l', Arbin<char>('a', Arbin<char>(), Arbin<char>()),
                              Arbin<char>('e', Arbin<char>(), Arbin<char>())));

    Arbin<int> F(2, Arbin<int>(7, Arbin<int>(2, Arbin<int>(), Arbin<int>()),
                               Arbin<int>(6, Arbin<int>(5, Arbin<int>(), Arbin<int>()),
                                          Arbin<int>(11, Arbin<int>(), Arbin<int>()))),
                 Arbin<int>(5, Arbin<int>(),
                            Arbin<int>(9, Arbin<int>(),
                                       Arbin<int>(4, Arbin<int>(), Arbin<int>()))));

    Arbin<char> G('o', Arbin<char>('r', Arbin<char>('p', Arbin<char>(), Arbin<char>()),
                                   Arbin<char>('o', Arbin<char>(), Arbin<char>())),
                  Arbin<char>('l', Arbin<char>('a', Arbin<char>(), Arbin<char>()),
                              Arbin<char>('e', Arbin<char>(), Arbin<char>())));


    ABB<int> BB6, BB7;



    // NUMERO HOJAS //
    cout << "Num. hojas del arbol B: " << numHojas(B) << endl;
    cout << "Num. hojas del arbol E: " << numHojas(E) << endl << endl;

    // COPIA SIMETRICA //
    Arbin<char> C = simetrico(B);
    cout << "Recorrido en inorden del arbol B: " << endl;
    inorden(B, B.getRaiz());
    cout << endl << "Recorrido en inorden del arbol C: " << endl;
    inorden(C, C.getRaiz());
    cout << endl << endl;


    // RECORRIDO EN ZIG-ZAG //
    cout << "Recorrido en zigzag I de B:\n";
    recorridoZigzag(B, 'I');
    cout << endl;
    cout << "Recorrido en zigzag D de C:\n";
    recorridoZigzag(C, 'D');
    cout << endl << endl;


    // COMPENSADO //
    cout << "Esta A compensado?:";
    cout << (compensado(A) ? " SI" : " NO") << endl;
    cout << "Esta B compensado?:";
    cout << (compensado(B) ? " SI" : " NO") << endl << endl;

    // PALABRAS DE UN ARBOL //
    cout << "PALABRAS DE A:\n";
    palabras(E);
    cout << "PALABRAS DE B:\n";
    palabras(B);
    cout << endl;

    // SIGUIENTE MAYOR
    BB6.insertar(8);
    BB6.insertar(3);
    BB6.insertar(10);
    BB6.insertar(1);
    BB6.insertar(6);
    BB6.insertar(14);
    BB6.insertar(4);
    BB6.insertar(7);
    BB6.insertar(13);
    try
    {
        cout << "Siguiente mayor en BB6 de 5: " << siguienteMayor(BB6, 5) << endl;
        cout << "Siguiente mayor en BB6 de 8: " << siguienteMayor(BB6, 8) << endl;
        cout << "Siguiente mayor en BB6 de 13: " << siguienteMayor(BB6, 13) << endl;
        cout << "Siguiente mayor en BB6 de 14: " << siguienteMayor(BB6, 14) << endl;
    }
    catch(const NoHaySiguienteMayor& e)
    {
        cout << e.Mensaje() << endl << endl;
    }

    try
    {
        cout << "Siguiente menor en BB6 de 3: " << siguienteMenor(BB6, 3) << endl;
        cout << "Siguiente menor en BB6 de 8: " << siguienteMenor(BB6, 8) << endl;
        cout << "Siguiente menor en BB6 de 13: " << siguienteMenor(BB6, 13) << endl;
        cout << "Siguiente menor en BB6 de 1: " << siguienteMenor(BB6, 1) << endl;
    }
    catch(const NoHaySiguienteMayor& e)
    {
        cout << e.Mensaje() << endl << endl;
    }

    // POSICION INORDEN //
    BB7.insertar(5);
    BB7.insertar(1);
    BB7.insertar(3);
    BB7.insertar(8);
    BB7.insertar(6);
    cout << "Posicion Inorden en BB7 de 3: ";
    cout << posicionInorden(BB7, 3);
    cout << endl << "Posicion Inorden en BB7 de 8: ";
    cout << posicionInorden(BB7, 8);
    cout << endl << "Posicion Inorden en BB7 de 7: ";
    cout << posicionInorden(BB7, 7);
    cout << endl << endl;

    // SUMA CAMINO
    cout << "Hay un camino de suma 26 en F?:";
    cout << (haySumaCamino(F, 26) ? " SI" : " NO") << endl;
    cout << "Hay un camino de suma 9 en F?:";
    cout << (haySumaCamino(F, 9) ? " SI" : " NO") << endl;
    cout << endl << endl;

    //No hay hojas
    cout<<"En el arbol A faltan: "<<HojasFaltantes(A)<<" hojas."<<endl;
    cout<<"En el arbol B faltan: "<<HojasFaltantes(B)<<" hojas."<<endl;
    cout<<"En el arbol E faltan: "<<HojasFaltantes(E)<<" hojas."<<endl;
    cout<<"En el arbol D faltan: "<<HojasFaltantes(D)<<" hojas."<<endl;
    cout<<"En el arbol F faltan: "<<HojasFaltantes(F)<<" hojas."<<endl;

    //Cargar Par
    cout << "Tiene A carga par?:";
    cout << (CargarPar(A) ? " SI" : " NO") << endl;
    cout << "Tiene B carga par?:";
    cout << (CargarPar(B) ? " SI" : " NO") << endl << endl;

    //Contrasena
    cout << "Contrasena DE A:\n";
    cout << (contrasena(A,"tkd") ? " SI" : " NO") << endl;
    cout << "Contrasena DE B:\n";
    cout << (contrasena(B,"tkd") ? " SI" : " NO") << endl;

    cout << endl;

    //Hay posible camino
    cout << "Hay un camino de suma mayor que 20 en F?:";
    cout << (haySumaCamino(F, 20) ? " SI" : " NO") << endl;
    cout << "Hay un camino de suma mayor que 50 en F?:";
    cout << (haySumaCamino(F, 50) ? " SI" : " NO") << endl;
    cout << endl << endl;

    //PesoHoja
    cout<<"Pesos de hoja inferiores a 40: ";
    PesoDeHoja(F, 40);
    cout<<endl;

     //Ejercicio 1

    cout << "En el arbol F faltan " << HojasFaltantes(F) << " hojas para que este completo" << endl;
    cout << "En el arbol D faltan " << HojasFaltantes(D) << " hojas para que este completo" << endl;
    cout << "En el arbol G faltan " << HojasFaltantes(G) << " hojas para que este completo" << endl;
    cout << "En el arbol B faltan " << HojasFaltantes(B) << " hojas para que este completo" << endl;


    // Ejercicio 2

    cout << "El predecesor de 6 en el ABB BB6 es: " << predecesor(BB6, 6) << endl;
    cout << "El predecesor de 4 en el ABB BB6 es: " << predecesor(BB6, 4) << endl;
    cout << "El predecesor de 14 en el ABB BB6 es: " << predecesor(BB6, 14) << endl;

    cout << endl << endl;
    system("PAUSE");
    return 0;
}
