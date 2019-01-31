package com.example.arnavanand.liontigergame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    enum player
    {
        ONE, TWO ,No
    }

    player currentplayer = player.ONE;


    player[] playerChoices = new player[9];

    int [][] winnerRowsColumns = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

    private boolean gameOver = false;
    private Button btnReset;
    private GridLayout gridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /* playerChoices[0] = player.No;
        playerChoices[1] = player.No;
        playerChoices[2] = player.No;
        playerChoices[3] = player.No;
        playerChoices[4] = player.No;
        playerChoices[5] = player.No;
        playerChoices[6] = player.No;
        playerChoices[7] = player.No;
        playerChoices[8] = player.No;
        */
        for(int index = 0; index < playerChoices.length; index++ )
        {
            playerChoices[index] = player.No;
        }

        btnReset = findViewById(R.id.btnReset);
        gridLayout = findViewById(R.id.gridLayout);

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTheGame();

            }
        });
    }
    public void ImageViewIsTapped(View imageView)
    {
      ImageView tappedImageView = (ImageView) imageView;
      int titag = Integer.parseInt(tappedImageView.getTag().toString());

      if(playerChoices[titag] == player.No && !gameOver)
      {
          tappedImageView.setTranslationX(-2000);

          playerChoices[titag] = currentplayer;

          if (currentplayer == player.ONE) {
              tappedImageView.setImageResource(R.drawable.lion);
              currentplayer = player.TWO;
          } else if (currentplayer == player.TWO) {
              tappedImageView.setImageResource(R.drawable.tiger);
              currentplayer = player.ONE;
          }

          tappedImageView.animate().translationXBy(2000).alpha(1).rotation(3600).setDuration(1000);

          Toast.makeText(this, tappedImageView.getTag().toString(), Toast.LENGTH_SHORT).show();

          for (int[] winnerColumns : winnerRowsColumns) {
              if (playerChoices[winnerColumns[0]] == playerChoices[winnerColumns[1]]
                      && playerChoices[winnerColumns[1]] == playerChoices[winnerColumns[2]]
                      && playerChoices[winnerColumns[0]] != player.No)


              {
                  btnReset.setVisibility(View.VISIBLE);
                  gameOver = true;
                  if (currentplayer == player.ONE) {
                      Toast.makeText(this, "Player 2 is the winner", Toast.LENGTH_LONG).show();
                  } else if (currentplayer == player.TWO) {
                      Toast.makeText(this, "Player 1 is the winner", Toast.LENGTH_LONG).show();
                  }
              }

          }
      }

    }

    //Reset game func
    private void resetTheGame()
    {
        for (int index = 0; index < gridLayout.getChildCount(); index++) {
            ImageView imageView = (ImageView) gridLayout.getChildAt(index);
            imageView.setImageDrawable(null);
            imageView.setAlpha(0.2f);
        }
        currentplayer = player.ONE;
        /*
        playerChoices[0] = player.No;
        playerChoices[1] = player.No;
        playerChoices[2] = player.No;
        playerChoices[3] = player.No;
        playerChoices[4] = player.No;
        playerChoices[5] = player.No;
        playerChoices[6] = player.No;
        playerChoices[7] = player.No;
        playerChoices[8] = player.No;
        */
        for(int index = 0; index < playerChoices.length; index++ )
        {
            playerChoices[index] = player.No;
        }

        gameOver = false;

        btnReset.setVisibility(View.INVISIBLE);
    }
}
