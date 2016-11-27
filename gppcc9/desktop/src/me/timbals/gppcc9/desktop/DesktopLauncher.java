package me.timbals.gppcc9.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import me.timbals.gppcc9.Game;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.width = Game.WIDTH;
        config.height = Game.HEIGHT;

		new LwjglApplication(Game.getInstance(), config);
	}
}
