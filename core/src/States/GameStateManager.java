package States;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public class GameStateManager {
    private Stack<State> states = new Stack<>();

    void push(State paramState) {
        this.states.push(paramState);
    }

    void render(SpriteBatch paramSpriteBatch) {
        this.states.peek().render(paramSpriteBatch);
    }

    public void set(State paramState) {
        this.states.pop();
        this.states.push(paramState);
    }

    void update(float paramFloat) {
        this.states.peek().update(paramFloat);
    }
}
