package com.andrejlohn.flappybird.sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * This class provides all necessary functionality to animate a two dimensional object given a
 * series of frames in a single linear texture.
 *
 * @version %I%, %G%
 */
public class Animation {

    private Array<TextureRegion> frames;
    private float maxFrameTime;
    private float currentFrameTime;
    private int frameCount;
    private int frame;

    /**
     * Creates the animation based on the given animation texture, the number of frames within this
     * texture and the time specified to complete whole animation.
     *
     * @param region        the animation texture
     * @param frameCount    the number of frames in the texture
     * @param cycleTime     the time required for one animation cycle
     * @see                 TextureRegion
     * @see                 TextureRegion#getRegionWidth()
     * @see                 TextureRegion#getRegionHeight()
     * @see                 Array#add(Object)
     */
    public Animation(TextureRegion region, int frameCount, float cycleTime) {
        frames = new Array<TextureRegion>();
        int frameWidth = region.getRegionWidth() / frameCount;

        for(int i=0; i<frameCount; i++) {
            frames.add(new TextureRegion(region, i * frameWidth, 0, frameWidth, region.getRegionHeight()));
        }

        this.frameCount = frameCount;
        maxFrameTime = cycleTime / frameCount;
        frame = 0;
    }

    /**
     * Cycles through the animation step by step based on the time passed since the last update.
     *
     * @param dt    the time since the last update
     */
    public void update(float dt) {
        currentFrameTime += dt;

        if(currentFrameTime > maxFrameTime) {
            frame++;
            currentFrameTime = 0;
        }

        if(frame >= frameCount) {
             frame = 0;
        }
    }

    /**
     * Gets the current frame of the animation.
     *
     * @return  the current animation frame
     * @see     TextureRegion
     */
    public TextureRegion getFrame() {
        return frames.get(frame);
    }
}
