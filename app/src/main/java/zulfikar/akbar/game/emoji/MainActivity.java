package zulfikar.akbar.game.emoji;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends Activity {
    private ImageButton start_game, high_score, help, sound_on, sound_off;
    static MediaPlayer gameSound;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);


        start_game = findViewById(R.id.start_button);
        high_score = findViewById(R.id.highscore_button);
        help = findViewById(R.id.help);
        sound_on = findViewById(R.id.sound);
        sound_off = findViewById(R.id.sound_off);

        gameSound = MediaPlayer.create(MainActivity.this,R.raw.reharmonization);
        gameSound.start();

        sound_on.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                gameSound = MediaPlayer.create(MainActivity.this,R.raw.reharmonization);
                gameSound.start();
            }
        });
        sound_off.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                gameSound.stop();
            }
        });

        start_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =  new Intent(MainActivity.this, StartActivity.class);
                startActivity(i);
            }
        });

        high_score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =  new Intent(MainActivity.this, ScoreActivity.class);
                startActivity(i);
            }
        });
//
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =  new Intent(MainActivity.this, HelpActivity.class);
                startActivity(i);
            }
        });
    }
}
