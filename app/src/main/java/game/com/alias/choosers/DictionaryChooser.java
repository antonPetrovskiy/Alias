package game.com.alias.choosers;

import android.content.res.Resources;

import game.com.alias.GameActivity;
import game.com.alias.R;

public class DictionaryChooser {

    public static String name = "";

    public static int getDictionary(String s){
        if(name.equals("general_easy")) {
            if (s.equals("en"))
                return R.array.string_array_general_easy_en;
            if (s.equals("ru"))
                return R.array.string_array_general_easy_ru;
            return R.array.string_array_general_easy_en;
        }

        if(name.equals("music_easy")) {
            if (s.equals("en"))
                return R.array.string_array_music_easy_en;
            if (s.equals("ru"))
                return R.array.string_array_music_easy_ru;
            return R.array.string_array_music_easy_en;
        }

        if(name.equals("movies_easy")) {
            if (s.equals("en"))
                return R.array.string_array_movies_easy_en;
            if (s.equals("ru"))
                return R.array.string_array_movies_easy_ru;
            return R.array.string_array_movies_easy_en;
        }

        if(name.equals("literature_easy")) {
            if (s.equals("en"))
                return R.array.string_array_literature_easy_en;
            if (s.equals("ru"))
                return R.array.string_array_literature_easy_ru;
            return R.array.string_array_literature_easy_en;
        }

        if(name.equals("general_medium")) {
            if (s.equals("en"))
                return R.array.string_array_general_medium_en;
            if (s.equals("ru"))
                return R.array.string_array_general_medium_ru;
            return R.array.string_array_general_medium_en;
        }

        if(name.equals("music_medium")) {
            if (s.equals("en"))
                return R.array.string_array_music_medium_en;
            if (s.equals("ru"))
                return R.array.string_array_music_medium_ru;
            return R.array.string_array_music_medium_en;
        }

        if(name.equals("movies_medium")) {
            if (s.equals("en"))
                return R.array.string_array_movies_medium_en;
            if (s.equals("ru"))
                return R.array.string_array_movies_medium_ru;
            return R.array.string_array_movies_medium_en;
        }

        if(name.equals("literature_medium")) {
            if (s.equals("en"))
                return R.array.string_array_literature_medium_en;
            if (s.equals("ru"))
                return R.array.string_array_literature_medium_ru;
            return R.array.string_array_literature_medium_en;
        }

        if(name.equals("general_hard")) {
            if (s.equals("en"))
                return R.array.string_array_general_hard_en;
            if (s.equals("ru"))
                return R.array.string_array_general_hard_ru;
            return R.array.string_array_general_hard_en;
        }

        if(name.equals("music_hard")) {
            if (s.equals("en"))
                return R.array.string_array_music_hard_en;
            if (s.equals("ru"))
                return R.array.string_array_music_hard_ru;
            return R.array.string_array_music_hard_en;
        }

        if(name.equals("movies_hard")) {
            if (s.equals("en"))
                return R.array.string_array_movies_hard_en;
            if (s.equals("ru"))
                return R.array.string_array_movies_hard_ru;
            return R.array.string_array_movies_hard_en;
        }

        if(name.equals("literature_hard")) {
            if (s.equals("en"))
                return R.array.string_array_literature_hard_en;
            if (s.equals("ru"))
                return R.array.string_array_literature_hard_ru;
            return R.array.string_array_literature_hard_en;
        }

        return R.array.string_array_general_easy_en;
    }

    public static int getTeam(String s){
        if (s.equals("en"))
            return R.array.string_array_teams_en;
        if (s.equals("ru"))
            return R.array.string_array_teams_ru;
        return R.array.string_array_teams_en;
    }
}
