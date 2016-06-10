package in.ka4tik.dino.sprites;

import com.badlogic.gdx.graphics.Texture;

import java.util.List;

public class Animation {
    private List<Texture> frames;
    private float maxFrameTime;
    private float currentFrameTime;
    private int frameCount;
    private int frame;

    public Animation(List<Texture> frames, float cycleTime) {
        this.frames = frames;
        frameCount = frames.size();
        currentFrameTime = frames.size();
        maxFrameTime = cycleTime / frameCount;
        frame = 0;
    }

    public void update(float dt) {
        currentFrameTime += dt;
        if (currentFrameTime > maxFrameTime) {
            frame++;
            currentFrameTime = 0;
        }
        if (frame >= frameCount)
            frame = 0;
    }

    public Texture getFrame() {
        return frames.get(frame);
    }
}