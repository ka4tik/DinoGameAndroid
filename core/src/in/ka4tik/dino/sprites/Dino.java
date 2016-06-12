package in.ka4tik.dino.sprites;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import in.ka4tik.dino.PlayState;

import java.util.Arrays;
import java.util.List;

public class Dino {
    public static final float ASSETS_SCALING_FACTOR = 0.3f;
    private static final int GRAVITY = -500;
    private static final int FORWARD_VELOCITY = 300;
    private static final int MAX_FORWARD_VELOCITY = 600;
    private static final int MAX_GRAVITY = -1000;

    private Vector3 position, velocity;
    private Rectangle bounds;
    private Texture dino_texture1, dino_texture2, dino_jump_texture;
    private Sound jump;
    private Animation dinoAnimation;

    public Dino(int x, int y) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        dino_texture1 = new Texture("dino_ground.png");
        dino_texture2 = new Texture("dino_ground_2.png");
        dino_jump_texture = new Texture("dino_jump.png");

        List<Texture> places = Arrays.asList(dino_texture1, dino_texture2);

        dinoAnimation = new Animation(places, 0.5f);
        bounds = new Rectangle(x, y, dino_texture1.getWidth() * ASSETS_SCALING_FACTOR, dino_texture1.getHeight() * ASSETS_SCALING_FACTOR);
        jump = Gdx.audio.newSound(Gdx.files.internal("sfx_wing.ogg"));
    }

    public void update(float dt, int score) {
        dinoAnimation.update(dt);
        if (position.y >= PlayState.GROUND_HEIGHT + PlayState.DINO_OFFSET)
            velocity.add(0, getCurrentGravity(score) * dt, 0);
        position.add(getCurrentForwardSpeed(score) *dt, velocity.y * dt, 0);
        if (position.y < PlayState.GROUND_HEIGHT + PlayState.DINO_OFFSET)
            position.y = PlayState.GROUND_HEIGHT + PlayState.DINO_OFFSET;

        bounds.setPosition(position.x, position.y);
    }

    public Vector3 getPosition() {
        return position;
    }

    public Texture getTexture() {
        if (velocity.y > 0) {
            return dino_jump_texture;
        }
        return dinoAnimation.getFrame();
    }

    public int getHeight() {
        return (int) (dino_texture1.getHeight() * ASSETS_SCALING_FACTOR);
    }

    public int getWidth() {
        return (int) (dino_texture1.getWidth() * ASSETS_SCALING_FACTOR);
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
        dino_texture1.dispose();
        dino_texture2.dispose();
        jump.dispose();
    }
    public int getCurrentForwardSpeed(int score){
        return (int) Math.min(MAX_FORWARD_VELOCITY, ((double)score / 25 + 1) * FORWARD_VELOCITY);
    }
    public int getCurrentGravity(int score){
        return (int) Math.min(MAX_GRAVITY, ((double)score / 25 + 1) * GRAVITY);
    }

}
