package in.ka4tik.dino;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class HighScoreManager {

    public static HighScoreManager instance = new HighScoreManager();
    private Preferences prefs;

    private HighScoreManager() {
        prefs = Gdx.app.getPreferences("My Preferences");

    }

    public void saveHighScore(int score) {
        prefs.putInteger("score", score);
        prefs.flush();
    }

    public int getHighScore() {
        return prefs.getInteger("score", 0);
    }
}
