/* Programa principal */
#include "TestOrdenacion.h"
#include "Constantes.h"
#include "TestBusqueda.h"


int menu()
{
	int op;
	system("cls");
	cout << "***FAA. Practica 3***" << endl << endl;
	cout << "Alumno: Daniel Linfon Ye Liu  \t\tProf. Teresa Santos" << endl;
	cout << "---------------------------------------------------------------" << endl;
	cout << "***MENU PRINCIPAL***" << endl << endl;
	cout << "\t1.-Menu de ordenacion" << endl;
	cout << "\t2.-Menu de busqueda" << endl;
	cout << "\t0.-Salir" << endl << endl;
	cout << "Elige opcion: ";
	cin >> op;

	return op;
}

int menubusqueda()
{
	int op;
	system("cls");
	cout << "***MENU BUSQUEDA***" << endl<<endl;
	cout << "\t1.-Probar los metodos de busqueda" << endl;
	cout << "\t2.-Obtener el caso medio de un metodo de busqueda" << endl;
	cout << "\t3.-Comparar dos metodos" << endl;
	cout << "\t4.-Comparar todos los metodos" << endl;
	cout << "\t0.-Volver al menu principal" << endl;
	cout << "-----------------------------------------------------" << endl;
	cout << "Elige opcion: ";
	cin >> op;
	
	return op;
	
}

int menuordenacion()
{
	int op;
	system("cls");
	cout << "***MENU ORDENACION***" << endl<<endl;
	cout << "	1.-Probar los metodos de ordenacion" << endl;
	cout << "	2.-Obtener el caso medio de un metodo de ordenacion" << endl;
	cout << "	3.-Comparar dos metodos" << endl;
	cout << "	4.-Comparar todos los metodos" << endl;
	cout << "	0.-Volver al menu principal" << endl;
	cout << "-------------------------------------------" << endl;
	cout << "\Elige opcion: ";
	cin >> op;
	return op;
}

int menucasomedord()
{
	int op;
	system("cls");
	cout << "***Metodo a estudiar para el caso medio***" << endl;
	cout << "\t0.-Burbuja" << endl;
	cout << "\t1.-Inserccion" << endl;
	cout << "\t2.-Seleccion" << endl;
	cout << "---------------" << endl;
	cout << "Elige opcion: ";
	cin >> op;

	return op;
}

int	menucasomedbusq()
{
	int op;
	system("cls");
	cout << "***Metodo a estudiar para el caso medio***" << endl;
	cout << "\t0.-Busqueda Secuencial Iterativa" << endl;
	cout << "\t1.-Busqueda Binaria Iterativa" << endl;
	cout << "\t2.-Busqueda de Interpolacion Iterativa" << endl;
	cout << "---------------" << endl;
	cout << "Elige opcion: ";
	cin >> op;

	return op;
}


int main()
{
	/* ESCRIBIR PARA COMPLETAR LA PRACTICA */
	bool salir = false;
	bool volver = false;
	bool vol = false;
	int op, opc2, opc, opcion;
	TestOrdenacion t;
	TestBusqueda b;
	

	do
	{
		op = menu();
		switch (op)
		{
		case 1:
		{
			do
			{
				opc2 = menuordenacion();
				switch (opc2)
				{
				case 1:
				{
					t.comprobarMetodosOrdenacion();
					system("pause");
					break;
				}
				case 2:
				{
					opcion = menucasomedord();
					switch (opcion)
					{
					case 0:
					{
						t.casoMedio(0);
						system("pause");
						break;
					}
					case 1:
					{
						t.casoMedio(1);
						system("pause");
						break;
					}
					case 2:
					{
						t.casoMedio(2);
						system("pause");
						break;
					}
					default:
					{
						cout << "introduzca una opcion valida. ";
						system("pause");
						break;
					}

					}
					system("pause");
					break;
				}

				case 3:
				{
					int opc3, opci;
					system("cls");
					cout << "***COMPARACION DE DOS METODOS DE ORDENACION***" << endl;
					cout << "\tSelecciona los dos metodos a comparar" << endl;
					cout << "\t\t0.-Burbuja" << endl;
					cout << "\t\t1.-Inserccion" << endl;
					cout << "\t\t2.-Seleccion" << endl;
					cout << "Elige opcion 1: ";
					do
					{
						cin >> opc3;
					} while (opc3 < 0 || opc3>2);
					cout << "\nElige opcion 2: ";
					do
					{
						cin >> opci;
					} while (opci < 0 || opci>2);
					t.comparar(opc3, opci);
					system("pause");
					break;
				}

				case 4:
				{
					t.comparaTodos();
					system("pause");
					break;
				}
				case 0:
				{
					volver = true;
					break;
				}
				default:
				{
					cout << "introduzca una opcion valida.";
					system("pause");
					break;
				}

				}

			} while (!volver);
			system("pause");
			break;
		}

		case 2:
		{
			do
			{
				opc2 = menubusqueda();
				switch (opc2)
				{
				case 1:
				{
					b.comprobarMetodosBusqueda();
					system("pause");
					break;
				}
				case 2:
				{
					opc = menucasomedbusq();
					switch (opc)
					{
					case 0:
					{
						b.casoMedio(0);
					
						break;
					}
					case 1:
					{
						b.casoMedio(1);
						
						break;
					}
					case 2:
					{
						b.casoMedio(2);
						
						break;
					}
					default:
					{
						cout << "introduzca una opcion valida. ";
						
						break;
					}
					}
					system("pause");
					break;
				}
				case 3:
				{
					int opc3, opci;
					system("cls");
					cout << "***COMPARACION DE DOS METODOS DE BUSQUEDA***" << endl;
					cout << "\tSelecciona los dos metodos a comparar" << endl;
					cout << "\t\t0.-Busqueda Secuencial" << endl;
					cout << "\t\t1.-Busqueda Binaria" << endl;
					cout << "\t\t2.-Busqueda por Interpolacion" << endl;

					cout << "Elige opcion 1: ";
					do
					{
						cin >> opc3;
					} while (opc3 < 0 || opc3>2);

					cout << "\nElige opcion 2: ";
					do
					{
						cin >> opci;
					} while (opci < 0 || opci>2);
					b.comparar(opc3, opci);
					system("pause");
					break;
				}

				case 4:
				{
					b.comparaTodos();
					system("pause");
					break;
				}

				case 0:
				{
					vol = true;
					break;
				}
				default:
				{
					cout << "introduzca una opcion valida.";
					system("pause");
					break;
				}

				}

			} while (!vol);
			system("pause");
			break;
		}

		case 0:
		{
			salir = true;
			break;
		}
		default:
		{
			cout << "introduzca una opcion valida.";
			system("pause");
			break;
		}
		}
	} while (!salir);
		return 0; 

}