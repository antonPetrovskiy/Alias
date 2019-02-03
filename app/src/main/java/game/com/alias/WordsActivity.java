package game.com.alias;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import game.com.alias.choosers.ImageChooser;
import game.com.alias.choosers.MusicWorker;

import static android.widget.AbsListView.CHOICE_MODE_MULTIPLE;

public class WordsActivity extends AppCompatActivity {

    ArrayList<String> wordsList;
    ListView listView;
    ArrayAdapter<String> adapter;

    ImageView contin;
    ImageView teamImg;

    ArrayList<String> list;
    TextView score;
    TextView team;
    ArrayList<Boolean> flags;
    FileWorker fw;
    int words = 0;
    boolean checked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Intent intent = getIntent();
        flags = (ArrayList<Boolean>) intent.getSerializableExtra("flags");

        init();
        buttons();


    }

    public void init(){

        fw = FileWorker.getInstance();
        words = fw.getWords();
        listView = findViewById(R.id.list);
        listView.setChoiceMode(CHOICE_MODE_MULTIPLE);

        score = findViewById(R.id.textView7);
        team = findViewById(R.id.textView8);
        team.setText(fw.getTeamAt(Integer.parseInt(fw.readLineGame(6))));
        teamImg = findViewById(R.id.imageView16);
        teamImg.setImageResource(ImageChooser.getNextroundImg(fw.getTeamAt(Integer.parseInt(fw.readLineGame(6)))));
        writeList();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice,list);
        for(int i = 0; i < list.size(); i ++)
            if(flags.get(i)){
                listView.setItemChecked(i,true);
            }else{
                listView.setItemChecked(i,true);
            }

        listView.setAdapter(adapter);
        for(int i = 0; i < list.size(); i ++)
            if(flags.get(i)){
                listView.setItemChecked(i,true);
            }else{
                listView.setItemChecked(i,true);
            }


        if(getResources().getConfiguration().locale.getLanguage().equals("ru")){
            score.setText(listView.getCheckedItemCount()/2 + " очков");
        }else{
            score.setText(listView.getCheckedItemCount()/2 + " points");
        }

        Typeface face = Typeface.createFromAsset(getAssets(), "first.otf");
        score.setTypeface(face);
        team.setTypeface(face);

        contin = findViewById(R.id.imageView5);
        contin.setImageResource(ImageChooser.getNext(getResources().getConfiguration().locale.getLanguage(), "next"));

    }

    public void buttons(){
        contin.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        MusicWorker.getInstance(getApplicationContext()).playSound(MusicWorker.click);
                        contin.setImageResource(ImageChooser.getNext(getResources().getConfiguration().locale.getLanguage(), "nextused"));
                        break;

                    case MotionEvent.ACTION_UP:
                        setScore();
                        String s = fw.readLineGame(6);
                        int n = Integer.parseInt(s);
                        if(n==Integer.parseInt(fw.readLineGame(1))){
                            n = 1;
                        }else{
                            n++;
                        }
                        fw.writeLineGame(6,n +"");
                        Intent intent = new Intent(getApplicationContext(), TeamsActivity.class);
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                checked = true;
                words = listView.getCheckedItemCount();
                if(getResources().getConfiguration().locale.getLanguage().equals("ru")){
                    score.setText(words-list.size() + " очков");
                }else{
                    score.setText(words-list.size() + " points");
                }

            }
        });
    }



    public void writeList(){
        list = new ArrayList<>();
        list = fw.getFileArray(fw.getWordsFile());
        for(int i = 0; i < list.size(); i ++)
            listView.setItemChecked(i,true);
    }



    public void setScore(){

        String s = fw.readLineGame(6);
        int n = Integer.parseInt(s);
        if(checked){
            fw.writeScore(n,words-list.size()+"");
        }else{
            fw.writeScore(n,words+"");
        }

        fw.removeFile(fw.getWordsFile());
    }


    @Override
    public void onBackPressed() {
        AlertDialog.Builder ad;
        Context cnt = WordsActivity.this;
        MusicWorker.getInstance(getApplicationContext()).playSound(MusicWorker.back);
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

        ad = new AlertDialog.Builder(cnt);
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
