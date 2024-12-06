
package libClases;


class TransicionAFD {

    private String e1, e2;
    private char simbolo;

    public TransicionAFD(String e1, String e2, char simbolo) {
        this.e1 = e1;
        this.e2 = e2;
        this.simbolo = simbolo;
    }
    
    public String getEstadoInicial() {
        return e1;
    }

    public String getEstadoFinal() {
        return e2;
    }

    public char getSimbolo() {
        return simbolo;
    }

}
