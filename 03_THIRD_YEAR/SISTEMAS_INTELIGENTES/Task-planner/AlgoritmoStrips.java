package si2024.daniellinfonyealu.p06;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import si2024.daniellinfonyealu.p06.Acciones.Accion;

public class AlgoritmoStrips {

	private Problema problema;
	private List<Accion> plan;
	private ArrayList<Nodo> Abierta = new ArrayList<Nodo>();
	private Nodo actual;
	private Estado estadoActual;

	public AlgoritmoStrips(Problema problema) {
		this.problema = problema;
		estadoActual = problema.getEstadoInicial();
		this.plan = new ArrayList<>();
	}

	public List<Accion> resolver() {
		Stack<Object> pila = new Stack<Object>();
		pila.add(problema.getEstadoMeta());
		Abierta.add(new Nodo(plan, pila, estadoActual));

		while (!pila.isEmpty()) {
			actual = Abierta.remove(Abierta.size() - 1);
			if (actual.pila.isEmpty()) {
				return actual.plan;
			} else {
				
				Object cima = actual.pila.peek();
				if (cima instanceof Accion) { // operador
					Accion accion = (Accion) cima;
					
					if (estadoActual.contieneTodas(accion.getPrecond())) { //Se puede ejecutar
						
						//Ejecutar
						estadoActual.aplicarAccion(accion);

						//Quitarlo de la pila
						pila = new Stack<Object>();
						pila.addAll(actual.pila);
						pila.pop();

						//Añadirlo al plan
						plan = new ArrayList<Accion>(actual.plan);
						plan.add(accion);

						Abierta.add(new Nodo(plan, pila, estadoActual));
						
					} else {
						
						estadoActual = new Estado(actual.estado.getProposiciones());
						
						//Introducir precondicones a la pila
						pila = new Stack<Object>();
						pila.addAll(actual.pila);
						if (accion.getPrecond().size() == 1) {
							String precond = accion.getPrecond().get(0);
							pila.add(precond);
						} else {
							Estado pr = new Estado(accion.getPrecond());
							pila.add(pr);
						}
						
						plan = new ArrayList<Accion>(actual.plan);
						Abierta.add(new Nodo(plan, pila, estadoActual));
					}
				} else if (cima instanceof String) { // meta
					String meta = (String) cima;
					
					if (estadoActual.contiene(meta)) {
						
						estadoActual = new Estado(actual.estado.getProposiciones());
						
						//Eliminar de la pila
						pila = new Stack<Object>();
				        pila.addAll(actual.pila);
						pila.pop();
						
						plan = new ArrayList<Accion>(actual.plan);
						
						Abierta.add(new Nodo(plan, pila, estadoActual));
						
					} else {
						if (!actual.esBucle()) { // Detectar ciclo de meta
							
							estadoActual = new Estado(actual.estado.getProposiciones());
							plan = new ArrayList<Accion>(actual.plan);
							
							//generar un sucesor por cada instanciación de operador que añade la meta 
							for (Accion accion : problema.getAcciones()) {
								if (accion.getAdicion().contains(meta)) {
									pila = new Stack<Object>();
									pila.addAll(actual.pila);
									pila.add(accion);
									Abierta.add(new Nodo(plan, pila, estadoActual));
								}
							}
						}
					}
				} else if (cima instanceof Estado) { // conjunción de metas
					Estado estadoMeta = (Estado) cima;
					
					// se cumple
					if (estadoActual.contieneTodas(estadoMeta.getProposiciones())) {
						
						estadoActual = new Estado(actual.estado.getProposiciones());
						
						//Eliminar de la pila
						pila = new Stack<Object>();
						pila.addAll(actual.pila);
						pila.pop();
						
						plan = new ArrayList<Accion>(actual.plan);
						Abierta.add(new Nodo(plan, pila, estadoActual));
						
					} else {
						
						estadoActual = new Estado(actual.estado.getProposiciones());
						
						//generar como sucesores todas las posibles combinaciones de las metas
						ArrayList<ArrayList<String>> permutaciones = generarPermutaciones(estadoMeta.getProposiciones());

						plan = new ArrayList<Accion>(actual.plan);
						
						for (ArrayList<String> permutacion : permutaciones) {
							pila = new Stack<Object>();
							pila.addAll(actual.pila);
							for (String est : permutacion) {
								pila.add(est);
							}

							Abierta.add(new Nodo(plan, pila, estadoActual));
						}
					}
				}
			}
		}

		return actual.plan;
	}

	private ArrayList<ArrayList<String>> generarPermutaciones(List<String> list) {
		ArrayList<ArrayList<String>> resultado = new ArrayList<>();
		permutar(list, 0, resultado);
		return resultado;
	}

	private void permutar(List<String> list, int indice, ArrayList<ArrayList<String>> resultado) {
		if (indice == list.size() - 1) {
			resultado.add(new ArrayList<>(list));
			return;
		}

		for (int i = indice; i < list.size(); i++) {
			intercambiar(list, indice, i);
			permutar(list, indice + 1, resultado);
			intercambiar(list, indice, i); 
		}
	}

	private static void intercambiar(List<String> list, int i, int j) {
		String temp = list.get(i);
		list.set(i, list.get(j));
		list.set(j, temp);
	}

}
