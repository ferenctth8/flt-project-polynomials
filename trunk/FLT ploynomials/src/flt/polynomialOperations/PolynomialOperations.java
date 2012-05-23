package flt.polynomialOperations;

import flt.polynomial.Monomial;
import flt.polynomial.Polynomial;

public class PolynomialOperations {

	/**
	 * The method addPolynomials() is responsible for the addition of the
	 * current class attributes
	 * 
	 * @return the result of the addition operation
	 */
	public static Polynomial add(Polynomial termP, Polynomial termQ) {
		int mindeg = 0;
		int maxdeg = 0;
		boolean isLonger = false;

		// compare the 2 degrees to see which one will be assigned to the result
		// of the operation; in addition also establish a similar relation
		// for the nbOfCoefficients of the result
		isLonger = termQ.getDegree() < termP.getDegree();
		if (termQ.getDegree() < termP.getDegree()) {
			maxdeg = termP.getDegree();
			mindeg = termQ.getDegree();
		} else {
			maxdeg = termQ.getDegree();
			mindeg = termP.getDegree();
		}
		Polynomial additionResult = new Polynomial(maxdeg);
		// until finishing with the operand of lower degree, perform the
		// addition of the coefficients normally
		for (int i = 0; i <= mindeg; i++) {
			// get the coefficients in question
			double coefficient1 = termP.getMonomialAt(i).getCoefficient();
			double coefficient2 = termQ.getMonomialAt(i).getCoefficient();
			// compute their sum
			double sum = coefficient1 + coefficient2;
			// set the result at the corresponding position
			additionResult.setMonomialAt(i, new Monomial(i, sum));
		}
		// copy the rest of the coefficients from the array associated to the
		// operand possessing a higher degree
		for (int i = mindeg + 1; i <= maxdeg; i++) {
			if (isLonger) {
				additionResult.setMonomialAt(i, termP.getMonomialAt(i));
			} else {
				additionResult.setMonomialAt(i, termQ.getMonomialAt(i));
			}
		}
		return additionResult;
	}

	/**
	 * The method subtractPolynomials() is responsible for the subtraction of
	 * the 2 attributes of the current class
	 * 
	 * @return the result of the subtraction
	 */
	public static Polynomial subtract(Polynomial minuend,
			Polynomial subtrahend) {
		int mindeg = 0;
		int maxdeg = 0;
		boolean isLonger = false;

		// compare the 2 degrees to see which one will be assigned to the result
		// of the operation; in addition also establish a similar relation
		// for the nbOfCoefficients of the result
		isLonger = subtrahend.getDegree() < minuend.getDegree();
		if (subtrahend.getDegree() < minuend.getDegree()) {
			maxdeg = minuend.getDegree();
			mindeg = subtrahend.getDegree();
		} else {
			maxdeg = subtrahend.getDegree();
			mindeg = minuend.getDegree();
		}
		Polynomial subtractionResult = new Polynomial(maxdeg);
		// until finishing with the operand of lower degree, perform the
		// addition of the coefficients normally
		for (int i = 0; i <= mindeg; i++) {
			// get the coefficients in question
			double coefficient1 = minuend.getMonomialAt(i).getCoefficient();
			double coefficient2 = subtrahend.getMonomialAt(i).getCoefficient();
			// compute their sum
			double difference = coefficient1 - coefficient2;
			// set the result at the corresponding position
			subtractionResult.setMonomialAt(i, new Monomial(i, difference));
		}
		// copy the rest of the coefficients from the array associated to the
		// operand possessing a higher degree
		for (int i = mindeg + 1; i <= maxdeg; i++) {
			if (isLonger) {
				subtractionResult.setMonomialAt(i, minuend.getMonomialAt(i));
			} else {
				subtractionResult.setMonomialAt(i, subtrahend.getMonomialAt(i));
			}
		}
		return subtractionResult;
	}

	public static Polynomial multiply(Polynomial multiplicand,
			Polynomial multiplier) {
		// create the result Polynomial object with the degree equal to the sum
		// of the degrees of the parameters
		Polynomial multiplicationResult = new Polynomial(
				multiplicand.getDegree() + multiplier.getDegree());
		// then we perform the requested operation
		for (int i = 0; i <= multiplicand.getDegree(); i++) {
			// take the coefficient of the multiplicand
			double coefficient1 = multiplicand.getMonomialAt(i).getCoefficient();
			for (int j = 0; j <= multiplier.getDegree(); j++) {
				// then take the coefficients of the multiplier, 1 by 1
				double coefficient2 = multiplier.getMonomialAt(i)
						.getCoefficient();
				// and of the result from the sum of the current coefficients
				double resCoefficient = multiplicationResult
						.getMonomialAt(i + j).getCoefficient();
				// set the value of the new coefficient
				multiplicationResult.getMonomialAt(i + j).setCoefficient(
						resCoefficient + coefficient1 * coefficient2);
			}
		}
		// and finally return the result
		return multiplicationResult;
	}

	/**
	 * The current method, named dividePolynomials(), executes the division
	 * operation between the first 2 attributes.
	 */
	public static Polynomial[] divide(Polynomial dividend,
			Polynomial divisor) {

		Polynomial quotient;
		Polynomial remainder;
		Polynomial[] result = new Polynomial[2];
		// step 1:before performing the division itself, we have to test the
		// dividend
		// and the divisor to see how their degrees are and take appropriate
		// action
		if (dividend.getDegree() < divisor.getDegree()) {
			// step 2: if the degree of the dividend is less than that of the
			// divisor
			// the remainder is the dividend, while the quotient is 0
			quotient = new Polynomial(-1);
			remainder = dividend;
		}
		// step 3:if all the prerequisite conditions are assumed to be
		// satisfied,
		// then we shall begin the performance of the division itself
		// step 3a:set the degrees of the quotient and the remainder
		quotient = new Polynomial(dividend.getDegree() - divisor.getDegree());
		remainder = new Polynomial(divisor.getDegree() - 1);
		// step 3b:perform the necessary arithmetic operations associated to our
		// division
		// among the coefficients of the Polynomial objects in question as well
		// as
		// set the values from the coefficient array of the old Polynomial to 0
		for (int i = quotient.getDegree(), j = dividend.getDegree(); i >= 0; i--, j--) {
			// get the divisor and dividend coefficients
			double dividendCoefficient = dividend.getMonomialAt(j)
					.getCoefficient();
			double divisorCoefficient = divisor.getMonomialAt(
					divisor.getDegree()).getCoefficient();
			// perform the division operation of the coefficients in question
			double divResultCoefficient = dividendCoefficient
					/ divisorCoefficient;
			// set the current quotient element according to the result
			quotient.setMonomialAt(i, new Monomial(i, divResultCoefficient));
			// adjust the partial result as the new dividend
			for (int k = divisor.getDegree(); k >= 0; k--) {
				// retrieve the coefficients in question
				double partDividendCoefficient1 = dividend.getMonomialAt(i + k)
						.getCoefficient();
				double partDividendCoefficient2A = quotient.getMonomialAt(i)
						.getCoefficient();
				double partDividendCoefficient2B = divisor.getMonomialAt(k)
						.getCoefficient();
				// compute the product of the last 2
				double partDividendCoefficient2 = partDividendCoefficient2A
						* partDividendCoefficient2B;
				// compute the difference of the first coefficient and the
				// previous product
				double newCoefficient = partDividendCoefficient1
						- partDividendCoefficient2;
				dividend.setMonomialAt(i + k,
						new Monomial(i + k, newCoefficient));
			}
			dividend.setMonomialAt(j, new Monomial(j, 0));
		}

		// step 3c:assign all the non-zero coefficients from the array of the
		// old
		// dividend Polynomial to the positions in the array of the remainder
		// Polynomial
		for (int i = 0; i <= divisor.getDegree() - 1; i++) {
			remainder.setMonomialAt(i, dividend.getMonomialAt(i));
		}
		result[0] = quotient;
		result[1] = remainder;

		return result;
	}

	public static Polynomial computeRemainder(Polynomial divisor,
			Polynomial dividend) {
		Polynomial[] result = divide(dividend, divisor);
		return result[1];
	}
	
	public static Polynomial computeQuotient(Polynomial divisor,
			Polynomial dividend) {
		Polynomial[] result = divide(dividend, divisor);
		return result[0];
	}
	
	/**
	 * The method differentiatePolynomial() is a method designed for computing the first formal derivative of a given Polynomial object.
	 * @return result - the Polynomial object being the result of the differentiation
	 */	
	public static Polynomial differentiate(Polynomial difInput) {
		//create the object which is to be returned as a process result
		Polynomial differentiationResult = new Polynomial(difInput.getDegree() - 1);
		for (int i = difInput.getDegree(); i > 0; i--) {
			//get the current monome component of the input polynomial
			double currentCoefficient = difInput.getMonomialAt(i).getCoefficient();
			//compute formal derivative of order 1 by simply multiplying the current coefficient with the old power
			differentiationResult.getMonomialAt(i - 1).setCoefficient(currentCoefficient*i);
			//result.setElementAt(i - 1, new Monomial(i - 1, currentCoefficient*i));
		}
		return differentiationResult;				
	}
	
	/**
	 * The method computeIntegral() is a method written to compute the integral
	 * of the Polynomial object given as a parameter, with the result given back
	 * in a new Polynomial object
	 * @return result - the result of the integration
	 */
	public static Polynomial integrate(Polynomial integInput) {
		//create the result object to be processed further on
		Polynomial result = new Polynomial(integInput.getDegree() + 1);
		//set its last element to 1
		result.setMonomialAt(0, new Monomial(0,1.0));
		//for the rest of the elements, just compute the division between the current coefficient and the old degree
		for (int i = 1; i <= integInput.getDegree() + 1; i++) {
			//get the coefficient of the old term
			double initialCoefficient = integInput.getMonomialAt(i-1).getCoefficient();
			//divide it with the current power
			double integCoefficient = initialCoefficient / i;
			//set the current result element as a new monomial component
			result.setMonomialAt(i, new Monomial(i, integCoefficient));
		}
		return result;
	}
}
