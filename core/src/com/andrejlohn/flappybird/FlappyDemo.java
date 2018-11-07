package com.andrejlohn.flappybird;

import com.andrejlohn.flappybird.states.GameStateManager;
import com.andrejlohn.flappybird.states.MenuState;
import com.andrejlohn.flappybird.states.State;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * The games main class. Provides the necessary constants and objects to start and run the game.
 * An instance of this class is created by the systems respective launcher.
 *
 * @version %I%, %G%
 * @see     ApplicationAdapter
 */
public class FlappyDemo extends ApplicationAdapter {
	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;
	public static final String TITLE = "Flappy Bird";

    private GameStateManager gsm;
	private SpriteBatch batch;
	private Music music;

    /**
     * Sets up the games necessary objects. This is called on the games start.
     *
     * @see SpriteBatch
     * @see GameStateManager
     * @see Music
     * @see MenuState
     * @see ApplicationAdapter#create()
     * @see GameStateManager#push(State)
     * @see Music#setLooping(boolean)
     * @see Music#setVolume(float)
     */
    @Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
		music.setLooping(true);
		music.setVolume(0.1f);
		music.play();
        Gdx.gl.glClearColor(1, 0, 0, 1);
        gsm.push(new MenuState(gsm));
	}

    /**
     * Runs a game loop of updating all game objects and rendering them to the screen.
     *
     * @see ApplicationAdapter#render()
     * @see GameStateManager#update(float)
     * @see GameStateManager#render(SpriteBatch)
     */
    @Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}

    /**
     * Removes all game objects not subject to the garbage collection from memory.
     * Prevents memory leak.
     *
     * @see ApplicationAdapter#dispose()
     * @see SpriteBatch#dispose()
     * @see Music#dispose()
     */
    @Override
	public void dispose () {
		super.dispose();
	    batch.dispose();
	    music.dispose();
	}
}
