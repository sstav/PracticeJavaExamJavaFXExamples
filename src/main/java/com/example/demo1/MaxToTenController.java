package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

import java.util.LinkedList;

public class MaxToTenController {

    @FXML
    private GridPane grid;

    @FXML
    private Button[][] btns;

    @FXML
    private Button reset;

    final int SIZE = 4;

    private GraphicsContext gc;
    private long startTime;
    private long endTime;
    private LinkedList<Rectangle> rects;
    private int press = 0;

    @FXML
    public void initialize() {
        btns = new Button[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                btns[i][j] = new Button("0");
                btns[i][j].setPrefSize(grid.getPrefWidth() / SIZE, grid.getPrefHeight() / SIZE);
                grid.add(btns[i][j], i, j);

                btns[i][j].setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                        for (int i = 0; i < SIZE; i++) {
                            for (int j = 0; j < SIZE; j++) {
                                if (btns[i][j] == event.getSource()) {
                                    if (Integer.parseInt(btns[i][j].getText()) < 10) {
                                        press++;
                                        int n1 = (Integer.parseInt(btns[i - 1][j].getText()) == 10)?1:0;
                                        int n2 = (Integer.parseInt(btns[i - 1][j].getText()) == 10)?1:0;
                                        int n3 = (Integer.parseInt(btns[i - 1][j].getText()) == 10)?1:0;
                                        int n4 = (Integer.parseInt(btns[i - 1][j].getText()) == 10)?1:0;
                                        int total = n1 + n2 + n3 + n4;

                                        if (total >= 2) {
                                            btns[i][j].setText("10");
                                        } else {
                                            btns[i][j].setText(Integer.parseInt(btns[i][j].getText()) + 1 + "");
                                        }
                                        checkIfDone();
                                    }
                                }
                            }
                        }
                    }
                });
            }
        }
    }

    @FXML
    void reset(ActionEvent event) {
        press = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                btns[i][j].setText("0");
            }
        }
    }

    private void alert(String title, String msg){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    private void checkIfDone() {
        int isTen = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (btns[i][j].getText().equals("10")) {
                    isTen++;
                }
            }
        }
        if (isTen == SIZE * SIZE) {
            alert( "Game over!", "There was:" + press + "pressing");
        }
    }
}