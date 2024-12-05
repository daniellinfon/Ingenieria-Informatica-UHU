/* Programa principal */
#include "TestOrdenacion.h"
#include "Constantes.h"
#include "TestAlgoritmo.h"

int menu_PRINCIPAL()
{
	int opc;
	do
	{	
		system("cls");
		cout << "\t\t MENU DE SUBPROGRAMA\n"
			<< "1.- Graficas Teoricas" << endl
			<< "2.- Graficas Empiricas" << endl
			<< "3.- Salir" << endl;
		cout << "\tELIGE OPCION: ";
		cin >> opc;
	} while (opc != 1 && opc != 2 && opc != 3);
	return opc;
}
int menu_teor() {
	int opc;
	do {
		system("cls");
		cout << "PRACTICA 1 FUNDAMENTOS DE ANALISIS DE ALGORITMOS" << endl;
		cout << "ALUMNO: ALBERTO APONTE ARAUJO" << endl;
		cout << "*** ESTUDIO DE LA COMPLEJIDAD DEL ALGORITMO DE BUSQUEDA SECUENCIAL ***" << endl;
		cout << "1.- ESTUDIO TEORICO" << endl;
		cout << "2.- ESTUDIO EMPIRICO" << endl;
		cout << "3.- Salir" << endl;
		cout << "------------------" << endl;
		cout << "ELIGE OPCION: ";
		cin >> opc;
		system("cls");
	} while (opc != 1 && opc != 2 && opc !=3);

	return opc;
}

int menuteorico() {
	int opc;
	do {
		cout << "*** MENU TEORICO BUSQUEDA SECUENCIAL ***" << endl;
		cout << "1.- PROBAR ALGORITMO DE BUSQUEDA SECUENCIAL" << endl;
		cout << "2.- OBTENER CASOS DEL METODO DE BUSQUEDA SECUENCIA" << endl;
		cout << "3.- COMPARAR CASOS" << endl;
		cout << "4.- VOLVER AL MENU ANTERIOR" << endl;
		cout << "----------------" << endl;
		cout << "ELIGE OPCION: ";
		cin >> opc;
		system("cls");
	} while (opc != 1 && opc != 2 && opc != 3 && opc != 4);

	return opc;
}

int menuteorico2() {
	int opc;
	do {
		cout << "*** CASO A ESTUDIAR PARA BUSQUEDA SECUENCIAL ***" << endl;
		cout << "0.- CASO PEOR" << endl;
		cout << "1.- CASO MEDIO ***MODIFICADO***" << endl;
		cout << "2.- CASO MEJOR" << endl;
		cout << "----------------" << endl;
		cout << "ELIGE OPCION: ";
		cin >> opc;
		system("cls");
	} while (opc != 0 && opc != 1 && opc != 2);

	return opc;

}

int menuempirico1() {
	int opc;
	do {
		cout << "*** MENU TEORICO BUSQUEDA SECUENCIAL ***" << endl;
		cout << "1.- PROBAR ALGORITMO DE BUSQUEDA SECUENCIAL" << endl;
		cout << "2.- OBTENER CASOS DEL METODO DE BUSQUEDA SECUENCIA" << endl;
		cout << "3.- COMPARAR CASOS" << endl;
		cout << "4.- VOLVER AL MENU ANTERIOR" << endl;
		cout << "----------------" << endl;
		cout << "ELIGE OPCION: ";
		cin >> opc;
		system("cls");
	} while (opc != 1 && opc != 2 && opc != 3 && opc != 4);

	return opc;
}

int menuempirico2() {
	int opc;
	do {
		cout << "*** CASO A ESTUDIAR PARA BUSQUEDA SECUENCIAL ***" << endl;
		cout << "0.- CASO PEOR" << endl;
		cout << "1.- CASO MEDIO" << endl;
		cout << "2.- CASO MEJOR" << endl;
		cout << "----------------" << endl;
		cout << "ELIGE OPCION: ";
		cin >> opc;
		system("cls");
	} while (opc != 0 && opc != 1 && opc != 2);

	return opc;
}

int menu()
{
	int op;
	system("cls");
	cout << "***FAA. Practica 2***" << endl<<endl;
	cout << "Alumno: Alberto Aponte Araujo  \t\tProf. Teresa Santos" << endl;
	cout << "---------------------------------------------------------------"<<endl;
	cout << "***MENU PRINCIPAL***" << endl<<endl;
	cout << "\t***ANALISIS EXPERIMENTAL DE ALGORITMOS DE ORDENACION***" << endl;
	cout << "\t  1.-Probar los metodos de ordenacion" << endl;
	cout << "\t  2.-Obtener el caso medio de un metodo de ordenacion" << endl;
	cout << "\t  3.-Comparar 2 metodos" << endl;
	cout << "\t  4.-Comparar todos los metodos" << endl;
	cout << "\t  5.-Salir" << endl<<endl;
	cout << "Elige opcion: ";
	cin >> op; 
		
	return op;
}

int menu2()
{
	int op;
	system("cls");
	cout << "***Metodo a estudiar para el caso medio***" << endl<<endl;
	cout << "\t0.-Shakersort" << endl;
	cout << "\t1.-Mergesort" << endl;
	cout << "\t2.-Seleccion" << endl << endl;
	cout << "Elige opcion: ";
	cin >> op;
	
		return op;
}


int main()
{	
	int OPC;
	int op, op2;
	TestOrdenacion t;
	int opc;
	int opc1;
	int opc2;
	int caso;
	int caso2;
	TestAlgoritmo test;
	do
	{
		OPC = menu_PRINCIPAL();
		switch (OPC)
		{
		case 1:
		{
			do {
				opc = menu_teor();
				cout << "opc: " << opc;
				switch (opc) {
				case 1:
					opc1 = menuteorico();
					switch (opc1) {
					case 1:
						test.comprobarAlgoritmo();
						break;
					case 2:
						caso = menuteorico2();
						test.costeCasoTeorico(caso);
						break;
					case 3:
						test.compararTeorico(SECUENCIALPEOR, SECUENCIALMEDIO, SECUENCIALMEJOR);
						break;
					default:
						break;
					}
					break;

				case 2:
					opc2 = menuempirico1();
					switch (opc2)
					{
					case 1:
						test.comprobarAlgoritmo();
						break;
					case 2:
						caso2 = menuempirico2();
						test.costeCasoEmpirico(caso2);
						break;
					case 3:
						test.compararEmpirico(SECUENCIALPEOR, SECUENCIALMEDIO, SECUENCIALMEJOR);
						break;
					default:
						break;
					}
					break;

				default:
					break;
				}
				cout << "\n"; system("pause");
			} while (opc != 3);
			break;
		}

		case 2:
		{
			do
			{
				op = menu();
				switch (op)
				{
				case 1:
				{
					t.comprobarMetodosOrdenacion();
					break;
				}
				case 2:
				{
					op2 = menu2();
					t.casoMedio(op2);
					break;
				}
				case 3:
				{
					int A1, A2;
					system("cls");
					cout << "Compara 2 algoritmos: " << endl << endl;
					cout << "\t0.-Shakersort" << endl;
					cout << "\t1.-Inserccion" << endl;
					cout << "\t2.-Seleccion" << endl << endl;
					cout << "Elige opcion: ";
					cin >> A1;
					system("cls");
					cout << "Elija el otro algoritmo: " << endl << endl;
					if (A1 == 0)
					{
						cout << "\t1.-Mergesort" << endl;
						cout << "\t2.-Seleccion" << endl << endl;
						cout << "Elige opcion: ";
						cin >> A2;
					}
					if (A1 == 1)
					{
						cout << "\t0.-Shakersort" << endl;
						cout << "\t2.-Seleccion" << endl << endl;
						cout << "Elige opcion: ";
						cin >> A2;
					}
					if (A1 == 2)
					{
						cout << "\t0.-Shakersort" << endl;
						cout << "\t1.-Mergesort" << endl;
						cout << "Elige opcion: ";
						cin >> A2;
					}
					system("cls");
					t.comparar(A1, A2);
					break;
				}
				case 4:
				{
					t.comparaTodos();
					break;
				}
				case 5:
				{
					cout << "Saliendo del programa...\n";
					break;
				}
				default:
				{
					cout << endl << "Teclee 0, 1, 2, 3 o 4" << endl << endl;
					system("pause");
					break;
				}
				}
			} while (op != 5);
			break;
		}
		}
	} while (OPC != 3);

	return 0;
};