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
import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/*
 *  Open an XML file and parse the content
 *  showing the results on screen
 */
public class ParserXml implements Quiz {


	private String xmlFile;


	private File file;

	private DocumentBuilderFactory dbf;

	private DocumentBuilder db;
	private Document doc;
	private NodeList nodeLst; // questions list
	private NodeList answerElementList; // Answer list
	private NodeList theAnswer; // Answer text
	private NodeList answerAttrValue; // The answer an be true or false
	private Node fstNode; // Question node
	private Element questionNode; // Question node
	private Element answerElementOne; // <answer1>

	private Attr answerAttr; // Answer attribute


	public ParserXml(String fileName) {
		// Open XML file
		xmlFile = fileName;
		file = new File(xmlFile);

		try {
			dbf = DocumentBuilderFactory.newInstance();
			db = dbf.newDocumentBuilder();
			doc = db.parse(file);
			doc.getDocumentElement().normalize();
		} catch (Exception e) {
			System.out.println("Exception found in ParserXml constructor: "
					+ e.getMessage());
		}
	}

	public int getNumberOfQuestions() {
		int numberOfQuestions = 0;

                /**
                 * Access to question list
                 */
		nodeLst = doc.getElementsByTagName("question");

	
		numberOfQuestions = nodeLst.getLength();

		if (numberOfQuestions < 0) {
			numberOfQuestions = 0;
		}

		return (numberOfQuestions);
	}

	public String[] getQuestionIDstrings() {

		 
		int numberOfQuestions = this.getNumberOfQuestions();

		int i = 0;

		/**array for storing questions
                */
		String idStrings[] = new String[numberOfQuestions];

		for (i = 0; i < numberOfQuestions; i++) {

			/**Read <question> node with index "i"
                         */ 
			fstNode = nodeLst.item(i);
			NamedNodeMap aNamedNodeMap = fstNode.getAttributes();
      
			Node anItem = aNamedNodeMap.item(0);
			
			NodeList aNodeList = anItem.getChildNodes();
			Node anItem2 = aNodeList.item(0);
			idStrings[i] = anItem2.getNodeValue();
		}

		return (idStrings);
	} 


	public String getQuestionText(int questionNumber) {
		String questionText = this
				.getQuestionText(this.getQuestionIDstrings()[questionNumber - 1]);

		return (questionText);
	} 


	public String getQuestionText(String questionIDstring) {
		String questionText = "";
		String questionIdString_tmp = "";

		boolean found = false;

		int i = 0;

		for (; i < this.getNumberOfQuestions() && found == false; i++) {
			// Read <question> node with index "i"
			fstNode = nodeLst.item(i);

			// Access to the ID string
			questionIdString_tmp = fstNode.getAttributes().item(0)
					.getChildNodes().item(0).getNodeValue();

			// Is this the ID string you are looking for?
			if (questionIdString_tmp.equals(questionIDstring)) {
				found = true; // Should stop the loop

				// Access to the question text
				questionText = fstNode.getChildNodes().item(0).getNodeValue();
			} // if
		} // for

		return (questionText);
	} // getter questionText

	/**
     * 
     */
	public String getQuestionAnswerText(int answerNumber, int questionNumber) {
		String questionAnswerText = "";
		// Access to question list
		nodeLst = doc.getElementsByTagName("question");

		// Read <question> node with index "i"
		fstNode = nodeLst.item(questionNumber - 1);

		// Read the <answer> tag
		questionNode = (Element) fstNode;
		answerElementList = questionNode.getElementsByTagName("answer");
		answerElementOne = (Element) answerElementList.item(answerNumber - 1);

		// Read <answer> tag childrens
		theAnswer = answerElementOne.getChildNodes();

		// Print the first child value (the answer)
		questionAnswerText = theAnswer.item(0).getNodeValue();

		return (questionAnswerText);
	} // getter QuestionAnswerText

	/**
     * 
     */
	public boolean isQuestionAnswerCorrect(int answerNumber, int questionNumber) {

		boolean isCorrect = false; // By default initialize value to 'false'

		String answerState = "";
		// Access to question list

		nodeLst = doc.getElementsByTagName("question");

		// Read <question> node with index "i"

		fstNode = nodeLst.item(questionNumber - 1);

		// Read the <answer> tag

		questionNode = (Element) fstNode;
		answerElementList = questionNode.getElementsByTagName("answer");
		answerElementOne = (Element) answerElementList.item(answerNumber - 1);
		answerAttr = (Attr) answerElementOne.getAttributes().item(0);
		
		if (answerAttr != null) {
			answerAttrValue = answerAttr.getChildNodes();
			answerState = answerAttrValue.item(0).getNodeValue();

//			
			// Is it the correct answer?
			if (answerState.trim().compareTo("true") == 0) {
				isCorrect = true;
			} else {
				isCorrect = false;
			}
		} else { // if else
			System.out.println("Unknown state: true or false?");
		} // else

		return (isCorrect);
	} // is QuestionAnswerCorrect method

	public String getQuestionAnswerText(int answerNumber,
			String questionIDstring) {
		String questionAnswerText = "";

		boolean questionFound = false; // Used to indicate that question was
										// found

		String questionIDString = "";
		int i = 0;

		// Given the ID string, look for the question
		for (; i < this.getNumberOfQuestions() && questionFound == false; i++) {
			// Read <question> node with index "i"
			fstNode = nodeLst.item(i);
			// Get the attribute ID
			questionIDString = fstNode.getAttributes().item(0).getChildNodes()
					.item(0).getNodeValue();

			// Was the question ID string found?
			if (questionIDString.equals(questionIDstring)) {
				questionFound = true;
			}
		} // for

		// Given the question now access the answer with 'answerNumber' value
		questionNode = (Element) fstNode;
		answerElementList = questionNode.getElementsByTagName("answer");
		answerElementOne = (Element) answerElementList.item(answerNumber - 1);

		// Access to the answer text
		questionAnswerText = answerElementOne.getChildNodes().item(0)
				.getNodeValue();

		return (questionAnswerText);
	} // getter


	/*
	 * Show the possible answers to the question
	 */
	private void showAnswers() {
		String answerValue = "";

		for (int answerIndex = 0; answerIndex < answerElementList.getLength(); answerIndex++) {
			answerElementOne = (Element) answerElementList.item(answerIndex);

			/**
                         * Read <answer> tag childrens
                         */
			theAnswer = answerElementOne.getChildNodes();

			
                        /**
                         * Print the first child value (the answer)
                         */
			answerValue = theAnswer.item(0).getNodeValue();
			System.out.println("Answer: " + answerValue);

			showAnswerValue(answerElementOne);
		} 

	} 

	private void showAnswerValue(Element answerElementOne) {
		String answerState = "";

		answerAttr = (Attr) answerElementOne.getAttributes().item(0);

		if (answerAttr != null) {
			answerAttrValue = answerAttr.getChildNodes();
			answerState = answerAttrValue.item(0).getNodeValue();

		}
	}
}
