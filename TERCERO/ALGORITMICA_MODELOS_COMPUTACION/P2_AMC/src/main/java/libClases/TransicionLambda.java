
package libClases;


class TransicionLambda {

    private String e1;
    private String[] e2;

    public TransicionLambda(String e1, String[] e2) {
        this.e1 = e1;
        this.e2 = e2;
    }
    
    public String getEstadoInicial() {
        return e1;
    }

    public String[] getEstadosFinales() {
        return e2;
    }

    
}
