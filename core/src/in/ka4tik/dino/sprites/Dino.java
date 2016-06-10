package in.ka4tik.dino.sprites;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import in.ka4tik.dino.PlayState;

public class Dino {
    public static final float ASSETS_SCALING_FACTOR = 0.3f;
    private static final int GRAVITY = -500;
    private static final int FORWARD_VELOCITY = 200;
    private Vector3 position, velocity;
    private Rectangle bounds;
    private Texture texture;
    private Sound jump;

    public Dino(int x, int y) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        texture = new Texture("dino_ground.png");
        bounds = new Rectangle(x, y, texture.getWidth() * ASSETS_SCALING_FACTOR, texture.getHeight() * ASSETS_SCALING_FACTOR);
        jump = Gdx.audio.newSound(Gdx.files.internal("sfx_wing.ogg"));
    }

    public void update(float dt) {
        if (position.y >= PlayState.GROUND_HEIGHT + PlayState.DINO_OFFSET)
            velocity.add(0, GRAVITY * dt, 0);
        position.add(FORWARD_VELOCITY * dt, velocity.y * dt, 0);
        if (position.y < PlayState.GROUND_HEIGHT + PlayState.DINO_OFFSET)
            position.y = PlayState.GROUND_HEIGHT + PlayState.DINO_OFFSET;

        bounds.setPosition(position.x, position.y);
    }

    public Vector3 getPosition() {
        return position;
    }

    public Texture getTexture() {
        return texture;
    }

    public int getHeight() {
        return (int) (texture.getHeight() * ASSETS_SCALING_FACTOR);
    }

    public int getWidth() {
        return (int) (texture.getWidth() * ASSETS_SCALING_FACTOR);
    }

    public void jump() {
        if (position.y < PlayState.GROUND_HEIGHT + PlayState.DINO_OFFSET + 25) {
            velocity.y = 300;
            jump.play(0.5f);
        }
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void dispose() {
        texture.dispose();
        jump.dispose();
    }

}
