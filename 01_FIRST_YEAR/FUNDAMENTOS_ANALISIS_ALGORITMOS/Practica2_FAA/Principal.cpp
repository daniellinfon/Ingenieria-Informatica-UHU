/* Programa principal */
#include "TestOrdenacion.h"
#include "Constantes.h"

int menu()
{
	int op;
	system("cls");
	cout << "***FAA. Practica 2***" << endl << endl;
	cout << "Alumno: Daniel Linfon Ye Liu  \t\tProf. Teresa Santos" << endl;
	cout << "---------------------------------------------------------------" << endl;
	cout << "***MENU PRINCIPAL***" << endl << endl;
	cout << "\t***ANALISIS EXPERIMENTAL DE ALGORITMOS DE ORDENACION***" << endl;
	cout << "\t  1.-Probar los metodos de ordenacion" << endl;
	cout << "\t  2.-Obtener el caso medio de un metodo de ordenacion" << endl;
	cout << "\t  3.-Comparar dos metodos" << endl;
	cout << "\t  4.-Comparar todos los metodos" << endl;
	cout << "\t  0.-Salir" << endl << endl;
	cout << "Elige opcion: ";
	cin >> op;

	return op;
}

int menu2()
{
	int op;
	system("cls");
	cout << "***Metodo a estudiar para el caso medio***" << endl << endl;
	cout << "\t0.-Burbuja" << endl;
	cout << "\t1.-Inserccion" << endl;
	cout << "\t2.-Seleccion" << endl << endl;
	cout << "Elige opcion: ";
	cin >> op;

	return op;
}


int main()
{
	int op, opc2;
	TestOrdenacion t;

	do
	{
		op = menu();
		switch (op)
		{
		case 1:
		{
			t.comprobarMetodosOrdenacion();
			system("pause");
			break;
		}
		case 2:
		{
			opc2 = menu2();
			t.casoMedio(opc2);
			system("pause");
			break;
		}
		case 3:
		{
			system("cls");
			cout << "Compara dos metodos: " << endl;
			cout << "\t0. Burbuja" << endl;
			cout << "\t1.-Inserccion" << endl;
			cout << "\t2.-Seleccion" << endl << endl;
			cout << "Elige metodo 1: ";
			int m1, m2;
			cin >> m1;
			cout << "\nElige metodo 2: ";
			cin >> m2;

			t.comparar(m1, m2);
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
			system("pause");
			break;
		}
		default:
		{
			cout << "Teclee 0, 1, 2, 3 o 4" << endl;
			system("pause");
			break;
		}

		}
	} while (op != 0);



	/* ESCRIBIR PARA COMPLETAR LA PRACTICA */
	return 0;
};