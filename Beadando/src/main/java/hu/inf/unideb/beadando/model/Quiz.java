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
public interface Quiz {
	/** */
	int getNumberOfQuestions();
    /** */
	String[] getQuestionIDstrings();
    /** */
	String getQuestionText(int questionNumber);
    /** */
	String getQuestionText(String questionIDstring);
    /** */
	String getQuestionAnswerText(int answerNumber, int questionNumber);
    /** */
	boolean isQuestionAnswerCorrect(int answerNumber, int questionNumber);
    /** */
	String getQuestionAnswerText(int answerNumber, String questionIDstring);
    /** */
//	boolean isQuestionAnswerCorrect(int answerNumber, String questionIDstring);
} // Quiz class
