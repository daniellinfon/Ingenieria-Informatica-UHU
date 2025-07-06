package libClases;

import java.util.Scanner;

public class Empresa implements Cloneable{
	
	 
	 private int ncli; //para saber cuántos clientes hay en el array (al principio 0)
	 //private int nmaxcli;
	 private Cliente []Clientes;
	    
	public Empresa() {
		ncli=0;
		//nmaxcli=10;
		Clientes= new Cliente[10];
	}
	 
	public Empresa(Empresa e) {
		ncli=e.ncli;
		//nmaxcli=e.nmaxcli;
		Clientes=new Cliente[e.Clientes.length];
		for(int i=0;i<ncli;i++) {
			//Clientes[i]= (Cliente) e.Clientes[i].clone();	
		if(e.Clientes[i].getClass()==ClienteMovil.class)
			Clientes[i]= (ClienteMovil) e.Clientes[i].clone();	
		else
			Clientes[i]= (ClienteTarifaPlana) e.Clientes[i].clone();	
		}
	}

	protected int buscarCliente(String dni)  //si no existe devuelve -1 
	{
	    int i=0,pos=-1;
	    boolean encontrado=false;
	    while(i<ncli && !encontrado)
	    {
	        if(Clientes[i].getNif().equals(dni))
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

	public void alta() {
		Scanner sc = new Scanner(System.in);
	    int pos, tipo;
		float minHab;
		
	    if(ncli==Clientes.length){//Ampliar vector
			//nmaxcli=nmaxcli*2;
			Cliente [ ] aux =Clientes;
			Clientes = new Cliente[2*Clientes.length];
			for(int i = 0 ; i < aux.length; i++) 
			 Clientes[i]=aux[i];
		}
		
		if(ncli<Clientes.length) {
			 
			System.out.print("Introduce DNI: ");
			String dni= sc.nextLine(); //guarda el valor de teclado en dni
			pos=buscarCliente(dni);
		
			if(pos==-1) {
		    
		        
		        System.out.print("Introduce Nombre: ");
		        String nombre = sc.nextLine();
	
		        System.out.print("--Fecha Nacimiento--\n"); 
		        Fecha fecha= new Fecha(Fecha.pedirFecha());
		        
		        System.out.print("--Fecha de Alta--\n");
				Fecha fecha2= new Fecha(Fecha.pedirFecha());
				
				
		       do{
		        	System.out.print("Tipo de Contrato a abrir (1-Movil, 2-Tarifa Plana): ");
			        tipo = sc.nextInt();
			        sc.nextLine();
			        if(tipo!=1 && tipo!=2)
			        	System.out.println("Intentelo de nuevo...");
		        	
		        }while(tipo!=1 && tipo!=2);
		       
		        System.out.print("Introduce los minutos hablados: ");
		        minHab = sc.nextFloat();
		        sc.nextLine();
		        	  if(tipo==2) { //ClienteTP
		        		  
		        		  System.out.print("Introduce la Nacionalidad: ");
		        		  String nac= sc.next();
		        		  sc.nextLine();
		        		  Clientes[ncli]= new ClienteTarifaPlana(dni,nombre, fecha, fecha2,minHab,nac);
		        		  ncli++;
		        	  }
		        	  else if(tipo==1) { //ClienteMovil
		        		  System.out.println("Introduce Precio Minuto: ");
				        	float precio = sc.nextFloat();
				        	sc.nextLine();
				        	System.out.print("--Fecha de Permanencia--\n");
				        	Fecha fper= new Fecha(Fecha.pedirFecha());
				        	
				        	Clientes[ncli]= new ClienteMovil(dni,nombre, fecha, fecha2, fper,minHab,precio);
				        	ncli++;
		        	  }
		        
		        	}
			else {
				System.out.println("Ya existe un cliente con ese DNI: ");
				if(Clientes[pos].getClass()==ClienteMovil.class)
					System.out.println(((ClienteMovil) Clientes[pos]).toString());
				else
					System.out.println(((ClienteTarifaPlana) Clientes[pos]).toString());
					
			}
		}
		//sc.close();
	}
	
	public void alta(Cliente c) {
		int pos=buscarCliente(c.getNif());
		if(ncli==Clientes.length){
			//Ampliar vector
			//nmaxcli=nmaxcli*2;
			Cliente [ ] aux =Clientes;
			Clientes = new Cliente[Clientes.length*2];
			for(int i = 0 ; i < aux.length; i++) 
			 Clientes[i]=aux[i];
		}
		
		if(ncli<Clientes.length) {
			if(pos==-1) {
			Clientes[ncli]= c;
			ncli++;
			}
			else {
				System.out.println("Ya existe un cliente con ese DNI: ");
			if(Clientes[pos].getClass()==ClienteMovil.class)
				System.out.println(((ClienteMovil) Clientes[pos]).toString());
			else
				System.out.println(((ClienteTarifaPlana) Clientes[pos]).toString());
			}	
		}
		
	}
	
	public void baja() {
		System.out.println("Introduzca nif cliente a dar de baja: ");
		Scanner sc = new Scanner(System.in);
		String dni= sc.nextLine();
		int pos=buscarCliente(dni);
		if(pos==-1)
			System.out.println("No existe un cliente con DNI: "+dni);
		else {
			System.out.print(Clientes[pos].toString()+"\n¿Seguro que desea eliminarlo (s/n)? ");
			String op=sc.next();
			if(op.equals("n"))
				System.out.println("El cliente con nif "+dni+" no se elimina");
			else
				baja(dni);
		}
		//sc.close();
	}
	
	public void baja(String dni) {
		int pos;
		pos=buscarCliente(dni);
		if(pos==-1) //No existe
			System.out.println("No existe un cliente con DNI: "+dni);
		else {
			System.out.println("El cliente "+Clientes[pos].getNombre()+" con nif "+dni+" ha sido eliminado\n");
			for(int i=pos;i<ncli;i++) 
				Clientes[i]=Clientes[i+1];
			ncli--;
		}
	}
	
	public String toString() { //devuelve una cadena con la información de la empresa
		
		String s="", s2="";
		for(int i=0;i<ncli;i++){
			s2=Clientes[i].toString();
			s=s+s2+"\n";
		}
			
		return s;
	}

	public float factura() {
		float suma=0;
		for(int i=0; i<ncli; i++) {
	        if(Clientes[i].getClass()==ClienteMovil.class)
	        	suma=suma+((ClienteMovil) Clientes[i]).factura();
	        else
				suma=suma+((ClienteTarifaPlana) Clientes[i]).factura();
		}    
		return suma;
	}

	public int nClienteMovil() {
		int contador=0;
	    for(int i=0; i<ncli; i++)
	        if(Clientes[i].getClass()==ClienteMovil.class)
	            contador++;

	    return contador;
	}

	public int getN() {return ncli;}

	public void descuento(float num) {
		float precioRebajado, precio;
	    for(int i=0; i<ncli; i++)
	    {
	        if(Clientes[i].getClass()==ClienteMovil.class)
	        {
	            precio=((ClienteMovil) Clientes[i]).getPrecio();
	            precioRebajado= precio -((precio*num)/100);
	           ((ClienteMovil) Clientes[i]).setPrecio(precioRebajado);
	        }
	    }
		
	}
	
	public Object clone() {
		 return new Empresa(this);
		 
		/* Object obj=null;
		 try {
		 
		 obj=super.clone(); //se llama al clone() de la clase base (Object)
		 //que hace copia binaria de los atributos
		 
		 
		 } catch(CloneNotSupportedException ex) {
		 System.out.println(" no se puede duplicar");
		 }
		 return obj;*/
	}

	
	public int eliminarPeoresClienteMovil() {
		int cont=0;
		float media=0,div=0,Media,fact=0;
		for(int i=0;i<ncli;i++) {
			if(Clientes[i].getClass()==ClienteMovil.class) {
				media= media + ((ClienteMovil) Clientes[i]).factura();
				div++;
			}	
		}
		Media=media/div;
		//System.out.println(Media);
		for(int j=ncli;j>=0;j--) {
			fact=0; 
			if(Clientes[j] instanceof ClienteMovil) {
				fact= ((ClienteMovil) Clientes[j]).factura();
				if(fact<Media) {
					Clientes[j].ver();
					baja(Clientes[j].getNif());
					cont++;
				}
			}
		}
		return cont;
	}
	
	public void listarClienteMasJoven() {
		int minimo=0,pos=0;
		int aux;
		for(int i=0;i<ncli;i++) {
			if(i==0){//primera pasada
				minimo= Fecha.convertir(Clientes[i].getFechaNac());
				pos=i;
			}
			if(i>0) {
				if(minimo<Fecha.convertir(Clientes[i].getFechaNac())) {
					minimo= Fecha.convertir(Clientes[i].getFechaNac());
					pos=i;
				}
			}
		}
		//Clientes[pos].ver();
		for(int j=0;j<ncli;j++) {
			aux=Fecha.convertir(Clientes[j].getFechaNac());
			if(minimo==aux)
				Clientes[j].ver();
		}
	}
	
	public void bajaTPNac(String nac, int anio) {
		for(int i=0;i<ncli;i++) {
			if(Clientes[i].getClass()==ClienteTarifaPlana.class) {
				ClienteTarifaPlana m= (ClienteTarifaPlana) Clientes[i];
				if(m.getNacionalidad().equals(nac) && m.getFechaAlta().getAnio()==anio) {
					baja(Clientes[i].getNif());
				}
			}
		}
	}
	
	public static int subir(Empresa e, float precio, int anio) {
		int cont=0;
		for(int i=0;i<e.ncli;i++) {
			if(e.Clientes[i].getClass()==ClienteMovil.class) {
				ClienteMovil m= (ClienteMovil)e.Clientes[i];
					if(m.getFechaAlta().getAnio()==anio)
						m.setPrecio(precio);
				cont++;
			}
		}
		return cont;
	}

}