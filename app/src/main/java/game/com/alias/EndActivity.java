package game.com.alias;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import game.com.alias.choosers.ImageChooser;
import game.com.alias.choosers.MusicWorker;

public class EndActivity extends AppCompatActivity {

    TextView text;
    TextView cool;
    String team;
    ImageView next;
    ImageView teamImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Intent intent = getIntent();
        team = intent.getStringExtra("team");
        init();
        buttons();
        MusicWorker.getInstance(getApplicationContext()).playSound(MusicWorker.win);

        Typeface face = Typeface.createFromAsset(getAssets(), "first.otf");
        text.setTypeface(face);
        cool.setTypeface(face);
    }

    public void init(){
        text = findViewById(R.id.textView12);
        cool = findViewById(R.id.textView11);
        next = findViewById(R.id.imageView1);
        teamImg = findViewById(R.id.imageView17);
        teamImg.setImageResource(ImageChooser.getWinImg(team));
        next.setImageResource(ImageChooser.getNext(getResources().getConfiguration().locale.getLanguage(), "next"));

        if(getResources().getConfiguration().locale.getLanguage().equals("ru")){
            cool.setText("Поздравляем!");
            text.setText("Команда " + team + " победила!");
        }else{
            cool.setText("Congratulations!");
            text.setText("Team " + team + " wins!");
        }

    }

    public void buttons(){
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MusicWorker.getInstance(getApplicationContext()).playSound(MusicWorker.click);
                Intent intent = new Intent(getApplicationContext(), FinalscoreActivity.class);
                intent.putExtra("team", team);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {

    }
    }
