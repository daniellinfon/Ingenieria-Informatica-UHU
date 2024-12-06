
public class Resultado {

    private Punto[] parCercano;
    private double distanciaMin;
    private Punto[] solucion;
    private double distanciaTotal;

    public Resultado(double distanciaMin) {
        this.parCercano = new Punto [2];
        this.distanciaMin = distanciaMin;
    }
    
    public Resultado(double distanciaTotal, int num) {
        this.solucion = new Punto [num];
        this.distanciaTotal = distanciaTotal;
    }
    
    public double getDistanciaMin() {
        
        return distanciaMin;
    }

    public void setDistanciaMin(double distanciaMin) {
        this.distanciaMin = distanciaMin;
    }

    public Punto[] getParCercano() {
        return parCercano;
    }

    public void setParCercano(Punto p1, Punto p2) {
        this.parCercano[0] = p1;
        this.parCercano[1] = p2;
    }
    
      public double getDistanciaTotal() {
        
        return distanciaTotal;
    }

    public void setDistanciaTotal(double distanciaTotal) {
        this.distanciaTotal = distanciaTotal;
    }

    public Punto[] getSolucion() {
        return solucion;
    }

    public void setSolucion(Punto[] solucion) {
        this.solucion = solucion;
    }
}
