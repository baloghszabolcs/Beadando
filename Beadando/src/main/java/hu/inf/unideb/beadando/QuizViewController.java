package hu.inf.unideb.beadando;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import hu.inf.unideb.beadando.model.TestModel;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;

public class QuizViewController implements Initializable {

    @FXML
    Button exitBtn;
    @FXML
    Button button;
    @FXML
    Label TimeLbl;
    @FXML
    private RadioButton rb1;

    @FXML
    private ProgressBar Pb;

    @FXML
    private RadioButton rb2;

    @FXML
    private Label questionLabel;
    @FXML
    private ToggleGroup rbGroup;

    private TestModel theTest = new TestModel("src/main/java/hu/inf/unideb/beadando/model/quiz.xml");

    private int questionIterator = 0;
    private int randomIterator = 0;

    private int correctIndex = theTest.getQuestions().get(questionIterator).getCorrectAnswer();

    private final int randomNumbersSize = 10;//theTest.getNumberOfQuestions();

    public int getRandomNumbersSize() {
        return randomNumbersSize;
    }

    private ArrayList<Integer> randomSzamok = new ArrayList<>(randomNumbersSize);

    private void randomArrayGenerator() {
        Random randomgen = new Random();
        while (randomSzamok.size() < randomNumbersSize) {
            int rand = randomgen.nextInt(41);
            if (!randomSzamok.contains(rand)) {
                randomSzamok.add(rand);
            }
        }
    }
    private int TIME_SEC = 90; //in seconds

    private void ido() {

        Task<Void> task = new Task<Void>() {

            @Override
            protected Void call() throws Exception {
                SimpleDateFormat simpDate;

                simpDate = new SimpleDateFormat("mm:ss");
                for (; TIME_SEC >= -1; TIME_SEC--) {

                    Platform.runLater(new Runnable() {

                        @Override
                        public void run() {
                            TimeLbl.setText("" + simpDate.format(TIME_SEC * 1000));

                            if (TIME_SEC == -1) {
                                exit();
                                cancel(true);
                            }

                        }

                    });

                    Thread.sleep(1000);

                }
                return null;
            }

        };

        Thread t1 = new Thread(task);
        t1.setDaemon(true);
        t1.start();

    }

    private final double progress = 1.000000 / randomNumbersSize;//theTest.getNumberOfQuestions();
    private double progressDinamic = progress;
    private boolean ok = false;

    private static int score;

    public static void setScore(int score) {
        QuizViewController.score = score;
    }

    public int getScore() {

        return QuizViewController.score;//this.score
    }

    public void scorePlus() {
        QuizViewController.score++;
    }

    @FXML
    private void next(ActionEvent event) {
        ok = false;
        if (rb1.isSelected() && (correctIndex == 1)) {
//            System.out.println("helyesValasz");   debug
            if (randomIterator >= getScore()) {
                scorePlus();
            }
        } else if (rb2.isSelected() && (correctIndex == 2)) {
//            System.out.println("helyesValasz");   debug
            if (randomIterator >= getScore()) {
                scorePlus();
            }
        } else {
            ok = true;

        }

        if (ok == true) {
            randomIterator = 0;
            questionIterator = 0;
            questionIterator = randomSzamok.get(randomIterator);
            progressDinamic = progress * randomIterator;
            Pb.setProgress(progressDinamic);
            Question();
            Answer();

        }
        if (randomIterator + 1 == randomNumbersSize) {
            exit();

        }

        if (randomIterator + 1 != randomNumbersSize) {

            randomIterator++;
            progressDinamic = progress * randomIterator;
            Pb.setProgress(progressDinamic);
            questionIterator = randomSzamok.get(randomIterator);
            Question();
            Answer();
            if (ok == true) {
                randomIterator = 0;
                questionIterator = 0;
                questionIterator = randomSzamok.get(randomIterator);
                progressDinamic = progress * randomIterator;
                Pb.setProgress(progressDinamic);
                Question();
                Answer();

            }

            if (rb1.isSelected()) {
                rb1.setSelected(false);
            } else if (rb2.isSelected()) {
                rb2.setSelected(false);
            }

        }
    }

    @FXML
    private void exitGame(ActionEvent event) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/startScene.fxml"));

            Stage stage = (Stage) button.getScene().getWindow();
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/startStyle.css");
            stage.setResizable(false);
            stage.setTitle("Start");
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(QuizStartController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void exit() {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/endScene.fxml"));

            Stage stage = (Stage) button.getScene().getWindow();
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/endStyle.css");
            stage.setResizable(false);
            stage.setTitle("End");
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(QuizStartController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void Question() {

        questionLabel.setText(theTest.getQuestions().get(questionIterator).getQuestionText());
    }

    private void Answer() {
        rb1.setText(theTest.getQuestions().get(questionIterator).getAnswer1Text());
        rb2.setText(theTest.getQuestions().get(questionIterator).getAnswer2Text());
        correctIndex = theTest.getQuestions().get(questionIterator).getCorrectAnswer();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ido();
        randomArrayGenerator();
        questionIterator = randomSzamok.get(randomIterator);

        rbGroup = new ToggleGroup();
        Question();
        Answer();
        Pb.setProgress(0);
        rb1.setToggleGroup(rbGroup);
        rb2.setToggleGroup(rbGroup);
        setScore(0);

    }

}

