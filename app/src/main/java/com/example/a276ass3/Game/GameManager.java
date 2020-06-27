package com.example.a276ass3.Game;

import java.util.Random;

public class GameManager {
//    public static final int GAME_START = 0;
//    public static final int GAME_STOP = 1;
//    public static final int GAME_READY = 2;

    private static GameManager gameManager;

    private int stepUsed;
    private int rows;
    private int cols;
    private int totalBomb;
    private int restOfTotalBomb;
    public GameBoard[][] gameBoards;
    public int[][] restOfLineBomb;

    public static GameManager getInstance() {
        if (gameManager == null) {
            gameManager = new GameManager();
        }
        return gameManager;
    }

    private GameManager() {
        rows = 4;
        cols = 6;
        totalBomb = 6;
        restOfTotalBomb = totalBomb;
        stepUsed = 0;
        setBomb(rows, cols, totalBomb);
    }

    public void resetGame() {
        restOfTotalBomb = totalBomb;
        stepUsed = 0;
        setBomb(rows, cols, totalBomb);
    }

    private void setBomb(int rows, int cols, int totalBomb) {
        gameBoards = new GameBoard[rows][cols];
        restOfLineBomb = new int[rows][cols];
        restOfTotalBomb = totalBomb;
        Random random = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                gameBoards[i][j] = new GameBoard(cols, rows);
            }
        }
        while(restOfTotalBomb != 0) {
            int row = random.nextInt(rows);
            int col = random.nextInt(cols);
            gameBoards[row][col].setIsBomb(true);
            restOfTotalBomb--;
        }
        calRestOfLineBomb();
    }

    public void calRestOfLineBomb() {
        int[] rowRest = new int[rows];
        int[] colRest = new int[cols];

        for (int i = 0; i < rows; i++) {
            rowRest[i] = 0;
            for (int j = 0; j < cols; j++) {
                colRest[j] = 0;
                restOfLineBomb[i][j] = 0;
            }
        }
        restOfTotalBomb = 0;
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++) {
                if (gameBoards[i][j].isBomb() && gameBoards[i][j].getStepUsed() == 0) {
                    rowRest[i]++;
                    colRest[j]++;
                    restOfTotalBomb++;
                    restOfLineBomb[i][j]--;
                }
            }

        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++) {
                restOfLineBomb[i][j] = restOfLineBomb[i][j] + rowRest[i] + colRest[j];
            }
    }

    public int getRestOfTotalBomb() {
        return restOfTotalBomb;
    }

    public void setRestOfTotalBomb(int restOfTotalBomb) {
        this.restOfTotalBomb = restOfTotalBomb;
    }

    public int getTotalBomb() {
        return totalBomb;
    }

    public void setTotalBomb(int totalBomb) {
        this.totalBomb = totalBomb;
    }

    public int getRow() {
        return rows;
    }

    public void setRow(int rows) {
        this.rows = rows;
    }

    public int getCol() {
        return cols;
    }

    public void setCol(int cols) {
        this.cols = cols;
    }

    public int getStepUsed() {
        return stepUsed;
    }

    public void addStepUsed() {
        stepUsed ++;
    }

    public void setStepUsed(int stepUsed) {
        this.stepUsed = stepUsed;
    }




}
