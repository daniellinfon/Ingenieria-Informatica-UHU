package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import ArbolSintaxisAbs.ConcatList;
import ArbolSintaxisAbs.Expression;
import ArbolSintaxisAbs.Operation;
import ArbolSintaxisAbs.OptionList;
import ArbolSintaxisAbs.Symbol;
import GenerarAFD.Estado;
import GenerarAFD.Fila;
import GenerarAFD.GenerarFichero;
import GenerarAFD.Transicion;
import ArbolSintaxisAbs.Fichero;
import parser.*;
import java.util.Collections;

public class Main {

	static int PosPuntoFinal;

	public static void main(String[] args) throws IOException {

		File file = new File("src/prueba3.txt");
		FileInputStream fis = new FileInputStream(file);
		AFDParser parser = new AFDParser(fis);
		Fichero f = parser.parse();
		Expression e = f.getExp();
		GenerarFichero gf = new GenerarFichero(f.getId());

		ArrayList<Character> listaSymbol = new ArrayList<>();
		buscarSimbolos(e, listaSymbol);

		ArrayList<Integer> posPuntos = new ArrayList<Integer>();
		AtomicInteger SymbolVisitado = new AtomicInteger(-1);
		AlgoritmoER_AFD(e, -1, posPuntos, 1, SymbolVisitado);

		PosPuntoFinal = listaSymbol.size();

		Estado inicial = new Estado(0, posPuntos.contains(PosPuntoFinal));
		ArrayList<Transicion> transicionesE0 = new ArrayList<>();
		Fila f0 = new Fila(inicial, posPuntos, transicionesE0);

		ArrayList<Fila> conjuntoFilas = new ArrayList<Fila>();
		conjuntoFilas.add(f0);

		int contadorFilas = 0;
		while (contadorFilas < conjuntoFilas.size()) {

			Fila fila = conjuntoFilas.get(contadorFilas);
			generaTransiciones(e, fila, conjuntoFilas, listaSymbol);
			contadorFilas++;
		}

		System.out.println(f.getId() + " ::= " + escribirExp(e));
		for (Fila F : conjuntoFilas) {
			System.out.print("Estado: " + F.getEstado().getId());
			if (F.getEstado().isFinal())
				System.out.print("*");
			for (Transicion trans : F.getTransiciones()) {
				System.out.print("\t" + trans.getSimbolo() + "-->" + trans.getDestino() + "   ");
			}
			System.out.println();
		}

		gf.generar(conjuntoFilas);

	}

	 // Tipo punteada:

	static final int SEGUIR_PUNTEANDO = 1;
	static final int BUSCAR_PUNTEADO = 2;
	static final int CONCATLIST_COMPLETO = 3;
	static final int NO_PUNTEAR_MAS = -1;

	private static void AlgoritmoER_AFD(Expression e, int posPuntoIni, ArrayList<Integer> posPuntos, int tipoPunteada, AtomicInteger SymbolVisitado) {

		if (e instanceof OptionList) {
			Expression exp = ((OptionList) e).getList().get(0);
			AlgoritmoER_AFD(exp, posPuntoIni, posPuntos, tipoPunteada, SymbolVisitado);

		} else if (e instanceof ConcatList) {
			int puntear = tipoPunteada;
			int i = 1;
			for (Expression exp : ((ConcatList) e).getList()) {
				if (i == ((ConcatList) e).getList().size()) {
					puntear = Puntear(exp, posPuntoIni, posPuntos, puntear, SymbolVisitado);

					if (puntear == CONCATLIST_COMPLETO)
						puntear = SEGUIR_PUNTEANDO;
					if (puntear == SEGUIR_PUNTEANDO)
						posPuntos.add(PosPuntoFinal);
					
				} else {
					puntear = Puntear(exp, posPuntoIni, posPuntos, puntear, SymbolVisitado);
					if (puntear == NO_PUNTEAR_MAS)
						break;
					if (puntear == CONCATLIST_COMPLETO)
						puntear = SEGUIR_PUNTEANDO;
				}
				i++;
			}
		}

	}

	private static int Puntear(Expression e, int posPuntoIni, ArrayList<Integer> posPuntos, int tipoPunteada, AtomicInteger SymbolVisitado) {

		if (e instanceof ConcatList) {
			if (tipoPunteada == SEGUIR_PUNTEANDO) {
				int puntear = tipoPunteada;
				int i = 1;
				for (Expression exp : ((ConcatList) e).getList()) {
					puntear = Puntear(exp, posPuntoIni, posPuntos, puntear, SymbolVisitado);
					if (puntear == NO_PUNTEAR_MAS) {
						SymbolVisitado.set(SymbolVisitado.get() + (((ConcatList) e).getList().size() - i));
						break;
					}
					i++;
				}

				return NO_PUNTEAR_MAS;
			} else if (tipoPunteada == BUSCAR_PUNTEADO) {

				int puntear = tipoPunteada;
				int i = 1;
				for (Expression exp : ((ConcatList) e).getList()) {
					if (i == ((ConcatList) e).getList().size()) {
						puntear = Puntear(exp, posPuntoIni, posPuntos, puntear, SymbolVisitado);
						if (puntear == SEGUIR_PUNTEANDO)
							return CONCATLIST_COMPLETO;
					} else {
						puntear = Puntear(exp, posPuntoIni, posPuntos, puntear, SymbolVisitado);
						if (puntear == NO_PUNTEAR_MAS) {
							SymbolVisitado.set(SymbolVisitado.get() + (((ConcatList) e).getList().size() - i));
							return NO_PUNTEAR_MAS;
						}
					}
					i++;
				}

				return puntear;
			}

		} else if (e instanceof OptionList) {

			if (tipoPunteada == SEGUIR_PUNTEANDO) {
				for (Expression exp : ((OptionList) e).getList()) 
					Puntear(exp, posPuntoIni, posPuntos, tipoPunteada, SymbolVisitado);
				
				return NO_PUNTEAR_MAS;
			} else if (tipoPunteada == BUSCAR_PUNTEADO) {
				int puntear = tipoPunteada;
				for (Expression exp : ((OptionList) e).getList()) {
					puntear = Puntear(exp, posPuntoIni, posPuntos, puntear, SymbolVisitado);
					if (puntear == NO_PUNTEAR_MAS)
						return NO_PUNTEAR_MAS;
					if (puntear == CONCATLIST_COMPLETO)
						return CONCATLIST_COMPLETO;

				}
				return puntear;
			}

		} else if (e instanceof Operation) {

			int tipo = ((Operation) e).getOperator();
			Expression exp = ((Operation) e).getOperand();

			if (tipoPunteada == SEGUIR_PUNTEANDO) {
				if (Operation.operator(tipo) == "STAR") {
					Puntear(exp, posPuntoIni, posPuntos, tipoPunteada, SymbolVisitado);
					return SEGUIR_PUNTEANDO;
				}
				if (Operation.operator(tipo) == "PLUS") {
					Puntear(exp, posPuntoIni, posPuntos, tipoPunteada, SymbolVisitado);
					return NO_PUNTEAR_MAS;
				}
			} else if (tipoPunteada == BUSCAR_PUNTEADO) {
				if (Operation.operator(tipo) == "STAR" || Operation.operator(tipo) == "PLUS") {
					AtomicInteger aux = new AtomicInteger(SymbolVisitado.get());
					int puntear = Puntear(exp, posPuntoIni, posPuntos, tipoPunteada, SymbolVisitado);
					if (puntear == CONCATLIST_COMPLETO) {
						Puntear(exp, posPuntoIni, posPuntos, SEGUIR_PUNTEANDO, aux);
						SymbolVisitado.set(aux.get());
						return SEGUIR_PUNTEANDO;
					}
					if (puntear != BUSCAR_PUNTEADO)
						return NO_PUNTEAR_MAS;
					else
						return puntear;
				}
			}

		} else if (e instanceof Symbol) {

			SymbolVisitado.incrementAndGet();
			
			if (tipoPunteada == BUSCAR_PUNTEADO) {
				if (SymbolVisitado.get() < posPuntoIni) {
					return BUSCAR_PUNTEADO;
				}
				if (SymbolVisitado.get() == posPuntoIni) {
					return SEGUIR_PUNTEANDO;
				}
			}
			if (tipoPunteada == SEGUIR_PUNTEANDO) {
				posPuntos.add(SymbolVisitado.get());
				return NO_PUNTEAR_MAS;
			}
		}

		return 0;
	}

	private static void buscarSimbolos(Expression e, ArrayList<Character> listaSymbol) {

		if (e instanceof ConcatList) {

			for (Expression exp : ((ConcatList) e).getList()) {
				buscarSimbolos(exp, listaSymbol);
			}

		} else if (e instanceof OptionList) {

			for (Expression exp : ((OptionList) e).getList()) {
				buscarSimbolos(exp, listaSymbol);
			}

		} else if (e instanceof Operation) {

			buscarSimbolos(((Operation) e).getOperand(), listaSymbol);

		} else if (e instanceof Symbol) {

			listaSymbol.add(((Symbol) e).getSymbol());
		}

	}

	private static void generaTransiciones(Expression e, Fila fila, ArrayList<Fila> conjuntoFilas,
			ArrayList<Character> listaSymbol) {

		ArrayList<Integer> posPuntos = fila.getPosPuntos();
		ArrayList<ArrayList<Integer>> posPuntos_de_cada_posPunto = new ArrayList<ArrayList<Integer>>();

		for (Integer pos : posPuntos) {
			if (pos == PosPuntoFinal)
				continue;
			ArrayList<Integer> posP = new ArrayList<Integer>();
			AtomicInteger SymbolVisitado = new AtomicInteger(-1);
			AlgoritmoER_AFD(e, pos, posP, 2, SymbolVisitado);

			posPuntos_de_cada_posPunto.add(posP);
		}

		if (posPuntos_de_cada_posPunto.isEmpty())
			return;

		ArrayList<Character> ListaSymbolTransiciones = ListaSymbolPosPuntos(listaSymbol, posPuntos); 

		if (ListaSymbolTransiciones.size() > 1) {

			ArrayList<Character> ListaSymbolUnique = CharUnicos(ListaSymbolTransiciones);
			ArrayList<ArrayList<Object>> PosSymbol = new ArrayList<ArrayList<Object>>();

			for (int i = 0; i < ListaSymbolUnique.size(); i++) {
				PosSymbol.add(new ArrayList<Object>());
				int index = PosSymbol.size() - 1;
				PosSymbol.get(index).add(ListaSymbolUnique.get(i));
				for (int j = 0; j < ListaSymbolTransiciones.size(); j++) {
					Character c = ListaSymbolTransiciones.get(j);
					if (c == ListaSymbolUnique.get(i)) {
						PosSymbol.get(index).add(j);
					}
				}
				if (PosSymbol.get(index).size() == 2) {
					PosSymbol.remove(index);
				}
			}

			for (int i = 0; i < ListaSymbolTransiciones.size(); i++) {
				Character c = ListaSymbolTransiciones.get(i);
				ArrayList<Object> listaRepetido = comprobarRepetido(PosSymbol, c);
				ArrayList<Integer> ListaElementos = null;
				if (listaRepetido != null) {
					if (listaRepetido.size() > 1) {
						int posLista1 = (Integer) listaRepetido.get(1);
						for (int j = 2; j < listaRepetido.size(); j++) {
							int posLista2 = (Integer) listaRepetido.get(j);
							posPuntos_de_cada_posPunto.get(posLista1).addAll(posPuntos_de_cada_posPunto.get(posLista2));
						}
						listaRepetido.clear();
						listaRepetido.add(c);
						ListaElementos = IntUnicos(posPuntos_de_cada_posPunto.get(posLista1));
					} else
						continue;
				} else {
					ListaElementos = posPuntos_de_cada_posPunto.get(i);
				}

				int valorTransicion = perteneceEstado(ListaElementos, conjuntoFilas);
				Transicion Trans = new Transicion(c, valorTransicion);
				fila.anadirTransicion(Trans);

				if (valorTransicion == conjuntoFilas.size()) {
					Estado estado = new Estado(valorTransicion, ListaElementos.contains(PosPuntoFinal));
					ArrayList<Transicion> transicionesE = new ArrayList<>();
					Fila F = new Fila(estado, ListaElementos, transicionesE);
					conjuntoFilas.add(F);
				}

			}

		} else {
			ArrayList<Integer> ListaElementos = posPuntos_de_cada_posPunto.get(0);
			Character c = ListaSymbolTransiciones.get(0);

			int valorTransicion = perteneceEstado(ListaElementos, conjuntoFilas);
			Transicion Trans = new Transicion(c, valorTransicion);
			fila.anadirTransicion(Trans);

			if (valorTransicion == conjuntoFilas.size()) {
				Estado estado = new Estado(valorTransicion, ListaElementos.contains(PosPuntoFinal));
				ArrayList<Transicion> transicionesE = new ArrayList<>();
				Fila F = new Fila(estado, ListaElementos, transicionesE);
				conjuntoFilas.add(F);
			}
		}

	}

	private static int perteneceEstado(ArrayList<Integer> ListaElementos, ArrayList<Fila> conjuntoFilas) {

		int estado = 0;
		for (Fila tupla : conjuntoFilas) {
			if (posPuntosIguales(ListaElementos, tupla.getPosPuntos()))
				return estado;
			estado++;
		}
		return estado;
	}

	private static boolean posPuntosIguales(ArrayList<Integer> posPuntos1, ArrayList<Integer> posPuntos2) {

		Collections.sort(posPuntos1);
		Collections.sort(posPuntos2);
		return Objects.equals(posPuntos1, posPuntos2);
	}

	private static ArrayList<Object> comprobarRepetido(ArrayList<ArrayList<Object>> PosSymbol, Character c) {
		for (ArrayList<Object> lista : PosSymbol) {
			if ((Character) lista.get(0) == c)
				return lista;
		}
		return null;
	}

	private static ArrayList<Character> CharUnicos(ArrayList<Character> lista) {

		ArrayList<Character> unicos = new ArrayList<Character>();
		for (Character valor : lista) {
			if (!unicos.contains(valor))
				unicos.add(valor);
		}
		return unicos;
	}

	private static ArrayList<Integer> IntUnicos(ArrayList<Integer> lista) {

		ArrayList<Integer> unicos = new ArrayList<Integer>();
		for (Integer valor : lista) {
			if (!unicos.contains(valor))
				unicos.add(valor);
		}
		return unicos;
	}

	private static ArrayList<Character> ListaSymbolPosPuntos(ArrayList<Character> listaSymbol,
			ArrayList<Integer> posPuntos) {

		ArrayList<Character> listaSymbolPosP = new ArrayList<Character>();

		for (Integer pos : posPuntos) {
			if (pos != PosPuntoFinal)
				listaSymbolPosP.add(listaSymbol.get(pos));
		}

		return listaSymbolPosP;
	}

	private static String escribirExp(Expression e) {

		String expresion = "";

		if (e instanceof ConcatList) {

		for (Expression exp : ((ConcatList) e).getList()) {
		expresion += escribirExp(exp);
		expresion += " ";
		}

		} else if (e instanceof OptionList) {

		int i = 0;

		for (Expression exp : ((OptionList) e).getList()) {

		i++;

		expresion += escribirExp(exp);

		if(((OptionList) e).getList().size() > 1 && i != ((OptionList) e).getList().size())
		expresion += " | ";
		}

		} else if (e instanceof Operation) {

		expresion += "(";
		expresion += escribirExp(((Operation) e).getOperand());
		expresion += ")";
		expresion += Operation.operator(((Operation) e).getOperator());

		} else if (e instanceof Symbol) {

		expresion += ((Symbol) e).getSymbol();
		}

		return expresion;
		}
	
}
