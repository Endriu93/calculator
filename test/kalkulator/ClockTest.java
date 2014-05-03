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
public class ClockTest extends TestCase {
    
    public ClockTest(String testName) {
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
     * Test of startClock method, of class Clock.
     */
    public void testStartClock() {
        System.out.println("startClock");
        Clock instance = new Clock();
        instance.startClock();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of stopClock method, of class Clock.
     */
    public void testStopClock() {
        System.out.println("stopClock");
        Clock instance = new Clock();
        instance.stopClock();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOperationTime method, of class Clock.
     */
    public void testGetOperationTime() {
        System.out.println("getOperationTime");
        Clock instance = new Clock();
        long expResult = 0L;
        long result = instance.getOperationTime();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
