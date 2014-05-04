package tests;


/**
 * @author Endriu
 *
 */

import static org.junit.Assert.*;


import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import calculations.ExpressionCalculation;
import calculations.RPNConverter;

@SuppressWarnings("unused")

public class ExpressionCalculationTest {


	private ExpressionCalculation Example1[];		// prawidłowe
	private ExpressionCalculation Example2[]; 	// Nieprawidłowe
	private String Expected[]; // Spodziewany wynik konwersji
	private double[] results;
	
	
	
	@Before
	public void setUp()
	{
		Example1= new ExpressionCalculation[2];
		Expected = new String[2];
		results = new double[2];
		
		Example2= new ExpressionCalculation[2];
		
		Example1[0] = new ExpressionCalculation("3+8-(2+4)");
		Expected[0] = "3 + 8 - ( 2 + 4 )";
		results[0]=(double)5;
		
		Example1[1] = new ExpressionCalculation("6*9");
		Expected[1] = "6 * 9";
		results[1] = (double) (6*9);
		// itd....
		
		// przykładowe błędy
		Example2[0] = new ExpressionCalculation("33xxx");
		Example2[1] = new ExpressionCalculation("noname4");
		
	}
	
	
	// sprawdza, czy rzuca wyjątki,jeżeli dostanie błedny String
	@Test
	public void testConvertInputStringToRPNConvertable()
	{
		for(ExpressionCalculation item : Example2)
		try
		{
		String tmp  = item.getRPNConvertableExp();
		System.out.println(tmp);
		
		}
		
		catch(IncorrectTextException e)  // 
		{
			System.out.println(e.getMessage());
		}
		
		Assert.fail("powinien rzucić wyjątek");
	}
	
	// sprawdza poprawne 
	@Test
	public void testConvertInputStringToRPNConvertable_2()
	{
		for(int i=0; i<Example1.length; i++)
		{
			System.out.println(Example1[i].getRPNConvertableExp());
			System.out.println(Expected[i]);
		Assert.assertTrue(Example1[i].getRPNConvertableExp().equals(Expected[i]));
		// getRPNConvertableExp : dodałem ją do ExpressionCalculations
		}
	}
	
	@Test
	public void testCalculate()
	{
		for(int i=0; i<Example1.length; i++)
		{
		System.out.println(Example1[i].getResult()+" | "+(results[i]));
		Assert.assertTrue(Example1[i].getResult()==(results[i]));
		}
	}
	
	

public class IncorrectTextException extends RuntimeException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	IncorrectTextException(String message)
	{
		super(message);
	}
}
	
	

}
