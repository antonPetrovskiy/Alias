package game.com.alias;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.ContextThemeWrapper;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Locale;

import game.com.alias.choosers.ImageChooser;
import game.com.alias.choosers.MusicWorker;

public class MenuActivity extends AppCompatActivity {

    public ImageView rulesButton;
    public ImageView gameButton;


    Resources res;
    DisplayMetrics dm;
    android.content.res.Configuration conf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        res = this.getResources();
        dm = res.getDisplayMetrics();
        conf = res.getConfiguration();
        if(getResources().getConfiguration().locale.getLanguage().equals("ru") || getResources().getConfiguration().locale.getLanguage().equals("ua")){
            conf.locale = new Locale("ru".toLowerCase());
            res.updateConfiguration(conf, dm);
        }else{
            conf.locale = new Locale("en".toLowerCase());
            res.updateConfiguration(conf, dm);
        }


        init();
        buttons();


    }

    public void init(){
        rulesButton = findViewById(R.id.rulesButton);
        gameButton = findViewById(R.id.gameButton);
        gameButton.setImageResource(ImageChooser.getPlay(getResources().getConfiguration().locale.getLanguage()));
        rulesButton.setImageResource(ImageChooser.getRules(getResources().getConfiguration().locale.getLanguage()));
    }


    public void buttons(){
        rulesButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        rulesButton.setImageResource(ImageChooser.getRulesused(getResources().getConfiguration().locale.getLanguage()));
                        MusicWorker.getInstance(getApplicationContext()).playSound(MusicWorker.click);
                        break;

                    case MotionEvent.ACTION_UP:
                        rulesButton.setImageResource(ImageChooser.getRules(getResources().getConfiguration().locale.getLanguage()));
                        Intent intent = new Intent(getApplicationContext(), RulesActivity.class);
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });



        gameButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        gameButton.setImageResource(ImageChooser.getPlayused(getResources().getConfiguration().locale.getLanguage()));
                        MusicWorker.getInstance(getApplicationContext()).playSound(MusicWorker.click);
                        break;

                    case MotionEvent.ACTION_UP:
                        gameButton.setImageResource(ImageChooser.getPlay(getResources().getConfiguration().locale.getLanguage()));
                        Intent intent = new Intent(getApplicationContext(), DictionaryActivity.class);
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });
    }


    @Override
    public void onBackPressed() {
        AlertDialog.Builder ad;
        Context cnt = MenuActivity.this;

        String title;
        String message;
        String button1String;
        String button2String;
        if(getResources().getConfiguration().locale.getLanguage().equals("ru")){
             title = "Выход";
             message = "Вы действительно хотите выйти?";
             button1String = "да";
             button2String = "нет";
        }else{
             title = "Quit";
             message = "You really want to exit?";
             button1String = "yes";
             button2String = "no";
        }


        ad = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.dialog));
        ad.setTitle(title);  // заголовок
        ad.setMessage(message); // сообщение
        ad.setPositiveButton(button1String, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
                finish();
            }
        });
        ad.setNegativeButton(button2String, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {

            }
        });

        ad.show();
        MusicWorker.getInstance(getApplicationContext()).playSound(MusicWorker.back);
    }


    private void checkUserPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 123);

            }



        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 123:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED){


                }else{
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                    checkUserPermission();
                }
                break;

            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        }

    }
}
