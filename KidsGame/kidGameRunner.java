/*
 * Author: Nick DiRisio
 * Date: May 20th 2014
 * Program: kidGameRunner.java
 * Description: Runner for the kidGame application
 */

public class kidGameRunner {

	public static void main(String[] args) 
	{
		//build an object of the methods class & initialize main menu frame with the constructor
		kidGameMethods methods = new kidGameMethods();
		// next we need to initialize buttons and help window. Everything will generate from within other classes 
		methods.initializeMMbh();
	
	}

}
