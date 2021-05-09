package fi.tuni.MindSlicer.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import fi.tuni.MindSlicer.Main;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Main.WORLD_WIDTH;
		config.height = Main.WORLD_HEIGHT;
		new LwjglApplication(new Main(), config);
	}
}
