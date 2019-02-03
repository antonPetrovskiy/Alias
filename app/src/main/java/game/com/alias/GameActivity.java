package game.com.alias;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import game.com.alias.choosers.DictionaryChooser;
import game.com.alias.choosers.MusicWorker;
import me.itangqi.waveloadingview.WaveLoadingView;

public class GameActivity extends AppCompatActivity {

    FileWorker fw;
    boolean played = false;
    boolean end = false;
    boolean start = false;
    boolean get = true;
    boolean firstclick = true;
    WaveLoadingView loadingView;
    //TextView time;
    Timer timer;
    int seconds = 0;
    //TextView name;
    ImageView slider;
    ArrayList<String> allwords;
    ArrayList<Boolean> flags;

    boolean isTouched = false;
    boolean firstTouch = true;
    boolean isDragging = false;

    private Animation animationTop;
    private Animation animationBot;

    TextView word;

    float constY;
    int y = 0;
    int coord = 0;

    String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        init();
        buttons();


    }



    public void init(){
        fw = FileWorker.getInstance();
        loadingView = findViewById(R.id.waveLoadingView);
        if(getResources().getConfiguration().locale.getLanguage().equals("ru")){
            loadingView.setCenterTitle("Готов?");
        }else{
            loadingView.setCenterTitle("Ready?");
        }

        //time = findViewById(R.id.textView2);
        timer = new Timer();
        slider = findViewById(R.id.imageView15);
        flags = new ArrayList<>();
        allwords = new ArrayList<>();
        allwords = fw.readallWords();
        animationTop = AnimationUtils.loadAnimation(getApplication(), R.anim.totop);
        animationBot = AnimationUtils.loadAnimation(getApplication(), R.anim.tobot);
        //name = findViewById(R.id.textView4);
        word = findViewById(R.id.word);
        Typeface face = Typeface.createFromAsset(getAssets(), "first.otf");
        word.setTypeface(face);
        //name.setText(fw.getTeamAt(Integer.parseInt(fw.readLineGame(6))));
    }


    class UpdateTimeTask extends TimerTask {
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(seconds<=60){
                        if((seconds<31 || seconds>34) && (seconds<41 || seconds>44)){
                            loadingView.setProgressValue(loadingView.getProgressValue()+2);
                        }

                        //time.setText(seconds+"");
                        loadingView.setBottomTitle(60-seconds+"");

                        seconds++;
                    }else if(!end){
                        MusicWorker.getInstance(getApplicationContext()).playSound(MusicWorker.timeout);
                        int n = Integer.parseInt(fw.readLineGame(5));
                        n++;
                        fw.writeLineGame(5,n+"");
                        played = false;
                        end = true;
                    }
                }
            });

        }
    }


    public void buttons(){
        slider.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                int x1 = (int)event.getRawX();
                int y1 = (int)event.getRawY();

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if(firstTouch)
                            constY = (int)event.getRawY();

                        firstTouch = false;
                        isTouched = true;
                        isDragging = true;
                        break;

                    case MotionEvent.ACTION_UP:
                        isTouched = false;
                        break;

                    case MotionEvent.ACTION_MOVE:
                        if(isDragging){
                            if(y1>y){

                                y = y1;
                                coord+=20;
                                //slider.setY(slider.getY()+20);
                                checkSlide(coord);
                            }
                            if(y1<y){

                                y = y1;
                                coord-=20;
                                //slider.setY(slider.getY()-20);
                                checkSlide(coord);
                            }
                        }

                        break;
                }
                return true;
            }
        });

    }

    public void upload(){
        loadingView.setCenterTitleStrokeColor(Color.TRANSPARENT);
        if(!start){
            timer.schedule(new UpdateTimeTask(), 0, 1000); //тикаем каждую секунду без задержки
            played = true;
            start = true;
            s = generateWord();

            //loadingView.setCenterTitleSize(setSize(s));
            word.setText(s);
            loadingView.setCenterTitle("");
        }else if(played) {
            if(get){
                fw.writeWords(s);
                fw.writeallWords(s);
            }
            s = generateWord();
            //loadingView.setCenterTitleSize(setSize(s));
            word.setText(s);
            loadingView.setCenterTitle("");
        }else{

            if(get){
                //fw.writeWords(s);
                fw.writeallWords(s);
                if(fw.readLineGame(3).equals("true")){
                    chooseLastWord();
                }else{
                    fw.writeWords(s);
                    Intent intent = new Intent(getApplicationContext(), WordsActivity.class);
                    intent.putExtra("flags", flags);
                    startActivity(intent);
                }

            }else{
                Intent intent = new Intent(getApplicationContext(), WordsActivity.class);
                intent.putExtra("flags", flags);
                startActivity(intent);
            }

        }
    }

    public String generateWord(){
        allwords = fw.readallWords();
        final Random random = new Random();
        Resources res = getResources();
        String[] words = res.getStringArray(DictionaryChooser.getDictionary(getResources().getConfiguration().locale.getLanguage()));
        int n = 0;
        boolean check = true;
        while(check) {
            n = random.nextInt(words.length-1);
            check = false;
            for (int i = 0; i < allwords.size(); i++) {
                if (allwords.size()!=0 && words[n].equals(allwords.get(i))) {
                    check = true;
                }
            }
        }
        return words[n];
    }

    public void checkSlide(float f){

        float result = f ;
        if(result > 50){
            get = false;
            if(start)
                flags.add(false);
            //slider.setY(constY);
            isDragging = false;
            MusicWorker.getInstance(getApplicationContext()).playSound(MusicWorker.bot);
            slider.startAnimation(animationBot);
            //slider.setY(constY);
            coord = 0;
            upload();
        }else if(result < -50){
            get = true;
            if(start)
                flags.add(true);
            //slider.setY(constY);
            isDragging = false;
            MusicWorker.getInstance(getApplicationContext()).playSound(MusicWorker.top);
            slider.startAnimation(animationTop);
            //slider.setY(constY);
            coord = 0;
            upload();
        }
        //if(Math.abs(result) > 200){
            //slider.setY(constY);
            //isDragging = false;
            //upload();
        //}
    }

    public void chooseLastWord(){
        String[] arr = fw.readTeams().toArray(new String[fw.readTeams().size()]);
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.dialog));
        if(getResources().getConfiguration().locale.getLanguage().equals("ru")){
            builder.setTitle("Последнее слово");
        }else{
            builder.setTitle("Last word");
        }
        builder.setItems(arr, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        fw.writeScore(which+1,1+"");
                        Intent intent = new Intent(getApplicationContext(), WordsActivity.class);
                        intent.putExtra("flags", flags);
                        startActivity(intent);
                    }
                });
        builder.show();
    }



    @Override
    public void onBackPressed() {
        MusicWorker.getInstance(getApplicationContext()).playSound(MusicWorker.back);
        AlertDialog.Builder ad;
        Context cnt = GameActivity.this;

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
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                timer.cancel();
                finish();
                startActivity(intent);

            }
        });
        ad.setNegativeButton(button2String, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {

            }
        });
        ad.show();

    }

}
