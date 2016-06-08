package in.ka4tik.dino;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import in.ka4tik.dino.sprites.Dino;


public class PlayState extends State {

    public static final int GROUND_HEIGHT = 75;
    public static final int DINO_OFFSET = 7;
    public static final float ASSETS_SCALING_FACTOR = 0.5f;
    private Dino dino;
    private Texture ground;
    private Vector2 groundPos1, groundPos2;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        //make viewport half of total screen
        cam.setToOrtho(false, DinoGame.WIDTH / 2, DinoGame.HEIGHT / 2);
        ground = new Texture("ground.png");
        groundPos1 = new Vector2(cam.position.x - cam.viewportWidth / 2, GROUND_HEIGHT);
        groundPos2 = new Vector2((cam.position.x - cam.viewportWidth / 2) + ground.getWidth() * ASSETS_SCALING_FACTOR, GROUND_HEIGHT);

        dino = new Dino(0, GROUND_HEIGHT + DINO_OFFSET);
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched())
            dino.jump();
    }

    @Override
    public void update(float dt) {
        handleInput();
        updateGround();
        cam.position.x = dino.getPosition().x + 80;

        dino.update(dt);
        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();

        sb.draw(ground, groundPos1.x, groundPos1.y, ground.getWidth() * ASSETS_SCALING_FACTOR, ground.getHeight() * ASSETS_SCALING_FACTOR);
        sb.draw(ground, groundPos2.x, groundPos2.y, ground.getWidth() * ASSETS_SCALING_FACTOR, ground.getHeight() * ASSETS_SCALING_FACTOR);

        sb.draw(dino.getTexture(), dino.getPosition().x, dino.getPosition().y, dino.getTexture().getHeight() * ASSETS_SCALING_FACTOR, dino.getTexture().getHeight() * ASSETS_SCALING_FACTOR);

        sb.end();
    }

    @Override
    public void dispose() {

    }

    private void updateGround() {
        if (cam.position.x - (cam.viewportWidth / 2) > groundPos1.x + ground.getWidth() * ASSETS_SCALING_FACTOR)
            groundPos1.add(ground.getWidth() * ASSETS_SCALING_FACTOR * 2, 0);
        if (cam.position.x - (cam.viewportWidth / 2) > groundPos2.x + ground.getWidth() * ASSETS_SCALING_FACTOR)
            groundPos2.add(ground.getWidth() * ASSETS_SCALING_FACTOR * 2, 0);
    }

}
