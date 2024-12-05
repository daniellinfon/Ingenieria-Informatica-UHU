package libClases;

public class ClienteTarifaPlana extends Cliente {

	  private static float minutosTP=300;
	  private static float precioTP=20;
	  private float minutosHablados;
	  private String nacionalidad;
	  private static float precioExcesoMinutos= (float) 0.15;
	  
	  
public ClienteTarifaPlana(String NIF, String nom, Fecha fNac, Fecha fAlta, float min, String nac) {
		super(NIF, nom, fNac, fAlta);
		minutosHablados=min;
		nacionalidad=nac;
	}
	  
public ClienteTarifaPlana(String NIF, String nom, Fecha fNac, float min, String nac) {
		super(NIF, nom, fNac);
		minutosHablados=min;
		nacionalidad=nac;
}	  
	  
public void setNacionalidad(String nac)	{
	nacionalidad=nac;
}

public void setMinutos(float m) {
	minutosHablados=m;
}

public void ver() {
	System.out.println(this/*.toString()*/);
}

public static float getLimite() {
	return minutosTP;
}

public static void setTarifa(float m, float precio) {
	minutosTP=m;
	precioTP=precio;
}

public static float getTarifa() {
	return precioTP;
}

public String toString() { //devuelve una cadena con la información del cliente
	String s=""; //805W 08/03/1980: Pepe (10 - 04/04/2004) India [300.0 por 20.0] 500.0 --> 50.0 
	s= /*getNif()+" "+getFechaNac()+": "+getNombre()+" ("+getCodCliente()+" - "+getFechaAlta()+")"*/super.toString()+" "+getNacionalidad()+" ["+minutosTP+" por "+precioTP+"] "+minutosHablados+" --> "+factura();
	return s;
}

public String getNacionalidad() {
	return nacionalidad;
}

public float factura() {
		  float exceso;
		    if(minutosHablados>minutosTP)
		        exceso=minutosHablados-minutosTP;
		    else
		        exceso=0;
		    return precioTP+(exceso*precioExcesoMinutos);
	  }

public Object clone() {
	 return new ClienteTarifaPlana(super.getNif(),super.getNombre(),super.getFechaNac(),super.getFechaAlta(),minutosHablados,nacionalidad);
	 
	/*Object obj=null;
	 obj=super.clone(); //se llama al clone() de la clase base (Object)
	 //que hace copia binaria de los atributos
	 return obj;*/
}
}
