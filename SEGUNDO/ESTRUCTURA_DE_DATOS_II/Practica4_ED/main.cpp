#include <iostream>
#include <cstdlib>
#include "grafo.h"
#include "conjunto.h"
#include <queue>
#include <sstream>
#include <map>


using namespace std;

//Ejercicio 1
template <typename T>
T verticeMaxCoste(const Grafo<T, float>& G)
{
    map<T,float> dCoste; //Se crea un diccionario.
    T v, vMax;

    //inicializa el diccionario de costes a 0;
    Conjunto<Vertice<T> > cv = G.vertices();
    while(!cv.esVacio())
    {
        v = cv.quitar().getObj(); //devuelve un vertice y lo elimina del conjunto
        dCoste[v] = 0; //inicializo el valor de cada clave a 0
    }

    //Actualizo los costes de las etiquetas
    Conjunto<Arista<T,float> > cAristas = G.aristas();
    while(!cAristas.esVacio())
    {
        Arista<T,float> a = cAristas.quitar(); //Se crea una arista por copia
        dCoste[a.getOrigen()] += a.getEtiqueta();
    }

    //Recorrer el diccionario dCoste para encontrar el vertice con mayor coste.

    float maxCoste = 0;
    for(typename map<T,float>::iterator it = dCoste.begin() ; it != dCoste.end() ; it++)
        if(it->second >= maxCoste)
        {
            maxCoste = it->second;
            vMax = it->first;
        }
    return vMax;
}


//Ejercicio 2
template <typename T, typename U>
void inaccesibles(const Grafo<T, U>& G)
{
    map<T,bool> dEntrada;
    T v;

    //inicializa el diccionario de costes a 0;
    Conjunto<Vertice<T> > cv = G.vertices();
    while(!cv.esVacio())
    {
        v = cv.quitar().getObj();
        dEntrada[v] = false;
    }

    Conjunto<Arista<T,float> > cAristas = G.aristas();
    while(!cAristas.esVacio())
    {
        Arista<T,float> a = cAristas.quitar();
        dEntrada[a.getDestino()] = true;
    }

    for(typename map<T,bool>::iterator it = dEntrada.begin() ; it != dEntrada.end() ; it++)
    {
        if(it->second == false)
            cout << it->first << " ";
    }
    cout<<endl;
}


// Ejercicio 3
template <typename T, typename U>
bool caminoEntreDos(const Grafo<T, U>& G, const T& vo, const T& vd)
{
    queue<T> cola;
    Conjunto<Vertice<T>> ady, cv;
    cv = G.vertices();
    T u, w;
    map<T,bool> visitados;
    bool existeCamino = false;

    //inicializar dic
    while (!cv.esVacio())
    {
        u = cv.quitar().getObj();
        visitados[u] = false;
    }

    cola.push(vo);
    visitados[vo]=true;

    while(!cola.empty() && !existeCamino)
    {
        u = cola.front();
        cola.pop();
        if (u != vd)
        {
            ady = G.adyacentes(u);
            while (!ady.esVacio())
            {
                w = ady.quitar().getObj();
                if (!visitados[w])
                {
                    cola.push(w);
                    visitados[w] = true;
                }
            }
        }
        else
            existeCamino = true;
    }
    return existeCamino;
}


//Ejercicio 4
template <typename T>
void caminosAcotados(const Grafo<T, float>& G, const T& u, float maxCoste)
{
    stringstream ss;
    ss << u;
    caminosAcotados(G, u, maxCoste, ss.str(), 0);
}

template <typename T>
void caminosAcotados(const Grafo<T, float>& G, const T& u, float maxCoste, string s, float CosteAcumulado)
{
    Conjunto<Arista<T, float> > ca = G.aristas();
    cout << "(" << s << ") coste = " << CosteAcumulado << endl;
    while(!ca.esVacio())
    {
        Arista<T, float> a = ca.quitar();
        if (a.getOrigen() == u && ((a.getEtiqueta() + CosteAcumulado) <= maxCoste))
        {
            //cout << "(" << s << ") coste = " << CosteAcumulado << endl;
            CosteAcumulado += a.getEtiqueta();
            stringstream u1;
            string s2=s;
            u1 << a.getDestino();
            s = s + " - " + u1.str();
            caminosAcotados(G,a.getDestino(),maxCoste,s,CosteAcumulado);
            //Deshacer cambios
            CosteAcumulado -= a.getEtiqueta();
            s=s2;
        }
    }
}

//Ejercicio 5
template <typename T, typename U>
T outConectado(const Grafo<T, U>& G)
{
    map<T,float> visitados;
    T v;
    Conjunto<Vertice<T> > cv = G.vertices();
    Conjunto<Arista<T, float>> ca = G.aristas();
    while(!cv.esVacio())
    {
        v = cv.quitar().getObj(); //devuelve un vertice y lo elimina del conjunto
        visitados[v] = 0; //inicializo el valor de cada clave a 0
    }

    while(!ca.esVacio())
    {
        Arista<T, float> a = ca.quitar();
        visitados[a.getOrigen()]++;
        visitados[a.getDestino()]--;
    }

    for(typename map<T,float>::iterator it = visitados.begin() ; it != visitados.end() ; it++)
        if(it->second > 0)
            return it->first;
}


//Ejercicio 6
template <typename T, typename U>
void recorrido_profundidad(const Grafo<T, U>& G, const T& v)
{
    map<T,bool> visitados;
    T x;
    //inicializa el diccionario a false;
    Conjunto<Vertice<T> > cv = G.vertices();
    while(!cv.esVacio())
    {
        x = cv.quitar().getObj();
        visitados[x] = false;
    }
    cout<< v <<"  ";
    visitados[v]=true;
    recorrido_profundidad(G,v,visitados);
}

template <typename T, typename U>
void recorrido_profundidad(const Grafo<T, U>& G, const T& v, map<T,bool> &visitados)
{
    Conjunto<Vertice<T>> ady = G.adyacentes(v);
    T w;
    while (!ady.esVacio())
    {
        w = ady.quitar().getObj();
        if (!visitados[w])
        {
            visitados[w] = true;
            cout<< w <<"  ";
            recorrido_profundidad(G,w,visitados);
        }
    }
}


//anchura

template <typename T, typename U>
void recorrido_anchura(const Grafo<T, U>& G, const T& v)
{
    map<T,bool> visitados;
    queue<T> cola;
    T x;
    Conjunto<Vertice<T>> ady;

    //inicializa el diccionario a false;
    Conjunto<Vertice<T> > cv = G.vertices();
    while(!cv.esVacio())
    {
        x = cv.quitar().getObj();
        visitados[x] = false;
    }
    cola.push(v);
    visitados[v]=true;
    while(!cola.empty())
    {
        x = cola.front();
        cola.pop();
        cout<<x<<"  ";
        ady= G.adyacentes(x);
        while(!ady.esVacio())
        {
            x = ady.quitar().getObj();
            if(!visitados[x])
            {
                cola.push(x);
                visitados[x]=true;
            }
        }
    }
}

//Camino mas largo
template <typename T, typename U>
void CaminoMasLargo(const Grafo<T, U>& G, const T& v1, const T& v2,map<T,bool> &visitados,string &s,string resp, int &cont, int comparador)
{
    Conjunto<Vertice<T>> ady = G.adyacentes(v1);
    T x;
    visitados[v1]=true;
    stringstream ss;
    ss << v1;
    resp += ss.str()+"   ";
    comparador++;

    while(!ady.esVacio())
    {
        x=ady.quitar().getObj();
        if(x==v2 && (comparador>cont))
        {
            cont=comparador;
            stringstream ss2;
            ss2 <<x;
            s = resp;
            s += " "+ss2.str();
        }
        else if(!visitados[x])
            CaminoMasLargo(G,x,v2,visitados,s,resp,cont,comparador);

        visitados[x]=false;
    }
}

template <typename T, typename U>
void CaminoMasLargo(const Grafo<T, U>& G, const T& v1, const T& v2)
{
    T x;
    int cont=0;
    map<T,bool> visitados;

    //inicializa el diccionario a false;
    Conjunto<Vertice<T> > cv = G.vertices();
    while(!cv.esVacio())
    {
        x = cv.quitar().getObj();
        visitados[x] = false;
    }

    string s;
    string resp= "";
    s = "";
    CaminoMasLargo(G, v1, v2, visitados,s,resp,cont,0);

    cout<< s;

}

//Camino mas corto
template <typename T, typename U>
void CaminoMasCorto(const Grafo<T, U>& G, const T& v1, const T& v2,map<T,bool> &visitados,string &s,string resp, int &cont, int comparador)
{
    Conjunto<Vertice<T>> ady = G.adyacentes(v1);
    T x;
    visitados[v1]=true;
    stringstream ss;
    ss << v1;
    resp += ss.str()+"   ";
    comparador++;

    while(!ady.esVacio())
    {
        x=ady.quitar().getObj();
        if(x==v2 && (comparador<cont))
        {
            cont=comparador;
            stringstream ss2;
            ss2 <<x;
            s = resp;
            s += " "+ss2.str();
        }
        else if(!visitados[x])
            CaminoMasCorto(G,x,v2,visitados,s,resp,cont,comparador);

        visitados[x]=false;
    }
}

template <typename T, typename U>
void CaminoMasCorto(const Grafo<T, U>& G, const T& v1, const T& v2)
{
    T x;
    int cont=0;
    map<T,bool> visitados;

    //inicializa el diccionario a false;
    Conjunto<Vertice<T> > cv = G.vertices();
    while(!cv.esVacio())
    {
        x = cv.quitar().getObj();
        cont++;
        visitados[x] = false;
    }
    string s="";
    string resp= "";
    CaminoMasCorto(G, v1, v2, visitados,s,resp,cont,0);

    cout<< s;

}

//In out
template <typename T, typename U>
T InConectado(const Grafo<T, U>& G)
{
    map<T,float> visitados;
    T v;
    Conjunto<Vertice<T> > cv = G.vertices();
    Conjunto<Arista<T, float>> ca = G.aristas();
    while(!cv.esVacio())
    {
        v = cv.quitar().getObj(); //devuelve un vertice y lo elimina del conjunto
        visitados[v] = 0; //inicializo el valor de cada clave a 0
    }

    while(!ca.esVacio())
    {
        Arista<T, float> a = ca.quitar();
        visitados[a.getOrigen()]++;
        visitados[a.getDestino()]--;
    }

    for(typename map<T,float>::iterator it = visitados.begin() ; it != visitados.end() ; it++)
        if(it->second < 0)
            return it->first;
}

//Caminos simples

template <typename T, typename U>
void Caminos(const Grafo<T, U>& G, const T& v1, const T& v2)
{
    map<T,bool> visitados;
    T v;
    Conjunto<Vertice<T> > cv = G.vertices();

    while(!cv.esVacio())
    {
        v = cv.quitar().getObj(); //devuelve un vertice y lo elimina del conjunto
        visitados[v] = false; //inicializo el valor de cada clave a false
    }

    string s ="";
    string s2="";
    int i=1;
    Caminos(G,v1,v2,visitados,s,s2,i);
}

template <typename T, typename U>
void Caminos(const Grafo<T, U>& G, const T& v1, const T& v2, map<T,bool> &visitados, string &s, string s2,int &i)
{
    T x;
    visitados[v1]=true;
    Conjunto<Vertice<T>> ady = G.adyacentes(v1);

    stringstream ss;
    ss << v1;
    s2 += ss.str() + ", ";

    while(!ady.esVacio())
    {
        x=ady.quitar().getObj();
        if(x==v2)
        {
            s = s2;
            stringstream ss2;
            ss2 <<x;
            s += ss2.str();
            cout<<"Camino "<<i<< ": "<< s <<endl;
            i++;
        }
        else if(!visitados[x])
            Caminos(G,x,v2,visitados,s,s2,i);

        visitados[x]=false;
    }
}

//Grados de separacion
template <typename T, typename U>
void gradosSeparacion(const Grafo<T, U>& G, const T& v1, const T& v2,map<T,bool> &visitados,int act,int &solucion)
{
    Conjunto<Vertice<T>> ady = G.adyacentes(v1);
    T x;
    visitados[v1]=true;
    act++;

    while(!ady.esVacio())
    {
        x=ady.quitar().getObj();
        if(x==v2 && (act<solucion))
        {
            act++;
            solucion=act;
        }
        else if(!visitados[x])
            gradosSeparacion(G,x,v2,visitados,act,solucion);

        visitados[x]=false;
    }
}

template <typename T, typename U>
int gradosSeparacion(const Grafo<T, U>& G, const T& v1, const T& v2)
{
    T x;
    int solucion=0;
    map<T,bool> visitados;

    //inicializa el diccionario a false;
    Conjunto<Vertice<T> > cv = G.vertices();
    while(!cv.esVacio())
    {
        x = cv.quitar().getObj();
        solucion++;
        visitados[x] = false;
    }

    gradosSeparacion(G, v1, v2, visitados,0,solucion);

    return solucion;

}

template <typename T, typename U>
void hamiltoniano(const Grafo<T, U>& G, const T& v1, const T& v2,map<T,bool> &visitados,int act, bool &encontrado)
{
    Conjunto<Vertice<T>> ady = G.adyacentes(v1);
    T x;
    visitados[v1]=true;
    act--;

    while(!ady.esVacio() && !encontrado)
    {
        x = ady.quitar().getObj();
        if(act==1 && x==v2)
            encontrado=true;

        else if(!visitados[x])
            hamiltoniano(G,x,v2,visitados,act,encontrado);
    }

     visitados[x]=false;

}

template <typename T, typename U>
bool hamiltoniano(const Grafo<T, U>& G, const T& v1, const T& v2)
{
    T x;
    int num = 0;
    bool encontrado = false;
    map<T,bool> visitados;

    //inicializa el diccionario a false;
    Conjunto<Vertice<T> > cv = G.vertices();
    while(!cv.esVacio())
    {
        x = cv.quitar().getObj();
        visitados[x] = false;
        num++;
    }

    hamiltoniano(G,v1,v2,visitados,num,encontrado);
    return encontrado;
}


//********************************************************************//
int main()
{
    Grafo<int, float> G(7);
    for (int i = 1; i <= 7; i++) G.insertarVertice(i);
    G.insertarArista(2, 1, 4);
    G.insertarArista(1, 3, 3);
    G.insertarArista(1, 4, 2);
    G.insertarArista(1, 6, 1);
    G.insertarArista(6, 4, 5);
    G.insertarArista(4, 7, 3);
    G.insertarArista(5, 1, 6);

    Grafo<int, float> X(4);
    for (int i = 1; i <= 4; i++) X.insertarVertice(i);
    X.insertarArista(1, 2, 4);
    X.insertarArista(1, 3, 3);
    X.insertarArista(2, 4, 2);
    X.insertarArista(2, 3, 1);
    X.insertarArista(3, 2, 5);


    Grafo<string, float> H(7);
    H.insertarVertice("Huelva");
    H.insertarVertice("Lepe");
    H.insertarVertice("Niebla");
    H.insertarVertice("Mazagon");
    H.insertarVertice("Almonte");
    H.insertarVertice("Aljaraque");
    H.insertarVertice("Matalascañas");
    H.insertarArista("Lepe", "Huelva", 4);
    H.insertarArista("Huelva", "Niebla", 3);
    H.insertarArista("Huelva", "Mazagon", 2);
    H.insertarArista("Huelva", "Aljaraque", 1);
    H.insertarArista("Mazagon", "Almonte", 3);
    H.insertarArista("Mazagon", "Matalascañas", 4);
    H.insertarArista("Aljaraque", "Mazagon", 5);
    H.insertarArista("Almonte", "Huelva", 6);


    cout << " Vertice de maximo coste en G: " << verticeMaxCoste(G) << endl;
    cout << " Vertice de maximo coste en H: " << verticeMaxCoste(H) << endl;

    cout << endl << " Vertices inaccesibles en G: ";
    inaccesibles(G);

    cout << endl << " Camino entre Dos en H de Lepe a Almonte: ";
    cout << (caminoEntreDos(H, string("Lepe"), string("Almonte")) ? " SI " : " NO ") << endl;
    cout << endl << " Camino entre Dos en H de Aljaraque a Lepe: ";
    cout << (caminoEntreDos(H, string("Aljaraque"), string("Lepe")) ? " SI " : " NO ") << endl;

    cout << endl << " Caminos acotados en G a coste 9 desde el vertice 2:" << endl;
    caminosAcotados(G, 2, 9);

    cout << endl << endl << " Vertice outConectado en G: " << outConectado(G);
    cout << endl << " Vertice outConectado en H: " << outConectado(H);

    cout << endl << endl << " Vertice InConectado en G: " << InConectado(G);
    cout << endl << " Vertice InConectado en H: " << InConectado(H);

    cout << endl << endl << " Recorrido en profundidad de H desde el vertice Huelva:  ";
    recorrido_profundidad(H, string("Huelva"));

    cout << endl << endl << " Recorrido en anchura de H desde el vertice Huelva:  ";
    recorrido_anchura(H, string("Huelva"));


    cout<< endl << endl << " Camino mas largo desde 1 a 7: ";
    CaminoMasLargo(G,1,7);

    cout<< endl << endl << " Camino mas corto desde 1 a 7: ";
    CaminoMasCorto(G,1,7);

    cout<< endl << endl << " Grado de seperacion entre 1 a 7: ";
    cout<<gradosSeparacion(G,1,7);

    cout<< endl << endl << " Caminos entre 1 y 7: "<<endl;
    Caminos(G,1,7);

    cout<< endl << endl << "El grafo H es hamiltoniano: ";
    cout << (hamiltoniano(H, string("Lepe"), string("Almonte")) ? " SI " : " NO ");
    cout<< endl << endl << "El grafo X es hamiltoniano: ";
    cout << (hamiltoniano(X,1,4) ? " SI " : " NO ") << endl;

    cout << endl << endl;

    system("PAUSE");
    return EXIT_SUCCESS;
}
