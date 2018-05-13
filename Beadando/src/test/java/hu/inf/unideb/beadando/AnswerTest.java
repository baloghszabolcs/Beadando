/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.inf.unideb.beadando;

import hu.inf.unideb.beadando.model.ParserXml;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author balogh
 */

public class AnswerTest {
    
        private static ParserXml parser = new ParserXml("src/test/java/hu/inf/unideb/beadando/quiztest.xml");
     
        public AnswerTest(){}
    
      @BeforeClass
    public static void setUpClass() {
        parser = new ParserXml("src/test/java/hu/inf/unideb/beadando/quiztest.xml");
    }
    @AfterClass
    public static void tearDownClass() {
        parser = null;
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    /**
     * Tests if an answer is true or false
     */
    
     @Test
    public void correctAnswerTest() {
        
        boolean expResult = true;
        boolean result = parser.isQuestionAnswerCorrect(1, 1);
        assertEquals(expResult,result);
        
        expResult = false;
        result = parser.isQuestionAnswerCorrect(2, 1);
        assertEquals(expResult,result);
        

    }
    
    /**
     * Tests that one of the answers is correct
     */
       @Test
    public void oneIsCorrectTest() {
        
        boolean expResult = true;
        boolean result = (parser.isQuestionAnswerCorrect(1, 1) != parser.isQuestionAnswerCorrect(2, 1));
        assertEquals(expResult,result);
        

    }
    
    
}
