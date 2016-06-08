package in.ka4tik.dino.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Cactus {

    private Vector3 position;
    private Rectangle bounds;
    private static final Texture texture = new Texture("cactus.png");

    public Cactus(int x, int y) {
        position = new Vector3(x, y, 0);
        bounds = new Rectangle(x, y, texture.getWidth(), texture.getHeight());
    }

    public Vector3 getPosition() {
        return position;
    }

    public Texture getTexture() {
        return texture;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void dispose() {
    }

}
