package States;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class State {
    GameStateManager gsm;

    protected State() {
    }

    public State(GameStateManager paramGameStateManager) {
        this.gsm = paramGameStateManager;
    }

    public abstract void dispose();

    public abstract void handleInput();

    public abstract void render(SpriteBatch paramSpriteBatch);

    public abstract void update(float paramFloat);
}
