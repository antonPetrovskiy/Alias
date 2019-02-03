package game.com.alias;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import game.com.alias.choosers.ImageChooser;
import game.com.alias.choosers.MusicWorker;

public class FinalscoreActivity extends AppCompatActivity {

    TextView text1;
    TextView text2;
    String team;

    ImageView next;


    ArrayList<String> teamList;
    ArrayList<String> scoreList;
    ListView listView;
    ListView listView1;
    ArrayAdapter<String> adapter;
    ArrayAdapter<String> adapter1;
    FileWorker fw;

    LinearLayout listViewImg;
    ImageView winTeam;
    TextView winName;
    TextView winScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalscore);


        Intent intent = getIntent();
        team = intent.getStringExtra("team");
        init();
        buttons();
    }








    public void init(){
        text1 = findViewById(R.id.textView2);
        text2 = findViewById(R.id.textView3);
        next = findViewById(R.id.imageView1);
        winName = findViewById(R.id.textView4);
        winScore = findViewById(R.id.textView13);
        Typeface face = Typeface.createFromAsset(getAssets(), "first.otf");
        text1.setTypeface(face);
        text2.setTypeface(face);
        winName.setTypeface(face);
        winScore.setTypeface(face);
        winTeam = findViewById(R.id.imageView19);



        next.setImageResource(ImageChooser.getNext(getResources().getConfiguration().locale.getLanguage(), "next"));
        if(getResources().getConfiguration().locale.getLanguage().equals("ru")){
            text1.setText("Молодцы,");
            text2.setText("команда " + team);
        }else{
            text1.setText("Well done,");
            text2.setText("team " + team);
        }


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
        for(int i = 0; i < teamList.size(); i++){
            ImageView img = new ImageView(getApplicationContext());
            img.setAdjustViewBounds(true);

            if(teamList.get(i).equals(team)){
                winName.setText(teamList.get(i));
                winScore.setText(scoreList.get(i));
                winTeam.setImageResource(ImageChooser.getWinteamImg(teamList.get(i)));
                img.setImageResource(ImageChooser.getScoreImg(teamList.get(i)));
            }else{
                img.setImageResource(ImageChooser.getScoreImg(teamList.get(i)));
            }

            listViewImg.addView(img);
        }

    }



    public void buttons(){
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MusicWorker.getInstance(getApplicationContext()).playSound(MusicWorker.click);
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {

    }
}
