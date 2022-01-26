package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

import java.util.LinkedList;

public class MaxToTenController {

    @FXML
    private GridPane grid;

    @FXML
    private Button[][] btns;

    final int SIZE = 4;
    private int press = 0;

    @FXML
    public void initialize() {
        btns = new Button[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                btns[i][j] = new Button("0");
                btns[i][j].setPrefSize(grid.getPrefWidth() / SIZE, grid.getPrefHeight() / SIZE);
                grid.add(btns[i][j], i, j);

                btns[i][j].setOnAction(event -> {
                        for (int ii = 0; ii < SIZE; ii++) {
                            for (int jj = 0; jj < SIZE; jj++) {
                                if (btns[ii][jj] == event.getSource()) {
                                    if (Integer.parseInt(btns[ii][jj].getText()) < 10) {
                                        press++;
                                        int n1 = 0,n2 = 0,n3 = 0,n4 = 0;
                                        if ((ii-1) >= 0) {
                                             n1 = (Integer.parseInt(btns[ii - 1][jj].getText()) == 10) ? 1 : 0;
                                        }
                                        if ((jj-1) >= 0) {
                                             n2 = (Integer.parseInt(btns[ii][jj - 1].getText()) == 10) ? 1 : 0;
                                        }
                                        if ((jj+1) < SIZE) {
                                            n3 = (Integer.parseInt(btns[ii][jj + 1].getText()) == 10) ? 1 : 0;
                                        }
                                        if ((ii+1) < SIZE) {
                                            n4 = (Integer.parseInt(btns[ii+1][jj].getText()) == 10) ? 1 : 0;
                                        }

                                        int total = n1 + n2 + n3 + n4;

                                        if (total >= 2) {
                                            btns[ii][jj].setText("10");
                                        } else {
                                            if (Integer.parseInt(btns[ii][jj].getText())<10){
                                                btns[ii][jj].setText((Integer.parseInt((btns[ii][jj].getText()) )+ 1) + "");
                                            }
                                        }
                                        checkIfDone();
                                    }
                                    break;
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
            alert( "Game over!", "There was: " + press + " pressing");
        }
    }
}