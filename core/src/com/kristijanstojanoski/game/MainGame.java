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
import States.Shop;
import States.State;

public class MainGame extends State {
    private static final int BLURRY_BACKGROUND = 0;
    private static final int CONTINUE_WITH_ADS_BUTTON = 6;
    private static final int CONTINUE_WITH_DIAMONDS_BUTTON = 7;
    private static final int END_GAME_BUTTON = 8;
    private static final String FIRST_TIME_END_STORY_CITY = "firstTimeEndStoryCity";
    private static final String FIRST_TIME_END_STORY_DESERT = "firstTimeEndStoryDesert";
    private static final String FIRST_TIME_START_STORY_CITY = "firstTimeStartStoryCity";
    private static final String FIRST_TIME_START_STORY_DESERT = "firstTimeStartStoryDesert";
    private static final String FIRST_TIME_TUTORIAL = "firstTimeTutorial";
    private static final String HEALED_FROM_STONES = "healedFromStones";
    private static final int MENU_BUTTON = 5;
    private static final int PAUSE_BUTTON = 1;
    private static final int PLAY_BUTTON = 3;
    private static final int RESTART_BUTTON = 4;
    public static final String REVIVED_AFTER_DEATH = "revivedAfterDeath";
    private static final int SOUND_BUTTON = 2;

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
        TextureAtlas mainGameAtlas = manager.get("main_game/main_game.atlas", TextureAtlas.class);
        musicSoundsObject = new MusicSounds(manager);

        if (stageNumber == 1) {
            background[0] = mainGameAtlas.findRegion("city_background", 1);
            background[1] = mainGameAtlas.findRegion("city_background", 2);
            background[2] = mainGameAtlas.findRegion("city_background", 3);
            background[3] = mainGameAtlas.findRegion("city_background", 4);
            background[4] = mainGameAtlas.findRegion("city_background", 1);
        } else if (stageNumber == 2) {
            background[0] = mainGameAtlas.findRegion("desert_background", 1);
            background[1] = mainGameAtlas.findRegion("desert_background", 2);
            background[2] = mainGameAtlas.findRegion("desert_background", 3);
            background[3] = mainGameAtlas.findRegion("desert_background", 4);
            background[4] = mainGameAtlas.findRegion("desert_background", 1);
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

        Image storyStartFirst = new Image(new TextureRegionDrawable(new TextureRegion(manager.get("story_city_start_first.png", Texture.class))));
        storyStartFirst.setSize(worldXToScreenX(500.0F), worldYToScreenY(1000.0F));
        storyStartFirst.setPosition(worldXToScreenX(0.0F), worldYToScreenY(0.0F));
        if (stageNumber == 2)
            storyStartFirst.setDrawable(new TextureRegionDrawable(new TextureRegion(manager.get("story_city_start_first.png", Texture.class))));

        storyStartSecond = new Image(new TextureRegionDrawable(new TextureRegion(manager.get("story_city_start_second.png", Texture.class))));
        storyStartSecond.setSize(worldXToScreenX(500.0F), worldYToScreenY(1000.0F));
        storyStartSecond.setPosition(worldXToScreenX(0.0F), worldYToScreenY(0.0F));
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
        storyRightStyle.up = new TextureRegionDrawable(new TextureRegion(manager.get("arrow_story_right.png", Texture.class)));
        storyRightStyle.down = new TextureRegionDrawable(new TextureRegion(manager.get("arrow_story_right.png", Texture.class)));

        ImageButton storyRightButton = new ImageButton(storyRightStyle);
        storyRightButton.setPosition(worldXToScreenX(450.0F), worldYToScreenY(10.0F));
        storyRightButton.setSize(worldXToScreenX(40.0F), worldYToScreenY(40.0F));
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
        storyLeftButtonStyle.up = new TextureRegionDrawable(new TextureRegion(manager.get("arrow_story_left.png", Texture.class)));
        storyLeftButtonStyle.down = new TextureRegionDrawable(new TextureRegion(manager.get("arrow_story_left.png", Texture.class)));

        ImageButton storyLeftButton = new ImageButton(storyLeftButtonStyle);
        storyLeftButton.setPosition(worldXToScreenX(10.0F), worldYToScreenY(10.0F));
        storyLeftButton.setSize(worldXToScreenX(40.0F), worldYToScreenY(40.0F));
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
        skipButtonStyle.up = new TextureRegionDrawable(new TextureRegion(manager.get("skip_button.png", Texture.class)));
        skipButtonStyle.down = new TextureRegionDrawable(new TextureRegion(manager.get("skip_button.png", Texture.class)));

        ImageButton skipButton = new ImageButton(skipButtonStyle);
        skipButton.setPosition(worldXToScreenX(200.0F), worldYToScreenY(20.0F));
        skipButton.setSize(worldXToScreenX(100.0F), worldYToScreenY(100.0F));
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
                if (prefs.getBoolean("sound", true)) {
                    prefs.putBoolean("sound", false);
                    prefs.flush();
                } else {
                    prefs.putBoolean("sound", true);
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
                if (prefs.getBoolean("music", true)) {
                    prefs.putBoolean("music", false);
                    prefs.flush();
                    musicSoundsObject.playButtonClick();
                } else {
                    prefs.putBoolean("music", true);
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
                //adsController.showRewardedVideo();
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
                    MainGame.this.prefs.putFloat("storyModeCity", MainGame.this.timerGame);
                else if (stageNumber == 2)
                    MainGame.this.prefs.putFloat("storyModeDesert", MainGame.this.timerGame);
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
        stage.addActor(storyContinueButton);
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
        spikesObject.initializeValues(mainGameAtlas);
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


    public void render(SpriteBatch paramSpriteBatch) {
        Gdx.input.setCatchKey(4, true);
        if (Gdx.input.isKeyPressed(4)) {
            this.pauseGame = true;
            this.stage.getActors().get(0).setVisible(true);
            this.stage.getActors().get(1).setVisible(false);
            this.stage.getActors().get(24).setVisible(true);
            this.stage.getActors().get(25).setVisible(true);
            this.stage.getActors().get(26).setVisible(true);
        }
        if (this.prefs.getBoolean("sound", true)) {
            this.soundButton.setStyle(this.soundButtonStyle);
        } else {
            this.soundButton.setStyle(this.noSoundButtonStyle);
        }
        if (this.prefs.getBoolean("music", true)) {
            this.musicButton.setStyle(this.musicButtonStyle);
        } else {
            this.musicButton.setStyle(this.noMusicButtonStyle);
        }
        paramSpriteBatch.begin();
        if (this.showEndStory) {
            int j = this.stageNumber;
            if (j == 1) {
                showEndStory(j);
            } else if (j == 2) {
                showEndStory(j);
            }
        } else {
            int j = this.stageNumber;
            if (j == 1) {
                showStartStory(j);
            } else if (j == 2) {
                showStartStory(j);
            }
        }
        if (this.prefs.getBoolean("firstTimeTutorial", true)) {
            int j = getActiveBackgroundIdx(this.bgCords[0]);
            paramSpriteBatch.draw(this.background[j], this.bgCords[j], 0.0F, worldXToScreenX(500.0F), worldYToScreenY(1000.0F));
            TextureAtlas.AtlasRegion[] arrayOfAtlasRegion = this.background;
            paramSpriteBatch.draw(arrayOfAtlasRegion[++j], this.bgCords[j] - worldXToScreenX(2.0F), 0.0F, worldXToScreenX(500.0F), worldYToScreenY(1000.0F));
        }
        this.shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        this.shapeRenderer.setColor(Color.RED);
        int i = this.gameState;
        if (i == 1) {
            if (!this.pauseGame)
                for (i = 0; i < 5; i++) {
                    float[] arrayOfFloat = this.bgCords;
                    arrayOfFloat[i] = arrayOfFloat[i] - (int) worldXToScreenX(3.0F) * this.dt * 60.0F;
                }
            if (this.bgCords[4] <= 0.0F)
                for (i = 0; i < 5; i++)
                    this.bgCords[i] = (int) worldXToScreenX((i * 500));
            i = getActiveBackgroundIdx(this.bgCords[0]);
            paramSpriteBatch.draw(this.background[i], this.bgCords[i], 0.0F, worldXToScreenX(500.0F), worldYToScreenY(1000.0F));
            TextureAtlas.AtlasRegion[] arrayOfAtlasRegion = this.background;
            paramSpriteBatch.draw(arrayOfAtlasRegion[++i], this.bgCords[i] - worldXToScreenX(2.0F), 0.0F, worldXToScreenX(500.0F), worldYToScreenY(1000.0F));
            if (!this.pauseGame) {
                this.timerObjects += Gdx.graphics.getDeltaTime();
                this.timerGame += Gdx.graphics.getDeltaTime();
            }
            if (this.prefs.getInteger("metresWithoutCoinsAtmAchievement", 0) < 6)
                this.achievementsObject.checkMetresWithoutCoins(this.timerGame, this.coinObject.getCoinCount());
            if (this.coinRushObject.isCoinRushEnd() && this.timerObjects - this.coinObject.getLastCoinTimer() >= this.coinObject.getCoinTime() / 1000.0F) {
                this.coinObject.makeCoin();
                this.coinObject.setLastCoinTimer(this.timerObjects);
                if (!this.coinRushObject.isCoinRush())
                    this.coinObject.setCoinTime(this.random.nextInt(2001) + 1000 - (this.prefs.getInteger("spawnRateCoinsUpgraded") + this.coinSpawnUpgrade) * 50);
            }
            if (this.rocksGo) {
                if (this.timerObjects - this.rockObject.getLastRockTimer() >= 2.0F) {
                    this.rockObject.makeRock();
                    this.rockObject.setLastRockTimer(this.timerObjects);
                }
                if (this.rockObject.isFirstRockHit() && this.timerObjects - this.rockObject.getFirstRockHitTimer() >= 5.0F) {
                    this.rockObject.setFirstRockHitFalse();
                    Preferences preferences = this.prefs;
                    preferences.putInteger("healedFromStones", preferences.getInteger("healedFromStones", 0) + 1);
                    this.prefs.flush();
                    if (this.prefs.getInteger("healStonesAtmAchievement", 0) < 7)
                        this.achievementsObject.checkHealStones(this.prefs.getInteger("healedFromStones", 0));
                }
            }
            if (this.bombsGo && this.timerObjects - this.bombObject.getLastBombTimer() >= (this.random.nextInt(2001) + 5000) / 1000.0F) {
                this.bombObject.makeBomb();
                this.bombObject.setLastBombTimer(this.timerObjects);
            }
            if (this.spikesGo && this.timerObjects - this.spikesObject.getLastSpikeDownTimer() >= this.spikesObject.getSpikeDownTime() / 1000.0F) {
                this.spikesObject.makeSpikeDown();
                this.spikesObject.setSpikeDownTime(this.random.nextInt(6001) + 4000);
                this.spikesObject.setLastSpikeDownTimer(this.timerObjects);
            }
            if (this.rocketsGo) {
                if (this.timerObjects - this.rocketObject.getLastArrowRedTimer() >= 3.0F) {
                    Rocket rocket = this.rocketObject;
                    rocket.makeRocket(rocket.getRedHeight());
                    rocket = this.rocketObject;
                    rocket.setRedHeight(rocket.makeRocketArrowRed());
                    this.rocketObject.setLastArrowRedTimer(this.timerObjects);
                }
                if (this.timerObjects - this.rocketObject.getLastArrowRedTimer() >= 2.0F && this.yellowGo && this.timerObjects - this.rocketObject.getLastArrowYellowTimer() >= 3.0F) {
                    Rocket rocket = this.rocketObject;
                    rocket.makeRocket(rocket.getYellowHeight());
                    rocket = this.rocketObject;
                    rocket.setYellowHeight(rocket.makeRocketArrowYellow());
                    this.rocketObject.setLastArrowYellowTimer(this.timerObjects);
                }
                if (this.timerObjects - this.rocketObject.getLastArrowRedTimer() >= 1.0F && this.greenGo && this.timerObjects - this.rocketObject.getLastArrowGreenTimer() >= 3.0F) {
                    Rocket rocket = this.rocketObject;
                    rocket.makeRocket(rocket.getGreenHeight());
                    rocket = this.rocketObject;
                    rocket.setGreenHeight(rocket.makeRocketArrowGreen());
                    this.rocketObject.setLastArrowGreenTimer(this.timerObjects);
                }
            }
            float f = this.timerObjects;
            if (f >= 10.0F && f - this.collectiblesTimer >= 1.5F) {
                this.collectiblesChance = this.random.nextFloat() * 99.0F + 1.0F;
                this.collectiblesTimer = this.timerObjects;
            }
            if (this.collectiblesChance <= this.normalChance + this.prefs.getInteger("spawnRateUpgraded")) {
                i = this.collectiblesChoice;
                if (i != 1) {
                    if (i != 2) {
                        if (i == 3 && !this.coinMagnetObject.isHasCoinMagnet())
                            this.coinMagnetObject.makeCoinMagnet();
                    } else if (!this.coinRushObject.isCoinRush()) {
                        this.coinRushObject.makeSpeedCoin();
                    }
                } else if (!this.shieldObject.isHasShield()) {
                    this.shieldObject.makeShield();
                }
                this.collectiblesChance = 100.0F;
                this.collectiblesChoice = this.random.nextInt(3) + 1;
            }
            if (this.shieldObject.isHasShield()) {
                if (!this.onceShield) {
                    this.normalChance /= 2.0F;
                    this.onceShield = true;
                }
                if (this.timerObjects - this.shieldObject.getShieldOnTimer() >= this.prefs.getInteger("shieldUpgraded") + 7.0F + this.shieldUpgrade && this.timerObjects - this.shieldObject.getShieldOnTimer() < this.prefs.getInteger("shieldUpgraded") + 10.0F + this.shieldUpgrade)
                    if (this.shieldObject.getShieldStatePause() < 5) {
                        Shield shield = this.shieldObject;
                        shield.setShieldStatePause(shield.getShieldStatePause() + 1);
                    } else {
                        this.shieldObject.setShieldStatePause(0);
                        Shield shield = this.shieldObject;
                        shield.setShieldState(1 - shield.getShieldState());
                    }
                if (this.timerObjects - this.shieldObject.getShieldOnTimer() >= this.prefs.getInteger("shieldUpgraded") + 10.0F + this.shieldUpgrade) {
                    this.shieldObject.setHasShieldFalse();
                    this.normalChance *= 2.0F;
                    this.onceShield = false;
                }
            }
            if (!this.coinRushObject.isCoinRush() && this.timerObjects - this.coinRushObject.getCoinRushOnTimer() >= this.prefs.getInteger("coinRushUpgraded") + 8.0F + this.coinRushUpgrade && this.timerObjects - this.coinRushObject.getCoinRushOnTimer() < this.prefs.getInteger("coinRushUpgraded") + 8.2F + this.coinRushUpgrade) {
                Coin coin = this.coinObject;
                coin.setCoinSpeed(coin.worldXToScreenX(5.0F));
                this.coinRushObject.setCoinRushEnd(true);
            }
            if (this.coinRushObject.isCoinRush()) {
                if (!this.onceCoinRush) {
                    this.normalChance /= 2.0F;
                    this.onceCoinRush = true;
                }
                this.coinObject.setCoinTime(100);
                Coin coin = this.coinObject;
                coin.setCoinSpeed(coin.worldXToScreenX(10.0F));
                if (this.timerObjects - this.coinRushObject.getCoinRushOnTimer() >= this.prefs.getInteger("coinRushUpgraded") + 5.0F + this.coinRushUpgrade) {
                    this.coinRushObject.setCoinRushFalse();
                    this.coinRushObject.setCoinRushEnd(false);
                    this.normalChance *= 2.0F;
                    this.onceCoinRush = false;
                }
            }
            if (this.coinMagnetObject.isHasCoinMagnet()) {
                if (!this.onceMagnet) {
                    this.normalChance /= 2.0F;
                    this.onceMagnet = true;
                }
                if (this.timerObjects - this.coinMagnetObject.getCoinMagnetOnTimer() >= this.prefs.getInteger("magnetUpgraded") + 10.0F + this.magnetUpgrade) {
                    this.coinMagnetObject.setHasCoinMagnet();
                    this.normalChance *= 2.0F;
                    this.onceMagnet = false;
                }
            }
            if (this.coinMagnetObject.isHasCoinMagnet()) {
                this.coinObject.drawCoinMagnetized(paramSpriteBatch, this.playerObject.getPlayerXRect(), this.playerObject.getPlayerY(), this.playerObject.getPlayerWidthRect(), this.playerObject.getPlayerHeightRect(), this.pauseGame);
            } else {
                this.coinObject.drawCoin(paramSpriteBatch, this.pauseGame);
            }
            this.coinObject.removeCoin();
            i = this.stageNumber;
            if (i == 1) {
                this.rockObject.drawRock(paramSpriteBatch, this.pauseGame, this.shapeRenderer, 6.6F);
                this.bombObject.drawBomb(paramSpriteBatch, this.pauseGame, this.shapeRenderer, 6.6F);
            } else if (i == 2) {
                this.rockObject.drawRock(paramSpriteBatch, this.pauseGame, this.shapeRenderer, 8.6F);
                this.bombObject.drawBomb(paramSpriteBatch, this.pauseGame, this.shapeRenderer, 8.6F);
                this.spikesObject.drawSpikeDown(paramSpriteBatch, this.pauseGame, this.shapeRenderer);
            }
            if (this.rocketsGo) {
                this.rocketObject.redArrowState(this.timerObjects);
                this.rocketObject.yellowArrowState(this.timerObjects);
                this.rocketObject.greenArrowState(this.timerObjects);
                this.rocketObject.drawArrows(this.firstArrowsAppearanceRed, this.firstArrowsAppearanceYellow, this.firstArrowsAppearanceGreen, paramSpriteBatch);
                i = this.stageNumber;
                if (i == 1) {
                    this.rocketObject.drawRocket(paramSpriteBatch, this.pauseGame, this.shapeRenderer, 12.45F);
                } else if (i == 2) {
                    this.rocketObject.drawRocket(paramSpriteBatch, this.pauseGame, this.shapeRenderer, 15.0F);
                }
            }
            this.shieldObject.drawShieldCollectible(paramSpriteBatch, this.pauseGame, this.shapeRenderer);
            this.coinRushObject.drawSpeedCoinCollectible(paramSpriteBatch, this.pauseGame);
            this.coinMagnetObject.drawCoinMagnetCollectible(paramSpriteBatch, this.pauseGame);
            this.playerObject.drawPlayerRun(paramSpriteBatch, this.shieldObject, this.rockObject, this.pauseGame, this.shapeRenderer, this.prefs);
            f = this.timerGame;
            if (f >= 30.0F && f < 60.0F) {
                this.rocksGo = false;
                this.rockObject.setFirstRockHitFalse();
                this.bombsGo = true;
            }
            if (this.stageNumber == 2)
                this.spikesGo = true;
            f = this.timerGame;
            if (f >= 60.0F && f < 100.0F) {
                this.bombsGo = false;
                this.rocketsGo = true;
                rocketFirstTime();
            }
            i = this.stageNumber;
            if (i == 1) {
                timingStory("storyModeCity");
            } else if (i == 2) {
                timingStory("storyModeDesert");
            }
            f = this.timerGame;
            if ((f - this.scoreTimer) >= 0.3D) {
                this.score++;
                this.scoreTimer = f;
            }
        } else if (i == 0) {
            i = getActiveBackgroundIdx(this.bgCords[0]);
            paramSpriteBatch.draw(this.background[i], this.bgCords[i], 0.0F, worldXToScreenX(500.0F), worldYToScreenY(1000.0F));
            TextureAtlas.AtlasRegion[] arrayOfAtlasRegion = this.background;
            paramSpriteBatch.draw(arrayOfAtlasRegion[++i], this.bgCords[i] - worldXToScreenX(2.0F), 0.0F, worldXToScreenX(500.0F), worldYToScreenY(1000.0F));
            this.playerObject.drawPlayerStart(paramSpriteBatch, this.prefs);
            this.stage.getActors().get(1).setVisible(false);
            this.showScoreAndCoinLabel = false;
            if (Gdx.input.justTouched()) {
                this.gameState = 1;
                this.stage.getActors().get(1).setVisible(true);
                this.showScoreAndCoinLabel = true;
            }
        } else if (i == 2) {
            i = getActiveBackgroundIdx(this.bgCords[0]);
            paramSpriteBatch.draw(this.background[i], this.bgCords[i], 0.0F, worldXToScreenX(500.0F), worldYToScreenY(1000.0F));
            TextureAtlas.AtlasRegion[] arrayOfAtlasRegion = this.background;
            paramSpriteBatch.draw(arrayOfAtlasRegion[++i], this.bgCords[i] - worldXToScreenX(2.0F), 0.0F, worldXToScreenX(500.0F), worldYToScreenY(1000.0F));
            this.playerObject.drawPlayerFaint(paramSpriteBatch, this.pauseGame, this.prefs);
            boolean bool1 = this.mAdsController.getAdLoaded();
            boolean bool2 = this.mAdsController.getRewardReceived();
            this.stage.getActors().get(1).setVisible(false);
            this.stage.getActors().get(6).setVisible(true);
            this.showScoreAndCoinLabel = false;
            if (this.twoTimesAd < 2 && bool1) {
                this.stage.getActors().get(6).setTouchable(Touchable.enabled);
                this.continueWithAdsButton.setStyle(this.continueWithAdsButtonStyle);
            } else if (this.twoTimesAd < 2) {
                this.stage.getActors().get(6).setTouchable(Touchable.disabled);
                this.continueWithAdsButton.setStyle(this.continueWithAdsWaitButtonStyle);
            }
            if (bool2) {
                this.twoTimesAd++;
                this.gameState = 0;
                resetAfterRevive();
                this.stage.getActors().get(0).setVisible(false);
                this.stage.getActors().get(6).setVisible(false);
                this.stage.getActors().get(7).setVisible(false);
                this.stage.getActors().get(8).setVisible(false);
                this.mAdsController.setRewardReceived(false);
                this.stage.getActors().get(23).setVisible(false);
                Preferences preferences = this.prefs;
                preferences.putInteger("revivedAfterDeath", preferences.getInteger("revivedAfterDeath", 0) + 1);
                preferences = this.prefs;
                preferences.putInteger("adsWatched", preferences.getInteger("adsWatched", 0) + 1);
                this.prefs.flush();
            } else {
                this.stage.getActors().get(8).setVisible(true);
                this.stage.getActors().get(7).setVisible(true);
                if (this.showDiamondsCount)
                    this.stage.getActors().get(23).setVisible(true);
            }
        } else if (i == 3) {
            if (this.timerGame <= 100.8F)
                for (i = 0; i < 5; i++) {
                    float[] arrayOfFloat = this.bgCords;
                    arrayOfFloat[i] = arrayOfFloat[i] - (int) worldXToScreenX(3.0F) * this.dt * 60.0F;
                }
            if (this.bgCords[4] <= 0.0F)
                for (i = 0; i < 5; i++)
                    this.bgCords[i] = (int) worldXToScreenX((i * 500));
            i = getActiveBackgroundIdx(this.bgCords[0]);
            paramSpriteBatch.draw(this.background[i], this.bgCords[i], 0.0F, worldXToScreenX(500.0F), worldYToScreenY(1000.0F));
            TextureAtlas.AtlasRegion[] arrayOfAtlasRegion = this.background;
            paramSpriteBatch.draw(arrayOfAtlasRegion[++i], this.bgCords[i] - worldXToScreenX(2.0F), 0.0F, worldXToScreenX(500.0F), worldYToScreenY(1000.0F));
            this.playerObject.drawPlayerWin(paramSpriteBatch);
            this.villainObject.drawVillain(paramSpriteBatch, this.pauseGame);
            float f = this.timerGame;
            if (f >= 102.0F) {
                this.showEndStory = true;
            } else {
                this.timerGame = f + Gdx.graphics.getDeltaTime();
            }
        }
        this.coinObject.coinCollision(this.playerObject.getPlayerRectangle(), this.prefs.getBoolean("doubleCoins", false));
        if (!this.shieldObject.isHasShield()) {
            if (this.rockObject.isFirstRockHit()) {
                this.gameState = this.rockObject.rockCollisionSecond(this.playerObject.getPlayerRectangle(), this.gameState, this.prefs);
            } else {
                this.rockObject.rockCollisionFirst(this.playerObject.getPlayerRectangle(), this.timerObjects);
            }
            this.gameState = this.bombObject.bombCollision(this.playerObject.getPlayerRectangle(), this.gameState, this.prefs);
            this.gameState = this.spikesObject.spikeDownCollision(this.playerObject.getPlayerRectangle(), this.gameState, prefs);
            this.gameState = this.rocketObject.rocketCollision(this.playerObject.getPlayerRectangle(), this.gameState, this.prefs);
        }
        this.shieldObject.shieldCollision(this.playerObject.getPlayerRectangle(), this.timerObjects, this.prefs);
        this.coinRushObject.speedCoinCollision(this.playerObject.getPlayerRectangle(), this.timerObjects, this.prefs);
        this.coinMagnetObject.coinMagnetCollision(this.playerObject.getPlayerRectangle(), this.timerObjects, this.prefs);
        if (this.showScoreAndCoinLabel) {
            paramSpriteBatch.draw(this.scoreAndCoinBackground, worldXToScreenX(-25.0F), worldYToScreenY(950.0F), worldXToScreenX(125.0F), worldYToScreenY(50.0F));
            paramSpriteBatch.draw(this.scoreAndCoinBackground, worldXToScreenX(-25.0F), worldYToScreenY(900.0F), worldXToScreenX(125.0F), worldYToScreenY(50.0F));
            this.scoreAndCoinFont.draw(paramSpriteBatch, String.valueOf(this.score), worldXToScreenX(10.0F), worldYToScreenY(985.0F));
            this.scoreAndCoinFont.draw(paramSpriteBatch, String.valueOf(this.coinObject.getCoinCount()), worldXToScreenX(30.0F), worldYToScreenY(935.0F));
            paramSpriteBatch.draw(this.coin, worldXToScreenX(0.0F), worldYToScreenY(910.0F), worldXToScreenX(30.0F), worldYToScreenY(30.0F));
        }
        paramSpriteBatch.end();
        this.shapeRenderer.end();
        this.stage.act(Gdx.graphics.getDeltaTime());
        this.stage.draw();
    }

    public void update(float paramFloat) {
    }

    private int getActiveBackgroundIdx(float paramFloat) {
        return (int) Math.floor(Math.abs(paramFloat / this.screenWidth));
    }

    private void resetAfterRevive() {
        this.playerObject.resetPlayerStats(this.prefs);
        this.coinObject.resetCoinStats();
        this.rockObject.resetRockStats();
        this.bombObject.resetBombStats();
        this.spikesObject.resetSpikeDownStats();
        this.rocketObject.resetRocketStats();
        this.shieldObject.resetShieldStats();
        this.coinRushObject.resetCoinRushStats();
        this.coinMagnetObject.resetCoinMagnetState();
        this.timerObjects = 0.0F;
        this.normalChance = 10.0F;
        this.firstArrowsAppearanceRed = false;
        this.firstArrowsAppearanceYellow = false;
        this.firstArrowsAppearanceGreen = false;
        this.firstTimeArrowsRed = true;
        this.firstTimeArrowsYellow = true;
        this.firstTimeArrowsGreen = true;
        this.yellowGo = false;
        this.greenGo = false;
    }

    private void resetStatsAndRestart() {
        this.gameState = 0;
        this.score = 0;
        for (byte b = 0; b < 5; b++)
            this.bgCords[b] = (int) worldXToScreenX((b * 500));
        this.playerObject.resetPlayerStats(this.prefs);
        this.coinObject.resetCoinStats();
        this.rockObject.resetRockStats();
        this.bombObject.resetBombStats();
        this.spikesObject.resetSpikeDownStats();
        this.rocketObject.resetRocketStats();
        this.shieldObject.resetShieldStats();
        this.coinRushObject.resetCoinRushStats();
        this.coinMagnetObject.resetCoinMagnetState();
        this.timerObjects = 0.0F;
        this.timerGame = 0.0F;
        this.collectiblesTimer = 0.0F;
        this.normalChance = 10.0F;
        this.rocksGo = true;
        this.bombsGo = false;
        this.spikesGo = false;
        this.rocketsGo = false;
        this.firstArrowsAppearanceRed = false;
        this.firstArrowsAppearanceYellow = false;
        this.firstArrowsAppearanceGreen = false;
        this.firstTimeArrowsRed = true;
        this.firstTimeArrowsYellow = true;
        this.firstTimeArrowsGreen = true;
        this.yellowGo = false;
        this.greenGo = false;
        this.scoreTimer = 0.0F;
        this.twoTimesAd = 0;
    }

    private void rocketFirstTime() {
        if (this.firstTimeArrowsRed) {
            this.firstArrowsAppearanceRed = true;
            this.rocketObject.setLastArrowRedTimer(this.timerObjects);
            this.firstTimeArrowsRed = false;
        }
        if (this.firstTimeArrowsYellow && this.timerObjects - this.rocketObject.getLastArrowRedTimer() >= 2.0F) {
            this.firstArrowsAppearanceYellow = true;
            this.rocketObject.setLastArrowYellowTimer(this.timerObjects);
            this.firstTimeArrowsYellow = false;
            this.yellowGo = true;
        }
        if (this.firstTimeArrowsGreen && this.timerObjects - this.rocketObject.getLastArrowRedTimer() >= 1.0F) {
            this.firstArrowsAppearanceGreen = true;
            this.rocketObject.setLastArrowGreenTimer(this.timerObjects);
            this.firstTimeArrowsGreen = false;
            this.greenGo = true;
        }
    }

    private void showEndStory(int paramInt) {
        int i = this.storyEndNumber;
        if (i == 0) {
            this.stage.getActors().get(14).setVisible(true);
            this.stage.getActors().get(15).setVisible(false);
            this.stage.getActors().get(16).setVisible(true);
            this.stage.getActors().get(17).setVisible(false);
            this.stage.getActors().get(27).setVisible(true);
        } else if (i == 1) {
            this.stage.getActors().get(14).setVisible(false);
            this.stage.getActors().get(15).setVisible(true);
            this.stage.getActors().get(17).setVisible(true);
            if (paramInt == 1) {
                this.storyEndSecond.setDrawable(new TextureRegionDrawable(new TextureRegion(this.manager.get("story_city_end_second.png", Texture.class))));
            } else if (paramInt == 2) {
                this.storyEndSecond.setDrawable(new TextureRegionDrawable(new TextureRegion(this.manager.get("story_city_end_second.png", Texture.class))));
            }
        } else if (i == 2) {
            if (paramInt == 1) {
                this.storyEndSecond.setDrawable(new TextureRegionDrawable(new TextureRegion(this.manager.get("story_city_end_third.png", Texture.class))));
            } else if (paramInt == 2) {
                this.storyEndSecond.setDrawable(new TextureRegionDrawable(new TextureRegion(this.manager.get("story_city_end_third.png", Texture.class))));
            }
        } else if (i == 3) {
            if (paramInt == 1) {
                this.storyEndSecond.setDrawable(new TextureRegionDrawable(new TextureRegion(this.manager.get("story_city_end_fourth.png", Texture.class))));
            } else if (paramInt == 2) {
                this.storyEndSecond.setDrawable(new TextureRegionDrawable(new TextureRegion(this.manager.get("story_city_end_fourth.png", Texture.class))));
            }
        }
    }

    private void showStartStory(int paramInt) {
        int i = this.storyStartNumber;
        if (i == 0) {
            this.stage.getActors().get(12).setVisible(true);
            this.stage.getActors().get(13).setVisible(false);
            this.stage.getActors().get(16).setVisible(true);
            this.stage.getActors().get(17).setVisible(false);
            this.stage.getActors().get(27).setVisible(true);
        } else if (i == 1) {
            this.stage.getActors().get(12).setVisible(false);
            this.stage.getActors().get(13).setVisible(true);
            this.stage.getActors().get(17).setVisible(true);
            if (paramInt == 1) {
                this.storyStartSecond.setDrawable(new TextureRegionDrawable(new TextureRegion(this.manager.get("story_city_start_second.png", Texture.class))));
            } else if (paramInt == 2) {
                this.storyStartSecond.setDrawable(new TextureRegionDrawable(new TextureRegion(this.manager.get("story_city_start_second.png", Texture.class))));
            }
        } else if (i == 2) {
            if (paramInt == 1) {
                this.storyStartSecond.setDrawable(new TextureRegionDrawable(new TextureRegion(this.manager.get("story_city_start_third.png", Texture.class))));
            } else if (paramInt == 2) {
                this.storyStartSecond.setDrawable(new TextureRegionDrawable(new TextureRegion(this.manager.get("story_city_start_third.png", Texture.class))));
            }
        } else if (i == 3) {
            if (paramInt == 1) {
                this.storyStartSecond.setDrawable(new TextureRegionDrawable(new TextureRegion(this.manager.get("story_city_start_fourth.png", Texture.class))));
            } else if (paramInt == 2) {
                this.storyStartSecond.setDrawable(new TextureRegionDrawable(new TextureRegion(this.manager.get("story_city_start_fourth.png", Texture.class))));
            }
        }
    }

    private void timingStory(String paramString) {
        if (this.prefs.getFloat(paramString) >= 100.0F) {
            float f = this.timerGame;
            if (f >= 100.0F && f < 160.0F) {
                this.rocketsGo = false;
                this.rocksGo = true;
                this.bombsGo = true;
            }
            f = this.timerGame;
            if (f >= 160.0F && f < 240.0F) {
                this.rocksGo = false;
                this.rockObject.setFirstRockHitFalse();
                this.rocketsGo = true;
                rocketFirstTime();
            }
            if (this.timerGame >= 240.0F)
                this.rocksGo = true;
        } else if (this.timerGame > 100.0F) {
            this.gameState = 3;
        }
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
