package com.andrejlohn.flappybird.states;

import com.andrejlohn.flappybird.FlappyDemo;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;

/**
 * This state is the default start up state of the game as well as the state invoked after the
 * player looses the game. As defined by the state base class it carries the game state manager,
 * the camera and the mouse. In addition texture objects for the game background and buttons are
 * provided in this class. By the current version a simple screen touch will start the game.
 *
 * @version %I%, %G%
 * @see     State
 */
public class MenuState extends State {
    private Texture background;
    private Texture playBtn;

    /**
     * Creates the menu state. Sets up the game camera and initializes all required textures.
     *
     * @param gsm   the game state manager
     * @see         State
     * @see         Texture
     * @see         com.badlogic.gdx.graphics.OrthographicCamera#setToOrtho(boolean, float, float)
     */
    public MenuState(GameStateManager gsm) {
        super(gsm);

        cam.setToOrtho(
                false,
                FlappyDemo.WIDTH/2,
                FlappyDemo.HEIGHT/2);
        background = new Texture("bg.png");
        playBtn = new Texture("playBtn.png");
    }

    /**
     * Handles user input in the game menu. By the current version only a simple screen touch is
     * recognized and will start the game by setting a new play state in the game state manager.
     *
     * @see Gdx#input#justTouched()
     * @see GameStateManager#set(State)
     */
    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
        }
    }

    /**
     * Updates the menu state according to the time since the last update and the user input.
     *
     * @param dt    the time since the last update
     * @see         #handleInput()
     */
    @Override
    public void update(float dt) {
        handleInput();
    }

    /**
     * Renders all sprites of a given sprite batch to the screen with respect to the camera
     * settings.
     *
     * @param sb    the batch of game sprites
     * @see         com.badlogic.gdx.graphics.OrthographicCamera#combined
     * @see         SpriteBatch#setProjectionMatrix(Matrix4)
     * @see         SpriteBatch#begin()
     * @see         SpriteBatch#end()
     * @see         SpriteBatch#draw(Texture, float, float)
     */
    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, 0, 0);
        sb.draw(playBtn,
                cam.position.x - playBtn.getWidth() / 2,
                cam.position.y);
        sb.end();
    }

    /**
     * Disposes all game objects not subject to the garbage collection. Prevents memory leaks.
     *
     * @see State#dispose()
     * @see Texture#dispose()
     */
    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
    }
}
