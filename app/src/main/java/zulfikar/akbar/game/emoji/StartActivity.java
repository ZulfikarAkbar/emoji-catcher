package zulfikar.akbar.game.emoji;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class StartActivity extends AppCompatActivity {
    private ImageButton back, level_1, level_2, level_3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_start);

        back = findViewById(R.id.back_start);

        level_1 = findViewById(R.id.Level_1_button);
        level_2 = findViewById(R.id.Level_2_button);
        level_3 = findViewById(R.id.Level_3_button);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i =  new Intent(StartActivity.this,MainActivity.class);
                startActivity(i);
            }
        });
        level_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =  new Intent(StartActivity.this,GameActivity.class);
                startActivity(i);
            }
        });
        level_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =  new Intent(StartActivity.this,GameActivity2.class);
                startActivity(i);
            }
        });
        level_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =  new Intent(StartActivity.this,GameActivity3.class);
                startActivity(i);
            }
        });

//        level_3.setOnClickListener(this);
//        level_2.setOnClickListener(this);
//        level_1.setOnClickListener(this);
    }
//    @Override
//    public void onClick(View v) {
//        //starting game activity
//        startActivity(new Intent(this, GameActivity.class));
//    }
}
