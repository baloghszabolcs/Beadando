/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.inf.unideb.beadando;
import hu.inf.unideb.beadando.model.QuestionModel;
import hu.inf.unideb.beadando.model.TestModel;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * @author balogh
 */
public class QuizTest {
    
    private static QuizViewController quiz;
    
     public QuizTest() {
    }
     
     @BeforeClass
    public static void setUpClass() {
        quiz = new QuizViewController();
    }
    @AfterClass
    public static void tearDownClass() {
        quiz = null;
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void scoreTest() {
        quiz.setScore(10);
        quiz.scorePlus();
        int expected = 11;
        assertEquals(expected, quiz.getScore());
        
        quiz.setScore(20);
        quiz.scorePlus();
        expected = 21;
        assertEquals(expected, quiz.getScore());


    }
     
}
