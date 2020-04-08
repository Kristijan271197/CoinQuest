package com.kristijanstojanoski.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.Random;

import States.Achievements;
import States.AdsController;
import States.ChooseStageMenu;
import States.EndGame;
import States.GameStateManager;
import States.MusicSounds;
import States.Settings;
import States.Shop;
import States.State;

public class MainGame extends State {
    private static final String FIRST_TIME_TUTORIAL = "firstTimeTutorial";
    private static final String HEALED_FROM_STONES = "healedFromStones";
    public static final String REVIVED_AFTER_DEATH = "revivedAfterDeath";

    private Achievements achievementsObject;
    private TextureAtlas.AtlasRegion[] background = new TextureAtlas.AtlasRegion[5];
    private float[] bgCords;
    private Bomb bombObject;
    private boolean bombsGo = false;
    private TextureAtlas.AtlasRegion coin;
    private CoinMagnet coinMagnetObject;
    private Coin coinObject;
    private CoinRush coinRushObject;
    private int coinRushUpgrade;
    private int coinSpawnUpgrade;
    private float collectiblesChance;
    private int collectiblesChoice;
    private float collectiblesTimer = 0.0F;
    private ImageButton continueWithAdsButton;
    private ImageButton.ImageButtonStyle continueWithAdsButtonStyle;
    private ImageButton.ImageButtonStyle continueWithAdsWaitButtonStyle;
    private int continueWithDiamonds = 1;
    private float dt = Gdx.graphics.getDeltaTime();
    private boolean firstArrowsAppearanceGreen = false;
    private boolean firstArrowsAppearanceRed = false;
    private boolean firstArrowsAppearanceYellow = false;
    private boolean firstTimeArrowsGreen = true;
    private boolean firstTimeArrowsRed = true;
    private boolean firstTimeArrowsYellow = true;
    private int gameState = -1;
    private boolean greenGo = false;
    private AdsController mAdsController;
    private int magnetUpgrade;
    private AssetManager manager;
    private ImageButton musicButton;
    private ImageButton.ImageButtonStyle musicButtonStyle;
    private MusicSounds musicSoundsObject;
    private ImageButton.ImageButtonStyle noMusicButtonStyle;
    private ImageButton.ImageButtonStyle noSoundButtonStyle;
    private float normalChance;
    private boolean onceCoinRush;
    private boolean onceMagnet;
    private boolean onceShield;
    private boolean pauseGame;
    private Player playerObject;
    private Preferences prefs;
    private Random random;
    private Rock rockObject;
    private Rocket rocketObject;
    private boolean rocketsGo = false;
    private boolean rocksGo = true;
    private int score = 0;
    private TextureAtlas.AtlasRegion scoreAndCoinBackground;
    private BitmapFont scoreAndCoinFont;
    private float scoreTimer = 0.0F;
    private final float screenWidth = worldXToScreenX(500.0F);
    private ShapeRenderer shapeRenderer;
    private Shield shieldObject;
    private int shieldUpgrade;
    private boolean showDiamondsCount = true;
    private boolean showEndStory;
    private boolean showScoreAndCoinLabel = false;
    private ImageButton soundButton;
    private ImageButton.ImageButtonStyle soundButtonStyle;
    private boolean spikesGo = false;
    private Spikes spikesObject;
    private Stage stage;
    private int stageNumber;
    private int storyEndNumber = 0;
    private Image storyEndSecond;
    private int storyStartNumber = 0;
    private Image storyStartSecond;
    private float timerGame = 0.0F;
    private float timerObjects = 0.0F;
    private int twoTimesAd = 0;
    private Villain villainObject;
    private boolean yellowGo = false;
    private Label label1;
    private TextureAtlas mainGameAtlas;
    private boolean musicOnce = true;

    public MainGame(final GameStateManager gsm, final AdsController adsController, final AssetManager manager, final int stageNumber) {
        super(gsm);
        this.manager = manager;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        mAdsController = adsController;
        this.stageNumber = stageNumber;
        prefs = Gdx.app.getPreferences("prefs");

        TextureAtlas mainMenuAtlas = manager.get("main_menu/main_menu.atlas", TextureAtlas.class);
        TextureAtlas sharedAtlas = manager.get("shared/shared.atlas", TextureAtlas.class);
        mainGameAtlas = manager.get("main_game/main_game.atlas", TextureAtlas.class);
        musicSoundsObject = new MusicSounds(manager);

        if (stageNumber == 1) {
            if (prefs.getBoolean(Settings.NIGHT_MODE, false)) {
                background[0] = mainGameAtlas.findRegion("city_background_night", 1);
                background[1] = mainGameAtlas.findRegion("city_background_night", 2);
                background[2] = mainGameAtlas.findRegion("city_background_night", 3);
                background[3] = mainGameAtlas.findRegion("city_background_night", 4);
                background[4] = mainGameAtlas.findRegion("city_background_night", 1);
            } else {
                background[0] = mainGameAtlas.findRegion("city_background", 1);
                background[1] = mainGameAtlas.findRegion("city_background", 2);
                background[2] = mainGameAtlas.findRegion("city_background", 3);
                background[3] = mainGameAtlas.findRegion("city_background", 4);
                background[4] = mainGameAtlas.findRegion("city_background", 1);
            }
        } else if (stageNumber == 2) {
            if (prefs.getBoolean(Settings.NIGHT_MODE, false)) {
                background[0] = mainGameAtlas.findRegion("desert_background_night", 1);
                background[1] = mainGameAtlas.findRegion("desert_background_night", 2);
                background[2] = mainGameAtlas.findRegion("desert_background_night", 3);
                background[3] = mainGameAtlas.findRegion("desert_background_night", 4);
                background[4] = mainGameAtlas.findRegion("desert_background_night", 1);
            } else {
                background[0] = mainGameAtlas.findRegion("desert_background", 1);
                background[1] = mainGameAtlas.findRegion("desert_background", 2);
                background[2] = mainGameAtlas.findRegion("desert_background", 3);
                background[3] = mainGameAtlas.findRegion("desert_background", 4);
                background[4] = mainGameAtlas.findRegion("desert_background", 1);
            }

        }

        bgCords = new float[5];
        for (int i = 0; i < 5; i++)
            bgCords[i] = (int) worldXToScreenX((i * 500));

        shieldUpgrade = 0;
        magnetUpgrade = 0;
        coinRushUpgrade = 0;
        coinSpawnUpgrade = 0;

        if (prefs.getInteger(Shop.COSTUME_SELECTED_GAME) == Shop.ROBOT_NUMBER)
            shieldUpgrade = 5;
        else if (prefs.getInteger(Shop.COSTUME_SELECTED_GAME) == Shop.KNIGHT_NUMBER)
            coinRushUpgrade = 5;
        else if (prefs.getInteger(Shop.COSTUME_SELECTED_GAME) == Shop.NINJA_MALE_NUMBER)
            magnetUpgrade = 5;
        else if (prefs.getInteger(Shop.COSTUME_SELECTED_GAME) == Shop.NINJA_FEMALE_NUMBER)
            coinSpawnUpgrade = 5;
        else if (prefs.getInteger(Shop.COSTUME_SELECTED_GAME) == Shop.DINO_NUMBER) {
            shieldUpgrade = 5;
            magnetUpgrade = 5;
            coinRushUpgrade = 5;
        }

        Image storyStartFirst = new Image(new TextureRegionDrawable(new TextureRegion(mainGameAtlas.findRegion("story_city_start_first"))));
        storyStartFirst.setSize(worldXToScreenX(501.0F), worldYToScreenY(1001.0F));
        storyStartFirst.setPosition(worldXToScreenX(-1.0F), worldYToScreenY(-1.0F));
        if (stageNumber == 2)
            storyStartFirst.setDrawable(new TextureRegionDrawable(new TextureRegion(manager.get("story_city_start_first.png", Texture.class))));

        storyStartSecond = new Image(new TextureRegionDrawable(new TextureRegion(mainGameAtlas.findRegion("story_city_start_second"))));
        storyStartSecond.setSize(worldXToScreenX(501.0F), worldYToScreenY(1001.0F));
        storyStartSecond.setPosition(worldXToScreenX(-1.0F), worldYToScreenY(-1.0F));
        if (stageNumber == 2)
            storyStartSecond.setDrawable(new TextureRegionDrawable(new TextureRegion(manager.get("story_city_start_second.png", Texture.class))));

        Image storyEndFirst = new Image(new TextureRegionDrawable(new TextureRegion(manager.get("story_city_end_first.png", Texture.class))));
        storyEndFirst.setSize(worldXToScreenX(500.0F), worldYToScreenY(1000.0F));
        storyEndFirst.setPosition(worldXToScreenX(0.0F), worldYToScreenY(0.0F));
        if (stageNumber == 2)
            storyEndFirst.setDrawable(new TextureRegionDrawable(new TextureRegion(manager.get("story_city_end_first.png", Texture.class))));

        storyEndSecond = new Image(new TextureRegionDrawable(new TextureRegion(manager.get("story_city_end_second.png", Texture.class))));
        storyEndSecond.setSize(worldXToScreenX(500.0F), worldYToScreenY(1000.0F));
        storyEndSecond.setPosition(worldXToScreenX(0.0F), worldYToScreenY(0.0F));
        if (stageNumber == 2)
            storyEndSecond.setDrawable(new TextureRegionDrawable(new TextureRegion(manager.get("story_city_end_second.png", Texture.class))));

        ImageButton.ImageButtonStyle storyRightStyle = new ImageButton.ImageButtonStyle();
        storyRightStyle.up = new TextureRegionDrawable(new TextureRegion(mainGameAtlas.findRegion("story_right_button_unpressed")));
        storyRightStyle.down = new TextureRegionDrawable(new TextureRegion(mainGameAtlas.findRegion("story_right_button_pressed")));

        ImageButton storyRightButton = new ImageButton(storyRightStyle);
        storyRightButton.setPosition(worldXToScreenX(405.0F), worldYToScreenY(20.0F));
        storyRightButton.setSize(worldXToScreenX(75.0F), worldYToScreenY(75.0F));
        storyRightButton.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                musicSoundsObject.playButtonClick();
                if (showEndStory) {
                    if (storyEndNumber == 3) {
                        stage.getActors().get(15).setVisible(false);
                        stage.getActors().get(16).setVisible(false);
                        stage.getActors().get(17).setVisible(false);
                        stage.getActors().get(18).setVisible(true);
                        stage.getActors().get(19).setVisible(true);
                        stage.getActors().get(20).setVisible(true);
                        stage.getActors().get(27).setVisible(false);
                        stage.getActors().get(0).setVisible(true);
                        stage.getActors().get(1).setVisible(false);
                        storyEndNumber = storyEndNumber + 1;
                    } else
                        storyEndNumber = storyEndNumber + 1;
                } else if (MainGame.this.storyStartNumber == 3) {
                    stage.getActors().get(13).setVisible(false);
                    stage.getActors().get(16).setVisible(false);
                    stage.getActors().get(17).setVisible(false);
                    stage.getActors().get(27).setVisible(false);
                    storyStartNumber = storyStartNumber + 1;

                    if (stageNumber == 1) {
                        if (prefs.getBoolean(FIRST_TIME_TUTORIAL, true)) {
                            stage.getActors().get(10).setVisible(true);
                            stage.getActors().get(11).setVisible(true);
                        } else
                            gameState = 0;
                    } else if (stageNumber == 2)
                        gameState = 0;
                } else
                    storyStartNumber = storyStartNumber + 1;
            }
        });

        ImageButton.ImageButtonStyle storyLeftButtonStyle = new ImageButton.ImageButtonStyle();
        storyLeftButtonStyle.up = new TextureRegionDrawable(new TextureRegion(sharedAtlas.findRegion("back_button_unpressed")));
        storyLeftButtonStyle.down = new TextureRegionDrawable(new TextureRegion(sharedAtlas.findRegion("back_button_pressed")));

        ImageButton storyLeftButton = new ImageButton(storyLeftButtonStyle);
        storyLeftButton.setPosition(worldXToScreenX(20), worldYToScreenY(20));
        storyLeftButton.setSize(worldXToScreenX(75), worldYToScreenY(75));
        storyLeftButton.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                musicSoundsObject.playButtonClick();
                if (showEndStory)
                    storyEndNumber = storyEndNumber - 1;
                else
                    storyStartNumber = storyStartNumber - 1;
            }
        });

        ImageButton.ImageButtonStyle skipButtonStyle = new ImageButton.ImageButtonStyle();
        skipButtonStyle.up = new TextureRegionDrawable(new TextureRegion(mainGameAtlas.findRegion("skip_story_button_unpressed")));
        skipButtonStyle.down = new TextureRegionDrawable(new TextureRegion(mainGameAtlas.findRegion("skip_story_button_pressed")));

        ImageButton skipButton = new ImageButton(skipButtonStyle);
        skipButton.setPosition(worldXToScreenX(155), worldYToScreenY(20));
        skipButton.setSize(worldXToScreenX(190), worldYToScreenY(75));
        skipButton.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                musicSoundsObject.playButtonClick();
                if (showEndStory) {
                    stage.getActors().get(14).setVisible(false);
                    stage.getActors().get(15).setVisible(false);
                    stage.getActors().get(16).setVisible(false);
                    stage.getActors().get(17).setVisible(false);
                    stage.getActors().get(18).setVisible(true);
                    stage.getActors().get(19).setVisible(true);
                    stage.getActors().get(20).setVisible(true);
                    stage.getActors().get(27).setVisible(false);
                    stage.getActors().get(0).setVisible(true);
                    stage.getActors().get(1).setVisible(false);
                    storyEndNumber = 4;
                } else {
                    stage.getActors().get(12).setVisible(false);
                    stage.getActors().get(13).setVisible(false);
                    stage.getActors().get(16).setVisible(false);
                    stage.getActors().get(17).setVisible(false);
                    stage.getActors().get(27).setVisible(false);
                    storyStartNumber = 4;
                    if (stageNumber == 1) {
                        if (prefs.getBoolean(FIRST_TIME_TUTORIAL, true)) {
                            stage.getActors().get(10).setVisible(true);
                            stage.getActors().get(11).setVisible(true);
                        } else
                            gameState = 0;
                    } else if (stageNumber == 2)
                        gameState = 0;
                }
            }
        });

        Image mainGameTutorial = new Image(new TextureRegionDrawable(new TextureRegion(mainGameAtlas.findRegion("main_game_tutorial"))));
        mainGameTutorial.setSize(worldXToScreenX(450.0F), worldYToScreenY(625.0F));
        mainGameTutorial.setPosition(worldXToScreenX(25.0F), worldYToScreenY(187.5F));

        ImageButton.ImageButtonStyle tutorialContinueButtonStyle = new ImageButton.ImageButtonStyle();
        tutorialContinueButtonStyle.up = new TextureRegionDrawable(new TextureRegion(mainGameAtlas.findRegion("story_continue_button_unpressed")));
        tutorialContinueButtonStyle.down = new TextureRegionDrawable(new TextureRegion(mainGameAtlas.findRegion("story_continue_button_pressed")));

        ImageButton tutorialContinueButton = new ImageButton(tutorialContinueButtonStyle);
        tutorialContinueButton.setPosition(worldXToScreenX(380.0F), worldYToScreenY(200.0F));
        tutorialContinueButton.setSize(worldXToScreenX(75.0F), worldYToScreenY(75.0F));
        tutorialContinueButton.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                musicSoundsObject.playButtonClick();
                stage.getActors().get(10).setVisible(false);
                stage.getActors().get(11).setVisible(false);
                gameState = 0;
                prefs.putBoolean(FIRST_TIME_TUTORIAL, false);
                prefs.flush();
            }
        });

        Image mainGamePauseDarkBackground = new Image(new TextureRegionDrawable(new TextureRegion(mainGameAtlas.findRegion("main_game_pause_dark_background"))));
        mainGamePauseDarkBackground.setColor(255.0F, 255.0F, 255.0F, 0.5F);
        mainGamePauseDarkBackground.setSize(worldXToScreenX(500.0F), worldYToScreenY(1000.0F));
        mainGamePauseDarkBackground.setPosition(0.0F, 0.0F);

        ImageButton.ImageButtonStyle playButtonStyle = new ImageButton.ImageButtonStyle();
        playButtonStyle.up = new TextureRegionDrawable(new TextureRegion(mainMenuAtlas.findRegion("play_button_unpressed")));
        playButtonStyle.down = new TextureRegionDrawable(new TextureRegion(mainMenuAtlas.findRegion("play_button_pressed")));

        ImageButton playButton = new ImageButton(playButtonStyle);
        playButton.setPosition(worldXToScreenX(50.0F), worldYToScreenY(525.0F));
        playButton.setSize(worldXToScreenX(100.0F), worldYToScreenY(100.0F));
        playButton.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                musicSoundsObject.playButtonClick();
                pauseGame = false;
                stage.getActors().get(0).setVisible(false);
                stage.getActors().get(1).setVisible(true);
                stage.getActors().get(2).setVisible(false);
                stage.getActors().get(3).setVisible(false);
                stage.getActors().get(4).setVisible(false);
                stage.getActors().get(5).setVisible(false);
                stage.getActors().get(21).setVisible(false);
                if (stageNumber == 1)
                    musicSoundsObject.getCityMusic().play();
                else if (stageNumber == 2)
                    musicSoundsObject.getDesertMusic().play();
            }
        });

        ImageButton.ImageButtonStyle restartButtonStyle = new ImageButton.ImageButtonStyle();
        restartButtonStyle.up = new TextureRegionDrawable(new TextureRegion(mainGameAtlas.findRegion("main_game_restart_button_unpressed")));
        restartButtonStyle.down = new TextureRegionDrawable(new TextureRegion(mainGameAtlas.findRegion("main_game_restart_button_pressed")));

        ImageButton restartButton = new ImageButton(restartButtonStyle);
        restartButton.setPosition(worldXToScreenX(200.0F), worldYToScreenY(525.0F));
        restartButton.setSize(worldXToScreenX(100.0F), worldYToScreenY(100.0F));
        restartButton.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                musicSoundsObject.playButtonClick();
                resetStatsAndRestart();
                coinObject.setCoinCount();
                pauseGame = false;
                stage.getActors().get(0).setVisible(false);
                stage.getActors().get(1).setVisible(true);
                stage.getActors().get(2).setVisible(false);
                stage.getActors().get(3).setVisible(false);
                stage.getActors().get(4).setVisible(false);
                stage.getActors().get(5).setVisible(false);
                stage.getActors().get(21).setVisible(false);
                if (stageNumber == 1)
                    musicSoundsObject.getCityMusic().stop();
                else if (stageNumber == 2)
                    musicSoundsObject.getDesertMusic().stop();
            }
        });

        ImageButton.ImageButtonStyle mainMenuButtonStyle = new ImageButton.ImageButtonStyle();
        mainMenuButtonStyle.up = new TextureRegionDrawable(new TextureRegion(mainGameAtlas.findRegion("main_game_menu_button_unpressed")));
        mainMenuButtonStyle.down = new TextureRegionDrawable(new TextureRegion(mainGameAtlas.findRegion("main_game_menu_button_pressed")));

        ImageButton mainMenuButton = new ImageButton(mainMenuButtonStyle);
        mainMenuButton.setPosition(worldXToScreenX(350.0F), worldYToScreenY(525.0F));
        mainMenuButton.setSize(worldXToScreenX(100.0F), worldYToScreenY(100.0F));
        mainMenuButton.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                gsm.set(new ChooseStageMenu(gsm, adsController, manager));
                musicSoundsObject.playButtonClick();
                musicSoundsObject.playBackgroundMusic();
                if (stageNumber == 1)
                    musicSoundsObject.getCityMusic().stop();
                else if (stageNumber == 2)
                    musicSoundsObject.getDesertMusic().stop();
                dispose();
            }
        });

        ImageButton.ImageButtonStyle pauseButtonStyle = new ImageButton.ImageButtonStyle();
        pauseButtonStyle.up = new TextureRegionDrawable(new TextureRegion(mainGameAtlas.findRegion("main_game_pause_button_unpressed")));
        pauseButtonStyle.down = new TextureRegionDrawable(new TextureRegion(mainGameAtlas.findRegion("main_game_pause_button_pressed")));

        ImageButton pauseButton = new ImageButton(pauseButtonStyle);
        pauseButton.setPosition(0.0F, 0.0F);
        pauseButton.setSize(worldXToScreenX(80.0F), worldYToScreenY(80.0F));
        pauseButton.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                musicSoundsObject.playButtonClick();
                pauseGame = true;
                stage.getActors().get(0).setVisible(true);
                stage.getActors().get(1).setVisible(false);
                stage.getActors().get(2).setVisible(true);
                stage.getActors().get(3).setVisible(true);
                stage.getActors().get(4).setVisible(true);
                stage.getActors().get(5).setVisible(true);
                stage.getActors().get(21).setVisible(true);
                if (stageNumber == 1)
                    musicSoundsObject.getCityMusic().pause();
                else if (stageNumber == 2)
                    musicSoundsObject.getDesertMusic().pause();
            }
        });

        soundButtonStyle = new ImageButton.ImageButtonStyle();
        soundButtonStyle.up = new TextureRegionDrawable(new TextureRegion(mainGameAtlas.findRegion("main_game_sound_button_unpressed")));
        soundButtonStyle.down = new TextureRegionDrawable(new TextureRegion(mainGameAtlas.findRegion("main_game_sound_button_pressed")));

        noSoundButtonStyle = new ImageButton.ImageButtonStyle();
        noSoundButtonStyle.up = new TextureRegionDrawable(new TextureRegion(mainGameAtlas.findRegion("main_game_no_sound_button_unpressed")));
        noSoundButtonStyle.down = new TextureRegionDrawable(new TextureRegion(mainGameAtlas.findRegion("main_game_no_sound_button_pressed")));

        soundButton = new ImageButton(soundButtonStyle);
        soundButton.setPosition(worldXToScreenX(125.0F), worldYToScreenY(375.0F));
        soundButton.setSize(worldXToScreenX(100.0F), worldYToScreenY(100.0F));
        soundButton.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                if (prefs.getBoolean(Settings.SOUND, true)) {
                    prefs.putBoolean(Settings.SOUND, false);
                    prefs.flush();
                } else {
                    prefs.putBoolean(Settings.SOUND, true);
                    prefs.flush();
                    musicSoundsObject.playButtonClick();
                }
            }
        });

        musicButtonStyle = new ImageButton.ImageButtonStyle();
        musicButtonStyle.up = new TextureRegionDrawable(new TextureRegion(mainGameAtlas.findRegion("main_game_music_button_unpressed")));
        musicButtonStyle.down = new TextureRegionDrawable(new TextureRegion(mainGameAtlas.findRegion("main_game_music_button_pressed")));

        noMusicButtonStyle = new ImageButton.ImageButtonStyle();
        noMusicButtonStyle.up = new TextureRegionDrawable(new TextureRegion(mainGameAtlas.findRegion("main_game_no_music_button_unpressed")));
        noMusicButtonStyle.down = new TextureRegionDrawable(new TextureRegion(mainGameAtlas.findRegion("main_game_no_music_button_pressed")));

        musicButton = new ImageButton(musicButtonStyle);
        musicButton.setPosition(worldXToScreenX(275.0F), worldYToScreenY(375.0F));
        musicButton.setSize(worldXToScreenX(100.0F), worldYToScreenY(100.0F));
        musicButton.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                if (prefs.getBoolean(Settings.MUSIC, true)) {
                    prefs.putBoolean(Settings.MUSIC, false);
                    prefs.flush();
                    musicSoundsObject.playButtonClick();
                } else {
                    prefs.putBoolean(Settings.MUSIC, true);
                    prefs.flush();
                    musicSoundsObject.playButtonClick();
                }
            }
        });

        continueWithAdsButtonStyle = new ImageButton.ImageButtonStyle();
        continueWithAdsButtonStyle.up = new TextureRegionDrawable(new TextureRegion(mainGameAtlas.findRegion("continue_ad_button_unpressed")));
        continueWithAdsButtonStyle.down = new TextureRegionDrawable(new TextureRegion(mainGameAtlas.findRegion("continue_ad_button_pressed")));

        continueWithAdsWaitButtonStyle = new ImageButton.ImageButtonStyle();
        continueWithAdsWaitButtonStyle.up = new TextureRegionDrawable(new TextureRegion(mainGameAtlas.findRegion("ad_wait_button")));
        continueWithAdsWaitButtonStyle.down = new TextureRegionDrawable(new TextureRegion(mainGameAtlas.findRegion("ad_wait_button")));

        continueWithAdsButton = new ImageButton(continueWithAdsButtonStyle);
        continueWithAdsButton.setPosition(worldXToScreenX(25.0F), worldYToScreenY(410.0F));
        continueWithAdsButton.setSize(worldXToScreenX(200.0F), worldYToScreenY(80.0F));
        continueWithAdsButton.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                adsController.showRewardedVideo();
                musicSoundsObject.playButtonClick();
            }
        });

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = manager.get("font/font_scale_1.fnt", BitmapFont.class);
        labelStyle.fontColor = Color.BLACK;

        label1 = new Label(String.valueOf(continueWithDiamonds), labelStyle);
        label1.setSize(worldXToScreenX(40.0F), worldYToScreenY(40.0F));
        label1.setPosition(worldXToScreenX(370.0F), worldYToScreenY(420.0F));
        label1.setAlignment(1);

        ImageButton.ImageButtonStyle continueWithDiamondsButtonStyle = new ImageButton.ImageButtonStyle();
        continueWithDiamondsButtonStyle.up = new TextureRegionDrawable(new TextureRegion(mainGameAtlas.findRegion("continue_diamond_button_unpressed")));
        continueWithDiamondsButtonStyle.down = new TextureRegionDrawable(new TextureRegion(mainGameAtlas.findRegion("continue_diamond_button_pressed")));

        ImageButton continueWithDiamondsButton = new ImageButton(continueWithDiamondsButtonStyle);
        continueWithDiamondsButton.setPosition(worldXToScreenX(275.0F), worldYToScreenY(410.0F));
        continueWithDiamondsButton.setSize(worldXToScreenX(200.0F), worldYToScreenY(80.0F));
        continueWithDiamondsButton.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                musicSoundsObject.playButtonClick();
                if (prefs.getInteger(Shop.DIAMONDS) >= continueWithDiamonds) {
                    prefs.putInteger(Shop.DIAMONDS, prefs.getInteger(Shop.DIAMONDS) - continueWithDiamonds);
                    prefs.putInteger(REVIVED_AFTER_DEATH, prefs.getInteger(REVIVED_AFTER_DEATH, 0) + 1);
                    prefs.flush();
                    gameState = 0;
                    resetAfterRevive();
                    stage.getActors().get(0).setVisible(false);
                    stage.getActors().get(1).setVisible(true);
                    stage.getActors().get(6).setVisible(false);
                    stage.getActors().get(7).setVisible(false);
                    stage.getActors().get(8).setVisible(false);
                    continueWithDiamonds = continueWithDiamonds + 1;
                    label1.setText(String.valueOf(MainGame.this.continueWithDiamonds));
                    stage.getActors().get(23).setVisible(false);
                } else {
                    showDiamondsCount = false;
                    stage.getActors().get(9).setVisible(true);
                    stage.getActors().get(22).setVisible(true);
                    stage.getActors().get(23).setVisible(false);
                    stage.getActors().get(6).setTouchable(Touchable.disabled);
                    stage.getActors().get(7).setTouchable(Touchable.disabled);
                    stage.getActors().get(8).setTouchable(Touchable.disabled);
                }
            }
        });

        Image notEnoughDiamondsWindow = new Image(new TextureRegionDrawable(new TextureRegion(mainGameAtlas.findRegion("not_enough_diamonds_window"))));
        notEnoughDiamondsWindow.setSize(worldXToScreenX(480.0F), worldYToScreenY(200.0F));
        notEnoughDiamondsWindow.setPosition(worldXToScreenX(10.0F), worldYToScreenY(400.0F));

        ImageButton.ImageButtonStyle xButtonStyle = new ImageButton.ImageButtonStyle();
        xButtonStyle.up = new TextureRegionDrawable(new TextureRegion(sharedAtlas.findRegion("congratulations_window_x_button_unpressed")));
        xButtonStyle.down = new TextureRegionDrawable(new TextureRegion(sharedAtlas.findRegion("congratulations_window_x_button_pressed")));

        ImageButton xButtonDiamonds = new ImageButton(xButtonStyle);
        xButtonDiamonds.setPosition(worldXToScreenX(420.0F), worldYToScreenY(535.0F));
        xButtonDiamonds.setSize(worldXToScreenX(50.0F), worldYToScreenY(50.0F));
        xButtonDiamonds.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                musicSoundsObject.playButtonClick();
                showDiamondsCount = true;
                stage.getActors().get(9).setVisible(false);
                stage.getActors().get(22).setVisible(false);
                stage.getActors().get(23).setVisible(true);
                stage.getActors().get(6).setTouchable(Touchable.enabled);
                stage.getActors().get(7).setTouchable(Touchable.enabled);
                stage.getActors().get(8).setTouchable(Touchable.enabled);
            }
        });

        ImageButton.ImageButtonStyle endGameButtonStyle = new ImageButton.ImageButtonStyle();
        endGameButtonStyle.up = new TextureRegionDrawable(new TextureRegion(mainGameAtlas.findRegion("end_game_button_unpressed")));
        endGameButtonStyle.down = new TextureRegionDrawable(new TextureRegion(mainGameAtlas.findRegion("end_game_button_pressed")));

        ImageButton endGameButton = new ImageButton(endGameButtonStyle);
        endGameButton.setPosition(worldXToScreenX(150.0F), worldYToScreenY(0.0F));
        endGameButton.setSize(worldXToScreenX(200.0F), worldYToScreenY(80.0F));
        endGameButton.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                musicSoundsObject.playButtonClick();
                prefs.putInteger(Shop.COINS, coinObject.getCoinCount() + prefs.getInteger(Shop.COINS));
                prefs.flush();
                gsm.set(new EndGame(gsm, adsController, manager, coinObject.getCoinCount(), score, timerGame, stageNumber));
                dispose();
            }
        });

        Image congratulationsStoryWindow = new Image(new TextureRegionDrawable(new TextureRegion(mainGameAtlas.findRegion("congratulations_story_window"))));
        congratulationsStoryWindow.setSize(worldXToScreenX(480.0F), worldYToScreenY(250.0F));
        congratulationsStoryWindow.setPosition(worldXToScreenX(10.0F), worldYToScreenY(375.0F));

        ImageButton xButtonStoryContinue = new ImageButton(xButtonStyle);
        xButtonStoryContinue.setPosition(worldXToScreenX(120.0F), worldYToScreenY(385.0F));
        xButtonStoryContinue.setSize(worldXToScreenX(75.0F), worldYToScreenY(75.0F));
        xButtonStoryContinue.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                musicSoundsObject.playButtonClick();
                prefs.putInteger(Shop.COINS, coinObject.getCoinCount() + prefs.getInteger(Shop.COINS));
                prefs.flush();
                gsm.set(new EndGame(gsm, adsController, manager, coinObject.getCoinCount(), score, timerGame, stageNumber));
                dispose();
            }
        });

        ImageButton.ImageButtonStyle storyContinueButtonStyle = new ImageButton.ImageButtonStyle();
        storyContinueButtonStyle.up = new TextureRegionDrawable(new TextureRegion(mainGameAtlas.findRegion("story_continue_button_unpressed")));
        storyContinueButtonStyle.down = new TextureRegionDrawable(new TextureRegion(mainGameAtlas.findRegion("story_continue_button_pressed")));

        ImageButton storyContinueButton = new ImageButton(storyContinueButtonStyle);
        storyContinueButton.setPosition(worldXToScreenX(305.0F), worldYToScreenY(385.0F));
        storyContinueButton.setSize(worldXToScreenX(75.0F), worldYToScreenY(75.0F));
        storyContinueButton.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                musicSoundsObject.playButtonClick();
                stage.getActors().get(18).setVisible(false);
                stage.getActors().get(19).setVisible(false);
                stage.getActors().get(20).setVisible(false);
                stage.getActors().get(0).setVisible(false);
                resetAfterRevive();
                gameState = 0;
                if (stageNumber == 1)
                    MainGame.this.prefs.putFloat(EndGame.STORY_MODE_CITY, MainGame.this.timerGame);
                else if (stageNumber == 2)
                    MainGame.this.prefs.putFloat(EndGame.STORY_MODE_DESERT, MainGame.this.timerGame);
                prefs.flush();
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
                musicSoundsObject.playButtonClick();
                pauseGame = false;
                stage.getActors().get(0).setVisible(false);
                stage.getActors().get(1).setVisible(true);
                stage.getActors().get(24).setVisible(false);
                stage.getActors().get(25).setVisible(false);
                stage.getActors().get(26).setVisible(false);
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

        stage.addActor(mainGamePauseDarkBackground);
        stage.addActor(pauseButton);
        stage.addActor(soundButton);
        stage.addActor(playButton);
        stage.addActor(restartButton);
        stage.addActor(mainMenuButton);
        stage.addActor(continueWithAdsButton);
        stage.addActor(continueWithDiamondsButton);
        stage.addActor(endGameButton);
        stage.addActor(notEnoughDiamondsWindow);
        stage.addActor(mainGameTutorial);
        stage.addActor(tutorialContinueButton);
        stage.addActor(storyStartFirst);
        stage.addActor(storyStartSecond);
        stage.addActor(storyEndFirst);
        stage.addActor(storyEndSecond);
        stage.addActor(storyRightButton);
        stage.addActor(storyLeftButton);
        stage.addActor(congratulationsStoryWindow);
        stage.addActor(xButtonStoryContinue);
        stage.addActor(storyContinueButton);
        stage.addActor(musicButton);
        stage.addActor(xButtonDiamonds);
        stage.addActor(label1);
        stage.addActor(sureQuitWindow);
        stage.addActor(notQuitButton);
        stage.addActor(quitButton);
        stage.addActor(skipButton);

        stage.getActors().get(0).setVisible(false);
        stage.getActors().get(2).setVisible(false);
        stage.getActors().get(3).setVisible(false);
        stage.getActors().get(4).setVisible(false);
        stage.getActors().get(5).setVisible(false);
        stage.getActors().get(6).setVisible(false);
        stage.getActors().get(7).setVisible(false);
        stage.getActors().get(8).setVisible(false);
        stage.getActors().get(9).setVisible(false);
        stage.getActors().get(10).setVisible(false);
        stage.getActors().get(11).setVisible(false);
        stage.getActors().get(12).setVisible(false);
        stage.getActors().get(13).setVisible(false);
        stage.getActors().get(14).setVisible(false);
        stage.getActors().get(15).setVisible(false);
        stage.getActors().get(16).setVisible(false);
        stage.getActors().get(17).setVisible(false);
        stage.getActors().get(18).setVisible(false);
        stage.getActors().get(19).setVisible(false);
        stage.getActors().get(20).setVisible(false);
        stage.getActors().get(21).setVisible(false);
        stage.getActors().get(22).setVisible(false);
        stage.getActors().get(23).setVisible(false);
        stage.getActors().get(24).setVisible(false);
        stage.getActors().get(25).setVisible(false);
        stage.getActors().get(26).setVisible(false);
        stage.getActors().get(27).setVisible(false);
        if (prefs.getBoolean(FIRST_TIME_TUTORIAL, true)) {
            stage.getActors().get(1).setVisible(false);
            showScoreAndCoinLabel = false;
        }
        pauseGame = false;
        random = new Random(System.currentTimeMillis());

        coinObject = new Coin();
        rockObject = new Rock();
        bombObject = new Bomb();
        spikesObject = new Spikes();
        rocketObject = new Rocket();
        shieldObject = new Shield();
        coinRushObject = new CoinRush();
        coinMagnetObject = new CoinMagnet();
        playerObject = new Player();
        villainObject = new Villain();
        achievementsObject = new Achievements();

        coinObject.initializeValues(sharedAtlas, manager);
        rockObject.initializeValues(mainGameAtlas, stageNumber, manager);
        bombObject.initializeValues(mainGameAtlas, manager);
        spikesObject.initializeValues(mainGameAtlas, prefs);
        rocketObject.initializeValues(mainGameAtlas);
        shieldObject.initializeValues(mainGameAtlas);
        coinRushObject.initializeValues(mainGameAtlas);
        coinMagnetObject.initializeValues(mainGameAtlas);
        playerObject.initializeValues(manager, prefs);
        villainObject.initializeValues(mainGameAtlas);

        scoreAndCoinFont = manager.get("font/font_scale_1.fnt", BitmapFont.class);
        scoreAndCoinFont.setColor(Color.BLACK);
        scoreAndCoinFont.getData().setScale(worldXToScreenX(1.0F), worldYToScreenY(1.0F));

        scoreAndCoinBackground = mainGameAtlas.findRegion("score_coin_background");
        coin = sharedAtlas.findRegion("coin");
        collectiblesChoice = random.nextInt(3) + 1;
        collectiblesChance = 100.0F;
        normalChance = 10.0F;

        if (prefs.getInteger(Achievements.ADS_WATCHED_ATM_ACHIEVEMENT, 0) < 5)
            achievementsObject.checkAdsWatched(prefs.getInteger(EndGame.ADS_WATCHED, 0));

        shapeRenderer = new ShapeRenderer();
    }


    public void render(SpriteBatch batch) {
        Gdx.input.setCatchKey(4, true);
        if (Gdx.input.isKeyPressed(4)) {
            pauseGame = true;
            stage.getActors().get(0).setVisible(true);
            stage.getActors().get(1).setVisible(false);
            stage.getActors().get(24).setVisible(true);
            stage.getActors().get(25).setVisible(true);
            stage.getActors().get(26).setVisible(true);
        }

        if (prefs.getBoolean("sound", true))
            soundButton.setStyle(soundButtonStyle);
        else
            soundButton.setStyle(noSoundButtonStyle);

        if (prefs.getBoolean("music", true))
            musicButton.setStyle(musicButtonStyle);
        else
            musicButton.setStyle(noMusicButtonStyle);

        batch.begin();

        if (showEndStory) {
            if (stageNumber == 1)
                showEndStory(stageNumber);
            else if (stageNumber == 2)
                showEndStory(stageNumber);
        } else {
            if (stageNumber == 1)
                showStartStory(stageNumber);
            else if (stageNumber == 2)
                showStartStory(stageNumber);
        }

        if (prefs.getBoolean(FIRST_TIME_TUTORIAL, true)) {
            batch.draw(background[getActiveBackgroundIdx(bgCords[0])], bgCords[getActiveBackgroundIdx(bgCords[0])], 0.0F, worldXToScreenX(500.0F), worldYToScreenY(1000.0F));
            batch.draw(background[getActiveBackgroundIdx(bgCords[0]) + 1], bgCords[getActiveBackgroundIdx(bgCords[0]) + 1] - worldXToScreenX(2.0F), 0.0F, worldXToScreenX(500.0F), worldYToScreenY(1000.0F));
        }

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.RED);

        if (gameState == 1) {

            if (musicOnce) {
                if (stageNumber == 1)
                    musicSoundsObject.playCityMusic();
                else if (stageNumber == 2)
                    musicSoundsObject.playDesertMusic();
                musicOnce = false;
            }


            if (!pauseGame)
                for (int i = 0; i < 5; i++)
                    bgCords[i] = bgCords[i] - (int) worldXToScreenX(3.0F) * dt * 60.0F;

            if (bgCords[4] <= 0.0F)
                for (int i = 0; i < 5; i++)
                    bgCords[i] = (int) worldXToScreenX((i * 500));

            batch.draw(background[getActiveBackgroundIdx(bgCords[0])], bgCords[getActiveBackgroundIdx(bgCords[0])], 0.0F, worldXToScreenX(500.0F), worldYToScreenY(1000.0F));
            batch.draw(background[getActiveBackgroundIdx(bgCords[0]) + 1], bgCords[getActiveBackgroundIdx(bgCords[0]) + 1] - worldXToScreenX(2.0F), 0.0F, worldXToScreenX(500.0F), worldYToScreenY(1000.0F));

            if (!pauseGame) {
                timerObjects += Gdx.graphics.getDeltaTime();
                timerGame += Gdx.graphics.getDeltaTime();
            }

            if (prefs.getInteger(Achievements.METRES_WITHOUT_COINS_ATM_ACHIEVEMENT, 0) < 6)
                achievementsObject.checkMetresWithoutCoins(timerGame, coinObject.getCoinCount());
            if (coinRushObject.isCoinRushEnd() && timerObjects - coinObject.getLastCoinTimer() >= coinObject.getCoinTime() / 1000.0F) {
                coinObject.makeCoin();
                coinObject.setLastCoinTimer(timerObjects);
                if (!coinRushObject.isCoinRush())
                    coinObject.setCoinTime(random.nextInt(2001) + 1000 - (prefs.getInteger(Shop.SPAWN_RATE_COINS_UPGRADED) + coinSpawnUpgrade) * 50);
            }

            if (rocksGo) {
                if (timerObjects - rockObject.getLastRockTimer() >= 2.0F) {
                    rockObject.makeRock();
                    rockObject.setLastRockTimer(timerObjects);
                }
                if (rockObject.isFirstRockHit() && timerObjects - rockObject.getFirstRockHitTimer() >= 5.0F) {
                    rockObject.setFirstRockHitFalse();
                    prefs.putInteger(HEALED_FROM_STONES, prefs.getInteger(HEALED_FROM_STONES, 0) + 1);
                    prefs.flush();
                    if (prefs.getInteger(Achievements.HEAL_STONES_ATM_ACHIEVEMENT, 0) < 7)
                        achievementsObject.checkHealStones(prefs.getInteger(HEALED_FROM_STONES, 0));
                }
            }

            if (bombsGo && timerObjects - bombObject.getLastBombTimer() >= (random.nextInt(2001) + 5000) / 1000.0F) {
                bombObject.makeBomb();
                bombObject.setLastBombTimer(timerObjects);
            }

            if (spikesGo && timerObjects - spikesObject.getLastSpikeDownTimer() >= spikesObject.getSpikeDownTime() / 1000.0F) {
                spikesObject.makeSpikeDown();
                spikesObject.setSpikeDownTime(random.nextInt(6001) + 4000);
                spikesObject.setLastSpikeDownTimer(timerObjects);
            }

            if (rocketsGo) {
                if (timerObjects - rocketObject.getLastArrowRedTimer() >= 3.0F) {
                    rocketObject.makeRocket(rocketObject.getRedHeight(), musicSoundsObject);
                    rocketObject.setRedHeight(rocketObject.makeRocketArrowRed());
                    rocketObject.setLastArrowRedTimer(timerObjects);
                }

                if (timerObjects - rocketObject.getLastArrowRedTimer() >= 2.0F && yellowGo && timerObjects - rocketObject.getLastArrowYellowTimer() >= 3.0F) {
                    rocketObject.makeRocket(rocketObject.getYellowHeight(), musicSoundsObject);
                    rocketObject.setYellowHeight(rocketObject.makeRocketArrowYellow());
                    rocketObject.setLastArrowYellowTimer(timerObjects);
                }

                if (timerObjects - rocketObject.getLastArrowRedTimer() >= 1.0F && greenGo && timerObjects - rocketObject.getLastArrowGreenTimer() >= 3.0F) {
                    rocketObject.makeRocket(rocketObject.getGreenHeight(), musicSoundsObject);
                    rocketObject.setGreenHeight(rocketObject.makeRocketArrowGreen());
                    rocketObject.setLastArrowGreenTimer(timerObjects);
                }
            }

            if (timerObjects >= 10.0F && timerObjects - collectiblesTimer >= 1.5F) {
                collectiblesChance = random.nextFloat() * 99.0F + 1.0F;
                collectiblesTimer = timerObjects;
            }
            if (collectiblesChance <= normalChance + prefs.getInteger(Shop.SPAWN_RATE_UPGRADED)) {
                if (collectiblesChoice == 1 && !shieldObject.isHasShield())
                    shieldObject.makeShield();
                else if (collectiblesChoice == 2 && !coinMagnetObject.isHasCoinMagnet())
                    coinMagnetObject.makeCoinMagnet();
                else if (collectiblesChoice == 3 && !coinRushObject.isCoinRush())
                    coinRushObject.makeSpeedCoin();
                collectiblesChance = 100.0F;
                collectiblesChoice = random.nextInt(3) + 1;
            }

            if (shieldObject.isHasShield()) {
                if (!onceShield) {
                    normalChance /= 2.0F;
                    onceShield = true;
                }

                if (timerObjects - shieldObject.getShieldOnTimer() >= prefs.getInteger(Shop.SHIELD_UPGRADED) + 7.0F + shieldUpgrade && timerObjects - shieldObject.getShieldOnTimer() < prefs.getInteger(Shop.SHIELD_UPGRADED) + 10.0F + shieldUpgrade)
                    if (this.shieldObject.getShieldStatePause() < 5) {
                        Shield shield = this.shieldObject;
                        shield.setShieldStatePause(shield.getShieldStatePause() + 1);
                    } else {
                        shieldObject.setShieldStatePause(0);
                        shieldObject.setShieldState(1 - shieldObject.getShieldState());
                    }

                if (timerObjects - shieldObject.getShieldOnTimer() >= prefs.getInteger(Shop.SHIELD_UPGRADED) + 10.0F + shieldUpgrade) {
                    shieldObject.setHasShieldFalse();
                    normalChance *= 2.0F;
                    onceShield = false;
                }
            }

            if (!coinRushObject.isCoinRush() && timerObjects - coinRushObject.getCoinRushOnTimer() >= prefs.getInteger(Shop.COIN_RUSH_UPGRADED) + 8.0F + coinRushUpgrade && timerObjects - coinRushObject.getCoinRushOnTimer() < prefs.getInteger(Shop.COIN_RUSH_UPGRADED) + 8.2F + coinRushUpgrade) {
                coinObject.setCoinSpeed(coinObject.worldXToScreenX(5.0F));
                coinRushObject.setCoinRushEnd(true);
            }

            if (coinRushObject.isCoinRush()) {
                if (!onceCoinRush) {
                    normalChance /= 2.0F;
                    onceCoinRush = true;
                }

                coinObject.setCoinTime(100);
                coinObject.setCoinSpeed(coinObject.worldXToScreenX(10.0F));
                if (timerObjects - coinRushObject.getCoinRushOnTimer() >= prefs.getInteger(Shop.COIN_RUSH_UPGRADED) + 5.0F + coinRushUpgrade) {
                    coinRushObject.setCoinRushFalse();
                    coinRushObject.setCoinRushEnd(false);
                    normalChance *= 2.0F;
                    onceCoinRush = false;
                }
            }

            if (coinMagnetObject.isHasCoinMagnet()) {
                if (!onceMagnet) {
                    normalChance /= 2.0F;
                    onceMagnet = true;
                }

                if (timerObjects - coinMagnetObject.getCoinMagnetOnTimer() >= prefs.getInteger(Shop.MAGNET_UPGRADED) + 10.0F + magnetUpgrade) {
                    coinMagnetObject.setHasCoinMagnet();
                    normalChance *= 2.0F;
                    onceMagnet = false;
                }
            }

            if (coinMagnetObject.isHasCoinMagnet())
                coinObject.drawCoinMagnetized(batch, playerObject.getPlayerXRect(), playerObject.getPlayerY(), playerObject.getPlayerWidthRect(), playerObject.getPlayerHeightRect(), pauseGame);
            else
                coinObject.drawCoin(batch, pauseGame);

            coinObject.removeCoin();

            if (stageNumber == 1) {
                rockObject.drawRock(batch, pauseGame, 6.6F);
                bombObject.drawBomb(batch, pauseGame, 6.6F);
            } else if (stageNumber == 2) {
                rockObject.drawRock(batch, pauseGame, 8.6F);
                bombObject.drawBomb(batch, pauseGame, 8.6F);
                spikesObject.drawSpikeDown(batch, pauseGame);
            }

            if (rocketsGo) {
                rocketObject.redArrowState(timerObjects);
                rocketObject.yellowArrowState(timerObjects);
                rocketObject.greenArrowState(timerObjects);
                rocketObject.drawArrows(firstArrowsAppearanceRed, firstArrowsAppearanceYellow, firstArrowsAppearanceGreen, batch);
                if (stageNumber == 1)
                    rocketObject.drawRocket(batch, pauseGame, 12.45F);
                else if (stageNumber == 2)
                    rocketObject.drawRocket(batch, pauseGame, 15.0F);
            }

            shieldObject.drawShieldCollectible(batch, pauseGame);
            coinRushObject.drawSpeedCoinCollectible(batch, pauseGame);
            coinMagnetObject.drawCoinMagnetCollectible(batch, pauseGame);
            playerObject.drawPlayerRun(batch, shieldObject, rockObject, pauseGame, prefs);

            if (timerGame >= 30.0F && timerGame < 60.0F) {
                rocksGo = false;
                rockObject.setFirstRockHitFalse();
                bombsGo = true;
            }

            if (stageNumber == 2)
                spikesGo = true;

            if (timerGame >= 60.0F && timerGame < 100.0F) {
                bombsGo = false;
                rocketsGo = true;
                rocketFirstTime();
            }

            if (stageNumber == 1)
                timingStory(EndGame.STORY_MODE_CITY);
            else if (stageNumber == 2)
                timingStory(EndGame.STORY_MODE_DESERT);


            if ((timerGame - scoreTimer) >= 0.3D) {
                score++;
                scoreTimer = timerGame;
            }

        } else if (gameState == 0) {
            batch.draw(background[getActiveBackgroundIdx(bgCords[0])], bgCords[getActiveBackgroundIdx(bgCords[0])], 0.0F, worldXToScreenX(500.0F), worldYToScreenY(1000.0F));
            batch.draw(background[getActiveBackgroundIdx(bgCords[0]) + 1], bgCords[getActiveBackgroundIdx(bgCords[0]) + 1] - worldXToScreenX(2.0F), 0.0F, worldXToScreenX(500.0F), worldYToScreenY(1000.0F));
            playerObject.drawPlayerStart(batch, prefs);
            stage.getActors().get(1).setVisible(false);
            showScoreAndCoinLabel = false;
            if (Gdx.input.justTouched()) {
                gameState = 1;
                stage.getActors().get(1).setVisible(true);
                showScoreAndCoinLabel = true;
            }
        } else if (gameState == 2) {
            if (stageNumber == 1)
                musicSoundsObject.getCityMusic().stop();
            else if (stageNumber == 2)
                musicSoundsObject.getDesertMusic().stop();
            batch.draw(background[getActiveBackgroundIdx(bgCords[0])], bgCords[getActiveBackgroundIdx(bgCords[0])], 0.0F, worldXToScreenX(500.0F), worldYToScreenY(1000.0F));
            batch.draw(background[getActiveBackgroundIdx(bgCords[0]) + 1], bgCords[getActiveBackgroundIdx(bgCords[0]) + 1] - worldXToScreenX(2.0F), 0.0F, worldXToScreenX(500.0F), worldYToScreenY(1000.0F));
            playerObject.drawPlayerFaint(batch, pauseGame, prefs);
            boolean adLoaded = mAdsController.getAdLoaded();
            boolean rewardReceived = mAdsController.getRewardReceived();
            stage.getActors().get(1).setVisible(false);
            stage.getActors().get(6).setVisible(true);
            showScoreAndCoinLabel = false;

            if (twoTimesAd < 2 && adLoaded) {
                stage.getActors().get(6).setTouchable(Touchable.enabled);
                continueWithAdsButton.setStyle(continueWithAdsButtonStyle);
            } else if (twoTimesAd < 2) {
                stage.getActors().get(6).setTouchable(Touchable.disabled);
                continueWithAdsButton.setStyle(continueWithAdsWaitButtonStyle);
            }
            if (rewardReceived) {
                twoTimesAd++;
                gameState = 0;
                resetAfterRevive();
                stage.getActors().get(0).setVisible(false);
                stage.getActors().get(6).setVisible(false);
                stage.getActors().get(7).setVisible(false);
                stage.getActors().get(8).setVisible(false);
                mAdsController.setRewardReceived(false);
                stage.getActors().get(23).setVisible(false);
                prefs.putInteger(REVIVED_AFTER_DEATH, prefs.getInteger(REVIVED_AFTER_DEATH, 0) + 1);
                prefs.putInteger(EndGame.ADS_WATCHED, prefs.getInteger(EndGame.ADS_WATCHED, 0) + 1);
                prefs.flush();
            } else {
                stage.getActors().get(8).setVisible(true);
                stage.getActors().get(7).setVisible(true);
                if (showDiamondsCount)
                    stage.getActors().get(23).setVisible(true);
            }
        } else if (gameState == 3) {
            if (timerGame <= 100.8F)
                for (int i = 0; i < 5; i++)
                    bgCords[i] = bgCords[i] - (int) worldXToScreenX(3.0F) * dt * 60.0F;

            if (bgCords[4] <= 0.0F)
                for (int i = 0; i < 5; i++)
                    bgCords[i] = (int) worldXToScreenX((i * 500));

            batch.draw(background[getActiveBackgroundIdx(bgCords[0])], bgCords[getActiveBackgroundIdx(bgCords[0])], 0.0F, worldXToScreenX(500.0F), worldYToScreenY(1000.0F));
            batch.draw(background[getActiveBackgroundIdx(bgCords[0]) + 1], bgCords[getActiveBackgroundIdx(bgCords[0]) + 1] - worldXToScreenX(2.0F), 0.0F, worldXToScreenX(500.0F), worldYToScreenY(1000.0F));

            playerObject.drawPlayerWin(batch);
            villainObject.drawVillain(batch, pauseGame);

            if (timerGame >= 102.0F) {
                showEndStory = true;
                if (stageNumber == 1)
                    musicSoundsObject.getCityMusic().stop();
                else if (stageNumber == 2)
                    musicSoundsObject.getDesertMusic().stop();
            } else
                timerGame = timerGame + Gdx.graphics.getDeltaTime();
        }

        coinObject.coinCollision(playerObject.getPlayerRectangle(), prefs.getBoolean(Shop.DOUBLE_COINS, false));

        if (!shieldObject.isHasShield()) {
            if (rockObject.isFirstRockHit())
                gameState = rockObject.rockCollisionSecond(playerObject.getPlayerRectangle(), gameState, prefs);
            else
                rockObject.rockCollisionFirst(playerObject.getPlayerRectangle(), timerObjects);

            gameState = bombObject.bombCollision(playerObject.getPlayerRectangle(), gameState, prefs);
            gameState = spikesObject.spikeDownCollision(playerObject.getPlayerRectangle(), gameState, prefs, musicSoundsObject);
            gameState = rocketObject.rocketCollision(playerObject.getPlayerRectangle(), gameState, prefs, musicSoundsObject);
        }

        shieldObject.shieldCollision(playerObject.getPlayerRectangle(), timerObjects, prefs, musicSoundsObject);
        coinRushObject.speedCoinCollision(playerObject.getPlayerRectangle(), timerObjects, prefs, musicSoundsObject);
        coinMagnetObject.coinMagnetCollision(playerObject.getPlayerRectangle(), timerObjects, prefs, musicSoundsObject);

        if (showScoreAndCoinLabel) {
            batch.draw(scoreAndCoinBackground, worldXToScreenX(-25.0F), worldYToScreenY(950.0F), worldXToScreenX(125.0F), worldYToScreenY(50.0F));
            batch.draw(scoreAndCoinBackground, worldXToScreenX(-25.0F), worldYToScreenY(900.0F), worldXToScreenX(125.0F), worldYToScreenY(50.0F));
            scoreAndCoinFont.draw(batch, String.valueOf(score), worldXToScreenX(10.0F), worldYToScreenY(985.0F));
            scoreAndCoinFont.draw(batch, String.valueOf(coinObject.getCoinCount()), worldXToScreenX(30.0F), worldYToScreenY(935.0F));
            batch.draw(coin, worldXToScreenX(0.0F), worldYToScreenY(910.0F), worldXToScreenX(30.0F), worldYToScreenY(30.0F));
        }

        batch.end();
        shapeRenderer.end();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    public void update(float paramFloat) {
    }

    private int getActiveBackgroundIdx(float paramFloat) {
        return (int) Math.floor(Math.abs(paramFloat / screenWidth));
    }

    private void resetAfterRevive() {
        playerObject.resetPlayerStats(this.prefs);
        coinObject.resetCoinStats();
        rockObject.resetRockStats();
        bombObject.resetBombStats();
        spikesObject.resetSpikeDownStats();
        rocketObject.resetRocketStats();
        shieldObject.resetShieldStats();
        coinRushObject.resetCoinRushStats();
        coinMagnetObject.resetCoinMagnetState();
        timerObjects = 0.0F;
        normalChance = 10.0F;
        firstArrowsAppearanceRed = false;
        firstArrowsAppearanceYellow = false;
        firstArrowsAppearanceGreen = false;
        firstTimeArrowsRed = true;
        firstTimeArrowsYellow = true;
        firstTimeArrowsGreen = true;
        yellowGo = false;
        greenGo = false;
        musicOnce = true;
    }

    private void resetStatsAndRestart() {
        gameState = 0;
        score = 0;
        for (int i = 0; i < 5; i++)
            bgCords[i] = (int) worldXToScreenX((i * 500));

        playerObject.resetPlayerStats(prefs);
        coinObject.resetCoinStats();
        rockObject.resetRockStats();
        bombObject.resetBombStats();
        spikesObject.resetSpikeDownStats();
        rocketObject.resetRocketStats();
        shieldObject.resetShieldStats();
        coinRushObject.resetCoinRushStats();
        coinMagnetObject.resetCoinMagnetState();
        timerObjects = 0.0F;
        timerGame = 0.0F;
        collectiblesTimer = 0.0F;
        normalChance = 10.0F;
        rocksGo = true;
        bombsGo = false;
        spikesGo = false;
        rocketsGo = false;
        firstArrowsAppearanceRed = false;
        firstArrowsAppearanceYellow = false;
        firstArrowsAppearanceGreen = false;
        firstTimeArrowsRed = true;
        firstTimeArrowsYellow = true;
        firstTimeArrowsGreen = true;
        yellowGo = false;
        greenGo = false;
        scoreTimer = 0.0F;
        twoTimesAd = 0;
        musicOnce = true;
    }

    private void rocketFirstTime() {
        if (firstTimeArrowsRed) {
            firstArrowsAppearanceRed = true;
            rocketObject.setLastArrowRedTimer(timerObjects);
            firstTimeArrowsRed = false;
        }

        if (firstTimeArrowsYellow && timerObjects - rocketObject.getLastArrowRedTimer() >= 2.0F) {
            firstArrowsAppearanceYellow = true;
            rocketObject.setLastArrowYellowTimer(timerObjects);
            firstTimeArrowsYellow = false;
            yellowGo = true;
        }

        if (firstTimeArrowsGreen && timerObjects - rocketObject.getLastArrowRedTimer() >= 1.0F) {
            firstArrowsAppearanceGreen = true;
            rocketObject.setLastArrowGreenTimer(timerObjects);
            firstTimeArrowsGreen = false;
            greenGo = true;
        }
    }

    private void showEndStory(int storyPageNumber) {
        if (storyEndNumber == 0) {
            stage.getActors().get(14).setVisible(true);
            stage.getActors().get(15).setVisible(false);
            stage.getActors().get(16).setVisible(true);
            stage.getActors().get(17).setVisible(false);
            stage.getActors().get(27).setVisible(true);
        } else if (storyEndNumber == 1) {
            stage.getActors().get(14).setVisible(false);
            stage.getActors().get(15).setVisible(true);
            stage.getActors().get(17).setVisible(true);

            if (storyPageNumber == 1)
                storyEndSecond.setDrawable(new TextureRegionDrawable(new TextureRegion(this.manager.get("story_city_end_second.png", Texture.class))));
            else if (storyPageNumber == 2)
                storyEndSecond.setDrawable(new TextureRegionDrawable(new TextureRegion(this.manager.get("story_city_end_second.png", Texture.class))));
        } else if (storyEndNumber == 2) {
            if (storyPageNumber == 1)
                storyEndSecond.setDrawable(new TextureRegionDrawable(new TextureRegion(this.manager.get("story_city_end_third.png", Texture.class))));
            else if (storyPageNumber == 2)
                storyEndSecond.setDrawable(new TextureRegionDrawable(new TextureRegion(this.manager.get("story_city_end_third.png", Texture.class))));
        } else if (storyEndNumber == 3) {
            if (storyPageNumber == 1)
                storyEndSecond.setDrawable(new TextureRegionDrawable(new TextureRegion(this.manager.get("story_city_end_fourth.png", Texture.class))));
            else if (storyPageNumber == 2)
                storyEndSecond.setDrawable(new TextureRegionDrawable(new TextureRegion(this.manager.get("story_city_end_fourth.png", Texture.class))));
        }
    }

    private void showStartStory(int storyPageNumber) {
        if (storyStartNumber == 0) {
            stage.getActors().get(12).setVisible(true);
            stage.getActors().get(13).setVisible(false);
            stage.getActors().get(16).setVisible(true);
            stage.getActors().get(17).setVisible(false);
            stage.getActors().get(27).setVisible(true);
        } else if (storyStartNumber == 1) {
            stage.getActors().get(12).setVisible(false);
            stage.getActors().get(13).setVisible(true);
            stage.getActors().get(17).setVisible(true);
            if (storyPageNumber == 1)
                storyStartSecond.setDrawable(new TextureRegionDrawable(new TextureRegion(mainGameAtlas.findRegion("story_city_start_second"))));
            else if (storyPageNumber == 2)
                storyStartSecond.setDrawable(new TextureRegionDrawable(new TextureRegion(this.manager.get("story_city_start_second.png", Texture.class))));
        } else if (storyStartNumber == 2) {
            if (storyPageNumber == 1)
                storyStartSecond.setDrawable(new TextureRegionDrawable(new TextureRegion(mainGameAtlas.findRegion("story_city_start_third"))));
            else if (storyPageNumber == 2)
                storyStartSecond.setDrawable(new TextureRegionDrawable(new TextureRegion(this.manager.get("story_city_start_third.png", Texture.class))));
        } else if (storyStartNumber == 3) {
            if (storyPageNumber == 1)
                storyStartSecond.setDrawable(new TextureRegionDrawable(new TextureRegion(mainGameAtlas.findRegion("story_city_start_fourth"))));
            else if (storyPageNumber == 2)
                storyStartSecond.setDrawable(new TextureRegionDrawable(new TextureRegion(this.manager.get("story_city_start_fourth.png", Texture.class))));
        }
    }

    private void timingStory(String paramString) {
        if (prefs.getFloat(paramString) >= 100.0F) {
            if (timerGame >= 100.0F && timerGame < 160.0F) {
                rocketsGo = false;
                rocksGo = true;
                bombsGo = true;
            }

            if (timerGame >= 160.0F && timerGame < 240.0F) {
                rocksGo = false;
                rockObject.setFirstRockHitFalse();
                rocketsGo = true;
                rocketFirstTime();
            }
            if (timerGame >= 240.0F)
                rocksGo = true;
        } else if (timerGame > 100.0F)
            gameState = 3;

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
