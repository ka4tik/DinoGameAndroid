package in.ka4tik.dino.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Cactus {

    public static final float ASSETS_SCALING_FACTOR = 0.3f;
    private static final Texture texture = new Texture("cactus.png");
    public boolean marked;
    private Vector3 position;
    private Rectangle bounds;
    private float scale;

    public Cactus(int x, int y, float scale) {
        position = new Vector3(x, y, 0);
        bounds = new Rectangle(x, y, texture.getWidth() * ASSETS_SCALING_FACTOR * scale, texture.getHeight() * ASSETS_SCALING_FACTOR * scale);
        this.scale = scale;
        this.marked = false;
    }

    public static Texture getTexture() {
        return texture;
    }

    public Vector3 getPosition() {
        return position;
    }

    public int getHeight() {
        return (int) (texture.getHeight() * scale * ASSETS_SCALING_FACTOR);
    }

    public int getWidth() {
        return (int) (texture.getWidth() * scale * ASSETS_SCALING_FACTOR);
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void dispose() {
    }

}
