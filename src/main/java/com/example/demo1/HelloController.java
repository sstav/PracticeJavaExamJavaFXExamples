package com.example.demo1;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

import java.util.Random;

public class HelloController {

    private int changeTimes;
    @FXML
    private Label num1;
    @FXML
    private Label num2;
    @FXML
    private Label num3;

    @FXML
    public void initialize() {
        this.changeTimes = 2;
        this.setCards();
    }

    private void setCards(){
        this.num1.setText(Integer.toString(this.rand()));
        this.num2.setText(Integer.toString(this.rand()));
        this.num3.setText(Integer.toString(this.rand()));
    }

    private int rand(){
        return new Random().nextInt(100);
    }

    @FXML
    private void stop(){
        int sum = Integer.parseInt(this.num1.getText()) + Integer.parseInt(this.num2.getText()) + Integer.parseInt(this.num3.getText());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game End");
        alert.setHeaderText(null);
        alert.setContentText("Your Number Is: " + sum);

        alert.showAndWait();
        this.initialize();
    }

    @FXML
    protected void change() {
        if (this.changeTimes > 0){
            this.setCards();
            this.changeTimes--;
        }

        if (this.changeTimes == 0){
            this.stop();
        }

    }
}