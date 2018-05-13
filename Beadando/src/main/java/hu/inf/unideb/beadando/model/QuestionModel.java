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
public class QuestionModel {
	/**    */
	private String questionText;
	/**    */
    private String answer1Text;
	/**    */
	private String answer2Text;
	/**    */
//	private String answer3Text;
//	/**    */
//	private String answer4Text;
//	/**    */
	private int correctAnswer;
    /**
     * 
     * @return
     */
	public String getQuestionText() {
		return questionText;
	} // getter
    /**
     * 
     * @param questionText
     */
	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	} // setter
    /**
     * 
     * @return
     */
	public String getAnswer1Text() {
		return answer1Text;
	} // getter
    /**
     * 
     * @param answer1Text
     */
	public void setAnswer1Text(String answer1Text) {
		this.answer1Text = answer1Text;
	}  // setter
    /**
     * 
     * @return
     */
	public String getAnswer2Text() {
		return answer2Text;
	} // getter
    /**
     * 
     * @param answer2Text
     */
	public void setAnswer2Text(String answer2Text) {
		this.answer2Text = answer2Text;
	} // setter
    /**
     * 
     * @return
     */
//	public String getAnswer3Text() {
//		return answer3Text;
//	} // getter
//    /**
//     * 
//     * @param answer3Text
//     */
//	public void setAnswer3Text(String answer3Text) {
//		this.answer3Text = answer3Text;
//	} // setter
    /**
     * 
     * @return
     */
//	public String getAnswer4Text() {
//		return answer4Text;
//	} // getter
//    /**
//     * 
//     * @param answer4Text
//     */
//	public void setAnswer4Text(String answer4Text) {
//		this.answer4Text = answer4Text;
//	} // setter
    /**
     * 
     * @param question
     * @param answer1
     * @param answer2
     * @param answer3
     * @param answer4
     * @param correctAnswer
     */
	public QuestionModel(String question, String answer1, String answer2,int correctAnswer){
//			String answer3, String answer4, int correctAnswer) {
                
		this.questionText = question;
		this.answer1Text = answer1;
		this.answer2Text = answer2;
//		this.answer3Text = answer3;
//		this.answer4Text = answer4;
		this.correctAnswer = correctAnswer;
	} // constructor
    /**
     * 
     * @return
     */
	public int getCorrectAnswer() {
		return correctAnswer;
	} // getter
    /**
     * 
     * @param correctAnswer
     */
	public void setCorrectAnswer(int correctAnswer) {
		this.correctAnswer = correctAnswer;
	} // setter
}  // QuestionModel class

