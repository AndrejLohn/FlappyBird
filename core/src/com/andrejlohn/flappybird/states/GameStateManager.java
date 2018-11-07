package com.andrejlohn.flappybird.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * The collection for the games states. This is implemented as a stack. When a new state is invoked,
 * it is placed on top of the stack and set active. All other states in the stack will be inactive
 * and waiting for further execution. Only the state on top of the stack is subject to further
 * updating.
 *
 * @version %I%, %G%
 * @see     Stack
 */
public class GameStateManager {
    private Stack<State> states;

    /**
     * Creates the game state manager by initializing a new stack of states.
     *
     * @see Stack
     */
    public GameStateManager(){
        states = new Stack<State>();
    }

    /**
     * Pushes a state on top of the stack of game states.
     *
     * @param state the new active game state
     * @see         Stack#push(Object)
     */
    public void push(State state){
        states.push(state);
    }

    /**
     * Removes the top state from the stack and deletes all its game objects.
     *
     * @see State#dispose()
     * @see Stack#pop()
     */
    public void pop(){
        states.pop().dispose();
    }

    /**
     * Pops the top state from the stack and immediately pushes a new state.
     *
     * @param state the new active game state
     * @see         #pop()
     * @see         #push(State)
     */
    public void set(State state){
        states.pop().dispose();
        states.push(state);
    }

    /**
     * Updates the active state with all its game objects according to the tome passed since the
     * last update.
     *
     * @param dt    the time passed since the last update
     * @see         State#update(float)
     */
    public void update(float dt){
        states.peek().update(dt);
    }

    /**
     * Renders the active states sprites to the screen.
     *
     * @param sb    the batch of game sprites
     * @see         State#render(SpriteBatch)
     */
    public void render(SpriteBatch sb){
        states.peek().render(sb);
    }
}
