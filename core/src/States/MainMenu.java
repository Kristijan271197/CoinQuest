package States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.Random;

public class MainMenu extends State {
    private TextureAtlas.AtlasRegion coin;
    private BitmapFont coinAndDiamondFont;
    private int coinGlobal;
    private int freeCoins;
    private boolean freeCoinsWindowShow;
    private AdsController mAdsController;
    private MusicSounds musicSoundsObject;
    private Preferences prefs;
    private Random random;
    private TextureAtlas.AtlasRegion ruby;
    private int rubyGlobal;
    private Stage stage;
    private BitmapFont windowText;

    MainMenu(GameStateManager paramGameStateManager, final AdsController adsController, final AssetManager manager) {
        super(paramGameStateManager);
        this.mAdsController = adsController;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        random = new Random(System.currentTimeMillis());
        Achievements achievements = new Achievements();
        TextureAtlas mainMenuAtlas = manager.get("main_menu/main_menu.atlas", TextureAtlas.class);
        TextureAtlas sharedAtlas = manager.get("shared/shared.atlas", TextureAtlas.class);
        TextureAtlas mainGameAtlas = manager.get("main_game/main_game.atlas", TextureAtlas.class);

        prefs = Gdx.app.getPreferences("prefs");
        prefs.putInteger("coins", 10000000);
        prefs.putInteger("ruby", 10000000);
        prefs.flush();
        MusicSounds musicSounds = new MusicSounds(manager);
        musicSoundsObject = musicSounds;
        musicSounds.playBackgroundMusic();

        if (prefs.getInteger(Achievements.ADS_WATCHED_ATM_ACHIEVEMENT, 0) < 5)
            achievements.checkAdsWatched(prefs.getInteger("adsWatched", 0));

        coinGlobal = prefs.getInteger("coins");
        rubyGlobal = prefs.getInteger("ruby");

        Image bg = new Image(sharedAtlas.findRegion("menu_background"));
        bg.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        bg.setPosition(0.0F, 0.0F);
        coin = sharedAtlas.findRegion("coin");
        ruby = sharedAtlas.findRegion("diamond");

        coinAndDiamondFont = manager.get("font/font_scale_07.fnt", BitmapFont.class);
        coinAndDiamondFont.setColor(Color.WHITE);
        coinAndDiamondFont.getData().setScale(worldXToScreenX(0.7F), worldYToScreenY(0.7F));
        windowText = manager.get("font/font_scale_1.fnt", BitmapFont.class);
        windowText.setColor(Color.WHITE);
        windowText.getData().setScale(worldXToScreenX(1.0F), worldYToScreenY(1.0F));

        Image gameName = new Image(mainMenuAtlas.findRegion("game_name"));
        gameName.setSize(worldXToScreenX(480.0F), worldYToScreenY(280.0F));
        gameName.setPosition(worldXToScreenX(10.0F), worldYToScreenY(600.0F));

        ImageButton.ImageButtonStyle playButtonStyle = new ImageButton.ImageButtonStyle();
        playButtonStyle.up = new TextureRegionDrawable(new TextureRegion(mainMenuAtlas.findRegion("play_button_unpressed")));
        playButtonStyle.down = new TextureRegionDrawable(new TextureRegion(mainMenuAtlas.findRegion("play_button_pressed")));

        ImageButton playButton = new ImageButton(playButtonStyle);
        playButton.setPosition(worldXToScreenX(150.0F), worldYToScreenY(380.0F));
        playButton.setSize(worldXToScreenX(200.0F), worldYToScreenY(200.0F));
        playButton.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                musicSoundsObject.playButtonClick();
                gsm.set(new ChooseStageMenu(gsm, MainMenu.this.mAdsController, manager));
                dispose();
            }
        });

        ImageButton.ImageButtonStyle shopButtonStyle = new ImageButton.ImageButtonStyle();
        shopButtonStyle.up = new TextureRegionDrawable(new TextureRegion(mainMenuAtlas.findRegion("shop_button_unpressed")));
        shopButtonStyle.down = new TextureRegionDrawable(new TextureRegion(mainMenuAtlas.findRegion("shop_button_pressed")));

        ImageButton shopButton = new ImageButton(shopButtonStyle);
        shopButton.setPosition(worldXToScreenX(175.0F), worldYToScreenY(180.0F));
        shopButton.setSize(worldXToScreenX(150.0F), worldYToScreenY(150.0F));
        shopButton.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                gsm.set(new Shop(gsm, adsController, manager));
                musicSoundsObject.playButtonClick();
                dispose();
            }
        });

        ImageButton.ImageButtonStyle freeCoinsStyle = new ImageButton.ImageButtonStyle();
        freeCoinsStyle.up = new TextureRegionDrawable(new TextureRegion(mainMenuAtlas.findRegion("free_coins_button_unpressed")));
        freeCoinsStyle.down = new TextureRegionDrawable(new TextureRegion(mainMenuAtlas.findRegion("free_coins_button_pressed")));

        ImageButton freeCoinsButton = new ImageButton(freeCoinsStyle);
        freeCoinsButton.setPosition(worldXToScreenX(20.0F), worldYToScreenY(40.0F));
        freeCoinsButton.setSize(worldXToScreenX(100.0F), worldYToScreenY(100.0F));
        freeCoinsButton.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                //adsController.showRewardedVideo();
                musicSoundsObject.playButtonClick();
            }
        });

        Image congratulationsWindow = new Image(new TextureRegionDrawable(new TextureRegion(sharedAtlas.findRegion("congratulations_window"))));
        congratulationsWindow.setSize(worldXToScreenX(480.0F), worldYToScreenY(180.0F));
        congratulationsWindow.setPosition(worldXToScreenX(10.0F), worldYToScreenY(410.0F));

        ImageButton.ImageButtonStyle xButtonStyle = new ImageButton.ImageButtonStyle();
        xButtonStyle.up = new TextureRegionDrawable(new TextureRegion(sharedAtlas.findRegion("congratulations_window_x_button_unpressed")));
        xButtonStyle.down = new TextureRegionDrawable(new TextureRegion(sharedAtlas.findRegion("congratulations_window_x_button_pressed")));

        ImageButton xButton = new ImageButton(xButtonStyle);
        xButton.setPosition(worldXToScreenX(420.0F), worldYToScreenY(525.0F));
        xButton.setSize(worldXToScreenX(50.0F), worldYToScreenY(50.0F));
        xButton.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                freeCoinsWindowShow = false;
                stage.getActors().get(5).setVisible(false);
                stage.getActors().get(6).setVisible(false);
                stage.getActors().get(1).setTouchable(Touchable.enabled);
                stage.getActors().get(2).setTouchable(Touchable.enabled);
                stage.getActors().get(3).setTouchable(Touchable.enabled);
                stage.getActors().get(4).setTouchable(Touchable.enabled);
                musicSoundsObject.playButtonClick();
            }
        });
        ImageButton.ImageButtonStyle spinningWheelStyle = new ImageButton.ImageButtonStyle();
        spinningWheelStyle.up = new TextureRegionDrawable(new TextureRegion(mainMenuAtlas.findRegion("spinning_wheel_button_unpressed")));
        spinningWheelStyle.down = new TextureRegionDrawable(new TextureRegion(mainMenuAtlas.findRegion("spinning_wheel_button_pressed")));

        ImageButton spinningWheelButton = new ImageButton(spinningWheelStyle);
        spinningWheelButton.setPosition(worldXToScreenX(140.0F), worldYToScreenY(40.0F));
        spinningWheelButton.setSize(worldXToScreenX(100.0F), worldYToScreenY(100.0F));
        spinningWheelButton.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                gsm.set(new SpinningWheel(gsm, adsController, manager));
                musicSoundsObject.playButtonClick();
                dispose();
            }
        });

        ImageButton.ImageButtonStyle objectivesStyle = new ImageButton.ImageButtonStyle();
        objectivesStyle.up = new TextureRegionDrawable(new TextureRegion(mainMenuAtlas.findRegion("objectives_button_unpressed")));
        objectivesStyle.down = new TextureRegionDrawable(new TextureRegion(mainMenuAtlas.findRegion("objectives_button_pressed")));

        ImageButton objectivesButton = new ImageButton(objectivesStyle);
        objectivesButton.setPosition(worldXToScreenX(260.0F), worldYToScreenY(40.0F));
        objectivesButton.setSize(worldXToScreenX(100.0F), worldYToScreenY(100.0F));
        objectivesButton.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                gsm.set(new Achievements(gsm, adsController, manager));
                musicSoundsObject.playButtonClick();
                dispose();
            }
        });

        ImageButton.ImageButtonStyle settingsStyle = new ImageButton.ImageButtonStyle();
        settingsStyle.up = new TextureRegionDrawable(new TextureRegion(mainMenuAtlas.findRegion("settings_button_unpressed")));
        settingsStyle.down = new TextureRegionDrawable(new TextureRegion(mainMenuAtlas.findRegion("settings_button_pressed")));

        ImageButton settingsButton = new ImageButton(settingsStyle);
        settingsButton.setPosition(worldXToScreenX(380.0F), worldYToScreenY(40.0F));
        settingsButton.setSize(worldXToScreenX(100.0F), worldYToScreenY(100.0F));
        settingsButton.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                musicSoundsObject.playButtonClick();
                gsm.set(new Settings(MainMenu.this.gsm, adsController, manager));
                dispose();
            }
        });

        Image sureQuitWindow = new Image(sharedAtlas.findRegion("sure_quit_window"));
        sureQuitWindow.setSize(worldXToScreenX(480.0F), worldYToScreenY(210.0F));
        sureQuitWindow.setPosition(worldXToScreenX(10.0F), worldYToScreenY(395.0F));

        ImageButton.ImageButtonStyle notQuitButtonStyle = new ImageButton.ImageButtonStyle();
        notQuitButtonStyle.up = new TextureRegionDrawable(new TextureRegion(sharedAtlas.findRegion("congratulations_window_x_button_unpressed")));
        notQuitButtonStyle.down = new TextureRegionDrawable(new TextureRegion(sharedAtlas.findRegion("congratulations_window_x_button_pressed")));

        ImageButton notQuitButton = new ImageButton(notQuitButtonStyle);
        notQuitButton.setPosition(worldXToScreenX(116.0F), worldYToScreenY(405.0F));
        notQuitButton.setSize(worldXToScreenX(75.0F), worldYToScreenY(75.0F));
        notQuitButton.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                stage.getActors().get(10).setVisible(false);
                stage.getActors().get(11).setVisible(false);
                stage.getActors().get(12).setVisible(false);
                musicSoundsObject.playButtonClick();
            }
        });

        ImageButton.ImageButtonStyle quitButtonStyle = new ImageButton.ImageButtonStyle();
        quitButtonStyle.up = new TextureRegionDrawable(new TextureRegion(mainGameAtlas.findRegion("story_continue_button_unpressed")));
        quitButtonStyle.down = new TextureRegionDrawable(new TextureRegion(mainGameAtlas.findRegion("story_continue_button_pressed")));

        ImageButton quitButton = new ImageButton(quitButtonStyle);
        quitButton.setPosition(worldXToScreenX(307.0F), worldYToScreenY(405.0F));
        quitButton.setSize(worldXToScreenX(75.0F), worldYToScreenY(75.0F));
        quitButton.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                musicSoundsObject.playButtonClick();
                Gdx.app.exit();
            }
        });

        stage.addActor(bg);
        stage.addActor(playButton);
        stage.addActor(shopButton);
        stage.addActor(objectivesButton);
        stage.addActor(freeCoinsButton);
        stage.addActor(congratulationsWindow);
        stage.addActor(xButton);
        stage.addActor(spinningWheelButton);
        stage.addActor(settingsButton);
        stage.addActor(gameName);
        stage.addActor(sureQuitWindow);
        stage.addActor(notQuitButton);
        stage.addActor(quitButton);
        stage.getActors().get(4).setVisible(false);
        stage.getActors().get(5).setVisible(false);
        stage.getActors().get(6).setVisible(false);
        stage.getActors().get(10).setVisible(false);
        stage.getActors().get(11).setVisible(false);
        stage.getActors().get(12).setVisible(false);
    }

    private float worldXToScreenX(float paramFloat) {
        return Gdx.graphics.getWidth() / 500.0F * paramFloat;
    }

    private float worldYToScreenY(float paramFloat) {
        return Gdx.graphics.getHeight() / 1000.0F * paramFloat;
    }

    public void dispose() {
        stage.dispose();
    }

    public void handleInput() {
    }

    public void render(SpriteBatch batch) {
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

        Gdx.input.setCatchKey(4, true);
        if (Gdx.input.isKeyPressed(4)) {
            stage.getActors().get(10).setVisible(true);
            stage.getActors().get(11).setVisible(true);
            stage.getActors().get(12).setVisible(true);
        }
//        boolean adLoaded = this.mAdsController.getAdLoaded();
//        boolean rewardReceived = this.mAdsController.getRewardReceived();

        batch.begin();
        batch.draw(coin, worldXToScreenX(10.0F), worldYToScreenY(960.0F), worldXToScreenX(25.0F), worldYToScreenY(25.0F));
        coinAndDiamondFont.draw(batch, String.valueOf(coinGlobal), worldXToScreenX(40.0F), worldYToScreenY(980.0F));
        batch.draw(ruby, worldXToScreenX(10.0F), worldYToScreenY(930.0F), worldXToScreenX(25.0F), worldYToScreenY(25.0F));
        coinAndDiamondFont.draw(batch, String.valueOf(rubyGlobal), worldXToScreenX(40.0F), worldYToScreenY(950.0F));
//        if (adLoaded)
//            this.stage.getActors().get(4).setVisible(true);
//
//        if (rewardReceived) {
//            freeCoins = (random.nextInt(9) + 2) * 10;
//            prefs.putInteger("coins", freeCoins + prefs.getInteger("coins"));
//            prefs.putInteger("adsWatched", prefs.getInteger("adsWatched", 0) + 1);
//            prefs.flush();
//            coinGlobal = prefs.getInteger("coins");
//            mAdsController.setRewardReceived(false);
//            stage.getActors().get(5).setVisible(true);
//            stage.getActors().get(6).setVisible(true);
//            stage.getActors().get(1).setTouchable(Touchable.disabled);
//            stage.getActors().get(2).setTouchable(Touchable.disabled);
//            stage.getActors().get(3).setTouchable(Touchable.disabled);
//            stage.getActors().get(4).setTouchable(Touchable.disabled);
//            freeCoinsWindowShow = true;
//        }
        if (freeCoinsWindowShow)
            windowText.draw(batch, "You received " + freeCoins + " coins", worldXToScreenX(90.0F), worldYToScreenY(500.0F));

        batch.end();
    }

    public void update(float paramFloat) {
        handleInput();
    }
}


/* Location:              C:\Users\nikol\Desktop\dex-tools-2.1-SNAPSHOT\kiki-dex2jar.jar!\States\MainMenu.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */