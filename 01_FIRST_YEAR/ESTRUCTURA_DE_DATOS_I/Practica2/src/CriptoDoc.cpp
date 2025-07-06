#include "CriptoDoc.h"

bool CriptoDoc::leer(char fichero[])
{
    ifstream fich;
    bool lectura;
    linea leido;
    fich.open(fichero);
    if(!fich.fail())
    {
        getline(fich,leido);
        while(!fich.eof())
        {
           texto.anadirDch(leido);
           getline(fich,leido);
        }

        return lectura=true;
    }
    else
        return lectura=false;

    fich.close();
}

void CriptoDoc::vaciar()
{
    if(!texto.esvacia())
    {
        while(!texto.esvacia())
        {
            texto.eliminarDch();
        }
    }
    else
        cout<<"La lista ya esta vacia..."<<endl;
}

bool CriptoDoc::escribir(char fichero[])
{
    ofstream fich2(fichero);
    bool escritura;
    linea text;

    if(!fich2.fail())
    {
        for(int i=1; i<=texto.longitud();i++)
        {
            text=texto.observar(i);
           ;
        } fich2<<text<<endl
        fich2.close();

        return escritura=true;
    }
    else
    {
        return escritura=false;
            fich2.close();
    }

}

int CriptoDoc::numlineas()
{
    return texto.longitud();
}

void CriptoDoc::cifrar(int codigo)
{
    int num;
    linea cifra;
    for(int i=1; i<=texto.longitud(); i++)
    {
        cifra = texto.observar(i);
        for(int j=0; j<cifra.length();j++)
        {
            cifra[j] = cifra[j] + codigo;
            if(cifra[j]>255)
                cifra[j]=cifra[j]-256;
        }
        texto.modificar(i,cifra);
    }
}

void CriptoDoc::descifrar(int codigo)
{
    int num;
    linea cifra;
    for(int i=1; i<=texto.longitud(); i++)
    {
        cifra = texto.observar(i);
        for(int j=0; j<cifra.length();j++)
        {
            cifra[j] = cifra[j] - codigo;
            if(cifra[j]<0)
                cifra[j]=cifra[j]+256;
        }
        texto.modificar(i,cifra);
    }
}

void CriptoDoc::concatenar(CriptoDoc &doc)
{
    for(int i=1; i<=doc.numlineas();i++)
        texto.anadirDch(doc.observar(i));
}

linea CriptoDoc::observar(int i)
{
    return texto.observar(i);
}
