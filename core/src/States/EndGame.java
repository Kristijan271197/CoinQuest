package States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.kristijanstojanoski.game.MainGame;


public class EndGame extends State {
    public static final String ADS_WATCHED = "adsWatched";
    private static final String HIGH_SCORE_CITY = "highScoreCity";
    private static final String HIGH_SCORE_DESERT = "highScoreDesert";
    private static final String LIFETIME_COINS_COLLECTED = "lifetimeCoinsCollected";
    private static final String LIFETIME_METRES = "lifetimeMetres";
    public static final String POWER_UPS_COLLECTED = "powerUpsCollected";
    private static final String STAGES_COMPLETED = "stagesCompleted";
    private static final String STAGES_PLAYED = "stagesPlayed";
    public static final String STORY_MODE_CITY = "storyModeCity";
    public static final String STORY_MODE_DESERT = "storyModeDesert";

    private int coinCount;
    private BitmapFont coinsHighScoreFont;
    private AdsController mAdsController;
    private MusicSounds musicSoundsObject;
    private Preferences prefs;
    private int score;
    private BitmapFont scoreFont;
    private boolean showTripleCoinsText = false;
    private Stage stage = new Stage(new ScreenViewport());
    private int stageNumber;
    private float story;
    private ImageButton tripleCoinsAdButton;
    private ImageButton.ImageButtonStyle tripleCoinsAdButtonTexture;
    private ImageButton.ImageButtonStyle tripleCoinsAdWaitButtonTexture;
    private BitmapFont tripleCoinsFont;

    public EndGame(GameStateManager paramGameStateManager, final AdsController adsController, final AssetManager manager, int coinCount, int score, float timer, final int stageNumber) {
        super(paramGameStateManager);
        Gdx.input.setInputProcessor(stage);
        this.mAdsController = adsController;
        prefs = Gdx.app.getPreferences("prefs");
        this.stageNumber = stageNumber;

        TextureAtlas mainMenuAtlas = manager.get("main_menu/main_menu.atlas", TextureAtlas.class);
        TextureAtlas sharedAtlas = manager.get("shared/shared.atlas", TextureAtlas.class);
        TextureAtlas mainGameAtlas = manager.get("main_game/main_game.atlas", TextureAtlas.class);
        TextureAtlas endGameAtlas = manager.get("end_game/end_game.atlas", TextureAtlas.class);

        story = timer;

        Achievements achievements = new Achievements();
        musicSoundsObject = new MusicSounds(manager);

        prefs.putInteger(LIFETIME_COINS_COLLECTED, prefs.getInteger(LIFETIME_COINS_COLLECTED, 0) + coinCount);
        prefs.putInteger(STAGES_PLAYED, prefs.getInteger(STAGES_PLAYED, 0) + 1);
        prefs.putFloat(LIFETIME_METRES, prefs.getFloat(LIFETIME_METRES, 0.0F) + timer);
        prefs.flush();

        if (stageNumber == 1) {
            if (prefs.getInteger(HIGH_SCORE_CITY, 0) < score) {
                prefs.putInteger(HIGH_SCORE_CITY, score);
                prefs.flush();
            }

            if (prefs.getFloat(STORY_MODE_CITY, 0.0F) < timer) {
                prefs.putFloat(STORY_MODE_CITY, timer);
                prefs.flush();
            }
        } else if (stageNumber == 2) {
            if (prefs.getInteger(HIGH_SCORE_DESERT, 0) < score) {
                prefs.putInteger(HIGH_SCORE_DESERT, score);
                prefs.flush();
            }

            if (prefs.getFloat(STORY_MODE_DESERT, 0.0F) < timer) {
                prefs.putFloat(STORY_MODE_DESERT, timer);
                prefs.flush();
            }
        }

        if (stageNumber == 1 && prefs.getInteger(Achievements.CITY_ATM_ACHIEVEMENT, 0) < 7) {
            achievements.checkCityAchievement(timer);
        } else if (stageNumber == 2 && prefs.getInteger(Achievements.DESERT_ATM_ACHIEVEMENT, 0) < 7)
            achievements.checkDesertAchievement(timer);

        if (prefs.getInteger(Achievements.COINS_ONE_GAME_ATM_ACHIEVEMENT, 0) < 6)
            achievements.checkCoinsOneGameAtmAchievement(coinCount);

        if (prefs.getInteger(Achievements.LIFETIME_COINS_COLLECTED_ATM_ACHIEVEMENT, 0) < 8)
            achievements.checkLifetimeCoinsCollected(prefs.getInteger(LIFETIME_COINS_COLLECTED, 0));

        if (prefs.getInteger(Achievements.STAGE_COMPLETED_ATM_ACHIEVEMENT, 0) < 1 && prefs.getFloat(STORY_MODE_CITY, 0.0F) > 100.0F)
            prefs.putInteger(STAGES_COMPLETED, prefs.getInteger(STAGES_COMPLETED, 0) + 1);

        if (prefs.getInteger(Achievements.STAGE_COMPLETED_ATM_ACHIEVEMENT, 0) < 2 && prefs.getFloat(STORY_MODE_DESERT, 0.0F) > 100.0F)
            prefs.putInteger(STAGES_COMPLETED, prefs.getInteger(STAGES_COMPLETED, 0) + 1);

        prefs.flush();

        if (prefs.getInteger(Achievements.STAGE_COMPLETED_ATM_ACHIEVEMENT, 0) < 2)
            achievements.checkStageCompleted(prefs.getInteger(STAGES_COMPLETED, 0));

        if (prefs.getInteger(Achievements.REVIVED_AFTER_DEATH_ATM_ACHIEVEMENT, 0) < 6)
            achievements.checkReviveAfterDeath(prefs.getInteger(MainGame.REVIVED_AFTER_DEATH, 0));

        if (prefs.getInteger(Achievements.STAGES_PLAYED_ATM_ACHIEVEMENT, 0) < 8)
            achievements.checkStagesPlayed(prefs.getInteger(STAGES_PLAYED, 0));

        if (prefs.getInteger(Achievements.POWER_UPS_COLLECTED_ATM_ACHIEVEMENT, 0) < 7)
            achievements.checkPowerUpsCollected(prefs.getInteger(POWER_UPS_COLLECTED, 0));

        if (prefs.getInteger(Achievements.ADS_WATCHED_ATM_ACHIEVEMENT, 0) < 5)
            achievements.checkAdsWatched(prefs.getInteger(ADS_WATCHED, 0));

        if (prefs.getInteger(Achievements.LIFETIME_METRES_ATM_ACHIEVEMENT, 0) < 6)
            achievements.checkLifetimeMetres(prefs.getFloat(LIFETIME_METRES, 0.0F));

        this.coinCount = coinCount;
        this.score = score;

        scoreFont = manager.get("font/font_end_game.fnt", BitmapFont.class);
        scoreFont.setColor(Color.BLACK);
        scoreFont.getData().setScale(worldXToScreenX(1.3F), worldYToScreenY(1.3F));
        scoreFont.getRegion().getRegionWidth();

        coinsHighScoreFont = manager.get("font/font_end_game_smaller.fnt", BitmapFont.class);
        coinsHighScoreFont.setColor(Color.BLACK);
        coinsHighScoreFont.getData().setScale(worldXToScreenX(1.2F), worldYToScreenY(1.2F));

        tripleCoinsFont = manager.get("font/font_scale_1.fnt", BitmapFont.class);
        tripleCoinsFont.setColor(Color.WHITE);
        tripleCoinsFont.getData().setScale(worldXToScreenX(1.0F), worldYToScreenY(1.0F));

        Image bg = new Image(sharedAtlas.findRegion("menu_background"));
        bg.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        bg.setPosition(0.0F, 0.0F);

        Image scoreTable = new Image(endGameAtlas.findRegion("score_table"));
        scoreTable.setSize(worldXToScreenX(290.0F), worldYToScreenY(350.0F));
        scoreTable.setPosition(worldXToScreenX(200.0F), worldYToScreenY(550.0F));

        Image playerImage = new Image(endGameAtlas.findRegion("player_image"));
        playerImage.setSize(worldXToScreenX(300.0F), worldYToScreenY(200.0F));
        playerImage.setPosition(worldXToScreenX(10.0F), worldYToScreenY(625.0F));

        if (prefs.getInteger(Shop.COSTUME_SELECTED_GAME) == Shop.PLAYER_NUMBER) {
            playerImage.setDrawable(new TextureRegionDrawable(new TextureRegion(endGameAtlas.findRegion("player_image"))));
            playerImage.setSize(worldXToScreenX(117.0F), worldYToScreenY(200.0F));
        } else if (this.prefs.getInteger(Shop.COSTUME_SELECTED_GAME) == Shop.ROBOT_NUMBER) {
            playerImage.setSize(worldXToScreenX(119.0F), worldYToScreenY(200.0F));
            playerImage.setDrawable(new TextureRegionDrawable(new TextureRegion(endGameAtlas.findRegion("robot_image"))));
        } else if (this.prefs.getInteger(Shop.COSTUME_SELECTED_GAME) == Shop.KNIGHT_NUMBER) {
            playerImage.setSize(worldXToScreenX(140.0F), worldYToScreenY(200.0F));
            playerImage.setDrawable(new TextureRegionDrawable(new TextureRegion(endGameAtlas.findRegion("knight_image"))));
        } else if (this.prefs.getInteger(Shop.COSTUME_SELECTED_GAME) == Shop.COWBOY_NUMBER) {
            playerImage.setSize(worldXToScreenX(131.0F), worldYToScreenY(200.0F));
            playerImage.setDrawable(new TextureRegionDrawable(new TextureRegion(endGameAtlas.findRegion("cowboy_image"))));
        } else if (this.prefs.getInteger(Shop.COSTUME_SELECTED_GAME) == Shop.COWGIRL_NUMBER) {
            playerImage.setSize(worldXToScreenX(124.0F), worldYToScreenY(200.0F));
            playerImage.setDrawable(new TextureRegionDrawable(new TextureRegion(endGameAtlas.findRegion("cowgirl_image"))));
        } else if (this.prefs.getInteger(Shop.COSTUME_SELECTED_GAME) == Shop.NINJA_MALE_NUMBER) {
            playerImage.setSize(worldXToScreenX(109.0F), worldYToScreenY(200.0F));
            playerImage.setDrawable(new TextureRegionDrawable(new TextureRegion(endGameAtlas.findRegion("ninja_male_image"))));
        } else if (this.prefs.getInteger(Shop.COSTUME_SELECTED_GAME) == Shop.NINJA_FEMALE_NUMBER) {
            playerImage.setSize(worldXToScreenX(120.0F), worldYToScreenY(200.0F));
            playerImage.setDrawable(new TextureRegionDrawable(new TextureRegion(endGameAtlas.findRegion("ninja_female_image"))));
        } else if (this.prefs.getInteger(Shop.COSTUME_SELECTED_GAME) == Shop.DINO_NUMBER) {
            playerImage.setSize(worldXToScreenX(188.0F), worldYToScreenY(200.0F));
            playerImage.setDrawable(new TextureRegionDrawable(new TextureRegion(endGameAtlas.findRegion("dino_image"))));
        }

        tripleCoinsAdButtonTexture = new ImageButton.ImageButtonStyle();
        tripleCoinsAdButtonTexture.up = new TextureRegionDrawable(new TextureRegion(endGameAtlas.findRegion("triple_coins_ad_button_unpressed")));
        tripleCoinsAdButtonTexture.down = new TextureRegionDrawable(new TextureRegion(endGameAtlas.findRegion("triple_coins_ad_button_pressed")));

        tripleCoinsAdWaitButtonTexture = new ImageButton.ImageButtonStyle();
        tripleCoinsAdWaitButtonTexture.up = new TextureRegionDrawable(new TextureRegion(mainGameAtlas.findRegion("ad_wait_button")));
        tripleCoinsAdWaitButtonTexture.down = new TextureRegionDrawable(new TextureRegion(mainGameAtlas.findRegion("ad_wait_button")));

        tripleCoinsAdButton = new ImageButton(tripleCoinsAdButtonTexture);
        tripleCoinsAdButton.setPosition(worldXToScreenX(100.0F), worldYToScreenY(280.0F));
        tripleCoinsAdButton.setSize(worldXToScreenX(300.0F), worldYToScreenY(118.0F));
        tripleCoinsAdButton.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                adsController.showRewardedVideo();
                musicSoundsObject.playButtonClick();
            }
        });


        ImageButton.ImageButtonStyle restartButtonStyle = new ImageButton.ImageButtonStyle();
        restartButtonStyle.up = new TextureRegionDrawable(new TextureRegion(mainGameAtlas.findRegion("main_game_restart_button_unpressed")));
        restartButtonStyle.down = new TextureRegionDrawable(new TextureRegion(mainGameAtlas.findRegion("main_game_restart_button_pressed")));

        ImageButton restartButton = new ImageButton(restartButtonStyle);
        restartButton.setPosition(worldXToScreenX(175.0F), worldYToScreenY(40.0F));
        restartButton.setSize(worldXToScreenX(150.0F), worldYToScreenY(150.0F));
        restartButton.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                gsm.set(new MainGame(gsm, adsController, manager, stageNumber));
                musicSoundsObject.playButtonClick();
                dispose();
            }
        });

        Image congratulationsWindow = new Image(sharedAtlas.findRegion("congratulations_window"));
        congratulationsWindow.setSize(worldXToScreenX(480.0F), worldYToScreenY(180.0F));
        congratulationsWindow.setPosition(worldXToScreenX(10.0F), worldYToScreenY(420.0F));

        ImageButton.ImageButtonStyle xButtonStyle = new ImageButton.ImageButtonStyle();
        xButtonStyle.up = new TextureRegionDrawable(new TextureRegion(sharedAtlas.findRegion("congratulations_window_x_button_unpressed")));
        xButtonStyle.down = new TextureRegionDrawable(new TextureRegion(sharedAtlas.findRegion("congratulations_window_x_button_pressed")));

        ImageButton xButtonCongratulationsWindow = new ImageButton(xButtonStyle);
        xButtonCongratulationsWindow.setPosition(worldXToScreenX(420.0F), worldYToScreenY(535.0F));
        xButtonCongratulationsWindow.setSize(worldXToScreenX(50.0F), worldYToScreenY(50.0F));
        xButtonCongratulationsWindow.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                stage.getActors().get(4).setVisible(false);
                stage.getActors().get(5).setVisible(false);
                showTripleCoinsText = false;
                musicSoundsObject.playButtonClick();
            }
        });

        ImageButton.ImageButtonStyle mainMenuButtonStyle = new ImageButton.ImageButtonStyle();
        mainMenuButtonStyle.up = new TextureRegionDrawable(new TextureRegion(mainGameAtlas.findRegion("main_game_menu_button_unpressed")));
        mainMenuButtonStyle.down = new TextureRegionDrawable(new TextureRegion(mainGameAtlas.findRegion("main_game_menu_button_pressed")));

        ImageButton mainMenuButton = new ImageButton(mainMenuButtonStyle);
        mainMenuButton.setPosition(worldXToScreenX(25.0F), worldYToScreenY(20.0F));
        mainMenuButton.setSize(worldXToScreenX(100.0F), worldYToScreenY(100.0F));
        mainMenuButton.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                gsm.set(new ChooseStageMenu(gsm, adsController, manager));
                musicSoundsObject.playButtonClick();
                musicSoundsObject.playBackgroundMusic();
                dispose();
            }
        });

        ImageButton.ImageButtonStyle shopButtonStyle = new ImageButton.ImageButtonStyle();
        shopButtonStyle.up = new TextureRegionDrawable(new TextureRegion(mainMenuAtlas.findRegion("shop_button_unpressed")));
        shopButtonStyle.down = new TextureRegionDrawable(new TextureRegion(mainMenuAtlas.findRegion("shop_button_pressed")));

        ImageButton shopButton = new ImageButton(shopButtonStyle);
        shopButton.setPosition(worldXToScreenX(375.0F), worldYToScreenY(20.0F));
        shopButton.setSize(worldXToScreenX(100.0F), worldYToScreenY(100.0F));
        shopButton.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                gsm.set(new Shop(gsm, adsController, manager));
                musicSoundsObject.playButtonClick();
                musicSoundsObject.getBackgroundMusic().play();
                dispose();
            }
        });

        Image sureQuitWindow = new Image(sharedAtlas.findRegion("sure_quit_window"));
        sureQuitWindow.setSize(worldXToScreenX(480.0F), worldYToScreenY(210.0F));
        sureQuitWindow.setPosition(worldXToScreenX(10.0F), worldYToScreenY(395.0F));

        ImageButton.ImageButtonStyle notQuitStyle = new ImageButton.ImageButtonStyle();
        notQuitStyle.up = new TextureRegionDrawable(new TextureRegion(sharedAtlas.findRegion("congratulations_window_x_button_unpressed")));
        notQuitStyle.down = new TextureRegionDrawable(new TextureRegion(sharedAtlas.findRegion("congratulations_window_x_button_pressed")));

        ImageButton notQuitButton = new ImageButton(notQuitStyle);
        notQuitButton.setPosition(worldXToScreenX(116.0F), worldYToScreenY(405.0F));
        notQuitButton.setSize(worldXToScreenX(75.0F), worldYToScreenY(75.0F));
        notQuitButton.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                stage.getActors().get(9).setVisible(false);
                stage.getActors().get(10).setVisible(false);
                stage.getActors().get(11).setVisible(false);
                musicSoundsObject.playButtonClick();
            }
        });

        ImageButton.ImageButtonStyle quitStyle = new ImageButton.ImageButtonStyle();
        quitStyle.up = new TextureRegionDrawable(new TextureRegion(mainGameAtlas.findRegion("story_continue_button_unpressed")));
        quitStyle.down = new TextureRegionDrawable(new TextureRegion(mainGameAtlas.findRegion("story_continue_button_pressed")));

        ImageButton quitButton = new ImageButton(quitStyle);
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
        stage.addActor(scoreTable);
        stage.addActor(playerImage);
        stage.addActor(tripleCoinsAdButton);
        stage.addActor(congratulationsWindow);
        stage.addActor(xButtonCongratulationsWindow);
        stage.addActor(restartButton);
        stage.addActor(mainMenuButton);
        stage.addActor(shopButton);
        stage.addActor(sureQuitWindow);
        stage.addActor(notQuitButton);
        stage.addActor(quitButton);
        stage.getActors().get(4).setVisible(false);
        stage.getActors().get(5).setVisible(false);
        stage.getActors().get(9).setVisible(false);
        stage.getActors().get(10).setVisible(false);
        stage.getActors().get(11).setVisible(false);

        adsController.showInterstitialAd();

    }

    public void render(SpriteBatch batch) {
        Gdx.input.setCatchKey(4, true);
        if (Gdx.input.isKeyPressed(4)) {
            stage.getActors().get(9).setVisible(true);
            stage.getActors().get(10).setVisible(true);
            stage.getActors().get(11).setVisible(true);
        }

        if(mAdsController.getInterstitialAdClosed()){
            stage.getActors().get(3).setTouchable(Touchable.enabled);
            stage.getActors().get(6).setTouchable(Touchable.enabled);
            stage.getActors().get(7).setTouchable(Touchable.enabled);
            stage.getActors().get(8).setTouchable(Touchable.enabled);
        } else{
            stage.getActors().get(3).setTouchable(Touchable.disabled);
            stage.getActors().get(6).setTouchable(Touchable.disabled);
            stage.getActors().get(7).setTouchable(Touchable.disabled);
            stage.getActors().get(8).setTouchable(Touchable.disabled);
        }


        boolean adLoaded = mAdsController.getAdLoaded();
        boolean rewardReceived = mAdsController.getRewardReceived();

        if (adLoaded) {
            tripleCoinsAdButton.setStyle(tripleCoinsAdButtonTexture);
            stage.getActors().get(3).setTouchable(Touchable.enabled);
        } else {
            tripleCoinsAdButton.setStyle(tripleCoinsAdWaitButtonTexture);
            stage.getActors().get(3).setTouchable(Touchable.disabled);
        }

        if (rewardReceived) {
            stage.getActors().get(3).setVisible(false);
            stage.getActors().get(4).setVisible(true);
            stage.getActors().get(5).setVisible(true);
            showTripleCoinsText = true;
            prefs.putInteger(Shop.COINS, coinCount + prefs.getInteger(Shop.COINS));
            prefs.putInteger(ADS_WATCHED, prefs.getInteger(ADS_WATCHED, 0) + 1);
            prefs.flush();
            mAdsController.setRewardReceived(false);
        }

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

        batch.begin();
        scoreFont.draw(batch, String.valueOf(score), worldXToScreenX(330.0F), worldYToScreenY(815.0F), worldXToScreenX(30.0F), 1, true);
        coinsHighScoreFont.draw(batch, String.valueOf(coinCount), worldXToScreenX(330.0F), worldYToScreenY(735.0F), worldXToScreenX(30.0F), 1, true);

        if (stageNumber == 1) {
            if (prefs.getFloat(STORY_MODE_CITY, 0.0F) < 100.0F)
                coinsHighScoreFont.draw(batch, (int) story + "%", worldXToScreenX(330.0F), worldYToScreenY(665.0F), worldXToScreenX(30.0F), 1, true);
            else
                coinsHighScoreFont.draw(batch, "100%", worldXToScreenX(330.0F), worldYToScreenY(665.0F), worldXToScreenX(30.0F), 1, true);
        } else if (stageNumber == 2) {
            if (prefs.getFloat(STORY_MODE_DESERT, 0.0F) < 100.0F)
                coinsHighScoreFont.draw(batch, (int) story + "%", worldXToScreenX(330.0F), worldYToScreenY(665.0F), worldXToScreenX(30.0F), 1, true);
            else
                coinsHighScoreFont.draw(batch, "100%", worldXToScreenX(330.0F), worldYToScreenY(665.0F), worldXToScreenX(30.0F), 1, true);
        }
        if (showTripleCoinsText)
            tripleCoinsFont.draw(batch, "You received " + coinCount * 2 + " coins", worldXToScreenX(100.0F), worldYToScreenY(510.0F), worldXToScreenX(300.0F), 1, false);

        batch.end();
    }

    public void update(float paramFloat) {
    }

    private float worldXToScreenX(float paramFloat) {
        return Gdx.graphics.getWidth() / 500.0F * paramFloat;
    }

    private float worldYToScreenY(float paramFloat) {
        return Gdx.graphics.getHeight() / 1000.0F * paramFloat;
    }

    public void dispose() {
        this.stage.dispose();
    }

    public void handleInput() {
    }
}
