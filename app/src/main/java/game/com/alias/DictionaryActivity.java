package game.com.alias;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import game.com.alias.choosers.ImageChooser;
import game.com.alias.choosers.MusicWorker;

public class DictionaryActivity extends AppCompatActivity {

    public ImageView nextButton;
    public ImageView general;
    public ImageView music;
    public ImageView movies;
    public ImageView literature;


    LinearLayout l;

    public boolean generalFlag;
    public boolean musicFlag;
    public boolean moviesFlag;
    public boolean literatureFlag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictioary);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        init();
        buttons();
    }

    public void init(){
        nextButton = findViewById(R.id.imageView5);
        general = findViewById(R.id.imageView11);
        music = findViewById(R.id.imageView13);
        movies = findViewById(R.id.imageView14);
        literature = findViewById(R.id.imageView15);

        general.setImageResource(ImageChooser.getDictionary(getResources().getConfiguration().locale.getLanguage(), "general"));
        music.setImageResource(ImageChooser.getDictionary(getResources().getConfiguration().locale.getLanguage(), "music"));
        movies.setImageResource(ImageChooser.getDictionary(getResources().getConfiguration().locale.getLanguage(), "movies"));
        literature.setImageResource(ImageChooser.getDictionary(getResources().getConfiguration().locale.getLanguage(), "literature"));
        nextButton.setImageResource(ImageChooser.getNext(getResources().getConfiguration().locale.getLanguage(), "next"));

    }


    public void buttons(){

        general.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MusicWorker.getInstance(getApplicationContext()).playSound(MusicWorker.click);
                if(!generalFlag){
                    uncheck();
                    general.setImageResource(ImageChooser.getDictionary(getResources().getConfiguration().locale.getLanguage(), "generalused"));
                    generalFlag = true;
                }else{
                    general.setImageResource(ImageChooser.getDictionary(getResources().getConfiguration().locale.getLanguage(), "general"));
                    generalFlag = false;
                }

            }
        });

        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MusicWorker.getInstance(getApplicationContext()).playSound(MusicWorker.click);
                if(!musicFlag){
                    uncheck();
                    music.setImageResource(ImageChooser.getDictionary(getResources().getConfiguration().locale.getLanguage(), "musicused"));
                    musicFlag = true;
                }else{
                    music.setImageResource(ImageChooser.getDictionary(getResources().getConfiguration().locale.getLanguage(), "music"));
                    musicFlag = false;
                }

            }
        });

        movies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MusicWorker.getInstance(getApplicationContext()).playSound(MusicWorker.click);
                if(!moviesFlag){
                    uncheck();
                    movies.setImageResource(ImageChooser.getDictionary(getResources().getConfiguration().locale.getLanguage(), "moviesused"));
                    moviesFlag = true;
                }else{
                    movies.setImageResource(ImageChooser.getDictionary(getResources().getConfiguration().locale.getLanguage(), "movies"));
                    moviesFlag = false;
                }

            }
        });

        literature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MusicWorker.getInstance(getApplicationContext()).playSound(MusicWorker.click);
                if(!literatureFlag){
                    uncheck();
                    literature.setImageResource(ImageChooser.getDictionary(getResources().getConfiguration().locale.getLanguage(), "literatureused"));
                    literatureFlag = true;
                }else{
                    literature.setImageResource(ImageChooser.getDictionary(getResources().getConfiguration().locale.getLanguage(), "literature"));
                    literatureFlag = false;
                }

            }
        });



        nextButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                String s = "";
                if(generalFlag)
                    s = "general";
                if(musicFlag)
                    s = "music";
                if(moviesFlag)
                    s = "movies";
                if(literatureFlag)
                    s = "literature";


                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        MusicWorker.getInstance(getApplicationContext()).playSound(MusicWorker.click);
                        nextButton.setImageResource(ImageChooser.getNext(getResources().getConfiguration().locale.getLanguage(), "nextused"));
                        break;

                    case MotionEvent.ACTION_UP:
                        nextButton.setImageResource(ImageChooser.getNext(getResources().getConfiguration().locale.getLanguage(), "next"));
                        if(!s.equals("")){
                            Intent intent = new Intent(getApplicationContext(), LevelActivity.class);
                            intent.putExtra("dictionary", s);
                            startActivity(intent);
                        }else{
                            Toast toast = Toast.makeText(getApplicationContext(),
                                    "Choose the dictionary", Toast.LENGTH_SHORT);
                            toast.show();
                        }

                        break;
                }
                return true;
            }
        });
    }


    public void uncheck(){
        general.setImageResource(ImageChooser.getDictionary(getResources().getConfiguration().locale.getLanguage(), "general"));
        music.setImageResource(ImageChooser.getDictionary(getResources().getConfiguration().locale.getLanguage(), "music"));
        movies.setImageResource(ImageChooser.getDictionary(getResources().getConfiguration().locale.getLanguage(), "movies"));
        literature.setImageResource(ImageChooser.getDictionary(getResources().getConfiguration().locale.getLanguage(), "literature"));
        literatureFlag = false;
        musicFlag = false;
        generalFlag = false;
        moviesFlag = false;
    }
}
