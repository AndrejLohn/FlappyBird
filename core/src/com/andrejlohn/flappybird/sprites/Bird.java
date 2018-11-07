package com.andrejlohn.flappybird.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * This class represents the player character with its position, upwards velocity, bounding box,
 * animation and sound effects. The character is subject to gravitational pull and reacts to player
 * input.
 *
 * @version &I&, &G&
 */
public class Bird {

    private static final int GRAVITY = -15;
    private static final int MOVEMENT = 100;

    private Vector3 position;
    private Vector3 velocity;
    private Rectangle bounds;
    private Texture texture;
    private Animation birdAnimation;
    private Sound flap;

    /**
     * Creates the player character at a specified starting position. All required values are set
     * appropriately. the characters starting velocity is zero.
     *
     * @param x the characters starting position x-coordinate
     * @param y the characters starting position y-coordinate
     * @see     Vector3
     * @see     Rectangle
     * @see     TextureRegion
     * @see     Animation
     * @see     Sound
     * @see     Texture
     * @see     Texture#getHeight()
     * @see     Texture#getWidth()
     * @see     com.badlogic.gdx.Files#internal(String)
     */
    public Bird(int x, int y) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0,0,0);
        texture = new Texture("birdAnimation.png");
        birdAnimation = new Animation(new TextureRegion(texture), 3, 0.5f);
        bounds = new Rectangle(x, y, texture.getWidth() / 3, texture.getHeight());
        flap = Gdx.audio.newSound(Gdx.files.internal("sfx_wing.ogg"));
    }

    /**
     * Updates the player character and its bounding box according to its velocity and the time
     * passed since the last update.
     * The character is moved forwards with a fixed speed, upwards based on its velocity and
     * downwards according to the gravitational pull. The character can not move downwards below
     * the ground level (y=0).
     *
     * @param dt    the time since the last update
     * @see         Animation#update(float)
     * @see         Vector3#add(Vector3)
     * @see         Vector3#scl(float)
     * @see         Rectangle#setPosition(float, float)
     */
    public void update(float dt) {
        birdAnimation.update(dt);

        if(position.y > 0) {
            velocity.add(0, GRAVITY, 0);
        }

        velocity.scl(dt);
        position.add(MOVEMENT * dt, velocity.y, 0);

        if(position.y < 0) {
            position.y = 0;
        }

        velocity.scl(1/dt);

        bounds.setPosition(position.x, position.y);
    }

    /**
     * Pushes the character upwards and plays a jump sound effect.
     */
    public void jump() {
        velocity.y = 250;
        flap.play(0.3f);
    }

    /**
     * Disposes all game objects not subject to the garbage collection. Prevents memory leaks.
     *
     * @see Texture#dispose()
     * @see Sound#dispose()
     */
    public void dispose() {
        texture.dispose();
        flap.dispose();
    }

    /**
     * Gets the characters bounding box.
     *
     * @return  the characters bounding box
     */
    public Rectangle getBounds() { return bounds; }

    /**
     * Gets the characters position vector.
     *
     * @return  the characters position
     * @see     Vector3
     */
    public Vector3 getPosition() {
        return position;
    }

    /**
     * Gets the current frame of the characters animation.
     *
     * @return  the characters current animation frame
     * @see     TextureRegion
     */
    public TextureRegion getTexture() {
        return birdAnimation.getFrame();
    }
}
