
package libClases;


class TransicionAFND {

    private String e1;
    private String[] e2;
    private char simbolo;

    public TransicionAFND(String e1, String[] e2, char simbolo) {
        this.e1 = e1;
        this.e2 = e2;
        this.simbolo = simbolo;
    }
    
    

    public String getEstadoInicial() {
        return e1;
    }

    public String[] getEstadosFinales() {
        return e2;
    }

    public char getSimbolo() {
        return simbolo;
    }
}
