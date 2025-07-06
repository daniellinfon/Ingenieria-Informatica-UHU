
public class Punto {
    
    private double x;
    private double y;
    private int etiqueta;
    private boolean visitado;
    
    public Punto()
    {
        this.x=0;
        this.y=0;
    }
    
    public Punto(double x, double y)
    {
        this.x=x;
        this.y=y;
    }
    
    public double getx() {
        return x;
    }
    
    public double gety() {
        return y;
    }
    
    public int getEtiqueta()
    {
        return this.etiqueta;
        
    }
    
    public void setx(double x) {
        this.x=x;
    }
    
    public void sety(double y) {
        this.y=y;
    }
    
    public void setEtiqueta(int etiqueta){
        
        this.etiqueta=etiqueta;
    }
    
    public boolean getVisitado() {
        return visitado;
    }

    public void setVisitado(boolean visitado) {
        this.visitado = visitado;
    }
}
