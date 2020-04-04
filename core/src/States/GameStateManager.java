package States;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.Stack;

public class GameStateManager {
  private Stack<State> states = new Stack<State>();
  
  void push(State paramState) {
    this.states.push(paramState);
  }
  
  void render(SpriteBatch paramSpriteBatch) {
    ((State)this.states.peek()).render(paramSpriteBatch);
  }
  
  public void set(State paramState) {
    this.states.pop();
    this.states.push(paramState);
  }
  
  void update(float paramFloat) {
    ((State)this.states.peek()).update(paramFloat);
  }
}


/* Location:              C:\Users\nikol\Desktop\dex-tools-2.1-SNAPSHOT\kiki-dex2jar.jar!\States\GameStateManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */