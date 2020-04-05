package States;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainClass extends ApplicationAdapter {
    private final AdsController adsController;

    private SpriteBatch batch;

    private GameStateManager gsm;

    public MainClass(AdsController adsController) {
        this.adsController = adsController;
    }

    public void create() {
        this.batch = new SpriteBatch();
        GameStateManager gameStateManager = new GameStateManager();
        this.gsm = gameStateManager;
        gameStateManager.push(new LoadingScreen(gameStateManager, this.adsController));
    }

    public void dispose() {
        this.batch.dispose();
    }

    public void render() {
        this.gsm.update(Gdx.graphics.getDeltaTime());
        this.gsm.render(this.batch);
    }
}
