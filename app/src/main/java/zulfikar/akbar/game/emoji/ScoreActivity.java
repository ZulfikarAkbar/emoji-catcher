package zulfikar.akbar.game.emoji;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {
    private ImageButton back;
    TextView textView1,textView2,textView3,textView4,textView5;

    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_score);
        back = findViewById(R.id.back_score);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =  new Intent(ScoreActivity.this,MainActivity.class);
                startActivity(i);
            }
        });

        //initializing the textViews
        textView1 = findViewById(R.id.list_score_1);
        textView2 = findViewById(R.id.list_score_2);
        textView3 = findViewById(R.id.list_score_3);
        textView4 = findViewById(R.id.list_score_4);
        //textView5 = findViewById(R.id.list_score_5);

        sharedPreferences  = getSharedPreferences("SHAR_PREF_NAME", Context.MODE_PRIVATE);

        //setting the values to the textViews
        textView1.setText("1."+sharedPreferences.getInt("score1",0));
        textView2.setText("2."+sharedPreferences.getInt("score2",0));
        textView3.setText("3."+sharedPreferences.getInt("score3",0));
        textView4.setText("4."+sharedPreferences.getInt("score4",0));
        //textView5.setText("5."+sharedPreferences.getInt("score5",0));

    }
}
