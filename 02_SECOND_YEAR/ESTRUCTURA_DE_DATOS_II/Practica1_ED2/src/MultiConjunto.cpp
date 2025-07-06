#include "MultiConjunto.h"

template <typename T> MultiConjunto<T>::MultiConjunto()
{
    num=0;
}

template <typename T> bool MultiConjunto<T>::esVacio() const
{
    if(num==0)
        return true;
    else
        return false;
}

template <typename T> int MultiConjunto<T>::cardinalidad() const
{
    return num;
}

template <typename T> void MultiConjunto<T>::anade(const T& objeto)
{
    if(num<100)
    {
        c[num]=objeto;
        num++;
    }
    else
    {
        cout<<"Vector completo"<<endl;
    }
}

template <typename T> void MultiConjunto<T>::elimina(const T& objeto)
{
    int i=0;
    bool encontrado=false;
    while(!encontrado && i<num)
    {
        if(c[i]==objeto)
        {
            encontrado=true;
            for(int j=i; j<num; j++)
                c[j]=c[j+1];
            num--;
        }
        else
            i++;
    }
}

template <typename T> bool MultiConjunto<T>::pertenece(const T& objeto) const
{
    int i=0;
    bool encontrado=false;
    while(!encontrado && i<num)
    {
        if(c[i]==objeto)
            encontrado=true;
        else
            i++;
    }
    return encontrado;
}

template <typename T> void MultiConjunto<T>::ver() const
{
    for(int i=0; i<num; i++)
        cout<<c[i]<<endl;

}

template class MultiConjunto<int>;
template class MultiConjunto<char>;
template class MultiConjunto<Persona>;
