package pl.pwr.s241936.autka_android_v2;

import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.TextView;
import static pl.pwr.s241936.autka_android_v2.Game_sensor.SHARED_PREFS;


public class MainActivity extends AppCompatActivity {

    private int best_score;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView) findViewById(R.id.textView2);

        Button b = (Button) findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setText("");
                openSecond_activity();
            }
        });

        Button b2 = (Button) findViewById(R.id.button2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setText("");
                openThird_activity();
            }
        });

        Button b3 = (Button) findViewById(R.id.button3);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
                best_score = sharedPreferences.getInt("POINTS",0);
                text.setText("  Your Best Score is " + String.valueOf(best_score) + " points");
            }
        });

    }

    public void openSecond_activity(){
        Intent intent = new Intent(this,MainActivity2.class);
        startActivity(intent);
    }
    public void openThird_activity(){
        Intent intent = new Intent(this,MainActivity3.class);
        startActivity(intent);
    }
}


