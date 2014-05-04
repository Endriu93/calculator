package tests;

/**
 * @author Endriu
 *
 */

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import calculations.RPNConverter;

@RunWith(Parameterized.class) 
public class RPNConverterTest {

	
	private RPNConverter example;
	private String Expected; // Spodziewany wynik konwersji
	
	
	
	public RPNConverterTest(String ToConverting, String Converted)
	{
		example = new RPNConverter(ToConverting);
		Expected = Converted;
	}
	
	@Parameters
	public static Collection<String[]> data()
	{
		String[][] database = {{"3 + 4 * 2 / ( 1 - 5 ) ^ 2","3 4 2 * 1 5 - 2 ^ / +"}
							,{"5 * 8 ^ 3 ( 6 + 8 ) - 32","5 8 3 6 8 + ^ * 32 -"}
							,{"log ( 5 ) - abs ( -30 )","5 log -30 abs -"}
							};
		// Można dodać ciekawe przypadki do tablicy
		
		return Arrays.asList(database);
	}
	
	@Test
	public void testConvertToRPN()
	{
		Assert.assertTrue((example.convertToRPN().trim()).equals(Expected.trim()));
		System.out.println(example.convertToRPN());
		System.out.println(Expected);
		
	}

}
