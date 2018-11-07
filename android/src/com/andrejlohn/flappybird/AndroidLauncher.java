package com.andrejlohn.flappybird;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

/**
 * The launcher to run the game as an android application as provided by libGDX.
 *
 * @version %I%, %G%
 * @see     AndroidApplication
 * @see     AndroidApplicationConfiguration
 */
public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new FlappyDemo(), config);
	}
}
