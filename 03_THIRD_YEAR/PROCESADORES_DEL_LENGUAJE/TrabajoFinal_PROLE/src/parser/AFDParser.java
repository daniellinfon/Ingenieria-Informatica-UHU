//------------------------------------------------------------------//
//                        COPYRIGHT NOTICE                          //
//------------------------------------------------------------------//
// Copyright (c) 2017, Francisco Jos� Moreno Velo                   //
// All rights reserved.                                             //
//                                                                  //
// Redistribution and use in source and binary forms, with or       //
// without modification, are permitted provided that the following  //
// conditions are met:                                              //
//                                                                  //
// * Redistributions of source code must retain the above copyright //
//   notice, this list of conditions and the following disclaimer.  // 
//                                                                  //
// * Redistributions in binary form must reproduce the above        // 
//   copyright notice, this list of conditions and the following    // 
//   disclaimer in the documentation and/or other materials         // 
//   provided with the distribution.                                //
//                                                                  //
// * Neither the name of the University of Huelva nor the names of  //
//   its contributors may be used to endorse or promote products    //
//   derived from this software without specific prior written      // 
//   permission.                                                    //
//                                                                  //
// THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND           // 
// CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES,      // 
// INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF         // 
// MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE         // 
// DISCLAIMED. IN NO EVENT SHALL THE COPRIGHT OWNER OR CONTRIBUTORS //
// BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,         // 
// EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED  //
// TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,    //
// DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND   // 
// ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT          //
// LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING   //
// IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF   //
// THE POSSIBILITY OF SUCH DAMAGE.                                  //
//------------------------------------------------------------------//

//------------------------------------------------------------------//
//                      Universidad de Huelva                       //
//           Departamento de Tecnolog�as de la Informaci�n          //
//   �rea de Ciencias de la Computaci�n e Inteligencia Artificial   //
//------------------------------------------------------------------//
//                     PROCESADORES DE LENGUAJE                     //
//------------------------------------------------------------------//
//                                                                  //
//                  Compilador del lenguaje Tinto                   //
//                                                                  //
//------------------------------------------------------------------//

package parser;

import java.io.File;
import java.io.FileInputStream;
import java.security.DrbgParameters.NextBytes;

import ArbolSintaxisAbs.ConcatList;
import ArbolSintaxisAbs.Expression;
import ArbolSintaxisAbs.Fichero;
import ArbolSintaxisAbs.Operation;
import ArbolSintaxisAbs.OptionList;
import ArbolSintaxisAbs.Symbol;

/**
 * Analizador sint�ctico de Tinto basado en una gram�tica BNF y LL(1)
 * 
 * @author Francisco Jos� Moreno Velo
 *
 */
public class AFDParser implements TokenConstants {

	// ----------------------------------------------------------------//
	// Miembros privados dedicados al an�lisis //
	// ----------------------------------------------------------------//

	/**
	 * Analizador l�xico
	 */
	private AFDLexer lexer;

	/**
	 * �ltimo token analizado
	 */
	private Token prevToken;

	/**
	 * Siguiente token de la cadena de entrada
	 */
	private Token nextToken;

	// ----------------------------------------------------------------//
	// Miembros privados dedicados al tratamiento de errores //
	// ----------------------------------------------------------------//

	/**
	 * Contador de errores
	 */
	private int errorCount;

	/**
	 * Mensaje de errores
	 */
	private String errorMsg;

	// ----------------------------------------------------------------//
	// M�todos relacionados con el an�lisis //
	// ----------------------------------------------------------------//

	/**
	 * Constructor
	 */
	public AFDParser(FileInputStream fis) {
		this.lexer = new AFDLexer(fis);
		this.prevToken = null;
		this.nextToken = lexer.getNextToken();
		this.errorCount = 0;
		this.errorMsg = "";
	}

	// ----------------------------------------------------------------//
	// M�todos relacionados con el tratamiento de errores //
	// ----------------------------------------------------------------//

	/**
	 * Obtiene el n�mero de errores del an�lisis
	 * 
	 * @return
	 */
	public int getErrorCount() {
		return this.errorCount;
	}

	/**
	 * Obtiene el mensaje de error del an�lisis
	 * 
	 * @return
	 */
	public String getErrorMsg() {
		return this.errorMsg;
	}

	/**
	 * Almacena un error de an�lisis
	 * 
	 * @param ex
	 */
	private void catchError(Exception ex) {
		this.errorCount++;
		this.errorMsg += ex.toString();
	}

	/**
	 * Sincroniza la cadena de tokens
	 * 
	 * @param left
	 * @param right
	 */
	private void skipTo(int[] left, int[] right) {
		boolean flag = false;
		if (prevToken.getKind() == EOF || nextToken.getKind() == EOF)
			flag = true;
		for (int i = 0; i < left.length; i++)
			if (prevToken.getKind() == left[i])
				flag = true;
		for (int i = 0; i < right.length; i++)
			if (nextToken.getKind() == right[i])
				flag = true;

		while (!flag) {
			prevToken = nextToken;
			nextToken = lexer.getNextToken();
			if (prevToken.getKind() == EOF || nextToken.getKind() == EOF)
				flag = true;
			for (int i = 0; i < left.length; i++)
				if (prevToken.getKind() == left[i])
					flag = true;
			for (int i = 0; i < right.length; i++)
				if (nextToken.getKind() == right[i])
					flag = true;
		}
	}

	// ----------------------------------------------------------------//
	// M�todos relacionados con el an�lisis de la gram�tica //
	// ----------------------------------------------------------------//

	/**
	 * M�todo que consume un token de la cadena de entrada
	 * 
	 * @param kind Tipo de token a consumir
	 * @throws SintaxException Si el tipo no coincide con el token
	 */
	private void match(int kind) throws SintaxException {
		if (nextToken.getKind() == kind) {
			prevToken = nextToken;
			nextToken = lexer.getNextToken();
		} else
			throw new SintaxException(nextToken, kind);
	}

	public Fichero parse() {
		this.errorCount = 0;
		this.errorMsg = "";
		return tryFichero();
	}

	/**
	 * Analiza el s�mbolo <Fichero>
	 * 
	 * @return
	 */
	public Fichero tryFichero() {
		int[] lsync = {};
		int[] rsync = { EOF };
		Fichero f = null;
		try {
			f = parseFichero();
		} catch (Exception ex) {
			catchError(ex);
			skipTo(lsync, rsync);
		}
		return f;
	}

	/**
	 * Analiza el s�mbolo <Fichero>
	 * 
	 * @return
	 * 
	 * @throws SintaxException
	 */
	public Fichero parseFichero() throws SintaxException {
		int[] expected = { IDENTIFIER };
		OptionList s = null;
		Fichero f = null;
		switch (nextToken.getKind()) {
		case IDENTIFIER:
			String id = nextToken.getLexeme();
			match(IDENTIFIER);
			match(EQ);
			s = tryExpr();
			match(SEMICOLON);
			f = new Fichero(id, s);
			break;
		default:
			throw new SintaxException(nextToken, expected);
		}
		return f;
	}

	
	private OptionList tryOrOption() {
		int[] lsync = {};
		int[] rsync = { SEMICOLON, RPAREN };
		OptionList s = null;
		try {
			s = parseOrOption();
		} catch (Exception ex) {
			catchError(ex);
			skipTo(lsync, rsync);
		}
		return s;
	}

	
	private OptionList parseOrOption() throws SintaxException {
		int[] expected = { SEMICOLON, RPAREN, OR };
		OptionList s = null;
		switch (nextToken.getKind()) {
		case SEMICOLON:
		case RPAREN:
			s = new OptionList();
			break;
		case OR:
			match(OR);
			ConcatList Option_s = tryOption();
			OptionList OrOpt_s = tryOrOption();
			OrOpt_s.addOption(Option_s);
			s = OrOpt_s;
			break;
		default:
			throw new SintaxException(nextToken, expected);
		}
		return s;
	}

	/**
	 * Analiza el s�mbolo <Option>
	 * 
	 * @return
	 */
	private ConcatList tryOption() {
		int[] lsync = {};
		int[] rsync = { SEMICOLON, RPAREN, OR };
		ConcatList s = null;
		try {
			s = parseOption();
		} catch (Exception ex) {
			catchError(ex);
			skipTo(lsync, rsync);
		}
		return s;
	}

	/**
	 * Analiza el s�mbolo <Option>
	 * 
	 * @return
	 * 
	 * @throws SintaxException
	 */
	private ConcatList parseOption() throws SintaxException {
		int[] expected = { SYMBOL, LPAREN };
		ConcatList s = null;
		switch (nextToken.getKind()) {
		case SYMBOL:
		case LPAREN:
			Expression base_s = tryBase();
			ConcatList BasePos_s = tryBasePositive();
			BasePos_s.concat(base_s);
			s = BasePos_s;
			break;
		default:
			throw new SintaxException(nextToken, expected);
		}
		return s;
	}

	/**
	 * Analiza el s�mbolo <Base>
	 * 
	 * @return
	 */
	private Expression tryBase() {
		int[] lsync = {};
		int[] rsync = { SEMICOLON, RPAREN, OR, SYMBOL, LPAREN };
		Expression s = null;
		try {
			s = parseBase();
		} catch (Exception ex) {
			catchError(ex);
			skipTo(lsync, rsync);
		}
		return s;
	}

	/**
	 * Analiza el s�mbolo <Base>
	 * 
	 * @return
	 * 
	 * @throws SintaxException
	 */
	private Expression parseBase() throws SintaxException {
		int[] expected = { SYMBOL, LPAREN };
		Expression s = null;
		switch (nextToken.getKind()) {
		case SYMBOL:
			s = new Symbol(nextToken.getLexeme().charAt(1));
			match(SYMBOL);
			break;
		case LPAREN:
			match(LPAREN);
			Expression h = tryExpr();
			match(RPAREN);
			s = tryOper(h);
			break;
		default:
			throw new SintaxException(nextToken, expected);
		}
		return s;
	}

	/**
	 * Analiza el s�mbolo <Oper>
	 * 
	 * @return
	 */
	private Expression tryOper(Expression h) {
		int[] lsync = {};
		int[] rsync = { SEMICOLON, RPAREN, OR, SYMBOL, LPAREN };
		Expression s = null;
		try {
			s = parseOper(h);
		} catch (Exception ex) {
			catchError(ex);
			skipTo(lsync, rsync);
		}
		return s;
	}

	/**
	 * Analiza el s�mbolo <Oper>
	 * 
	 * @return
	 * 
	 * @throws SintaxException
	 */
	private Expression parseOper(Expression h) throws SintaxException {
		int[] expected = { STAR, PLUS, HOOK, SEMICOLON, RPAREN, OR, SYMBOL, LPAREN };
		Expression s = null;
		switch (nextToken.getKind()) {
		case STAR:
		case PLUS:
		case HOOK:
		case SEMICOLON:
		case RPAREN:
		case OR:
		case SYMBOL:
		case LPAREN:
			s = tryStarPlusHook(h);
			break;
		default:
			throw new SintaxException(nextToken, expected);
		}
		return s;
	}

	
	private Expression tryStarPlusHook(Expression h) {
		int[] lsync = {};
		int[] rsync = { SEMICOLON, RPAREN, OR, SYMBOL, LPAREN };
		Expression s = null;
		try {
			s = parseStarPlusHook(h);
		} catch (Exception ex) {
			catchError(ex);
			skipTo(lsync, rsync);
		}
		return s;
	}

	
	private Expression parseStarPlusHook(Expression h) throws SintaxException {
		int[] expected = { SEMICOLON, RPAREN, OR, SYMBOL, LPAREN, STAR, PLUS, HOOK };
		Expression s = null;
		switch (nextToken.getKind()) {
		case SEMICOLON:
		case RPAREN:
		case OR:
		case SYMBOL:
		case LPAREN:
			s = h;
			break;
		case STAR:
			match(STAR);
			s = new Operation(Operation.STAR, h);
			break;
		case PLUS:
			match(PLUS);
			s = new Operation(Operation.PLUS, h);
			break;
		case HOOK:
			match(HOOK);
			s = new Operation(Operation.HOOK, h);
			break;
		default:
			throw new SintaxException(nextToken, expected);
		}
		return s;
	}

	private ConcatList tryBasePositive() {
		int[] lsync = {};
		int[] rsync = { SEMICOLON, RPAREN, OR };
		ConcatList s = null;
		try {
			s = parseBasePositive();
		} catch (Exception ex) {
			catchError(ex);
			skipTo(lsync, rsync);
		}
		return s;
	}

	
	private ConcatList parseBasePositive() throws SintaxException {
		int[] expected = { SEMICOLON, RPAREN, OR, SYMBOL, LPAREN };

		ConcatList BasePositive = null;

		switch (nextToken.getKind()) {
		case SEMICOLON:
		case RPAREN:
		case OR:
			BasePositive = new ConcatList();
			break;
		case SYMBOL:
		case LPAREN:
			Expression base_s = tryBase();
			ConcatList BasePos_s = tryBasePositive();
			BasePos_s.concat(base_s);
			BasePositive = BasePos_s;
			break;
		default:
			throw new SintaxException(nextToken, expected);
		}
		return BasePositive;
	}

	/**
	 * Analiza el s�mbolo <Expr>
	 */
	private OptionList tryExpr() {
		int[] lsync = {};
		int[] rsync = { SEMICOLON, RPAREN };
		OptionList s = null;
		try {
			s = parseExpr();
		} catch (Exception ex) {
			catchError(ex);
			skipTo(lsync, rsync);
		}
		return s;
	}

	/**
	 * Analiza el s�mbolo <Expr>
	 * 
	 * @return
	 * 
	 * @throws SintaxException
	 */
	private OptionList parseExpr() throws SintaxException {
		int[] expected = { SYMBOL, LPAREN };
		OptionList s = null;
		switch (nextToken.getKind()) {
		case SYMBOL:
		case LPAREN:
			ConcatList Option_s = tryOption();
			OptionList OrOpt_s = tryOrOption();
			OrOpt_s.addOption(Option_s);
			s = OrOpt_s;
			break;
		default:
			throw new SintaxException(nextToken, expected);
		}

		return s;
	}
}
