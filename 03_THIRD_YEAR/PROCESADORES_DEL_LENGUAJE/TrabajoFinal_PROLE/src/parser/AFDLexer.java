package parser;

import java.io.*;

/**
 * Clase que desarrolla el analizador lexico del AFD
 * 
 * @author Daniel Linfon Ye Liu
 *
 */
public class AFDLexer extends Lexer implements TokenConstants {

	/**
	 * Transiciones del aut�mata del analizador l�xico
	 * 
	 * @param state Estado inicial
	 * @param symbol S�mbolo del alfabeto
	 * @return Estado final
	 */
	protected int transition(int state, char symbol) 
	{
		switch(state) 
		{
			case 0: 
				
				//Blanco
				if(symbol == ' ' || symbol == '\t' || symbol == '\r' || symbol == '\n') return 1; 
				
				//Comentario
				else if(symbol == '/') return 2; 
				
				//------------------Identificador-----------------------
				else if(symbol >= 'a' && symbol <= 'z') return 6;
				else if(symbol >= 'A' && symbol <= 'Z') return 6;
				else if(symbol == '_') return 6;
				//------------------------------------------------------
				
				else if(symbol == '\'') return 7; //Symbol
				else if(symbol == ':') return 13; //EQ
				else if(symbol == '|') return 16; //OR
				else if(symbol == ';') return 17; //SEMICOLON
				else if(symbol == '(') return 18; //LPAREN
				else if(symbol == ')') return 19; //RPAREN
				else if(symbol == '*') return 20; //STAR
				else if(symbol == '+') return 21; //PLUS
				else if(symbol == '?') return 22; //HOOK
				else return -1;
				
			case 1: //Blanco
				if(symbol == ' ' || symbol == '\t' || symbol == '\r' || symbol == '\n') return 1;
				else return -1;
			//---------Comentario-------------
			case 2:
				if(symbol == '*') return 3;
				else return -1;
			case 3:
				if(symbol == '*') return 4;
				else return 3;
			case 4:
				if(symbol == '*') return 4;
				else if(symbol == '/') return 5;
				else return 3;
			//-------------Symbol--------------------------
			case 6:
				if(symbol >= 'a' && symbol <= 'z') return 6;
				else if(symbol >= 'A' && symbol <= 'Z') return 6;
				else if(symbol == '_') return 6;
				else return -1;	
			case 7:
				if(symbol == '\\') return 10;
				else if(symbol != '\'' && symbol != '\n' && symbol != '\r') return 8;
				else return -1;
			case 8:
				if(symbol == '\'') return 9;
				else return -1;
			case 10:
				if(symbol == 'n' || symbol == 't' || symbol == 'b') return 8;
				else if(symbol == 'r' || symbol == 'f' || symbol == '\\') return 8;
				else if(symbol == '\'' || symbol == '\"') return 8;
				else return -1;
			
			//----------------EQ------------------------
			case 13:
				if(symbol == ':') return 14;
				else return -1;
			case 14:
				if(symbol == '=') return 15;
				else return -1;		
			default:
				return -1;
		}
	}
	
	/**
	 * Verifica si un estado es final
	 * 
	 * @param state Estado
	 * @return true, si el estado es final
	 */
	protected boolean isFinal(int state) 
	{
		if(state <=0 || state > 22) return false;
		switch(state) 
		{
			case 1:
			case 5:
			case 6:
			case 9:
			case 15:
			case 16:
			case 17:
			case 18:
			case 19:
			case 20:
			case 21:
			case 22:
				return true;
			default: 
				return false;
		}
	}
	
	/**
	 * Genera el componente l�xico correspondiente al estado final y
	 * al lexema encontrado. Devuelve null si la acci�n asociada al
	 * estado final es omitir (SKIP).
	 * 
	 * @param state Estado final alcanzado
	 * @param lexeme Lexema reconocido
	 * @param row Fila de comienzo del lexema
	 * @param column Columna de comienzo del lexema
	 * @return Componente l�xico correspondiente al estado final y al lexema
	 */
	protected Token getToken(int state, String lexeme, int row, int column) 
	{
		switch(state) 
		{	
			case 1: return null;
			case 5: return null;
			case 6: return new Token(IDENTIFIER, lexeme, row, column);
			case 9: return new Token(SYMBOL, lexeme, row, column);
			case 15: return new Token(EQ, lexeme, row, column);
			case 16: return new Token(OR, lexeme, row, column);
			case 17: return new Token(SEMICOLON, lexeme, row, column);
			case 18: return new Token(LPAREN, lexeme, row, column);
			case 19: return new Token(RPAREN, lexeme, row, column);
			case 20: return new Token(STAR, lexeme, row, column);
			case 21: return new Token(PLUS, lexeme, row, column);
			case 22: return new Token(HOOK, lexeme, row, column);
			default: return null;
		}
	}
	
	
	/**
	 * Constructor
	 * @param filename Nombre del fichero fuente
	 * @throws IOException En caso de problemas con el flujo de entrada
	 */
	public AFDLexer(File file) throws IOException 
	{
		super(file);
	}

	public AFDLexer(FileInputStream fis) {
		super(fis);
	}
	
}
