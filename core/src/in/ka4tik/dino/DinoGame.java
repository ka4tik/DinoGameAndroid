package in.ka4tik.dino;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class DinoGame extends ApplicationAdapter {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 480;

    public static final String TITLE = "Dino";
    private GameStateManager gsm;
    private SpriteBatch batch;

    @Override
    public void create() {
        batch = new SpriteBatch();
        gsm = new GameStateManager();
        gsm.push(new MenuState(gsm));
        Gdx.gl.glClearColor(244f/255, 244f/255, 244f/255, 1); //grey
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gsm.update(Gdx.graphics.getDeltaTime());
        gsm.render(batch);
    }
}
