package src.polynomials;

public class Polynomial {
	/**
	 * These are the 2 instance variables the application uses mainly:
	 * degree - an integer value which represents the degree of the Polynomial object
	 * coefficientVector - a vector of integers which make up the array of coefficients
	 *                     for the given Polynomial object
	 */
	private int degree;
	private float[] coefficientVector;

	/**
	 * The default parameterless class constructor, Polynomial(), is responsible for 
	 * setting up a new object, initially empty.It is only specified here, because 
	 * of not being provided by Java due to the usage of its previous, parameterized 
	 * version.
	 */
	public Polynomial(){

	}

	/**
	 * The constructor Polynomial() has the task of building a given object of this
	 * class with the degree given as an integer parameter to it.
	 * @param deg - the integer to be used as a degree indicator of the current 
	 *              Polynomial object
	 */
	public Polynomial(int deg){
		//set the degree first
		this.degree = deg;
		//reserve space for the coefficients next
		this.coefficientVector = new float[deg+1];
	}	

	/**
	 * The method toString() is inherited from class Object, here it is overridden 
	 * in order to output the Polynomial object created by the class constructor 
	 * from the procession of the input file
	 * @return s - the String object containing the required Polynomial
	 */
	public String toString(){
		//define the returned String object containing the required Polynomial 
		//initialize it with the free term of our Polynomial object - i.e. the 
		//coefficient of X^0
		String s =""; 
		if (degree >0){
		s = this.coefficientVector[0] + " + ";
		//attach successively the remaining terms of the Polynomial to the String
		
			for (int i = 1; i < this.degree; i++){
				s += this.coefficientVector[i] + "*X^" + i + " + ";
			}
			//attach the last term of the Polynomial to the String
			s += this.coefficientVector[degree] + "*X^" + degree;
		} else {
			s = ""+this.coefficientVector[0];
		}
		return s;
	}
	
	/**
	 * The method getCoefficients returns the coefficientVector 
	 * array containing the coefficients of a given Polynomial object     
	 * @return coefficientVector - the vector containing the real coefficients of the 
	 *                             current Polynomial object
	 */
	public float[] getCoefficients(){
		return this.coefficientVector;
	}

	/**
	 * The method setCoefficients() has the task of setting the coefficients of given
	 * Polynomial object 
	 * @param coefficients - the vector of real values to be assigned as polynomial 
	 *                       coefficients
	 */
	public void setCoefficients(float[] coefficients){
		for (int i=0; i<=this.degree; i++){
			this.coefficientVector[i] = coefficients[i];
		}	
	}

	/**
	 * The method getDegree() is defined as a mutator method for accessing the 
	 * degree of the current Polynomial object
	 * @return degree - an integer value representing the degree of the current
	 *                  Polynomial object
	 */
	public int getDegree(){
		return this.degree;
	}

	/**
	 * The method setDegree() has the responsibility of setting the degree of the 
	 * the current Polynomial object
	 * @param deg - the integer value representing the current Polynomial degree 
	 *              which is to be checked for correctness
	 */
	public void setDegree(int deg){
		//define a local variable of type int, called testDeg to verify if the degree
		//given as a parameter is valid or not
		int testDeg = deg;
		//define also a counter for traversing the array of coefficients in case 
		//it is necessary
		int i=0;
		//check if the degree is a non-negative number 
		if (deg>=0) {
			//check if the degree truly represents the current Polynomial degree
			if (this.coefficientVector[0]>0){
				this.degree = testDeg;
			} else {
				//in case the first element of the coefficientVector is 0,decrease
				//testDeg - proceed similarly until the first non-zero element is 
				//encountered
				do {
					testDeg--;
					i++;
				} while(this.coefficientVector[i]==0);
				this.degree = testDeg;
			}
		} else {
			//specify the wrong choice and the cause of it
			System.out.println("Wrong choice for degree assignment!");
		}
	}

	/**
	 * The method getElementAt() has the task of accessing the element with the index
	 * given as a parameter from the array of coefficients
	 * @param i - an integer value given the element index
	 * @return - the double element of the coefficient vector situated at the position given
	 *           as a parameter to the method
	 */
	public float getElementAt(int i){
		return coefficientVector[i];
	}

	/**
	 * The method setElementAt() has the responsibility of setting the element from
	 * a given index inside the coefficient array of the Polynomial object
	 * @param i - an integer value given the element index
	 * @param value - the double element to be put inside the array at the specified index
	 */
	public void setElementAt(int i, float value){
		coefficientVector[i] = value;
	}

	/**
	 * The method scalarMultiply is responsible of fulfilling 2 main tasks:
	 * 1.perform the multiplication between a scalar and a Polynomial - fact remarkable
	 * from the input parameter taken in by it
	 * 2.retrieve the result of this operation in form of a String - fact indicated by
	 * its return type
	 * @param scalar - the integer value multiplying the current Polynomial object
	 * @return s - the String object holding the result of the multiplication
	 */
	public void scalarMultiply(int scalar){
		//in case of a multiplication with a null value, we get the null Polynomial,
		//so just output 1 single 0 at the end
		if (scalar == 0) {
			System.out.println("The result is the null polynomial");
		} else {
			//otherwise, perform the operation normally...
			for(int i=0; i<=this.degree; i++){
				this.coefficientVector[i] *= scalar;
			}
		}
	}

	/**
	 * The method evaluatePolynomial() has the responsibility of evaluating the 
	 * current Polynomial object for a given value represented by the integer n by 
	 * using the well-known scheme described by mathematicians William George Horner
	 * (1786-1837) and Paolo Ruffini(1765-1822).In particular, if for a parameter n 
	 * the value of the current Polynomial object is 0, it means that the corresponding
	 * parameter is a root for the object in question and the method will display an
	 * appropriate message. 
	 * @param n - the integer parameter used for examining the Polynomial behavior
	 * @return value - the integer value of the current Polynomial object associated 
	 *                 with the parameter n
	 */
	public int evaluatePolynomial(int n){
		//define the variable to hold the returned result, initialized to 0
		float value = 0;
		//use the Horner-Ruffini scheme for evaluation of the Polynomial in case of 
		//the given parameter n
		for(int i=this.degree; i>=0; i--){
			value = this.coefficientVector[i] + value * n;
		}
		//display a message for the previously mentioned particular case and
		//return the final result of the computation
		return (int)value;
	}

}
