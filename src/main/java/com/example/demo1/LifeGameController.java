package com.example.demo1;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.util.Random;

public class LifeGameController {
    final int SIZE = 10;
    private int lifeGen = 0;
    private boolean[][] matrix = new boolean[SIZE][SIZE];
    private boolean[][] next_gen_matrix = new boolean[SIZE][SIZE];
    /**
     * @param modulu - Set Modulu for the generage random number;
     * @return - Int random number
     */
    private int rand(int modulu){
        Random rand = new Random();
        int num = rand.nextInt() % modulu;
        /** This Loop is for improve the coincidence **/
        for (int i = 0; i<100; i++){
            num = rand.nextInt() % modulu;
        }

        if (num < 0) num = -num;

        return num;
    }

    @FXML
    private Button button_gen;
    @FXML
    private Canvas board;

    @FXML
    protected void nextGen() {
        GraphicsContext gc = this.board.getGraphicsContext2D();
        this.next_gen(gc);
    }

    private void gen_rand_matrix(){
        for (int i = 0 ; i < SIZE ; i++){
            for (int j = 0 ; j < SIZE ; j++){
                this.matrix[i][j] = (1 == this.rand(2));
            }
        }
    }

    private void gc_clear(GraphicsContext gc){
        gc.clearRect(0, 0, board.getWidth(), board.getHeight());
    }

    private void draw_matrix(GraphicsContext gc){
        double x = 0;
        double y = 0;
        double screen_x = board.getWidth();
        double screen_y = board.getHeight();
        double x_step = (screen_x/SIZE);
        double y_step = (screen_y/SIZE);


        for (int i = 0 ; i < SIZE ; i++){
            for (int j = 0 ; j < SIZE ; j++){
                if (this.matrix[i][j]){
                    gc.fillRect(x,y,x_step,y_step);
                }else{
                    gc.strokeRect(x,y,x_step,y_step);
                }

                x = x + x_step;
            }
            x = 0;
            y = y + y_step;
        }
    }


    /** Check if Is Valid Cell
     * @param row Index Row
     * @param col Index Column
     */
    private boolean valid_cell(int row , int col){
        if (row  < 0) return false;
        if (row >= SIZE) return false;
        if (col < 0) return false;
        if (col >= SIZE) return false;
        return true;
    }

    /**
     * @param row Index Row
     * @param col Index Column
     * @return Count Neighbors of cell
     */
    private int find_life_neighbors(int row , int col){
        int x,y,cnt = 0;
        x = row - 1; y = col - 1;
        for (int i = 0 ; i < 3 ; i++){
            if (valid_cell(x,y) && matrix[x][y]) cnt++;
            y++;
        }

        x = row; y = col - 1;
        for (int i = 0 ; i < 3 ; i++){
            if (y == col) {
                y++;
                continue;
            }
            if (valid_cell(x,y) && matrix[x][y]) cnt++;
            y++;
        }

        x = row + 1; y = col - 1;
        for (int i = 0 ; i < 3 ; i++){
            if (valid_cell(x,y) && matrix[x][y]) cnt++;
            y++;
        }

        return cnt;
    }

    private boolean law_Birth(int row , int col){
        return (this.find_life_neighbors(row, col) == 3);
    }

    private boolean law_Death(int row , int col){
        boolean dead = false;
        if (this.find_life_neighbors(row, col) == 0) dead = true;
        else if (this.find_life_neighbors(row, col) == 1) dead = true;
        else if (this.find_life_neighbors(row, col) > 4) dead = true;
        return dead;
    }


    private void copy_matrix(boolean[][] from,boolean[][] to){
        for (int i = 0 ; i < SIZE ; i++) {
            for (int j = 0; j < SIZE; j++) {
                to[i][j] = from[i][j];
            }
        }
    }

    private void generate_next_life(GraphicsContext gc){
        for (int i = 0 ; i < SIZE ; i++){
            for (int j = 0 ; j < SIZE ; j++){
                if (this.matrix[i][j]){
                    if (law_Death(i,j)){
                        this.next_gen_matrix[i][j] = false;
                    }
                }else{
                    if (law_Birth(i , j)){
                        this.next_gen_matrix[i][j] = true;
                    }
                }
            }
        }
    }

    /** Call Button From FXML **/
    private void next_gen(GraphicsContext gc){
        this.lifeGen++;
        this.gc_clear(gc);
        if (lifeGen == 1){
            this.gen_rand_matrix();
            this.copy_matrix(this.matrix,this.next_gen_matrix);
            this.draw_matrix(gc);
        } else {
            this.generate_next_life(gc);
            this.copy_matrix(this.next_gen_matrix,this.matrix);
            this.draw_matrix(gc);
        }

        button_gen.setText("Gen " + this.lifeGen);


    }


}