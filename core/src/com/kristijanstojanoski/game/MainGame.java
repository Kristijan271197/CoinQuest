//package com.kristijanstojanoski.game;
//
//import States.Achievements;
//import States.AdsController;
//import States.ChooseStageMenu;
//import States.EndGame;
//import States.GameStateManager;
//import States.MusicSounds;
//import States.State;
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.InputProcessor;
//import com.badlogic.gdx.Preferences;
//import com.badlogic.gdx.assets.AssetManager;
//import com.badlogic.gdx.graphics.Color;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.Batch;
//import com.badlogic.gdx.graphics.g2d.BitmapFont;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.graphics.g2d.TextureAtlas;
//import com.badlogic.gdx.graphics.g2d.TextureRegion;
//import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
//import com.badlogic.gdx.scenes.scene2d.Actor;
//import com.badlogic.gdx.scenes.scene2d.EventListener;
//import com.badlogic.gdx.scenes.scene2d.InputEvent;
//import com.badlogic.gdx.scenes.scene2d.Stage;
//import com.badlogic.gdx.scenes.scene2d.Touchable;
//import com.badlogic.gdx.scenes.scene2d.ui.Button;
//import com.badlogic.gdx.scenes.scene2d.ui.Image;
//import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
//import com.badlogic.gdx.scenes.scene2d.ui.Label;
//import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
//import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
//import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
//import com.badlogic.gdx.utils.viewport.ScreenViewport;
//import com.badlogic.gdx.utils.viewport.Viewport;
//import java.util.Random;
//
//public class MainGame extends State {
//  private static final int BLURRY_BACKGROUND = 0;
//
//  private static final int CONTINUE_WITH_ADS_BUTTON = 6;
//
//  private static final int CONTINUE_WITH_DIAMONDS_BUTTON = 7;
//
//  private static final int END_GAME_BUTTON = 8;
//
//  private static final String FIRST_TIME_END_STORY_CITY = "firstTimeEndStoryCity";
//
//  private static final String FIRST_TIME_END_STORY_DESERT = "firstTimeEndStoryDesert";
//
//  private static final String FIRST_TIME_START_STORY_CITY = "firstTimeStartStoryCity";
//
//  private static final String FIRST_TIME_START_STORY_DESERT = "firstTimeStartStoryDesert";
//
//  private static final String FIRST_TIME_TUTORIAL = "firstTimeTutorial";
//
//  private static final String HEALED_FROM_STONES = "healedFromStones";
//
//  private static final int MENU_BUTTON = 5;
//
//  private static final int PAUSE_BUTTON = 1;
//
//  private static final int PLAY_BUTTON = 3;
//
//  private static final int RESTART_BUTTON = 4;
//
//  public static final String REVIVED_AFTER_DEATH = "revivedAfterDeath";
//
//  private static final int SOUND_BUTTON = 2;
//
//  private Achievements achievementsObject;
//
//  private TextureAtlas.AtlasRegion[] background = new TextureAtlas.AtlasRegion[5];
//
//  private float[] bgCords;
//
//  private Bomb bombObject;
//
//  private boolean bombsGo = false;
//
//  private TextureAtlas.AtlasRegion coin;
//
//  private CoinMagnet coinMagnetObject;
//
//  private Coin coinObject;
//
//  private CoinRush coinRushObject;
//
//  private int coinRushUpgrade;
//
//  private int coinSpawnUpgrade;
//
//  private float collectiblesChance;
//
//  private int collectiblesChoice;
//
//  private float collectiblesTimer = 0.0F;
//
//  private ImageButton continueWithAdsButton;
//
//  private ImageButton.ImageButtonStyle continueWithAdsButtonStyle;
//
//  private ImageButton.ImageButtonStyle continueWithAdsWaitButtonStyle;
//
//  private int continueWithDiamonds = 1;
//
//  private float dt = Gdx.graphics.getDeltaTime();
//
//  private boolean firstArrowsAppearanceGreen = false;
//
//  private boolean firstArrowsAppearanceRed = false;
//
//  private boolean firstArrowsAppearanceYellow = false;
//
//  private boolean firstTimeArrowsGreen = true;
//
//  private boolean firstTimeArrowsRed = true;
//
//  private boolean firstTimeArrowsYellow = true;
//
//  private int gameState = -1;
//
//  private boolean greenGo = false;
//
//  private AdsController mAdsController;
//
//  private int magnetUpgrade;
//
//  private AssetManager manager;
//
//  private ImageButton musicButton;
//
//  private ImageButton.ImageButtonStyle musicButtonStyle;
//
//  private MusicSounds musicSoundsObject;
//
//  private ImageButton.ImageButtonStyle noMusicButtonStyle;
//
//  private ImageButton.ImageButtonStyle noSoundButtonStyle;
//
//  private float normalChance;
//
//  private boolean onceCoinRush;
//
//  private boolean onceMagnet;
//
//  private boolean onceShield;
//
//  private boolean pauseGame;
//
//  private Player playerObject;
//
//  private Preferences prefs;
//
//  private Random random;
//
//  private Rock rockObject;
//
//  private Rocket rocketObject;
//
//  private boolean rocketsGo = false;
//
//  private boolean rocksGo = true;
//
//  private int score = 0;
//
//  private TextureAtlas.AtlasRegion scoreAndCoinBackground;
//
//  private BitmapFont scoreAndCoinFont;
//
//  private float scoreTimer = 0.0F;
//
//  private final float screenWidth = worldXToScreenX(500.0F);
//
//  private ShapeRenderer shapeRenderer;
//
//  private Shield shieldObject;
//
//  private int shieldUpgrade;
//
//  private boolean showDiamondsCount = true;
//
//  private boolean showEndStory;
//
//  private boolean showScoreAndCoinLabel = false;
//
//  private ImageButton soundButton;
//
//  private ImageButton.ImageButtonStyle soundButtonStyle;
//
//  private boolean spikesGo = false;
//
//  private Spikes spikesObject;
//
//  private Stage stage;
//
//  private int stageNumber;
//
//  private int storyEndNumber = 0;
//
//  private Image storyEndSecond;
//
//  private int storyStartNumber = 0;
//
//  private Image storyStartSecond;
//
//  private float timerGame = 0.0F;
//
//  private float timerObjects = 0.0F;
//
//  private int twoTimesAd = 0;
//
//  private Villain villainObject;
//
//  private boolean yellowGo = false;
//
//  public MainGame(final GameStateManager gsm, final AdsController adsController, final AssetManager manager, final int stageNumber) {
//    super(gsm);
//    this.manager = manager;
//    TextureAtlas textureAtlas1 = (TextureAtlas)manager.get("main_menu/main_menu.atlas", TextureAtlas.class);
//    TextureAtlas textureAtlas2 = (TextureAtlas)manager.get("shared/shared.atlas", TextureAtlas.class);
//    TextureAtlas textureAtlas3 = (TextureAtlas)manager.get("main_game/main_game.atlas", TextureAtlas.class);
//    this.musicSoundsObject = new MusicSounds(manager);
//    if (stageNumber == 1) {
//      this.background[0] = textureAtlas3.findRegion("city_background", 1);
//      this.background[1] = textureAtlas3.findRegion("city_background", 2);
//      this.background[2] = textureAtlas3.findRegion("city_background", 3);
//      this.background[3] = textureAtlas3.findRegion("city_background", 4);
//      this.background[4] = textureAtlas3.findRegion("city_background", 1);
//    } else if (stageNumber == 2) {
//      this.background[0] = textureAtlas3.findRegion("desert_background", 1);
//      this.background[1] = textureAtlas3.findRegion("desert_background", 2);
//      this.background[2] = textureAtlas3.findRegion("desert_background", 3);
//      this.background[3] = textureAtlas3.findRegion("desert_background", 4);
//      this.background[4] = textureAtlas3.findRegion("desert_background", 1);
//    }
//    this.bgCords = new float[5];
//    for (byte b = 0; b < 5; b++)
//      this.bgCords[b] = (int)worldXToScreenX((b * 500));
//    this.stage = new Stage((Viewport)new ScreenViewport());
//    Gdx.input.setInputProcessor((InputProcessor)this.stage);
//    this.mAdsController = adsController;
//    this.stageNumber = stageNumber;
//    Preferences preferences = Gdx.app.getPreferences("prefs");
//    this.prefs = preferences;
//    this.shieldUpgrade = 0;
//    this.magnetUpgrade = 0;
//    this.coinRushUpgrade = 0;
//    this.coinSpawnUpgrade = 0;
//    if (preferences.getInteger("costumeSelectedGame") == 1) {
//      this.shieldUpgrade = 5;
//    } else if (this.prefs.getInteger("costumeSelectedGame") == 2) {
//      this.coinRushUpgrade = 5;
//    } else if (this.prefs.getInteger("costumeSelectedGame") == 5) {
//      this.magnetUpgrade = 5;
//    } else if (this.prefs.getInteger("costumeSelectedGame") == 6) {
//      this.coinSpawnUpgrade = 5;
//    } else if (this.prefs.getInteger("costumeSelectedGame") == 7) {
//      this.shieldUpgrade = 5;
//      this.magnetUpgrade = 5;
//      this.coinRushUpgrade = 5;
//    }
//    Image image2 = new Image((Drawable)new TextureRegionDrawable(new TextureRegion((Texture)manager.get("story_city_start_first.png", Texture.class))));
//    image2.setSize(worldXToScreenX(500.0F), worldYToScreenY(1000.0F));
//    image2.setPosition(worldXToScreenX(0.0F), worldYToScreenY(0.0F));
//    if (stageNumber == 2)
//      image2.setDrawable((Drawable)new TextureRegionDrawable(new TextureRegion((Texture)manager.get("story_city_start_first.png", Texture.class))));
//    Image image3 = new Image((Drawable)new TextureRegionDrawable(new TextureRegion((Texture)manager.get("story_city_start_second.png", Texture.class))));
//    this.storyStartSecond = image3;
//    image3.setSize(worldXToScreenX(500.0F), worldYToScreenY(1000.0F));
//    this.storyStartSecond.setPosition(worldXToScreenX(0.0F), worldYToScreenY(0.0F));
//    if (stageNumber == 2)
//      this.storyStartSecond.setDrawable((Drawable)new TextureRegionDrawable(new TextureRegion((Texture)manager.get("story_city_start_second.png", Texture.class))));
//    image3 = new Image((Drawable)new TextureRegionDrawable(new TextureRegion((Texture)manager.get("story_city_end_first.png", Texture.class))));
//    image3.setSize(worldXToScreenX(500.0F), worldYToScreenY(1000.0F));
//    image3.setPosition(worldXToScreenX(0.0F), worldYToScreenY(0.0F));
//    if (stageNumber == 2)
//      image3.setDrawable((Drawable)new TextureRegionDrawable(new TextureRegion((Texture)manager.get("story_city_end_first.png", Texture.class))));
//    Image image4 = new Image((Drawable)new TextureRegionDrawable(new TextureRegion((Texture)manager.get("story_city_end_second.png", Texture.class))));
//    this.storyEndSecond = image4;
//    image4.setSize(worldXToScreenX(500.0F), worldYToScreenY(1000.0F));
//    this.storyEndSecond.setPosition(worldXToScreenX(0.0F), worldYToScreenY(0.0F));
//    if (stageNumber == 2)
//      this.storyEndSecond.setDrawable((Drawable)new TextureRegionDrawable(new TextureRegion((Texture)manager.get("story_city_end_second.png", Texture.class))));
//    ImageButton.ImageButtonStyle imageButtonStyle2 = new ImageButton.ImageButtonStyle();
//    imageButtonStyle2.up = (Drawable)new TextureRegionDrawable(new TextureRegion((Texture)manager.get("arrow_story_right.png", Texture.class)));
//    imageButtonStyle2.down = (Drawable)new TextureRegionDrawable(new TextureRegion((Texture)manager.get("arrow_story_right.png", Texture.class)));
//    ImageButton imageButton3 = new ImageButton(imageButtonStyle2);
//    imageButton3.setPosition(worldXToScreenX(450.0F), worldYToScreenY(10.0F));
//    imageButton3.setSize(worldXToScreenX(40.0F), worldYToScreenY(40.0F));
//    imageButton3.addListener((EventListener)new ClickListener() {
//          public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
//            super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
//            MainGame.this.musicSoundsObject.playButtonClick();
//            if (MainGame.this.showEndStory) {
//              if (MainGame.this.storyEndNumber == 3) {
//                ((Actor)MainGame.this.stage.getActors().get(15)).setVisible(false);
//                ((Actor)MainGame.this.stage.getActors().get(16)).setVisible(false);
//                ((Actor)MainGame.this.stage.getActors().get(17)).setVisible(false);
//                ((Actor)MainGame.this.stage.getActors().get(18)).setVisible(true);
//                ((Actor)MainGame.this.stage.getActors().get(19)).setVisible(true);
//                ((Actor)MainGame.this.stage.getActors().get(20)).setVisible(true);
//                ((Actor)MainGame.this.stage.getActors().get(27)).setVisible(false);
//                ((Actor)MainGame.this.stage.getActors().get(0)).setVisible(true);
//                ((Actor)MainGame.this.stage.getActors().get(1)).setVisible(false);
//                MainGame.access$208(MainGame.this);
//              } else {
//                MainGame.access$208(MainGame.this);
//              }
//            } else if (MainGame.this.storyStartNumber == 3) {
//              ((Actor)MainGame.this.stage.getActors().get(13)).setVisible(false);
//              ((Actor)MainGame.this.stage.getActors().get(16)).setVisible(false);
//              ((Actor)MainGame.this.stage.getActors().get(17)).setVisible(false);
//              ((Actor)MainGame.this.stage.getActors().get(27)).setVisible(false);
//              MainGame.access$408(MainGame.this);
//              param1Int1 = stageNumber;
//              if (param1Int1 == 1) {
//                if (MainGame.this.prefs.getBoolean("firstTimeTutorial", true)) {
//                  ((Actor)MainGame.this.stage.getActors().get(10)).setVisible(true);
//                  ((Actor)MainGame.this.stage.getActors().get(11)).setVisible(true);
//                } else {
//                  MainGame.access$602(MainGame.this, 0);
//                }
//              } else if (param1Int1 == 2) {
//                MainGame.access$602(MainGame.this, 0);
//              }
//            } else {
//              MainGame.access$408(MainGame.this);
//            }
//          }
//        });
//    ImageButton.ImageButtonStyle imageButtonStyle3 = new ImageButton.ImageButtonStyle();
//    imageButtonStyle3.up = (Drawable)new TextureRegionDrawable(new TextureRegion((Texture)manager.get("arrow_story_left.png", Texture.class)));
//    imageButtonStyle3.down = (Drawable)new TextureRegionDrawable(new TextureRegion((Texture)manager.get("arrow_story_left.png", Texture.class)));
//    ImageButton imageButton4 = new ImageButton(imageButtonStyle3);
//    imageButton4.setPosition(worldXToScreenX(10.0F), worldYToScreenY(10.0F));
//    imageButton4.setSize(worldXToScreenX(40.0F), worldYToScreenY(40.0F));
//    imageButton4.addListener((EventListener)new ClickListener() {
//          public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
//            super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
//            MainGame.this.musicSoundsObject.playButtonClick();
//            if (MainGame.this.showEndStory) {
//              MainGame.access$210(MainGame.this);
//            } else {
//              MainGame.access$410(MainGame.this);
//            }
//          }
//        });
//    ImageButton.ImageButtonStyle imageButtonStyle4 = new ImageButton.ImageButtonStyle();
//    imageButtonStyle4.up = (Drawable)new TextureRegionDrawable(new TextureRegion((Texture)manager.get("skip_button.png", Texture.class)));
//    imageButtonStyle4.down = (Drawable)new TextureRegionDrawable(new TextureRegion((Texture)manager.get("skip_button.png", Texture.class)));
//    ImageButton imageButton5 = new ImageButton(imageButtonStyle4);
//    imageButton5.setPosition(worldXToScreenX(200.0F), worldYToScreenY(20.0F));
//    imageButton5.setSize(worldXToScreenX(100.0F), worldYToScreenY(100.0F));
//    imageButton5.addListener((EventListener)new ClickListener() {
//          public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
//            super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
//            MainGame.this.musicSoundsObject.playButtonClick();
//            if (MainGame.this.showEndStory) {
//              ((Actor)MainGame.this.stage.getActors().get(14)).setVisible(false);
//              ((Actor)MainGame.this.stage.getActors().get(15)).setVisible(false);
//              ((Actor)MainGame.this.stage.getActors().get(16)).setVisible(false);
//              ((Actor)MainGame.this.stage.getActors().get(17)).setVisible(false);
//              ((Actor)MainGame.this.stage.getActors().get(18)).setVisible(true);
//              ((Actor)MainGame.this.stage.getActors().get(19)).setVisible(true);
//              ((Actor)MainGame.this.stage.getActors().get(20)).setVisible(true);
//              ((Actor)MainGame.this.stage.getActors().get(27)).setVisible(false);
//              ((Actor)MainGame.this.stage.getActors().get(0)).setVisible(true);
//              ((Actor)MainGame.this.stage.getActors().get(1)).setVisible(false);
//              MainGame.access$202(MainGame.this, 4);
//            } else {
//              ((Actor)MainGame.this.stage.getActors().get(12)).setVisible(false);
//              ((Actor)MainGame.this.stage.getActors().get(13)).setVisible(false);
//              ((Actor)MainGame.this.stage.getActors().get(16)).setVisible(false);
//              ((Actor)MainGame.this.stage.getActors().get(17)).setVisible(false);
//              ((Actor)MainGame.this.stage.getActors().get(27)).setVisible(false);
//              MainGame.access$402(MainGame.this, 4);
//              param1Int1 = stageNumber;
//              if (param1Int1 == 1) {
//                if (MainGame.this.prefs.getBoolean("firstTimeTutorial", true)) {
//                  ((Actor)MainGame.this.stage.getActors().get(10)).setVisible(true);
//                  ((Actor)MainGame.this.stage.getActors().get(11)).setVisible(true);
//                } else {
//                  MainGame.access$602(MainGame.this, 0);
//                }
//              } else if (param1Int1 == 2) {
//                MainGame.access$602(MainGame.this, 0);
//              }
//            }
//          }
//        });
//    Image image5 = new Image((Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)textureAtlas3.findRegion("main_game_tutorial"))));
//    image5.setSize(worldXToScreenX(450.0F), worldYToScreenY(625.0F));
//    image5.setPosition(worldXToScreenX(25.0F), worldYToScreenY(187.5F));
//    ImageButton.ImageButtonStyle imageButtonStyle5 = new ImageButton.ImageButtonStyle();
//    imageButtonStyle5.up = (Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)textureAtlas3.findRegion("story_continue_button_unpressed")));
//    imageButtonStyle5.down = (Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)textureAtlas3.findRegion("story_continue_button_pressed")));
//    ImageButton imageButton6 = new ImageButton(imageButtonStyle5);
//    imageButton6.setPosition(worldXToScreenX(380.0F), worldYToScreenY(200.0F));
//    imageButton6.setSize(worldXToScreenX(75.0F), worldYToScreenY(75.0F));
//    imageButton6.addListener((EventListener)new ClickListener() {
//          public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
//            super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
//            MainGame.this.musicSoundsObject.playButtonClick();
//            ((Actor)MainGame.this.stage.getActors().get(10)).setVisible(false);
//            ((Actor)MainGame.this.stage.getActors().get(11)).setVisible(false);
//            MainGame.access$602(MainGame.this, 0);
//            MainGame.this.prefs.putBoolean("firstTimeTutorial", false);
//            MainGame.this.prefs.flush();
//          }
//        });
//    Image image6 = new Image((Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)textureAtlas3.findRegion("main_game_pause_dark_background"))));
//    image6.setColor(255.0F, 255.0F, 255.0F, 0.5F);
//    image6.setSize(worldXToScreenX(500.0F), worldYToScreenY(1000.0F));
//    image6.setPosition(0.0F, 0.0F);
//    ImageButton.ImageButtonStyle imageButtonStyle6 = new ImageButton.ImageButtonStyle();
//    imageButtonStyle6.up = (Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)textureAtlas1.findRegion("play_button_unpressed")));
//    imageButtonStyle6.down = (Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)textureAtlas1.findRegion("play_button_pressed")));
//    ImageButton imageButton2 = new ImageButton(imageButtonStyle6);
//    imageButton2.setPosition(worldXToScreenX(50.0F), worldYToScreenY(525.0F));
//    imageButton2.setSize(worldXToScreenX(100.0F), worldYToScreenY(100.0F));
//    imageButton2.addListener((EventListener)new ClickListener() {
//          public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
//            super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
//            MainGame.this.musicSoundsObject.playButtonClick();
//            MainGame.access$702(MainGame.this, false);
//            ((Actor)MainGame.this.stage.getActors().get(0)).setVisible(false);
//            ((Actor)MainGame.this.stage.getActors().get(1)).setVisible(true);
//            ((Actor)MainGame.this.stage.getActors().get(2)).setVisible(false);
//            ((Actor)MainGame.this.stage.getActors().get(3)).setVisible(false);
//            ((Actor)MainGame.this.stage.getActors().get(4)).setVisible(false);
//            ((Actor)MainGame.this.stage.getActors().get(5)).setVisible(false);
//            ((Actor)MainGame.this.stage.getActors().get(21)).setVisible(false);
//          }
//        });
//    imageButtonStyle6 = new ImageButton.ImageButtonStyle();
//    imageButtonStyle6.up = (Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)textureAtlas3.findRegion("main_game_restart_button_unpressed")));
//    imageButtonStyle6.down = (Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)textureAtlas3.findRegion("main_game_restart_button_pressed")));
//    ImageButton imageButton7 = new ImageButton(imageButtonStyle6);
//    imageButton7.setPosition(worldXToScreenX(200.0F), worldYToScreenY(525.0F));
//    imageButton7.setSize(worldXToScreenX(100.0F), worldYToScreenY(100.0F));
//    imageButton7.addListener((EventListener)new ClickListener() {
//          public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
//            super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
//            MainGame.this.musicSoundsObject.playButtonClick();
//            MainGame.this.resetStatsAndRestart();
//            MainGame.this.coinObject.setCoinCount();
//            MainGame.access$702(MainGame.this, false);
//            ((Actor)MainGame.this.stage.getActors().get(0)).setVisible(false);
//            ((Actor)MainGame.this.stage.getActors().get(1)).setVisible(true);
//            ((Actor)MainGame.this.stage.getActors().get(2)).setVisible(false);
//            ((Actor)MainGame.this.stage.getActors().get(3)).setVisible(false);
//            ((Actor)MainGame.this.stage.getActors().get(4)).setVisible(false);
//            ((Actor)MainGame.this.stage.getActors().get(5)).setVisible(false);
//            ((Actor)MainGame.this.stage.getActors().get(21)).setVisible(false);
//          }
//        });
//    ImageButton.ImageButtonStyle imageButtonStyle7 = new ImageButton.ImageButtonStyle();
//    imageButtonStyle7.up = (Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)textureAtlas3.findRegion("main_game_menu_button_unpressed")));
//    imageButtonStyle7.down = (Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)textureAtlas3.findRegion("main_game_menu_button_pressed")));
//    ImageButton imageButton8 = new ImageButton(imageButtonStyle7);
//    imageButton8.setPosition(worldXToScreenX(350.0F), worldYToScreenY(525.0F));
//    imageButton8.setSize(worldXToScreenX(100.0F), worldYToScreenY(100.0F));
//    imageButton8.addListener((EventListener)new ClickListener() {
//          public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
//            super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
//            GameStateManager gameStateManager = gsm;
//            gameStateManager.set((State)new ChooseStageMenu(gameStateManager, adsController, manager));
//            MainGame.this.musicSoundsObject.playButtonClick();
//            MainGame.this.dispose();
//          }
//        });
//    ImageButton.ImageButtonStyle imageButtonStyle8 = new ImageButton.ImageButtonStyle();
//    imageButtonStyle8.up = (Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)textureAtlas3.findRegion("main_game_pause_button_unpressed")));
//    imageButtonStyle8.down = (Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)textureAtlas3.findRegion("main_game_pause_button_pressed")));
//    ImageButton imageButton9 = new ImageButton(imageButtonStyle8);
//    imageButton9.setPosition(0.0F, 0.0F);
//    imageButton9.setSize(worldXToScreenX(80.0F), worldYToScreenY(80.0F));
//    imageButton9.addListener((EventListener)new ClickListener() {
//          public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
//            super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
//            MainGame.this.musicSoundsObject.playButtonClick();
//            MainGame.access$702(MainGame.this, true);
//            ((Actor)MainGame.this.stage.getActors().get(0)).setVisible(true);
//            ((Actor)MainGame.this.stage.getActors().get(1)).setVisible(false);
//            ((Actor)MainGame.this.stage.getActors().get(2)).setVisible(true);
//            ((Actor)MainGame.this.stage.getActors().get(3)).setVisible(true);
//            ((Actor)MainGame.this.stage.getActors().get(4)).setVisible(true);
//            ((Actor)MainGame.this.stage.getActors().get(5)).setVisible(true);
//            ((Actor)MainGame.this.stage.getActors().get(21)).setVisible(true);
//          }
//        });
//    ImageButton.ImageButtonStyle imageButtonStyle11 = new ImageButton.ImageButtonStyle();
//    this.soundButtonStyle = imageButtonStyle11;
//    imageButtonStyle11.up = (Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)textureAtlas3.findRegion("main_game_sound_button_unpressed")));
//    this.soundButtonStyle.down = (Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)textureAtlas3.findRegion("main_game_sound_button_pressed")));
//    imageButtonStyle11 = new ImageButton.ImageButtonStyle();
//    this.noSoundButtonStyle = imageButtonStyle11;
//    imageButtonStyle11.up = (Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)textureAtlas3.findRegion("main_game_no_sound_button_unpressed")));
//    this.noSoundButtonStyle.down = (Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)textureAtlas3.findRegion("main_game_no_sound_button_pressed")));
//    ImageButton imageButton12 = new ImageButton(this.soundButtonStyle);
//    this.soundButton = imageButton12;
//    imageButton12.setPosition(worldXToScreenX(125.0F), worldYToScreenY(375.0F));
//    this.soundButton.setSize(worldXToScreenX(100.0F), worldYToScreenY(100.0F));
//    this.soundButton.addListener((EventListener)new ClickListener() {
//          public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
//            super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
//            if (MainGame.this.prefs.getBoolean("sound", true)) {
//              MainGame.this.prefs.putBoolean("sound", false);
//              MainGame.this.prefs.flush();
//            } else {
//              MainGame.this.prefs.putBoolean("sound", true);
//              MainGame.this.prefs.flush();
//              MainGame.this.musicSoundsObject.playButtonClick();
//            }
//          }
//        });
//    ImageButton.ImageButtonStyle imageButtonStyle10 = new ImageButton.ImageButtonStyle();
//    this.musicButtonStyle = imageButtonStyle10;
//    imageButtonStyle10.up = (Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)textureAtlas3.findRegion("main_game_music_button_unpressed")));
//    this.musicButtonStyle.down = (Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)textureAtlas3.findRegion("main_game_music_button_pressed")));
//    imageButtonStyle10 = new ImageButton.ImageButtonStyle();
//    this.noMusicButtonStyle = imageButtonStyle10;
//    imageButtonStyle10.up = (Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)textureAtlas3.findRegion("main_game_no_music_button_unpressed")));
//    this.noMusicButtonStyle.down = (Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)textureAtlas3.findRegion("main_game_no_music_button_pressed")));
//    ImageButton imageButton11 = new ImageButton(this.musicButtonStyle);
//    this.musicButton = imageButton11;
//    imageButton11.setPosition(worldXToScreenX(275.0F), worldYToScreenY(375.0F));
//    this.musicButton.setSize(worldXToScreenX(100.0F), worldYToScreenY(100.0F));
//    this.musicButton.addListener((EventListener)new ClickListener() {
//          public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
//            super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
//            if (MainGame.this.prefs.getBoolean("music", true)) {
//              MainGame.this.prefs.putBoolean("music", false);
//              MainGame.this.prefs.flush();
//              MainGame.this.musicSoundsObject.playButtonClick();
//            } else {
//              MainGame.this.prefs.putBoolean("music", true);
//              MainGame.this.prefs.flush();
//              MainGame.this.musicSoundsObject.playButtonClick();
//            }
//          }
//        });
//    ImageButton.ImageButtonStyle imageButtonStyle9 = new ImageButton.ImageButtonStyle();
//    this.continueWithAdsButtonStyle = imageButtonStyle9;
//    imageButtonStyle9.up = (Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)textureAtlas3.findRegion("continue_ad_button_unpressed")));
//    this.continueWithAdsButtonStyle.down = (Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)textureAtlas3.findRegion("continue_ad_button_pressed")));
//    imageButtonStyle9 = new ImageButton.ImageButtonStyle();
//    this.continueWithAdsWaitButtonStyle = imageButtonStyle9;
//    imageButtonStyle9.up = (Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)textureAtlas3.findRegion("ad_wait_button")));
//    this.continueWithAdsWaitButtonStyle.down = (Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)textureAtlas3.findRegion("ad_wait_button")));
//    ImageButton imageButton10 = new ImageButton(this.continueWithAdsButtonStyle);
//    this.continueWithAdsButton = imageButton10;
//    imageButton10.setPosition(worldXToScreenX(25.0F), worldYToScreenY(410.0F));
//    this.continueWithAdsButton.setSize(worldXToScreenX(200.0F), worldYToScreenY(80.0F));
//    this.continueWithAdsButton.addListener((EventListener)new ClickListener() {
//          public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
//            super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
//            adsController.showRewardedVideo();
//            MainGame.this.musicSoundsObject.playButtonClick();
//          }
//        });
//    Label.LabelStyle labelStyle = new Label.LabelStyle();
//    labelStyle.font = (BitmapFont)manager.get("font/font_scale_1.fnt", BitmapFont.class);
//    labelStyle.fontColor = Color.BLACK;
//    final Label label1 = new Label(String.valueOf(this.continueWithDiamonds), labelStyle);
//    label.setSize(worldXToScreenX(40.0F), worldYToScreenY(40.0F));
//    label.setPosition(worldXToScreenX(370.0F), worldYToScreenY(420.0F));
//    label.setAlignment(1);
//    ImageButton.ImageButtonStyle imageButtonStyle12 = new ImageButton.ImageButtonStyle();
//    imageButtonStyle12.up = (Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)textureAtlas3.findRegion("continue_diamond_button_unpressed")));
//    imageButtonStyle12.down = (Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)textureAtlas3.findRegion("continue_diamond_button_pressed")));
//    ImageButton imageButton14 = new ImageButton(imageButtonStyle12);
//    imageButton14.setPosition(worldXToScreenX(275.0F), worldYToScreenY(410.0F));
//    imageButton14.setSize(worldXToScreenX(200.0F), worldYToScreenY(80.0F));
//    imageButton14.addListener((EventListener)new ClickListener() {
//          public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
//            super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
//            MainGame.this.musicSoundsObject.playButtonClick();
//            if (MainGame.this.prefs.getInteger("ruby") >= MainGame.this.continueWithDiamonds) {
//              MainGame.this.prefs.putInteger("ruby", MainGame.this.prefs.getInteger("ruby") - MainGame.this.continueWithDiamonds);
//              MainGame.this.prefs.putInteger("revivedAfterDeath", MainGame.this.prefs.getInteger("revivedAfterDeath", 0) + 1);
//              MainGame.this.prefs.flush();
//              MainGame.access$602(MainGame.this, 0);
//              MainGame.this.resetAfterRevive();
//              ((Actor)MainGame.this.stage.getActors().get(0)).setVisible(false);
//              ((Actor)MainGame.this.stage.getActors().get(1)).setVisible(true);
//              ((Actor)MainGame.this.stage.getActors().get(6)).setVisible(false);
//              ((Actor)MainGame.this.stage.getActors().get(7)).setVisible(false);
//              ((Actor)MainGame.this.stage.getActors().get(8)).setVisible(false);
//              MainGame.access$1008(MainGame.this);
//              label1.setText(String.valueOf(MainGame.this.continueWithDiamonds));
//              ((Actor)MainGame.this.stage.getActors().get(23)).setVisible(false);
//            } else {
//              MainGame.access$1202(MainGame.this, false);
//              ((Actor)MainGame.this.stage.getActors().get(9)).setVisible(true);
//              ((Actor)MainGame.this.stage.getActors().get(22)).setVisible(true);
//              ((Actor)MainGame.this.stage.getActors().get(23)).setVisible(false);
//              ((Actor)MainGame.this.stage.getActors().get(6)).setTouchable(Touchable.disabled);
//              ((Actor)MainGame.this.stage.getActors().get(7)).setTouchable(Touchable.disabled);
//              ((Actor)MainGame.this.stage.getActors().get(8)).setTouchable(Touchable.disabled);
//            }
//          }
//        });
//    Image image7 = new Image((Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)textureAtlas3.findRegion("not_enough_diamonds_window"))));
//    image7.setSize(worldXToScreenX(480.0F), worldYToScreenY(200.0F));
//    image7.setPosition(worldXToScreenX(10.0F), worldYToScreenY(400.0F));
//    ImageButton.ImageButtonStyle imageButtonStyle13 = new ImageButton.ImageButtonStyle();
//    imageButtonStyle13.up = (Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)textureAtlas2.findRegion("congratulations_window_x_button_unpressed")));
//    imageButtonStyle13.down = (Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)textureAtlas2.findRegion("congratulations_window_x_button_pressed")));
//    ImageButton imageButton13 = new ImageButton(imageButtonStyle13);
//    imageButton13.setPosition(worldXToScreenX(420.0F), worldYToScreenY(535.0F));
//    imageButton13.setSize(worldXToScreenX(50.0F), worldYToScreenY(50.0F));
//    imageButton13.addListener((EventListener)new ClickListener() {
//          public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
//            super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
//            MainGame.this.musicSoundsObject.playButtonClick();
//            MainGame.access$1202(MainGame.this, true);
//            ((Actor)MainGame.this.stage.getActors().get(9)).setVisible(false);
//            ((Actor)MainGame.this.stage.getActors().get(22)).setVisible(false);
//            ((Actor)MainGame.this.stage.getActors().get(23)).setVisible(true);
//            ((Actor)MainGame.this.stage.getActors().get(6)).setTouchable(Touchable.enabled);
//            ((Actor)MainGame.this.stage.getActors().get(7)).setTouchable(Touchable.enabled);
//            ((Actor)MainGame.this.stage.getActors().get(8)).setTouchable(Touchable.enabled);
//          }
//        });
//    ImageButton.ImageButtonStyle imageButtonStyle14 = new ImageButton.ImageButtonStyle();
//    imageButtonStyle14.up = (Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)textureAtlas3.findRegion("end_game_button_unpressed")));
//    imageButtonStyle14.down = (Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)textureAtlas3.findRegion("end_game_button_pressed")));
//    ImageButton imageButton16 = new ImageButton(imageButtonStyle14);
//    imageButton16.setPosition(worldXToScreenX(150.0F), worldYToScreenY(0.0F));
//    imageButton16.setSize(worldXToScreenX(200.0F), worldYToScreenY(80.0F));
//    imageButton16.addListener((EventListener)new ClickListener() {
//          public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
//            super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
//            MainGame.this.musicSoundsObject.playButtonClick();
//            MainGame.this.prefs.putInteger("coins", MainGame.this.coinObject.getCoinCount() + MainGame.this.prefs.getInteger("coins"));
//            MainGame.this.prefs.flush();
//            GameStateManager gameStateManager = gsm;
//            gameStateManager.set((State)new EndGame(gameStateManager, adsController, manager, MainGame.this.coinObject.getCoinCount(), MainGame.this.score, MainGame.this.timerGame, stageNumber));
//            MainGame.this.dispose();
//          }
//        });
//    Image image8 = new Image((Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)textureAtlas3.findRegion("congratulations_story_window"))));
//    image8.setSize(worldXToScreenX(480.0F), worldYToScreenY(250.0F));
//    image8.setPosition(worldXToScreenX(10.0F), worldYToScreenY(375.0F));
//    ImageButton imageButton15 = new ImageButton(imageButtonStyle13);
//    imageButton15.setPosition(worldXToScreenX(120.0F), worldYToScreenY(385.0F));
//    imageButton15.setSize(worldXToScreenX(75.0F), worldYToScreenY(75.0F));
//    imageButton15.addListener((EventListener)new ClickListener() {
//          public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
//            super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
//            MainGame.this.musicSoundsObject.playButtonClick();
//            MainGame.this.prefs.putInteger("coins", MainGame.this.coinObject.getCoinCount() + MainGame.this.prefs.getInteger("coins"));
//            MainGame.this.prefs.flush();
//            GameStateManager gameStateManager = gsm;
//            gameStateManager.set((State)new EndGame(gameStateManager, adsController, manager, MainGame.this.coinObject.getCoinCount(), MainGame.this.score, MainGame.this.timerGame, stageNumber));
//            MainGame.this.dispose();
//          }
//        });
//    ImageButton.ImageButtonStyle imageButtonStyle1 = new ImageButton.ImageButtonStyle();
//    imageButtonStyle1.up = (Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)textureAtlas3.findRegion("story_continue_button_unpressed")));
//    imageButtonStyle1.down = (Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)textureAtlas3.findRegion("story_continue_button_pressed")));
//    ImageButton imageButton1 = new ImageButton(imageButtonStyle1);
//    imageButton1.setPosition(worldXToScreenX(305.0F), worldYToScreenY(385.0F));
//    imageButton1.setSize(worldXToScreenX(75.0F), worldYToScreenY(75.0F));
//    imageButton1.addListener((EventListener)new ClickListener() {
//          public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
//            super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
//            MainGame.this.musicSoundsObject.playButtonClick();
//            ((Actor)MainGame.this.stage.getActors().get(18)).setVisible(false);
//            ((Actor)MainGame.this.stage.getActors().get(19)).setVisible(false);
//            ((Actor)MainGame.this.stage.getActors().get(20)).setVisible(false);
//            ((Actor)MainGame.this.stage.getActors().get(0)).setVisible(false);
//            MainGame.this.resetAfterRevive();
//            MainGame.access$602(MainGame.this, 0);
//            param1Int1 = stageNumber;
//            if (param1Int1 == 1) {
//              MainGame.this.prefs.putFloat("storyModeCity", MainGame.this.timerGame);
//            } else if (param1Int1 == 2) {
//              MainGame.this.prefs.putFloat("storyModeDesert", MainGame.this.timerGame);
//            }
//            MainGame.this.prefs.flush();
//          }
//        });
//    Image image1 = new Image((TextureRegion)textureAtlas2.findRegion("sure_quit_window"));
//    image1.setSize(worldXToScreenX(480.0F), worldYToScreenY(210.0F));
//    image1.setPosition(worldXToScreenX(10.0F), worldYToScreenY(395.0F));
//    ImageButton.ImageButtonStyle imageButtonStyle15 = new ImageButton.ImageButtonStyle();
//    imageButtonStyle15.up = (Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)textureAtlas2.findRegion("congratulations_window_x_button_unpressed")));
//    imageButtonStyle15.down = (Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)textureAtlas2.findRegion("congratulations_window_x_button_pressed")));
//    ImageButton imageButton17 = new ImageButton(imageButtonStyle15);
//    imageButton17.setPosition(worldXToScreenX(116.0F), worldYToScreenY(405.0F));
//    imageButton17.setSize(worldXToScreenX(75.0F), worldYToScreenY(75.0F));
//    imageButton17.addListener((EventListener)new ClickListener() {
//          public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
//            super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
//            MainGame.this.musicSoundsObject.playButtonClick();
//            MainGame.access$702(MainGame.this, false);
//            ((Actor)MainGame.this.stage.getActors().get(0)).setVisible(false);
//            ((Actor)MainGame.this.stage.getActors().get(1)).setVisible(true);
//            ((Actor)MainGame.this.stage.getActors().get(24)).setVisible(false);
//            ((Actor)MainGame.this.stage.getActors().get(25)).setVisible(false);
//            ((Actor)MainGame.this.stage.getActors().get(26)).setVisible(false);
//          }
//        });
//    ImageButton.ImageButtonStyle imageButtonStyle16 = new ImageButton.ImageButtonStyle();
//    imageButtonStyle16.up = (Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)textureAtlas3.findRegion("story_continue_button_unpressed")));
//    imageButtonStyle16.down = (Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)textureAtlas3.findRegion("story_continue_button_pressed")));
//    ImageButton imageButton18 = new ImageButton(imageButtonStyle16);
//    imageButton18.setPosition(worldXToScreenX(307.0F), worldYToScreenY(405.0F));
//    imageButton18.setSize(worldXToScreenX(75.0F), worldYToScreenY(75.0F));
//    imageButton18.addListener((EventListener)new ClickListener() {
//          public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
//            super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
//            MainGame.this.musicSoundsObject.playButtonClick();
//            Gdx.app.exit();
//          }
//        });
//    this.stage.addActor((Actor)image6);
//    this.stage.addActor((Actor)imageButton9);
//    this.stage.addActor((Actor)this.soundButton);
//    this.stage.addActor((Actor)imageButton2);
//    this.stage.addActor((Actor)imageButton7);
//    this.stage.addActor((Actor)imageButton8);
//    this.stage.addActor((Actor)this.continueWithAdsButton);
//    this.stage.addActor((Actor)imageButton14);
//    this.stage.addActor((Actor)imageButton16);
//    this.stage.addActor((Actor)image7);
//    this.stage.addActor((Actor)image5);
//    this.stage.addActor((Actor)imageButton6);
//    this.stage.addActor((Actor)image2);
//    this.stage.addActor((Actor)this.storyStartSecond);
//    this.stage.addActor((Actor)image3);
//    this.stage.addActor((Actor)this.storyEndSecond);
//    this.stage.addActor((Actor)imageButton3);
//    this.stage.addActor((Actor)imageButton4);
//    this.stage.addActor((Actor)image8);
//    this.stage.addActor((Actor)imageButton15);
//    this.stage.addActor((Actor)imageButton1);
//    this.stage.addActor((Actor)this.musicButton);
//    this.stage.addActor((Actor)imageButton13);
//    this.stage.addActor((Actor)label);
//    this.stage.addActor((Actor)image1);
//    this.stage.addActor((Actor)imageButton17);
//    this.stage.addActor((Actor)imageButton18);
//    this.stage.addActor((Actor)imageButton5);
//    ((Actor)this.stage.getActors().get(0)).setVisible(false);
//    ((Actor)this.stage.getActors().get(2)).setVisible(false);
//    ((Actor)this.stage.getActors().get(3)).setVisible(false);
//    ((Actor)this.stage.getActors().get(4)).setVisible(false);
//    ((Actor)this.stage.getActors().get(5)).setVisible(false);
//    ((Actor)this.stage.getActors().get(6)).setVisible(false);
//    ((Actor)this.stage.getActors().get(7)).setVisible(false);
//    ((Actor)this.stage.getActors().get(8)).setVisible(false);
//    ((Actor)this.stage.getActors().get(9)).setVisible(false);
//    ((Actor)this.stage.getActors().get(10)).setVisible(false);
//    ((Actor)this.stage.getActors().get(11)).setVisible(false);
//    ((Actor)this.stage.getActors().get(12)).setVisible(false);
//    ((Actor)this.stage.getActors().get(13)).setVisible(false);
//    ((Actor)this.stage.getActors().get(14)).setVisible(false);
//    ((Actor)this.stage.getActors().get(15)).setVisible(false);
//    ((Actor)this.stage.getActors().get(16)).setVisible(false);
//    ((Actor)this.stage.getActors().get(17)).setVisible(false);
//    ((Actor)this.stage.getActors().get(18)).setVisible(false);
//    ((Actor)this.stage.getActors().get(19)).setVisible(false);
//    ((Actor)this.stage.getActors().get(20)).setVisible(false);
//    ((Actor)this.stage.getActors().get(21)).setVisible(false);
//    ((Actor)this.stage.getActors().get(22)).setVisible(false);
//    ((Actor)this.stage.getActors().get(23)).setVisible(false);
//    ((Actor)this.stage.getActors().get(24)).setVisible(false);
//    ((Actor)this.stage.getActors().get(25)).setVisible(false);
//    ((Actor)this.stage.getActors().get(26)).setVisible(false);
//    ((Actor)this.stage.getActors().get(27)).setVisible(false);
//    if (this.prefs.getBoolean("firstTimeTutorial", true)) {
//      ((Actor)this.stage.getActors().get(1)).setVisible(false);
//      this.showScoreAndCoinLabel = false;
//    }
//    this.pauseGame = false;
//    this.random = new Random(System.currentTimeMillis());
//    this.coinObject = new Coin();
//    this.rockObject = new Rock();
//    this.bombObject = new Bomb();
//    this.spikesObject = new Spikes();
//    this.rocketObject = new Rocket();
//    this.shieldObject = new Shield();
//    this.coinRushObject = new CoinRush();
//    this.coinMagnetObject = new CoinMagnet();
//    this.playerObject = new Player();
//    this.villainObject = new Villain();
//    this.achievementsObject = new Achievements();
//    this.coinObject.initializeValues(textureAtlas2, manager);
//    this.rockObject.initializeValues(textureAtlas3, stageNumber, manager);
//    this.bombObject.initializeValues(textureAtlas3, manager);
//    this.spikesObject.initializeValues(textureAtlas3);
//    this.rocketObject.initializeValues(textureAtlas3);
//    this.shieldObject.initializeValues(textureAtlas3);
//    this.coinRushObject.initializeValues(textureAtlas3);
//    this.coinMagnetObject.initializeValues(textureAtlas3);
//    this.playerObject.initializeValues(manager, this.prefs);
//    this.villainObject.initializeValues(textureAtlas3);
//    BitmapFont bitmapFont = (BitmapFont)manager.get("font/font_scale_1.fnt", BitmapFont.class);
//    this.scoreAndCoinFont = bitmapFont;
//    bitmapFont.setColor(Color.BLACK);
//    this.scoreAndCoinFont.getData().setScale(worldXToScreenX(1.0F), worldYToScreenY(1.0F));
//    this.scoreAndCoinBackground = textureAtlas3.findRegion("score_coin_background");
//    this.coin = textureAtlas2.findRegion("coin");
//    this.collectiblesChoice = this.random.nextInt(3) + 1;
//    this.collectiblesChance = 100.0F;
//    this.normalChance = 10.0F;
//    if (this.prefs.getInteger("adsWatchedAtmAchievement", 0) < 5)
//      this.achievementsObject.checkAdsWatched(this.prefs.getInteger("adsWatched", 0));
//    this.shapeRenderer = new ShapeRenderer();
//  }
//
//  private int getActiveBackgroundIdx(float paramFloat) {
//    return (int)Math.floor(Math.abs(paramFloat / this.screenWidth));
//  }
//
//  private void resetAfterRevive() {
//    this.playerObject.resetPlayerStats(this.prefs);
//    this.coinObject.resetCoinStats();
//    this.rockObject.resetRockStats();
//    this.bombObject.resetBombStats();
//    this.spikesObject.resetSpikeDownStats();
//    this.rocketObject.resetRocketStats();
//    this.shieldObject.resetShieldStats();
//    this.coinRushObject.resetCoinRushStats();
//    this.coinMagnetObject.resetCoinMagnetState();
//    this.timerObjects = 0.0F;
//    this.normalChance = 10.0F;
//    this.firstArrowsAppearanceRed = false;
//    this.firstArrowsAppearanceYellow = false;
//    this.firstArrowsAppearanceGreen = false;
//    this.firstTimeArrowsRed = true;
//    this.firstTimeArrowsYellow = true;
//    this.firstTimeArrowsGreen = true;
//    this.yellowGo = false;
//    this.greenGo = false;
//  }
//
//  private void resetStatsAndRestart() {
//    this.gameState = 0;
//    this.score = 0;
//    for (byte b = 0; b < 5; b++)
//      this.bgCords[b] = (int)worldXToScreenX((b * 500));
//    this.playerObject.resetPlayerStats(this.prefs);
//    this.coinObject.resetCoinStats();
//    this.rockObject.resetRockStats();
//    this.bombObject.resetBombStats();
//    this.spikesObject.resetSpikeDownStats();
//    this.rocketObject.resetRocketStats();
//    this.shieldObject.resetShieldStats();
//    this.coinRushObject.resetCoinRushStats();
//    this.coinMagnetObject.resetCoinMagnetState();
//    this.timerObjects = 0.0F;
//    this.timerGame = 0.0F;
//    this.collectiblesTimer = 0.0F;
//    this.normalChance = 10.0F;
//    this.rocksGo = true;
//    this.bombsGo = false;
//    this.spikesGo = false;
//    this.rocketsGo = false;
//    this.firstArrowsAppearanceRed = false;
//    this.firstArrowsAppearanceYellow = false;
//    this.firstArrowsAppearanceGreen = false;
//    this.firstTimeArrowsRed = true;
//    this.firstTimeArrowsYellow = true;
//    this.firstTimeArrowsGreen = true;
//    this.yellowGo = false;
//    this.greenGo = false;
//    this.scoreTimer = 0.0F;
//    this.twoTimesAd = 0;
//  }
//
//  private void rocketFirstTime() {
//    if (this.firstTimeArrowsRed) {
//      this.firstArrowsAppearanceRed = true;
//      this.rocketObject.setLastArrowRedTimer(this.timerObjects);
//      this.firstTimeArrowsRed = false;
//    }
//    if (this.firstTimeArrowsYellow && this.timerObjects - this.rocketObject.getLastArrowRedTimer() >= 2.0F) {
//      this.firstArrowsAppearanceYellow = true;
//      this.rocketObject.setLastArrowYellowTimer(this.timerObjects);
//      this.firstTimeArrowsYellow = false;
//      this.yellowGo = true;
//    }
//    if (this.firstTimeArrowsGreen && this.timerObjects - this.rocketObject.getLastArrowRedTimer() >= 1.0F) {
//      this.firstArrowsAppearanceGreen = true;
//      this.rocketObject.setLastArrowGreenTimer(this.timerObjects);
//      this.firstTimeArrowsGreen = false;
//      this.greenGo = true;
//    }
//  }
//
//  private void showEndStory(int paramInt) {
//    int i = this.storyEndNumber;
//    if (i == 0) {
//      ((Actor)this.stage.getActors().get(14)).setVisible(true);
//      ((Actor)this.stage.getActors().get(15)).setVisible(false);
//      ((Actor)this.stage.getActors().get(16)).setVisible(true);
//      ((Actor)this.stage.getActors().get(17)).setVisible(false);
//      ((Actor)this.stage.getActors().get(27)).setVisible(true);
//    } else if (i == 1) {
//      ((Actor)this.stage.getActors().get(14)).setVisible(false);
//      ((Actor)this.stage.getActors().get(15)).setVisible(true);
//      ((Actor)this.stage.getActors().get(17)).setVisible(true);
//      if (paramInt == 1) {
//        this.storyEndSecond.setDrawable((Drawable)new TextureRegionDrawable(new TextureRegion((Texture)this.manager.get("story_city_end_second.png", Texture.class))));
//      } else if (paramInt == 2) {
//        this.storyEndSecond.setDrawable((Drawable)new TextureRegionDrawable(new TextureRegion((Texture)this.manager.get("story_city_end_second.png", Texture.class))));
//      }
//    } else if (i == 2) {
//      if (paramInt == 1) {
//        this.storyEndSecond.setDrawable((Drawable)new TextureRegionDrawable(new TextureRegion((Texture)this.manager.get("story_city_end_third.png", Texture.class))));
//      } else if (paramInt == 2) {
//        this.storyEndSecond.setDrawable((Drawable)new TextureRegionDrawable(new TextureRegion((Texture)this.manager.get("story_city_end_third.png", Texture.class))));
//      }
//    } else if (i == 3) {
//      if (paramInt == 1) {
//        this.storyEndSecond.setDrawable((Drawable)new TextureRegionDrawable(new TextureRegion((Texture)this.manager.get("story_city_end_fourth.png", Texture.class))));
//      } else if (paramInt == 2) {
//        this.storyEndSecond.setDrawable((Drawable)new TextureRegionDrawable(new TextureRegion((Texture)this.manager.get("story_city_end_fourth.png", Texture.class))));
//      }
//    }
//  }
//
//  private void showStartStory(int paramInt) {
//    int i = this.storyStartNumber;
//    if (i == 0) {
//      ((Actor)this.stage.getActors().get(12)).setVisible(true);
//      ((Actor)this.stage.getActors().get(13)).setVisible(false);
//      ((Actor)this.stage.getActors().get(16)).setVisible(true);
//      ((Actor)this.stage.getActors().get(17)).setVisible(false);
//      ((Actor)this.stage.getActors().get(27)).setVisible(true);
//    } else if (i == 1) {
//      ((Actor)this.stage.getActors().get(12)).setVisible(false);
//      ((Actor)this.stage.getActors().get(13)).setVisible(true);
//      ((Actor)this.stage.getActors().get(17)).setVisible(true);
//      if (paramInt == 1) {
//        this.storyStartSecond.setDrawable((Drawable)new TextureRegionDrawable(new TextureRegion((Texture)this.manager.get("story_city_start_second.png", Texture.class))));
//      } else if (paramInt == 2) {
//        this.storyStartSecond.setDrawable((Drawable)new TextureRegionDrawable(new TextureRegion((Texture)this.manager.get("story_city_start_second.png", Texture.class))));
//      }
//    } else if (i == 2) {
//      if (paramInt == 1) {
//        this.storyStartSecond.setDrawable((Drawable)new TextureRegionDrawable(new TextureRegion((Texture)this.manager.get("story_city_start_third.png", Texture.class))));
//      } else if (paramInt == 2) {
//        this.storyStartSecond.setDrawable((Drawable)new TextureRegionDrawable(new TextureRegion((Texture)this.manager.get("story_city_start_third.png", Texture.class))));
//      }
//    } else if (i == 3) {
//      if (paramInt == 1) {
//        this.storyStartSecond.setDrawable((Drawable)new TextureRegionDrawable(new TextureRegion((Texture)this.manager.get("story_city_start_fourth.png", Texture.class))));
//      } else if (paramInt == 2) {
//        this.storyStartSecond.setDrawable((Drawable)new TextureRegionDrawable(new TextureRegion((Texture)this.manager.get("story_city_start_fourth.png", Texture.class))));
//      }
//    }
//  }
//
//  private void timingStory(String paramString) {
//    if (this.prefs.getFloat(paramString) >= 100.0F) {
//      float f = this.timerGame;
//      if (f >= 100.0F && f < 160.0F) {
//        this.rocketsGo = false;
//        this.rocksGo = true;
//        this.bombsGo = true;
//      }
//      f = this.timerGame;
//      if (f >= 160.0F && f < 240.0F) {
//        this.rocksGo = false;
//        this.rockObject.setFirstRockHitFalse();
//        this.rocketsGo = true;
//        rocketFirstTime();
//      }
//      if (this.timerGame >= 240.0F)
//        this.rocksGo = true;
//    } else if (this.timerGame > 100.0F) {
//      this.gameState = 3;
//    }
//  }
//
//  private float worldXToScreenX(float paramFloat) {
//    return Gdx.graphics.getWidth() / 500.0F * paramFloat;
//  }
//
//  private float worldYToScreenY(float paramFloat) {
//    return Gdx.graphics.getHeight() / 1000.0F * paramFloat;
//  }
//
//  public void dispose() {
//    this.stage.dispose();
//  }
//
//  public void handleInput() {}
//
//  public void render(SpriteBatch paramSpriteBatch) {
//    Gdx.input.setCatchKey(4, true);
//    if (Gdx.input.isKeyPressed(4)) {
//      this.pauseGame = true;
//      ((Actor)this.stage.getActors().get(0)).setVisible(true);
//      ((Actor)this.stage.getActors().get(1)).setVisible(false);
//      ((Actor)this.stage.getActors().get(24)).setVisible(true);
//      ((Actor)this.stage.getActors().get(25)).setVisible(true);
//      ((Actor)this.stage.getActors().get(26)).setVisible(true);
//    }
//    if (this.prefs.getBoolean("sound", true)) {
//      this.soundButton.setStyle((Button.ButtonStyle)this.soundButtonStyle);
//    } else {
//      this.soundButton.setStyle((Button.ButtonStyle)this.noSoundButtonStyle);
//    }
//    if (this.prefs.getBoolean("music", true)) {
//      this.musicButton.setStyle((Button.ButtonStyle)this.musicButtonStyle);
//    } else {
//      this.musicButton.setStyle((Button.ButtonStyle)this.noMusicButtonStyle);
//    }
//    paramSpriteBatch.begin();
//    if (this.showEndStory) {
//      int j = this.stageNumber;
//      if (j == 1) {
//        showEndStory(j);
//      } else if (j == 2) {
//        showEndStory(j);
//      }
//    } else {
//      int j = this.stageNumber;
//      if (j == 1) {
//        showStartStory(j);
//      } else if (j == 2) {
//        showStartStory(j);
//      }
//    }
//    if (this.prefs.getBoolean("firstTimeTutorial", true)) {
//      int j = getActiveBackgroundIdx(this.bgCords[0]);
//      paramSpriteBatch.draw((TextureRegion)this.background[j], this.bgCords[j], 0.0F, worldXToScreenX(500.0F), worldYToScreenY(1000.0F));
//      TextureAtlas.AtlasRegion[] arrayOfAtlasRegion = this.background;
//      paramSpriteBatch.draw((TextureRegion)arrayOfAtlasRegion[++j], this.bgCords[j] - worldXToScreenX(2.0F), 0.0F, worldXToScreenX(500.0F), worldYToScreenY(1000.0F));
//    }
//    this.shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
//    this.shapeRenderer.setColor(Color.RED);
//    int i = this.gameState;
//    if (i == 1) {
//      if (!this.pauseGame)
//        for (i = 0; i < 5; i++) {
//          float[] arrayOfFloat = this.bgCords;
//          arrayOfFloat[i] = arrayOfFloat[i] - (int)worldXToScreenX(3.0F) * this.dt * 60.0F;
//        }
//      if (this.bgCords[4] <= 0.0F)
//        for (i = 0; i < 5; i++)
//          this.bgCords[i] = (int)worldXToScreenX((i * 500));
//      i = getActiveBackgroundIdx(this.bgCords[0]);
//      paramSpriteBatch.draw((TextureRegion)this.background[i], this.bgCords[i], 0.0F, worldXToScreenX(500.0F), worldYToScreenY(1000.0F));
//      TextureAtlas.AtlasRegion[] arrayOfAtlasRegion = this.background;
//      paramSpriteBatch.draw((TextureRegion)arrayOfAtlasRegion[++i], this.bgCords[i] - worldXToScreenX(2.0F), 0.0F, worldXToScreenX(500.0F), worldYToScreenY(1000.0F));
//      if (!this.pauseGame) {
//        this.timerObjects += Gdx.graphics.getDeltaTime();
//        this.timerGame += Gdx.graphics.getDeltaTime();
//      }
//      if (this.prefs.getInteger("metresWithoutCoinsAtmAchievement", 0) < 6)
//        this.achievementsObject.checkMetresWithoutCoins(this.timerGame, this.coinObject.getCoinCount());
//      if (this.coinRushObject.isCoinRushEnd() && this.timerObjects - this.coinObject.getLastCoinTimer() >= this.coinObject.getCoinTime() / 1000.0F) {
//        this.coinObject.makeCoin();
//        this.coinObject.setLastCoinTimer(this.timerObjects);
//        if (!this.coinRushObject.isCoinRush())
//          this.coinObject.setCoinTime(this.random.nextInt(2001) + 1000 - (this.prefs.getInteger("spawnRateCoinsUpgraded") + this.coinSpawnUpgrade) * 50);
//      }
//      if (this.rocksGo) {
//        if (this.timerObjects - this.rockObject.getLastRockTimer() >= 2.0F) {
//          this.rockObject.makeRock();
//          this.rockObject.setLastRockTimer(this.timerObjects);
//        }
//        if (this.rockObject.isFirstRockHit() && this.timerObjects - this.rockObject.getFirstRockHitTimer() >= 5.0F) {
//          this.rockObject.setFirstRockHitFalse();
//          Preferences preferences = this.prefs;
//          preferences.putInteger("healedFromStones", preferences.getInteger("healedFromStones", 0) + 1);
//          this.prefs.flush();
//          if (this.prefs.getInteger("healStonesAtmAchievement", 0) < 7)
//            this.achievementsObject.checkHealStones(this.prefs.getInteger("healedFromStones", 0));
//        }
//      }
//      if (this.bombsGo && this.timerObjects - this.bombObject.getLastBombTimer() >= (this.random.nextInt(2001) + 5000) / 1000.0F) {
//        this.bombObject.makeBomb();
//        this.bombObject.setLastBombTimer(this.timerObjects);
//      }
//      if (this.spikesGo && this.timerObjects - this.spikesObject.getLastSpikeDownTimer() >= this.spikesObject.getSpikeDownTime() / 1000.0F) {
//        this.spikesObject.makeSpikeDown();
//        this.spikesObject.setSpikeDownTime(this.random.nextInt(6001) + 4000);
//        this.spikesObject.setLastSpikeDownTimer(this.timerObjects);
//      }
//      if (this.rocketsGo) {
//        if (this.timerObjects - this.rocketObject.getLastArrowRedTimer() >= 3.0F) {
//          Rocket rocket = this.rocketObject;
//          rocket.makeRocket(Float.valueOf(rocket.getRedHeight()));
//          rocket = this.rocketObject;
//          rocket.setRedHeight(rocket.makeRocketArrowRed());
//          this.rocketObject.setLastArrowRedTimer(this.timerObjects);
//        }
//        if (this.timerObjects - this.rocketObject.getLastArrowRedTimer() >= 2.0F && this.yellowGo && this.timerObjects - this.rocketObject.getLastArrowYellowTimer() >= 3.0F) {
//          Rocket rocket = this.rocketObject;
//          rocket.makeRocket(Float.valueOf(rocket.getYellowHeight()));
//          rocket = this.rocketObject;
//          rocket.setYellowHeight(rocket.makeRocketArrowYellow());
//          this.rocketObject.setLastArrowYellowTimer(this.timerObjects);
//        }
//        if (this.timerObjects - this.rocketObject.getLastArrowRedTimer() >= 1.0F && this.greenGo && this.timerObjects - this.rocketObject.getLastArrowGreenTimer() >= 3.0F) {
//          Rocket rocket = this.rocketObject;
//          rocket.makeRocket(Float.valueOf(rocket.getGreenHeight()));
//          rocket = this.rocketObject;
//          rocket.setGreenHeight(rocket.makeRocketArrowGreen());
//          this.rocketObject.setLastArrowGreenTimer(this.timerObjects);
//        }
//      }
//      float f = this.timerObjects;
//      if (f >= 10.0F && f - this.collectiblesTimer >= 1.5F) {
//        this.collectiblesChance = this.random.nextFloat() * 99.0F + 1.0F;
//        this.collectiblesTimer = this.timerObjects;
//      }
//      if (this.collectiblesChance <= this.normalChance + this.prefs.getInteger("spawnRateUpgraded")) {
//        i = this.collectiblesChoice;
//        if (i != 1) {
//          if (i != 2) {
//            if (i == 3 && !this.coinMagnetObject.isHasCoinMagnet())
//              this.coinMagnetObject.makeCoinMagnet();
//          } else if (!this.coinRushObject.isCoinRush()) {
//            this.coinRushObject.makeSpeedCoin();
//          }
//        } else if (!this.shieldObject.isHasShield()) {
//          this.shieldObject.makeShield();
//        }
//        this.collectiblesChance = 100.0F;
//        this.collectiblesChoice = this.random.nextInt(3) + 1;
//      }
//      if (this.shieldObject.isHasShield()) {
//        if (!this.onceShield) {
//          this.normalChance /= 2.0F;
//          this.onceShield = true;
//        }
//        if (this.timerObjects - this.shieldObject.getShieldOnTimer() >= this.prefs.getInteger("shieldUpgraded") + 7.0F + this.shieldUpgrade && this.timerObjects - this.shieldObject.getShieldOnTimer() < this.prefs.getInteger("shieldUpgraded") + 10.0F + this.shieldUpgrade)
//          if (this.shieldObject.getShieldStatePause() < 5) {
//            Shield shield = this.shieldObject;
//            shield.setShieldStatePause(shield.getShieldStatePause() + 1);
//          } else {
//            this.shieldObject.setShieldStatePause(0);
//            Shield shield = this.shieldObject;
//            shield.setShieldState(1 - shield.getShieldState());
//          }
//        if (this.timerObjects - this.shieldObject.getShieldOnTimer() >= this.prefs.getInteger("shieldUpgraded") + 10.0F + this.shieldUpgrade) {
//          this.shieldObject.setHasShieldFalse();
//          this.normalChance *= 2.0F;
//          this.onceShield = false;
//        }
//      }
//      if (!this.coinRushObject.isCoinRush() && this.timerObjects - this.coinRushObject.getCoinRushOnTimer() >= this.prefs.getInteger("coinRushUpgraded") + 8.0F + this.coinRushUpgrade && this.timerObjects - this.coinRushObject.getCoinRushOnTimer() < this.prefs.getInteger("coinRushUpgraded") + 8.2F + this.coinRushUpgrade) {
//        Coin coin = this.coinObject;
//        coin.setCoinSpeed(coin.worldXToScreenX(5.0F));
//        this.coinRushObject.setCoinRushEnd(true);
//      }
//      if (this.coinRushObject.isCoinRush()) {
//        if (!this.onceCoinRush) {
//          this.normalChance /= 2.0F;
//          this.onceCoinRush = true;
//        }
//        this.coinObject.setCoinTime(100);
//        Coin coin = this.coinObject;
//        coin.setCoinSpeed(coin.worldXToScreenX(10.0F));
//        if (this.timerObjects - this.coinRushObject.getCoinRushOnTimer() >= this.prefs.getInteger("coinRushUpgraded") + 5.0F + this.coinRushUpgrade) {
//          this.coinRushObject.setCoinRushFalse();
//          this.coinRushObject.setCoinRushEnd(false);
//          this.normalChance *= 2.0F;
//          this.onceCoinRush = false;
//        }
//      }
//      if (this.coinMagnetObject.isHasCoinMagnet()) {
//        if (!this.onceMagnet) {
//          this.normalChance /= 2.0F;
//          this.onceMagnet = true;
//        }
//        if (this.timerObjects - this.coinMagnetObject.getCoinMagnetOnTimer() >= this.prefs.getInteger("magnetUpgraded") + 10.0F + this.magnetUpgrade) {
//          this.coinMagnetObject.setHasCoinMagnet();
//          this.normalChance *= 2.0F;
//          this.onceMagnet = false;
//        }
//      }
//      if (this.coinMagnetObject.isHasCoinMagnet()) {
//        this.coinObject.drawCoinMagnetized(paramSpriteBatch, this.playerObject.getPlayerXRect(), this.playerObject.getPlayerY(), this.playerObject.getPlayerWidthRect(), this.playerObject.getPlayerHeightRect(), this.pauseGame);
//      } else {
//        this.coinObject.drawCoin(paramSpriteBatch, this.pauseGame);
//      }
//      this.coinObject.removeCoin();
//      i = this.stageNumber;
//      if (i == 1) {
//        this.rockObject.drawRock(paramSpriteBatch, this.pauseGame, this.shapeRenderer, 6.6F);
//        this.bombObject.drawBomb(paramSpriteBatch, this.pauseGame, this.shapeRenderer, 6.6F);
//      } else if (i == 2) {
//        this.rockObject.drawRock(paramSpriteBatch, this.pauseGame, this.shapeRenderer, 8.6F);
//        this.bombObject.drawBomb(paramSpriteBatch, this.pauseGame, this.shapeRenderer, 8.6F);
//        this.spikesObject.drawSpikeDown(paramSpriteBatch, this.pauseGame, this.shapeRenderer);
//      }
//      if (this.rocketsGo) {
//        this.rocketObject.redArrowState(this.timerObjects);
//        this.rocketObject.yellowArrowState(this.timerObjects);
//        this.rocketObject.greenArrowState(this.timerObjects);
//        this.rocketObject.drawArrows(this.firstArrowsAppearanceRed, this.firstArrowsAppearanceYellow, this.firstArrowsAppearanceGreen, paramSpriteBatch);
//        i = this.stageNumber;
//        if (i == 1) {
//          this.rocketObject.drawRocket(paramSpriteBatch, this.pauseGame, this.shapeRenderer, 12.45F);
//        } else if (i == 2) {
//          this.rocketObject.drawRocket(paramSpriteBatch, this.pauseGame, this.shapeRenderer, 15.0F);
//        }
//      }
//      this.shieldObject.drawShieldCollectible(paramSpriteBatch, this.pauseGame, this.shapeRenderer);
//      this.coinRushObject.drawSpeedCoinCollectible(paramSpriteBatch, this.pauseGame);
//      this.coinMagnetObject.drawCoinMagnetCollectible(paramSpriteBatch, this.pauseGame);
//      this.playerObject.drawPlayerRun(paramSpriteBatch, this.shieldObject, this.rockObject, this.pauseGame, this.shapeRenderer, this.prefs);
//      f = this.timerGame;
//      if (f >= 30.0F && f < 60.0F) {
//        this.rocksGo = false;
//        this.rockObject.setFirstRockHitFalse();
//        this.bombsGo = true;
//      }
//      if (this.stageNumber == 2)
//        this.spikesGo = true;
//      f = this.timerGame;
//      if (f >= 60.0F && f < 100.0F) {
//        this.bombsGo = false;
//        this.rocketsGo = true;
//        rocketFirstTime();
//      }
//      i = this.stageNumber;
//      if (i == 1) {
//        timingStory("storyModeCity");
//      } else if (i == 2) {
//        timingStory("storyModeDesert");
//      }
//      f = this.timerGame;
//      if ((f - this.scoreTimer) >= 0.3D) {
//        this.score++;
//        this.scoreTimer = f;
//      }
//    } else if (i == 0) {
//      i = getActiveBackgroundIdx(this.bgCords[0]);
//      paramSpriteBatch.draw((TextureRegion)this.background[i], this.bgCords[i], 0.0F, worldXToScreenX(500.0F), worldYToScreenY(1000.0F));
//      TextureAtlas.AtlasRegion[] arrayOfAtlasRegion = this.background;
//      paramSpriteBatch.draw((TextureRegion)arrayOfAtlasRegion[++i], this.bgCords[i] - worldXToScreenX(2.0F), 0.0F, worldXToScreenX(500.0F), worldYToScreenY(1000.0F));
//      this.playerObject.drawPlayerStart(paramSpriteBatch, this.prefs);
//      ((Actor)this.stage.getActors().get(1)).setVisible(false);
//      this.showScoreAndCoinLabel = false;
//      if (Gdx.input.justTouched()) {
//        this.gameState = 1;
//        ((Actor)this.stage.getActors().get(1)).setVisible(true);
//        this.showScoreAndCoinLabel = true;
//      }
//    } else if (i == 2) {
//      i = getActiveBackgroundIdx(this.bgCords[0]);
//      paramSpriteBatch.draw((TextureRegion)this.background[i], this.bgCords[i], 0.0F, worldXToScreenX(500.0F), worldYToScreenY(1000.0F));
//      TextureAtlas.AtlasRegion[] arrayOfAtlasRegion = this.background;
//      paramSpriteBatch.draw((TextureRegion)arrayOfAtlasRegion[++i], this.bgCords[i] - worldXToScreenX(2.0F), 0.0F, worldXToScreenX(500.0F), worldYToScreenY(1000.0F));
//      this.playerObject.drawPlayerFaint(paramSpriteBatch, this.pauseGame, this.prefs);
//      boolean bool1 = this.mAdsController.getAdLoaded();
//      boolean bool2 = this.mAdsController.getRewardReceived();
//      ((Actor)this.stage.getActors().get(1)).setVisible(false);
//      ((Actor)this.stage.getActors().get(6)).setVisible(true);
//      this.showScoreAndCoinLabel = false;
//      if (this.twoTimesAd < 2 && bool1) {
//        ((Actor)this.stage.getActors().get(6)).setTouchable(Touchable.enabled);
//        this.continueWithAdsButton.setStyle((Button.ButtonStyle)this.continueWithAdsButtonStyle);
//      } else if (this.twoTimesAd < 2) {
//        ((Actor)this.stage.getActors().get(6)).setTouchable(Touchable.disabled);
//        this.continueWithAdsButton.setStyle((Button.ButtonStyle)this.continueWithAdsWaitButtonStyle);
//      }
//      if (bool2) {
//        this.twoTimesAd++;
//        this.gameState = 0;
//        resetAfterRevive();
//        ((Actor)this.stage.getActors().get(0)).setVisible(false);
//        ((Actor)this.stage.getActors().get(6)).setVisible(false);
//        ((Actor)this.stage.getActors().get(7)).setVisible(false);
//        ((Actor)this.stage.getActors().get(8)).setVisible(false);
//        this.mAdsController.setRewardReceived(false);
//        ((Actor)this.stage.getActors().get(23)).setVisible(false);
//        Preferences preferences = this.prefs;
//        preferences.putInteger("revivedAfterDeath", preferences.getInteger("revivedAfterDeath", 0) + 1);
//        preferences = this.prefs;
//        preferences.putInteger("adsWatched", preferences.getInteger("adsWatched", 0) + 1);
//        this.prefs.flush();
//      } else {
//        ((Actor)this.stage.getActors().get(8)).setVisible(true);
//        ((Actor)this.stage.getActors().get(7)).setVisible(true);
//        if (this.showDiamondsCount)
//          ((Actor)this.stage.getActors().get(23)).setVisible(true);
//      }
//    } else if (i == 3) {
//      if (this.timerGame <= 100.8F)
//        for (i = 0; i < 5; i++) {
//          float[] arrayOfFloat = this.bgCords;
//          arrayOfFloat[i] = arrayOfFloat[i] - (int)worldXToScreenX(3.0F) * this.dt * 60.0F;
//        }
//      if (this.bgCords[4] <= 0.0F)
//        for (i = 0; i < 5; i++)
//          this.bgCords[i] = (int)worldXToScreenX((i * 500));
//      i = getActiveBackgroundIdx(this.bgCords[0]);
//      paramSpriteBatch.draw((TextureRegion)this.background[i], this.bgCords[i], 0.0F, worldXToScreenX(500.0F), worldYToScreenY(1000.0F));
//      TextureAtlas.AtlasRegion[] arrayOfAtlasRegion = this.background;
//      paramSpriteBatch.draw((TextureRegion)arrayOfAtlasRegion[++i], this.bgCords[i] - worldXToScreenX(2.0F), 0.0F, worldXToScreenX(500.0F), worldYToScreenY(1000.0F));
//      this.playerObject.drawPlayerWin(paramSpriteBatch);
//      this.villainObject.drawVillain(paramSpriteBatch, this.pauseGame);
//      float f = this.timerGame;
//      if (f >= 102.0F) {
//        this.showEndStory = true;
//      } else {
//        this.timerGame = f + Gdx.graphics.getDeltaTime();
//      }
//    }
//    this.coinObject.coinCollision(this.playerObject.getPlayerRectangle(), this.prefs.getBoolean("doubleCoins", false));
//    if (!this.shieldObject.isHasShield()) {
//      if (this.rockObject.isFirstRockHit()) {
//        this.gameState = this.rockObject.rockCollisionSecond(this.playerObject.getPlayerRectangle(), this.gameState, this.prefs);
//      } else {
//        this.rockObject.rockCollisionFirst(this.playerObject.getPlayerRectangle(), this.timerObjects);
//      }
//      this.gameState = this.bombObject.bombCollision(this.playerObject.getPlayerRectangle(), this.gameState, this.prefs);
//      this.gameState = this.spikesObject.spikeDownCollision(this.playerObject.getPlayerRectangle(), this.gameState);
//      this.gameState = this.rocketObject.rocketCollision(this.playerObject.getPlayerRectangle(), this.gameState, this.prefs);
//    }
//    this.shieldObject.shieldCollision(this.playerObject.getPlayerRectangle(), this.timerObjects, this.prefs);
//    this.coinRushObject.speedCoinCollision(this.playerObject.getPlayerRectangle(), this.timerObjects, this.prefs);
//    this.coinMagnetObject.coinMagnetCollision(this.playerObject.getPlayerRectangle(), this.timerObjects, this.prefs);
//    if (this.showScoreAndCoinLabel) {
//      paramSpriteBatch.draw((TextureRegion)this.scoreAndCoinBackground, worldXToScreenX(-25.0F), worldYToScreenY(950.0F), worldXToScreenX(125.0F), worldYToScreenY(50.0F));
//      paramSpriteBatch.draw((TextureRegion)this.scoreAndCoinBackground, worldXToScreenX(-25.0F), worldYToScreenY(900.0F), worldXToScreenX(125.0F), worldYToScreenY(50.0F));
//      this.scoreAndCoinFont.draw((Batch)paramSpriteBatch, String.valueOf(this.score), worldXToScreenX(10.0F), worldYToScreenY(985.0F));
//      this.scoreAndCoinFont.draw((Batch)paramSpriteBatch, String.valueOf(this.coinObject.getCoinCount()), worldXToScreenX(30.0F), worldYToScreenY(935.0F));
//      paramSpriteBatch.draw((TextureRegion)this.coin, worldXToScreenX(0.0F), worldYToScreenY(910.0F), worldXToScreenX(30.0F), worldYToScreenY(30.0F));
//    }
//    paramSpriteBatch.end();
//    this.shapeRenderer.end();
//    this.stage.act(Gdx.graphics.getDeltaTime());
//    this.stage.draw();
//  }
//
//  public void update(float paramFloat) {}
//}
//
//
///* Location:              C:\Users\nikol\Desktop\dex-tools-2.1-SNAPSHOT\kiki-dex2jar.jar!\com\zappycode\coinman\game\MainGame.class
// * Java compiler version: 6 (50.0)
// * JD-Core Version:       1.1.3
// */