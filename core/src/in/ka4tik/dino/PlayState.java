package in.ka4tik.dino;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import in.ka4tik.dino.sprites.Cactus;
import in.ka4tik.dino.sprites.Dino;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;


public class PlayState extends State {

    public static final int GROUND_HEIGHT = 20;
    public static final int DINO_OFFSET = 4;
    public static final float ASSETS_SCALING_FACTOR = 0.3f;
    private static final float VIEWPORT_SCALING_FACTOR = 0.5f;
    private Dino dino;
    private Texture ground;
    private Vector2 groundPos1, groundPos2;
    private List<Cactus> cactuses;
    private Random random;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, DinoGame.WIDTH * VIEWPORT_SCALING_FACTOR, DinoGame.HEIGHT * VIEWPORT_SCALING_FACTOR);
        ground = new Texture("ground.png");
        groundPos1 = new Vector2(cam.position.x - cam.viewportWidth / 2, GROUND_HEIGHT);
        groundPos2 = new Vector2((cam.position.x - cam.viewportWidth / 2) + ground.getWidth() * ASSETS_SCALING_FACTOR, GROUND_HEIGHT);
        random = new Random();
        dino = new Dino(0, GROUND_HEIGHT + DINO_OFFSET);
        cactuses = new ArrayList<Cactus>();
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched())
            dino.jump();
    }

    @Override
    public void update(float dt) {
        handleInput();
        updateView();
        cam.position.x = dino.getPosition().x + 80;

        dino.update(dt);
        cam.update();
        if (checkCollisions())
            gsm.set(new PlayState(gsm));
    }

    private boolean checkCollisions() {
        for (Cactus cactus : cactuses) {
            if (cactus.getBounds().overlaps(dino.getBounds())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();

        sb.draw(ground, groundPos1.x, groundPos1.y, ground.getWidth() * ASSETS_SCALING_FACTOR, ground.getHeight() * ASSETS_SCALING_FACTOR);
        sb.draw(ground, groundPos2.x, groundPos2.y, ground.getWidth() * ASSETS_SCALING_FACTOR, ground.getHeight() * ASSETS_SCALING_FACTOR);
        for (Cactus cactus : cactuses) {
            sb.draw(Cactus.getTexture(), cactus.getPosition().x, cactus.getPosition().y, cactus.getWidth(), cactus.getHeight());
        }
        sb.draw(dino.getTexture(), dino.getPosition().x, dino.getPosition().y, dino.getHeight(), dino.getHeight());

        sb.end();
    }

    @Override
    public void dispose() {

    }

    private void updateView() {
        for (Iterator<Cactus> iterator = cactuses.iterator(); iterator.hasNext(); ) {
            Cactus cactus = iterator.next();
            if (cam.position.x - (cam.viewportWidth / 2) > cactus.getPosition().x + Cactus.getTexture().getWidth() * ASSETS_SCALING_FACTOR) {
                iterator.remove();
            }
        }
        if (cam.position.x - (cam.viewportWidth / 2) > groundPos1.x + ground.getWidth() * ASSETS_SCALING_FACTOR) {
            groundPos1.add(ground.getWidth() * ASSETS_SCALING_FACTOR * 2, 0);
            generateCatuses(cactuses, groundPos1.x, Cactus.getTexture().getWidth() * ASSETS_SCALING_FACTOR);

        }
        if (cam.position.x - (cam.viewportWidth / 2) > groundPos2.x + ground.getWidth() * ASSETS_SCALING_FACTOR) {
            groundPos2.add(ground.getWidth() * ASSETS_SCALING_FACTOR * 2, 0);
            generateCatuses(cactuses, groundPos2.x, Cactus.getTexture().getWidth() * ASSETS_SCALING_FACTOR);


        }
    }


    private void generateCatuses(List<Cactus> cactuses, float offset, float cactusWidth) {

        int number = 1 + random.nextInt(2);
        for (int i = 0; i < number; i++) {
            float x = 0;
            int iterations = 0;
            while (iterations < 100) {
                x = offset + (int) (cactusWidth + random.nextInt((int) (cam.viewportWidth - 2 * cactusWidth)));
                boolean ok = true;
                for (Cactus cactus : cactuses) {
                    if (Math.abs(cactus.getPosition().x - x) <= 4 * cactusWidth)
                        ok = false;
                }
                iterations++;
                if (ok) break;
            }
            if (iterations > 100) continue;
            if (random.nextBoolean()) {
                cactuses.add(new Cactus((int) x, GROUND_HEIGHT + DINO_OFFSET, 1));

            } else {
                cactuses.add(new Cactus((int) x, GROUND_HEIGHT + DINO_OFFSET, 0.5f));
                cactuses.add(new Cactus((int) (x + cactusWidth / 2), GROUND_HEIGHT + DINO_OFFSET, 0.5f));
                cactuses.add(new Cactus((int) (x + cactusWidth), GROUND_HEIGHT + DINO_OFFSET, 0.5f));

            }
        }
    }

}
