package fi.tuni.MindSlicer.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import fi.tuni.MindSlicer.main;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = main.WORLD_WIDTH;
		config.height = main.WORLD_HEIGHT;
		new LwjglApplication(new main(), config);
	}
}
