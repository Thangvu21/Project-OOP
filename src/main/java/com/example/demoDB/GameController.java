package com.example.demoDB;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.*;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class GameController extends GeneralController implements Initializable {

    @FXML
    private ImageView im1;

    @FXML
    private ImageView im2;

    @FXML
    private ImageView im3;

    @FXML
    private ImageView im4;

    @FXML
    private ImageView im5;

    @FXML
    private ImageView im6;

    @FXML
    private ImageView im0;

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

    public StringBuffer listInCorrcetChar = new StringBuffer();

    public void hideImage() {
        im1.setVisible(false);
        im2.setVisible(false);
        im3.setVisible(false);
        im4.setVisible(false);
        im5.setVisible(false);
        im6.setVisible(false);
        im0.setVisible(false);
    }

    public void setShow() {
        hideImage();
        switch (inCorrcetGuess) {
            case 0: im0.setVisible(true); break;
            case 1: im1.setVisible(true); break;
            case 2: im2.setVisible(true); break;
            case 3: im3.setVisible(true); break;
            case 4: im4.setVisible(true); break;
            case 5: im5.setVisible(true); break;
            case 6: im6.setVisible(true); break;
            case 7: im0.setVisible(true); break;
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //show
        setShow();
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
            ++inCorrcetGuess;
            setShow();
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
        setShow();
        listInCorrcetChar.setLength(0);
        // init secretWord
        generateHiddenCharacters();
        // show mảng chữ sai
        inCorrectChar.setText("");
        // show kết quả
        endGame.setText("");
    }

}
