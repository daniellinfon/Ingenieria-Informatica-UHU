#include <Prueba.h>
#include <Clasificacion.h>
#include <iostream>
#include <fstream>
using namespace std;

int marcas(int s)
{
    return (rand()%s +7000);
}

void tiempo(int segundos)
{
    int horas = segundos / 3600;
    int minutos = (segundos%3600)/60;
    segundos = ((segundos%3600)%60);

    cout<<horas<<" : "<<minutos<<" : "<<segundos;
}

Prueba::Prueba(char FicheroOrigen[],char FicheroDestino[])
{
    fich.open(FicheroOrigen, ios::binary|ios::in);
    fichero.open(FicheroDestino, ios::binary|ios::out);
    if(fich.fail()) //si no existe fichero
    {
        numCiclistas=0;
        fichero.write((char*)&numCiclistas, sizeof(numCiclistas));
    }
    else //si existe
    {
        if(fichero.fail())
        {
            cout<<"Se produce Error\n";
        }
        else
        {
            int num=0;
            numCiclistas=0;
            Ciclista c;
            fich.read((char*)&num, sizeof(int)); //lee un entero (4)
            fichero.write((char*)&numCiclistas, sizeof(int)); //escribe el 4
            while(!fich.eof())
            {
                numCiclistas+=num;
                for(int i=0; i<num; i++)
                {
                    fich.read((char*)&c, sizeof(Ciclista));
                    if(fich.fail())
                        cout<<"Se produce Error\n";
                    else
                        fichero.write((char*)&c, sizeof(Ciclista));
                }
                fich.read((char*)&num, sizeof(int));
            }
            fichero.seekp(0,ios::beg);//me posiciono al principio del fichero
            fichero.write((char*)&numCiclistas, sizeof(int)); //sobreescribo  el num de ciclistas totales (9) sobre  el 4
            fich.close();
            fichero.close();
            fichero.open(FicheroDestino, ios::binary|ios::in|ios::out);

            //Examen

            paises = new PaisParticipante[SALTO2];
            if (paises!=NULL)
            {
                tamPaises=SALTO2;
                numPaises=0;
            }
            else
            {
                tamPaises=numPaises=-1;
            }

        }
    }
}

Prueba::~Prueba()
{
    fichero.close();
}

int Prueba::getNumCiclistas()
{
    return numCiclistas;
}

void Prueba::mostrar(cadena pais)
{

    bool encontrado=false;
    Ciclista c;
    fichero.seekg(0, ios::beg);
    if((int)fichero.tellg()!=0)
    {
        cout<<"Se produce Error\n";
    }
    else
    {
        fichero.read((char*)&numCiclistas, sizeof(int));
        if(strcmp(pais, "*")==0)
            cout<<"Ciclistas: "<<numCiclistas<<endl<<"--------------------------"<<endl<<endl;
        else
            cout<<pais<<endl<<"--------------------------"<<endl<<endl;

        for(int i=0; i<numCiclistas; i++)
        {
            fichero.read((char*)&c, sizeof(Ciclista));
            if(strcmp(pais, "*")==0||strcmp(c.pais, pais)==0)
            {
                cout<<"Dorsal: "<<c.dorsal<<"\n"<<"Pais: "<<c.pais<<"\n"<<"Nombre: "<<c.nombre<<"\n"<<"Apellidos: "<<c.apellidos<<endl<<endl;
                encontrado=true;
            }
        }
        if(!encontrado)
            cout<<"\nERROR: Pais no encontrado\n";
    }
}

Ciclista Prueba::consultar(int posicion)
{
    Ciclista c;
    fichero.seekg(sizeof(int)+(posicion-1)*sizeof(c), ios::beg);// me posiciono al principio y me salto el numciclistas y los ciclistas que van por delante del que buscamos
    if((int)fichero.tellg()!=sizeof(int)+(posicion-1)*sizeof(c))
    {
        cout<<"Se produce Error\n";
    }
    else
    {
        fichero.read((char*)&c, sizeof(c));
        return c;
    }
}

int Prueba::buscar(int dor)
{
    Ciclista cic;
    int posicion=-1, x=0;
    bool encontrado=false;
    fichero.seekg(sizeof(int), ios::beg);
    if((int)fichero.tellg()!=sizeof(int))
    {
        cout<<"Se produce Error\n";
    }
    else
    {
        while(!encontrado&&x<numCiclistas)
        {
            fichero.read((char*)&cic, sizeof(cic));
            if (cic.dorsal!=dor)
                x++;
            else
                encontrado=true;
        }
        if(encontrado)
            posicion=x+1;
        return posicion;
    }
}

void Prueba::insertar(Ciclista c)
{
    Ciclista cic, cicAux;
    int j=0;
    if(buscar(c.dorsal)!=-1)
        cout<<"Error: ***Ciclista YA INSCRITO***"<<endl;
    else
    {
        fichero.seekg(sizeof(int), ios::beg);
        bool encontrado=false;
        while(!encontrado&&j<numCiclistas)
        {
            fichero.read((char*)&cic, sizeof(cic));
            if (strcmp(cic.pais, c.pais)!=0)
                j++;
            else
                encontrado=true;
        }
        if(!encontrado)//se añade un ciclista con nueva nacionalidad
        {
            fichero.seekp(sizeof(int)+j*sizeof(cic), ios::beg);
            fichero.write((char*)&c, sizeof(c));
        }
        else
        {
            fichero.seekg(sizeof(int)+j*sizeof(cic), ios::beg);
            fichero.read((char*)&cic, sizeof(cic));//leo el primer ciclista de un pais para guardar sus datos
            fichero.seekp(sizeof(int)+j*sizeof(cic), ios::beg);
            fichero.write((char*)&c, sizeof(c));//sobreescribo el nuevo ciclista sobre el que hemos guardado sus datos

            //desplazamiento
            int x=j+1;
            for(int i=j+1; i<numCiclistas; i++)
            {
                cicAux=cic;//cicAux primero es el ciclista del que guardamos sus datos
                fichero.seekg(sizeof(int)+i*sizeof(cic), ios::beg);
                fichero.read((char*)&cic, sizeof(cic));//leo el cilista de la siguiente posicion para guardar sus datos
                fichero.seekp(sizeof(int)+i*sizeof(cicAux), ios::beg);
                fichero.write((char*)&cicAux, sizeof(cicAux));//lo sobreescribo
                x++;
            }
            fichero.seekp(sizeof(int)+x*sizeof(cicAux), ios::beg);
            fichero.write((char*)&cicAux, sizeof(cicAux));//añado el el ultimo ciclista
        }
        numCiclistas++;
        fichero.seekp(0, ios::beg);
        fichero.write((char*)&numCiclistas, sizeof(numCiclistas));//escribo el nuevo numero d ciclistas
    }
}

void Prueba::modificar(Ciclista c, int posicion)
{
    Ciclista cic;
    cic=consultar(posicion);
    //if(cic.dorsal!=c.dorsal)
    //cout<<"ERROR: El ciclista no existe\n";
    //else
    //{

    strcpy(c.pais,cic.pais);
    fichero.seekp(sizeof(int)+(posicion-1)*sizeof(cic), ios::beg);
    fichero.write((char*)&c, sizeof(c));//escribo al ciclista modificado segun el pais
    //}
}

void Prueba::eliminar(int posicion)
{
    if(posicion>numCiclistas)
        cout<<"ERROR: Posicion no existe\n";
    else
    {
        if(posicion-1==numCiclistas-1)
        {
            numCiclistas--;
            fichero.seekp(0, ios::beg);
            fichero.write((char*)&numCiclistas, sizeof(int));
        }
        else
        {
            Ciclista cic;
            for(int i=posicion-1; i<numCiclistas-1; i++)
            {
                //sobrescribir y desplazarº
                fichero.seekg((sizeof(int))+((i+1)*sizeof(Ciclista)), ios::beg);
                fichero.read((char*)&cic, sizeof(Ciclista));
                fichero.seekp((sizeof(int))+((i)*sizeof(Ciclista)), ios::beg);
                fichero.write((char*)&cic, sizeof(Ciclista));
            }
            numCiclistas--;
            fichero.seekp(0, ios::beg);
            fichero.write((char*)&numCiclistas, sizeof(int));
        }
    }
}

void Prueba::Competicion()
{
    Clasificacion carrera;
    Participante part;
    Ciclista cic;
    for(int i=1; i<=numCiclistas; i++)
    {
        int marc=marcas(3000);
        fichero.seekg(sizeof(int)+(i-1)*sizeof(Ciclista), ios::beg);
        fichero.read((char*)&cic, sizeof(cic));
        part.dorsal=cic.dorsal;
        part.marca=marc;
        part.indice=i;
        carrera.anadirparticipante(part);
    }
    carrera.ordenar();
    int participantes=carrera.numparticipantes();
    cout<<"\nClasficacion\t\t\tNombre   -    Dorsal     -    Pais\t\t\tTiempo"<<endl<<endl;
    for(int i=1; i<=participantes; i++)
    {
        part=carrera.consultar(i);
        fichero.seekg(sizeof(int)+(part.indice-1)*sizeof(Ciclista), ios::beg);
        fichero.read((char*)&cic, sizeof(cic));
        cic.marca=part.marca;
        cic.posicion=i;
        modificar(cic, part.indice);
        cout<<"    "<< i <<"\t\t\t\t"<<cic.nombre<< "\t\t"<<cic.dorsal<<"\t   "<<cic.pais<<"\t\t\t";
        tiempo(part.marca);
        cout<<endl<<endl;
    }
}

void Prueba::generarInfoPaises()
{

    Ciclista c;
    cadena paisaux;
    if(tamPaises==numPaises)
    {
        PaisParticipante *nuevo=new PaisParticipante[tamPaises+SALTO];
        if(nuevo!=NULL)
            for(int i=0; i<numPaises; i++)
                nuevo[i]=paises[i];
        if(paises!=NULL)
            delete[]paises;

        paises=nuevo;
        tamPaises+=SALTO2;

    }
    if(numPaises<tamPaises)
    {

        fichero.seekg(0, ios::beg);
        if((int)fichero.tellg()!=0)
        {
            cout<<"Se produce Error\n";
        }
        else
        {



            for(int i=0; i<numCiclistas; i++)
            {
                if(numPaises==0)
                {
                    fichero.read((char*)&numCiclistas, sizeof(int));
                    fichero.read((char*)&c, sizeof(Ciclista));
                    numPaises++;
                    strcpy(paises[i].nombrePais,c.pais);
                    strcpy(paisaux,c.pais);
                    paises[i].numParticipantes++;
                }
                else
                {

                    fichero.read((char*)&c, sizeof(Ciclista));
                    if(strcmp(c.pais,paisaux)!=0)
                    {
                        strcpy(paisaux,c.pais);
                        numPaises++;
                        strcpy(paises[numPaises].nombrePais,c.pais);

                    }
                    else
                    {

                        paises[numPaises-1].numParticipantes++;

                    }
                }

            }
        }
    }
}
void Prueba::mostrarInfoPaises()
{
    for(int i=0; i<numPaises;i++)
    {
        cout<<paises[i].nombrePais<<": ";
        cout<<paises[i].numParticipantes;

    }
}
