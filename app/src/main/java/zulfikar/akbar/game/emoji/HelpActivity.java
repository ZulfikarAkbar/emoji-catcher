package zulfikar.akbar.game.emoji;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class HelpActivity extends AppCompatActivity {
    private ImageButton back;
    private TextView help_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_help);
        help_text = findViewById(R.id.text_help);
        help_text.setMovementMethod(new ScrollingMovementMethod());
        back = findViewById(R.id.back_help);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =  new Intent(HelpActivity.this,MainActivity.class);
                startActivity(i);
            }
        });
    }
}
