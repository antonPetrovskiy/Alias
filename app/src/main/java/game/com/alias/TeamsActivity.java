package game.com.alias;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import game.com.alias.choosers.ImageChooser;
import game.com.alias.choosers.MusicWorker;

public class TeamsActivity extends AppCompatActivity {

    ArrayList<String> teamList;
    ArrayList<String> scoreList;
    ListView listView;
    ListView listView1;
    ArrayAdapter<String> adapter;
    ArrayAdapter<String> adapter1;
    FileWorker fw;
    TextView team;
    LinearLayout listViewImg;
    TextView nextRound;

    ImageView contin;
    ImageView teamImg;
    ImageView scoreImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        init();
        buttons();
    }



    public void init(){
        fw = FileWorker.getInstance();
        teamList = new ArrayList<>();
        scoreList = new ArrayList<>();
        listView = findViewById(R.id.list);
        listView1 = findViewById(R.id.list1);
        listViewImg = findViewById(R.id.imglist);
        teamList = fw.readTeams();
        scoreList = fw.readScore();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,teamList);
        adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,scoreList);

        listView.setAdapter(adapter);
        listView1.setAdapter(adapter1);
        contin = findViewById(R.id.imageView5);
        contin.setImageResource(ImageChooser.getPlayBig(getResources().getConfiguration().locale.getLanguage(),"play"));
        team = findViewById(R.id.textView10);
        nextRound = findViewById(R.id.textView9);
        teamImg = findViewById(R.id.imageView20);
        Typeface face = Typeface.createFromAsset(getAssets(), "first.otf");
        nextRound.setTypeface(face);
        team.setTypeface(face);

        String s = fw.readLineGame(6);
        int k = Integer.parseInt(s);

        teamImg.setImageResource(ImageChooser.getNextroundImg(fw.getTeamAt(k)));
        team.setText(fw.getTeamAt(Integer.parseInt(fw.readLineGame(6))));
        scoreImg = findViewById(R.id.imageView1);
        scoreImg.setImageResource(ImageChooser.getScoretitle(getResources().getConfiguration().locale.getLanguage()));
        for(int i = 0; i < teamList.size(); i++){
            ImageView img = new ImageView(getApplicationContext());
            img.setAdjustViewBounds(true);

            img.setImageResource(ImageChooser.getScoreImg(teamList.get(i)));
            listViewImg.addView(img);
        }


        if(getResources().getConfiguration().locale.getLanguage().equals("ru")){
            nextRound.setText("Следующий раунд:");
        }else{
            nextRound.setText("Next Round:");
        }


        if(Integer.parseInt(fw.readLineGame(5)) > Integer.parseInt(fw.readLineGame(4))){
            int n = 0;
            int max = Integer.parseInt(scoreList.get(n));
            for (int i = 0; i < scoreList.size(); i ++){
                if(Integer.parseInt(scoreList.get(i))>max) {
                    max = Integer.parseInt(scoreList.get(i));
                    n=i;
                }
            }

            Intent intent = new Intent(getApplicationContext(), EndActivity.class);
            intent.putExtra("team", fw.getTeamAt(n+1));
            startActivity(intent);
        }
    }



    public void buttons(){
        contin.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        MusicWorker.getInstance(getApplicationContext()).playSound(MusicWorker.click);
                        contin.setImageResource(ImageChooser.getPlayBig(getResources().getConfiguration().locale.getLanguage(),"playused"));
                        break;

                    case MotionEvent.ACTION_UP:
                        contin.setImageResource(ImageChooser.getPlayBig(getResources().getConfiguration().locale.getLanguage(),"play"));
                        Intent intent = new Intent(getApplicationContext(), GameActivity.class);
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });

    }


    @Override
    public void onBackPressed() {
        MusicWorker.getInstance(getApplicationContext()).playSound(MusicWorker.back);
        AlertDialog.Builder ad;
        Context cnt = TeamsActivity.this;

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
                startActivity(intent);
                finish();
            }
        });
        ad.setNegativeButton(button2String, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {

            }
        });

        ad.show();

    }


}
