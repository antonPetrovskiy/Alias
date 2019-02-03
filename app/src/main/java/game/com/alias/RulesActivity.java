package game.com.alias;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import game.com.alias.choosers.ImageChooser;
import game.com.alias.choosers.MusicWorker;

public class RulesActivity extends AppCompatActivity {
    public ImageView nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        nextButton = findViewById(R.id.imageView5);
        nextButton.setImageResource(ImageChooser.getNext(getResources().getConfiguration().locale.getLanguage(), "next"));

        TextView tv = findViewById(R.id.textView);

        if(getResources().getConfiguration().locale.getLanguage().equals("ru")){
            tv.setText("1. Команды ходят по очереди. Обычно ход команды длится минуту (но это можно менять в настройках). За эту минуту один из членов команды должен объяснить слова, появляющиеся на экране другим членам команды.\n" +
                    "\n" +
                    "\n" +
                    "2. Описывать слово нужно, не употребляя однокоренных к нему.\n" +
                    "\n" +
                    "3. Нельзя использовать иноязычные аналоги этого слова.\n" +
                    "\n" +
                    "4. Нельзя жестами показывать это слово или указывать на этот предмет. \n" +
                    "\n" +
                    "5. Переход к следующему слову возможен только после того, как команда отгадала предыдущее, или когда слово пометили, как пропущенное.\n" +
                    "\n" +
                    "6. Количество команд и игроков неограниченно.");
        }


        nextButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        nextButton.setImageResource(ImageChooser.getNext(getResources().getConfiguration().locale.getLanguage(), "nextused"));
                        MusicWorker.getInstance(getApplicationContext()).playSound(MusicWorker.click);
                        break;

                    case MotionEvent.ACTION_UP:
                        nextButton.setImageResource(ImageChooser.getNext(getResources().getConfiguration().locale.getLanguage(), "next"));
                        onBackPressed();
                        finish();
                        break;
                }
                return true;
            }
        });
    }
}
