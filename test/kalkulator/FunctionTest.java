/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kalkulator;

import junit.framework.TestCase;

/**
 *
 * @author Bartosz
 */
public class FunctionTest extends TestCase {
    
    public FunctionTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of Log method, of class Function.
     */
    public void testLog() {
        System.out.println("Log");
        Double [][] n = new Double[][]{{-3., -2.5, -1., 0., 1., 2., 3.4, 3.5,  1.1E100, -1.1E100}, 
                                       {1., -1., 0., 0., 0., 0.69314, 1.2237, 1.25276, 230.35282, -1.}};
        for(int i = 0; i < 10; i++){
        try{
            Double x = n[0][i];
            double expResult = n[1][i];
            double result = Function.Log(x);
            assertEquals(expResult, result, 0.001);
        }
        catch(NegativeLogException e){
            assertTrue(e instanceof NegativeLogException);
        }
        }
    }

    /**
     * Test of Sqrt method, of class Function.
     */
    public void testSqrt() {
        System.out.println("Sqrt");
        Double [][] n = new Double[][]{{-3., -2.5, -1., 0., 1., 2., 3.4, 3.5,  1.1E100, -1.1E100}, 
                                       {1., -1., 0., 0., 1., 1.41421, 1.84391, 1.187083, 1.0488088481E50, -1.}};
        for(int i = 0; i < 10; i++){
        try{
            Double x = n[0][i];
            double expResult = n[1][i];
            double result = Function.Sqrt(x);
            assertEquals(expResult, result, 0.001);
        }
        catch(Exception e){
        }
        }
    }

    /**
     * Test of Abs method, of class Function.
     */
    public void testAbs() {
        System.out.println("Abs");
        Double [][] n = new Double[][]{{-3., -2.5, -1., 0., 1., 2., 3.4, 3.5,  1.1E100, -1.1E100}, 
                                       {3., 2.5, 1., 0., 1., 2., 3.4, 3.5, 1.1E100, 1.1E100}};
        for(int i = 0; i < 10; i++){
            Double x = n[0][i];
            double expResult = n[1][i];
            double result = Function.Abs(x);
            assertEquals(expResult, result, 0.001);
        }
    }

    /**
     * Test of Fact method, of class Function.
     */
    public void testFact() {
        System.out.println("Fact");
        Double [][] n = new Double[][]{{-3., -2.5, -1., 0., 1., 2., 3., 3.5,  10., 100.}, 
                                       {3., 2.5, 1., 1., 1., 2., 6., 11.6317, 3628800., 93326215443944152681699238856266700490715968264381621468592963895217599993229915608941463976156518286253697920827223758251185210916864000000000000000000000000.}};
        for(int i = 0; i < 10; i++){
            try{
                Double x = n[0][i];
                double expResult = n[1][i];
                double result = Function.Fact(x);
                assertEquals(expResult, result, 0.001);
            }
            catch(Exception e){
            }
            }
        }
}
