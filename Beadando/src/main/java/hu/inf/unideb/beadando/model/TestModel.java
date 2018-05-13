/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.inf.unideb.beadando.model;

/**
 *
 * @author balogh
 */
//import hu.inf.unideb.beadando.model.ParserXml;
import java.util.ArrayList;
import java.util.Iterator;
//import model.QuestionModel;

//import net.sourceforge.javaquiz.util.ParserXml;
/**
 * 
 * @author Larry
 *
 */
public class TestModel {
	/** */
	private boolean DEBUG = false;
	/** */
	private int numberOfQuestions;
    /**
     * 
     * @param fileName
     */
	public TestModel(String fileName) {
		ParserXml parser = new ParserXml(fileName);
		numberOfQuestions = parser.getNumberOfQuestions();
		for (int i = 0; i < numberOfQuestions; i++) {
			String question = null;
			String answer1 = null;
			String answer2 = null;
			int correctAnswer = 0;
			question = parser.getQuestionText(i + 1);
			answer1 = parser.getQuestionAnswerText(1, i + 1);
			answer2 = parser.getQuestionAnswerText(2, i + 1);
			if (parser.isQuestionAnswerCorrect(1, i + 1)) {
				correctAnswer = 1;
			}
			if (parser.isQuestionAnswerCorrect(2, i + 1)) {
				correctAnswer = 2;
			}
			QuestionModel questionModel = new QuestionModel(question, answer1, answer2, correctAnswer);       
			this.addQuestion(questionModel);
		}
	}
    /** */
	private ArrayList<QuestionModel> questions = new ArrayList<QuestionModel>();

    public ArrayList<QuestionModel> getQuestions() {
        return questions;
    }
        
	/**
	 * 
	 * @param question
	 */
	public void addQuestion(QuestionModel question) {
		this.questions.add(question);
	}
        
    /** */
	private boolean freshIterator = true;
	/** */
	private Iterator<QuestionModel> questionsIterator;
    /**
     * 
     * @return
     */
	public boolean moreQuestions() {
		if (freshIterator) {
			questionsIterator = questions.iterator();
			freshIterator = false;
		}
		if (questionsIterator.hasNext()) {
			return true;
		} else {
			freshIterator = true;
			return false;
		}
	}
    /**
     * 
     * @return
     */
	public QuestionModel nextQuestion() {
		return (QuestionModel) questionsIterator.next();
	}
    /**
     * 
     * @return
     */
	public int getNumberOfQuestions() {
		return numberOfQuestions;
	}

} //TestModel class
