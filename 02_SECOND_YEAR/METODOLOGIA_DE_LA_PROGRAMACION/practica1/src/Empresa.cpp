#include "Empresa.h"
#include <typeinfo>
#include <cstring>
typedef char cadena[50];
Empresa::Empresa():nmaxcli(100)
{
    this->ncli=0;
    this->ncon=0;
    this->contratos=new Contrato *[10]; //inicialmente capacidad para 10 Contratos
    this->nmaxcon=10;
}
//el destructor debe, además de eliminar el array dinámico creado en el constructor,
//eliminar los objetos clientes y contratos apuntados por ambos arrays

Empresa::~Empresa()
{
    for(int i=0; i<this->ncon; i++)   //primero elimino los objetos contratos
    {
        delete this->contratos[i];
    }
    delete [] this->contratos; //luego elimino el array de punteros
    for(int i=0; i<this->ncli; i++)   //primero elimino los objetos contratos
    {
        delete this->clientes[i];
    }
//delete [] this->clientes; //ERROR el array clientes no es dinámico
}

//método auxiliar usado por el método crearContrato
int Empresa::altaCliente(Cliente *c)   //añade cliente apuntado por c al array clientes
{
    int pos=-1; //devuelve -1 si no cabe y la posición donde
    if (this->ncli<nmaxcli)   //donde lo he metido si cabe
    {
        this->clientes[this->ncli]=c;
        pos=this->ncli;
        this->ncli++;
    }
    else
    {
        cout << "Lo siento, el cupo de clientes esta lleno";
        pos=-1;
    }
    return pos;
}

//método auxiliar usado por el método crearContrato
int Empresa::buscarCliente(long int dni) const   //si no existe devuelve -1 y si existe
{
    int i=0,pos=-1;
    bool encontrado=false;
    while(i<ncli && !encontrado)
    {
        if(clientes[i]->getDni()==dni)
        {
            pos=i;
            encontrado=true;
        }
        else
            i++;

    }
    return pos;
    //con ese dni en el array clientes
}

void Empresa::crearContrato()   //EL ALUMNO DEBE TERMINAR DE IMPLEMENTAR ESTE METODO
{
    long int dni;
    int pos;
    cout << "Introduce DNI: ";
    cin >> dni;
//supongo que hay un metodo buscarCliente(dni) que devuelve -1 si no existe y si
//existe devuelve la posicion del cliente en el array this->cli

    pos=this->buscarCliente(dni); //OJO ESTE METODO HAY QUE IMPLEMENTARLO
    if (pos==-1)   //el cliente no existe y hay que darlo de alta
    {
        int dia, mes, anio;
        cadena nombre;
        Cliente *c; //NO CREO NINGUN CLIENTE SINO SOLO UN PUNTERO A CLIENTE
        cout << "Introduce Nombre: ";
        fflush(stdin);
        gets(nombre);
        cout << "Fecha de nacimiento: " "\ndia: ";
        cin>>dia;
        cout<<"mes: ";
        cin>>mes;
        cout<<"anio: ";
        cin>>anio;;

        c=new Cliente(dni, nombre, Fecha(dia, mes, anio));
        pos=this->altaCliente(c); //OJO HAY QUE IMPLEMENTARLO
    }

//viendo cuanto vale la variable pos sé si el cliente se ha dado de alta o no
    if (pos!=-1)   //el cliente existe o se ha dado de alta
    {
        int op;
        int dia, mes, anio, minHablados;
        float PrecioMin;
        char *nac,*cor;
        cadena nacion, correo;
        do
        {
            cout<<"Tipo de Contrato a abrir (1-Tarifa Plana, 2-Movil): ";
            cin>>op;
            if(op!=1 && op!=2)
                cout<<"Intentelo de nuevo..."<<endl;
        }
        while(op!=1 && op!=2);

        cout<<"Fecha del contrato: ""\ndia: ";
        cin>>dia;
        cout<<"mes: ";
        cin>>mes;
        cout<<"anio: ";
        cin>>anio;
        cout<<"minutos hablados: ";
        cin>>minHablados;
        cout<<"Correo: ";
        fflush(stdin);
        gets(correo);
        cor=correo;
        if(op==1) //Contrato TP
        {

            contratos[ncon] =new ContratoTP(dni,Fecha(dia,mes,anio),minHablados,cor);
            ncon++;
            cout<<"Contrato TP creado"<<endl<<endl;
        }
        else //Contrato Movil
        {
            cout<<"Precio minuto: ";
            cin>>PrecioMin;
            cout<<"Nacionalidad: ";
            fflush(stdin);
            gets(nacion);
            nac = nacion;
            contratos[ncon] =new ContratoMovil(dni,Fecha(dia,mes,anio),PrecioMin,minHablados,nac);
            ncon++;
            cout<<"Contrato Movil creado"<<endl<<endl;
        }

    }
}

void Empresa::cargarDatos()
{
    Fecha f1(29,2,2001), f2(31,1,2002), f3(1,2,2002);
    this->clientes[0] = new Cliente(75547001, "Peter Lee", f1);
    this->clientes[1] = new Cliente(45999000, "Juan Perez", Fecha(29,2,2000));
    this->clientes[2] = new Cliente(37000017, "Luis Bono", f2);
    this->ncli=3;
    this->contratos[0] = new ContratoMovil(75547001, f1, 0.12, 110, "DANES"); //habla 110m a 0.12€/m
    this->contratos[1] = new ContratoMovil(75547001, f2, 0.09, 170, "DANES"); //habla 170m a 0.09€/m
    this->contratos[2] = new ContratoTP(37000017, f3, 250, "PEPE"); //habla 250m (300m a 10€, exceso 0.15€/m)
    this->contratos[3] = new ContratoTP(75547001, f1, 312, "JUAN"); //habla 312m (300m a 10€, exceso 0.15€/m)
    this->contratos[4] = new ContratoMovil(45999000, f2, 0.10, 202, "ESPAÑOL"); //habla 202m a 0.10/m
    this->contratos[5] = new ContratoMovil(75547001, f2, 0.15, 80, "DANES"); //habla 80m a 0.15€/m
    this->contratos[6] = new ContratoTP(45999000, f3, 400, "ANA"); //habla 400m (300m a 10€, exceso 0.15€/m)
    this->ncon=7;
}

void Empresa::ver() const
{
    cout<<"La empresa tiene "<<ncli<<" clientes y "<<ncon<<" contratos" <<endl;
    cout<<"Clientes: "<<endl;
    for(int i=0; i<ncli; i++)
        cout<<*clientes[i]<<endl;
    cout<<"\nContratos: "<<endl;
    for(int j=0; j<ncon; j++)
    {
        contratos[j]->ver(); //vitual ver() en Contrato
        cout<<endl;

        /*if(typeid(*contratos[j]) == typeid(ContratoTP)) //contrato TP
            cout<<*(( ContratoTP*)contratos[j]; //operator<< de ContratoTP

        else //Contrato movil
            (( ContratoMovil*)contratos[j])->ver(); //metodo ver de ContratoMovil
        cout<<endl;*/
    }
}

int Empresa::nContratosTP() const
{
    int contador=0;
    for(int i=0; i<ncon; i++)
        if(typeid(*contratos[i]) == typeid(ContratoTP))
            contador++;

    return contador;
}

bool Empresa::cancelarContrato(int idContrato)
{
    int i=0;
    bool encontrado=false;
    while(i<ncon && !encontrado)
    {
        if(contratos[i]->getIdContrato()==idContrato)
        {
            delete contratos[i];
            for(int j=i; j<ncon; j++)
                contratos[j]=contratos[j+1];
            ncon--;
            encontrado=true;
        }
        else
            i++;
    }
    if(!encontrado)
        cout<<"No existe el contrato..."<<endl;

    return encontrado;
}

bool Empresa::bajaCliente(long int dni)
{
    int pos=buscarCliente(dni), j=0;
    bool encontrado=false;

    if(pos!=-1)
    {
        //Primero borramos el cliente
        delete clientes[pos];
        for(int i=pos; i<ncon; i++)
            clientes[i]=clientes[i+1];
        ncli--;
        //Y luego sus contratos
        while(j<ncon)
        {
            if(contratos[j]->getDniContrato()==dni)
                cancelarContrato(contratos[j]->getIdContrato());

            else
                j++;
        }
        encontrado=true;
    }
    else
        cout<<"No existe el cliente..."<<endl;
    return encontrado;
}
/*
int Empresa::descuento (float porcentaje) const
{
    float precioRebajado, precio;
    int contador=0;
    for(int i=0; i<ncon; i++)
    {
        if(Contrato *c=dynamic_cast <ContratoMovil*>(contratos[i]))
        {
            precio=(( ContratoMovil*)contratos[i])->ContratoMovil::getPrecioMinuto();
            precioRebajado= precio -((precio*porcentaje)/100);
            (( ContratoMovil*)contratos[i])->ContratoMovil::setPrecioMinuto(precioRebajado);
            contador++;
        }
    }
    return ncon-contador;
}
*/
int Empresa::descuento (float porcentaje) const
{
    float precioRebajado, precio;
    int contador=0;
    for(int i=0; i<ncon; i++)
    {
        if(ContratoMovil *c=dynamic_cast <ContratoMovil*>(contratos[i]))
        {
            precio=c->getPrecioMinuto();
            precioRebajado= precio -((precio*porcentaje)/100);
            c->ContratoMovil::setPrecioMinuto(precioRebajado);
            contador++;
        }
    }
    return contador;
}

int Empresa::eliminarPeorContratosTP()
{
    float minimo,aux;
    int contador=0;
    for(int i=0; i<ncon; i++)//para ver cual es la factura minima recorremos todos los ContratosTP
    {
        if(ContratoTP *c=dynamic_cast <ContratoTP*>(contratos[i]))
        {
            aux=c->factura();

            if(i==0)
                minimo=aux;

            else if(aux<minimo)
                minimo=aux;

        }
    }
    //Ahora que tenemos la factura minima, eliminamos los que sean igual o menor
    for(int j=0; j<ncon; j++)
    {
        if(ContratoTP *con=dynamic_cast <ContratoTP*>(contratos[j]))
        {
            if(con->factura()<=minimo)
            {
                cancelarContrato(contratos[j]->getIdContrato());
                contador++;
            }

        }
    }
    return contador;
}

int Empresa::rebaja(Empresa& e, float num)
{
    int contador;
    float precio;
    for(int i=0; i<e.getNcon(); i++)
    {
        if(ContratoMovil *c=dynamic_cast <ContratoMovil*>(e.contratos[i]))
        {
            precio= c->getPrecioMinuto()- num;
            if(precio<0)
              c->setPrecioMinuto(0);
            else
              c->setPrecioMinuto(precio);
        }
    }
    return contador;
}


