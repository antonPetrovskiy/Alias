package game.com.alias;

import android.os.Environment;
import android.view.Menu;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileWorker {
    private File gameFile;
    private File teamsFile;
    private File scoreFile;
    private File wordsFile;
    private File allwordsFile;

    File sdPath;

    private static FileWorker instance;

    public static synchronized FileWorker getInstance() {
        if (instance == null) {
            instance = new FileWorker();
        }
        return instance;
    }

    private FileWorker(){
        removeAll();
        createFile();
    }

    public void createFile(){
        if (!Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {

            return;
        }

        sdPath = Environment.getExternalStorageDirectory();
        // добавляем свой каталог к пути
        sdPath = new File(sdPath.getAbsolutePath() + "/" + "Alias");
        // создаем каталог
        sdPath.mkdirs();
        // формируем объект File, который содержит путь к файлу
        gameFile = new File(sdPath, "game");
        wordsFile = new File(sdPath, "words");
        allwordsFile = new File(sdPath, "allwords");
        teamsFile = new File(sdPath, "teams");
        scoreFile = new File(sdPath, "score");
    }





    public int getTeamsNumber(){
        String sCurrentLine = "";
        ArrayList<String> list = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(getTeamsFile()));

            while ((sCurrentLine = br.readLine()) != null)
            {
                list.add(sCurrentLine);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list.size();
    }

    public void writeTeams(ArrayList<String> l){
        removeFile(getTeamsFile());
        try {
            // открываем поток для записи
            BufferedWriter bw = new BufferedWriter(new FileWriter(getTeamsFile(), true));
            for (String s : l) {
                bw.write(s  + "\n");
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            // открываем поток для записи
            BufferedWriter bw = new BufferedWriter(new FileWriter(scoreFile, true));
            for (String s : l) {
                bw.write("0"  + "\n");
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<String> readTeams(){
        String sCurrentLine = "";
        ArrayList<String> list = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(getTeamsFile()));
            while ((sCurrentLine = br.readLine()) != null)
            {
                list.add(sCurrentLine);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<String> readScore(){
        String sCurrentLine = "";
        ArrayList<String> list = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(scoreFile));
            while ((sCurrentLine = br.readLine()) != null)
            {
                list.add(sCurrentLine);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Positions for this function
     * 1 - write teams
     * 2 - write rounds
     * 3 - write last word
     * 4 - write allrounds
     * 5 - write nowround
     * 6 - write nowteam
     */
    public void createGame(String teams, String rounds, String lastw, String allrounds, String nowround, String nowteam){
        removeFile(getGameFile());
        removeFile(getScoreFile());
        removeFile(getWordsFile());
        removeFile(getallWordsFile());
        try {
            // открываем поток для записи
            BufferedWriter bw = new BufferedWriter(new FileWriter(getGameFile(), true));
            BufferedWriter bw1 = new BufferedWriter(new FileWriter(getScoreFile(), true));

            bw.write(teams  + "\n");
            bw.write(rounds  + "\n");
            bw.write(lastw  + "\n");
            bw.write(allrounds  + "\n");
            bw.write(nowround  + "\n");
            bw.write(nowteam  + "\n");

            for(int i = 0; i < Integer.parseInt(teams); i ++)
                bw1.write(0 + "\n");

            bw.close();
            bw1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Positions for this function
     * 1 - write teams
     * 2 - write rounds
     * 3 - write last word
     * 4 - write allrounds
     * 5 - write nowround
     * 6 - write nowteam
     */
    public void writeLineGame(int position, String s){
        ArrayList<String> list;
        list = getFileArray(gameFile);
        list.remove(position-1);
        list.add(position-1, s);


        removeFile(gameFile);
        gameFile = new File(sdPath, "game");

        try {
            // открываем поток для записи
            BufferedWriter bw = new BufferedWriter(new FileWriter(gameFile, true));
            for (int i = 0; i < list.size(); i++){
                bw.write(list.get(i)  + "\n");
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Positions for this function
     * 1 - write teams
     * 2 - write rounds
     * 3 - write last word
     * 4 - write allrounds
     * 5 - write nowround
     * 6 - write nowteam
     */
    public String readLineGame(int position){
        String line = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(gameFile));
            for(int i = 0; i < position; i++){
                line = br.readLine();
            }
            return line;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void writeWords(String s){
        ArrayList<String> list;
        list = getFileArray(wordsFile);
        list.add(s);

        ArrayList<String> list2;
        list2 = getFileArray(allwordsFile);
        list2.add(s);

        removeFile(getWordsFile());
        removeFile(getallWordsFile());
        wordsFile = new File(sdPath, "words");
        allwordsFile = new File(sdPath, "allwords");
        try {
            // открываем поток для записи
            BufferedWriter bw = new BufferedWriter(new FileWriter(wordsFile, true));
            BufferedWriter bw2 = new BufferedWriter(new FileWriter(allwordsFile, true));
            for (String str : list) {
                bw.write(str  + "\n");
            }
            for (String str : list2) {
                bw2.write(str  + "\n");
            }
            bw.close();
            bw2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeallWords(String s){
        ArrayList<String> list;
        list = getFileArray(allwordsFile);
        list.add(s);

        removeFile(getallWordsFile());
        allwordsFile = new File(sdPath, "allwords");
        try {
            // открываем поток для записи
            BufferedWriter bw = new BufferedWriter(new FileWriter(allwordsFile, true));
            for (String str : list) {
                bw.write(str  + "\n");
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> readallWords(){
        return getFileArray(allwordsFile);
    }

    public int getWords(){
        ArrayList<String> list;
        list = getFileArray(wordsFile);
        return list.size();
    }

    public void writeScore(int position, String s){
        ArrayList<String> list;
        list = getFileArray(scoreFile);
        String sc = list.get(position-1);
        int n = Integer.parseInt(sc);
        int m = Integer.parseInt(s);
        int l = n + m;
        list.set(position-1,l+"");


        removeFile(scoreFile);
        scoreFile = new File(sdPath, "score");
        try {
            // открываем поток для записи
            BufferedWriter bw = new BufferedWriter(new FileWriter(scoreFile, true));
            for (String str : list) {
                bw.write(str  + "\n");
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getTeamAt(int n){
        String line = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(getTeamsFile()));
            for(int i = 0; i < n; i++){
                line = br.readLine();
            }
            return line;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }





    public void removeFile(File f){
        f.delete();
    }

    public void removeAll(){
        if(gameFile!=null)
            gameFile.delete();
        if(wordsFile!=null)
            wordsFile.delete();
        if(teamsFile!=null)
            teamsFile.delete();
        if(scoreFile!=null)
            scoreFile.delete();
        if(allwordsFile!=null)
            allwordsFile.delete();
    }

    public File getGameFile(){
        return gameFile;
    }

    public File getWordsFile(){
        return wordsFile;
    }

    public File getallWordsFile(){
        return allwordsFile;
    }

    public File getTeamsFile(){
        return teamsFile;
    }

    public File getScoreFile(){
        return scoreFile;
    }

    public ArrayList<String> getFileArray(File f){
        String sCurrentLine = "";
        ArrayList<String> list = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            sCurrentLine = "";
            while ((sCurrentLine = br.readLine()) != null)
            {
                list.add(sCurrentLine);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

}
