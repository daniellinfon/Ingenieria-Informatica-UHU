package si2024.daniellinfonyealu.p06;

import java.util.List;

import si2024.daniellinfonyealu.p06.Acciones.Accion;

public class Problema {
	
	private Estado estadoInicial;
    private Estado estadoFinal;
    private List<Accion> acciones;

    public Problema(Estado estadoInicial, Estado estadoMeta, List<Accion> acciones) {
        this.estadoInicial = estadoInicial;
        this.estadoFinal = estadoMeta;
        this.acciones = acciones;
    }

    public Estado getEstadoInicial() {
        return estadoInicial;
    }

    public Estado getEstadoMeta() {
        return estadoFinal;
    }

    public List<Accion> getAcciones() {
        return acciones;
    }
}
