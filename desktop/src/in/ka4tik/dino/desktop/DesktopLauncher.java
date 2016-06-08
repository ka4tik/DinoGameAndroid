package in.ka4tik.dino.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import in.ka4tik.dino.DinoGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = DinoGame.WIDTH;
		config.height = DinoGame.HEIGHT;
		config.title = DinoGame.TITLE;
		new LwjglApplication(new DinoGame(), config);
	}
}
