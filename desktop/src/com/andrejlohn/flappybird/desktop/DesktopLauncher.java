package com.andrejlohn.flappybird.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.andrejlohn.flappybird.FlappyDemo;

/**
 * The launcher to run the game as a desktop application as provided by libGDX.
 *
 * @version %I%, %G%
 * @see     LwjglApplication
 * @see     LwjglApplicationConfiguration
 */
public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = FlappyDemo.WIDTH;
		config.height = FlappyDemo.HEIGHT;
		config.title = FlappyDemo.TITLE;
		new LwjglApplication(new FlappyDemo(), config);
	}
}
