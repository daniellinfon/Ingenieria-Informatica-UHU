package libClases;

public class Cliente implements Cloneable, Proceso {
	private final String nif;			//dni del cliente (letra incluida) (NO puede cambiar)
	private final int codCliente;		//codigo único (y fijo) generado por la aplicación
    private String nombre;				//nombre completo del cliente (SI se puede modificar)
	private final Fecha fechaNac;		//fecha nacimiento del cliente (NO se puede cambiar)
    private final Fecha fechaAlta;		//fecha de alta del cliente (SI se puede modificar)
	private static int n=0;
	private static Fecha fechadefecto= new Fecha(1,1,2018);
		 
public Cliente (String NIF, String nom, Fecha fNac, Fecha fAlta){ //constructor
	nif=NIF;
	nombre=nom;
	this.codCliente = n+1;
	fechaNac= new Fecha(fNac);
	fechaAlta= new Fecha(fAlta);
	n++;
	
}
	 
public Cliente (String NIF, String nom, Fecha fNac) {//constructor
	nif=NIF;
	nombre=nom;
	this.codCliente = n+1;
	fechaNac= new Fecha(fNac);
	fechaAlta= new Fecha(fechadefecto);
	n++;
}
	 
public String toString() { //devuelve una cadena con la información del cliente
	String s=""; //793X 02/02/1972: Luis (1 - 07/03/1980) 
	
	s= nif+" "+fechaNac.toString()+": "+nombre+" ("+codCliente+" - "+fechaAlta+")";
	return s;
}

public Cliente(Cliente c) {
	nif=c.nif;
	codCliente=n+1;
	nombre=c.nombre;
	fechaNac=new Fecha(c.getFechaNac());
	fechaAlta= new Fecha(c.getFechaAlta());
	n++;
}

public String getNombre() { return nombre; }

public void setNombre(String nom) {
	nombre=nom;
}

public static Fecha getFechaPorDefecto() {
		return new Fecha(fechadefecto); 
}

public static void setFechaPorDefecto(Fecha f) {
		fechadefecto.setFecha(f.getDia(),f.getMes(),f.getAnio());
}

public Fecha getFechaNac() {
	return new Fecha(fechaNac); //devuelvo una copia
}

public void ver() {
	System.out.println(this/*.toString()*/);
}

public String getNif() {
	return nif;
}

public int getCodCliente() {
	return codCliente;
}
public static void main(String[] args) 
{
	Fecha f1= new Fecha(2,2,1972);
	Fecha f2= new Fecha(7,3,1944);
	Cliente C= new Cliente("793X","Luis", f1,f2);
	System.out.println(C);
	Fecha f3=Cliente.getFechaPorDefecto();
	Cliente C1= new Cliente("793Xdddd","Luis22", f1,f3);
	System.out.println(C1);
}

public void setFechaAlta(Fecha f) {
	
	fechaAlta.setFecha(f.getDia(),f.getMes(),f.getAnio());
}

public Fecha getFechaAlta() {
	return new Fecha(fechaAlta);
}


public Object clone() {
	 return new Cliente(this);
	 
	/* Object obj=null;
	 try {
	 
	 obj=super.clone(); //se llama al clone() de la clase base (Object)
	 //que hace copia binaria de los atributos
	 
	 
	 } catch(CloneNotSupportedException ex) {
	 System.out.println(" no se puede duplicar");
	 }
	 return obj;*/
}

public boolean equals(Object obj) { //true sin son iguales
if (this == obj) return true; //si apuntan al mismo sitio son iguales
if (obj == null) return false;
if (getClass() != obj.getClass())//if (!(obj instanceof Cliente))
return false; // si los 2 no son de la misma clase no son iguales
Cliente c = (Cliente) obj;
if(nif==c.nif && fechaNac==c.fechaNac || fechaAlta==c.fechaAlta);
return true;
}

}