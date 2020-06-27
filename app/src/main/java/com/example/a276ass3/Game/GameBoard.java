package com.example.a276ass3.Game;

public class GameBoard {

    private int rowNum;
    private int colNum;
    private boolean isBomb;
    private int stepUsed;

    public GameBoard(int rowNum, int colNum) {
        this.rowNum = rowNum;
        this.colNum = colNum;
        this.isBomb = false;
        stepUsed = 0;
    }

    public GameBoard(int rowNum, int colNum, boolean isBomb, int stepUsed) {
        this.rowNum = rowNum;
        this.colNum = colNum;
        this.isBomb = isBomb;
        this.stepUsed = stepUsed;
    }

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public int getColNum() {
        return colNum;
    }

    public void setColNum(int colNum) {
        this.colNum = colNum;
    }

    public boolean isBomb() {
        return isBomb;
    }

    public void setIsBomb(boolean tf) {
        isBomb = tf;
    }

    public int getStepUsed() {
        return stepUsed;
    }

    public void setStepUsed(int tmpStepUsed) {
        if (tmpStepUsed > 2) {
            tmpStepUsed = 2;
        }
        this.stepUsed = tmpStepUsed;
    }
}
