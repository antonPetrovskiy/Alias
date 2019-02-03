package game.com.alias;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import game.com.alias.choosers.ImageChooser;
import game.com.alias.choosers.MusicWorker;


public class LevelActivity extends AppCompatActivity {

    ImageView easy;
    ImageView medium;
    ImageView hard;
    String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        s = getIntent().getStringExtra("dictionary");

        init();
        buttons();

    }

    public void init(){
        easy = findViewById(R.id.imageView1);
        medium = findViewById(R.id.imageView2);
        hard = findViewById(R.id.imageView3);
        easy.setImageResource(ImageChooser.getLvl(getResources().getConfiguration().locale.getLanguage(), "easy"));
        medium.setImageResource(ImageChooser.getLvl(getResources().getConfiguration().locale.getLanguage(), "medium"));
        hard.setImageResource(ImageChooser.getLvl(getResources().getConfiguration().locale.getLanguage(), "hard"));
    }

    public void buttons(){
        easy.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        MusicWorker.getInstance(getApplicationContext()).playSound(MusicWorker.click);
                        easy.setImageResource(ImageChooser.getLvl(getResources().getConfiguration().locale.getLanguage(), "easyused"));
                        break;

                    case MotionEvent.ACTION_UP:
                        easy.setImageResource(ImageChooser.getLvl(getResources().getConfiguration().locale.getLanguage(), "easy"));
                        Intent intent = new Intent(getApplicationContext(), CreategameActivity.class);
                        intent.putExtra("dictionary", s+"_easy");
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });



        medium.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        MusicWorker.getInstance(getApplicationContext()).playSound(MusicWorker.click);
                        medium.setImageResource(ImageChooser.getLvl(getResources().getConfiguration().locale.getLanguage(), "mediumused"));
                        break;

                    case MotionEvent.ACTION_UP:
                        medium.setImageResource(ImageChooser.getLvl(getResources().getConfiguration().locale.getLanguage(), "medium"));
                        Intent intent = new Intent(getApplicationContext(), CreategameActivity.class);
                        intent.putExtra("dictionary", s+"_medium");
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });



        hard.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        MusicWorker.getInstance(getApplicationContext()).playSound(MusicWorker.click);
                        hard.setImageResource(ImageChooser.getLvl(getResources().getConfiguration().locale.getLanguage(), "hardused"));
                        break;

                    case MotionEvent.ACTION_UP:
                        hard.setImageResource(ImageChooser.getLvl(getResources().getConfiguration().locale.getLanguage(), "hard"));
                        Intent intent = new Intent(getApplicationContext(), CreategameActivity.class);
                        intent.putExtra("dictionary", s+"_hard");
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });
    }
}
