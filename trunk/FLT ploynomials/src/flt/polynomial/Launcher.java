package flt.polynomial;

import java.io.FileReader;

import java_cup.runtime.Symbol;
import flt.polynomialInterpreter.parser.PolynomialInterpreterLexer;
import flt.polynomialInterpreter.parser.PolynomialParser;

public class Launcher {

	/**
	 * Main class - interprets the code from file sampleInput.txt
	 * @param args - the program arguments
	 */
	public static void main(String[] args) throws Exception{
		String inputFile = "sampleInput.txt";
		//define the lexer and parser
		PolynomialInterpreterLexer polyLexer = new PolynomialInterpreterLexer(new FileReader(inputFile));
		PolynomialParser polyParser = new PolynomialParser(polyLexer);
		// Get the first monomial. Parsing result will be in Symbol.value
		Symbol sym = polyParser.parse();
		Monomial monomial = (Monomial) sym.value;

		// build Polynomial out of given result
		Polynomial polynomial = new Polynomial(monomial.getDegree(), monomial);
        System.out.println(polynomial);		
	}

}
