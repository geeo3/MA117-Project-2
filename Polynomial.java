/*
 * PROJECT II: Polynomial.java
 *
 * This file contains a template for the class Polynomial. Not all methods are
 * implemented. Make sure you have carefully read the project formulation
 * before starting to work on this file.
 *
 * This class is designed to use Complex in order to represent polynomials
 * with complex co-efficients. It provides very basic functionality and there
 * are very few methods to implement! The project formulation contains a
 * complete description.
 *
 * Remember not to change the names, parameters or return types of any
 * variables in this file! You should also test this class using the main()
 * function.
 *
 * The function of the methods and instance variables are outlined in the
 * comments directly above them.
 */

class Polynomial {
    /**
     * An array storing the complex co-efficients of the polynomial.
     */
    Complex[] coeff;

    // ========================================================
    // Constructor functions.
    // ========================================================

    /**
     * General constructor: assigns this polynomial a given set of
     * co-efficients.
     *
     * @param coeff  The co-efficients to use for this polynomial.
     */
    public Polynomial(Complex[] coeff) {
        // First, we check how many of the leading co-efficients are zero.
        int zeroCounter = 0; //Start with no leading co-efficients being 0.
        boolean isZero  = true; //We set isZero as true to get into the loop.
        while(isZero){ 
        	isZero = false; 
        	if(coeff[coeff.length - 1 - zeroCounter].abs2() == 0){ //Checks if current leading co-efficient is zero
        		zeroCounter++; //If zero, increases counter by one
        		isZero = true; // Sets isZero to true so we know to check if the next leading co-efficient  is zero.
        		}
        	}
        this.coeff = new Complex[coeff.length - zeroCounter]; //Sets up an array of the correct length.
        for (int i = 0; i < this.coeff.length; i++){
        	this.coeff[i] = new Complex(coeff[i].getReal(),coeff[i].getImag()); //Fills up coeff with the co-efficients of the polynomial.
        	}
        
        
    }
    
    /**
     * Default constructor: sets the Polynomial to the zero polynomial.
     */
    public Polynomial() {
        // You need to fill in this function.
        coeff = new Complex[1]; //Creates coeff as an array of length 1
        coeff[0] = new Complex(); //Sets the only entry in the array as 0, by calling the default constructor.
    }

    // ========================================================
    // Operations and functions with polynomials.
    // ========================================================

    /**
     * Create a string representation of the polynomial.
     *
     * For example: (1.0+1.0i)+(1.0+2.0i)X+(1.0+3.0i)X^2
     */
    public String toString() {
        // You need to fill in this function.
        String returnString = ""; //Initialises the string.
        for (int i = 0; i < this.coeff.length; i++){
        	if (i == this.coeff.length - 1){ //If term of greatest degree
        		if (i == 0){
        			returnString += "(" + this.coeff[i].toString() + ")";
        			}
    			else if (i == 1){
    				returnString += "(" + this.coeff[i].toString() + ")X";
        			}	
        		else{
        			returnString += "(" + this.coeff[i].toString() + ")X^" + i;
        			}			
    			}
	    		else{
		 			if (i == 0){
        			returnString += "(" + this.coeff[i].toString() + ")+";
        			}
        		else if (i == 1){
    				returnString += "(" + this.coeff[i].toString() + ")X+";
    				}
        		else{
        			returnString += "(" + this.coeff[i].toString() + ")X^" + i + "+";
        			}
    			}    		
        	}
        return returnString;
        }

    /**
     * Returns the degree of this polynomial.
     */
    public int degree() {
        // You need to fill in this function.
        return (this.coeff.length - 1); //degree is the last index of the array
    }

    /**
     * Evaluates the polynomial at a given point z.
     *
     * @param z  The point at which to evaluate the polynomial
     * @return   The complex number P(z).
     */
    public Complex evaluate(Complex z) {
        // You need to fill in this function.
        Complex eval = new Complex();
        for(int i = degree(); i >= 0; i--){ //Implement's Horner's method for evaluating polynomials.
       		eval = eval.multiply(z).add(this.coeff[i]);
       		}
       	return eval;
    }
    
    /**
     * Calculate and returns the derivative of this polynomial.
     *
     * @return The derivative of this polynomial.
     */
    public Polynomial derivative() {
        // You need to fill in this function.
        if (this.coeff.length > 1){
        	Complex[] derivCoeff = new Complex[coeff.length - 1]; //create a new array to feed into the constructor
        	for (int i = 1; i <= degree(); i++){ //If the polynomial is not of degree zero or less, differentiate as normal
        		derivCoeff[i - 1] = new Complex(i*(this.coeff[i].getReal()),i*(this.coeff[i].getImag()));
        		}
        	return new Polynomial(derivCoeff);
        } else { //return zero polynomial
        	return new Polynomial();
        	}	
    }
    
    // ========================================================
    // Tester function.
    // ========================================================

    public static void main(String[] args) {
        // Set up some arrays to give the constructors
        Complex[] cf1 = new Complex[] {new Complex(2), new Complex(3), new Complex(1), new Complex(), new Complex()};
        Complex[] cf2 = new Complex[] {new Complex(3), new Complex(5)};
        Complex[] cf3 = new Complex[] {new Complex(5)};
        //test the constructors
        Polynomial p = new Polynomial(cf1);
        Polynomial q = new Polynomial();
        Polynomial r = new Polynomial(cf2);
        Polynomial s = new Polynomial(cf3);
        //tests for polynomial p
        System.out.println("Polynomial p: "+p.toString()); //Should be 2 + 3X + X^2
        System.out.println("Degree of p: "+p.degree());
        System.out.println("Derivative of p: "+p.derivative());
        System.out.println("P at z = -1: "+p.evaluate(new Complex(-1)));
        //tests for polynomial r
        System.out.println("Polynomial r: "+r.toString()); // Should be 0
        System.out.println("Degree of r: "+r.degree()); //Should be 0
        System.out.println("Derivative of r: " + r.derivative());
        System.out.println("R at z = -1:" + r.evaluate(new Complex(-1)));
        //test for polynomial q
        System.out.println("Polynomial q:" + q.toString());
        System.out.println("Degree of q: " + q.degree());
        System.out.println("Derivative of q: "+ q.derivative());
        System.out.println("Q at z = -1: " +q.evaluate(new Complex(-1)));
        //tests for polynomial s
        System.out.println("Polynomial s :" + s.toString());
        System.out.println("Degree of S: "+s.degree());
        System.out.println("Derivative of S: "+s.derivative());
        System.out.println("S at z = -1: "+s.evaluate(new Complex(-1)));
        
        
        
    }
}