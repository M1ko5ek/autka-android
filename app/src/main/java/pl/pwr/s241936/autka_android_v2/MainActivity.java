package pl.pwr.s241936.autka_android_v2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b1 = (Button) findViewById(R.id.button);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSecond_activity();
            }
        });

        Button b2 = (Button) findViewById(R.id.button2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openThird_activity();
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


