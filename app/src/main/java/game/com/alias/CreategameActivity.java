package game.com.alias;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import game.com.alias.choosers.DictionaryChooser;
import game.com.alias.choosers.ImageChooser;
import game.com.alias.choosers.MusicWorker;

public class CreategameActivity extends AppCompatActivity {

    ImageView play;
    ImageView round;
    ImageView back;
    ImageView backTeamImg;

    ArrayList<String> teamList;
    LinearLayout listView;


    TextView rounds;
    Switch lastw;
    TextView roundsButton;

    TextView roundText;
    TextView lastwText;

    ImageView addTeam;
    String[] words;

    FileWorker fw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creategame);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        init();
        String dictionary = getIntent().getStringExtra("dictionary");
        DictionaryChooser.name = dictionary;
        buttons();
    }

    public void init(){
        play = findViewById(R.id.imageView5);
        play.setImageResource(ImageChooser.getNext(getResources().getConfiguration().locale.getLanguage(), "next"));
        back = findViewById(R.id.imageView18);
        backTeamImg = findViewById(R.id.imageView3);
        backTeamImg.setImageResource(ImageChooser.getBackteam(getResources().getConfiguration().locale.getLanguage()));
        listView = findViewById(R.id.list);
        rounds = findViewById(R.id.textView3);
        lastw = findViewById(R.id.checkBox);
        roundsButton = findViewById(R.id.textView3);
        addTeam = findViewById(R.id.imageView4);
        addTeam.setImageResource(ImageChooser.getAddteam(getResources().getConfiguration().locale.getLanguage(), "addteam"));
        teamList = new ArrayList<>();
        Typeface face = Typeface.createFromAsset(getAssets(), "first.otf");
        TextView roundText = findViewById(R.id.textView5);
        TextView lastwText = findViewById(R.id.textView6);

        if(getResources().getConfiguration().locale.getLanguage().equals("ru")){
            roundText.setText("Раунды");
            lastwText.setText("Последнее слово для всех");
        }else{
            roundText.setText("Rounds");
            lastwText.setText("Last word for everyone");
        }
        roundText.setTypeface(face);
        lastwText.setTypeface(face);
        rounds.setTypeface(face);

        round = findViewById(R.id.imageView15);
        fw = FileWorker.getInstance();
        initTeams();
        initTeams();
    }



    @SuppressLint("ClickableViewAccessibility")
    public void buttons(){

        play.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        MusicWorker.getInstance(getApplicationContext()).playSound(MusicWorker.click);
                        play.setImageResource(ImageChooser.getNext(getResources().getConfiguration().locale.getLanguage(), "nextused"));
                        break;

                    case MotionEvent.ACTION_UP:
                        play.setImageResource(ImageChooser.getNext(getResources().getConfiguration().locale.getLanguage(), "next"));
                        if(teamList.size()>=2){
                            fw.writeTeams(teamList);
                            int all = Integer.parseInt(rounds.getText().toString()) * teamList.size();
                            fw.createGame(teamList.size()+"", rounds.getText().toString(),lastw.isChecked()+"", all+"", "1", "1");
                            Intent intent = new Intent(getApplicationContext(), TeamsActivity.class);
                            startActivity(intent);
                        }else{
                            if(getResources().getConfiguration().locale.getLanguage().equals("ru")){
                                Toast toast = Toast.makeText(getApplicationContext(),
                                        "Минимальное количество команд - 2", Toast.LENGTH_LONG);
                                toast.show();
                            }else{
                                Toast toast = Toast.makeText(getApplicationContext(),
                                        "Minimal number of teams is 2", Toast.LENGTH_LONG);
                                toast.show();
                            }

                        }
                        break;
                }
                return true;
            }
        });




        roundsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MusicWorker.getInstance(getApplicationContext()).playSound(MusicWorker.click);
                final View views = LayoutInflater.from(CreategameActivity.this).inflate(R.layout.dialog_rounds,null);


                /*AlertDialog.Builder builder = new AlertDialog.Builder(CreategameActivity.this);
                builder.setView(views);
                // Get the layout inflater


                // Inflate and set the layout for the dialog
                // Pass null as the parent view because its going in the dialog layout

                builder.show();*/


                AlertDialog.Builder alert = new AlertDialog.Builder(new ContextThemeWrapper(CreategameActivity.this, R.style.dialog));

                final EditText input = new EditText(CreategameActivity.this);
                input.setInputType(InputType.TYPE_CLASS_NUMBER);
                if(getResources().getConfiguration().locale.getLanguage().equals("ru")){
                    alert.setMessage("Введите количество раундов");
                }else{
                    alert.setMessage("Enter rounds number");
                }

                alert.setView(input);
                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        if(!input.getText().toString().equals("")){
                            rounds.setText(input.getText().toString());
                        }
                    }
                });
                alert.show();
            }
        });

        addTeam.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        MusicWorker.getInstance(getApplicationContext()).playSound(MusicWorker.click);
                        addTeam.setImageResource(ImageChooser.getAddteam(getResources().getConfiguration().locale.getLanguage(), "addteamused"));
                        break;

                    case MotionEvent.ACTION_UP:
                        final Random random = new Random();
                        Resources res = getResources();
                        if(teamList.size()!=7) {
                            words = res.getStringArray(DictionaryChooser.getTeam(getResources().getConfiguration().locale.getLanguage()));
                            int n = random.nextInt(10);
                            if (teamList.size() == 0) {
                                teamList.add(words[n]);
                            } else if (teamList.size() != words.length) {
                                for (int i = 1; i <= teamList.size(); i++) {
                                    if (words[n].equals(teamList.get(i - 1))) {
                                        n = random.nextInt(10);
                                        i = 0;
                                    }
                                }

                                teamList.add(words[n]);
                                //adapter.notifyDataSetChanged();
                            }
                            //adapter.notifyDataSetChanged();
                            ImageView i = new ImageView(getApplicationContext());
                            i.setAdjustViewBounds(true);
                            i.setImageResource(ImageChooser.getCreateImg(teamList.get(teamList.size() - 1)));
                            i.setPadding(0, 8, 0, 0);

                            i.setId(setMyId(words[n]));

                            i.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    MusicWorker.getInstance(getApplicationContext()).playSound(MusicWorker.click);
                                    for (int i = 0; i < teamList.size(); i++) {

                                        if (view.getResources().getResourceName(view.getId()).contains(translater(teamList.get(i)))) {
                                            teamList.remove(i);
                                            listView.removeView(view);
                                            return;
                                        }
                                    }

                                }
                            });
                            listView.addView(i);
                        }
                        addTeam.setImageResource(ImageChooser.getAddteam(getResources().getConfiguration().locale.getLanguage(), "addteam"));
                        break;
                }
                return true;
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MusicWorker.getInstance(getApplicationContext()).playSound(MusicWorker.click);
                onBackPressed();
            }
        });




    }


    public void initTeams(){
        final Random random = new Random();
        Resources res = getResources();
        words = res.getStringArray(DictionaryChooser.getTeam(getResources().getConfiguration().locale.getLanguage()));
        int n = random.nextInt(10);
        if(teamList.size()==0){
            teamList.add(words[n]);
        }else if(teamList.size()!=words.length){
            for(int i = 1; i <= teamList.size(); i ++){
                if(words[n].equals(teamList.get(i-1))){
                    n = random.nextInt(10);
                    i = 0;
                }
            }

            teamList.add(words[n]);
            //adapter.notifyDataSetChanged();
        }
        //adapter.notifyDataSetChanged();
        ImageView i = new ImageView(getApplicationContext());
        i.setAdjustViewBounds(true);
        i.setImageResource(ImageChooser.getCreateImg(teamList.get(teamList.size()-1)));
        i.setPadding(0,8,0,0);
        i.setId(setMyId(words[n]));
        i.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MusicWorker.getInstance(getApplicationContext()).playSound(MusicWorker.click);
                for(int i = 0; i < teamList.size(); i ++){

                    if(view.getResources().getResourceName(view.getId()).contains(translater(teamList.get(i)))){
                        teamList.remove(i);
                        listView.removeView(view);
                        return;
                    }
                }


            }
        });
        listView.addView(i);
        //addTeam.setImageResource(R.drawable.addteam);
    }


    public int setMyId(String s){

            if (s.equals("Birds") || s.equals("Птицы"))
                return R.id.Birds;
            if (s.equals("Carrots") || s.equals("Морковки"))
                return R.id.Carrots;
            if (s.equals("Wizards") || s.equals("Чародеи"))
                return R.id.Wizards;
            if (s.equals("Potatoes") || s.equals("Картофелины"))
                return R.id.Potatoes;
            if (s.equals("Pterosauruses") || s.equals("Птерозавры"))
                return R.id.Pterosauruses;
            if (s.equals("Blackholes") || s.equals("Чёрные дыры"))
                return R.id.Blackholes;
            if (s.equals("Vikings") || s.equals("Викинги"))
                return R.id.Vikings;
            if (s.equals("Krakens") || s.equals("Кракены"))
                return R.id.Krakens;
            if (s.equals("Cupcakes") || s.equals("Кексики"))
                return R.id.Cupcakes;
            if (s.equals("Totems") || s.equals("Тотемы"))
                return R.id.Totems;

        return 0;
    }

    public String translater(String s){
        switch (s){
            case "Птицы":
                return "Birds";
            case "Морковки":
                return "Carrots";
            case "Чародеи":
                return "Wizards";
            case "Картофелины":
                return "Potatoes";
            case "Птерозавры":
                return "Pterosauruses";
            case "Чёрные дыры":
                return "Blackholes";
            case "Викинги":
                return "Vikings";
            case "Кракены":
                return "Krakens";
            case "Кексики":
                return "Cupcakes";
            case "Тотемы":
                return "Totems";
        }
        return s;
    }
}
