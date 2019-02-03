package game.com.alias.choosers;

import game.com.alias.R;

public class ImageChooser {

    public static int getCreateImg(String s){
        switch(s) {
            case "Birds":
                return R.drawable.t1;
            case "Carrots":
                return R.drawable.t3;
            case "Wizards":
                return R.drawable.t11;
            case "Potatoes":
                return R.drawable.t6;
            case "Pterosauruses":
                return R.drawable.t7;
            case "Blackholes":
                return R.drawable.t2;
            case "Vikings":
                return R.drawable.t10;
            case "Krakens":
                return R.drawable.t5;
            case "Cupcakes":
                return R.drawable.t4;
            case "Totems":
                return R.drawable.t9;
        }
        switch(s) {
            case "Птицы":
                return R.drawable.t1_ru;
            case "Морковки":
                return R.drawable.t3_ru;
            case "Чародеи":
                return R.drawable.t11_ru;
            case "Картофелины":
                return R.drawable.t6_ru;
            case "Птерозавры":
                return R.drawable.t7_ru;
            case "Чёрные дыры":
                return R.drawable.t2_ru;
            case "Викинги":
                return R.drawable.t10_ru;
            case "Кракены":
                return R.drawable.t5_ru;
            case "Кексики":
                return R.drawable.t4_ru;
            case "Тотемы":
                return R.drawable.t9_ru;
        }
        return 0;
    }

    public static int getNextroundImg(String s){

        switch(s) {
            case "Birds":
                return R.drawable.n1;
            case "Carrots":
                return R.drawable.n3;
            case "Wizards":
                return R.drawable.n11;
            case "Potatoes":
                return R.drawable.n6;
            case "Pterosauruses":
                return R.drawable.n7;
            case "Blackholes":
                return R.drawable.n2;
            case "Vikings":
                return R.drawable.n10;
            case "Krakens":
                return R.drawable.n5;
            case "Cupcakes":
                return R.drawable.n4;
            case "Totems":
                return R.drawable.n9;
        }

        switch(s) {
            case "Птицы":
                return R.drawable.n1;
            case "Морковки":
                return R.drawable.n3;
            case "Чародеи":
                return R.drawable.n11;
            case "Картофелины":
                return R.drawable.n6;
            case "Птерозавры":
                return R.drawable.n7;
            case "Чёрные дыры":
                return R.drawable.n2;
            case "Викинги":
                return R.drawable.n10;
            case "Кракены":
                return R.drawable.n5;
            case "Кексики":
                return R.drawable.n4;
            case "Тотемы":
                return R.drawable.n9;
        }
        return 0;
    }

    public static int getWinImg(String s){
        switch(s) {
            case "Birds":
                return R.drawable.w1;
            case "Carrots":
                return R.drawable.w3;
            case "Wizards":
                return R.drawable.w11;
            case "Potatoes":
                return R.drawable.w6;
            case "Pterosauruses":
                return R.drawable.w7;
            case "Blackholes":
                return R.drawable.w2;
            case "Vikings":
                return R.drawable.w10;
            case "Krakens":
                return R.drawable.w5;
            case "Cupcakes":
                return R.drawable.w4;
            case "Totems":
                return R.drawable.w9;
        }
        switch(s) {
            case "Птицы":
                return R.drawable.w1;
            case "Морковки":
                return R.drawable.w3;
            case "Чародеи":
                return R.drawable.w11;
            case "Картофелины":
                return R.drawable.w6;
            case "Птерозавры":
                return R.drawable.w7;
            case "Чёрные дыры":
                return R.drawable.w2;
            case "Викинги":
                return R.drawable.w10;
            case "Кракены":
                return R.drawable.w5;
            case "Кексики":
                return R.drawable.w4;
            case "Тотемы":
                return R.drawable.w9;
        }
        return 0;
    }

    public static int getScoreImg(String s){
        switch(s) {
            case "Birds":
                return R.drawable.s1;
            case "Carrots":
                return R.drawable.s3;
            case "Wizards":
                return R.drawable.s11;
            case "Potatoes":
                return R.drawable.s6;
            case "Pterosauruses":
                return R.drawable.s7;
            case "Blackholes":
                return R.drawable.s2;
            case "Vikings":
                return R.drawable.s10;
            case "Krakens":
                return R.drawable.s5;
            case "Cupcakes":
                return R.drawable.s4;
            case "Totems":
                return R.drawable.s9;
        }
        switch(s) {
            case "Птицы":
                return R.drawable.s1;
            case "Морковки":
                return R.drawable.s3;
            case "Чародеи":
                return R.drawable.s11;
            case "Картофелины":
                return R.drawable.s6;
            case "Птерозавры":
                return R.drawable.s7;
            case "Чёрные дыры":
                return R.drawable.s2;
            case "Викинги":
                return R.drawable.s10;
            case "Кракены":
                return R.drawable.s5;
            case "Кексики":
                return R.drawable.s4;
            case "Тотемы":
                return R.drawable.s9;
        }
        return 0;
    }

    public static int getPlay(String s){
        if(s.equals("en"))
            return R.drawable.play;
        if (s.equals("ru"))
            return R.drawable.play_ru;
        return R.drawable.play;
    }

    public static int getPlayused(String s){
        if(s.equals("en"))
            return R.drawable.playused;
        if (s.equals("ru"))
            return R.drawable.playused_ru;
        return R.drawable.playused;
    }

    public static int getPlayBig(String language, String btn){
        if(language.equals("en")) {
            if (btn.equals("play")) {
                return R.drawable.playn;
            }
            if (btn.equals("playused")) {
                return R.drawable.playnused;
            }
        }

        if(language.equals("ru")) {
            if (btn.equals("play")) {
                return R.drawable.playn_ru;
            }
            if (btn.equals("playused")) {
                return R.drawable.playnused_ru;
            }
        }
        return R.drawable.play;
    }

    public static int getRules(String s){
        if(s.equals("en"))
            return R.drawable.rules;
        if (s.equals("ru"))
            return R.drawable.rules_ru;
        return R.drawable.rules;
    }

    public static int getRulesused(String s){
        if(s.equals("en"))
            return R.drawable.rulesused;
        if (s.equals("ru"))
            return R.drawable.rulesused_ru;
        return R.drawable.rulesused;
    }

    public static int getNext(String language, String btn){
        if(language.equals("en")) {
            if (btn.equals("next")) {
                return R.drawable.next;
            }
            if (btn.equals("nextused")) {
                return R.drawable.nextused;
            }
        }

        if(language.equals("ru")) {
            if (btn.equals("next")) {
                return R.drawable.next_ru;
            }
            if (btn.equals("nextused")) {
                return R.drawable.nextused_ru;
            }
        }
        return R.drawable.next;
    }

    public static int getDictionary(String language, String btn){
        if(language.equals("en"))
            switch (btn){
                case "general":
                    return R.drawable.general;
                case "generalused":
                    return R.drawable.generalused;
                case "music":
                    return R.drawable.music;
                case "musicused":
                    return R.drawable.musicused;
                case "movies":
                    return R.drawable.movies;
                case "moviesused":
                    return R.drawable.moviesused;
                case "literature":
                    return R.drawable.literature;
                case "literatureused":
                    return R.drawable.literatureused;
            }
        if(language.equals("ru"))
            switch (btn){
                case "general":
                    return R.drawable.general_ru;
                case "generalused":
                    return R.drawable.generalused_ru;
                case "music":
                    return R.drawable.music_ru;
                case "musicused":
                    return R.drawable.musicused_ru;
                case "movies":
                    return R.drawable.movies_ru;
                case "moviesused":
                    return R.drawable.moviesused_ru;
                case "literature":
                    return R.drawable.literature_ru;
                case "literatureused":
                    return R.drawable.literatureused_ru;
            }
        return R.drawable.rulesused;
    }

    public static int getAddteam(String language, String btn){
        if(language.equals("en")) {
            if (btn.equals("addteam"))
                return R.drawable.addteam;
            if (btn.equals("addteamused"))
                return R.drawable.addteamused;
        }
        if (language.equals("ru")) {
            if (btn.equals("addteam"))
                return R.drawable.addteam_ru;
            if (btn.equals("addteamused"))
                return R.drawable.addteamused_ru;
        }
        return R.drawable.addteam;
    }

    public static int getBackteam(String language){
        if(language.equals("en")) {
            return R.drawable.backteam;
        }
        if (language.equals("ru")) {
            return R.drawable.backteam_ru;
        }
        return R.drawable.backteam;
    }

    public static int getScoretitle(String language){
        if(language.equals("en")) {
            return R.drawable.score;
        }
        if (language.equals("ru")) {
            return R.drawable.score_ru;
        }
        return R.drawable.score;
    }

    public static int getLvl(String language, String btn){
        if(language.equals("en")) {
            switch (btn){
                case "easy":
                    return R.drawable.easy;
                case "easyused":
                    return R.drawable.easyused;
                case "medium":
                    return R.drawable.medium;
                case "medium_used":
                    return R.drawable.mediumused;
                case "hard":
                    return R.drawable.hard;
                case "hardused":
                    return R.drawable.hardused;
            }
        }
        if (language.equals("ru")) {
            switch (btn){
                case "easy":
                    return R.drawable.easy_ru;
                case "easyused":
                    return R.drawable.easyused_ru;
                case "medium":
                    return R.drawable.medium_ru;
                case "mediumused":
                    return R.drawable.mediumused_ru;
                case "hard":
                    return R.drawable.hard_ru;
                case "hardused":
                    return R.drawable.hardused_ru;
            }
        }
        return R.drawable.easy;
    }

    public static int getWinteamImg(String s) {
        switch (s) {
            case "Birds":
                return R.drawable.wn1;
            case "Carrots":
                return R.drawable.wn3;
            case "Wizards":
                return R.drawable.wn11;
            case "Potatoes":
                return R.drawable.wn6;
            case "Pterosauruses":
                return R.drawable.wn7;
            case "Blackholes":
                return R.drawable.wn2;
            case "Vikings":
                return R.drawable.wn10;
            case "Krakens":
                return R.drawable.wn5;
            case "Cupcakes":
                return R.drawable.wn4;
            case "Totems":
                return R.drawable.wn9;
        }
        switch (s) {
            case "Птицы":
                return R.drawable.wn1;
            case "Морковки":
                return R.drawable.wn3;
            case "Чародеи":
                return R.drawable.wn11;
            case "Картофелины":
                return R.drawable.wn6;
            case "Птерозавры":
                return R.drawable.wn7;
            case "Чёрные дыры":
                return R.drawable.wn2;
            case "Викинги":
                return R.drawable.wn10;
            case "Кракены":
                return R.drawable.wn5;
            case "Кексики":
                return R.drawable.wn4;
            case "Тотемы":
                return R.drawable.wn9;
        }
        return 0;
    }
}
