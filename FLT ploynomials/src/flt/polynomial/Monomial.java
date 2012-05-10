package flt.polynomial;

public class Monomial {

	/**
	 * The attribute list of the current class.The only attributes of a monome are its degree and coefficient. 
	 */
	private int degree;
	private double coefficient;
	
	/**
	 * The default parameterless constructor.
	 */
	public Monomial(){
		
	} 
	
	/**
	 * The parameterized class constructor responsible for setting the degree and the coefficient of the Monome in question
	 * @param deg - the degree of the termX from its body
	 * @param coefficient - the coefficient of the term in question
	 */
	public Monomial(int deg, double coefficient){
		this.degree = deg;
		this.coefficient = coefficient;
	}

	/**
	 * @return the degree of the monome in question
	 */
	public int getDegree() {
		return degree;
	}

	/**
	 * @param degree the degree to set for the current monomial
	 */
	public void setDegree(int degree) {
		this.degree = degree;
	}

	/**
	 * @return the coefficient of the current monomial 
	 */
	public double getCoefficient() {
		return coefficient;
	}

	/**
	 * @param coefficient the coefficient to set for the current monomial
	 */
	public void setCoefficient(double coefficient) {
		this.coefficient = coefficient;
	}

	/**
	 * The overridden toString() method - used for returning the String representation of the current object
	 * According to the values of the coefficient and the degree, we may have the following five cases for return:
	 * coefficient = 1, degree <> 0 => return X^^degree
	 * coefficient = 1, degree = 0 => return 1.0
	 * coefficient <> 1, degree <> 0 => return coefficient*X^^degree
	 * coefficient <> 1, degree = 0 => return coefficient
	 * coefficient = 0 => return empty String  
	 */
	public String toString() {
		if (this.coefficient == 1.0 && this.degree != 0){
			return "X^^" + this.degree;
		} 
		if (this.coefficient == 1.0 && this.degree == 0){
			return "1.0";
		}
		if (this.coefficient != 1.0 && this.degree != 0){
			return this.coefficient + "*X^^" + this.degree;
		}
		if (this.coefficient != 1.0 && this.degree == 0){
			return new Double(this.coefficient).toString();
		}
		if (this.coefficient == 0.0){
			return "";
		}
		return null;
	}
	
	
}
