package com.andrejlohn.flappybird.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * Abstract class to represent a state of the game. A state is defined by the objects displayed
 * in the state and its behavior in respective to user input. The game states will be handled by
 * a game state manager.
 *
 * @version %I%, %G%
 */
public abstract class State {
    protected OrthographicCamera cam;
    protected Vector3 mouse;
    protected GameStateManager gsm;

    /**
     * Creates the state and initializes the game camera and mouse (for desktop application usage).
     * Makes the state aware of the game state manager.
     *
     * @param gsm   the applications game state manager
     * @see         GameStateManager
     * @see         OrthographicCamera
     * @see         Vector3
     */
    protected State(GameStateManager gsm) {
        this.gsm = gsm;
        cam = new OrthographicCamera();
        mouse = new Vector3();
    }

    /**
     * Provides the functionality to handle user input.
     */
    protected abstract void handleInput();

    /**
     * Updates the game states object according to the time passed since the last update.
     *
     * @param dt    the time since the last update
     */
    public abstract void update(float dt);

    /**
     * Renders all sprites of a given sprite batch to the screen.
     *
     * @param sb    the batch of game sprites
     */
    public abstract void render(SpriteBatch sb);

    /**
     * Disposes all game objects not subject to the garbage collection. Prevents memory leaks.
     */
    public abstract void dispose();
}
