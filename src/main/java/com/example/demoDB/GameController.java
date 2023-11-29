package com.example.demoDB;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class GameController extends GeneralController implements Initializable {

    @FXML
    private Text show;
    @FXML
    private Text inCorrectChar;

    @FXML
    private TextField guess;

    @FXML
    private Text endGame;

    @FXML
    private Text textForWord;

    public int inCorrcetGuess = 0;

    public String word;

    public StringBuilder secretWord = new StringBuilder();

    public final int MAX_MISTAKE = 7;

    public String setEndGame;

    public StringBuffer listInCorrcetChar = new StringBuffer();

    public static String[] FIGURES = {
            "   -------------    \n" +
                    "   |                \n" +
                    "   |                \n" +
                    "   |                \n" +
                    "   |                \n" +
                    "   |     \n" +
                    " -----   \n",
            "   -------------    \n" +
                    "   |           |    \n" +
                    "   |                \n" +
                    "   |                \n" +
                    "   |                \n" +
                    "   |     \n" +
                    " -----   \n",
            "   -------------    \n" +
                    "   |           |    \n" +
                    "   |           O    \n" +
                    "   |                \n" +
                    "   |                \n" +
                    "   |     \n" +
                    " -----   \n",
            "   -------------    \n" +
                    "   |           |    \n" +
                    "   |           O    \n" +
                    "   |           |    \n" +
                    "   |                \n" +
                    "   |     \n" +
                    " -----   \n",
            "   -------------    \n" +
                    "   |           |    \n" +
                    "   |           O    \n" +
                    "   |          /|    \n" +
                    "   |                \n" +
                    "   |     \n" +
                    " -----   \n",
            "   -------------    \n" +
                    "   |           |    \n" +
                    "   |           O    \n" +
                    "   |          /|\\  \n" +
                    "   |                \n" +
                    "   |     \n" +
                    " -----   \n",
            "   -------------    \n" +
                    "   |           |    \n" +
                    "   |           O    \n" +
                    "   |          /|\\  \n" +
                    "   |          /     \n" +
                    "   |     \n" +
                    " -----   \n",
            "   -------------    \n" +
                    "   |           |    \n" +
                    "   |           O    \n" +
                    "   |          /|\\  \n" +
                    "   |          / \\  \n" +
                    "   |     \n" +
                    " -----   \n",
    };

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //show
        show.setText(FIGURES[inCorrcetGuess]);
        // select Word
        word = wordList.get(getIntRandom(0,2000)).getWord_target();
        // init secretWord
        generateHiddenCharacters();
        // show mảng chữ sai
        inCorrectChar.setText("");
        // show kết quả
        endGame.setText("");
    }

    public int getIntRandom(int min, int max) {
        return (new Random().nextInt(max - min) + 1);
    }



    @FXML
    void getTextInput(ActionEvent event) {
        playTurn();
        guess.clear();
    }

    public void generateHiddenCharacters() {
        int wordLength = word.length();
        secretWord.append("*".repeat(wordLength));
        textForWord.setText(secretWord.toString());
    }

    public void playTurn() {
        String guess = this.guess.getText().toLowerCase();
        char letterGuess = guess.charAt(0);
        ArrayList<Integer> positions = new ArrayList<>();
        char[] wordChars = word.toCharArray();

        if (word.equals(guess)) {
            endGame.setText("YOU WIN");
            return;
        }

        // đánh dấu
        if (word.contains(guess)) {
            for (int i = 0; i < word.length(); i++) {
                if (letterGuess == wordChars[i]) {
                    positions.add(i);
                }
            }
            // update
            positions.forEach(pos ->{
                secretWord.setCharAt(pos,letterGuess);
            });

            textForWord.setText(String.valueOf(secretWord));
        } else {
            listInCorrcetChar.append(letterGuess + ", ");
            show.setText(FIGURES[++inCorrcetGuess]);
            inCorrectChar.setText(listInCorrcetChar.toString());
            if (inCorrcetGuess == MAX_MISTAKE) {
                endGame.setText("YOU LOSE!! the Word is: " + word);
                return;
            }
        }
    }

    @FXML
    void reset(ActionEvent event) {
        word = wordList.get(getIntRandom(0,2000)).getWord_target();
        secretWord.setLength(0);
        inCorrcetGuess = 0;
        show.setText(FIGURES[0]);

        listInCorrcetChar.setLength(0);
        // init secretWord
        generateHiddenCharacters();
        // show mảng chữ sai
        inCorrectChar.setText("");
        // show kết quả
        endGame.setText("");
    }

}
