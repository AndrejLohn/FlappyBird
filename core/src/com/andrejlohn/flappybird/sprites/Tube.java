package com.andrejlohn.flappybird.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * This class represents a tube obstacle with its position, bounding boxes and textures. Each tube
 * object is actually a pair of tubes, one facing upwards the other downwards with a gap between
 * them. Collision of the player character with a tube will cause game loss. Tubes will repeatedly
 * be placed in the game world with the gap positioned at a random height.
 * By this version the tube gap has a fixed length and all tubes will be distributed with a fixed
 * and equal distance throughout the game world. The random height position of the tube gap is
 * capped by fixed FLUCTUATION and LOWEST_OPENING values.
 *
 * @version %I%, %G%
 */
public class Tube {

    public static final int TUBE_WIDTH = 52;

    private static final int FLUCTUATION = 130;
    private static final int TUBE_GAP = 100;
    private static final int LOWEST_OPENING = 120;

    // TODO make those static for reuse
    private Texture topTube, bottomTube;

    private Vector2 posTopTube, posBotTube;
    private Rectangle boundsTop, boundsBot;
    private Random rand;

    /**
     * Creates the tube and its bounding boxes based on a passed horizontal value.
     *
     * @param x the x-coordinate of the tube
     * @see     Rectangle
     * @see     Texture
     * @see     Vector2
     * @see     Random#nextInt()
     */
    public Tube(float x){
        topTube = new Texture("topTube.png");
        bottomTube = new Texture("bottomTube.png");
        rand = new Random();

        posTopTube = new Vector2(x, rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        posBotTube = new Vector2(x, posTopTube.y - TUBE_GAP - bottomTube.getHeight());

        boundsTop = new Rectangle(
                posTopTube.x,
                posTopTube.y,
                topTube.getWidth(),
                topTube.getHeight());
        boundsBot = new Rectangle(
                posBotTube.x,
                posBotTube.y,
                bottomTube.getWidth(),
                bottomTube.getHeight());
    }

    /**
     * Repositions a tube within the game world and randomly change its gap position.
     * This allows for the usage of only a small number of tubes at any given time while
     * maintaining the impression of an infinite amount of tubes.
     *
     * @param x the tubes new x-coordinate
     */
    public void reposition(float x) {
        posTopTube.set(x, rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        posBotTube.set(x, posTopTube.y - TUBE_GAP - bottomTube.getHeight());

        boundsTop.setPosition(posTopTube.x, posTopTube.y);
        boundsBot.setPosition(posBotTube.x, posBotTube.y);
    }

    /**
     * Checks for collision of the bounding boxes of the tube and the player character.
     * As the tube consists of two tubes with separate bounding boxes, two collision checks are
     * required.
     *
     * @param player    the player character bounding box
     * @return          <code>true</code> if the bounding boxes intersect each other
     *                  <code>false</code> else
     */
    public boolean collides(Rectangle player) {
        return player.overlaps(boundsTop) || player.overlaps(boundsBot);
    }

    /**
     * Disposes all game objects not subject to the garbage collection. Prevents memory leaks.
     *
     * @see Texture#dispose()
     */
    public void dispose() {
        topTube.dispose();
        bottomTube.dispose();
    }

    /**
     * Gets the texture of this tube objects top tube.
     *
     * @return  the top tubes texture
     * @see     Texture
     */
    public Texture getTopTube() {
        return topTube;
    }

    /**
     * Gets the texture of this tube objects bottom tube.
     *
     * @return  the bottom tube texture
     * @see     Texture
     */
    public Texture getBottomTube() {
        return bottomTube;
    }

    /**
     * Gets the top tubes position vector.
     *
     * @return  the top tube position
     * @see     Vector2
     */
    public Vector2 getPosTopTube() {
        return posTopTube;
    }

    /**
     * Gets the bottom tubes position vector.
     *
     * @return  the bottom tube position
     * @see     Vector2
     */
    public Vector2 getPosBotTube() {
        return posBotTube;
    }
}
