package States;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class State {
  GameStateManager gsm;
  
  protected State() {}
  
  public State(GameStateManager paramGameStateManager) {
    this.gsm = paramGameStateManager;
  }
  
  public abstract void dispose();
  
  public abstract void handleInput();
  
  public abstract void render(SpriteBatch paramSpriteBatch);
  
  public abstract void update(float paramFloat);
}


/* Location:              C:\Users\nikol\Desktop\dex-tools-2.1-SNAPSHOT\kiki-dex2jar.jar!\States\State.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */