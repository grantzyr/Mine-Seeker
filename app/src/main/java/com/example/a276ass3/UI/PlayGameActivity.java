package com.example.a276ass3.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a276ass3.Game.GameBoard;
import com.example.a276ass3.Game.GameManager;
import com.example.a276ass3.R;

import static com.example.a276ass3.R.drawable.bomb;

public class PlayGameActivity extends AppCompatActivity {

    private static GameManager gameManager = GameManager.getInstance();

    private static int NUM_ROWS = gameManager.getRow();
    private static int NUM_COLS = gameManager.getCol();
    private Button[][] buttons;
    public static Intent makeLaunchIntent (Context context) {
        Intent intent = new Intent (context, PlayGameActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);
        gameManager.resetGame();
        NUM_ROWS = gameManager.getRow();
        NUM_COLS  =gameManager.getCol();
        setPlayGameTableView();

    }

    private void setPlayGameTableView() {
        TableLayout table = (TableLayout) findViewById(R.id.PlayGame_Table);
        buttons = new Button[NUM_ROWS][NUM_COLS];
        for (int rows = 0; rows < NUM_ROWS; rows++) {
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f
            ));
            table.addView(tableRow);

            for (int cols = 0; cols < NUM_COLS; cols++) {
                Button button = new Button(this);
                buttons[rows][cols] = button;
                button.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f
                ));
                button.setPadding(0, 0, 0, 0);

                final int COL = cols;
                final int ROW = rows;
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Toast.makeText(PlayGameActivity.this, "row" + ROW + ",col: " + COL, Toast.LENGTH_SHORT).show();

                        gameManager.addStepUsed();
                        GameBoard gameBoard = gameManager.gameBoards[ROW][COL];
                        gameBoard.setStepUsed(gameBoard.getStepUsed() + 1);

                        // lock Button Sizes
                        for (int row = 0; row < NUM_ROWS; row ++) {
                            for (int col = 0; col < NUM_COLS; col ++) {
                                Button button = buttons[row][col];

                                int width = button.getWidth();
                                button.setMinWidth(width);
                                button.setMaxWidth(width);

                                int height = button.getHeight();
                                button.setMinHeight(height);
                                button.setMaxHeight(height);
                            }
                        }

                        gameManager.calRestOfLineBomb();
                        for (int i = 0; i < NUM_ROWS; i++)
                            for (int j = 0; j < NUM_COLS; j++) {
                                Button button = buttons[i][j];
                                GameBoard tmpGameBoard = gameManager.gameBoards[i][j];
                                if (tmpGameBoard.isBomb() && tmpGameBoard.getStepUsed() == 1) {

                                    button.setBackground(getResources().getDrawable(bomb));

                                    int newWidth = button.getWidth();
                                    int newHeight = button.getHeight();
                                    Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), bomb);
                                    Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);
                                    Resources resources = getResources();
                                    button.setBackground(new BitmapDrawable(resources, scaledBitmap));
                                }
                                else if (tmpGameBoard.isBomb() && tmpGameBoard.getStepUsed() == 2) {
                                    button.setText("" + gameManager.restOfLineBomb[i][j]);
                                }
                                else if (!tmpGameBoard.isBomb() && tmpGameBoard.getStepUsed() > 0) {
                                    button.setText("" + gameManager.restOfLineBomb[i][j]);
                                }
                            }


                            TextView bombCount = (TextView) findViewById(R.id.PlayGame_TextView1);
                            TextView steps = (TextView) findViewById(R.id.PlayGame_TextView2);
                            bombCount.setText("Bombs Left: " + gameManager.getRestOfTotalBomb());
                            steps.setText("Step Used: " + gameManager.getStepUsed());


                            if (gameManager.getRestOfTotalBomb() == 0) {
                            FragmentManager manager = getSupportFragmentManager();
                            GameOver dialog = new GameOver();
                            dialog.show(manager, "Game Over");
                            }
                    }
                });

                tableRow.addView(button);
            }

        }
    }



}