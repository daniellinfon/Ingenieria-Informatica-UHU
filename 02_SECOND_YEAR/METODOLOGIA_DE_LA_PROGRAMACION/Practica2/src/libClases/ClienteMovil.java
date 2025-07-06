package libClases;

public class ClienteMovil extends Cliente {

	  private float precioMinuto;
	  private float minutosHablados;
	  private final Fecha Permanencia; //se puede modificar
	  
public ClienteMovil(String dni, String nom, Fecha fnac,Fecha fAlta, Fecha fPerm, float min, float precio) {
		  super(dni,nom,fnac,fAlta);
		  precioMinuto=precio;
		  minutosHablados=min;
		  Permanencia= new Fecha(fPerm);
		  
}

public ClienteMovil(String dni, String nom, Fecha fnac, float f, float precio) {
	super(dni,nom,fnac);
	precioMinuto=precio;
	minutosHablados=f;
	//Permanecia=FechaAlta + 1 anio
	Permanencia = new Fecha(super.getFechaAlta().getDia(),super.getFechaAlta().getMes(),super.getFechaAlta().getAnio()+1);
}
	  

public float factura() {
	  return precioMinuto*minutosHablados;
}

/*public void setFechaAlta(Fecha f) {
	super.setFechaAlta(f);
}*/

public void setFPermanencia(Fecha f) {
	Permanencia.setFecha(f.getDia(),f.getMes(),f.getAnio());
}

public Fecha getFPermanencia() {
	return new Fecha(Permanencia);
}

public String toString() { //devuelve una cadena con la información del cliente
	String s=""; //547B 07/03/1980: Bo Derek (5 - 03/03/2003) 03/03/2003 50.5 x 0.03 --> 1.515
	s=/* getNif()+" "+getFechaNac()+": "+getNombre()+" ("+getCodCliente()+" - "+getFechaAlta()+") "*/super.toString()+" "+Permanencia+" "+minutosHablados+" x "+precioMinuto+" --> "+factura();
	return s;
}

public void ver() {
	System.out.println(this/*.toString()*/);
}

public float getPrecio() {return precioMinuto;}
public void setPrecio(float p) {precioMinuto=p;}
public float getMinutos() {return minutosHablados;}

public Object clone() {
	 return new ClienteMovil(super.getNif(),super.getNombre(),super.getFechaNac(),super.getFechaAlta(),Permanencia,minutosHablados,precioMinuto);
	/* Object obj=null;
	 obj=super.clone(); //se llama al clone() de la clase base (Object)
	 //que hace copia binaria de los atributos
	 return obj;*/
}

}